package javasamplers.apps.dbdemo.db.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by ocean on 12/13/2017.
 */
@Component
public class JdbcHelper {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    JdbcHelper(JdbcTemplate jdbcTemplate){
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
        return namedJdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return  (JdbcTemplate) namedJdbcTemplate.getJdbcOperations();
    }
}
