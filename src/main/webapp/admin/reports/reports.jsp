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
				<button class="nav-link active" id="home-tab" data-bs-toggle="tab"
					data-bs-target="#home" type="button" role="tab"
					aria-controls="home" aria-selected="true">Favourite</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link" id="profile-tab" data-bs-toggle="tab"
					data-bs-target="#profile" type="button" role="tab"
					aria-controls="profile" aria-selected="false">Favourite
					user</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link" id="sharefriend-tab" data-bs-toggle="tab"
					data-bs-target="#sharefriend" type="button" role="tab"
					aria-controls="sharefriend" aria-selected="false">Share
					friend</button>
			</li>

		</ul>
		<jsp:include page="/admin/common/inform.jsp"></jsp:include>
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade show active" id="home" role="tabpanel"
				aria-labelledby="home-tab">

				<table class="table table-bordered mt-3">
					<tr>
						<td>Video Tilte</td>
						<td>Favourite Count</td>
						<td>Lasted Date</td>
						<td>Oldest Date</td>
					</tr>
					<c:forEach var="items" items="${reports}">
						<tr>
							<td>${items.tittle}</td>
							<td>${items.likeCount}</td>
							<td>${items.newest}</td>
							<td>${items.oldest}</td>
						</tr>
					</c:forEach>
				</table>


			</div>
			<div class="tab-pane fade" id="profile" role="tabpanel"
				aria-labelledby="profile-tab">
				<form action="" method="get"
					>
					<div class="row mt-3">
						<div class="col">
							<div class="form-inline">
								<div class="form-group ml-3">
									<label for="videoUserID">Video Title:</label> <select
										name="videoUserID" id="videoUserID" class="form-control">
										<c:forEach var="item" items="${vidList}">
											<option value="${item.videoID}"
												${item.videoID eq videoUserId ? 'selected' : ''}>${item.tittle}</option>
										</c:forEach>
									</select> 

									<button
										
										class="btn btn-info ml-3">Report</button>

								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col mt-3">
							<table class="table table-bordered">
								<tr>
									<td>User Name</td>
									<td>Full Name</td>
									<td>Email</td>
									<td>Favourite Date</td>
								</tr>
								<c:forEach var="item" items="${favUsers}">
									<tr>
										<td>${item.userID }</td>
										<td>${item.fullname }</td>
										<td>${item.email }</td>
										<td>${item.likeDate }</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</form>
			</div>
			<div class="tab-pane fade" id="sharefriend" role="tabpanel"
				aria-labelledby="sharefriend-tab">
				<form action="/ASMJAVA4/admin/ReportsManagement" method="get">
					<div class="row mt-3">
						<div class="col">
							<div class="form-inline">
								<div class="form-group">
									<label for="">Video Title </label> <input type="text"
										class="form-control" id="videotittle" name="videotittle"
										placeholder="title" />
									<button
										class="btn btn-info">Report</button>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col mt-3">
							<table class="table table-bordered">
								<tr>
									<td>Sender Name</td>
									<td>VideoID</td>
									<td>Receiver Email</td>
									<td>Sent Date</td>
								</tr>
								<c:forEach var="item" items="${share}">


									<tr>
										<td>${item.userID }</td>
										<td>${item.videoID }</td>
										<td>${item.email }</td>
										<td>${item.sharedate }</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
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