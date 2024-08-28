<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


    <main class="container">
        <section class="row">
            <div class="offset-4 col-4">
                <form action="Fogotpassword" method="post">
                    <div class="card mt-5">
                        <div class="card-header">
                            <b>Forgot password</b>
                        </div>
                        <div class="card-body">
                        <jsp:include page="/site/common/inform.jsp"></jsp:include>
                            <h4 class="card-title">
                                <div class="mb-3">
                                    <label for="userID" class="form-label">userID</label>
                                    <input type="text" class="form-control" name="userID" id="userID"
                                        aria-describedby="userIDHid" placeholder="userID" />
                                    <small id="userIDHid" class="form-text text-muted">userID is Required</small>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" name="email" id="email" placeholder="Email"
                                        required />
                                </div>


                            </h4>

                        </div>
                        <div class="card-footer text-muted">
                            <button class="btn btn-success">Retrive</button>
                        </div>
                    </div>

                </form>
            </div>
        </section>
    </main>
   