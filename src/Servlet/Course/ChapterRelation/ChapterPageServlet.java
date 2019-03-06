package Servlet.Course.ChapterRelation;

import Bean.ChapterBean;
import Bean.ChapterKnowledgeBean;
import Bean.CourseBean;
import Bean.UserBean;
import Dao.ChapterDao;
import Dao.ChapterKnowledgeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by your dad on 2018/7/25.
 */
@WebServlet(name = "ChapterPageServlet",value = "/ChapterPageServlet")
public class ChapterPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        CourseBean courseBean =(CourseBean)session.getAttribute("course");
        ChapterDao chapterDao =new ChapterDao();
        ArrayList<ChapterBean> chapter = chapterDao.searchChapter(courseBean.getCourse(),courseBean.getTeacher().getUsername());
        HashMap<String,ArrayList<ChapterKnowledgeBean>>
                chapterKnowledgeList = new HashMap<String,ArrayList<ChapterKnowledgeBean>>();
        ChapterKnowledgeDao chapterKnowledgeDao= new ChapterKnowledgeDao();
        for(ChapterBean chapterBean:chapter){
           ArrayList<ChapterKnowledgeBean>  chapterKnowledgeBeen= chapterKnowledgeDao.searchChapterKnowledge(courseBean.getCourse(),courseBean.getTeacher().getUsername(),chapterBean.getUnitName());
           chapterKnowledgeList.put(chapterBean.getUnitName(),chapterKnowledgeBeen);
        }

        request.setAttribute("chapter",chapter );
        request.setAttribute("chapterKnowledge",chapterKnowledgeList);
        request.getRequestDispatcher("/chapterPage.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request
        ,response);
    }
}
