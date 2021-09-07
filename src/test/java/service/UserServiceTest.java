package service;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zimi Li
 */
class UserServiceTest {

    UserDao userDao = Mockito.mock(UserDaoImpl.class);
    UserService userService = new UserService(userDao);

    @Test
    void login() {
        User user = new User();
        user.setUsername("ers1");
        user.setPassword("password");

        Mockito.when(userDao.login(user)).thenReturn(true);

        assertTrue(userService.login(user));
        Mockito.verify(userDao, Mockito.times(1)).login(user);
    }
}