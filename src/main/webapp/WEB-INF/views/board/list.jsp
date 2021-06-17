<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            DataTables Advanced Tables
                            <button type="button" id="regBtn" class="btn btn-xs pull-right">Register new Board</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>BNO</th>
                                        <th>Title</th>
                                        <th>Writer</th>
                                        <th>RegDate</th>
                                        <th>UpdateDate</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="board" items="${list }">
	                                    <tr>
	                                        <td><c:out value="${board.bno }" /></td>
	                                        <td>
	                                        	<a href="/board/get?bno=<c:out value="${board.bno }" /> ">
	                                        		<c:out value="${board.content }" />
	                                        	</a>
	                                       	</td>
	                                        <td><c:out value="${board.writer }" /></td>
	                                        <td><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd" /></td>
	                                        <td><fmt:formatDate value="${board.updatedate }" pattern="yyyy-MM-dd" /></td>
	                                    </tr>                                	
                                	</c:forEach>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                            
                            <div class="pull-right">
                            	<ul>
                            		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" step="1">
                            			
                            		</c:forEach>
                            	</ul>
                            </div>
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <div id="myModal" class="modal" tabindex="-1" role="dialog">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Modal title</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-primary">Save changes</button>
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			      </div>
			    </div>
			  </div>
			</div>
			            
            <script>
            	$(document).ready(function(){
            		var result = "<c:out value='${result }' />";
            		
            		checkModal(result);
            		
            		history.replaceState({}, null, null);
            		
            		function checkModal(result) {
            			if (result === '' || history.state) {
            				return;
            			}
            			
            			if (result === "success") {
            				$(".modal-body").html("정상적으로 처리되었습니다.");	
            			} else if (parseInt(result) > 0) {
            				$(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다.");
            			}
            			
            			$("#myModal").modal("show");
            		}
            		
            		$("#regBtn").click(function(){
            			self.location = "/board/register";
            		});
            	});
            </script>
   
<%@include file="../includes/footer.jsp" %>