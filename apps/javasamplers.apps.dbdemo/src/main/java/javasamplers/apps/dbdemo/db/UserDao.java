package javasamplers.apps.dbdemo.db;

import javasamplers.apps.dbdemo.model.User;

/**
 * Created by ocean on 12/13/2017.
 */
public interface UserDao {
    Long createUser(User user);

    User getUser(Long id);
}
