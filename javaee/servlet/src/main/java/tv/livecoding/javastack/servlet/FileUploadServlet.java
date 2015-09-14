package tv.livecoding.javastack.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Created by Eugene Orel on 9/14/2015.
 */
@WebServlet("/fileUpload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    // GET doesn't work with multipart requests

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Part part : req.getParts()) {
            resp.getWriter().print(part.getName() + ":" + part.getSize() + ";");
        }
    }
}
