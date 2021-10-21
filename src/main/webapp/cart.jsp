
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<c:import url="includes/head.jsp">
	<c:param name="title" value="PRJ321x-A3 Home"></c:param>
	<c:param name="cssFileName" value="cart.css"></c:param>
</c:import>


<c:import url="includes/header.jsp">
	<c:param name="home_active" value="active"></c:param>
</c:import>

<%@ include file="includes/login_register.jsp"%>

<body>

<div class="container">
		<div class="card m-4 p-4">


			<div>
				<h5>
					<b>Buyer Information</b>
				</h5>
			</div>
			<hr>

			<div class="row">
				<p>Name: ${ sessionScope.account.getName()}</p>
			</div>
			<div class="row">
				<p>Email: ${ sessionScope.account.getMail()}</p>
			</div>
			<div class="row">
				<p>Phone: ${ sessionScope.account.getPhone()}</p>
			</div>
			<div class="row">
				<p>Address: ${ sessionScope.account.getAddress()}</p>
			</div>
			<div class="row">
				<p>Email: ${ sessionScope.account.getMail()}</p>
			</div>


		</div>
	</div>


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
							<div class="col align-self-center text-right text-muted">${sessionScope.detailsList.getTotalItems()}
								items</div>
						</div>
					</div>


					<!--  JSPL data -->
					<sql:setDataSource var="ds"
						driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
						url="jdbc:sqlserver://localhost\\instance:1433;databaseName=ShoppingDB"
						user="sa" password="yourStrong(!)Password" />

					<c:forEach items="${sessionScope.detailsList.getList()}"
						var="detailsItem">
						<sql:query dataSource="${ds}"
							sql="SELECT * FROM Products WHERE product_id=?" var="results">
							<sql:param>${detailsItem.getProductID()}</sql:param>
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
						<c:set scope="page" var="orderDetailsItemById"
							value="${sessionScope.detailsList.getOrderDetailsObjectByProId(productID)}">
						</c:set>



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
									<a href="#" class="border">${detailsItem.getProductAmount()}</a>
								</div>
								<div class="col">
									VND ${detailsItem.getProductAmount() * detailsItem.getProductPrice()}0.000<span
										class="close">&#10005;</span>
								</div>

							</div>
						</div>
						<!--  end item -->

					</c:forEach>

					<div class="back-to-shop">
						<a href="<%=request.getContextPath()%>/controller">&leftarrow;</a><span
							class="text-muted">Back to shop</span>
					</div>
				</div>

				<div class="col-md-4 summary">
					<div>
						<h5>
							<b>Summary</b>
						</h5>
					</div>
					<hr>

					<div class="row"
						style="border-top: 1px solid rgba(0, 0, 0, .1); padding: 2vh 0;">
						<div class="col">TOTAL PRICE</div>
						<div class="col text-right">VND
							${sessionScope.detailsList.calculateTotalPrice()}0.000</div>
					</div>
					<form action='<%=request.getContextPath()%>/controller'
						method="post">
						<input type="hidden" name="action" value="checkout"> 
						<input
							type="submit" class="btn btn-warning" value="CHECK OUT"></input>
					</form>
				</div>
			</div>
		</div>
	</div>


	

	<%@ include file="includes/js-bootstrap.jsp"%>
	<%@ include file="includes/footer.jsp"%>
</body>
</html>