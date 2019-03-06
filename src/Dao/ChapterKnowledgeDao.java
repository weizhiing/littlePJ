package Dao;

import Bean.ChapterKnowledgeBean;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class ChapterKnowledgeDao extends ConnectionDao implements DaoInterface.chapterDao.ChapterKnowledgeDao {
    @Override
    public ArrayList<ChapterKnowledgeBean> searchChapterKnowledge(String courseName, String teacherName, String chapterName) {
        Connection connection = connection();
        ChapterDao chapterDao =new ChapterDao();
        String sql = "select * from chapterknowledge where ChapterId =" + chapterDao.idChapter(courseName, teacherName,chapterName);
        Statement st = null;
        ArrayList<ChapterKnowledgeBean> arrayList = new ArrayList<ChapterKnowledgeBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                ChapterKnowledgeBean ChapterKnowledgeBean = searchChapterKnowledge(Integer.parseInt(rs.getString(1)));
                arrayList.add(ChapterKnowledgeBean);
            }
            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }
    public ChapterKnowledgeBean searchChapterKnowledge(int idChapterKnowledge) {
        Connection connection = connection();
        String sql = "select * from ChapterKnowledge where id =" + idChapterKnowledge;
        Statement st = null;
        ChapterKnowledgeBean chapterKnowledgeBean = new ChapterKnowledgeBean();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            connectionExe(rs, "Knowledge");
            while (rs.next()) {
                ChapterDao ChapterDao = new ChapterDao();
                chapterKnowledgeBean.setChapter(ChapterDao.searchChapter(Integer.parseInt(rs.getString(2))));
                chapterKnowledgeBean.setKnowledgeName(rs.getString(3));
            }
            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return chapterKnowledgeBean;
    }
    @Override
    public boolean deleteChapterKnowledge(int idChapterKnowledge) {
        Connection connection = connection();
        if (searchChapterKnowledge(idChapterKnowledge).getChapter() == null) {
            return false;
        }
        String sql = "delete from chapterKnowledge where id =" + idChapterKnowledge;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
         sql = "delete from chapterKnowledgeContent where knowledgeid =" + idChapterKnowledge;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }connectionClose(connection, statement);
        return true;
    }

    @Override
    public boolean addChapterKnowledge(ChapterKnowledgeBean chapterKnowledge) {
        Connection connection = connection();
        if (idChapterKnowledge(chapterKnowledge.getChapter().getCourse().getCourse(), chapterKnowledge.getChapter().getCourse().getTeacher().getUsername()
                , chapterKnowledge.getChapter().getUnitName(),chapterKnowledge.getChapter().getUnitName()) != 0) {
            return false;
        }
        String sql = "insert into ChapterKnowledge (id ,chapterId,knowledge) values(?,?,?)";
        try {
            PreparedStatement pre = (PreparedStatement) connection.prepareStatement(sql);
            pre.setInt(1, 0);
            ChapterDao chapterDao =new ChapterDao();
            pre.setInt(2, chapterDao.idChapter(chapterKnowledge.getChapter().getCourse().getCourse(), chapterKnowledge.getChapter().getCourse().getTeacher().getUsername(),chapterKnowledge.getChapter().getUnitName()));
            pre.setString(3, chapterKnowledge.getKnowledgeName());
            pre.executeUpdate();
            connectionClose(connection, pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public int idChapterKnowledge(String courseName, String teacherName, String chapterName, String knowledgeName) {
        Connection connection = connection();

        ChapterDao chapterDao=new ChapterDao();
        String sql = "select * from ChapterKnowledge where chapterId ='" + chapterDao.idChapter(courseName, teacherName,chapterName)
                + "' and knowledge ='" + knowledgeName + "'";

        return connectionCount(connection, sql, "chapterKnowledge");

    }
}
