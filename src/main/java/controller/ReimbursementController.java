package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigurationFile;
import model.*;
import service.ReimbursementService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zimi Li
 */
public class ReimbursementController {
    private static ReimbursementController instance;
    private ReimbursementService reimbursementService;

    private ReimbursementController() {
        reimbursementService = new ReimbursementService();
    }

    public static ReimbursementController getInstance() {
        if (instance == null) instance = new ReimbursementController();
        return instance;
    }

    public void approveReimbursement(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("User");
        if (user != null && user.getRole().equals(ConfigurationFile.MANAGER) && reimbursementService.approveReimbursement(Integer.parseInt(req.getParameter("id")), user.getId()))
            resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(true, "Approve Succeed", null)));
        else
            resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(false, "Approve Failed", null)));
    }

    public void denyReimbursement(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("User");
        if ( user != null && user.getRole().equals(ConfigurationFile.MANAGER) && reimbursementService.denyReimbursement(Integer.parseInt(req.getParameter("id")), user.getId()))
            resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(true, "Deny Succeed", null)));
        else
            resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(false, "Deny Failed", null)));
    }

    public void createReimbursement(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Reimbursement reimbursement = new ObjectMapper().readValue(body, Reimbursement.class);
        User user = (User) req.getSession().getAttribute("User");
        if (user == null) {
            resp.getWriter().printf(new ObjectMapper().writeValueAsString(new Response(false, "Create Reimbursement Failed", null)));
            return;
        }
        reimbursement.setAuthor(user.getId());

        if (reimbursement.getHasReceipt()) reimbursement.setReceipt(ConfigurationFile.TEMP_FILE);

        if (reimbursementService.createReimbursement(reimbursement)) {
            resp.getWriter().printf(new ObjectMapper().writeValueAsString(new Response(true, "Create Reimbursement Succeed, redirecting in 3s...", null)));
        } else {
            resp.getWriter().printf(new ObjectMapper().writeValueAsString(new Response(false, "Create Reimbursement Failed", null)));
        }
    }

    public void viewReimbursement(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("User");

        List<ReimbursementView> reimbursementViews = null;
        if (user != null) {
            if (user.getRole().equals(ConfigurationFile.MANAGER)) {
                reimbursementViews = reimbursementService.getReimbursement(null);
            } else {
                reimbursementViews = reimbursementService.getReimbursement(user.getId());
            }
        }
        if (reimbursementViews == null) {
            resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(false, "Retrieve List Failed", null)));
        } else {
            resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(true, "", reimbursementViews)));
        }
    }

    public void viewReimbursementDetails(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer reimbursementID = Integer.parseInt(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute("User");
        ReimbursementDetailView reimbursementDetailView = reimbursementService.getReimbursementDetails(reimbursementID);

        if (reimbursementDetailView == null || user == null || (!reimbursementDetailView.getAuthorID().equals(user.getId()) && !user.getRole().equals(ConfigurationFile.MANAGER))) {
            resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(false, "Retrieve Detail Failed", null)));
        } else {
            resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(true, "", reimbursementDetailView)));

        }
    }
}

