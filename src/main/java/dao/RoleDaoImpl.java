package dao;

import config.ConfigurationFile;
import model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zimi Li
 */
public class RoleDaoImpl extends Command implements RoleDao {

    @Override
    public List<Role> getRoles() {
        ResultSet rs = select("SELECT * FROM ers_user_roles;");
        List<Role> ret = new ArrayList<>();
        try {
            while (rs.next()) {
                ret.add(new Role(rs.getInt("ers_user_role_id"), rs.getString("user_role")));
            }
        } catch (SQLException e) {
            ConfigurationFile.SQL_LOGGER.error(e, e.fillInStackTrace());
        }
        return ret;
    }
}
