
<%@ page import="Bean.CourseBean" %>
<%@ page import="Bean.UrlBean" %>
<%@ page import="Bean.UserBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<title>个人信息
</title>

</head>


<body>

	<%
	if(session.getAttribute("user") == null){
		response.sendRedirect("HomePageServlet");
	}
%>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">

			<div class="navbar-header">
				<a href="HomePageServlet" class="navbar-left"><img src="images/online.png"></a>
				<a href="HomePageServlet" class="navbar-brand">在线课程 </a>
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
						</li>
				</ul>

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

	<div class="container margin-top-80 margin-bottom-80">
		<div class="row clearfix">

			<div class="col-md-3 column">
				<div class="list-group">
					<a href="#" class="list-group-item active">账号管理</a>
					<div class="list-group-item">
						<p class="list-group-item-text">用户名: ${sessionScope.user.username }</p>
					</div>
					<div class="list-group-item">
						<p class="list-group-item-text">邮箱: ${sessionScope.user.email }</p>
					</div>
									
				</div>
			</div>

			<div class="col-md-9 column">
				<div class="tabbable" id="tabs-218336">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#panel-495545" data-toggle="tab">我选的课</a>
						</li>
						<li><a href="#panel-692123" data-toggle="tab">我开的课</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-495545">
							<div class="container col-md-12 column">
								<div class="row clearfix">
									<div>
									
									
					<% 
						ArrayList<CourseBean> selectCourse = (ArrayList<CourseBean>)request.getAttribute("selectCourse");
						ArrayList<CourseBean> newCourse = (ArrayList<CourseBean>)request.getAttribute("newCourse");
								
					%>
										<c:forEach items="${selectCourse}" var="course">
										<div class="col-md-4 height-250">
											<div class="hovereffect thumbnail">
												<a href="CourseInformationServlet?coursename=${course.course }&teacherName=${course.teacher.username}"> <img class="img-responsive"
													src="/Source/upload/${course.image.url}" alt="">
													<div class="overlay">
														<h4>${course.course }</h4>
														<p>${course.description }</p>
													</div>
												</a>
											</div>
										</div>			
										
										</c:forEach>

										<!-- 添加课程 -->
										<div class="col-md-4 height-250">

											<div class="hovereffect thumbnail" id="add">
												<a href="/SearchPageServlet"> <img alt="" src="images/add.png" title="添加" /></a>

											</div>

										</div>

									</div>
								</div>
							</div>
						</div>

						<div class="tab-pane" id="panel-692123">
							<div class="container col-md-12 column">
								<div class="row clearfix">


									<!-- 循环内容，展示当前用户开设的课程 -->
									<c:forEach items="${openCourse }" var="course">
										<div class="col-md-4 height-250">
											<div class="hovereffect thumbnail">
												<a href="CourseInformationServlet?coursename=${course.course }&teacherName=${course.teacher.username}"> <img class="img-responsive"
													src="/Source/upload/${course.image.url}" alt="">
													<div class="overlay">
														<h4>${course.course }</h4>
														<p>${course.description }</p>
													</div>
												</a>
											</div>
										</div>			
										
										</c:forEach>
									
									<!-- 开设课程 -->	
									<div class="col-md-4 height-250">
										<div class="hovereffect thumbnail" id="add">
											<a href="addCourse.jsp"> <img alt="" src="images/add.png" title="添加" /></a>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
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

</body>
</html>