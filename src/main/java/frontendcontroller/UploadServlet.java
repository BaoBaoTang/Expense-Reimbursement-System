package frontendcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigurationFile;
import model.Response;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Zimi Li
 */
@WebServlet("/api/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        try {
            for (Part part : req.getParts()) {
                InputStream is = part.getInputStream();
                ConfigurationFile.TEMP_FILE = new byte[is.available()];
                while (is.read(ConfigurationFile.TEMP_FILE)>0);
            }
        } catch (Exception e) {
            resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(false, "Upload Failed", null)));
            ConfigurationFile.FILE_LOGGER.error(e, e.fillInStackTrace());
            return;
        }

        resp.getWriter().println(new ObjectMapper().writeValueAsString(new Response(true, "Upload Succeed", null)));
    }
}
