<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
    </head>
    <body>
        <form action="pet" method="post">
            姓　名：<input type="text" name="user"><br>
            密　碼：<input type="password" name="passwd"><br>
            驗證碼：<input type="text" name="check"> <img src="password"/><br>
         <input type="submit" value="送出"/>
        </form>
    </body>
</html> 