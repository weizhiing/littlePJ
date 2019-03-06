package Servlet.Course.ChapterRelation;

import Bean.ChapterKnowledgeContentBean;
import Bean.CourseBean;
import Bean.UserBean;
import Dao.ChapterContent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/26.
 */
@WebServlet(name = "KnowledgeContentPageServlet",value = "/KnowledgeContentPageServlet")
public class KnowledgeContentPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        CourseBean courseBean = (CourseBean) session.getAttribute("course");
        String chapterName = request.getParameter("chapterName");
        String knowledgeName = request.getParameter("knowledgeName");
        if(chapterName!=null&&knowledgeName!=null){
        session.setAttribute("chapterName",chapterName);
        session.setAttribute("knowledgeName",knowledgeName);
        }
        ChapterContent chapterContent =new ChapterContent();
        ArrayList<ChapterKnowledgeContentBean> chapterKnowledgeContent =
                chapterContent.searchChapterKnowledgeContent(courseBean.getCourse(),courseBean.getTeacher().getUsername(),
                        chapterName,knowledgeName);
        System.out.println(chapterKnowledgeContent.size());
        request.setAttribute("chapterKnowledgeContent",chapterKnowledgeContent);
        request.getRequestDispatcher("/knowledgePage.jsp").forward(request,response);

    }
}
