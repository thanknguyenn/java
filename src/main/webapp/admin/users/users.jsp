<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en">

<head>
<title>Title</title>
<!-- Required meta tags -->
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<!-- Bootstrap CSS v5.2.1 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />

</head>

<body>
	<main class="container-fluid">
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item" role="presentation">
				<button class="nav-link" id="home-tab" data-bs-toggle="tab"
					data-bs-target="#home" type="button" role="tab"
					aria-controls="home" aria-selected="true">USER EDITTION</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link active" id="profile-tab"
					data-bs-toggle="tab" data-bs-target="#profile" type="button"
					role="tab" aria-controls="profile" aria-selected="false">USER
					LIST</button>
			</li>

		</ul>
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade" id="home" role="tabpanel"
				aria-labelledby="home-tab">
				<div class="container-fluid">


					<form action="/admin/UserManagement" method="post"
						enctype="multipart/form-data">
						<div class="card">
							<jsp:include page="/admin/common/inform.jsp"></jsp:include>
							<div class="card-body">
								<div class="row">
									<div class="col">
										<div class="mb-3">
											<label for="userID" class="form-label">userID</label> <input
												type="text" class="form-control" name="userID" id="userID"
												aria-describedby="userIDHid" placeholder="userID"
												value="${user.userID }" /> <small id="userIDHid"
												class="form-text text-muted">userID is required</small>
										</div>
										<div class="mb-3">
											<label for="fullname" class="form-label">FullName </label> <input
												type="text" class="form-control" name="fullname"
												id="fullname" aria-describedby="fullnameHid"
												placeholder="FullName" value="${user.fullname }" /> <small
												id="fullnameHid" class="form-text text-muted">fullname
												is required</small>
										</div>

										<div class="form-group">
											<div class="form-check">
												<input class="form-check-input" type="radio" value="true"
													id="admin" name="admin" ${user.admin ? 'checked' : ''} />
												<label class="form-check-label" for="active"> ADMIN
												</label>
											</div>
											<div class="form-check">
												<input class="form-check-input" type="radio" value="false"
													id="admin" name="admin" ${!user.admin ? 'checked' : ''} />
												<label class="form-check-label" for="active"> USER </label>
											</div>
										</div>

									</div>
									<div class="col">
										<div class="mb-3">
											<label for="password" class="form-label">Password</label> <input
												type="password" class="form-control" name="password"
												id="password" placeholder="password"
												value="${user.password }" />
										</div>
										<div class="mb-3">
											<label for="email" class="form-label">Email</label> <input
												type="email" class="form-control" name="email" id="email"
												aria-describedby="emailHid" placeholder="abc@mail.com"
												value="${user.email }" /> <small id="emailHid"
												class="form-text text-muted">Email is required</small>
										</div>

									</div>
								</div>
							</div>
							<div class="card-footer text-muted">
								<div class="footer">
									<button formaction="UserManagement/create">Create</button>
									<button formaction="UserManagement/update">Update</button>
									<button formaction="UserManagement/delete">Delete</button>
									<button formaction="UserManagement/reset">Reset</button>
								</div>
							</div>
						</div>

					</form>


				</div>
			</div>
		</div>
		<div class="tab-pane fade show active" id="profile" role="tabpanel"
			aria-labelledby="profile-tab">
			<div class="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>USERID</th>
							<th>FULLNAME</th>
							<th>EMAIL</th>
							<th>ROLE</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<c:forEach var="item" items="${listusercount}">
						<tbody>
							<tr>
								<td>${item.userID}</td>
								<td>${item.fullname}</td>
								<td>${item.email}</td>
								<td>${item.admin}</td>
								<td><a href="UserManagement/edit?userID=${item.userID}"><i
										class="bi bi-pencil-square" aria-hidden="true"></i>Edit</a> <a
									href="UserManagement/delete?userID=${item.userID}"><i
										class="bi bi-trash" aria-hidden="true"></i>Delete</a></td>
							</tr>
							<!-- Add more rows as needed -->
						</tbody>
					</c:forEach>
				</table>
			</div>
			<div class="footer">
				<form method="get" action="UserManagement">
					<!-- Nút chuyển đến trang đầu tiên -->
					<button type="submit" name="pageNumber" value="1">
						<i class="bi bi-chevron-double-left"></i>
					</button>
					<!-- Nút chuyển đến trang trước -->
					<button type="submit" name="pageNumber" value="${pageNumber - 1}">
						<i class="bi bi-chevron-left"></i>
					</button>
					<!-- Hiển thị số trang hiện tại và tổng số trang -->
					<span>Page ${pageNumber} of ${totalPages}</span>
					<!-- Nút chuyển đến trang tiếp theo -->
					<button type="submit" name="pageNumber" value="${pageNumber + 1}">
						<i class="bi bi-chevron-right"></i>
					</button>
					<!-- Nút chuyển đến trang cuối cùng -->
					<button type="submit" name="pageNumber" value="${totalPages}">
						<i class="bi bi-chevron-double-right"></i>
					</button>
				</form>
			</div>


		</div>
	</main>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
		integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
		crossorigin="anonymous"></script>
	<script src="admin.js"></script>
</body>


</html>