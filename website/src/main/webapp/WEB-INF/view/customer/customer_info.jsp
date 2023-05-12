<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/css/customer_info.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/png" href="/img/logo_favicon.png" />
    <title>회원정보</title>
    <style>
    </style>
</head>
<body>
    <div class="profile-container">
    	<div class="img_div">
        <img src="/img/default_profile.jpg" alt="프로필 이미지" class="profile-image">
        </div>
        <h2 class="profile-name">${memberInfo.name}</h2>
        <div class="profile-info">
            <p>이메일: ${memberInfo.email}</p>
            <p>등급: ${memberInfo.role}</p>
            <p>가입날짜: <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${memberInfo.append_date}"/></p> 
            		<a href="/customer/myWriting">내 작성글 보기</a>
            		<a href="/customer/changePassword_form">비밀번호 변경</a>
            		<a href="/customer/membershipWithdrawal">회원탈퇴</a>
            	
            
        </div>
    </div>
</body>
</html>
