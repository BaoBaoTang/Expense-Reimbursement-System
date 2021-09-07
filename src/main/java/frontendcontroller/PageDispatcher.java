package frontendcontroller;

import config.ConfigurationFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zimi Li
 */

@WebServlet({"", "/dashboard", "/register", "/reimbursement", "/details"})
public class PageDispatcher extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();
        path = path.substring(ConfigurationFile.CONTEXT_PATH.length());

        switch (path) {
            case "/":
            case "/dashboard":
                req.getRequestDispatcher(req.getSession().getAttribute("User") == null ? "./html/homepage.html" : "./html/dashboard.html").forward(req, resp);
                break;
            case "/register":
                req.getRequestDispatcher("./html/register.html").forward(req, resp);
                break;
            case "/reimbursement":
                req.getRequestDispatcher(req.getSession().getAttribute("User") == null ? "./html/homepage.html" : "./html/reimbursement.html").forward(req, resp);
                break;
            case "/details":
                req.getRequestDispatcher(req.getSession().getAttribute("User") == null ? "./html/homepage.html" : "./html/details.html").forward(req, resp);
                break;
        }
    }
}
