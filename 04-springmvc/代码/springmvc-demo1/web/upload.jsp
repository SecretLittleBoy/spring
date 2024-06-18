<%--
  Created by IntelliJ IDEA.
  User: zeleishi
  Date: 2023/12/11
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/upload" method="post">
        文件：<input type="file" name="uploadFile"><br/>
        <input type="submit" value="上传">
    </form>

</body>
</html>
