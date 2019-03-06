package Servlet.Course.HomeworkRelation;

import Bean.CourseBean;
import Bean.HomeworkBean;
import Bean.UserBean;
import Dao.HomeworkDao;

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
@WebServlet(name = "HomeworkAddServlet",value = "/HomeworkAddServlet")
public class HomeworkAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        CourseBean courseBean = (CourseBean) session.getAttribute("course");
        String workName = request.getParameter("homeworkName");
        String workContent = request.getParameter("homeworkDescription");
        HomeworkDao homeworkDao =new HomeworkDao();
       if( homeworkDao.idHomework(courseBean.getCourse(),courseBean.getTeacher().getUsername(),workName)!=0){
          request.setAttribute("error","name is used");
           request.getRequestDispatcher("/HomeworkPageServlet").forward(request,response);
       }
       homeworkDao.addHomework(new HomeworkBean(workName,workContent,courseBean));
        response.sendRedirect("/HomeworkPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
