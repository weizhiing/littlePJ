package test;

import Bean.CourseBean;
import Bean.SourceBean;
import Dao.CourseDao;
import Dao.SourceDao;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class SourceDaoTest {
    @Test
    public void searchSource() throws Exception {
        SourceDao sourceDao =new SourceDao();
        ArrayList<SourceBean> a=sourceDao.searchSource("math","zx");
        System.out.println(a.size());
        System.out.println(a.get(0).getSourceName());
        System.out.println(a.get(1).getSourceName());
    }

    @Test
    public void deleteSource() throws Exception {
        SourceDao sourceDao =new SourceDao();
        System.out.print(sourceDao.deleteSource(1));
    }

    @Test
    public void addSource() throws Exception {
        SourceDao sourceDao =new SourceDao();

        CourseDao courseDao =new CourseDao();
        CourseBean courseBean = courseDao.searchCourse(9);


        System.out.println(sourceDao.addSource(new SourceBean(courseBean,"攻读","ss","ss")));

    }

    @Test
    public void idSource() throws Exception {
        SourceDao sourceDao =new SourceDao();
        System.out.println(sourceDao.idSource("math","nsi4","树"));
    }

}