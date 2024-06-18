<%--
  Created by IntelliJ IDEA.
  User: zeleishi
  Date: 2023/11/29
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试如何传输复杂类型的参数</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/demo/test3" method="post">
        id:<input type="text" name="id"/><br/>
        姓名:<input type="text" name="name"/><br/>
        家庭住址:
            所在城市：<input type="text" name="address.city"/>
            所在街道：<input type="text" name="address.street"/><br/>
        爱好:<input type="checkbox" name="hobbies" value="读书">读书</input>
            <input type="checkbox" name="hobbies" value="看片">看片</input>
            <input type="checkbox" name="hobbies" value="听歌">听歌</input><br/>
        毕业院校:<input type="text" name="schools[0]"/><input type="text" name="schools[1]"/><br/>
        家庭成员:
            父亲：<input type="text" name="family[0].name"/>
            母亲：<input type="text" name="family[1].name"/><br/>
        成绩:
            计算机：<input type="text" name="scores['计算机']"/>
            大学英语：<input type="text" name="scores['大学英语']"/><br/>
        <input type="submit" value="注册"/>
    </form>
</body>
</html>
