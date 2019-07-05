package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                      throws ServletException, IOException {
        String name = request.getParameter("name");
        String passwd = request.getParameter("passwd");
        if("caterpillar".equals(name) && "123456".equals(passwd)) {
            Cookie cookie = new Cookie("user", "caterpillar");
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);
            response.sendRedirect("user");
        }
        else {
            response.sendRedirect("login.html");
        }
    }
} 