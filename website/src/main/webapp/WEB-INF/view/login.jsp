<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>로그인</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="/css/login.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/png" href="/img/logo_favicon.png" />
    <script src = "/js/loginform_check.js"></script>
    <style>
      
    </style>
  </head>
  <body>
    <div class="container">
      <div><a href="/main"><img src="/img/logo.png"></a></div>
      <form name="login_form" action="/login" method="POST">
        <label for="email">이메일</label>
        <br />
        <input type="email" id="email" name="username" required />
        <br />
        <label for="pwd">비밀번호</label>
        <br />
        <input type="password" id="pwd" name="password" required />
        <br />
        <input type="button" onclick="loginform_check();"value="로그인" />
      </form>
      <div class="alert">
      	<c:if test="${error}">
      		<p>${exception}</p>
      	</c:if>
      </div>
      <div class="links">
        <a href="/temporaryPassword_form">비밀번호를 잊으셨나요?</a>
        <a href="/join">계정 만들기</a>
      </div>
    </div>
  </body>
</html>
