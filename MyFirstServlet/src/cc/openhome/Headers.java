package cc.openhome;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/headers")
public class Headers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet ShowHeader</title>");
        out.println("</head>");
        out.println("<body>");

        Collections.list(request.getHeaderNames())
                   .forEach(name -> {
                       out.printf("%s: %s<br>%n", name, request.getHeader(name));
                   });

        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}