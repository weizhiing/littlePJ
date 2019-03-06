<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*,Bean.*,PPTtoPDF.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

				<form class="navbar-form navbar-right" role="search"
					action="SearchPageServlet" method="post">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<i class="glyphicon glyphicon-search"></i>
							</div>
							<input type="text" class="form-control" id="searchCourseName"
								placeholder="请输入课程关键词" name="searchCourseName" />
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
			<%
				//ArrayList<ChapterKnowledgeContentBean> knowledgeContentList = (ArrayList<ChapterKnowledgeContentBean>)request.getAttribute("chapterKnowledgeContent");
			%>
	
			
			<div class="col-md-4">
			
				<div class="list-group" style="margin-top: 10px;">
					
					<c:if test="${sessionScope.chapterName != null }">
						<a href="#" class="list-group-item active">${sessionScope.chapterName }</a> 
					</c:if>
					
					<c:if test="${sessionScope.knowledgeName != null}">
						<a href="" 
						class="list-group-item">${sessionScope.knowledgeName}</a>
					</c:if>
				</div>		

			</div>	
			
			<div class="col-md-8" id="outer" style="margin-top: 10px;">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="ChapterPageServlet">${sessionScope.chapterName }</a></li>
						<li class="breadcrumb-item active"><a href="#">${sessionScope.knowledgeName }</a></li>
					</ol>
				</nav>


				<!-- 老师能对该知识点进行资源添加 -->
				<c:if test="${sessionScope.role == \"teacher\" }">
				<form method="post" id="addForm" action="ContentAddServlet" enctype="multipart/form-data">
					<div class="form-group">
					
					<input name="pointId" value="21" hidden> <input onchange="filePreview(this)" 
							id="uploadFile" accept="application/pdf, application/vnd.openxmlformats-officedocument.presentationml.presentation, video/*" type="file" name="video" style="width: 250px;display: none"/>
						<button id="chooseFile" type="button" class="btn btn-default">选择文件（视频或PPT）</button>
						
						<button id="upload" type="submit" class="btn btn-primary">上传</button>
						
					</div>
				</form>
				
				
				<div id="previewBox">
					<video controls class="col-md-12" id="filePreview" style="display: none"></video>
					
				</div>
				</c:if>
				
				
				<!-- 学生只能查看该知识点下的资源 -->

					<c:forEach items="${requestScope.chapterKnowledgeContent}" var="knowledge">
						<c:if test="${fn:containsIgnoreCase(knowledge.url.getUrl(), \"mp4\")}">
						<%
						//System.out.println("mp4"); 
						%>
							<video controls class="col-md-12" src="/Source/upload/${knowledge.url.getUrl()}"></video>
						
						</c:if>
						
						<c:if test="${fn:containsIgnoreCase(knowledge.url.getUrl(), \"pptx\")}">
							<c:set var="sourceUrl" value="Source/upload/${knowledge.url.getUrl()}" scope="page"></c:set>
							<c:set var="sourceName" value="${knowledge.knowledge.knowledgeName}" scope="page"></c:set>
												
							<%

								String dir = request.getServletContext().getRealPath("/") ;
								String path = dir +(String)pageContext.getAttribute("sourceUrl") ;
							//	String path = (String)pageContext.getAttribute("sourceUrl");
								String filename = (String)pageContext.getAttribute("sourceName");
								String temp = dir+"imgofPPT/";
								System.out.println(path);
								if(path != null){
								//ConverPPTFileToImageUtil.converPPTXtoImage(path, temp, "jpeg");
							
								ArrayList<String> paths = (ArrayList<String>)PPTtoImage.converPPTXtoImage(path, temp, "jpeg").get("imgNames");
							
								ImagetoPDF.imageToPdf(dir+"pdf/"+filename+".pdf", temp);
							
								System.out.println(dir+"pdf/"+filename+".pdf");
										
							
								for(String imagePath : paths) {
							
									  System.out.println(temp+imagePath);
									}
							
								DeleteImgTemp.deleteDir(temp);
							
								}
							
								if((String)pageContext.getAttribute("sourceUrl") != null){
							
							%>
							<iframe src="pdf\<%=filename %>.pdf" width="750" height="600"></iframe>
							<%
								}
							
							%>

						</c:if>
						
						<c:if test="${fn:containsIgnoreCase(knowledge.url.getUrl(), \"pdf\")}">
						
						<%
						System.out.println("pdf"); 
						%>
						
							<embed class="col-md-12" height="500px" src="/Source/upload/${knowledge.url.getUrl()}"></embed>
						
						</c:if>
				
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
	
		
		function trim(str){

			return str.replace(/(^\s*)|(\s*$)/g, "");

		}
		
		function filePreview(obj){
	
			var path = document.getElementById("uploadFile").value;			
			
			var file = obj.files[0];
			var reg = /\w+(.flv|.rvmb|.mp4|.avi|.wmv)$/;
			
			if(reg.test(file.type)){
				
				document.getElementById("uploadFile").style.display="block";
				document.getElementById("filePreview").style.display = "block";
				

		        console.log(obj);console.log(file);
		        console.log("file.size = " + file.size);
		        //file.size 单位为byte

		        var reader = new FileReader();
		        //读取文件过程方法
		        reader.onloadstart = function (e) {
		            console.log("开始读取....");
		        }
		        reader.onprogress = function (e) {
		            console.log("正在读取中....");
		        }
		        reader.onabort = function (e) {
		            console.log("中断读取....");
		        }
		        reader.onerror = function (e) {
		            console.log("读取异常....");
		        }
		        reader.onload = function (e) {
		            console.log("成功读取....");

		            var file = document.getElementById("filePreview");
		            file.src = e.target.result;

		        }
		        reader.readAsDataURL(file)
			} else{
				document.getElementById("filePreview").style.display = "none";
				document.getElementById("uploadFile").style.display="block";
			}
			
	    }
		

		
	    $("#chooseFile").click(function () {
	        $("#uploadFile").click();
	    });
	
	</script>
</body>
</html>