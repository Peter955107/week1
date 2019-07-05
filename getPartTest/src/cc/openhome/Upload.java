package cc.openhome;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@MultipartConfig
@WebServlet("/upload") 
public class Upload extends HttpServlet { 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part photo = request.getPart("photo");
        String filename = photo.getSubmittedFileName();

        try(InputStream in = photo.getInputStream();  
            OutputStream out = new FileOutputStream(filename)) {
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        }
    }
}