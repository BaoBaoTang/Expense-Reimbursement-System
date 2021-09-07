package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigurationFile;
import model.Response;
import model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zimi Li
 */
class UserControllerTest {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
    HttpSession session = Mockito.mock(HttpSession.class);
    UserController userController = UserController.getInstance();

    @Test
    void login() throws IOException {
        User user = new User();
        user.setUsername("zimi");
        user.setPassword("OPHHiRHj](2r=N:d");
        StringWriter userString = new StringWriter();
        userString.write(new ObjectMapper().writeValueAsString(user));
        BufferedReader reader = new BufferedReader(new StringReader(userString.toString()));
        Mockito.when(req.getReader()).thenReturn(reader);

        StringWriter sw = new StringWriter();
        Mockito.when(resp.getWriter()).thenReturn(new PrintWriter(sw));

        Mockito.when(req.getSession(true)).thenReturn(session);


        userController.login(req, resp);

        StringWriter expected = new StringWriter();
        expected.write(new ObjectMapper().writeValueAsString(new Response(true, "Login Succeed", null)));
        expected.write("\n");
        expected.flush();

        assertEquals(sw.toString(), expected.toString());
        Mockito.verify(session, Mockito.times(1)).setAttribute(Mockito.isA(String.class), Mockito.isA(User.class));
    }

    @Test
    void loginTest2() throws IOException {
        User user = new User();
        user.setUsername("ers1");
        user.setPassword("incorrect");
        StringWriter userString = new StringWriter();
        userString.write(new ObjectMapper().writeValueAsString(user));
        BufferedReader reader = new BufferedReader(new StringReader(userString.toString()));
        Mockito.when(req.getReader()).thenReturn(reader);

        StringWriter sw = new StringWriter();
        Mockito.when(resp.getWriter()).thenReturn(new PrintWriter(sw));

        userController.login(req, resp);

        StringWriter expected = new StringWriter();
        expected.write(new ObjectMapper().writeValueAsString(new Response(false, "Login Failed", null)));
        expected.write("\n");
        expected.flush();

        assertEquals(sw.toString(), expected.toString());
    }


    @Test
    void getUser() throws IOException {
        Mockito.when(session.getAttribute("User")).thenReturn(null);
        Mockito.when(req.getSession()).thenReturn(session);
        StringWriter sw = new StringWriter();
        Mockito.when(resp.getWriter()).thenReturn(new PrintWriter(sw));

        userController.getUser(req, resp);

        StringWriter expected = new StringWriter();
        expected.write(new ObjectMapper().writeValueAsString(new Response(false, "", null)));
        expected.write("\n");
        expected.flush();

        assertEquals(sw.toString(), expected.toString());
    }

    @Test
    void getUserTest2() throws IOException {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setRole(1);
        user.setEmail("test@revature.com");

        Mockito.when(session.getAttribute("User")).thenReturn(user);
        Mockito.when(req.getSession()).thenReturn(session);
        StringWriter sw = new StringWriter();
        Mockito.when(resp.getWriter()).thenReturn(new PrintWriter(sw));

        userController.getUser(req, resp);

        StringWriter expected = new StringWriter();
        expected.write(new ObjectMapper().writeValueAsString(new Response(true, ConfigurationFile.MANAGER.toString(), req.getSession().getAttribute("User"))));
        expected.write("\n");
        expected.flush();

        assertEquals(sw.toString(), expected.toString());
    }

    @Test
    void logout() throws IOException {
        Mockito.when(req.getSession()).thenReturn(session);

        StringWriter sw = new StringWriter();
        Mockito.when(resp.getWriter()).thenReturn(new PrintWriter(sw));

        userController.logout(req, resp);

        StringWriter expected = new StringWriter();
        expected.write(new ObjectMapper().writeValueAsString(new Response(true, "Logout Succeed, redirecting in 3s...", null)));
        expected.write("\n");
        expected.flush();
        assertEquals(sw.toString(), expected.toString());
        Mockito.verify(session, Mockito.times(1)).invalidate();
    }

}