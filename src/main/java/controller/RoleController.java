package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Response;
import service.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zimi Li
 */
public class RoleController {
    private static RoleController instance;
    private RoleService roleService;

    private RoleController() {
        roleService = new RoleService();
    }

    public static RoleController getInstance() {
        if (instance == null) instance = new RoleController();
        return instance;
    }

    public void viewAllRoles(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().print(new ObjectMapper().writeValueAsString(new Response(true, "Succeed", roleService.getRoles())));
    }

}
