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
    <script src = "/js/passwordModify_check.js"></script>
    <style>
      
    </style>
  </head>
  <body>
    <div class="container">
      <div><a href="/customer/main"><img src="/img/logo.png"></a></div>
      <form name="passwordModify_form" action="/customer/changePassword_modify" method="post">
        <label for="email">${memberInfo.email}</label>
        <br />
        <br />
        <label for="pwd">새롭게 변경할 비밀번호를 입력 해주세요.</label>
        <br />
        <input type="password" id="pwd" name="password" required="required" />
        <br />
        <label for="pwdConfirm">변경할 비밀번호를 재입력 해주세요.</label>
        <br />
        <input type="password" id="pwdConfirm" name="passwordConfirm" required="required" />
        <br />
        <input type="button" onclick="passwordModify_check();" value="확인" />
      </form>
      <!-- <div class="links">
        <a href="#">비밀번호를 잊으셨나요?</a>
        <a href="/join">계정 만들기</a>
      </div> -->
    </div>
  </body>
</html>
