package frontendcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigurationFile;
import model.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zimi Li
 */
class UploadServletTest {
    UploadServlet uploadServlet = new UploadServlet();
    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);

    @Test
    void doPost() throws IOException, ServletException {
        byte[] file = {14, 13};
        List<Part> parts = new ArrayList<>();
        Part part = Mockito.mock(Part.class);
        Mockito.when(part.getInputStream()).thenReturn(new ByteArrayInputStream(file));
        parts.add(part);
        Mockito.when(req.getParts()).thenReturn(parts);
        StringWriter sw = new StringWriter();
        Mockito.when(resp.getWriter()).thenReturn(new PrintWriter(sw));

        uploadServlet.doPost(req, resp);

        StringWriter expected = new StringWriter();
        expected.write(new ObjectMapper().writeValueAsString(new Response(true, "Upload Succeed", null)));
        expected.write("\n");
        expected.flush();

        assertEquals(sw.toString(), expected.toString());
        assertArrayEquals(file, ConfigurationFile.TEMP_FILE);
    }
}