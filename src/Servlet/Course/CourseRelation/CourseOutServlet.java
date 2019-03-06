package Servlet.Course.CourseRelation;

import Bean.CourseBean;
import Bean.UserBean;
import Dao.CourseSelect;

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
@WebServlet(name = "CourseOutServlet",value = "/CourseOutServlet")
public class CourseOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =request.getSession();
        session.setAttribute("role","neither");
        CourseSelect courseSelect =new CourseSelect();
        CourseBean courseBean =(CourseBean) session.getAttribute("course");
        UserBean userBean =(UserBean)session.getAttribute("user");
        int id = courseSelect.idCourseSelect(courseBean.getCourse(),courseBean.getTeacher().getUsername(),userBean.getUsername());
        courseSelect.deleteCourseSelect(id);
        response.sendRedirect("/courseDetails.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doPost(request,response);
    }
}
