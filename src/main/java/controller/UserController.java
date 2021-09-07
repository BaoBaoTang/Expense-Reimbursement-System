package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigurationFile;
import model.Response;
import model.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * @author Zimi Li
 */
public class UserController {

    private static UserController instance;
    private UserService userService;

    private UserController() {
        userService = new UserService();
    }

    public static UserController getInstance() {
        if (instance == null) instance = new UserController();
        return instance;
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        User user = new ObjectMapper().readValue(body, User.class);

        if (userService.register(user)) {
            resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(true, "Register Succeed, redirecting in 3s...", null)));
        } else {
            resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(false, "Register Failed", null)));
        }
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        User user = new ObjectMapper().readValue(body, User.class);

        if (userService.login(user)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("User", user);
            resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(true, "Login Succeed", null)));
        } else {
            resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(false, "Login Failed", null)));
        }
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(true, "Logout Succeed, redirecting in 3s...", null)));
    }

    public void getUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("User");
        if (user == null)
            resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(false, "", null)));
        else
            resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(true, ConfigurationFile.MANAGER.toString(), req.getSession().getAttribute("User"))));
    }
}
