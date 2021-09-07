package service;

import dao.RoleDao;
import dao.RoleDaoImpl;
import model.Role;

import java.util.List;

/**
 * @author Zimi Li
 */
public class RoleService {

    RoleDao roleDao;

    public RoleService() {
        roleDao = new RoleDaoImpl();
    }

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public List<Role> getRoles() {
        return roleDao.getRoles();
    }
}
