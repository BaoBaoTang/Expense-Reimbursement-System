package service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.UserDao;
import dao.UserDaoImpl;
import model.User;

/**
 * @author Zimi Li
 */
public class UserService {

    UserDao userDao;

    public UserService() {
        userDao = new UserDaoImpl();
    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Boolean login(User user) {
        return userDao.login(user);
    }

    public Boolean register(User user) {
        String password = PasswordService.generatePassword();
        user.setPassword(BCrypt.withDefaults().hashToString(12, password.toCharArray()));
        if (userDao.register(user)) {
            PasswordService.sendMail(user.getEmail(), password);
            return true;
        }
        return false;
    }


}
