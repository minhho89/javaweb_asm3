<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="styles/login.css" type="text/css">

<title>Insert title here</title>
</head>
<body>
	<section class="login-block">
		<div class="container">
			<div class="row">
				<div class="col-md-4 login-sec">
					<h2 class="text-center">Login Now</h2>
					<form class="login-form"
					action='<%=request.getContextPath()%>/controller' method="post">
						<div class="form-group">
							<input type="hidden" name="action" value="dologin" />
							<label for="exampleInputEmail1" class="text-uppercase">Email</label>
							<input type="email" name="email" value='<%=session.getAttribute("account.mail")%>'  class="form-control" placeholder="email">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1" class="text-uppercase">Password</label>
							<input type="password" name="password" class="form-control" value='<%=session.getAttribute("accout.password")%>' placeholder="password">
						</div>


						<div class="form-check">
							<label class="form-check-label"> <input type="checkbox"
								class="form-check-input"> <small>Remember Me</small>
							</label>
							<button type="submit" value="Login" class="btn btn-login float-right">Submit</button>
						</div>
					</form>
					<p class="text-danger"><em>${message}</em></p>
				</div>
				<div class="col-md-8 banner-sec p-0">
					<div id="carouselExampleInterval" class="carousel slide"
						data-ride="carousel">
						<div class="carousel-inner">
							<div class="carousel-item active">
								<img
									src="https://getwallpapers.com/wallpaper/full/e/7/4/35857.jpg"
									class="d-block w-100" alt="1st slide">
							</div>
							<div class="carousel-item">
								<img
									src="https://getwallpapers.com/wallpaper/full/f/d/2/35774.jpg"
									class="d-block w-100" alt="2nd slide">
							</div>
							<div class="carousel-item">
								<img
									src="https://getwallpapers.com/wallpaper/full/d/7/e/35825.jpg"
									class="d-block w-100" alt="3rd slide">
							</div>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleInterval"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="carousel-control-next" href="#carouselExampleInterval"
							role="button" data-slide="next"> <span
							class="carousel-control-next-icon" aria-hidden="true"></span> <span
							class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</section>


	<%@ include file="includes/js-bootstrap.jsp"%>
	<%@ include file="includes/footer.jsp"%>

</body>
</html>