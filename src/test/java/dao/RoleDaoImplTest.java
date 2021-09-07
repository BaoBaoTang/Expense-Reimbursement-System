package dao;

import model.Role;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zimi Li
 */
class RoleDaoImplTest {

    RoleDao roleDao = new RoleDaoImpl();

    @Test
    void getRoles() {
        List<Role> expected = new ArrayList<>();
        expected.add(new Role(1, "Employee"));
        expected.add(new Role(2, "Finance Manager"));

        List<Role> actual = roleDao.getRoles();
        assertEquals(expected.toString(), actual.toString());
    }
}