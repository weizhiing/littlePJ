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
<title>知识点</title>

</head>

<body>
	<% 
	//接收添加章节、知识点等的的error信息，并弹窗显示
	if(request.getParameter("error") != null){
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
                    <h5>添加失败！<%= request.getParameter("error") %></h5>
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
						</li>
					</c:if>
				</ul>

				<form class="navbar-form navbar-right" role="search"
					action="/SearchPageServlet" method="post">
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
					<li class="active"><a href="#">知识点</a></li>
					<li><a href="SourcePageServlet">资料</a></li>
					<li><a href="HomeworkPageServlet">作业</a></li>
					<li><a href="DiscussPageServlet">讨论</a></li>

					<c:if test="${sessionScope.role == \"teacher\"}">
						<li><a href="StudentPageServlet">统计</a></li>
					</c:if>
				</ul>
			</div>
		</div>

<%	
	//ArrayList<ChapterBean> chapter = (ArrayList<ChapterBean>)request.getAttribute("chapter");
	//Map<String, ArrayList<ChapterKnowledgeBean>> chapterKnowledge = (HashMap<String, ArrayList<ChapterKnowledgeBean>>)request.getAttribute("chapterKnowledge");
%>



		<div class="row" style="margin: 10px;">
			<h3><c:out value="${sessionScope.course.course}"></c:out></h3>
			<div class="col-md-8">
				<div id="accordion">
				
				
				<!-- 展示章节 -->
				<c:forEach items="${requestScope.chapter}" var="chapter" varStatus="status">
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" class="btn btn-link"
									data-parent="#accordion" href="#${status.index}">${chapter.getUnitName()}</a>		
								
								<c:if test="${sessionScope.role == \"teacher\" }">
								<button id="addPointButton33" currentChapter="${chapter.getUnitName()}"
									class="btn btn-link addPointButton" style="float: right;"
									data-toggle="modal" data-target="#addPointModal">
									添加知识点</button>
									<button id="addPointButton33" 
									class="btn btn-danger addPointButton" style="float: right;"
									onClick="javascript:window.location.href='ChapterDeleteServlet?chapterName=${chapter.getUnitName()}'">
									删除章节</button>
								</c:if>	
							</h4>
						</div>
						<div id="${status.index}" class="panel-collapse collapse in"
							data-parent="#accordion">
							<ul class="list-group list-group-flush">			
							<c:forEach items="${requestScope.chapterKnowledge[chapter.getUnitName()]}" var="knowledge">
							
					
								<!-- 对章节知识点循环 -->

								<li class="list-group-item list-group-item-action">
								<a href="KnowledgeContentPageServlet?chapterName=${chapter.getUnitName()}&knowledgeName=${knowledge.getKnowledgeName()}">${knowledge.getKnowledgeName()}</a>
									
									<c:if test="${sessionScope.role == \"teacher\" }">
									<a id="${chapter.getUnitName()}" href="KnowledgeDeleteServlet?knowledgeName=${knowledge.getKnowledgeName()}&chapterName=${chapter.getUnitName()}"
									class="btn btn-link addPointButton" style="float: right;" >删除知识点</a>
									</c:if>
									
								</li>

						</c:forEach>

							</ul>
						</div>
					</div>
					
				</c:forEach>

				</div>
			</div>

			<div class="col-md-offset-1 col-md-2">
			
				
				<!-- 老师具有添加章节的操作 -->
				<c:if test="${sessionScope.role == \"teacher\" }">
					<a class="btn btn-primary" id="modal-1" href="#modal-container-1"
					data-toggle="modal">添加章节</a>
				</c:if>
			</div>
		</div>
	</div>



	<!-- 添加章节弹出框 -->
	<div class="modal fade" id="modal-container-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">添加章节</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="ChapterAddServlet" onsubmit="return addChapter()">
						<div class="form-group">
							<label for="chapterName">请输入章节名称: </label><input
								class="form-control" id="chapterName" type="text"
								name="chapterName" />
						</div>
						<div class="alert alert-warning alert-dismissable"
							style="display: none;" id="alertC">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">×</button>

							<strong>请输入有效的章节名称!</strong>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-primary"
								onClick="addChapter()">添加</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 添加知识点弹出框 -->
	<div class="modal fade" id="addPointModal" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">添加知识点</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="KnowledgeAddServlet" id="addPointForm" method="post" onsubmit="return addKnowledge()">
						<div class="form-group">
							<label for="knowledgeName">请输入知识点名称: </label><input
								class="form-control" id="knowledgeName" type="text"
								name="knowledgeName" />
						</div>
						<div class="alert alert-warning alert-dismissable"
							style="display: none;" id="alertK">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">×</button>

							<strong>请输入有效的知识点名称!</strong>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-primary"
								onClick="addKnowledge()">添加</button>
						</div>
					</form>
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
		function addChapter() {
			//var name = "2";
			var chaptername = document.getElementById("chapterName").value;
			if (chaptername !== "" && (chaptername.trim()) !== "") {
				document.getElementById("alertC").style.display = "none";
				return true;
				

			} else {
				document.getElementById("alertC").style.display = "block";
				return false
			}

		}
		
	

		function trim(str) {

			return str.replace(/(^\s*)|(\s*$)/g, "");

		}
	</script>
	
	<script>
	
	//自动弹出error提示框
        $(function () {
            $('#myModal').modal({
            keyboard: true
        })});
	
        var currentChapterName;
        $(".addPointButton").click(function () {

            currentChapterName = $(this).attr("currentChapter");
            $("#currentChapter").val(currentChapterName);
        })
        
        	function addKnowledge(){
       
			var pointname = document.getElementById("knowledgeName").value;
			if (pointname !== "" && (pointname.trim()) !== "") {
				document.getElementById("alertK").style.display = "none";
				
				document.getElementById("addPointForm").action="KnowledgeAddServlet?chapterName=" + currentChapterName;
				return true;
				

			} else {
				document.getElementById("alertK").style.display = "block";
				return false
			}
		}
 
    </script>
</body>
</html>