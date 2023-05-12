<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
<link href="/css/reg.css" rel="stylesheet" type="text/css">
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
			<a href="/index.html"><img src="/img/logo.png"></a>
		</div>
		<div class="div-search-bar">
			<div class="search-bar">
				<input type="text" placeholder="검색어 입력">
				<button>검색</button>
			</div>
		</div>
	</div>
	<div class="body-div">
		<!----------------------------------- 왼쪽 사이드 ------------------------------------------->
		<div class="left-side">
			
		</div>
		<!----------------------------------- 중앙 본문 --------------------------------------------->
		<form action="reg_process" method="post" enctype="multipart/form-data">
		<div class="center-content">
			<div class="center-content-table-div">
			<table class="table">
				<tbody>
			    <tr>
			      <th class="table-active">제목</th>
			      <td class="table-title-box" colspan="3"><input type="text" name="title" required="required"></td>
			    </tr>
			    <tr>
			      <th class="table-active">첨부파일</th>
			      <td colspan="3"><input type="file"  multiple="multiple" name="files"></td>
			    </tr>
			    <tr class="content">
			      <td class="textarea-box" colspan="4"><textarea name="content" required="required"></textarea></td>
			    </tr>
			    <tr>
                 <td colspan="4"><input type="checkbox"id="open" name="open" value="true"><label id="opne-label" for="open" >바로공개</label> </td>
                </tr>
                </tbody>
			</table>
			</div>
			<div class="reg-btn-div">
				<input class="btn btn-primary" type="submit" value="등록">
				<input id="cancle-btn" class="btn btn-primary" type="button" onclick="alert('페이지가 없습니다.');" value="취소">
			</div>
		</div>
		</form>
		<!----------------------------------------- 오른쪽 사이드 --------------------------------------------->
		<div class="right-side">
		
		</div>
	</div>
	<div class="footer">
	</div>
</body>
</html>