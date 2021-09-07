package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Response;
import model.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import service.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zimi Li
 */
class RoleControllerTest {
    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
    RoleController roleController = RoleController.getInstance();

    @Test
    void viewAllRoles() throws IOException {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1, "Employee"));
        roles.add(new Role(2, "Finance Manager"));

        StringWriter sw = new StringWriter();
        Mockito.when(resp.getWriter()).thenReturn(new PrintWriter(sw));

        roleController.viewAllRoles(req, resp);

        StringWriter expected = new StringWriter();
        expected.write(new ObjectMapper().writeValueAsString(new Response(true, "Succeed", roles)));
        expected.flush();

        assertEquals(sw.toString(), expected.toString());
    }
}