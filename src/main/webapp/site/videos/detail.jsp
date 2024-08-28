<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


				<main class="container">
					<div class="row">

						<div class="col-7">
							<div class="card text-center mt-3">
								<jsp:include page="/site/common/inform.jsp"></jsp:include>
								<div class="card-body">
									<div class="row">

										<iframe width="560" height="315" src="${video.href }"
											title="YouTube video player" frameborder="0"
											allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
											referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
									</div>
									<div class="row">
										<div class="col p-2">
											<b>${video.tittle}</b>
										</div>
									</div>
									<div class="row p-2">
										<label for="decription" class="col-sm-3 col-form-label">DESCRIPTION</label>
										<div class="col-sm-9">
											<textarea class="form-control" name="description" id="decription" rows="8"
												readonly="true">${video.decription}</textarea>
										</div>
									</div>

									<div class="card-footer text-right">
										<a href="Like?videoId=${video.videoID}" class="btn btn-success">Like</a>
										<a href="Share?videoId=${video.videoID}" class="btn btn-info">Share</a>
									</div>
								</div>
							</div>

						</div>
						<div class="col-5">
							<div class="row border mt-3 mb-3">

								<c:forEach var="item" items="${listvideo}">
									<div class="col-4 mb-3 mt-3">
										<a href="Detail?videoID=${item.videoID}" class="text-decoration-none text-dark">
											<div class="card">
												<img src="../${empty item.poster ? 'uploads/1058.jpg' : item.poster}"
													width="90%" class="card-img-left" alt="">

											</div>
										</a>
									</div>
									<div class="card-body col-5">
										<strong>
											<p class="card-text ">${item.tittle}</p>
										</strong>
									</div>


								</c:forEach>
							</div>
						</div>

					</div>
				</main>

