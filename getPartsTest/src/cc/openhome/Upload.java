package cc.openhome;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@MultipartConfig(location="D:/")
@WebServlet("/upload") 
public class Upload extends HttpServlet { 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8"); // 為了處理中文檔名

         request.getParts()
                .stream()
                .filter(part -> !"upload".equals(part.getName()))
                .forEach(part -> {
                    try {
                        part.write(part.getSubmittedFileName());
                    } catch(IOException ex) {
                        throw new UncheckedIOException(ex);
                    }
                });
    }
}