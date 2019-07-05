package cc.openhome;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(
    urlPatterns = {"/login"},
    initParams = {
        @WebInitParam(name = "SUCCESS", value = "user"),
        @WebInitParam(name = "ERROR", value = "login.html")
    }
)
public class Login extends HttpServlet {
    private String SUCCESS_VIEW;
    private String ERROR_VIEW;

    @Override
    public void init() throws ServletException {
        super.init();
        SUCCESS_VIEW = this.getInitParameter("SUCCESS");
        ERROR_VIEW = this.getInitParameter("ERROR");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                      throws ServletException, IOException {
        String name = request.getParameter("name");
        String passwd = request.getParameter("passwd");
        if("caterpillar".equals(name) && "123456".equals(passwd)) {
            request.changeSessionId();
            request.getSession().setAttribute("login", name);
            response.sendRedirect(SUCCESS_VIEW);
        }
        else {
            response.sendRedirect(ERROR_VIEW);
        }
    }
}