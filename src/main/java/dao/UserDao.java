package dao;

import model.User;

/**
 * @author Zimi Li
 */
public interface UserDao {

    Boolean login(User user);

    Boolean register(User user);
}
