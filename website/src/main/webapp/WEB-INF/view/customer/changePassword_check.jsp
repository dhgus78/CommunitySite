<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>비밀번호 변경</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="/css/login.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/png" href="/img/logo_favicon.png" />
    <script src = "/js/password_check.js"></script>
    <style>
      
    </style>
  </head>
  <body>
    <div class="container">
      <div><a href="/customer/main"><img src="/img/logo.png"></a></div>
      <form name="password_form" action="/customer/passwordCheck" method="post">
        <label for="email">${memberInfo.email}</label>
        <br />
        <br />
        <label for="pwd">비밀번호를 재입력 해주세요.</label>
        <br />
        <input type="password" id="pwd" name="password" required="required" />
        <br />
        <input type="button" onclick="password_check();" value="확인" />
      </form>
      <c:if test="${pwdFail eq 'fail'}">
      		비밀번호가 틀렸습니다.
      </c:if>
    </div>
  </body>
</html>
