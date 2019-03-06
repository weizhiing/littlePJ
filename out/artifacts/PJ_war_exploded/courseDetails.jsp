<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*,Bean.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<title>课程详情</title>

</head>

<body>
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
						</li>
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

	<div class="container margin-top-80">
		<div class="row clearfix">
			<div class="col-md-12 column">

				<!-- 二级导航栏，包含与课程的相关操作 -->
				<ul class="nav nav-tabs">
					<li class="active"><a href="courseDetails.jsp">课程详情</a></li>
					<li><a href="ChapterPageServlet">知识点</a></li>
					<li><a href="SourcePageServlet">资料</a></li>
					<li><a href="HomeworkPageServlet">作业</a></li>
					<li><a href="DiscussPageServlet">讨论</a></li>

					<c:if test="${sessionScope.role == \"teacher\"}">
						<li><a href="StudentPageServlet">统计</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>

	<div class="container margin-top-50">
		<div class="row clearfix">
		
			<div class="col-md-4 column">

				<div class="thumbnail">
					<img alt="300x200" src="/Source/upload/${sessionScope.course.image.url }" />

				</div>
			</div>
			<div class="thumbnail col-md-8 column">
				<div class="caption">
					<h3>${sessionScope.course.course}</h3>
					<p>${sessionScope.course.description }</p>
					<p>
						<!-- 选了这门课的学生可以进行退课或者查看章节 -->
						<c:if test="${sessionScope.role == \"student\"}">
							<a class="btn btn-primary" id="modal-1" href="ChapterPageServlet">查看章节</a>
							<a class="btn btn-danger" href="CourseOutServlet">退课</a>
						</c:if>

						<!-- 开设本门课的老师可以进行修改、删除等操作 -->
						<c:if test="${sessionScope.role == \"teacher\"}">
							<a class="btn btn-primary" id="modal-1" href="ChapterPageServlet">查看章节</a>
							<a class="btn btn-primary" type="button" data-toggle="modal"
								data-target="#modifyModal">修改简介</a>
							<a class="btn btn-danger" href="CourseDeleteServlet">删除</a>
						</c:if>
												
						<!-- 未选此门课的学生只能进行选课操作 -->
						<c:if test="${sessionScope.role == \"neither\"}">
							<a class="btn btn-primary" href="CourseSelectServlet">添加</a>
						</c:if>

					</p>
				</div>
			</div>
		</div>
	</div>

	<!-- 修改课程信息的弹窗 -->
	<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改课程信息</h4>
				</div>

				<div class="modal-body">

					<form role="form" action="CourseModifyServlet"
						onsubmit="return modifyCourseDesc()" method="post">
						<div class="form-group">
							<label for="coursename">课程名称: </label><input class="form-control"
								id="coursename" type="text" name="coursename"
								value="${sessionScope.course.course }" readonly /> <label
								for="recourseDesc">课程简介: </label>
							<textarea class="form-control" placeholder="请输入课程简介"
								id="recourseDesc" name="recourseDesc" rows="2"></textarea>
						</div>

						<div class="alert alert-warning alert-dismissable"
							style="display: none;" id="alertM">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">×</button>

							<strong>请输入有效的课程简介！</strong>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-primary"
								onClick="modifyCourseDesc()">修改</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>



	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
			
		function modifyCourseDesc() {
			var desc = document.getElementById("recourseDesc").value;
			if (desc !== "" && (desc.trim()) !== "") {
				//window.location.href="courseDetails.jsp?courseID=1";
				return true;

			} else {
				document.getElementById("alertM").style.display = "block";
				return false;
			}
		}

		function trim(str) {

			return str.replace(/(^\s*)|(\s*$)/g, "");

		}
	</script>
</body>
</html>