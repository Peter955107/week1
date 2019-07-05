package cc.openhome;

import java.io.*;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/user")
public class User extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                       throws ServletException, IOException {

        HttpSession session = request.getSession();
        Optional<Object> token = Optional.ofNullable(session.getAttribute("login"));

        if(token.isPresent()) {
            userHtml(request, response);
        } else {
            response.sendRedirect("login.html");
        }
    }

    private void userHtml(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>" + request.getSession().getAttribute("login") + "已登入</h1><br>");
        out.println("<a href='logout'>登出</a>");
        out.println("</body>");
        out.println("</html>");
    }
} 