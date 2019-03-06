package test;

import Bean.HomeworkBean;
import Bean.HomeworkDoBean;
import Bean.UserBean;
import Dao.HomeworkDao;
import Dao.HomeworkDoDao;
import Dao.UserDao;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class HomeworkDoDaoTest {
    @Test
    public void searchHomeworkDo() throws Exception {
        HomeworkDoDao homeworkDoDao = new HomeworkDoDao();
        ArrayList<HomeworkDoBean> a = homeworkDoDao.searchHomeworkDo("math","nsi4","aa");
        System.out.println(a.size());
        System.out.println(a.get(0).getContent());
        System.out.println(a.get(1).getContent());
    }

    @Test
    public void addHomeworkDo() throws Exception {
        HomeworkDoDao homeworkDoDao = new HomeworkDoDao();
        UserDao userDao=new UserDao();
        UserBean userBean =userDao.searchUser(2);
        HomeworkDao homeworkDao =new HomeworkDao();
        HomeworkBean homeworkBean =homeworkDao.searchHomeworkOne(3);

        System.out.println(homeworkDoDao.addHomeworkDo(new HomeworkDoBean(homeworkBean,userBean,"asdsa")));
    }

    @Test
    public void idHomeworkDo() throws Exception {
        HomeworkDoDao homeworkDoDao = new HomeworkDoDao();
        System.out.println(homeworkDoDao.idHomeworkDo("math","nsi4","离散","aa"));
    }

}