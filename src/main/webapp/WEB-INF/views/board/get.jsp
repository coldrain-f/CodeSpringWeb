<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Read</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board Read
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<div class="form-group">
                        		<label for="">BNO</label>
                        		<input type="text" class="form-control" name="bno" value="<c:out value="${board.bno }" />" readonly="readonly" />
                        	</div>
                        
                           	<div class="form-group">
                            	<label for="">Title</label>
                            	<input type="text" class="form-control" name="title" value="<c:out value="${board.title }" />" readonly="readonly" />                            	
                           	</div>
                           	
                           	<div class="form-group">
                           		<label for="">Content</label>
                           		<textarea rows="5" cols="50" class="form-control" name="content"><c:out value="${board.content }" /></textarea>
                           	</div>
                           	
                           	<div class="form-group">
                           		<label for="">Writer</label>
                           		<input type="text" class="form-control" name="writer" value="<c:out value="${board.writer }" />" readonly="readonly" />
                           	</div>
                           	
                           	<button type="button" class="btn btn-default" onclick="location.href='/board/list'">List</button>
                           	<button type="button" class="btn btn-default" onclick="location.href='/board/modify?bno=<c:out value="${board.bno }" />'">Modify</button>
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
   
<%@include file="../includes/footer.jsp" %>