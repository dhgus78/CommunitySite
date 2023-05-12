<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물</title>
<link href="/css/myUpate_detail.css" rel="stylesheet" type="text/css">
<link href="/css/bootstrap.css" rel="stylesheet" type="text/css">
<link rel="icon" type="image/png" href="/img/logo_favicon.png" />
<style>	
	
</style>
</head>
<body>

<!----------------------------------------------상단 메뉴바----------------------------------------------------->

	<div class="menu-bar">
		<ul>
			<li><a href="/customer/customer_info">${customerEmail}</a></li>
			<li><a href="/logout">로그아웃</a></li>
			<li><a href="/customer/reg">게시글 작성</a></li>
		</ul>
	</div>

<!------------------------------------------------header ----------------------------------------------------->

	<div class="header">
		<div class="header-logo">
			<a href="/customer/main"><img src="/img/logo.png"></a>
		</div>
		<%-- <div class="div-search-bar">
			<div class="search-bar">
			<form action="customer/main">
				<select name="f">
					<option ${(param.f == "title")?"selected":""} value="title">제목</option>
					<option ${(param.f == "memberName")?"selected":""} value="memberName">작성자</option>
				</select>
				<input type="text" placeholder="검색어 입력" name="q" value="">
				<button type="submit">검색</button>
			</form>
			</div>
		</div> --%>
	</div>
	<div class="body-div">
		<!----------------------------------- 왼쪽 사이드 ------------------------------------------->
		<div class="left-side">
			
		</div>
		<!-- 중앙 본문 -->
		<div class="center-content">
		<form action="/customer/myNoticeUpdateProcess" method="post" enctype="multipart/form-data">
			<div class="center-content-table-div">
			<table id="table-border" class="table">
			    <tr>
			      <th class="table-active">제목</th>
			      <td class="table-title-box" colspan="3"><input type="text" name="title" value="${n.title }" required="required"></td>
			    </tr>
			    <tr>
			      <th class="table-active">작성일</th>
			      <td colspan="3"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${n.regdate}"/></td>
			    </tr>
			    <tr>
			      <th class="table-active">작성자</th>
			      <td>${n.memberName }</td>
			      <th class="table-active">조회수</th>
			      <td>${n.hit }</td>
			    </tr>
			    <tr>
			      <th class="table-active">기존 첨부파일</th>
			      <td colspan="3"><c:forEach var="file" items="${f}" varStatus="status">
			      	<a href="/file/download/${file.savedName}">${file.orgName} </a>
			      	<input type="checkbox" id="deleteFile" name="deleteFile" value="${file.savedName}"><label id="deleteFile-label" for="deleteFile" >삭제하기</label>
			      	<c:if test="${!status.last}">
			      		,
			      	</c:if>
			      </c:forEach></td>
			    </tr>
			    <tr>
			      <th class="table-active">추가 첨부파일</th>
			      <td colspan="3"><input type="file"  multiple="multiple" name="files"></td>
			    </tr>
			    <tr class="content">
			      <td class="textarea-box" colspan="4"><textarea name="content" required="required">${n.content }</textarea></td>
			    </tr>
			    <c:set var="open" value="" />
				<c:if test="${n.pub}">
					<c:set var="open" value="checked" />
				</c:if>
			    <tr>
                 <td colspan="4"><input type="checkbox" ${open} id="open" name="open" value="true"><label id="opne-label" for="open" >바로공개</label> </td>
                </tr>
			</table>
			</div>
			<div class="list-div">
				<input type="hidden" name="id" value="${param.id}">
				<input class="btn btn-primary" type="submit" value="수정완료">
				<input class="btn btn-primary" type="button" onclick="location.href = '/customer/myWriting?f=${param.f}&q=${param.q}&p=${param.p }'" value="취소">
			</div>
			<%-- <div class="prev-next-table-div">
				<table class="table">
					<tr>
				      <th class="table-active">이전글</th>
				      <td colspan="3"><a href="/customer/detail?id=${n_prev.id }">${n_prev.title}</a></td>
				    </tr>
				    <tr>
				      <th class="table-active">다음글</th>
				      <td colspan="3"><a href="/customer/detail?id=${n_next.id }">${n_next.title}</a></td>
				    </tr>
				</table>
			</div> --%>
			
		</form>
		</div>
		
		<!----------------------------------------- 오른쪽 사이드 --------------------------------------------->
		<div class="right-side">
		
		</div>
	</div>
	<div class="footer">
	</div>
</body>
</html>