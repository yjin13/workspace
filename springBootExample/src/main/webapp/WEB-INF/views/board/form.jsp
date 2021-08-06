<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Example</title>
</head>
<body>
	<div class="container">
		<form id="form" method="post">
			<input type="hidden" name="boardSeq" value="${board == null ? 0 : board.boardSeq}"/>
			<div class="row mb-3">
				<label for="title" class="col-sm-2 col-form-label"><spring:message code="board.title"/></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="title" name="title" value="${board.title}" placeholder="<spring:message code="placeholder.required"/>">
				</div>
			</div>
			<div class="row mb-3">
				<label for="contents" class="col-sm-2 col-form-label"><spring:message code="board.contents"/></label>
				<div class="col-sm-10">
					<textarea class="form-control" id="contents" name="contents" placeholder="<spring:message code="placeholder.required"/>">${board.contents}</textarea>
				</div>
			</div>
			<button type="submit" class="btn btn-primary"><spring:message code="button.save"/></button>
		</form>
	</div>
	
	<script>
	$(function() {
		var $form = $('#form');
		$form.bind('submit', function() {
			$.ajax({
				url: '/${menuType}/save',
				type: 'post',
				data: $form.serialize(),
				dataType: 'json',
				success: function(response) {
					if(response.code == 'SUCCESS') {
						alert("저장되었습니다.");
						location.href = '/${menuType}/' + response.data;
					} else {
						alert(response.message);
					}
				}
			});
			return false; // 페이지 안넘어가게 처리
		});
	});
	</script>
</body>
</html>