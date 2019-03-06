package Dao;

import Bean.UrlBean;

import java.sql.*;

/**
 * Created by your dad on 2018/7/28.
 */
public class UrlDao extends ConnectionDao implements DaoInterface.urlDao {
    @Override
    public boolean addUrl(UrlBean url) {
        Connection connection =connection();
    /*    if (idUrl(url.getUrl()) != 0) {
            return false;
        }*/
        String sql = "insert into url(id ,url) values(?,?)";
        try {
            PreparedStatement pre = (PreparedStatement)connection.prepareStatement(sql);
            pre.setInt(1,0);
            pre.setString(2,url.getUrl());
            pre.executeUpdate();
            connectionClose(connection,pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean deleteUrl(int idUrl) {
        Connection connection =connection();
        if(searchUrl(idUrl).getUrl()==null){
            return  false;
        }
        String sql = "delete from url where id =" +idUrl;
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
    public UrlBean searchUrl(int idUrl) {
        Connection connection =connection();
        String sql = "select * from url where id ="+idUrl;
        Statement st = null;
        UrlBean url = new UrlBean();


        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            connectionExe(rs,"url");
            while (rs.next()) {
                url.setUrl(rs.getString(2));

            }
            connectionClose(connection,st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    @Override
    public boolean modifyUrl(int idUrl, String url) {
        return false;
    }

    @Override
    public int idUrl(String url) {
        Connection connection = connection();
        String sql = "select * from url where url ='" + url+"'";
        return connectionCount(connection,sql,"url");
    }
}
