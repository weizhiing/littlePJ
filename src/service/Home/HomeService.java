package service.Home;

import Bean.CourseBean;
import Dao.CourseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by your dad on 2018/7/29.
 */
@WebServlet(name = "HomeService",value = "/HomeService/home")
public class HomeService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //  HttpSession session = request.getSession();
        CourseDao courseDao =new CourseDao();
        ArrayList<CourseBean> arrayListHot = courseDao.searchHotCourse();
        ArrayList<CourseBean> arrayListNew = courseDao.searchNewCourse();

        Collections.reverse(arrayListNew);

        request.setAttribute("hotCourse",arrayListHot);
        request.setAttribute("newCourse",arrayListNew);
        request.getRequestDispatcher("/homepage.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
