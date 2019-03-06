package Servlet.Course.ChapterRelation;

import Bean.ChapterBean;
import Bean.CourseBean;
import Bean.UserBean;
import Dao.ChapterDao;

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
@WebServlet(name = "ChapterAddServlet",value = "/ChapterAddServlet")
public class ChapterAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        CourseBean courseBean =(CourseBean)session.getAttribute("course");
        String chapterName = request.getParameter("chapterName");
        System.out.print(chapterName);
        String error="";
        ChapterDao chapterDao =new ChapterDao();
        int id = chapterDao.idChapter(courseBean.getCourse(),courseBean.getTeacher().getUsername(),chapterName );
        if(id!=0){
            error = "章节名重复";
            request.setAttribute("error",error);
            request.getRequestDispatcher("/ChapterPageServlet").forward(request,response);
            return;
        }
        ChapterBean chapterBean =new ChapterBean(courseBean,chapterName);
        chapterDao.addChapter(chapterBean);
        response.sendRedirect("/ChapterPageServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 doPost(request,response);
    }
}
