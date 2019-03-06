package Dao;

import Bean.ChapterKnowledgeBean;
import Bean.ChapterKnowledgeContentBean;
import DaoInterface.chapterDao.ChapterKnowledgeContentDao;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class ChapterContent extends ConnectionDao implements ChapterKnowledgeContentDao {
    @Override
    public ArrayList<ChapterKnowledgeContentBean> searchChapterKnowledgeContent(String courseName, String teacherName, String chapterName, String knowledgeName) {
        Connection connection = connection();
        ChapterKnowledgeDao chapterKnowledgeDao =new ChapterKnowledgeDao();
        String sql = "select * from chapterknowledgeContent where knowledgeId =" + chapterKnowledgeDao.idChapterKnowledge(courseName, teacherName,chapterName,knowledgeName);
        Statement st = null;
        ArrayList<ChapterKnowledgeContentBean> arrayList = new ArrayList<ChapterKnowledgeContentBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                ChapterKnowledgeContentBean ChapterKnowledgeContentBean = searchChapterKnowledgeContent(Integer.parseInt(rs.getString(1)));
                arrayList.add(ChapterKnowledgeContentBean);
            }
            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }
    public ChapterKnowledgeContentBean searchChapterKnowledgeContent(int idChapterKnowledgeContent) {
        Connection connection = connection();
        String sql = "select * from ChapterKnowledgeContent where id =" + idChapterKnowledgeContent;
        Statement st = null;
        ChapterKnowledgeContentBean chapterKnowledgeContentBean = new ChapterKnowledgeContentBean();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            connectionExe(rs, "content");
            while (rs.next()) {
                ChapterKnowledgeDao ChapterKnowledgeDao = new ChapterKnowledgeDao();
                chapterKnowledgeContentBean.setKnowledge(ChapterKnowledgeDao.searchChapterKnowledge(Integer.parseInt(rs.getString(2))));
               UrlDao urlDao=new UrlDao();
                chapterKnowledgeContentBean.setUrl(urlDao.searchUrl(Integer.parseInt(rs.getString(3))));
            }
            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return chapterKnowledgeContentBean;
    }
    @Override
    public boolean deleteChapterKnowledgeContent(int idChapterKnowledgeContent) {
        Connection connection = connection();
        if (searchChapterKnowledgeContent(idChapterKnowledgeContent).getKnowledge() == null) {
            return false;
        }
        String sql = "delete from chapterKnowledgeContent where id =" + idChapterKnowledgeContent;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connectionClose(connection, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean addChapterKnowledgeContent(ChapterKnowledgeContentBean chapterKnowledgeContent) {
        Connection connection = connection();
       if (idChapterKnowledgeContent(chapterKnowledgeContent.getKnowledge().getChapter().getCourse().getCourse(), chapterKnowledgeContent.getKnowledge().getChapter().getCourse().getTeacher().getUsername()
                , chapterKnowledgeContent.getKnowledge().getChapter().getUnitName(),chapterKnowledgeContent.getKnowledge().getKnowledgeName()
             ,chapterKnowledgeContent.getUrl().getUrl()) != 0) {
            return false;
        }
        String sql = "insert into ChapterKnowledgeContent (id ,knowledgeId,UrliD) values(?,?,?)";
        try {
            PreparedStatement pre = (PreparedStatement) connection.prepareStatement(sql);
            pre.setInt(1, 0);
            ChapterKnowledgeDao chapterKnowledgeDao =new ChapterKnowledgeDao();
            pre.setInt(2, chapterKnowledgeDao.idChapterKnowledge(chapterKnowledgeContent.getKnowledge().getChapter().getCourse().getCourse(), chapterKnowledgeContent.getKnowledge().getChapter().getCourse().getTeacher().getUsername(),chapterKnowledgeContent.getKnowledge().getChapter().getUnitName(),chapterKnowledgeContent.getKnowledge().getKnowledgeName()));
          UrlDao urlDao =new UrlDao();
            pre.setInt(3,urlDao.idUrl(chapterKnowledgeContent.getUrl().getUrl()));
            pre.executeUpdate();
            connectionClose(connection, pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public int idChapterKnowledgeContent(String courseName, String teacherName, String chapterName, String knowledgeName, String url) {
        Connection connection = connection();

        ChapterKnowledgeDao chapterKnowledgeDao=new ChapterKnowledgeDao();
        UrlDao urlDao=new UrlDao();
        String sql = "select * from ChapterKnowledgeContent where knowledgeId ='" + chapterKnowledgeDao.idChapterKnowledge(courseName, teacherName,chapterName,knowledgeName)
                + "' and urlId ='" + urlDao.idUrl(url) + "'";

        return connectionCount(connection, sql, "chapterKnowledgeContent");

    }
}
