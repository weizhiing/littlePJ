package service.Search;

import Bean.CourseBean;
import Dao.CourseDao;
import Dao.UserDao;

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
@WebServlet(name = "SearchService", value = "/SearchService/search")
public class SearchService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String courseNameSearch = request.getParameter("courseNameSearch");
        String courseDesprictionSearch = request.getParameter("courseDescriptionSearch");
        String teacherNameSearch = request.getParameter("teacherNameSearch");
        String error ="";
      //  System.out.println(courseNameSearch);
   //     System.out.println(courseDesprictionSearch);
    //    System.out.println(teacherNameSearch);
       UserDao userDao = new UserDao();
        if (teacherNameSearch != null) {
            if (userDao.idUserStr(teacherNameSearch).size()== 0){
                error = error +"teacherNameSearch no matching   ";
                teacherNameSearch ="";
            }
        }else {
            teacherNameSearch ="";
        }
        CourseDao courseDao= new CourseDao();
        if (courseNameSearch != null) {
            if (courseDao.idCourseStr(courseNameSearch,2).size() == 0){
                error = error +"courseNameSearch no matching   ";
                courseNameSearch ="";
            }
        }else {
            courseNameSearch ="";
        }
        if (courseDesprictionSearch != null) {
            if (courseDao.idCourseStr(courseDesprictionSearch,3).size() == 0){
                error = error +"courseDescriptionSearch no matching";
                courseDesprictionSearch ="";
            }
        }else {
            courseDesprictionSearch ="";
        }
        ArrayList<CourseBean> newCourse = courseDao.searchNewCourseAll(teacherNameSearch,courseNameSearch,courseDesprictionSearch);
        ArrayList<CourseBean> hotCourse = courseDao.searchHotCourseAll(teacherNameSearch,courseNameSearch,courseDesprictionSearch);
        Collections.reverse(newCourse);
        request.setAttribute("newCourse",newCourse);
        request.setAttribute("hotCourse",hotCourse);
        request.setAttribute("error",error);
        request.getRequestDispatcher("/search.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
