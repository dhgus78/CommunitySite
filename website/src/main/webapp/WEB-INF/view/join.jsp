<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="/css/join.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/png" href="/img/logo_favicon.png" />
    <script src = "/js/joinform_check.js"></script>
    <title>회원가입</title>
    <style>
     
    </style>
  </head>
  <body>
    <div class="container">
      <div><a href="/main"><img src="/img/logo.png"></a></div>
      
      <form name="join_form" action="/join" method="POST">
      
        <label for="email">이메일</label>
        <input type="email" id="email" name="email" required />
        
        <label for="name">이름</label>
        <input type="text" id="name" name="name" required />

        <label for="pwd">비밀번호</label>
        <input type="password" id="pwd" name="pwd" required />

        <label for="confirm-pwd">비밀번호 확인</label>
        <input
          type="password"
          id="confirm_pwd"
          name="confirm_pwd"
          required
        />
        
        <input type="button" onclick="joinform_check();" value="회원가입" />
         <!-- 입력된 경우에 따라서 다르게 액션되면 button으로, 입력 값 상관 없이 무조건 보내기면 submit -->
    	<!-- type을 submit으로 하여 전송하기를 하지말고, script가서 함수를 만들고 전송하기를 함 button onclick으로 함수를 불러옴 함수안에 문제가 있는 경우 return false;-->
      </form>
     </div>
   </body>
</html>
