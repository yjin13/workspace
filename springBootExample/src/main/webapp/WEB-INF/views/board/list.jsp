<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Example</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Navbar</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"	aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item">
						<a class="nav-link active"	aria-current="page" href="/">Home</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/community"><spring:message code="menu.community"/></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/notice"><spring:message code="menu.notice"/></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/faq"><spring:message code="menu.faq"/></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/inquiry"><spring:message code="menu.community"/></a>
					</li>
				<form class="d-flex">
					<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</div>
	</nav>
	<div class="container">
		<h3>총 개수: ${totalCount}</h3>
		<form id="form" method="get" action="/board/list">
			<input type="hidden" name="boardType" value="COMMUNITY"/>
			<div class="row mb-3">
				<label for="keyword" class="col-sm-2 col-form-label"><spring:message code="search.keyword"/></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id=keyword name="keyword" value="${parameter.keyword}">
				</div>
			</div>
			<button type="submit" class="btn btn-primary"><spring:message code="button.search"/></button>
		</form>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col"><spring:message code="board.title"/></th>
					<th scope="col"><spring:message code="board.viewCount"/></th>
					<th scope="col"><spring:message code="board.regDate"/></th>
				</tr>
			</thead> 
			<tbody>
				<c:forEach var="board" items="${boardList}" varStatus="status">
					<tr>
						<th scope="row">${status.count}</th>
						<td><a href="/board/${board.boardSeq}">${board.title}</a></td>
						<td>${board.viewCount}</td>
						<td><fmt:formatDate value="${board.regDate}" pattern="yyyy.MM.dd HH:mm"/></td>
					</tr>
				</c:forEach>
				<c:if test="${fn:length(boardList) == 0}">
					<tr>
						<td colspan="4"><spring:message code="msg.board.empty"/></td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<div class="d-grid gap-2 d-md-flex justify-content-md-end mt-2">
			<a href="/board/form" class="btn btn-primary" type="button"><spring:message code="button.form"/></a>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<script>
	/* $(function() {
		var $form = $('#form');
		$form.bind('submit', function() {
			$.ajax({
				url: '/board/list',
				type: 'get',
				data: $form.serialize(),
				dataType: 'json',
				success: function(data) {
					if(data.code == 'SUCCESS') {
						console.log(data);
					} else {
						alert(data.message);
					}
				}
			});
			return false; // 페이지 안넘어가게 처리
		});
	}); */
	</script>
</body>
</html>