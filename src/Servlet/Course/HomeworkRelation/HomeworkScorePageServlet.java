package Servlet.Course.HomeworkRelation;

import Bean.CourseBean;
import Bean.HomeworkCheckBean;
import Bean.HomeworkDoBean;
import Bean.UserBean;
import Dao.HomeworkCheck;
import Dao.HomeworkDoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/26.
 */
@WebServlet(name = "HomeworkScorePageServlet",value = "/HomeworkScorePageServlet")
public class HomeworkScorePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CourseBean courseBean = (CourseBean) session.getAttribute("course");
        UserBean userBean = (UserBean)session.getAttribute("user");
        String homework  =  request.getParameter("homeworkName");
        String studentName  =  request.getParameter("studentName");
        HomeworkDoDao homeworkDoDao = new HomeworkDoDao();
        if(studentName==null){
            if(homework!=null){
            session.setAttribute("homework", homework);}
            homework =(String) session.getAttribute("homework");
            System.out.println(homework);
            ArrayList<HomeworkDoBean> homeworkDoBeanArrayList = homeworkDoDao.searchHomeworkDoCourse(courseBean.getCourse(), courseBean.getTeacher().getUsername(), homework);
            System.out.println(homeworkDoBeanArrayList.size());
            request.setAttribute("notScoredHomework", homeworkDoBeanArrayList);
            request.getRequestDispatcher("gradeHomework.jsp").forward(request, response);
        }else {

            String result  =  request.getParameter("score");
           int score = Integer.parseInt(result);
            homework = (String) session.getAttribute("homework");
            HomeworkDoBean homeworkDoBean = homeworkDoDao.searchHomeworkDo(homeworkDoDao.idHomeworkDo(courseBean.getCourse()
            ,courseBean.getTeacher().getUsername(),homework,studentName));
            HomeworkCheckBean homeworkCheckBean = new HomeworkCheckBean(homeworkDoBean,score);
            HomeworkCheck homeworkCheck =new HomeworkCheck();
            homeworkCheck.addHomeworkCheck(homeworkCheckBean);
           response.sendRedirect("/HomeworkScorePageServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 doPost(request,response);
    }
}
