package Servlet.Course.DiscussRelation;

import Bean.CourseBean;
import Bean.DiscussBean;
import Bean.UserBean;
import Dao.DiscussDao;

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
@WebServlet(name = "DiscussOpenServlet", value = "/DiscussOpenServlet")
public class DiscussOpenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String error  = "";
        HttpSession session =request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        CourseBean courseBean =(CourseBean)session.getAttribute("course");
        DiscussDao discussDao =new DiscussDao();

        int count =discussDao.idDiscuss(courseBean.getCourse(),courseBean.getTeacher().getUsername(),
                userBean.getUsername(),title);
        if( count!=0){
           error = "title repeat";
           request.setAttribute("error",error);
           request.getRequestDispatcher("/DiscussPageServlet").forward(request,response);
       }
         discussDao.addDiscuss(new DiscussBean(courseBean,userBean,title,content));
          response.sendRedirect("/DiscussPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
