package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(
    urlPatterns = {"/avatar"},
    initParams = {
        @WebInitParam(name = "AVATAR_DIR", value = "/")
    }
)
public class Avatar extends HttpServlet {
    private String AVATAR_DIR;

    @Override
    public void init() throws ServletException {
        super.init();
        AVATAR_DIR = getInitParameter("AVATAR_DIR");
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");

        getServletContext().getResourcePaths(AVATAR_DIR)
        					.forEach(out::println);

        out.println("</body>");
        out.println("</html>");
    }
}