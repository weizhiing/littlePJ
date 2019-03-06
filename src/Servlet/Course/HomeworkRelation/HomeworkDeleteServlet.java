package Servlet.Course.HomeworkRelation;

import Bean.CourseBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by your dad on 2018/7/26.
 */
@WebServlet(name = "HomeworkDeleteServlet",value = "/HomeworkDeleteServlet")
public class HomeworkDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Enumeration pNames=request.getParameterNames();
        while(pNames.hasMoreElements()){
            String name=(String)pNames.nextElement();
            String value=request.getParameter(name);
            System.out.println(name + "=" + value);
        }
        CourseBean courseBean = (CourseBean) session.getAttribute("course");
        Dao.HomeworkDao homeworkDao = new Dao.HomeworkDao();

        String homeworkName = request.getParameter("homeworkName");

        int id = homeworkDao.idHomework(courseBean.getCourse(),courseBean.getTeacher().getUsername(),
                                        homeworkName);
        homeworkDao.deleteHomework(id);
        response.sendRedirect("/HomeworkPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
