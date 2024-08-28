<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



    <main class="container">
        <section class="row">
            <div class="offset-3 col-6 mt-4">
                <form action="Changepassword" method="post">
                    <div class="card">
                        <div class="card-header">
                            <b>Change password</b>
                        </div>
                        <div class="card-body">
                        <jsp:include page="/site/common/inform.jsp"></jsp:include>
                            <div class="row">
                                <div class="col">
                                    <div class="mb-3">
                                        <label for="userID" class="form-label">userID</label>
                                        <input type="text" class="form-control" name="userID" id="userID"
                                            aria-describedby="userIDHid" placeholder="userID" value="${userID}" />
                                        <small id="userIDHid" class="form-text text-muted">userID is
                                            required</small>
                                    </div>

                                    <div class="mb-3">
                                        <label for="password" class="form-label">Password</label>
                                        <input type="password" class="form-control" name="password" id="password"
                                            placeholder="password" />
                                    </div>


                                </div>
                                <div class="col">
                                    <div class="mb-3">
                                        <label for="currentPassword" class="form-label">Current password</label>
                                        <input type="password" class="form-control" name="currentPassword"
                                            id="currentPassword" placeholder="currentPassword" />
                                    </div>
                                    <div class="mb-3">
                                        <label for="confirmPassword" class="form-label">Confirm password</label>
                                        <input type="password" class="form-control" name="confirmPassword"
                                            id="confirmPassword" placeholder="Confirm password" />
                                    </div>


                                </div>
                            </div>
                        </div>
                        <div class="card-footer text-muted">
                            <button class="btn btn-success">Change Password</button>
                        </div>
                    </div>


                </form>
            </div>
        </section>
    </main>
