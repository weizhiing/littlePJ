package service.User;

import Bean.CourseBean;
import Bean.UserBean;
import Dao.CourseDao;
import Dao.CourseSelect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
@WebServlet(name = "UserService",value = "/UserService/person")
public class UserService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        if(userBean==null||userBean.getUsername()==null)
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        CourseDao courseDao=new CourseDao();
        CourseSelect courseSelect=new CourseSelect();
        ArrayList<CourseBean>  selectCourse = courseSelect.searchCourseSelect(userBean.getUsername());
        ArrayList<CourseBean>  openCourse = courseDao.searchCourseList(userBean.getUsername());
        request.setAttribute("selectCourse",selectCourse);
        request.setAttribute("openCourse",openCourse);

        request.getRequestDispatcher("/myPage.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
