package Servlet.Course.SourceRelation;

import Bean.CourseBean;
import Bean.SourceBean;
import Bean.UserBean;
import Dao.SourceDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/25.
 */
@WebServlet(name = "SourcePageServlet", value = "/SourcePageServlet")
public class SourcePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        CourseBean courseBean =(CourseBean)session.getAttribute("course");
        SourceDao sourceDao =new SourceDao();
        ArrayList<SourceBean> source = sourceDao.searchSource(courseBean.getCourse(),courseBean.getTeacher().getUsername());
        request.setAttribute("sourceList",source);
        request.getRequestDispatcher("/courseData.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
