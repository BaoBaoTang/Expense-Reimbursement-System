package service;

import dao.RoleDao;
import dao.RoleDaoImpl;
import model.Role;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zimi Li
 */
class RoleServiceTest {

    RoleDao roleDao = Mockito.mock(RoleDaoImpl.class);
    RoleService roleService = new RoleService(roleDao);

    @Test
    void getRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1, "Employee"));
        roles.add(new Role(2, "Finance Manager"));

        Mockito.when(roleDao.getRoles()).thenReturn(roles);

        assertEquals(roleService.getRoles(), roles);
        Mockito.verify(roleDao, Mockito.times(1)).getRoles();
    }
}