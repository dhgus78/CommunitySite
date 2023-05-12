<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>
<link href="/css/main.css" rel="stylesheet" type="text/css">
<link href="/css/bootstrap.css" rel="stylesheet" type="text/css">
<link rel="icon" type="image/png" href="/img/logo_favicon.png" />
<style>	

</style>
</head>
<body>

<!----------------------------------------------상단 메뉴바----------------------------------------------------->

	<div class="menu-bar">
		<ul>
			<li><a href="/loginForm">로그인</a></li>
			<li><a href="/join">회원가입</a></li>
		</ul>
	</div>
	
<!------------------------------------------------header ----------------------------------------------------->

	<div class="header">
		<div class="header-logo">
			<a href="/main"><img src="/img/logo.png"></a>
		</div>
		<div class="div-search-bar">
			<div class="search-bar">
			<form>
				<select name="f">
					<option ${(param.f == "title")?"selected":""} value="title">제목</option>
					<option ${(param.f == "memberName")?"selected":""} value="memberName">작성자</option>
				</select>
				<input type="text" placeholder="검색어 입력" name="q" value="">
				<button type="submit">검색</button>
			</form>
			</div>
		</div>
	</div>
	<div class="body-div">
		<!----------------------------------- 왼쪽 사이드 ------------------------------------------->
		<div class="left-side">
			
		</div>
		<c:set var="page" value="${(empty param.p)?1:param.p}" />
		<c:set var="startNum" value="${page-(page-1)%5}" />
		<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.') }" />
		<c:set var="field" value="${param.f}"/>
		<c:set var="query" value="${param.q}"/>
		<!----------------------------------- 중앙 본문 --------------------------------------------->
		<div class="center-content">
			<div class="table-div" >
				<table class="table table-hover">
				  <thead>
				    <tr>
				      <th>번호</th>
				      <th colspan="2" style="font-weight: bold;">제목</th>
				      <th>작성자</th>
				      <th>작성일</th>
				      <th>조회수</th>
				    </tr>
				  </thead>
				  <tbody>
				  	<c:forEach var="n" items="${list}">
				    <tr>
				      <td>${n.id}</td>
				      <td colspan="2" style="font-weight: bold;"><a href="detail?id=${n.id}&p=${page}&f=${field}&q=${query}">${n.title}</a></td>
				      <td>${n.memberName}</td>
				      <td><fmt:formatDate pattern="yyyy-MM-dd" value="${n.regdate}"/></td>
				      <td>${n.hit}</td>
				    </tr>
				    </c:forEach>
				  </tbody>
			</table>
			</div>
			
			
			
			<div class="current-page">
					<h5 class="hidden">현재 페이지</h5>
					<div><span class="text-orange">${(empty param.p)?1:param.p}</span> / ${lastNum} pages</div>
			</div>
			<div class="table-bottom-page-inf">
			
			

				<div class="center-pager">	
					<div class="btn-prev-div">
						<c:if test="${startNum>1}">
							<input class="btn btn-primary" type="button" onclick="location.href='?p=${startNum-1}&t=&q='" value="prev">
						</c:if>
						<c:if test="${startNum<=1}">
							<input class="btn btn-primary" type="button" onclick="alert('이전 페이지가 없습니다.');" value="prev">
						</c:if>
					</div>
					<ul class="-list-center">
						<c:forEach var="i" begin="0" end="4">
							<c:if test="${(startNum+i)<=lastNum}">
							<li><a class="page-list-number${(page==startNum+i)?' present-page-red':''}" href="?p=${startNum+i}&f=${param.f}&q=${param.q}" >${startNum+i}</a></li>	
							</c:if>
						</c:forEach>	<!-- EL에서는 쌍따옴표를 홀따옴표로 써도 되도록 허용함. -->
					</ul>
					<div class="btn-next-div">
						<c:if test="${startNum+4 < lastNum}">
							<input class="btn btn-primary" type="button" onclick="location.href='?p=${startNum+5}&t=&q='" value="next">
						</c:if>
						<c:if test="${startNum+4 >= lastNum}">
							<input class="btn btn-primary" type="button" onclick="alert('다음 페이지가 없습니다.');" value="next">
						</c:if>
					</div>
				</div>
			</div>
		</div>
		
		<!----------------------------------------- 오른쪽 사이드 --------------------------------------------->
		<div class="right-side">
		
		</div>
	</div>
	<div class="footer">
	</div>
</body>
</html>