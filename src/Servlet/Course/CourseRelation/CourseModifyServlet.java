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
@WebServlet(name = "CourseModifyServlet",value = "/CourseModifyServlet")
public class CourseModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session =request.getSession();
        CourseBean courseBean =(CourseBean) session.getAttribute("course");
        String courseDescription = request.getParameter("recourseDesc");
        System.out.print(courseDescription);
        CourseDao courseDao =new CourseDao();
        courseBean.setDescription(courseDescription);
        session.setAttribute("course",courseBean);
        courseDao.modifyCourse(courseDao.idCourse(courseBean.getCourse(),courseBean.getTeacher().getUsername()),courseDescription);
        response.sendRedirect("/courseDetails.jsp");

     }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
