package dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import config.ConfigurationFile;
import model.User;

import java.sql.*;

/**
 * @author Zimi Li
 */
public class UserDaoImpl extends Command implements UserDao {

    @Override
    public Boolean login(User user) {
        ResultSet rs = select("SELECT * FROM ers_users WHERE ers_username = ?;",
                              user.getUsername());
        try {
            if (rs.next()) {
                String bcryptHashString = rs.getString("ers_password");
                BCrypt.Result result = BCrypt.verifyer().verify(user.getPassword().toCharArray(), bcryptHashString);
                if (result.verified) {
                    user.setId(rs.getInt("ers_users_id"));
                    user.setFirstname(rs.getString("user_first_name"));
                    user.setLastname(rs.getString("user_last_name"));
                    user.setEmail(rs.getString("user_email"));
                    user.setRole(rs.getInt("user_role_id"));
                    return true;
                }
            }
        } catch (SQLException e) {
            ConfigurationFile.SQL_LOGGER.error(e, e.fillInStackTrace());
        }

        return false;
    }

    @Override
    public Boolean register(User user) {
        return update("INSERT INTO ers_users VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);",
                      user.getUsername(), user.getPassword(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getRole());
    }


}
