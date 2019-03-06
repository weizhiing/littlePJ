<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ page import="Bean.CourseBean,Bean.UrlBean,Bean.UserBean"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">


<title>在线课程
</title>
</head>
<body onload="getCourseList()">

	<%
		//User user1 = new User(1, "admin1", "1234", "email");
		//session.setAttribute("user", user1);
	%>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">

			<div class="navbar-header">
				<a href="#" class="navbar-left"><img src="images/online.png"></a>
				<a href="#" class="navbar-brand">在线课程 </a>
			</div>

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">首页</a></li>
					<c:if test="${sessionScope.user == null }">
						<li><a href="login.jsp">登录</a></li>
						<li><a href="register.jsp">注册</a></li>
					</c:if>

					<c:if test="${sessionScope.user != null }">
					
						<li class="dropdown"><a data-toggle="dropdown"
							class="dropdown-toggle" href="#">${sessionScope.user.username}<strong
								class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="UserPageServlet">个人信息</a></li>
								<li class="divider"></li>
								<li><a href="LogoutServlet">退出登录</a></li>
							</ul>
					</c:if>

				</ul>

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

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-md-12">
				<h1 style="text-align: center;"><span class="label label-primary">HOT Course</span></h1>
				<div class="carousel slide" id="mycarousel" data-ride="carousel">
					<ol class="carousel-indicators">
						<li class="active" data-slide-to="0" data-target="#mycarousel"></li>
						<li data-slide-to="1" data-target="#mycarousel"></li>
						<li data-slide-to="2" data-target="#mycarousel"></li>
                        <li data-slide-to="3" data-target="#mycarousel"></li>
                        <li data-slide-to="4" data-target="#mycarousel"></li>
                        <li data-slide-to="5" data-target="#mycarousel"></li>

					</ol>
					<div class="carousel-inner">
						<%
						ArrayList<CourseBean> hotCourse = (ArrayList<CourseBean>)request.getAttribute("hotCourse");
						ArrayList<CourseBean> newCourse = (ArrayList<CourseBean>)request.getAttribute("newCourse");
						
					%>

						<!-- 最热的课程展示 -->
						<c:forEach items="${hotCourse}" var="course"
							varStatus="status" begin="0" end="5">
							<c:if test="${status.first }">
								<div class="item active"
									style="background: url(${course.image.url}); background-size: cover;">
							</c:if>
							<c:if test="${status.first == false }">
								<div class="item"
									style="background: url(${course.image.url}); background-size: cover;">
							</c:if>

							<a
								href="CourseInformationServlet?coursename=${course.course}&teacherName=${course.teacher.username}">
								<img alt="" src="/Source/upload/${course.image.url}"  />
							</a>
							<div class="carousel-caption">

								<h4>${course.course}</h4>
								<p>${course.description}</p>

							</div>
					</div>
					</c:forEach>

				</div>
				<a data-slide="prev" href="#mycarousel"
					class="left carousel-control"><span aria-hidden="true"
					class="glyphicon glyphicon-chevron-left"></span></a> <a
					data-slide="next" href="#mycarousel" class="right carousel-control"><span
					aria-hidden="true" class="glyphicon glyphicon-chevron-right"></span></a>
			</div>
		</div>
	</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-md-12">
         <h2 style="font-family: Serif;text-align: center;"><span class="label label-success">NEW COURSE</span></h2>
				<!-- 最新的课程展示 -->
				<c:forEach items="${newCourse }" var="course" begin="0" end="5">
					<div class="col-md-4">
						<div class="thumbnail">
							<img alt="300x200" src="/Source/upload/${course.image.url }" />
							<div class="caption">
								<h3>${course.course }</h3>
								<p>${course.description }</p>
								<p>
									<a class="btn btn-primary"
										href="CourseInformationServlet?coursename=${course.course }&teacherName=${course.teacher.username}">查看</a>
								</p>
							</div>
						</div>
					</div>

				</c:forEach>

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
	<script type="text/javascript">  
			function getCourseList(){
				$.ajax({  
            		url:"HomePageServlet",//servlet文件的名称
            		type:"GET",
            		success:function(e){
            		}
            	});
				
				
			}
			
			
     </script>

</body>
</html>