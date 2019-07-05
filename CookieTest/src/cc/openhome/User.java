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

        Optional<Cookie> userCookie = 
                Optional.ofNullable(request.getCookies())
                        .flatMap(this::userCookie);

        if(userCookie.isPresent()) {
            Cookie cookie = userCookie.get();
            request.setAttribute(cookie.getName(), cookie.getValue());
            userHtml(request, response);
        } else {
            response.sendRedirect("login.html");
        }

    }

    private Optional<Cookie> userCookie(Cookie[] cookies) {
        return Stream.of(cookies)
                     .filter(cookie -> "user".equals(cookie.getName()) && "caterpillar".equals(cookie.getValue()))
                     .findFirst();
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
        out.println("<h1>" + request.getAttribute("user") + "已登入</h1>");
        out.println("</body>");
        out.println("</html>");
    }
} 