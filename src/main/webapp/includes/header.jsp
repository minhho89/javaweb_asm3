<!-- Header -->
<header class="bg-dark">

	<!-- Search bar -->
	<div class="container-fluid p-2">
		<div class="row">
			<div class="col-1">
			<a href="#"><img src='img/logo/logo.png' height='50' width='50'></a>
			</div>
			
			<div class="col-11">
				<form>
					<div class="form-row">
						<div class="col-10">
							<div class="form-inline">
								<select class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref">
								    <option selected>Categories</option>
								    <option value="1">One</option>
								    <option value="2">Two</option>
								    <option value="3">Three</option>
							    </select>
								<input class="form-control flex-fill" type="search" placeholder="Search" aria-label="Search">
							</div>
						</div>
						<div class="col-2">
							<button class="btn btn-outline-warning my-2 my-sm-0" type="submit">Search</button>
						</div>
					</div>
				</form>	
			</div>
		</div>
	</div>
	
	<!-- Navbar -->
	<div class="container mb-2">
		<nav class="navbar navbar-expand-lg navbar-dark">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item active">
		        <a class="nav-link" href="#">Home</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#">Product</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#">About us</a>
		      </li>     
		     
		    </ul>
		    
		<!--  Login & Register -->
			<c:choose>
				<c:when test="${sessionScope.account != null}">
					<p class="text-light">Hello ${sessionScope.account.name} !</p>
					<a class="m-1 text-light" href="${pageContext.request.contextPath}/logout">Log out</a>
				</c:when>
				<c:otherwise>
				 <a class="m-1 text-light" href="${pageContext.request.contextPath}/login.jsp">Login</a>
				 <a class="m-1 text-light" href="#">Register</a>
				</c:otherwise>
			</c:choose>
		  </div>
		</nav>
	</div>
</header>
<!--  End Header -->
