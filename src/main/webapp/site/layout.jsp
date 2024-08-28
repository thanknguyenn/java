<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${page.title}</title>
<base href="/ASMJAVA4/site/" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="style-user.css">
<link rel="stylesheet" href="../fontawesome-free-6.5.1-web/css/all.css">
</head>

<body>

	<main class="container">
		<header class="row pt-5 pb-2">
			<div class="col-9">
				<h1>ONLINE ENTERTAIMENT</h1>
			</div>
			<div class="col-3 text-right">
				<img src="../uploads/" alt="" class="mr-4">
			</div>
		</header>
		<nav class="row">
			<nav class="navbar navbar-expand-sm navbar-light bg-light">
				<div class="container">
					<a class="navbar-brand" href="Home">ONLINE ENTERTAIMENT</a>
					<button class="navbar-toggler d-lg-none" type="button"
						data-bs-toggle="collapse" data-bs-target="#collapsibleNavId"
						aria-controls="collapsibleNavId" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="collapsibleNavId">
						<ul class="navbar-nav me-auto mt-2 mt-lg-0">
							<li class="nav-item"><a class="nav-link active"  href="Homepage"
								aria-current="page"><i class="fas fa-home"></i> Home <span
									class="visually-hidden">(current)</span></a></li>
							<li class="nav-item"><a class="nav-link" href="/ASMJAVA4/site/home/aboutus.jsp"><i
									class="far fas fas fa-info-circle" aria-hidden="true"></i>About
									Us</a></li>
							<li class="nav-item"><a class="nav-link" href="/ASMJAVA4/site/home/contactus.jsp"><i
									class="far fa-id-card" aria-hidden="true"></i>Contact Us</a></li>
							<li class="nav-item"><a class="nav-link" href="Favourite"><i
									class="far far fa-comment" aria-hidden="true"></i>My favourite</a>
							</li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="dropdownId"
								data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false" ><i class="fa fas fa-user"></i>My
									account</a>
								<div class="dropdown-menu" aria-labelledby="dropdownId" aria-current="page">
									<c:if test="${not isLogin }">
										<a class="dropdown-item" href="Login">Login</a>
										<a class="dropdown-item" href="Fogotpassword">Fogot password</a>
										<a class="dropdown-item" href="Registration">Registration</a>
									</c:if>
									<c:if test="${isLogin }">
										<a class="dropdown-item" href="Logoff">Log off</a>
										<a class="dropdown-item" href="Changepassword">Change Password</a>
										<a class="dropdown-item" href="Editprofile">Edit profile</a>
									</c:if>
								</div></li>
						</ul>

					</div>
				</div>
			</nav>

		</nav>
		<section class="row">
			<jsp:include page="${page.contenUrl }"></jsp:include>
		</section>
		<footer class="row">
			<div class="col text-center border-0">
				<Strong>fpoly &copy;2020. All rights reserved </Strong>
			</div>
		</footer>
	</main>


</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<c:if test="${not empty page.scripUrl }">
	<jsp:include page="${page.scripUrl }"></jsp:include>
</c:if>
</html>