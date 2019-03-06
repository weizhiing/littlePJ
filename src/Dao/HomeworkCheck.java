package Dao;

import Bean.HomeworkCheckBean;
import DaoInterface.homeworkDao.HomeworkCheckDao;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class HomeworkCheck extends ConnectionDao implements HomeworkCheckDao {
    @Override
    public ArrayList<HomeworkCheckBean> searchHomeworkCheck(String courseName, String teacherName, String studentName) {
        Connection connection = connection();
        String sql = "select * from homeworkCheck ";
        Statement st = null;
        ArrayList<HomeworkCheckBean> arrayList = new ArrayList<HomeworkCheckBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                HomeworkCheckBean homeworkCheckBeanBean = searchHomeworkCheck(Integer.parseInt(rs.getString(1)));
                if (homeworkCheckBeanBean.getHomeworkComplete().getHomework().getCourse().getCourse().equals(courseName) &&
                        homeworkCheckBeanBean.getHomeworkComplete().getHomework().getCourse().getTeacher().getUsername().equals(teacherName)
                        &&homeworkCheckBeanBean.getHomeworkComplete().getStudent().getUsername().equals(studentName)) {
                    arrayList.add(homeworkCheckBeanBean);
                }
            }
            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public HomeworkCheckBean searchHomeworkCheck(int isHomeworkCheck) {
        Connection connection = connection();
        String sql = "select * from homeworkCheck where id =" + isHomeworkCheck;
        Statement st = null;
        HomeworkCheckBean homeworkCheckBean = new HomeworkCheckBean();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            connectionExe(rs, "homeworkCheck");
            while (rs.next()) {
                HomeworkDoDao homeworkDoDao = new HomeworkDoDao();
                homeworkCheckBean.setHomeworkComplete(homeworkDoDao.searchHomeworkDo(Integer.parseInt(rs.getString(2))));
                homeworkCheckBean.setScore(Integer.parseInt(rs.getString(3)));

            }
            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return homeworkCheckBean;

    }

    @Override
    public boolean addHomeworkCheck(HomeworkCheckBean homeworkCheck) {
        Connection connection = connection();
        if (idHomeworkCheck(homeworkCheck.getHomeworkComplete().getHomework().getCourse().getCourse(),
                homeworkCheck.getHomeworkComplete().getHomework().getCourse().getTeacher().getUsername()
                , homeworkCheck.getHomeworkComplete().getHomework().getHomeworkName(),
                homeworkCheck.getHomeworkComplete().getStudent().getUsername()) != 0) {
            return false;
        }
        String sql = "insert into homeworkCheck(id ,homeworkdoid,score) values(?,?,?)";
        try {
            PreparedStatement pre = (PreparedStatement) connection.prepareStatement(sql);
            pre.setInt(1, 0);
            HomeworkDoDao homeworkDoDao = new HomeworkDoDao();
            pre.setInt(2, homeworkDoDao.idHomeworkDo(homeworkCheck.getHomeworkComplete().getHomework().getCourse().getCourse(),
                    homeworkCheck.getHomeworkComplete().getHomework().getCourse().getTeacher().getUsername(),
                    homeworkCheck.getHomeworkComplete().getHomework().getHomeworkName(),
                    homeworkCheck.getHomeworkComplete().getStudent().getUsername()));

            pre.setInt(3, homeworkCheck.getScore());

            pre.executeUpdate();
            connectionClose(connection, pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public int idHomeworkCheck(String courseName, String teacherName, String homeworkName, String studentName) {
        Connection connection = connection();

        HomeworkDoDao homeworkDoDao = new HomeworkDoDao();
        String sql = "select * from homeworkCheck where homeworkDoId ="
                + homeworkDoDao.idHomeworkDo(courseName, teacherName, homeworkName, studentName);

        return connectionCount(connection, sql, "homeworkCheck");
    }
}
