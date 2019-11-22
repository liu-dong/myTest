<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: 3hld
  Date: 2019/8/29
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>hello world!</h1>
<a href="${pageContext.request.contextPath}/page/goFileUpload">附件上传</a>

<form action="/login" method="post">
    用户名：<input type="text" name="username">
    密  码：<input type="password" name="password">
    <input type="submit" name="submit" value="提交">
</form>
</body>
</html>
