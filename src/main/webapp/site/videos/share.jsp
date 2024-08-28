<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

    <main class="container">
        <section class="row">
            <div class="offset-3 col-6">
                <form action="Share" method="post">
                <input type="hidden" name="videoId" value="${videoId }" />
                    <div class="card">
                        <div class="card-header">

                            Send Video for your friend</div>
                        <div class="card-body">
                            <div class="mb-3">
                                <label for="" class="form-label">Your's friend Email:</label>
                                <input type="text" class="form-control" name="email" id="email"
                                    aria-describedby="emailHeperId" placeholder="Email" />
                                <small id="emailHeperId" class="form-text text-muted">Email is Required</small>
                            </div>

                        </div>
                        <div class="card-footer">
                            <button class="btn btn-success">Send</button>
                        </div>
                    </div>

                </form>
            </div>
        </section>
 