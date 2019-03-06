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
	<%
		ArrayList<HomeworkBean> homeworkNoDo = (ArrayList<HomeworkBean>)request.getAttribute("homeworkNoDo");
		ArrayList<HomeworkDoBean> homeworkDo = (ArrayList<HomeworkDoBean>)request.getAttribute("homeworkDoBean");
		ArrayList<HomeworkCheckBean> homeworkCheck = (ArrayList<HomeworkCheckBean>)request.getAttribute("homeworkCheckBean");
		
	%>
	

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
			<div class="col-md-12 column">
				<ul class="nav nav-tabs">
					<li><a href="courseDetails.jsp">课程详情</a></li>
					<li><a href="ChapterPageServlet">知识点</a></li>
					<li><a href="SourcePageServlet">资料</a></li>
					<li class="active"><a href="#">作业</a></li>
					<li><a href="DiscussPageServlet">讨论</a></li>
					<c:if test="${sessionScope.role == \"teacher\"}">
						<li><a href="StudentPageServlet">统计</a></li>
					</c:if>
				</ul>
			</div>

			<div class="col-md-12 column margin-top-50">
				<div class="row">
					<c:forEach items="${requestScope.homeworkCheckBean }" var="homework">
						<div class="col-md-4">
							<div class="thumbnail">
								<div class="caption">

									<h3>作业名称:${homework.homeworkComplete.homework.homeworkName }</h3>
									<p>${homework.homeworkComplete.homework.homeworkDescription }</p>

									<!-- 学生：作业提交并已评分 -->
									<c:if test="${sessionScope.role == \"student\" }">
										<textarea class="form-control" rows="6" name="doWork"
											id="doWork" 
											readonly>${homework.homeworkComplete.content}</textarea>
										<div class="btn-group" style="margin: 1em auto;">
											<button class="btn btn-info" type="submit"
												onClick="checkDoWork()" disabled>已提交</button>
											<button class="btn btn-danger" type="button">
												我的得分:<strong> ${homework.score }</strong>
											</button>
										</div>
									</c:if>

									<!-- 教师界面 -->
									<c:if test="${sessionScope.role == \"teacher\" }">
										<div class="btn-group">
											<button class="btn btn-info modifyHomework" type="button"
												data-toggle="modal" data-target="#modifyModal" getModifyWorkName="${homework.homeworkComplete.homework.homeworkName }">修改作业</button>
											<button class="btn btn-danger deleteHomework" type="button" getWorkName="${homework.homeworkComplete.homework.homeworkName }"
												data-toggle="modal" data-target="#deleteModal">删除作业</button>
											<button class="btn btn-info" type="button"
												onClick="javascript:window.location.href='HomeworkScorePageServlet?homeworkName=${homework.homeworkComplete.homework.homeworkName}'">检查作业</button>
										</div>

									</c:if>


								</div>
							</div>
						</div>
					</c:forEach>

					<c:forEach items="${requestScope.homeworkDoBean }" var="homework">
						<div class="col-md-4">
							<div class="thumbnail">
								<div class="caption">
									<form action="HomeworkDoServlet?workName=${homework.homework.homeworkName }"
										onsubmit="return checkDoWork()">
										<h3>作业名称: ${homework.homework.homeworkName }</h3>
										<p>${homework.homework.homeworkDescription}</p>

										<!-- 学生：作业提交未评分 -->
										<c:if test="${sessionScope.role == \"student\" }">
											<textarea class="form-control" rows="6" name="doWork"
												id="doWork" readonly>${homework.content}</textarea>
											<div class="btn-group" style="margin: 1em auto;">
												<button class="btn btn-info" type="submit"
													onClick="checkDoWork()" disabled>已提交</button>

											</div>
										</c:if>

										<!-- 老师界面 -->
										<c:if test="${sessionScope.role == \"teacher\" }">
											<div class="btn-group">
												<button class="btn btn-info modifyHomework" type="button"
												data-toggle="modal" data-target="#modifyModal" getModifyWorkName="${homework.homework.homeworkName }">修改作业</button>
											<button class="btn btn-danger deleteHomework" type="button" getWorkName="${homework.homework.homeworkName }"
												data-toggle="modal" data-target="#deleteModal">删除作业</button>
												<button class="btn btn-info" type="button"
													onClick="javascript:window.location.href='HomeworkScorePageServlet?homeworkName=${homework.homework.homeworkName}'">检查作业</button>
											</div>

										</c:if>
									</form>
								</div>
							</div>
						</div>
					</c:forEach>


					<c:forEach items="${requestScope.homeworkNoDo }" var="homework">

						<div class="col-md-4">
							<div class="thumbnail">
								<div class="caption">

									<!-- 作业未提交 -->
									<c:if test="${sessionScope.role == \"student\" }">
										<form
											action="HomeworkDoServlet?homeworkName=${homework.homeworkName}"
											onsubmit="return checkDoWork()" method="post">
											<h3>作业名称: ${homework.homeworkName }</h3>
											<p>${homework.homeworkDescription}</p>
											<textarea class="form-control" rows="6" name="NodoWork"
												id="NodoWork" placeholder="请输入你的想法"></textarea>
											<div class="btn-group" style="margin: 1em auto;">
												<button class="btn btn-info" type="submit" onClick="checkDoWork()">提交作业</button>

											</div>
										</form>

									</c:if>

									<!-- 老师界面 -->
									<c:if test="${sessionScope.role == \"teacher\" }">
										<h3>作业名称: ${homework.homeworkName }</h3>
										<p>${homework.homeworkDescription}</p>
										<div class="btn-group">
											<button class="btn btn-info modifyHomework" type="button"
												data-toggle="modal" data-target="#modifyModal" getModifyWorkName="${homework.homeworkName }">修改作业</button>
											<button class="btn btn-danger deleteHomework" type="button" getWorkName="${homework.homeworkName }"
												data-toggle="modal" data-target="#deleteModal">删除作业</button>
											<button class="btn btn-info" type="button"
												onClick="javascript:window.location.href='HomeworkScorePageServlet?homeworkName=${homework.homeworkName}'">检查作业</button>
										</div>

									</c:if>
								</div>
							</div>
						</div>
					</c:forEach>

					<c:if test="${sessionScope.role == \"teacher\" }">
						<div class="col-md-4">
							<div class="thumbnail">

								<div class="caption text-center">
									<a href="javascript:void(0);" data-toggle="modal"
										data-target="#myModal"><img style="margin: 2em auto;"
										src="images/add2.png"></a>

								</div>
							</div>
						</div>
					</c:if>



					<!-- 添加作业的弹窗 -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">添加作业</h4>
								</div>

								<div class="modal-body">

									<form role="form" action="/HomeworkAddServlet"
										onsubmit="return addWork()" method="post">
										<div class="form-group">
											<label for="workname">作业名称: </label><input
												class="form-control" id="workname" type="text"
												placeholder="请输入作业名称" name="homeworkName" /> <label
												for="workcontent">作业内容: </label>
											<textarea class="form-control" placeholder="请输入作业具体内容"
												id="workcontent" name="homeworkDescription" rows="2"></textarea>
										</div>

										<div class="alert alert-warning alert-dismissable"
											style="display: none;" id="alertW">
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">×</button>

											<strong>请输入有效的作业名称和作业内容！</strong>
										</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">关闭</button>
											<button type="submit" class="btn btn-primary"
												onClick="addWork()">添加</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>


					<!-- 修改作业的弹窗 -->
					<div class="modal fade" id="modifyModal" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">修改作业</h4>
								</div>

								<div class="modal-body">

									<form role="form" action="HomeworkModifyServlet"
										onsubmit="return modifyWork()" method="post" id="modifyHomeworkForm">
										<div class="form-group">
											<label for="modifyWorkName">作业名称: </label><input
												class="form-control" id="modifyWorkName" type="text"
												name="homeworkName" readonly /> <label
												for="reworkcontent">作业内容: </label>
											<textarea class="form-control" placeholder="请输入作业具体内容"
												id="reworkcontent" name="homeworkNewDescription" rows="2"></textarea>
										</div>

										<div class="alert alert-warning alert-dismissable"
											style="display: none;" id="alertM">
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">×</button>

											<strong>请输入有效的作业内容！</strong>
										</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">关闭</button>
											<button type="submit" class="btn btn-primary"
												onClick="modifyWork()">修改</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>


					<!-- 删除作业的弹窗 -->
					<div class="modal fade" id="deleteModal" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">提示</h4>
								</div>

								<div class="modal-body">

									<h4>
										<strong>确认删除该作业吗？</strong>
									</h4>

								</div>
								<div class="modal-footer">
									<form action="HomeworkDeleteServlet" method="post" id="deleteHomeworkForm">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">关闭</button>
										<button type="submit" class="btn btn-danger" onClick="deleteWorkConfirm()">确认</button>
									</form>

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

	<script type="text/javascript">
		function addWork(){
			
			var workname = document.getElementById("workname").value;
			var workcontent = document.getElementById("workcontent").value;
			if(workname !== "" && (workname.trim()) !== "" && workcontent !== "" && (workcontent.trim()) !== "") {
				//window.location.href="courseDetails.jsp?courseID=1";
				return true;
				
			} else {
				document.getElementById("alertW").style.display = "block";
				return false;
			}
			
		}
		
		 var currentModifyWorkName;
	        $(".modifyHomework").click(function () {
	        	
	        	
	        	
	            currentModifyWorkName = $(this).attr("getModifyWorkName");
	            $("#getModifyWorkName").val(currentModifyWorkName);
	            
	            $("#modifyWorkName").attr("value", currentModifyWorkName);
	        	
	        })
		
		function modifyWork(){
			var workcontent = document.getElementById("reworkcontent").value;
			
			//document.getElementById("modifyWorkName").value = currentModifyWorkName;
			if(workcontent !== "" && (workcontent.trim()) !== "") {
				//window.location.href="courseDetails.jsp?courseID=1";
				return true;
				
			} else {
				document.getElementById("alertM").style.display = "block";
				return false;		
			
			}
		}
		
		function checkDoWork(){
			var doContent = document.getElementById("NodoWork").value;
			if(doContent !== "" && (doContent.trim()) !== "") {
				//window.location.href="courseDetails.jsp?courseID=1";
				//alert("yy");
				return true;
				
			} else {
				//alert("zz");
				return false;
			}
		}
		
		function trim(str){
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		
		
		//获取要删除的作业名
		var currentWorkName;
        $(".deleteHomework").click(function () {

            currentWorkName = $(this).attr("getWorkName");
            $("#getWorkName").val(currentWorkName);
        })
        
        function deleteWorkConfirm(){
        	document.getElementById("deleteHomeworkForm").action = "HomeworkDeleteServlet?homeworkName=" + currentWorkName;
        }
        
       
        
	</script>
</body>
</html>