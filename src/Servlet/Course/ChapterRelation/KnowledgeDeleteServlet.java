package Servlet.Course.ChapterRelation;

import Bean.CourseBean;
import Bean.UserBean;
import Dao.ChapterKnowledgeDao;

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
@WebServlet(name = "KnowledgeDeleteServlet",value = "/KnowledgeDeleteServlet")
public class KnowledgeDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        CourseBean courseBean = (CourseBean) session.getAttribute("course");
        String chapterName = request.getParameter("chapterName");
        String knowledgeName = request.getParameter("knowledgeName");
        ChapterKnowledgeDao chapterKnowledgeDao = new ChapterKnowledgeDao();
        int id = chapterKnowledgeDao.idChapterKnowledge(courseBean.getCourse(), courseBean.getTeacher().getUsername(), chapterName, knowledgeName);
        System.out.println(id);
        chapterKnowledgeDao.deleteChapterKnowledge(id);
        response.sendRedirect("/ChapterPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
