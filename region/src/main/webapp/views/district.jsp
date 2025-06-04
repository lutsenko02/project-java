<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.District"%>
<%
District d1 = new District(1l, "Александровка", "45", "1990", 10000);
District d2 = new District(2l, "Аксайский район", "100", "1890", 25000);
District d3= new District(3l, "Азовский район", "500", "1700", 500000);
District d4 = new District(4l, "Ленинский район", "10", "1800", 150000);
District[] districts = new District[]{d1, d2, d3, d4};
int length = districts.length;
pageContext.setAttribute("districts", districts);
%>

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
 <form method="POST" action="">
 <h3>Новый район</h3>
 <div class="mb-3">
 <br> 
 <label for="inputRole" class="col-sm-3 col-form-label">Район</label>
 <div class="col-sm-6">
 <input type="text" name="inputRole" class="form-control" id="personRole" />
 </div>
 </div>
 <p>
 <br> <br> <br>

 <button type="submit" class="btn btn-primary">Добавить</button>
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