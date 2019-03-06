<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">


<title>登录
  </title>
</head>
<body>

	<%
	//用户已登录时跳转到首页
	if(session.getAttribute("user") != null){
		response.sendRedirect("homepage.jsp");
	}
	
	//接收登录的error信息（用户名不存在或密码错误），并弹窗显示
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
                    <h5>登录失败！<%= request.getAttribute("error") %></h5>
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
				<a href="HomePageServlet" class="navbar-left"><img src="images/online.png"></a>
				<a href="HomePageServlet" class="navbar-brand">在线课程 </a>
			</div>

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="HomePageServlet">首页</a></li>
					<li class="active"><a href="#">登录</a></li>
					<li><a href="register.jsp">注册</a></li>
				</ul>

			<!-- 简易搜索框 -->
			<form class="navbar-form navbar-right" role="search" action="SearchPageServlet" method="post">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<i class="glyphicon glyphicon-search"></i>
							</div>
							<input type="text" class="form-control" id="searchCourseName" placeholder="请输入课程关键词"
								name="courseNameSearch"/>
						</div>
					</div>
					<button type="submit" class="btn btn-primary">搜索</button>
				</form>
			</div>
		</div>
	</nav>

	<!-- 登录表单 -->
	<div class="container-fluid margin-top-80">
		<div class="row-fluid col-md-12 column">
			<form action="LoginPageServlet" method="get" class="layout-center form-horizontal" onsubmit="return (checkLoginUser() && checkLoginPsd())">
				<h2 class="col-sm-offset-4">请登录</h2>
				<div class="form-group">
					<label for="username" class="control-label col-sm-2 col-sm-offset-2">用户名</label>
					<div class="col-sm-4">
						<input type="text" id="username" name="username" value="${param.username}"
							class="form-control controls" placeholder="请输入用户名">
							<p id="loginUsernameAlert">用户名不能为空</p>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword1" class="control-label col-sm-2 col-sm-offset-2">密码</label> 
					<div class="col-sm-4">
						<input type="password" id="password" name="password" class="form-control" placeholder="请输入密码" value="${param.password}">
						<p id="loginPsdAlert">密码不能为空</p>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4">
						<div class="checkbox">				
							 <label class="checkbox-inline"><input type="checkbox"/>记住我<br/></label>						
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4">
						 <button type="submit" class="btn btn-primary btn-block">登录</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	

	<!-- 页脚 -->	
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
	<script src="js/checkLogin.js"></script>
	<script>
	
	//自动弹出error提示框
        $(function () {
            $('#myModal').modal({
            keyboard: true
        })});
    </script>
</body>
</html>