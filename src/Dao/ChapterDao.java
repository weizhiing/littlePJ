package Dao;

import Bean.ChapterBean;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class ChapterDao extends ConnectionDao implements DaoInterface.chapterDao.ChapterDao {

    @Override
    public ArrayList<ChapterBean> searchChapter(String courseName, String teacherName) {
        Connection connection = connection();
        CourseDao courseDao = new CourseDao();
        String sql = "select * from chapter where courseId =" + courseDao.idCourse(courseName, teacherName);
        Statement st = null;
        ArrayList<ChapterBean> arrayList = new ArrayList<ChapterBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                ChapterBean ChapterBean = searchChapter(Integer.parseInt(rs.getString(1)));
                arrayList.add(ChapterBean);
            }
            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public ChapterBean searchChapter(int idChapter) {
        Connection connection = connection();
        String sql = "select * from Chapter where id =" + idChapter;
        Statement st = null;
        ChapterBean chapterBean = new ChapterBean();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            connectionExe(rs, "chapter");
            while (rs.next()) {
                CourseDao courseDao = new CourseDao();
                chapterBean.setCourse(courseDao.searchCourse(Integer.parseInt(rs.getString(2))));
                chapterBean.setUnitName(rs.getString(3));
            }
            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return chapterBean;
    }

    @Override
    public boolean deleteChapter(int idChapter) {
        Connection connection = connection();
        if (searchChapter(idChapter).getCourse() == null) {
            return false;
        }
        String sql = "delete * FROM chapterKnowledge where chapterId=" + idChapter;
        ArrayList<Integer> a = new ArrayList<Integer>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                a.add(Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "delete from chapter where id =" + idChapter;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "delete from chapterknowledge where chapterid=" + idChapter;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i=0;i<a.size();i++){
            sql = "delete from chapterKnowledgeContent where knowledgeId=" +a.get(i);
            try {
                statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        connectionClose(connection, statement);
        return true;
    }

    @Override
    public boolean addChapter(ChapterBean chapter) {
        Connection connection = connection();
        if (idChapter(chapter.getCourse().getCourse(), chapter.getCourse().getTeacher().getUsername()
                , chapter.getUnitName()) != 0) {
            return false;
        }
        String sql = "insert into Chapter(id ,courseId,chapterName) values(?,?,?)";
        try {
            PreparedStatement pre = (PreparedStatement) connection.prepareStatement(sql);
            pre.setInt(1, 0);
            CourseDao courseDao = new CourseDao();
            pre.setInt(2, courseDao.idCourse(chapter.getCourse().getCourse(), chapter.getCourse().getTeacher().getUsername()));
            pre.setString(3, chapter.getUnitName());
            pre.executeUpdate();
            connectionClose(connection, pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public int idChapter(String courseName, String teacherName, String chapterName) {
        Connection connection = connection();

        CourseDao courseDao = new CourseDao();
        String sql = "select * from Chapter where courseId ='" + courseDao.idCourse(courseName, teacherName)
                + "' and chapterName ='" + chapterName + "'";

        return connectionCount(connection, sql, "chapter");
    }
}
