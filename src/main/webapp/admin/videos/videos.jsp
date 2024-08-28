<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en">

<head>

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
<style type="text/css">
/* CSS for custom file input */
.custom-file-input::-webkit-file-upload-a {
	visibility: hidden;
}

.custom-file-input::before {
	content: 'Choose File';
	display: inline-block;
	background: #007bff;
	color: #fff;
	border: 1px solid #007bff;
	padding: 6px 12px;
	border-radius: 4px;
	cursor: pointer;
}

.custom-file-input:hover::before {
	background: #0056b3;
}

.custom-file-input:active::before {
	background: #0056b3;
}
</style>
</head>

<body>
	<main class="container-fluid">

		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item" role="presentation"><a class="nav-link"
				id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="a"
				role="tab" aria-controls="home" aria-selected="true">VIDEO
					EDITTION</a></li>
			<li class="nav-item" role="presentation"><a
				class="nav-link active" id="profile-tab" data-bs-toggle="tab"
				data-bs-target="#profile" type="a" role="tab"
				aria-controls="profile" aria-selected="false">VIDEO LIST</a></li>

		</ul>
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade" id="home" role="tabpanel"
				aria-labelledby="home-tab">
				<div class="container-fluid card">


					<form action="/admin/VideosManagement" method="post"
						enctype="multipart/form-data">
						<div class="row card-header">
							<jsp:include page="/admin/common/inform.jsp"></jsp:include>
							<div class="col-3">
								<div class="card card-default">
									<img id="selectedImage"
										src="${video.poster != null ? video.poster : 'images/OIP.jpg'}"
										alt="" class="img-fluid" />

									<div class="input-group mb-3 mt-3">
										<div class="input-group-text">Upload</div>
										<div class="custom-file">
											<input type="file" class="custom-file-input" id="cover"
												name="cover" onchange="updateSelectedImage(this)" /> <label
												class="custom-file-label" for="cover" id="coverLabel">Choose
												File</label>
										</div>
									</div>
								</div>
							</div>



							<div class="col-9 card-body">
								<div class="form-group">
									<div class="mb-3">
<label for="videoID" class="form-label">Video ID?</label> <input
											type="text" class="form-control" name="videoID" id="videoID"
											aria-describedby="helpId" placeholder="videoID"
											value="${video.videoID }" /> <small id="videoID"
											class="form-text text-muted">videoID is required</small>
									</div>

								</div>
								<div class="form-group">
									<div class="mb-3">
										<label for="title" class="form-label">VIDEO tittle</label> <input
											type="text" class="form-control" name="tittle" id="tittle"
											aria-describedby="tittleHid" placeholder="tittle"
											value="${video.tittle}" /> <small id="tittleHid"
											class="form-text text-muted">tittle is required</small>
									</div>

								</div>
								<div class="form-group">
									<div class="mb-3">
										<label for="views" class="form-label">VIEW COUNT?</label> <input
											type="text" class="form-control" name="views" id="views"
											aria-describedby="viewsHid" placeholder="views"
											value="${video.views}" /> <small id="viewsHid"
											class="form-text text-muted">view count is required</small>
									</div>


								</div>
								<div class="form-group">
									<div class="mb-3">
										<label for="href" class="form-label">HREF</label> <input
											type="text" class="form-control" name="href" id="href"
											aria-describedby="hrefHid" placeholder="href"
											value="${video.href}" /> <small id="hrefsHid"
											class="form-text text-muted">href is required</small>
									</div>


								</div>
								<div class="form-group">
									<div class="form-check">
										<input class="form-check-input" type="radio" value="true"
											id="active" name="active" ${video.active ? 'checked' : ''} />
										<label class="form-check-label" for="active"> ACTIVE </label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="radio" value="false"
											id="inactive" name="active" ${!video.active ? 'checked' : ''} />
										<label class="form-check-label" for="inactive">
											INACTIVE </label>
									</div>
								</div>



							</div>
						</div>
						<div class="row">
							<div class="mb-3">
								<label for="decription" class="form-label">DECRIPTION</label>
								<textarea class="form-control" name="decription" id="decription"
									rows="3">${video.decription }</textarea>
							</div>

						</div>
						<div class="footer">
							<button formaction="VideosManagement/create" >Create</button>
							<button formaction="VideosManagement/update" >Update</button>
							<button formaction="VideosManagement/delete">Delete</button>
							<button formaction="VideosManagement/reset">Reset</button>
						</div>
					</form>


				</div>
			</div>
			<div class="tab-pane fade show active" id="profile" role="tabpanel"
				aria-labelledby="profile-tab">
<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>YOUTUBE ID</th>
								<th>VIDEO tittle</th>
								<th>VIEW COUNT</th>
								<th>STATUS</th>
								<th>ACTION</th>
							</tr>
						</thead>


						<c:forEach var="item" items="${listvideocount}">
							<tbody>
								<tr>
									<td>${item.videoID}</td>
									<td>${item.tittle}</td>
									<td>${item.views}</td>
									<td>${item.active}</td>
									<td><a
										href="VideosManagement/edit?videoID=${item.videoID }"><i
											class="bi bi-pencil-square" aria-hidden="true"></i>Edit</a> <a
										href="VideosManagement/delete?videoID=${item.videoID }"><i
											class="bi bi-trash" aria-hidden="true"></i>Delete</a></td>

								</tr>
								<!-- Add more rows as needed -->
							</tbody>
						</c:forEach>

					</table>
					<!-- Phần phân trang -->
					<!-- Phần phân trang -->
					<div class="footer">
						<form method="get" action="VideosManagement">
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





			</div>
		</div>


	</main>
	<script type="text/javascript">
		//JavaScript to update selected image
		function updateSelectedImage(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					document.getElementById('selectedImage').src = e.target.result;
				}
				reader.readAsDataURL(input.files[0]);
				// Update the label to show the selected file name
				document.getElementById('coverLabel').innerText = input.files[0].name;
			}
		}
	</script>
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