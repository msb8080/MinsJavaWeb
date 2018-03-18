<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品</title>
<style type="text/css">
	a:link {
		font-size: 19px;
		color: purple;
		text-decoration: none;
	}
	
	a:visited {
		font-size: 19px;
		color: purple;
		text-decoration: none;
		//所修饰文本为正常格式
	}
	
	a:hover {
		font-size: 19px;
		color: #999999;
		text-decoration: none;
		//所修饰文本带有下划线
	}
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.5.1.min.js"></script>
<script type="text/javascript">
	/* var text = {"bookName":"MySql",'totalBookNumber':6,'totalPrice':700};
	alert(text.bookName);  */
	$(function() {
		var isHasCart = "${sessionScope.sc==null}";
		if (isHasCart == "true") {
			$("#cartstatus").hide();
		} else {
			$("#cartstatus").show();
			$("#bookName").text("${sessionScope.sc.bookName}");
			$("#totalPrice").text("${sessionScope.sc.totalPrice}");
			$("#totalBookNumber").text("${sessionScope.sc.totalBookNumber}");
		}
		$("a").click(function() {
			$("#cartstatus").show();
			var url = this.href;
			var args = {
				"time" : new Date()
			};

			$.getJSON(url, args, function(data) {

				$("#bookName").text(data.bookName);
				$("#totalPrice").text(data.totalPrice);
				$("#totalBookNumber").text(data.totalBookNumber);
			});
			return false;
		})

	})
</script>
</head>
<body>
	<div id="cartstatus">
		您已将&nbsp;<span id="bookName"></span>加入到购物车中。 购物车中的书有&nbsp;<span
			id="totalBookNumber"></span>本， 总价为&nbsp;<span id="totalPrice"></span>元<br>
	</div>
	Java&nbsp;&nbsp;
	<a
		href="${pageContext.request.contextPath}/addToCart?id=java&price=100">加入购物车</a>
	<br> MySql&nbsp;&nbsp;
	<a
		href="${pageContext.request.contextPath}/addToCart?id=MySql&price=200">加入购物车</a>
	<br> NodeJs&nbsp;&nbsp;
	<a
		href="${pageContext.request.contextPath}/addToCart?id=NodeJs&price=100">加入购物车</a>
</body>
</html>