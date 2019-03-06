package test;

import Bean.UrlBean;
import Dao.UrlDao;
import org.junit.Test;

/**
 * Created by your dad on 2018/7/28.
 */
public class urlDaoTest {
    @Test
    public void addUrl() throws Exception {
        UrlDao urlDao =new UrlDao();
        System.out.println(urlDao.addUrl(new UrlBean("assadsd")));

    }

    @Test
    public void deleteUrl() throws Exception {
        UrlDao urlDao =new UrlDao();
        System.out.println(urlDao.deleteUrl(2));
        System.out.println(urlDao.deleteUrl(2));
    }

    @Test
    public void searchUrl() throws Exception {
        UrlDao urlDao =new UrlDao();
        System.out.println(urlDao.searchUrl(1).getUrl());
    }

    @Test
    public void modifyUrl() throws Exception {

    }

    @Test
    public void idUrl() throws Exception {
        UrlDao urlDao =new UrlDao();
        System.out.print(urlDao.idUrl("../../桌面/卓越软件/PJ/web/WEB-INF/upload\\a5145249-5f58-45b7-9b0e-9ffe683a4e98_013267145X.jpg"));
        System.out.print(urlDao.idUrl("ss"));
    }

}