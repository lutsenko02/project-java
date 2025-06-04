<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.District"%>
<%@ page import="domain.Region"%>

<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Области</title>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Regions</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- jQuery -->
<script defer src="js/jquery.min.js"></script>
<!-- Bootstrap JS + PopperJS -->
<script defer src="js/bootstrap.min.js"></script>
<script
src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.mi
n.js"></script>
</head>

<body>
 <div class="container-fluid">
 <jsp:include page="/views/header.jsp" />
 <div class="container-fluid">
 <div class="row justify-content-start ">
 <div class="col-8 border bg-light px-4">
 <h3>Список областей</h3>
 <table class="table">
 <thead>
 <th scope="col">Код</th>
 <th scope="col">Название</th>
 <th scope="col">Площадь</th>
 <th scope="col">Административный центр</th>
 <th scope="col">Глава</th>
 <th scope="col">Район</th>
 <th scope="col">Редактировать</th>
 <th scope="col">Удалить</th>
 </thead>
 <tbody>
 <c:forEach var="region" items="${regions}">
 <tr>
 <td>${region.getId()}</td>
 <td>${region.getregionName()}</td>
 <td>${region.getregionArea()}</td>
 <td>${region.getregionCity()}</td>
 <td>${region.getregionHead()}</td>
 <td>${region.getDistrict().getNameDistrict()}</td>
 
 <td width="20"><a href="#" role="button" class="btn btn-outline-primary">
 <img alt="Редактировать" src="images/icon-edit.png"></a>
 </td>
 <td width="20"><a href="#" role="button"
 class="btn btn-outline-primary">
 <img alt="Удалить"
 src="images/icon-delete.png"></a>
 </td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>
 <div class="col-4 border px-4">
 <form method="POST" action="">
 <h3>Новая область</h3>
 <br>
 <div class="mb-3 row">
 <label for="lastname"
 class="col-sm-3 col-form-label">Название</label>
 <div class="col-sm-7">
 <input type="text" class="form-control"
 id="staticLastname"name="lastname" />
 </div>
 </div>
 <div class="mb-3 row">
<label for="firstname" class="col-sm-3 col-form-label">Площадь</label>
 <div class="col-sm-7">
 <input type="text"
 class="form-control" id="staticFirstname"
 name="firstname" />
 </div>
 </div>
 <div class="mb-3 row">
 <label for="districtname" class="col-sm-3 col-form-label">Район</label>
 <div class="col-sm-7">
 <select name="district" class="form-control">
 <option>Выберите район</option>
 <c:forEach var="district" items="${districts}">
 <option value="${district}">
 <c:out value="${district.getNameDistrict()}"></c:out>
 </option>
 </c:forEach>
 </select>
 </div>
 </div>

 <div class="mb-3 row">
 <label for="phone" class="col-sm-3 col-form-label">Административный центр</label>
 <div class="col-sm-7">
 <input type="text"
 class="form-control" id="staticphone"
 name="phone" />
 </div>
 </div>
 <div class="mb-3 row">
 <label for="email"
 class="col-sm-3 col-form-label">Глава</label>
 <div class="col-sm-7">
 <input type="text" class="form-control"
 id="staticemail" name="email" />
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
