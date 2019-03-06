package test;

import Bean.ChapterBean;
import Bean.ChapterKnowledgeBean;
import Dao.ChapterDao;
import Dao.ChapterKnowledgeDao;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class ChapterKnowledgeDaoTest {
    @Test
    public void searchChapterKnowledge() throws Exception {
        ChapterKnowledgeDao chapterKnowledgeDao =new ChapterKnowledgeDao();
        ArrayList<ChapterKnowledgeBean> a= chapterKnowledgeDao.searchChapterKnowledge("english","aa","asd");
        System.out.println(a.size());
        System.out.println(a.get(0).getKnowledgeName());
        System.out.println(a.get(1).getKnowledgeName());

    }

    @Test
    public void deleteChapterKnowledge() throws Exception {
        ChapterKnowledgeDao chapterKnowledgeDao =new ChapterKnowledgeDao();
       System.out.print(chapterKnowledgeDao.deleteChapterKnowledge(1));
    }

    @Test
    public void addChapterKnowledge() throws Exception {
        ChapterKnowledgeDao chapterKnowledgeDao =new ChapterKnowledgeDao();
        ChapterDao chapterDao=new ChapterDao();
        ChapterBean chapterBean=chapterDao.searchChapter(3);
        System.out.println(chapterKnowledgeDao.addChapterKnowledge(new ChapterKnowledgeBean(chapterBean,"你好啊")));

    }

    @Test
    public void idChapterKnowledge() throws Exception {
        ChapterKnowledgeDao chapterKnowledgeDao =new ChapterKnowledgeDao();
        System.out.println(chapterKnowledgeDao.idChapterKnowledge("english","aa","aa","ss"));
    }

}