package javasamplers.apps.dbdemo;

import javasamplers.apps.dbdemo.db.UserDao;
import javasamplers.apps.dbdemo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class DbDemoApp implements ApplicationRunner
{
    private static final Logger LOG = LoggerFactory.getLogger(DbDemoApp.class);

    public static void main( String[] args )
    {
        // Starting point
        SpringApplication.run(DbDemoApp.class, args);
    }

    @Autowired
    UserDao userDao;

    public void run(ApplicationArguments applicationArguments) throws Exception {
        // Entry point
        LOG.info("Successfully started demo app");

        Long id = userDao.createUser(new User(null, "test", LocalDateTime.now()));
        LOG.info("Successfully inserted with id {}", id);

        User user = userDao.getUser(id);
        LOG.info("Found {}", user);
    }
}
