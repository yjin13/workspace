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
	<div class="container">
		<div class="card">
			<div class="card-header">${board.title}</div>
			<div class="card-body">
				<blockquote class="blockquote mb-0">
					<p>${board.contents}</p>
					<footer class="blockquote-footer">
						<fmt:formatDate value="${board.regDate}" pattern="yyyy.MM.dd HH:mm"/>
					</footer>
				</blockquote>
			</div>
		</div>
		<div class="d-grid gap-2 d-md-flex justify-content-md-end mt-2">
			<a href="/board/list" class="btn btn-secondary me-md-2" type="button"><spring:message code="button.list"/></a>
			<a href="/board/edit/${board.boardSeq}" class="btn btn-primary" type="button"><spring:message code="button.edit"/></a>
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