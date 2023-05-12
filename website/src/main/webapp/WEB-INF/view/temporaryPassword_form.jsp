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
    <script src = "/js/tempPwd_form_check.js"></script>
    <style>
     	
    </style>
  </head>
  <body>
    <div class="container">
      <div><a href="/main"><img src="/img/logo.png"></a></div>
      <form name="tempPwd_form" action="/tempPwd" method="POST">
        <label for="email">가입한 이메일</label>
        <br />
        <input type="email" id="email" name="username" required />
        <br />
        <label for="name">가입한 이름</label>
        <br />
        <input type="text" id="name" name="name" required />
        <br />
        <input type="button" onclick="tempPwd_form_check();"value="확인" />
      </form>
      <div class="links">
      	<div>해당 이메일로 임시 비밀번호를 전송합니다.</div>
      	<br/>
      	<c:if test="${noMember}">
      	<div class="alert">조건에 맞는 회원이 없습니다.</div>
      	<br/>
      	</c:if>
        <a href="/temporaryPassword_form">비밀번호를 잊으셨나요?</a>
        <a href="/join">계정 만들기</a>
      </div>
    </div>
  </body>
</html>
