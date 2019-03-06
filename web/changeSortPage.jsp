<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import=" Bean.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%

	int pageSize = 8;
	int totalPage = 1;

	String sort = request.getParameter("sortWay");
	System.out.println(sort);
	ArrayList<CourseBean> hotCourseSession = (ArrayList<CourseBean>)session.getAttribute("hotCourse");
	ArrayList<CourseBean> newCourseSession = (ArrayList<CourseBean>)session.getAttribute("newCourse");
	
	if("hot".equals(sort)){
		session.setAttribute("courseList", hotCourseSession);
		totalPage = (hotCourseSession.size()%pageSize==0)?(hotCourseSession.size()/pageSize):(hotCourseSession.size()/pageSize+1);
		ArrayList<CourseBean> courseGet = (ArrayList<CourseBean>)hotCourseSession.clone();
		
	} else if(sort.equals("new")){
		session.setAttribute("courseList", newCourseSession);
		totalPage = (newCourseSession.size()%pageSize==0)?(newCourseSession.size()/pageSize):(newCourseSession.size()/pageSize+1);
		ArrayList<CourseBean> courseGet = (ArrayList<CourseBean>)newCourseSession.clone();
	}
	
	
	//int totalPage = (courseGet.size()%pageSize==0)?(courseGet.size()/pageSize):(courseGet.size()/pageSize+1);
	
	int currentPage;
	if(request.getParameter("page") == null){
		currentPage = 1;
	} else{
		currentPage = Integer.parseInt(request.getParameter("page"));
		if (currentPage <= 0) {//在第一页点击上一页的情况处理
	        currentPage = 1;
	    } else if(currentPage >= totalPage) {//在最后一页点击下一页的处理
	        currentPage = totalPage;
	    }
	}
	    int mark = (currentPage-1)*pageSize;
	    int end = mark + pageSize - 1;
	    int firstPage = 1;
	    int lastPage = totalPage;
	    int prePage = (currentPage>1)?currentPage-1:1;
	    int nextPage = (totalPage-currentPage>0)?currentPage+1:totalPage;
%>

<!-- 返回的搜索结果 -->	
<div class="col-md-12 margin-top-50 margin-bottom-80">

	<c:forEach items="${sessionScope.courseList}" var="course"
		begin="<%=mark %>" end="<%=end %>">
		<div class="col-md-3 height-250">
			<div class="hovereffect thumbnail">
				<img class="img-responsive" src="/Source/upload/${course.image.url}" alt="">
				<div class="overlay">
					<h4>${course.course }</h4>
					<p>${course.description }</p>
					<div class="btn-group">
						<a class="btn btn-primary"
							href="CourseInformationServlet?coursename=${course.course }&teacherName=${course.teacher.username}"
							id="viewDeatils">查看</a>
					</div>
				</div>

			</div>
		</div>
	</c:forEach>

</div>

<!-- 返回的分页 -->
<div class="margin-bottom-80">
		<ul class="pager">
			<li><a href="javascript:void(0);" onclick="turnPage(1)">首页</a></li>
			<li><a href="javascript:void(0);" onclick="turnPage(<%=currentPage%>-1)">上一页</a></li>
			<li><a href="javascript:void(0);" onclick="turnPage(<%=currentPage%>+1)">下一页</a></li>
			<li><a href="javascript:void(0);" onclick="turnPage(<%=totalPage%>)">尾页</a></li>
			<%=currentPage%> / <%=totalPage%> 页
		</ul>
</div>
	

