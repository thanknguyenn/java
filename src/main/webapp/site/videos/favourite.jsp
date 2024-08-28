<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<main class="container">
		<section class="row">
			<div class="col-9">
<jsp:include page="/site/common/inform.jsp"></jsp:include>
    <div class="row p-2">
        <c:forEach var="item" items="${listFavourite}">
            <div class="col-3 mt-2">
                <div class="card text-center">
                    <div class="card-body">
                         <a href="Detail?videoID=${item.video.videoID}">
                            <img src="../${empty item.video.poster ? 'uploads/1058.jpg' : item.video.poster}" width="90%" alt="" class="img-fluid">
                        </a>
                        <div class="row border-top mt-2">
                            <b>${item.video.tittle}</b>
                        </div>
                    </div>
                    <div class="card-footer">
                        <a href="Favourite/delete?favouriteID=${item.favouriteID}" class="btn btn-success">UnLike</a>
                        <a href="Share?videoId=${item.video.videoID}" class="btn btn-info">Share</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
				<div class="row">
					<div class="col">
						    <div class="footer">
						<form method="get" action="Favourite">
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
		</section>
	</main>
