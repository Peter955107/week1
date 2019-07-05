package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/postbody")
public class PostBody extends HttpServlet {
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");
        out.println(bodyContent(request.getReader()));
        out.println("</body>");
        out.println("</html>");
    }

    private String bodyContent(BufferedReader reader) throws IOException {
        String input = null;
        StringBuilder requestBody = new StringBuilder();
        while((input = reader.readLine()) != null) {
            requestBody.append(input)
                       .append("<br>");
        }
        return requestBody.toString();
    } 
}