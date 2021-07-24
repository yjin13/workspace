<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Example</title>
</head>
<body>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<script>
	$(function() {
		var jsonData = {
			user: {
				name: '홍길동',
				age: 14,
				address: '대한민국'
			}
		};
		
		$.ajax({
			url: '/example/ex6/json',
			type: 'post',
			data: JSON.stringify(jsonData),
			contentType: 'application/json',
			dataType: 'json',
			success: function(data) {
				console.log("== json ==");
				console.log(data);
			}
		});
		
		$.ajax({
			url: '/example/ex6/class',
			type: 'post',
			data: JSON.stringify(jsonData),
			contentType: 'application/json',
			dataType: 'json',
			success: function(data) {
				console.log("== class ==");
				console.log(data);
			}
		});
	});
	</script>
</body>
</html>