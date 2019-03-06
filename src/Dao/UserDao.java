package Dao;

import Bean.UserBean;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/27.
 */
public class UserDao extends ConnectionDao implements DaoInterface.UserDao {

    @Override
    public UserBean searchUser(int idUser) {
        Connection connection = connection();
        String sql = "select * from user where id =" + idUser;
        Statement st = null;
        UserBean user = new UserBean();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            connectionExe(rs, "user");
            while (rs.next()) {
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setEmail(rs.getString(4));
            }
            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean deleteUser(int idUser) {
        Connection connection = connection();
        if (searchUser(idUser).getUsername() == null) {
            return false;
        }
        String sql = "delete from user where id =" + idUser;
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
    public boolean addUser(UserBean user) {
        Connection connection = connection();
        if (idUser(user.getUsername()) != 0) {
            return false;
        }

        String sql = "insert into user(id ,username,password,email) values(?,?,?,?)";
        try {
            PreparedStatement pre = (PreparedStatement) connection.prepareStatement(sql);
            pre.setInt(1, 0);
            pre.setString(2, user.getUsername());
            pre.setString(3, user.getPassword());
            pre.setString(4, user.getEmail());
            pre.executeUpdate();
            connectionClose(connection, pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public int idUser(String name) {
        Connection connection = connection();
        String sql = "select id,userName from user where username ='" + name + "'";
        return connectionCount(connection, sql, "user");
    }

    public ArrayList<Integer> idUserStr(String nameStr) {
        Connection connection = connection();
        String sql = "select id,userName from user ";
        Statement statement = null;
        ResultSet rs = null;
        ArrayList<Integer> count = new ArrayList<Integer>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(2).contains(nameStr)) {
                    count.add(Integer.parseInt(rs.getString(1)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connectionClose(connection, statement);
        return count;
    }
}
