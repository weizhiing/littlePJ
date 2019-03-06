package Servlet.Course.SourceRelation;

import Bean.CourseBean;
import Bean.UserBean;
import Dao.SourceDao;

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
@WebServlet(name = "SourceDeleteServlet",value = "/SourceDeleteServlet")
public class SourceDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        CourseBean courseBean =(CourseBean)session.getAttribute("course");
        String sourceName = request.getParameter("sourceName");
        SourceDao sourceDao =new SourceDao();
        System.out.println(sourceDao.idSource(
                courseBean.getCourse(),courseBean.getTeacher().getUsername(),sourceName
        ));
        sourceDao.deleteSource(sourceDao.idSource(
                courseBean.getCourse(),courseBean.getTeacher().getUsername(),sourceName
        ));
        response.sendRedirect("/SourcePageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
