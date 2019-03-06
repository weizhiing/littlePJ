<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*,Bean.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<title>课程资源
</title>

</head>

<body>


	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">

			<div class="navbar-header">
				<a href="HomePageServlet" class="navbar-left"><img src="images/online.png"></a>
				<a href="/HomePageServlet" class="navbar-brand">在线课程 </a>
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
		<div class="col-md-12 column">
			<ul class="nav nav-tabs">
					<li><a href="courseDetails.jsp">课程详情</a></li>
					<li><a href="ChapterPageServlet">知识点</a></li>
					<li class="active"><a href="#">资料</a></li>
					<li><a href="HomeworkPageServlet">作业</a></li>
					<li><a href="DiscussPageServlet">讨论</a></li>

					<c:if test="${sessionScope.role == \"teacher\"}">
						<li><a href="StudentPageServlet">统计</a></li>
					</c:if>
				</ul>
		</div>
		
	
<div class="col-md-12 margin-top-50">

	<c:if test="${sessionScope.role == \"teacher\" }">
	
		<!-- 教师添加资源的表单 -->	
		<form class="form-group thumbnail alert-info" action="SourceAddServlet" method="post" enctype="multipart/form-data">
			<input type="file" class="form-control" style="width: 300px; height: 40px; display: inline;" name="courseData" id="courseData" accept="">
			<textarea class="form-control" rows="2" placeholder="请输入资源名称" id="sourceName" name="sourceName"></textarea>
			<textarea class="form-control" rows="2" placeholder="请输入资源简介" id="sourceDescription" name="sourceDescription"></textarea>

			<button class="btn btn-primary" style="display: inline;" type="submit">添加</button>
		
		</form>
	</c:if>		
				<table class="table table-hover">
					<thead>
						<tr>
							<th>文件名</th>
							<th>介绍</th>
							<th>上传者</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<%
						ArrayList<SourceBean> sourceList = (ArrayList<SourceBean>)request.getAttribute("sourceList");
					%>		
					
					<!-- 循环内容: 资源列表 -->		
					<c:forEach items="${requestScope.sourceList }" var="source">
						<tr>
							<td><a href="SourceDownLoadServlet?fileUrl=${source.url}">${source.sourceName }</a></td>
							<td>${source.sourceDescription }</td>
								<td>${source.course.teacher.username }</td>
							<td>
								<div class="btn-group" style="margin: -5px;">
								
									<a class="btn btn-warning" href="SourceDownLoadServlet?fileUrl=${source.url}">下载</a>
									<c:if test="${sessionScope.role == \"teacher\" }">
										<a class="btn btn-danger" href="SourceDeleteServlet?sourceName=${source.sourceName }">删除</a>
									</c:if>
								</div>
							</td>
						</tr>
					
					</c:forEach>
						
					</tbody>
				</table>
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