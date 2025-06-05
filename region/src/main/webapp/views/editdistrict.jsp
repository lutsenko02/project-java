<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.District"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Районы</title>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>District</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- jQuery -->
<script defer src="js/jquery-3.6.4.js"></script>
<!-- Bootstrap JS + Popper JS -->
<script defer src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
<jsp:include page="/views/header.jsp" />
<div class="container-fluid">
<div class="row justify-content-start ">
<div class="col-6 border bg-light px-4">
<h3>Список районов</h3>
<table class="table">
<thead>

<th scope="col">Код</th>
<th scope="col">Название района</th>
<th scope="col">Площадь</th>
<th scope="col">Год зайстройки</th>
<th scope="col">Население</th>
</thead>
<tbody>
<c:forEach var="district" items="${districts}">
<tr>
<td>${district.getId()}</td>
<td>${district.getNameDistrict()}</td>
<td>${district.getDistrictArea()}</td>
<td>${district.getDistrictYear()}</td>
<td>${district.getDistrictNumberOfPeople()}</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
<div class="col-6 border px-4">
<form method="POST" action="">
<h3>Редактировать район</h3>
<br> <br>
<div class="mb-3 row">
<label for="idrole" class="col-sm-3 col-form-label">
Код района</label>
<div class="col-sm-6">
<input type="text" class="form-control" readonly
value="${districtEdit.getId()}" />
</div>
</div>

<div class="mb-3 row">
<br> <label for="inputRole"
class="col-sm-3 col-form-label">Название района</label>
<div class="col-sm-6">
<input type="text" name="nameDistrict" class="form-control"
value="${districtEdit.getNameDistrict()}" id="nameDistrict" />
</div>
</div>

<div class="mb-3 row">
<br> <label for="inputRole"
class="col-sm-3 col-form-label">Площадь</label>
<div class="col-sm-6">
<input type="text" name="areaDistrict" class="form-control"
value="${districtEdit.getDistrictArea()}" id="areaDistrict" />
</div>
</div>

<div class="mb-3 row">
<br> <label for="inputRole"
class="col-sm-3 col-form-label">Население</label>
<div class="col-sm-6">
<input type="text" name="yearDistrict" class="form-control"
value="${districtEdit.getDistrictYear()}" id="yearDistrict" />
</div>
</div>

<div class="mb-3 row">
<br> <label for="inputRole"
class="col-sm-3 col-form-label">Год зайстройки</label>
<div class="col-sm-6">
<input type="text" name="numberDistrict" class="form-control"
value="${districtEdit.getDistrictNumberOfPeople()}" id="numberDistrict" />
</div>
</div>

<p>
<br> <br> <br>
<button type="submit"
class="btn btn-primary">Редактировать</button>
<a href='<c:url value="/district" />' role="button"
class="btn btn-secondary">Отменить</a>
<br>
</p>
</form>
</div>
</div>
</div>
<jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>