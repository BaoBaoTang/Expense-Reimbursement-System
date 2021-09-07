package dao;

import model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zimi Li
 */
class UserDaoImplTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    void login() {
        User user = new User();
        user.setUsername("zimi");
        user.setPassword("OPHHiRHj](2r=N:d");
        assertTrue(userDao.login(user));
    }
}