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
<!-- Bootstrap JS + PopperJS -->
<script defer src="js/bootstrap.min.js"></script>
</head>

<body>
 <div class="container-fluid">
 <jsp:include page="/views/header.jsp" />
 <div class="container-fluid">
 <div class="row justify-content-start ">
 <div class="col-8 border bg-light px-4">
 <h3>Список районов</h3>
 <table class="table">
 <thead>
	 <th scope="col">Код</th>
	 <th scope="col">Название района</th>
	 <th scope="col">Площадь</th>
	 <th scope="col">Год зайстройки</th>
	 <th scope="col">Население</th>
	 <th scope="col">Редактировать</th>
	 <th scope="col">Удалить</th>
 </thead>
 <tbody>
 <c:forEach var="District" items="${districts}">
 <tr>
 <td>${District.getId()}</td>
 <td>${District.getNameDistrict()}</td>
 <td>${District.getDistrictArea()}</td>
 <td>${District.getDistrictYear()}</td>
 <td>${District.getDistrictNumberOfPeople()}</td>
 <td width="20"><a href="#" role="button"
 class="btn btn-outline-primary">
 <img alt="Редактировать"
 src="images/icon-edit.png"></a></td>
 <td width="20"><a href="#" role="button"
 class="btn btn-outline-primary">
 <img alt="Удалить"
 src="images/icon-delete.png"></a></td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>
 <div class="col-4 border px-4">
 
 
 
 <form method="POST" action="district">
 <h3>Новый район</h3>
 <br>
 <div class="mb-3 row">
 <label for="lastname"
 class="col-sm-3 col-form-label">Название района</label>
 <div class="col-sm-7">
 <input type="text" class="form-control" id="nameDistrict" name="nameDistrict" />
 </div>
 </div>
 
 <div class="mb-3 row">
<label for="firstname" class="col-sm-3 col-form-label">Площадь</label>
 <div class="col-sm-7">
 <input type="text"
 class="form-control" id="areaDistrict"
 name="areaDistrict" />
 </div>
 </div>

 <div class="mb-3 row">
 <label for="phone" class="col-sm-3 col-form-label">Год зайстройки</label>
 <div class="col-sm-7">
 <input type="text"
 class="form-control" id="yearDistrict"
 name="yearDistrict" />
 </div>
 </div>
 
 <div class="mb-3 row">
 <label for="people"
 class="col-sm-3 col-form-label">Население</label>
 <div class="col-sm-7">
 <input type="number" class="form-control" id="peopleDistrict" name="peopleDistrict" />
 </div>
 </div>
 <p> <br>
 <button type="submit"
 class="btn btn-primary">Добавить</button>
 </p>
 </form>
 
 
 

 </div>
 </div>
 </div>
 <jsp:include page="/views/footer.jsp" />
 </div>
</body>
</html>