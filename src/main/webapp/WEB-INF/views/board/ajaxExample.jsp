<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Ajax Example</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Ajax Example
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<div class="form-group">
                        		<label for="bno">BNO</label>
                        		<input class="form-control" type="text" name="bno" id="bno" />
                        	</div>
                        	
                        	<div class="form-group">
                        		<label for="rno">RNO</label>
                        		<input class="form-control" type="text" name="rno" id="rno" />
                        	</div>
                        	
                        	<div class="form-group">
                        		<label for="reply">REPLY</label>
                        		<input class="form-control" type="text" name="reply" id="reply" />
                        	</div>
                        	
                        	<div class="form-group">
                        		<label for="replyer">REPLYER</label>
                        		<input class="form-control" type="text" name="replyer" id="replyer" />
                        	</div>
                        	
                        	<button type="button" data-oper="list" class="btn btn-primary">목록</button>
                        	<button type="button" data-oper="register" class="btn btn-primary">등록</button>
                        	<button type="button" data-oper="modify" class="btn btn-primary">수정</button>
                        	<button type="button" data-oper="remove" class="btn btn-primary">삭제</button>
                        </div>
                        <!-- /.panel-body -->
                        
                        <script>
                        	$(document).ready(function() {
                        		
                        		$("button").click(function() {
                        			
                        			const operation = $(this).data("oper")
                        			console.log("operation = " + operation)
                        			
                        			if (operation === "list") {
                        				
                        				$.ajax({
                        					type: "get",
                        					url: "/replies",
                        					success: function(result, status) {
                        						
                        					},
                        					error: function() {
                        						
                        					}
                        				})

                        			} else if (operation === "register") {
                        				
                        				var reply = {
                        					bno: $("#bno").val(),
                        					reply: $("#reply").val(),
                        					replyer: $("#replyer").val(),
                        				}
                        				
                        				$.ajax({
                        					type: "post", // method
                        					url: "/replies/new", // action
                        					data: JSON.stringify(reply), // 서버에 보내는 데이터
                        					contentType: "application/json; charset=utf-8", // 서버에 보내는 데이터의 타입
                        					success: function(result, status) { // 서버쪽에서 200 OK를 받았을 때 호출
                        						console.log("result = " + result)
                        					},
                        					error: function(xhr, status, er) { // 서버쪽에서 500 INTERNAL_SERVER_ERROR을 받았을 때 호출
                        						if (error) {
                        							error(er)
                        						}
                        					}
                        				})
                        			} else if (operation === "modify") {
                        				
                        			} else if (operation === "remove") {
                        				$.ajax({
                        					type: "delete",
                        					url: "/replies/" + $("#rno").val(),
                        					success: function(result, textStatus) {
                        						console.log("result = " + result)
                        						console.log("textStatus = " + textStatus)
                        					},
                        					error: function(xhr, textStatus, er) {
                        						if (error) {
                        							error(er)
                        						}
                        					}
                        				})
                        			}
                        			
                        		})
                        	})
                        </script>
                        
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
   
<%@include file="../includes/footer.jsp" %>