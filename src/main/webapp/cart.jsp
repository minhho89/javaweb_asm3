<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  Responsive -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">


<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<!--  custom CSS -->
<link rel="stylesheet" href="styles/cart.css" type="text/css">

<title>Insert title here</title>
</head>

<%@ include file="includes/header.jsp"%>

<body>

	<div class="container">
		<div class="card m-4">
			<div class="row">
				<div class="col-md-8 cart">
					<div class="title">
						<div class="row">
							<div class="col">
								<h4>
									<b>Shopping Cart</b>
								</h4>
							</div>
							<div class="col align-self-center text-right text-muted">3
								items</div>
						</div>
					</div>


					<!--  JSPL data -->
					<sql:setDataSource var="ds"
						driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
						url="jdbc:sqlserver://localhost\\instance:1433;databaseName=ShoppingDB"
						user="sa" password="yourStrong(!)Password" />

					<c:forEach items="${sessionScope.cartItems}" var="itemID">
						<sql:query dataSource="${ds}"
							sql="SELECT * FROM Products WHERE product_id=?" var="results">
							<sql:param>${itemID}</sql:param>
						</sql:query>

						<c:set scope="page" var="product" value="${results.rows[0]}"></c:set>
						<c:set scope="page" var="productID" value="${product.product_id}"></c:set>
						<c:set scope="page" var="productName"
							value="${product.product_name}"></c:set>
						<c:set scope="page" var="productImg"
							value="${product.product_img_source}"></c:set>
						<c:set scope="page" var="productPrice"
							value="${product.product_price}"></c:set>
						<c:set scope="page" var="productType"
							value="${product.product_type}"></c:set>
						<c:set scope="page" var="productInfo"
							value="${product.product_des}"></c:set>

					<!--  item -->
					<div class="row border-top border-bottom">
						<div class="row main align-items-center">
							<div class="col-2">
								<img class="img-fluid" src="${product.product_img_source}">
							</div>
							<div class="col">
								<div class="row text-muted">${product.product_type}</div>
								<div class="row">${product.product_name}</div>
							</div>
							<div class="col">
								<a href="#">-</a><a href="#" class="border">1</a><a href="#">+</a>
							</div>
							<div class="col">
								VND ${product.product_price}0.000 <span class="close">&#10005;</span>
							</div>
						</div>
					</div>
					<!--  end item -->

					</c:forEach>








					<!-- 					<div class="row">
						<div class="row main align-items-center">
							<div class="col-2">
								<img class="img-fluid" src="https://i.imgur.com/ba3tvGm.jpg">
							</div>
							<div class="col">
								<div class="row text-muted">Shirt</div>
								<div class="row">Cotton T-shirt</div>
							</div>
							<div class="col">
								<a href="#">-</a><a href="#" class="border">1</a><a href="#">+</a>
							</div>
							<div class="col">
								&euro; 44.00 <span class="close">&#10005;</span>
							</div>
						</div>
					</div>
					<div class="row border-top border-bottom">
						<div class="row main align-items-center">
							<div class="col-2">
								<img class="img-fluid" src="https://i.imgur.com/pHQ3xT3.jpg">
							</div>
							<div class="col">
								<div class="row text-muted">Sh/bodyrt</div>
								<div class="row">Cotton T-shirt</div>
							</div>
							<div class="col">
								<a href="#">-</a><a href="#" class="border">1</a><a href="#">+</a>
							</div>
							<div class="col">
								&euro; 44.00 <span class="close">&#10005;</span>
							</div>
						</div>
					</div> -->

					<div class="back-to-shop">
						<a href="#">&leftarrow;</a><span class="text-muted">Back to
							shop</span>
					</div>
				</div>
				<div class="col-md-4 summary">
					<div>
						<h5>
							<b>Summary</b>
						</h5>
					</div>
					<hr>
					<div class="row">
						<div class="col" style="padding-left: 0;">ITEMS 3</div>
						<div class="col text-right">&euro; 132.00</div>
					</div>
					<form>
						<p>SHIPPING</p>
						<select>
							<option class="text-muted">Standard-Delivery- &euro;5.00</option>
						</select>
						<p>GIVE CODE</p>
						<input id="code" placeholder="Enter your code">
					</form>
					<div class="row"
						style="border-top: 1px solid rgba(0, 0, 0, .1); padding: 2vh 0;">
						<div class="col">TOTAL PRICE</div>
						<div class="col text-right">&euro; 137.00</div>
					</div>
					<button class="btn">CHECKOUT</button>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="includes/js-bootstrap.jsp"%>
	<%@ include file="includes/footer.jsp"%>
</body>
</html>