package Servlet.Course.ChapterRelation;

import Bean.ChapterBean;
import Bean.ChapterKnowledgeBean;
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
@WebServlet(name = "KnowledgeAddServlet", value = "/KnowledgeAddServlet")
public class KnowledgeAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        CourseBean courseBean = (CourseBean) session.getAttribute("course");
        String chapterName = request.getParameter("chapterName");
        String knowledgeName = request.getParameter("knowledgeName");
        String error = "";
        ChapterKnowledgeDao chapterKnowledgeDao = new ChapterKnowledgeDao();
        int id = chapterKnowledgeDao.idChapterKnowledge(courseBean.getCourse(), courseBean.getTeacher().getUsername(), chapterName, knowledgeName);
        System.out.println(id);
        if (id != 0) {
            error = "知识点重复";
            request.setAttribute("error",error);
            request.getRequestDispatcher("/ChapterPageServlet").forward(request,response);
            return;
        }
        ChapterBean chapterBean = new ChapterBean(courseBean,chapterName);
        ChapterKnowledgeBean chapterKnowledgeBean = new ChapterKnowledgeBean(chapterBean,knowledgeName);
       chapterKnowledgeDao.addChapterKnowledge(chapterKnowledgeBean);
        response.sendRedirect("/ChapterPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
