package Servlet.Course.HomeworkRelation;

import Bean.CourseBean;
import Bean.HomeworkBean;
import Bean.HomeworkDoBean;
import Bean.UserBean;
import Dao.HomeworkDoDao;

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
@WebServlet(name = "HomeworkDoServlet",value = "/HomeworkDoServlet")
public class HomeworkDoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        CourseBean courseBean = (CourseBean) session.getAttribute("course");
        Dao.HomeworkDao homeworkDao = new Dao.HomeworkDao();
        HomeworkDoDao homeworkDoDao = new HomeworkDoDao();
        String homeworkName = request.getParameter("homeworkName");
        String content = request.getParameter("NodoWork");
        System.out.println(content);
        System.out.println(homeworkName);
        System.out.println(homeworkDao.idHomework(courseBean.getCourse(),courseBean.getTeacher().getUsername(),homeworkName));
        HomeworkBean homeworkBean = homeworkDao.searchHomeworkOne(homeworkDao.idHomework(courseBean.getCourse(),courseBean.getTeacher().getUsername(),homeworkName));
        HomeworkDoBean homeworkDoBean =new HomeworkDoBean(homeworkBean,userBean,content);
        homeworkDoDao.addHomeworkDo(homeworkDoBean);
        response.sendRedirect("/HomeworkPageServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request,response);
    }
}
