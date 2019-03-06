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
<title>讨论板</title>

</head>

<body>
	<% 
	//接收新建讨论话题的error信息（title已存在），并弹窗显示
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
                    <h5>新建话题失败！<%= request.getParameter("error") %></h5>
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
					<li><a href="HomeworkPageServlet">作业</a></li>
					<li class="active"><a href="#">讨论</a></li>

					<c:if test="${sessionScope.role == \"teacher\"}">
						<li><a href="StudentPageServlet">统计</a></li>
					</c:if>
				</ul>
		</div>
	</div>
</div>

<div class="container margin-bottom-80">
	<div class="row clearfix">
		<div class="col-md-12 column">
				<div class="panel-group" id="panel-add">
					<div class="panel panel-default">

						<!-- 新建话题 -->
						<form action="DiscussOpenServlet" onsubmit="" method="post">
							<div class="panel-heading">
								<input id="title" name="title" class="panel-title form-control"
									placeholder="新建话题" data-toggle="collapse"
									data-parent="#panel-add" href="#panel-element-input" required>
							</div>
							<div id="panel-element-input" class="panel-collapse collapse">
								<div class="panel-body">
									<textarea id="content" name="content" class="form-control"
										rows="6" required placeholder="请在此输入讨论内容"></textarea>
									<button class="btn btn-primary pull-right" type="submit"
										onClick="">发布</button>
								</div>
							</div>
						</form>
					</div>

				</div>
				
			<%	
				HashMap<DiscussBean, ArrayList<DiscussReplyBean>> discussBoard = (HashMap<DiscussBean, ArrayList<DiscussReplyBean>>)request.getAttribute("discussBoard");
								
			%>
			
			
				<!-- 循环内容：讨论板 -->
				<c:forEach items="${requestScope.discussBoard }" var="discuss">
					<div class="panel panel-default">
					
					<!-- 主话题 -->
					<div class="panel-heading">					
					<h3>发布人: ${discuss.key.getStudent().username}</h3>
						<h4>主题: ${discuss.key.title }</h4>
						<p>内容: <br>${discuss.key.content}</p>
							<div style="text-align: right;">
							<c:if test="${discuss.key.getBeAbleToDelete() }">
							<a class="btn btn-default" href="DiscussDeleteServlet?title=${discuss.key.title}" onclick="showReply()"><img src="images/deleteS.png" alt="删除" title="删除"></a>
							</c:if>
							<button class="btn btn-default"><img src="images/like.png" title="点赞"></button>
							</div>							
							
					</div>
					<c:forEach items="${discuss.value}" var="reply" >
					
						<!-- 回复内容 -->
						<div class="panel-body">
							<h4>回复人: ${reply.getStudent().username }</h4>
							<p>回复内容: ${reply.content}</p>
							<div class="" style="text-align: right;">
							
							<c:if test="${reply.getBeAbleToDelete()}">
								<a class="btn btn-default" href="DiscussReplyDeleteServlet?studentName=${discuss.key.getStudent().username}&title=${discuss.key.title}&content=${reply.content}"><img src="images/deleteS.png" alt="删除"></a>
								</c:if>
								
								<button class="btn btn-default"><img src="images/like.png" title="点赞"></button>
							</div>
						</div>
						<hr>
						
					</c:forEach>
					
						<!-- 回复框 -->
						<div class="panel-footer">
						<form id="replyForm" action="DiscussReplyServlet?title=${discuss.key.title}&studentName=${discuss.key.getStudent().username}" onsubmit="return checkReply()" method="post">
							<textarea class="form-control" name="replyContent" id="replyContent" placeholder="请输入回复"></textarea>
							<div style="text-align: right;">
							<button type="submit" class="btn btn-default" ><img src="images/reply.png" alt="回复"></button>
							
							</div>
						</form>
				
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
	<script>
		function checkReply(){
			var content = document.getElementById("doWork").value;
			if(content !== "" && (content.trim()) !== "") {
				//window.location.href="courseDetails.jsp?courseID=1";
				return true;
				
			} else {
			
				return false;
			}
		}
	
		function trim(str){
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}

	
	//自动弹出error提示框
        $(function () {
            $('#myModal').modal({
            keyboard: true
        })});
    </script>
</body>
</html>

