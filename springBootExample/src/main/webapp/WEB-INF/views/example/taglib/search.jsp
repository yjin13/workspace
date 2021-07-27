<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
	<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<div class="container">
		<h2>게시물 목록</h2>
		<form action="" method="get">
			<div class="mb-3 row">
				<label for="exampleFormControlInput1" class="col-sm-2 col-form-label">종류</label>
				<div class="col-sm-10">
					<tag:bootstrap-checkbox items="${boardTypes}" values="${parameter.boardTypes}"></tag:bootstrap-checkbox>
				</div>
			</div>
			<div class="mb-3 text-center">
				<button type="submit" class="btn btn-primary">검색</button>
			</div>
		</form>
		<table class="table">
			<tr>
				<th scope="col">#</th>
				<th scope="col">First</th>
				<th scope="col">Last</th>
				<th scope="col">Handle</th>
			</tr>
			<tr>
				<th scope="row">1</th>
				<td>Mark</td>
				<td>Otto</td>
				<td>@mdo</td>
			</tr>
			<tr>
				<th scope="row">2</th>
				<td>Jer</td>
				<td>Pit</td>
				<td>@Htt</td>
			</tr>
		</table>
	</div>
</body>
</html>