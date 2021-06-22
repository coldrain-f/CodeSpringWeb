console.log("Reply Module...........")
var replyService = (function(){
	function add(reply, callback, error) {
		console.log("reply.........")
		
		$.ajax({
			type: "post",
			url: "/replies/new",
			data: JSON.stringify(reply), //자바스크립트의 객체를 JSON문자열로 변환한다
			contentType: "application/json; charset=utf-8",
			success: function(result, status, xhr) {
				if (callback) {
					callback(result)
				}
			},
			error: function(xhr, status, er) {
				if (error) {
					error(er)
				}
			}
		})
	}

	function getList(param, callback, error) {
		var bno = param.bno
		var page = param.page || 1

		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
			function(data) {
				if (callback) {
					callback(data)
				}
			}).fail(function(xhr, status, err) {
				if (error) {
					error()
				}
			})
	}
	
	return {
		add: add
	}
})()