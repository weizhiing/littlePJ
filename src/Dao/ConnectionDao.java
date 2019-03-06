package Dao;

import java.sql.*;

/**
 * Created by your dad on 2018/7/27.
 */
public class ConnectionDao {

    public static Connection connection(){
        String URL = "jdbc:mysql://localhost:3306/PJ";
        String NAME = "root";
        String PASSWORD = "password";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("未能成功加载驱动程序，请检查是否导入驱动程序！");

            e.printStackTrace();
        }
        java.sql.Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
      //   System.out.println("获取数据库连接成功！");
        } catch (SQLException e) {
            System.out.println("获取数据库连接失败！");
            //添加一个println，如果连接失败，检查连接字符串或者登录名以及密码是否错误
            e.printStackTrace();
        }
        return  connection;
    }

    public static void connectionClose(Connection connection, Statement statement){
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("关闭失败");
        }

    }

    public static int connectionCount(Connection connection,String sql,String dateBase)  {
        Statement statement =null;
        ResultSet  rs = null;
        int count =0;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
          connectionExe(rs,dateBase);
            while (rs.next()) {
                count = Integer.parseInt(rs.getString(1) );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connectionClose(connection, statement);
        return count;
    }
    public static void connectionExe(ResultSet rs,String dateBase)  {
        int rows;
        try {
            rs.last();
            rows = rs.getRow();
            if(rows!=1&&rows!=0){
                throw  new Exception(dateBase+"数据库：出现名称重复错误");
            }
            rs.beforeFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
