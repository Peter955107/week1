package cc.openhome;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/upload")
public class Upload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        byte[] content = bodyContent(request);
        String contentAsTxt = new String(content, "ISO-8859-1");

        String filename = filename(contentAsTxt);
        Range fileRange = fileRange(contentAsTxt, request.getContentType());

        write(
            content, 
            contentAsTxt.substring(0, fileRange.start)
                        .getBytes("ISO-8859-1")
                        .length, 
            contentAsTxt.substring(0, fileRange.end)
                        .getBytes("ISO-8859-1")
                        .length, 
                        filename
        );
    }

    private byte[] bodyContent(HttpServletRequest request) throws IOException {
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            InputStream in = request.getInputStream();
            byte[] buffer = new byte[1024];
            int length = -1;
            while((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            return out.toByteArray();
        }
    }

    private String filename(String contentTxt) throws UnsupportedEncodingException {
        Pattern pattern = Pattern.compile("filename=\"(.*)\"");
        Matcher matcher = pattern.matcher(contentTxt);
        matcher.find();
        return matcher.group(1);
    }

    private static class Range {
        final int start;
        final int end;
        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private Range fileRange(String content, String contentType) {
        Pattern pattern = Pattern.compile("filename=\".*\"\\r\\n.*\\r\\n\\r\\n(.*+)");
        Matcher matcher = pattern.matcher(content);
        matcher.find();
        int start = matcher.start(1);

        String boundary = contentType.substring(
                contentType.lastIndexOf("=") + 1, contentType.length());
        int end = content.indexOf(boundary, start) - 4;   

        return new Range(start, end);
    }

    private void write(byte[] content, int start, int end, String file) throws IOException {
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(content, start, (end - start));
        }   
    }
}