<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



	<main class="container">
		<div class="row">
			<div class="offset-2 col-8">
				<form action="" method="post">
					${Route.SITE_REGISTRATION_PAGE }
					<div class="card">
						<div class="card-header">
							<b>REGISTRATION</b>
						</div>
						<div class="card-body">
							<jsp:include page="/site/common/inform.jsp"></jsp:include>
							<div class="row">
								<div class="col">
									<div class="mb-3">
										<label for="userID" class="form-label">userID</label> <input
											type="text" class="form-control" name="userID" id="userID"
											aria-describedby="userIDhelpId" placeholder="userID"
											value="${user.userID }" /> <small id="userIDHid"
											class="form-text text-muted">userID is Required</small>
									</div>
									<div class="mb-3">
										<label for="password" class="form-label">Password</label> <input
											type="password" class="form-control" name="password"
											id="password" placeholder="password"  /> <small
											id="passwordHelpId" class="form-text text-muted">Password
											is Required</small>
									</div>
								</div>
								<div class="col">

									<div class="mb-3">
										<label for="fullname" class="form-label">FullName</label> <input
											type="text" class="form-control" name="fullname"
											id="fullname" aria-describedby="fullnamehelpId"
											placeholder="fullname" value="${user.fullname }" /> <small
											id="fullname" class="form-text text-muted">FullName
											is Required</small>
									</div>

									<div class="mb-3">
										<label for="email" class="form-label">Email</label> <input
											type="email" class="form-control" name="email" id="email"
											aria-describedby="emailHelpId" placeholder="abc@mail.com"
											value="${user.email }" /> <small id="emailHId"
											class="form-text text-muted">Email is Required</small>
									</div>



								</div>
							</div>
						</div>
						<div class="card-footer text-muted"></div>
						<button class="btn btn-success">Login</button>
					</div>
				</form>
			</div>
		</div>
	</main>
	