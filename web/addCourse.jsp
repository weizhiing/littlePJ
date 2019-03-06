<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="Bean.UserBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">


<title>添加课程


</title>
</head>
<body>
	<%

		if (session.getAttribute("user") == null) {
			response.sendRedirect("HomePageServlet");
		}
	
	
	//接收添加课程的error信息（课程重复），并弹窗显示
	if(request.getAttribute("error") != null){
	%>
	<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" style="display: none;">error提示框</button>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog  modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">提示</h4>
                </div>
                <div class="modal-body bg-danger">
                    <h5>开课失败！<%= request.getAttribute("error") %></h5>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    
                </div>
            </div>
        </div>
    </div>
 
	<% 	} %>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">

			<div class="navbar-header">
				<a href="HomePageServlet" class="navbar-left"><img
					src="images/online.png"></a> <a href="HomePageServlet"
					class="navbar-brand">在线课程 </a>
			</div>

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="HomePageServlet">首页</a></li>
					<li class="dropdown"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#">${sessionScope.user.username}<strong
							class="caret"></strong></a>
						<ul class="dropdown-menu">
							<li><a href="UserPageServlet">个人信息</a></li>
							<li class="divider"></li>
							<li><a href="LogoutServlet">退出登录</a></li>
						</ul>
				</ul>

				<!-- 简易搜索框 -->
				<form class="navbar-form navbar-right" role="search"
					action="SearchPageServlet" method="post">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<i class="glyphicon glyphicon-search"></i>
							</div>
							<input type="text" class="form-control" id="searchCourseName"
								placeholder="请输入课程关键词" name="courseNameSearch" />
						</div>
					</div>
					<button type="submit" class="btn btn-primary">搜索</button>
				</form>
			</div>
		</div>
	</nav>

	<div class="container margin-top-80 margin-bottom-80">
		<div class="row clearfix">
			<div class="col-md-4 column"></div>

			<!-- 添加课程的表单 -->
			<div class="col-md-4 column">
				<form role="form" action="OpenCoursePageServlet" method="post"
					onsubmit="return(addCheckName() && addCheckDesc() && addCheckImage())"
					enctype="multipart/form-data">
					<h2>添加课程</h2>
					<div class="form-group">
						<label for="addCourseName">课程名</label> <input class="form-control"
							id="addCourseName" name="addCourseName" placeholder="请输入课程名"
							type="text" />
						<p id="addNameAlert"></p>
					</div>
					<div class="form-group">
						<label for="addCourseDescription">课程简介</label>
						<textarea class="form-control" id="addCourseDescription"
							placeholder="请输入课程简介" name="addCourseDescription"></textarea>
						<p id="addDescAlert"></p>
					</div>
					<div class="form-group">
						<label for="addCourseImage">课程封面</label> <input type="file"
							id="addCourseImage" name="addCourseImage" accept="image/*"
							style="min-width: 250px;" onchange="preview(this)" />
						<p id="addImageAlert"></p>
						<br> <img id="imgPreview" class="bg-warning" /><br>
					</div>
					<button type="submit" class="btn btn-primary">添加</button>
					<button type="button" class="btn btn-danger"
						onClick="javascript:window.location.href='UserPageServlet';">取消</button>
				</form>
			</div>
			<div class="col-md-4 column"></div>
		</div>
	</div>

	<div id="footer" class="container">
		<nav class="navbar navbar-default navbar-fixed-bottom">
			<div class="navbar-inner navbar-content-center">
				<p class="text-muted credit">
					联系邮箱: <a>someone@example.com</a>
				</p>
			</div>
		</nav>
	</div>
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/addCourseCheck.js"></script>
	<script type="text/javascript">
		function preview(obj) {

			var file = obj.files[0];

			console.log(obj);
			console.log(file);
			console.log("file.size = " + file.size);
			//file.size 单位为byte

			var reader = new FileReader();
			//读取文件过程方法
			reader.onloadstart = function(e) {
				console.log("开始读取....");
			}
			reader.onprogress = function(e) {
				console.log("正在读取中....");
			}
			reader.onabort = function(e) {
				console.log("中断读取....");
			}
			reader.onerror = function(e) {
				console.log("读取异常....");
			}
			reader.onload = function(e) {
				console.log("成功读取....");

				var img = document.getElementById("imgPreview");
				img.src = e.target.result;

			}
			reader.readAsDataURL(file)
		}
	</script>
	
	<script>
	
	//自动弹出error提示框
        $(function () {
            $('#myModal').modal({
            keyboard: true
        })});
    </script>
</body>
</html>