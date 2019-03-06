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
<title>作业</title>	

</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">

			<div class="navbar-header">
				<a href="HomePageServlet" class="navbar-left"><img src="images/online.png"></a>
				<a href="HomePageServlet" class="navbar-brand">在线课程 </a>
			</div>

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="HomePageServlet">首页</a></li>
					<li><a href="login.jsp">登录</a></li>
					<li><a href="register.jsp">注册</a></li>
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
	
	<div class="container margin-top-80">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<ul class="nav nav-tabs">
					<li><a href="courseDetails.jsp">课程详情</a></li>
					<li><a href="ChapterPageServlet">知识点</a></li>
					<li><a href="SourcePageServlet">资料</a></li>
					<li class="active"><a href="HomeworkPageServlet">作业</a></li>
					<li><a href="DiscussPageServlet">讨论</a></li>

					<c:if test="${sessionScope.role == \"teacher\"}">
						<li><a href="StudentPageServlet">统计</a></li>
					</c:if>
				</ul>
		</div>
	</div>
</div>
	<%
		//for test
		//List<HomeworkDoBean> notScored=(ArrayList<HomeworkDoBean>) request.getAttribute("notScoredHomework");
		//out.print(notScored.size());
	//	notScoredHomework.add(new HomeworkDoBean(new HomeworkBean("homework name"," some desc about the homework", new CourseBean("course name", "some desc about the course", new UrlBean("images/4.jpg"),new UserBean("teacher name","teacher psd","emai"))), new UserBean("student1","psd","email2"), "some content"));
		
	//	request.setAttribute("notScoredHomework", notScoredHomework);
	
	%>
<div class="container-fluid margin-top-50">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<table class="table">
					<thead>
						<tr>
							<th>课程名</th>
							<th>作业名</th>
							<th>学生</th>							
							<th>完成情况</th>
							<th>评分</th>
						</tr>
					</thead>
					<tbody>
					
					<c:forEach items="${requestScope.notScoredHomework }" var="homework">
					<tr>
							<td>${homework.homework.course.course }</td>
							<td>${homework.homework.homeworkName}</td>
							<td>${homework.student.username }</td>					
							<td><textarea class="form-control" rows="4" readonly>${homework.content}</textarea>
							</td>
							<td>
								<form action="HomeworkScorePageServlet?studentName=${homework.student.username }" onsubmit="return checkScore()" method="post">
									<input class="form-control input-sm" type="number" name="score"
										id="score" /> <br>
									<button class="btn btn-primary pull-right" type="submit"
										onClick="checkScore()">提交</button>
								</form>
							</td>
						</tr>
					
					</c:forEach>
						
					</tbody>
			</table>
		</div>
	</div>
</div>

	

	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
		function checkScore(){
			
			var score = document.getElementById("score").value;
			if(score !== "" && (score.trim()) !== "" && score >= 0){
				return true;
			} else{
				return false;
			}					
		}
		
		function trim(str){
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		
	</script>
	
</body>
</html>