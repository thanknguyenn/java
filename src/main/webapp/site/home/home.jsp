<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-9">
	<jsp:include page="/site/common/inform.jsp"></jsp:include>
	<div class="row p-2">
		<c:forEach var="item" items="${listvideocount}">
			<c:if test="${item.active}">
				<div class="col-3 mt-2">
					<div class="card text-center">
						<div class="card-body">
							<a href="Detail?videoID=${item.videoID}"> <img
								src="${empty item.poster ? 'uploads/1058.jpg' : item.poster}"
								width="90%" alt="${item.tittle}" class="img-fluid">
							</a>
							<div class="row border-top mt-2">
								<b>${item.tittle}</b>
							</div>
						</div>
						<div class="card-footer">
							<a href="Like?videoId=${item.videoID}" class="btn btn-success">Like</a>
							<a href="Share?videoId=${item.videoID}" class="btn btn-info">Share</a>
						</div>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</div>
	<div class="row">
		<div class="col">
			<div class="footer">
				<form method="get" action="Homepage">
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

<div class="col-3">
	<div class="row mt-3 mb-3">
		<div class="col">

			<div class="card">
				<div class="card-header">

					<i class="fa far fa-thumbs-up" aria-hidden="true"> My favourite</i>
				</div>
				<c:forEach var="item" items="${listfav}">
					<ul class="list-group list-group-flush">
						<div class="col text-center ">
							<strong>
								<li class="list-group-item"><a class="border-0"
									href="Detail?videoID=${item.video.videoID}"></a>${item.video.tittle}</li>
							</strong>
						</div>
						</ul>
				</c:forEach>
				
			</div>

		</div>
	</div>
</div>