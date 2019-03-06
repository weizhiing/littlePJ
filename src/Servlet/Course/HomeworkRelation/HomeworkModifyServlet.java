package Servlet.Course.HomeworkRelation;

import Bean.CourseBean;
import Bean.UserBean;
import Dao.CourseDao;
import DaoInterface.homeworkDao.HomeworkDao;

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
@WebServlet(name = "HomeworkModifyServlet",value = "/HomeworkModifyServlet")
public class HomeworkModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        CourseBean courseBean = (CourseBean) session.getAttribute("course");
        HomeworkDao homeworkDao = new Dao.HomeworkDao();
        String homeworkName = request.getParameter("homeworkName");
        Enumeration  pNames=request.getParameterNames();
        while(pNames.hasMoreElements()){
            String name=(String)pNames.nextElement();
            String value=request.getParameter(name);
            System.out.println(name + "=" + value);
        }

        System.out.println(homeworkName);
        String homeworkNewDescription = request.getParameter("homeworkNewDescription");
        CourseDao courseDao =new CourseDao();
        int id =courseDao.idCourse(courseBean.getCourse(),courseBean.getTeacher().getUsername());
        System.out.println(id);
        System.out.println(homeworkNewDescription);
        System.out.println(homeworkName);
        homeworkDao.modifyHomework(id,homeworkName,homeworkNewDescription);
        response.sendRedirect("/HomeworkPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doPost(request,response);
    }
}
