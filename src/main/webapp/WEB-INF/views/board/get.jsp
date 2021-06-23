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
                           	
                           	<form id="actionForm" action="/board/list" method="get">
                           		<input type="hidden" name="pageNum" value="${criteria.pageNum }"/>
                           		<input type="hidden" name="amount" value="${criteria.amount }" />
                           		<input type="hidden" name="bno" value="${board.bno }" />
                           		<input type="hidden" name="type" value="${criteria.type }" />
                           		<input type="hidden" name="keyword" value="${criteria.keyword }" />
                           	</form>
                           	
                           	<button type="button" class="btn btn-default listBtn">List</button>
                           	<button type="button" class="btn btn-default modifyBtn">Modify</button>
                            
                            <script src="/resources/js/reply.js"></script>
                            
                            <script>
                            	console.log("==============")
                            	console.log("JS TEST")
                            	
                            	var bnoValue = "<c:out value='${board.bno }' />"
                            	
                            	//for replyService add test
                            	/* replyService.add(
                            			{reply:"JS Test", replyer:"tester", bno:bnoValue},
                            			function (result) {
                            				alert("RESULT: " + result)
                            			}
                            	) */
                            	
                            	/* replyService.getList(
                            			{bno: bnoValue, page: 1 },
                            			function(list) {
                            				for (var i = 0, len = list.length || 0; i < len; i++) {
                            					console.log(list[i])
                            				}
                            			}
                            	) */
                            	
                            	/* replyService.remove(8, function(count) {
                            		console.log("count = " + count)
                            		if (count === "success") {
                            			alert("REMOVED")
                            		}
                            	}, function(er) {
                            		alert("ERROR...")
                            	}) */
                            	
                            	/* replyService.update(
                            			{rno: 7, bno: bnoValue, reply: "Modified Reply..."},
                            			function(result) {
                            				alert("수정 완료...")
                            			}
                            	) */
                            	
                            	/* replyService.get(7, function(data) {
                            		console.log(data)	
                            	}) */
                            	
                            	
                            </script>
                            
                            <script>
                            	$(document).ready(function() {
                            		var actionForm = $("#actionForm")
                            		
                            		$(".listBtn").on("click", function(event) {
                            			event.preventDefault()
                            			actionForm.find("input[name='bno']")
                            					  .remove()
                            			actionForm.submit()
                            		})
                            		
                            		$(".modifyBtn").on("click", function(event) {
                            			event.preventDefault()
                            			actionForm.attr("action", "/board/modify")
                            					  .submit()
                            		})
                            		
                            	})
                            </script>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <!-- 덧글 관련 -->
            <div class="row">
            	<div class="col-lg-12">
            		
            		<div class="panel panel-default">
            			<div class="panel-heading">
            				<i class="fa fa-comments fa-fw"></i> Reply
            				<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">New Reply</button>
            			</div>
            			
            			<div class="panel-body">
            				<ul class="chat">
            					<li class="left clearfix" data-rno="12">
            						<div>
            							<div class="header">
            								<strong class="primary-font">user00</strong>
            								<samll class="pull-right text-muted">2021-06-23 13:13</samll>
            							</div>
            							<p>Good Job!</p>
            						</div>
            					</li>
            				</ul>
            			</div>
            		</div>
            	</div>
            </div>
            
            <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
                        </div>
                        <div class="modal-body">
                        	<div class="form-group">
                        		<label>Reply</label>
                        		<input type="text" class="form-control" name="reply" />
                        	</div>
                        	
                        	<div class="form-group">
                        		<label>Replyer</label>
                        		<input type="text" class="form-control" name="replyer"/>
                        	</div>
                        	
                        	<div class="form-group">
                        		<label>Reply Date</label>
                        		<input type="text" class="form-control" name="replydate" />
                        	</div>
                        </div>
                        <div class="modal-footer">
                        	<button type="button" id="modalModifyBtn" class="btn btn-warning">Modify</button>
                        	<button type="button" id="modalRemoveBtn" class="btn btn-danger">Remove</button>
                        	<button type="button" id="modalRegisterBtn" class="btn btn-primary">Register</button>
                            <button type="button" id="modalCloseBtn" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
            
            <script>
            	$(document).ready(function() {
            		var bnoValue = "<c:out value='${board.bno }' />";
            		var replyUL = $(".chat")
            		
            		showList(1)
            		
            		function showList(page) {
            			replyService.getList({bno: bnoValue, page: page || 1}, function(list) {
            				var str = ""
            				if (list == null || list.length == 0) {
            					replyUL.html("")
            					return
            				}
            				
            				for (var i = 0, len = list.length || 0; i < len; i++) {
            					str += "<li class='left clearfix' data-rno='" + list[i].rno + "'>"
            					str += "<div>"
            					str += "<div class='header'>"
            					str += "<strong class='primary-font'>" + list[i].replyer + "</strong>"
            					str += "<samll class='pull-right text-muted'>"+ replyService.displayTime(list[i].replydate) +"</samll>"
            					str += "</div>"
            					str += "<p>" + list[i].reply + "</p>"
            					str += "<div></li></ul></div>"
            				}
            				
            				replyUL.html(str)
            			})
            		}
            		
            		var modal = $(".modal")
            		var modalInputReply = modal.find("input[name='reply']")
            		var modalInputReplyer = modal.find("input[name='replyer']")
            		var modalInputReplydate = modal.find("input[name='replydate']")
            		
            		var modalModifyBtn = $("#modalModifyBtn")
            		var modalRemoveBtn = $("#modalRemoveBtn")
            		var modalRegisterBtn = $("#modalRegisterBtn")
            		
            		$("#addReplyBtn").on("click", function(e) {
            			modal.find("input").val("") // closest()가 뭐지?
            			modalInputReplydate.closest("div").hide()
            			modal.find("button[id != 'modalCloseBtn']").hide()
            			
            			modalRegisterBtn.show()
            			$(".modal").modal("show")
            		})
            		
            		modalRegisterBtn.on("click", function() {
            			var reply = {
            					reply: modalInputReply.val(),
            					replyer: modalInputReplyer.val(),
            					bno: bnoValue
            			}
            			
            			replyService.add(reply, function(result) {
            				alert(result)
            				
            				modal.find("input").val("")
            				modal.modal("hide")
            				
            				showList(1)
            			})
            			
            		})
            		
            		
            		$(".chat").on("click", "li", function(e) {
            			var rno = $(this).data("rno")
            			console.log(rno)
            			
            			//동작 안함
            			replyService.get(rno, function(reply) {
            				console.log(reply)
            				modalInputReply.val(reply.rno)
            				modalInputReplyer.val(reply.replyer)
            				modalInputReplydate.val(replyService.displayTime(reply.replydate)).attr("readonly", "readonly")
            				modal.data("rno", reply.rno)
            				
            				modal.find("button[id != 'modalCloseBtn']").hide()
            				modalModifyBtn.show()
            				modalRemoveBtn.show()
            				
            				$(".modal").modal("show")
            			})
            		})
            		
            	})
            </script>
   
<%@include file="../includes/footer.jsp" %>