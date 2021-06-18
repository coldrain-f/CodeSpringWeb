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
	                                        	<a class="move" href="<c:out value="${board.bno }" />">
	                                        		<c:out value="${board.title }" />
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
                            
                            <form id="searchForm" action="/board/list" method="get">
                            	<select name="type">
                            	<option value="">---</option>
                            		<option value="T">제목</option>
                            		<option value="C">내용</option>
                            		<option value="W">작성자</option>
                            		<option value="TC">제목+내용</option>
                            		<option value="TCW">제목+내용+작성자</option>
                            	</select>
                            	<input type="text" name="keyword" />
                            	<input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum }" />
                            	<input type="hidden" name="amount" value="${pageMaker.criteria.amount }" />
                            	<button class="btn btn-default">Search</button>
                            </form>
                            
                            
                            <div class="pull-right">
                            	<ul class="pagination">
                            		<c:if test="${pageMaker.prev }">
	                            		<li class="page-item">
	                            			<a class="page-link" href="${pageMaker.startPage - 1 }" tabindex="-1">Previous</a>
	                            		</li>                            		
                            		</c:if>
                            		<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
                           				<li class="page-item ${pageMaker.criteria.pageNum eq num ? 'active' : '' }">
                           					<a href="${num }" class="page-link">${num }</a>
                           				</li>	
                            		</c:forEach>
                            		<c:if test="${pageMaker.next }">
	                            		<li class="page-item">
	                            			<a class="page-link" href="${pageMaker.endPage + 1 }" tabindex="-1">Next</a>
	                            		</li>                            		
                            		</c:if>
                            	</ul>
                            </div>
                            
                            <form id="actionForm" action="/board/list" method="get">
                            	<input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum }" />
                            	<input type="hidden" name="amount" value="${pageMaker.criteria.amount }" />
                            </form>
                            
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
            		var result = "<c:out value='${result }' />"
            		
            		checkModal(result)
            		
            		history.replaceState({}, null, null)
            		
            		function checkModal(result) {
            			if (result === '' || history.state) {
            				return
            			}
            			
            			if (result === "success") {
            				$(".modal-body").html("정상적으로 처리되었습니다.");	
            			} else if (parseInt(result) > 0) {
            				$(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다.")
            			}
            			
            			$("#myModal").modal("show")
            		}
            		
            		$("#regBtn").click(function(){
            			self.location = "/board/register"
            		});
            		
            		//링크 처리
            		var actionForm = $("#actionForm")
            		
            		$(".page-link").on("click", function(e) {
            			e.preventDefault()
            			
            			var targetPage = $(this).attr("href");
            			
            			console.log(targetPage)
            			
            			//input 태그의 name이 pageNum인것의 값을 targetPage로 변경해 준다.
            			actionForm.find("input[name='pageNum']").val(targetPage);
            			actionForm.submit()
            		});
            		
            		$(".move").on("click", function(event) {
            			event.preventDefault()
            			
            			var targetBno = $(this).attr("href");
            			console.log(targetBno)
            			
            			// actionForm.append("<input type='hidden' name='bno' value='" + targetBno + "'> /");
            			$("<input>").attr("type", "hidden")
            						.attr("name", "bno")
            						.attr("value", targetBno)
            						.appendTo(actionForm)
            			
            			actionForm.attr("action", "/board/get")
            					  .submit()
            		});
            		
            		var searchForm = $("#searchForm")
            		
            		$("#searchForm button").on("click", function(event) {
            			event.preventDefault()
            			
            			searchForm.find("input[name='pageNum']").val(1)
            			searchForm.submit()
            		})
            		
            	});
            </script>
   
<%@include file="../includes/footer.jsp" %>