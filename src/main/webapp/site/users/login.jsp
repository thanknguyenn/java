<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



    <main class="container">
        <div class="row">
            <div class="offset-4 col-4">
                <form action="" method="post">
                    <div class="card">
                        <div class="card-header">
                            <b>Login to System</b>
                        </div>
                        <div class="card-body">
                            <jsp:include page="/site/common/inform.jsp"></jsp:include>
                            <div class="mb-3">
                                <label class="form-label" for="userID">userID</label>
                                <input type="text" class="form-control" name="userID" id="userID"
                                    aria-describedby="helpId" placeholder="userID" value="${user.userID}" />
                                <small id="userIDHid" class="form-text text-muted">userID is Required</small>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="password">Password</label>
                                <input type="password" class="form-control" name="password" id="password"
                                    aria-describedby="helpId" placeholder="password" value="${user.password}" />
                                <small id="password" class="form-text text-muted">Password is Required</small>
                            </div>
                            <div class="form-check form-check-inline">
                                <label><input type="checkbox" class="form-check-input" name="remember">Remember
                                    me</label>
                            </div>
                        </div>
                        <div class="card-footer text-muted"></div>
                        <button class="btn btn-success">Login</button>
                    </div>
                </form>
            </div>
        </div>
    </main>
   
