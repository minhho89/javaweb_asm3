
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<c:import url="includes/head.jsp">
	<c:param name="title" value="PRJ321x-A3 Home"></c:param>
	<c:param name="cssFileName" value="style.css"></c:param>
</c:import>


<c:import url="includes/header.jsp">
	<c:param name="home_active" value="active"></c:param>
</c:import>

<%@ include file="includes/login_register.jsp"%>

<sql:setDataSource var="ds"
	driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
	url="jdbc:sqlserver://localhost\\instance:1433;databaseName=ShoppingDB"
	user="sa" password="yourStrong(!)Password" />
<sql:query dataSource="${ds}" sql="SELECT * FROM Products" var="results" />

<c:set var="gridWidth" value="3" />

<div class="container">
	<c:forEach var="product" items="${results.rows}" varStatus="row">
		<c:set scope="page" var="productName" value="${product.product_name}"></c:set>
		<c:set scope="page" var="productImg"
			value="${product.product_img_source}"></c:set>
		<c:set scope="page" var="productPrice"
			value="${product.product_price}"></c:set>
		<c:set scope="page" var="productType" value="${product.product_type}"></c:set>

		<c:if test="${row.index % gridWidth == 0}">
			<div class="row">
		</c:if>

		<a href="<c:url value="/productInfo.jsp?id=${product.product_id}" />">
			<div class="col-12 col-md-4">
				<div class="card mb-2 p-2" style="width: 18rem">
					<img src="${product.product_img_source}" class="card-img-top">
					<h5>${productType}</h5>
					<h6 class="text-info">${productName}</h6>
					<h6 class="text-danger">VND ${productPrice}0.000</h6>
				</div>
			</div>
		</a>

		<c:if test="${(row.index + 1) % gridWidth == 0}">
			</div>
		</c:if>


</c:forEach>

</div>



<%@ include file="includes/js-bootstrap.jsp"%>
<%@ include file="includes/footer.jsp"%>