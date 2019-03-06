package Servlet.Course.CourseRelation;

import Bean.CourseBean;
import Dao.CourseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by your dad on 2018/7/26.
 */
@WebServlet(name = "CourseDeleteServlet",value = "/CourseDeleteServlet")
public class CourseDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CourseBean courseBean =(CourseBean) session.getAttribute("course");
        String role = (String) session.getAttribute("role");
        CourseDao courseDao= new CourseDao();
        int id = courseDao.idCourse(courseBean.getCourse(),courseBean.getTeacher().getUsername());
        courseDao.deleteCourse(id);
        session.setAttribute("role","neither");
        session.setAttribute("course",null);
        response.sendRedirect("/UserPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
