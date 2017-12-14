package javasamplers.apps.dbdemo.db.jdbc;

import io.reactivex.functions.Function;
import javasamplers.apps.dbdemo.db.UserDao;
import javasamplers.apps.dbdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by ocean on 12/13/2017.
 */
@Component
public class UserJdbcDao implements UserDao {
    private final JdbcHelper jdbc;

    @Autowired
    UserJdbcDao(JdbcHelper jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Long createUser(User user) {

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        String sql = "insert into user (name, created_at) values (:name, :created_at)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", user.getName());
        params.addValue("created_at", user.getCreated());
        jdbc.getNamedJdbcTemplate().update(sql, params, holder);

        return holder.getKey().longValue();
    }

    @Override
    public User getUser(Long id) {

        String sql = "select id, name, created_at from user where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        Collector<User> collector = new Collector<>(3, c -> new User(c.getLong(0), c.getString(1), c.getDateTime(2)));

        jdbc.getNamedJdbcTemplate().query(sql, params, collector);

        return collector.asData();
    }

    private static class Collector<T> implements RowCallbackHandler {

        private final int arity;
        private final Function<Collector, T> mapper;
        private Object[] data;

        public Collector(int arity, Function<Collector, T> mapper) {
            this.arity = arity;
            this.data = new Object[arity];
            this.mapper = mapper;
        }

        // TODO: This can be called many times
        @Override
        public void processRow(ResultSet rs) throws SQLException {
            for(int i = 1; i <= arity; i++) {
                data[i - 1] = rs.getObject(i);
            }
        }

        public Long getLong(int i) {
            return (Long) data[i];
        }

        public Integer getInteger(int i) {
            return (Integer) data[i];
        }

        public String getString(int i) {
            return (String) data[i];
        }

        public LocalDateTime getDateTime(int i) {
            Date date = ((java.sql.Date) data[i]);
            return new java.util.Date(date.getTime())
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }

        public T asData() {
            try {
                return mapper.apply(this);
            } catch (Exception e) {
                // Do nothing
                e.printStackTrace();
                return null;
            }
        }

    }


}
