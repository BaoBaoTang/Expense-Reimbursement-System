package frontendcontroller;

import config.ConfigurationFile;
import controller.ReimbursementController;
import controller.ReimbursementTypeController;
import controller.RoleController;
import controller.UserController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zimi Li
 */

@WebServlet("/api/*")
public class APIDispatcher extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String path = req.getRequestURI();
        path = path.substring(ConfigurationFile.CONTEXT_PATH.length());

        switch (path) {
            case "/api/user":
                if (req.getMethod().equals("GET")) UserController.getInstance().getUser(req, resp);
                break;
            case "/api/user/login":
                if (req.getMethod().equals("POST")) UserController.getInstance().login(req, resp);
                break;
            case "/api/user/logout":
                if (req.getMethod().equals("GET")) UserController.getInstance().logout(req, resp);
                break;
            case "/api/user/register":
                if (req.getMethod().equals("POST")) UserController.getInstance().register(req, resp);
                break;
            case "/api/reimbursement/approve":
                if (req.getMethod().equals("PATCH")) ReimbursementController.getInstance().approveReimbursement(req, resp);
                break;
            case "/api/reimbursement/deny":
                if (req.getMethod().equals("PATCH")) ReimbursementController.getInstance().denyReimbursement(req, resp);
                break;
            case "/api/reimbursement/create":
                if (req.getMethod().equals("POST")) ReimbursementController.getInstance().createReimbursement(req, resp);
                break;
            case "/api/reimbursement/view":
                if (req.getMethod().equals("GET")) ReimbursementController.getInstance().viewReimbursement(req, resp);
                break;
            case "/api/reimbursement/detail-view":
                if (req.getMethod().equals("GET")) ReimbursementController.getInstance().viewReimbursementDetails(req, resp);
                break;
            case "/api/role/view":
                if (req.getMethod().equals("GET")) RoleController.getInstance().viewAllRoles(req, resp);
                break;
            case "/api/type/view":
                if (req.getMethod().equals("GET")) ReimbursementTypeController.getInstance().viewAllTypes(req, resp);
                break;
        }
    }
}
