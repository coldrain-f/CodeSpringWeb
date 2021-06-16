<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Modify/Remove</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board Modify/Remove
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<form>
	                        	<div class="form-group">
	                        		<label for="">BNO</label>
	                        		<input type="text" class="form-control" name="bno" value="<c:out value="${board.bno }" />" readonly="readonly" />
	                        	</div>
	                        
	                           	<div class="form-group">
	                            	<label for="">Title</label>
	                            	<input type="text" class="form-control" name="title" value="<c:out value="${board.title }" />" />                            	
	                           	</div>
	                           	
	                           	<div class="form-group">
	                           		<label for="">Content</label>
	                           		<textarea rows="5" cols="50" class="form-control" name="content"><c:out value="${board.content }" /></textarea>
	                           	</div>
	                           	
	                           	<div class="form-group">
	                           		<label for="">Writer</label>
	                           		<input type="text" class="form-control" name="writer" value="<c:out value="${board.writer }" />" readonly="readonly" />
	                           	</div>
	                           	
	                           	<button type="button" data-oper="modify" class="btn btn-default">Modify</button>
	                           	<button type="button" data-oper="remove" class="btn btn-danger">Remove</button>
	                           	<button type="button" data-oper="list" class="btn btn-info">List</button>                      	
                        	</form>
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
<script>
	$(document).ready(function(){
		
		var formObj = $("form");
		
		$("button").click(function(e){
			e.preventDefault();
			
			var operation = $(this).data("oper");
			
			console.log(operation);
			
			if (operation === "list") {
				self.location = "/board/list";
			} else if (operation === "remove") {
				formObj.attr("action", "/board/remove")
					   .attr("method", "post")
					   .submit();
			} else if (operation === "modify") {
				formObj.attr("action", "/board/modify")
					   .attr("method", "post")
					   .submit();
			}
		});
		
	});
</script>
   
<%@include file="../includes/footer.jsp" %>