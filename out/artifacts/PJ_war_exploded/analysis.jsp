<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page
        import="java.io.*,java.util.*,java.sql.*,Bean.*, java.text.DecimalFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <title>统计</title>

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

            <form class="navbar-form navbar-right" role="search"
                  action="SearchPageServlet" method="post">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="glyphicon glyphicon-search"></i>
                        </div>
                        <input type="text" class="form-control" id="searchCourseName"
                               placeholder="请输入课程关键词" name="courseNameSearch"/>
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
                <li><a href="DiscussPageServlet">讨论</a></li>

                <c:if test="${sessionScope.role == \"teacher\"}">
                    <li class="active"><a href="#">统计</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<%
    ArrayList<StudentStatisticBean> students = (ArrayList<StudentStatisticBean>) request
            .getAttribute("students");
    HashMap<String, ArrayList<HomeworkCheckBean>> studentMap = (HashMap<String, ArrayList<HomeworkCheckBean>>) request
            .getAttribute("homeworkScore");
%>

<div class="container-fluid margin-top-50 margin-bottom-80">
    <div class="row clearfix" style="width: 80%; margin: 0 auto;">

        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>序号</th>
                <th>学生</th>
                <th>总作业</th>
                <th>完成作业</th>
                <th>完成度</th>
                <th>具体情况</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.students}" var="student"
                       varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${student.studentName.username }</td>
                    <td>${student.homeworkDo }</td>
                    <td>${student.homework}</td>
                    <td>
                        <div class="progress">
                            <c:set var="workDo" value="${student.homeworkDo}"></c:set>
                            <c:set var="workAll" value="${student.homework}"></c:set>
                            <%
                                //得到作业完成度(百分数)
                                int homeworkDo = (int) pageContext.getAttribute("workDo");
                                int homework = (int) pageContext.getAttribute("workAll");

                                double value = (double) homeworkDo / homework;
                                DecimalFormat format = new DecimalFormat("0.00%");
                                String finishPercent = format.format(value);
                                System.out.println(finishPercent);
                            %>
                            <div class="progress-bar-success" role="progressbar"
                                 aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                                 style="width: <%=finishPercent%>;"><%=finishPercent%>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="panel panel-default">
                            <table class="table">

                                <c:forEach items="${requestScope.studentMap}" var="student2">


                                    <c:forEach items="${student2.value}" var="studentInfo">
                                        <c:if
                                                test="${student2.key == student.studentName.username }">
                                            <tr>
                                                <td>作业名:
                                                        ${studentInfo.getHomeworkComplete().getHomework().homeworkName}</td>
                                                <td>得分: ${studentInfo.score}</td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>

                                </c:forEach>

                                <tr>
                                    <c:if test="${student.result == 0 }">
                                        <td>总成绩:</td>
                                        <td>
                                            <form action="StudentScoreServlet?studentName=${student.studentName.username}"
                                                  method="post">
                                                <input class="special-input" type="number" id="result"
                                                       name="result" style="width: 80px;"/>
                                                <button class="btn btn-primary btn-sm" type="submit"
                                                        onClick="checkScore()">提交
                                                </button>
                                            </form>
                                        </td>
                                    </c:if>

                                    <c:if test="${student.result != 0 }">
                                        <td>总成绩:</td>
                                        <td>${student.result}</td>
                                    </c:if>

                                </tr>


                            </table>

                        </div>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
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

</body>
</html>