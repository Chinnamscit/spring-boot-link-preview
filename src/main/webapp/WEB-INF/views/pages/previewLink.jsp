<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html lang="en">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<title>Demo</title>
</head>
<body>
	<div class="container-fluid mt-2">
		<div class="row">
			<div class="col-12 col-md-5">
				<c:url value="/postLink" var="actionUrl" />
				<div class="form-group">
					<label for="title">Link </label> <input type="text" class="form-control" id="url" aria-describedby="url" placeholder="Enter URL here" />
				</div>
				<div class="mb-2">
					<button class="btn btn-sm btn-dark" value="preview" onclick="preview()">Preview Link</button>
				</div>
				<div class="form-group" id="link-preview"></div>
			</div>
			<div class="col-md-7 d-none d-md-block"></div>
		</div>
	</div>
	<script >
		function preview() {
			var url = $('#url').val();
			if (url) {
				$.get('/link/preview?url=' + url, function(data, status) {
					$('#link-preview').html(data);
				});
			}
		}
	</script>
</body>
</html>