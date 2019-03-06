package test;

import Bean.ChapterBean;
import Bean.CourseBean;
import Dao.ChapterDao;
import Dao.CourseDao;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class ChapterDaoTest {
    @Test
    public void searchChapter() throws Exception {
        ChapterDao chapterDao =new ChapterDao();
        ArrayList<ChapterBean> a= chapterDao.searchChapter("english","aa");
        System.out.println(a.size());
        System.out.println(a.get(0).getUnitName());
        System.out.println(a.get(1).getUnitName());
    }

    @Test
    public void deleteChapter() throws Exception {
        ChapterDao chapterDao =new ChapterDao();
        chapterDao.deleteChapter(1);
    }

    @Test
    public void addChapter() throws Exception {
        ChapterDao chapterDao =new ChapterDao();
        CourseDao courseDao =new CourseDao();
        CourseBean courseBean = courseDao.searchCourse(6);
        System.out.println(chapterDao.addChapter(new ChapterBean(courseBean,"asd")));


    }

    @Test
    public void idChapter() throws Exception {
        ChapterDao chapterDao =new ChapterDao();
        System.out.print(chapterDao.idChapter("math","nsi4","ss"));
    }

}