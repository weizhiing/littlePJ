package Servlet.Course.HomeworkRelation;

import Bean.*;
import Dao.HomeworkCheck;
import Dao.HomeworkDoDao;
import DaoInterface.homeworkDao.HomeworkDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by your dad on 2018/7/25.
 */
@WebServlet(name = "HomeworkPageServlet", value = "/HomeworkPageServlet")
public class HomeworkPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        CourseBean courseBean = (CourseBean) session.getAttribute("course");
        String role = (String) session.getAttribute("role");
        HomeworkDao homeworkDao = new Dao.HomeworkDao();
        HomeworkDoDao homeworkDoDao = new HomeworkDoDao();
        HomeworkCheck homeworkCheck = new HomeworkCheck();
        if (role.equals("neither")) {
            response.sendRedirect("/login.jsp");
            return;
        }else if(role.equals("student")){
        ArrayList<HomeworkBean> homeworkBeanArrayList =
                homeworkDao.searchHomework(courseBean.getCourse(), courseBean.getTeacher().getUsername());

       //     System.out.println(homeworkBeanArrayList.size());
        ArrayList<HomeworkDoBean> homeworkDoBeanArrayList =
                homeworkDoDao.searchHomeworkDo(courseBean.getCourse(), courseBean.getTeacher().getUsername(), userBean.getUsername());

      //      System.out.println(homeworkDoBeanArrayList.size());
        ArrayList<HomeworkCheckBean> homeworkCheckBeanArrayList =
                homeworkCheck.searchHomeworkCheck(courseBean.getCourse(), courseBean.getTeacher().getUsername(),
                        userBean.getUsername());
   //         System.out.println(homeworkCheckBeanArrayList.size());





        for (HomeworkDoBean homeworkDoBean : homeworkDoBeanArrayList) {
            HomeworkBean homework = homeworkDoBean.getHomework();
            Iterator<HomeworkBean> iterator = homeworkBeanArrayList.iterator();
            while(iterator.hasNext()){
                HomeworkBean homeworkBean= iterator.next();
                if (homework.getCourse().getCourse().equals(homeworkBean.getCourse().getCourse()) &&
                        homework.getCourse().getTeacher().getUsername().equals(homeworkBean.getCourse().getTeacher().getUsername()) &&
                        homework.getHomeworkName().equals(homeworkBean.getHomeworkName())
                        ) {
                    iterator.remove();
                }
            }

        }

        for (HomeworkCheckBean homeworkCheckBean : homeworkCheckBeanArrayList) {
            HomeworkDoBean homeworkDo = homeworkCheckBean.getHomeworkComplete();
            Iterator<HomeworkDoBean> iterator =homeworkDoBeanArrayList.iterator();
            while (iterator.hasNext()){
                HomeworkDoBean homeworkDoBean = iterator.next();
                if (homeworkDo.getHomework().getCourse().getCourse().equals(homeworkDoBean.getHomework().getCourse().getCourse())&&
                        homeworkDo.getHomework().getCourse().getTeacher().getUsername().equals( homeworkDoBean.getHomework().getCourse().getTeacher().getUsername())&&
                        homeworkDo.getHomework().getHomeworkName().equals(homeworkDoBean.getHomework().getHomeworkName())&&
                        homeworkDo.getStudent().getUsername().equals( homeworkDoBean.getStudent().getUsername())
                        ) {
                    iterator.remove();
                }
            }
        }
            System.out.println(homeworkBeanArrayList.size());
            System.out.println(homeworkDoBeanArrayList.size());
            System.out.println(homeworkCheckBeanArrayList.size());

        request.setAttribute("homeworkNoDo",homeworkBeanArrayList);
        request.setAttribute("homeworkDoBean",homeworkDoBeanArrayList);
        request.setAttribute("homeworkCheckBean",homeworkCheckBeanArrayList);
        request.getRequestDispatcher("/homework.jsp").forward(request,response);}
        else if(role.equals("teacher")){
            ArrayList<HomeworkBean> homeworkBeanArrayList =
                    homeworkDao.searchHomework(courseBean.getCourse(), courseBean.getTeacher().getUsername());
            request.setAttribute("homeworkNoDo",homeworkBeanArrayList);
          /*  if(homeworkBeanArrayList.size()>=2){
            System.out.println(homeworkBeanArrayList.get(0).getHomeworkName());
            System.out.println(homeworkBeanArrayList.get(1).getHomeworkName());}*/
            request.getRequestDispatcher("/homework.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
