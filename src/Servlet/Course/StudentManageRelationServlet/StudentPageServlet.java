package Servlet.Course.StudentManageRelationServlet;

import Bean.*;
import Dao.CourseSelect;
import Dao.HomeworkCheck;
import Dao.HomeworkDao;
import Dao.HomeworkDoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by your dad on 2018/7/25.
 */
@WebServlet(name = "StudentPageServlet", value = "/StudentPageServlet")
public class StudentPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CourseBean courseBean = (CourseBean) session.getAttribute("course");
        CourseSelect courseSelect = new CourseSelect();
        ArrayList<UserBean> userBeanArrayList = courseSelect.searchList(courseBean.getCourse()
                , courseBean.getTeacher().getUsername());
        ArrayList<CourseSelectBean> courseSelectBeanArrayList = new ArrayList<CourseSelectBean>();
        for (int i = 0; i < userBeanArrayList.size(); i++) {
            courseSelectBeanArrayList.add(courseSelect.searchCourseSelect(courseSelect.idCourseSelect(
                    courseBean.getCourse(), courseBean.getTeacher().getUsername(),
                    userBeanArrayList.get(i).getUsername()
            )));
        }
        HomeworkDao homeworkDao =new HomeworkDao();
        HomeworkDoDao homeworkDoDao =new HomeworkDoDao();
        HomeworkCheck homeworkCheck =new HomeworkCheck();
        ArrayList<StudentStatisticBean> students  = new ArrayList<StudentStatisticBean>();
        HashMap<String,ArrayList<HomeworkCheckBean>> homeworkScore =new HashMap<String,ArrayList<HomeworkCheckBean>>();
        for(int i=0;i<courseSelectBeanArrayList.size();i++){
            StudentStatisticBean studentStatisticBean = new StudentStatisticBean(
                    courseSelectBeanArrayList.get(i).getCourse(),courseSelectBeanArrayList.get(i).getStudent()
                 ,courseSelectBeanArrayList.get(i).getResult());
            ArrayList<HomeworkBean> homeworkBeanArrayList =
                    homeworkDao.searchHomework(courseBean.getCourse(), courseBean.getTeacher().getUsername());
            ArrayList<HomeworkDoBean> homeworkDoBeanArrayList =
                    homeworkDoDao.searchHomeworkDo(courseBean.getCourse(), courseBean.getTeacher().getUsername(),
                            courseSelectBeanArrayList.get(i).getStudent().getUsername());
            studentStatisticBean.setHomework(homeworkBeanArrayList.size());
            studentStatisticBean.setHomeworkDo(homeworkDoBeanArrayList.size());
            students .add(studentStatisticBean);

            ArrayList<HomeworkCheckBean> homeworkCheckBeanArrayList =
                    homeworkCheck.searchHomeworkCheck(courseBean.getCourse(), courseBean.getTeacher().getUsername(),
                            courseSelectBeanArrayList.get(i).getStudent().getUsername());
            homeworkScore.put(courseSelectBeanArrayList.get(i).getStudent().getUsername(),homeworkCheckBeanArrayList);
        }
        request.setAttribute("students",students);
        request.setAttribute("homeworkScore",homeworkScore);
        request.getRequestDispatcher("/analysis.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
