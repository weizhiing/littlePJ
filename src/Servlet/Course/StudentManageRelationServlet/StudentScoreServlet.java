package Servlet.Course.StudentManageRelationServlet;

import Bean.CourseBean;
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
@WebServlet(name = "StudentScoreServlet",value = "/StudentScoreServlet")
public class StudentScoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =request.getSession();
        CourseBean courseBean = (CourseBean) session.getAttribute("course");
      String studentName = request.getParameter("studentName");
        System.out.println(studentName);
        String result = request.getParameter("result");
        int score = Integer.parseInt(result);
        System.out.println(score);
        CourseSelect courseSelect =new CourseSelect();
      int id =    courseSelect.idCourseSelect(courseBean.getCourse(),courseBean.getTeacher().getUsername(),studentName);
        courseSelect.scoreDo(id,score);
        response.sendRedirect("/StudentPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
