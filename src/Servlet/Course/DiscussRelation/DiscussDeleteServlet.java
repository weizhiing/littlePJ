package Servlet.Course.DiscussRelation;

import Bean.CourseBean;
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
@WebServlet(name = "DiscussDeleteServlet",value = "/DiscussDeleteServlet")
public class DiscussDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        System.out.println(title);
        HttpSession session =request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        CourseBean courseBean =(CourseBean)session.getAttribute("course");
        DiscussDao discussDao =new DiscussDao();

        int count = discussDao.idDiscuss(courseBean.getCourse(),
                           courseBean.getTeacher().getUsername(),
                            userBean.getUsername(),title);
        System.out.println(count);
        System.out.println( discussDao.deleteDiscuss(count));
        response.sendRedirect("/DiscussPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
