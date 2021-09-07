package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Response;
import service.ReimbursementTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zimi Li
 */
public class ReimbursementTypeController {
    private static ReimbursementTypeController instance;
    private ReimbursementTypeService reimbursementTypeService;

    private ReimbursementTypeController() {
        reimbursementTypeService = new ReimbursementTypeService();
    }

    public static ReimbursementTypeController getInstance() {
        if (instance == null) instance = new ReimbursementTypeController();
        return instance;
    }


    public void viewAllTypes(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(true, "Succeed", reimbursementTypeService.getTypes())));
    }
}
