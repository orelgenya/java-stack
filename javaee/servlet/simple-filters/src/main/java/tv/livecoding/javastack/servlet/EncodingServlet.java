package tv.livecoding.javastack.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by OrelGenya on 11.09.2015.
 */
@WebServlet(value = {"/encoding"},
        initParams = {
                @WebInitParam(name = EncodingServlet.INITIAL_PARAM_TYPE, value = EncodingServlet.INITIAL_PARAM_VALUE)
        })
public class EncodingServlet extends HttpServlet {
    public static final String INITIAL_PARAM_VALUE = "initial параметр";
    public static final String INITIAL_PARAM_TYPE = "type";
    public static final String POST_RESPONSE = "мой post";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(getInitParameter(INITIAL_PARAM_TYPE));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(POST_RESPONSE);
    }
}
