package test;

import Bean.DiscussBean;
import Bean.DiscussReplyBean;
import Bean.UserBean;
import Dao.DiscussDao;
import Dao.DiscussReply;
import Dao.UserDao;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class DiscussReplyTest {
    @Test
    public void searchDiscussReply() throws Exception {
        DiscussReply discussReply = new DiscussReply();
        ArrayList<DiscussReplyBean> a = discussReply.searchDiscussReply("math","nsi4","aa","ss");
        System.out.println(a.size());
        System.out.println(a.get(0).getContent());
        System.out.println(a.get(1).getContent());
    }

    @Test
    public void deleteDiscussReply() throws Exception {
        Dao.DiscussReply discussReply =new Dao.DiscussReply();
        System.out.print(discussReply.deleteDiscussReply(3));
    }

    @Test
    public void addDiscussReply() throws Exception {
        Dao.DiscussReply discussReply =new Dao.DiscussReply();
        DiscussDao discussDao=new DiscussDao();
        UserDao userDao =new UserDao();
        DiscussBean discussBean = discussDao.searchDiscuss(3);
        UserBean userBean =userDao.searchUser(1);
        UserBean userBean2 =userDao.searchUser(2);
        System.out.println(discussReply.addDiscussReply(new DiscussReplyBean(discussBean,userBean,"ss")));
        System.out.println(discussReply.addDiscussReply(new DiscussReplyBean(discussBean,userBean2,"ss")));

    }

    @Test
    public void idDiscussReply() throws Exception {
        Dao.DiscussReply discussReply =new Dao.DiscussReply();
        System.out.println(discussReply.idDiscussReply("math","nsi4","aa","ss","nsi4","ss"));
    }

}