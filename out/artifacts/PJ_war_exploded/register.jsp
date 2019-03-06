<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="com.sun.net.ssl.internal.ssl.Provider"%>
<%@ page import="javax.mail.*"%>
<%@ page import="javax.mail.internet.AddressException"%>
<%@ page import="javax.mail.internet.InternetAddress"%>
<%@ page import="javax.mail.internet.MimeMessage"%>
<%@ page import="java.security.Security"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Properties"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">


<title>注册 </title>
</head>
<body>
	<%
		//已登录的用户访问此页面会跳转到首页
		if (session.getAttribute("user") != null) {
			response.sendRedirect("HomePageServlet");
		}
	
	
	//接收注册的error信息（用户名已被使用），并弹窗显示
	if(request.getAttribute("error") != null){
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
                    <h5>注册失败！<%= request.getAttribute("error") %></h5>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    
                </div>
            </div>
        </div>
    </div>
 
	<% 	} %>

	<%
		//简易的邮箱验证
		if ("after".equals(request.getParameter("getCode"))) {
			
			//随机生成邮箱验证码
			String code = "" + (int)((Math.random() * 9+1) * 100000);
			System.out.println(code);
			%>
			<input type="text" name="rightCode" id="rightCode" value="<%= code%>" style="display: none;">
			<% 
 			MimeMessage message;
			Security.addProvider(new Provider());
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			//QQ邮箱服务器
			String smtpHost = "smtp.qq.com";
			//邮箱用户名（QQ账号）
			final String username = "1376237699";
			//邮箱授权码
			final String password = "jhtsnpzalmzigjfe";
			//要发送到的邮箱
			String to = request.getParameter("email");
			//自己的邮箱
			String from = "1376237699@qq.com";
			Transport transport;

			Properties props = new Properties();
			props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.port", "465");
			props.setProperty("mail.smtp.socketFactory.port", "465");
			props.setProperty("mail.smtp.auth", "true");
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.username", username);
			props.put("mail.smtp.password", password);
			Session session2 = Session.getInstance(props, new Authenticator() {
				//身份认证
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			InternetAddress[] addresses = { new InternetAddress(to) };
			message = new MimeMessage(session2);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, addresses);
			//邮件标题
			message.setSubject("在线课程：邮箱验证");
			message.setSentDate(new Date());
			//邮件内容
			message.setText("您好！感谢您注册在线课程系统，您正在进行邮箱验证，本次请求的验证码为：" + code +"。");
			transport = session2.getTransport("smtp");
			transport.connect(smtpHost, username, password);
			transport.send(message);

			System.out.println("email has been sent");
		%>
			<c:set var="setCode" value="true"></c:set>	 		
		<%
		} else {
			System.out.println("email not ready");
		}
	%>

	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">

			<div class="navbar-header">
				<a href="#" class="navbar-left"><img src="images/online.png"></a>
				<a href="#" class="navbar-brand">在线课程 </a>
			</div>

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="HomePageServlet">首页</a></li>
					<li><a href="login.jsp">登录</a></li>
					<li class="active"><a href="#">注册</a></li>

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

	<div class="container-fluid margin-top-80">
		<div class="row-fluid col-md-12 column">
			<form
				action="<%="after".equals(request.getParameter("getCode")) ? "RegisterPageServlet" : "#"%>"
				method="get" class="layout-center form-horizontal"
				onsubmit="<%="after".equals(request.getParameter("getCode"))
					? "return (checkRegisterUser() && checkRegisterPsd() && checkRegisterRePsd() && checkRegisterEmail() && checkRegisterCode())"
					: "return (checkRegisterUser() && checkRegisterPsd() && checkRegisterRePsd() && checkRegisterEmail())"%>">


				<h2 class="col-sm-offset-4">请注册</h2>
				<div class="form-group">
					<label for="username"
						class="control-label col-sm-2 col-sm-offset-2">用户名</label>
					<div class="col-sm-4">
						<input type="text" id="userName" name="username"
							value="${param.username}" class="form-control controls"
							placeholder="至少六位，非纯数字" onchange="checkRegisterUser()" />
						<p id="registerUserAlert"></p>
					</div>
				</div>
				<div class="form-group">
					<label for="password"
						class="control-label col-sm-2 col-sm-offset-2">密码</label>
					<div class="col-sm-4">
						<input type="password" id="password" name="password"
							value="${param.password}" class="form-control"
							placeholder="至少六位，非纯数字，不能与用户名相同" onchange="checkRegisterPsd()">
						<p id="registerPsdAlert"></p>
					</div>
				</div>
				<div class="form-group">
					<label for="repassword"
						class="control-label col-sm-2 col-sm-offset-2">重复密码</label>
					<div class="col-sm-4">
						<input type="password" id="repassword" name="repassword"
							value="${param.repassword}" class="form-control"
							placeholder="重复密码" onkeyup="checkRegisterRePsd()" disabled>
						<p id="registerRePsdAlert"></p>
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="control-label col-sm-2 col-sm-offset-2">邮箱</label>
					<div class="col-sm-4">
						<input type="email" id="email" name="email" value="${param.email}"
							class="form-control" placeholder="如someone@example.com"
							onchange="checkRegisterEmail()">
						<p id="registerEmailAlert"></p>
						
							<c:if test="${setCode == null}">
								<button type="submit" class="btn btn-info" name="getCode"
							value="before" onClick="changeValue(this)">发送验证码</button>
							</c:if>
							<c:if test="${setCode == \"true\"}">
								<button type="submit" class="btn btn-info" name="getCode"
							value="after" onClick="changeValue(this)" disabled>已发送</button>
							</c:if>
							
					</div>
				</div>
				<div class="form-group">
					<label for="emailCode"
						class="control-label col-sm-2 col-sm-offset-2">验证码</label>
					<div class="col-sm-4">
						<input type="text" id="emailCode" name="emailCode"
							class="form-control" placeholder="请输入验证码">
						<p id="registerCodeAlert"></p>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4">
						<div class="checkbox">
							<label><input type="checkbox" />记住我</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4">
						<button type="submit" class="btn btn-primary btn-block" onclick="">注册</button>
					</div>
				</div>
			</form>
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
	<script src="js/checkRegister.js"></script>
	<script>
	
	//自动弹出error提示框
        $(function () {
            $('#myModal').modal({
            keyboard: true
        })});
    </script>

</body>
</html>