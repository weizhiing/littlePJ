<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Bean.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">


<title>搜索
</title>
</head>
<body>


	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">

			<div class="navbar-header">
				<a href="/HomePageServlet" class="navbar-left"><img src="images/online.png"></a>
				<a href="/HomePageServlet" class="navbar-brand">在线课程 </a>
			</div>

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="/HomePageServlet">首页</a></li>
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

	<!-- 搜索框，三个：课程名，课程简介，开课老师 -->
	<div class="container margin-top-80">
		<div class="row clearfix">
		<form action="SearchPageServlet" class="col-md-offset-6" onsubmit="" method="post">
					<div id="searchBox">
						<input type="text" id="courseNameSearch" name="courseNameSearch"
							placeholder="请输入课程名"> <input type="text"
							id="courseDescriptionSearch" name="courseDescriptionSearch"
							placeholder="请输入课程简介"> <input type="text"
							id="teacherNameSearch" name="teacherNameSearch"
							placeholder="请输入开课老师名">
						<button type="submit" class="btn btn-primary" value="搜索">搜索</button>
					</div>
				</form>
				
				<form action="#" class="col-md-offset-1">
					排序方式:
					<button type="button" onclick="changeSort(this)" class="btn" name="sort" value="hot" id="buHot">热度</button>
					<button type="button" onclick="changeSort(this)"  class="btn" name="sort" value="new" id="byNew">时间</button>
					<!-- 接收搜索error -->
					<c:out value="${requestScope.error }"></c:out>
					<input type="text" name="sortWay" id="sortWay" value="new" hidden>
				</form>
			<div class="col-md-12 column" id="showSearchResult">
				
				
				<div class="col-md-12 margin-top-50 margin-bottom-80">
		

				<%
				
					ArrayList<CourseBean> hotCourse = (ArrayList<CourseBean>)request.getAttribute("hotCourse");
					ArrayList<CourseBean> newCourse = (ArrayList<CourseBean>)request.getAttribute("newCourse");
					ArrayList<CourseBean> allCourse = (ArrayList<CourseBean>)newCourse.clone();
					
					
					session.setAttribute("hotCourse", hotCourse);
					session.setAttribute("newCourse", newCourse);
				
				
					int currentPage;
					if (request.getParameter("page") == null) {
						currentPage = 1;
					} else {
						currentPage = Integer.parseInt(request.getParameter("page"));
					}

					//ArrayList<CourseBean> courseGet = (ArrayList<CourseBean>) request.getAttribute("hotCourse");

					int pageSize = 8;
					int totalPage = 0;
					//int totalPage = (courseGet.size() % pageSize == 0) ? (courseGet.size() / pageSize)
					//: (courseGet.size() / pageSize + 1);

					int mark = (currentPage - 1) * pageSize;
					int end = mark + pageSize - 1;
					int firstPage = 1;
					//int lastPage = totalPage;
					int prePage = (currentPage > 1) ? currentPage - 1 : 1;
					//int nextPage = (totalPage - currentPage > 0) ? currentPage + 1 : totalPage;
				%>
				
		
				

				

				
				
				<%
					//ArrayList<CourseBean> courseGet = (ArrayList<CourseBean>) request.getAttribute("hotCourse");
					//ArrayList<CourseBean> courseGet2 = (ArrayList<CourseBean>) request.getAttribute("newCourse");				
					
					session.setAttribute("courseList", allCourse);
					
					totalPage = (allCourse.size() % pageSize == 0) ? (allCourse.size() / pageSize)
						: (allCourse.size() / pageSize + 1);
					int lastPage = totalPage;
					int nextPage = (totalPage - currentPage > 0) ? currentPage + 1 : totalPage;
				%>
					<c:forEach items="<%=allCourse %>" var="course" begin="<%=mark %>" end="<%=end %>">
					<div class="col-md-3 height-250">
						<div class="hovereffect thumbnail">
							<img class="img-responsive" src="/Source/upload/${course.image.url}"
								alt="">
								<div class="overlay">
									<h4>${course.course }</h4>
									<p>${course.description }</p>
										<div class="btn-group">
									<a class="btn btn-primary" href="CourseInformationServlet?coursename=${course.course }&teacherName=${course.teacher.username}" id="viewDeatils">查看</a>
									</div>
								</div>
							
						</div>
					</div>
				</c:forEach>
							
				
			
				</div>
			</div>
		</div>
	</div>

	<div class="margin-bottom-80" id="pagerBefore">
		<ul class="pager">
			<li><a href="javascript:void(0);" onclick="turnPage(1)">首页</a></li>
			<li><a href="javascript:void(0);" onclick="turnPage(<%=currentPage%>-1)">上一页</a></li>
			<li><a href="javascript:void(0);" onclick="turnPage(<%=currentPage%>+1)">下一页</a></li>
			<li><a href="javascript:void(0);" onclick="turnPage(<%=totalPage%>)">尾页</a></li>
			<%=currentPage%> / <%=totalPage%> 页
		</ul>
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
	<script>

	function turnPage(page) {
		document.getElementById("pagerBefore").style.display="none";
        var currentPage = page;

        if (window.XMLHttpRequest)
        {
            // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
            xmlhttp=new XMLHttpRequest();
        }
        else
        {
            // IE6, IE5 浏览器执行代码
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                document.getElementById("showSearchResult").innerHTML=xmlhttp.responseText;
            }
        }
        xmlhttp.open("GET","turnPage.jsp?page="+currentPage,true);
        xmlhttp.send();
    }
	
	
	
	function changeSort(obj){
		
		document.getElementById("pagerBefore").style.display="none";
		
		document.getElementById("sortWay").value = obj.value;
		var sortWay = document.getElementById("sortWay").value;
		
		//var currentPage = page;

        if (window.XMLHttpRequest)
        {
            // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
            xmlhttp=new XMLHttpRequest();
        }
        else
        {
            // IE6, IE5 浏览器执行代码
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                document.getElementById("showSearchResult").innerHTML=xmlhttp.responseText;
            }
        }
        xmlhttp.open("GET","changeSortPage.jsp?sortWay="+sortWay,true);
        xmlhttp.send();
	}

	
	</script>
</body>
</html>