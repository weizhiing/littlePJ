package Dao;

import Bean.DiscussReplyBean;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class DiscussReply extends ConnectionDao implements DaoInterface.discussDao.DiscussReply {

    @Override
    public ArrayList<DiscussReplyBean> searchDiscussReply(String courseName, String teacherName, String studentName, String title) {
        Connection connection =connection();
        DiscussDao discussDao =new DiscussDao();
        String sql = "select * from discussReply where discussId ="+discussDao.idDiscuss(courseName,teacherName,studentName,title);
        Statement st = null;
        ArrayList<DiscussReplyBean> arrayList = new ArrayList<DiscussReplyBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                DiscussReplyBean discussReply = searchDiscussReply(Integer.parseInt(rs.getString(1)));
                arrayList.add(discussReply);
            }
            connectionClose(connection,st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public DiscussReplyBean searchDiscussReply(int idDiscussReply){
        Connection connection =connection();
        String sql = "select * from DiscussReply where id ="+idDiscussReply;
        Statement st = null;
        DiscussReplyBean discussReply = new DiscussReplyBean();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            connectionExe(rs,"DiscussReply");
            while (rs.next()) {
                DiscussDao discussDao =new DiscussDao();
                discussReply.setDiscuss(discussDao.searchDiscuss(Integer.parseInt(rs.getString(2))));

                UserDao user= new UserDao();
                discussReply.setStudent(user.searchUser(Integer.parseInt(rs.getString(3))));
                discussReply.setContent(rs.getString(4));

            }
            connectionClose(connection,st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return discussReply;
    }

    @Override
    public boolean deleteDiscussReply(int idDiscussReply) {
        Connection connection =connection();
        if(searchDiscussReply(idDiscussReply).getDiscuss()==null){
            return false;
        }
        String sql = "delete from discussReply where id =" +idDiscussReply;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connectionClose(connection,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean addDiscussReply(DiscussReplyBean discussReply) {
        Connection connection =connection();
        if(idDiscussReply(discussReply.getDiscuss().getCourse().getCourse(),
                discussReply.getDiscuss().getCourse().getTeacher().getUsername(),
                discussReply.getDiscuss().getStudent().getUsername(),
                discussReply.getDiscuss().getTitle(),
                discussReply.getStudent().getUsername(),
                discussReply.getContent())!=0){
            return false;
        }
        String sql = "insert into discussReply(id ,DiscussId,studentId,content) values(?,?,?,?)";
        try {
            PreparedStatement pre = (PreparedStatement)connection.prepareStatement(sql);
            pre.setInt(1,0);
            DiscussDao discussDao =new DiscussDao();
            pre.setInt(2,discussDao.idDiscuss(discussReply.getDiscuss().getCourse().getCourse(),
                    discussReply.getDiscuss().getCourse().getTeacher().getUsername(),
                    discussReply.getDiscuss().getStudent().getUsername(),
                    discussReply.getDiscuss().getTitle()));
            UserDao userDao =new UserDao();
            pre.setInt(3,userDao.idUser(discussReply.getStudent().getUsername()));
            pre.setString(4,discussReply.getContent());
            pre.executeUpdate();
            connectionClose(connection,pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public int idDiscussReply(String courseName, String teacherName, String studentName1,String title, String studentName2, String content) {
        Connection connection = connection();
        UserDao userDao = new UserDao();
        DiscussDao discussDao = new DiscussDao();
        String sql = "select * from DiscussReply where discussId ='" +discussDao.idDiscuss(courseName,teacherName,studentName1,title)
                +"' and studentId ='"  +userDao.idUser(studentName2)+"' and content ='"+content+"'";

        return connectionCount(connection,sql,"courseSelect");
    }
}
