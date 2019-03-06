package Servlet.Course.CourseRelation;

import Bean.CourseBean;
import Bean.UserBean;
import Dao.CourseDao;
import Dao.CourseSelect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by your dad on 2018/7/25.
 */
@WebServlet(name = "CourseInformationServlet",value = "/CourseInformationServlet")
public class CourseInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =request.getSession();
        UserBean userBean =(UserBean) session.getAttribute("user");
        if(userBean==null)
            request.getRequestDispatcher("login.jsp").forward(request,response);

        String coursename = request.getParameter("coursename");
        String teacherName = request.getParameter("teacherName");
        CourseDao courseDao =new CourseDao();
        CourseSelect courseSelect =new CourseSelect();
        CourseBean courseBean = courseDao.searchCourse(courseDao.idCourse(coursename,teacherName));
        session.setAttribute("course",courseBean);
        if(teacherName.equals(userBean.getUsername())){
            session.setAttribute("role","teacher");
        }else if(courseSelect.idCourseSelect(coursename,teacherName,userBean.getUsername())!=0) {
            session.setAttribute("role","student");
        }else {
            session.setAttribute("role","neither");
        }

        response.sendRedirect("/courseDetails.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
