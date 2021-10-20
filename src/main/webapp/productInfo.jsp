
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<%@ include file="includes/head.jsp"%>
<%@ include file="includes/header.jsp"%>

<sql:setDataSource var="ds"
	driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
	url="jdbc:sqlserver://localhost\\instance:1433;databaseName=ShoppingDB"
	user="sa" password="yourStrong(!)Password" />
<sql:query dataSource="${ds}"
	sql="SELECT * FROM Products WHERE product_id=?" var="results">
	<sql:param>${param.id}</sql:param>
</sql:query>

<c:set scope="page" var="product" value="${results.rows[0]}"></c:set>
<c:set scope="page" var="productID" value="${product.product_id}"></c:set>
<c:set scope="page" var="productName" value="${product.product_name}"></c:set>
<c:set scope="page" var="productImg"
	value="${product.product_img_source}"></c:set>
<c:set scope="page" var="productPrice" value="${product.product_price}"></c:set>
<c:set scope="page" var="productType" value="${product.product_type}"></c:set>
<c:set scope="page" var="productInfo" value="${product.product_des}"></c:set>


<div class="container">
	<div class="row">
		<h1>${productName}</h1>
	</div>
	<hr>
	<div class="row">
		<div class="col">
			<img src="${product.product_img_source}">
		</div>

		<div class="col">
			<h2 class="text-danger">VND ${productPrice}0.000</h2>
			<p>${productInfo}</p>
			<form action='<%=request.getContextPath()%>/controller'  method="post">
			<input type="hidden" name="action" value="cart">
				<input type="hidden" name="productCode" value="${productID}">
				<input type="submit" class="btn btn-warning" value="Add to Cart"></input>
			</form>
	
		</div>
	</div>
</div>

<%@ include file="includes/js-bootstrap.jsp"%>
<%@ include file="includes/footer.jsp"%>