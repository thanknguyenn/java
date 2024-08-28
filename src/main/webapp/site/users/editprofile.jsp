<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



    <main class="container">
        <section class="row">
            <div class="col-8 offset-2">
                <form action="" method="post">

                    <div class="card">
                        <div class="card-header"><b>Edit profile</b></div>
                          <jsp:include page="/site/common/inform.jsp"></jsp:include>
                        <div class="card-body">
                      
                            <div class="row">
                                <div class="col">
                                    <div class="mb-3">
                                        <label for="userID" class="form-label">userID</label>
                                        <input type="text" class="form-control" name="userID" id="userID"
                                            aria-describedby="userIDHid" placeholder="userID" value="${user.userID }" required />
                                        <small id="userIDHid" class="form-text text-muted">userID is
                                            required</small>
                                    </div>
                                    <div class="mb-3">
                                        <label for="fullName" class="form-label">FullName</label>
                                        <input type="text" class="form-control" name="fullname" id="fullname"
                                            aria-describedby="fullnameHid" placeholder="FullName" value="${user.fullname}" Required />
                                        <small id="fullnameHid" class="form-text text-muted">FullName is
                                            required</small>
                                    </div>

                                </div>
                                <div class="col">
                                    <div class="mb-3">
                                        <label for="password" class="form-label">Password</label>
                                        <input type="password" class="form-control" name="password" id="password"
                                            placeholder="password" value=${user.password } required />
                                    </div>
                                    <div class="mb-3">
                                        <label for="email" class="form-label">Email</label>
                                        <input type="email" class="form-control" name="email" id="email"
                                            aria-describedby="emailHid" placeholder="abc@mail.com" value="${user.email }" required />
                                        <small id="emailHid" class="form-text text-muted">Email is required</small>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="card-footer text-muted">
                            <button class="btn btn-success">Update</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </main>
  