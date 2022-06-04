package com.txxia.game.fivechess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
    //要连接的数据库url,注意：此处连接的应该是服务器上的MySQl的地址
    private final static String url = "jdbc:mysql://180.76.227.152:3306/wuziqi";
    //连接数据库使用的用户名
    private final static String userName = "root";
    //连接的数据库时使用的密码
    private final static String password = "123456";
    public Connection connection=null;
    PreparedStatement ps=null;
    ResultSet rs=null;

//    public DBUtils() {
//        try {
//            //1、加载驱动
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            System.out.println("驱动加载成功！！！");
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public ResultSet query(String sql){
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("驱动加载成功！！！");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            //2、获取与数据库的连接
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("连接数据库成功！！！");
            //3.sql语句
            //4.获取用于向数据库发送sql语句的ps
            ps=connection.prepareStatement(sql);
            rs = ps.executeQuery();
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(connection!=null){
                try {

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return rs;
    }


    public void update(String sql){
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("驱动加载成功！！！");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            //2、获取与数据库的连接
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("连接数据库成功！！");
            //3.sql语句
            //4.获取用于向数据库发送sql语句的ps
            ps=connection.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        return (Connection) DriverManager.getConnection(url,userName,password);
    }
    public static void closeConnection(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}