<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.sun.net.ssl.internal.ssl.Provider"%>
<%@ page import="javax.mail.*"%>
<%@ page import="javax.mail.internet.AddressException"%>
<%@ page import="javax.mail.internet.InternetAddress"%>
<%@ page import="javax.mail.internet.MimeMessage"%>
<%@ page import="java.security.Security"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Properties"%>

<%
MimeMessage message;
Security.addProvider(new Provider());
final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
//QQ邮箱服务器
String smtpHost="smtp.qq.com";
//邮箱用户名，即QQ账号
final String username = "1376237699";
//邮箱授权码
final String password = "jhtsnpzalmzigjfe";
//要发送到的邮箱
String to = "m17717900994@163.com";
//自己的邮箱
String from = "1376237699@qq.com";
Transport transport;

Properties props = new Properties();
props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
props.setProperty("mail.smtp.socketFactory.fallback", "false");
props.setProperty("mail.smtp.port", "465");
props.setProperty("mail.smtp.socketFactory.port", "465");
props.setProperty("mail.smtp.auth", "true");
props.put("mail.smtp.host",smtpHost);
props.put("mail.smtp.username", username);
props.put("mail.smtp.password", password);
Session session2 = Session.getInstance(props,  new Authenticator() {
    //身份认证
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
});
InternetAddress[] addresses = {new InternetAddress(to)};
message = new MimeMessage(session2);
message.setFrom(new InternetAddress(from));
message.setRecipients(Message.RecipientType.TO,addresses);
message.setSubject("在线课程：邮箱验证");
message.setSentDate(new Date());
message.setText("您好！感谢您注册在线课程系统，您正在进行邮箱验证，本次请求的验证码为：589985。");
transport = session2.getTransport("smtp");
transport.connect(smtpHost, username, password);
transport.send(message);

request.setAttribute("username", request.getParameter("username"));
request.setAttribute("password", request.getParameter("password"));
request.setAttribute("repassword", request.getParameter("repassword"));
request.setAttribute("email", request.getParameter("email"));

response.sendRedirect("register.jsp?emailCode=exsit");

System.out.println("email has been sent");
%>