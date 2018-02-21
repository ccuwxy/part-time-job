<%--
  Created by IntelliJ IDEA.
  User: jangitlau
  Date: 2017/11/2
  Time: 下午7:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>$Title$</title>
</head>
<body>
<form action="/storage/temporary" method="post" enctype="multipart/form-data">
  <input type="text" name="name">
  <input type="file" name="username">
  <input type="submit" value="提交" name="">
</form>

<form action="/storage/temporary" method="get">
  <input name="file_token" type="text"/>
  <input id="btnSave"  type="submit" value="导&nbsp;入" />
</form>
<form action="/storage/file" method="post" enctype="multipart/form-data">
  <input type="text" name="name">
  <input type="file" name="username">
  <input type="submit" value="提交" name="">
</form>

<form action="/storage/file" method="get">
  <input name="file_token" type="text"/>
  <input id="btnSave1"  type="submit" value="导&nbsp;入" />
</form>

<form action="/storage/api" method="get">
  <input name="file_token" type="text"/>
  <input id="btnSave2"  type="submit" value="change" />
</form>

<form action="/storage/api" method="post">
  <input name="file_token" type="text"/>
  <input id="btnSave3"  type="submit" value="delete" />
</form>
<form action="/api/user/registration" method="get">
  <input name="phone_num" type="text"/>
  <input id="btnSave1"  type="submit" value="导&nbsp;入" />
</form>
<form action="/api/user/registration" method="post">
  <input name="phone_num" type="text"/>
  <input name="username" type="text"/>
  <input name="password_key" type="text"/>
  <input name="type" type="text"/>
  <input id="btnSave"  type="submit" value="导&nbsp;入" />
</form>

</body>
</html>
