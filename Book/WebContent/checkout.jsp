<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Product"%>
<%@page import="model.Category"%>
<%@page import="model.Image"%>
<%@page import="model.Cart"%>
<%@page import="model.Item"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.*"%>

<%
	
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<jsp:include page="include/head.jsp"></jsp:include>

<link rel="stylesheet" href="css/checkout.css">

</head>

<body>


	<!-- menu top -->
	<jsp:include page="include/menu-area.jsp"></jsp:include>
	<!-- end menu top -->


	<div class="infomation">
		<h3>Check out</h3>
	</div>
	<div class="container">

		<div class="list-product">



			<form action="/Book/Checkout" method="post">
				<div class="input-group">
					<label for="">Nhập tên khách hàng </label> <input type="text"
						name="customer" />
				</div>

				<div class="input-group">
					<label for="">Nhập địa chỉ nhận hàng</label> <input type="text"
						name="address" />
				</div>

				<div class="input-group">
					<label for="">Nhập sdt liên hệ</label> <input type="text"
						name="phone" />
				</div>

				<button type="submit">CheckOut</button>
			</form>

		</div>

	</div>
	<script src="js/my.js"></script>

</body>

</html>