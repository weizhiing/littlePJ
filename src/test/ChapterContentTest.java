package test;

import Bean.ChapterKnowledgeBean;
import Bean.ChapterKnowledgeContentBean;
import Bean.UrlBean;
import Dao.ChapterContent;
import Dao.ChapterKnowledgeDao;
import Dao.UrlDao;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class ChapterContentTest {
    @Test
    public void searchChapterKnowledgeContent() throws Exception {
        ChapterContent chapterContent = new ChapterContent();
        ArrayList<ChapterKnowledgeContentBean> a = chapterContent.searchChapterKnowledgeContent("english","aa","asd","buhaa");
 System.out.println(a.size());
        System.out.println(a.get(0).getUrl().getUrl());
        System.out.println(a.get(1).getUrl().getUrl());
    }

    @Test
    public void deleteChapterKnowledgeContent() throws Exception {
        ChapterContent chapterContent = new ChapterContent();
        chapterContent.deleteChapterKnowledgeContent(2);
    }

    @Test
    public void addChapterKnowledgeContent() throws Exception {
        ChapterContent chapterContent = new ChapterContent();
        ChapterKnowledgeDao chapterKnowledgeDao=new ChapterKnowledgeDao();
        ChapterKnowledgeBean chapterKnowledgeBean = chapterKnowledgeDao.searchChapterKnowledge(3);
        UrlDao urlDao =new UrlDao();
        UrlBean userBean =urlDao.searchUrl(3);
        System.out.println(chapterContent.addChapterKnowledgeContent(new ChapterKnowledgeContentBean(chapterKnowledgeBean,userBean)));
    }

    @Test
    public void idChapterKnowledgeContent() throws Exception {
        ChapterContent chapterContent = new ChapterContent();
        System.out.print(chapterContent.idChapterKnowledgeContent("english","aa","asd","buhaa","ss"));

    }

}