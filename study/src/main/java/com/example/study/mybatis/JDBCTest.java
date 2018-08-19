package com.example.study.mybatis;

import java.sql.*;

public class JDBCTest {

    public static void main(String[] args){
        Connection  conn = null;
        PreparedStatement ps = null;
        try {
            //1:j加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2：连接数据库
            conn = getCollection();
            //3、获得执行sql语句
            String sql = "SELECT * FROM changjiang_account";
            //4、执行SQL语句
            ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            //5、处理执行后的结果
            String result = doResultSet(resultSet);
            System.out.println(result);
        }catch (Exception e){
            if (ps != null){
                try {
                    ps.close();
                }catch (Exception e1){

                }

            }
            if (conn != null){
                try {
                    conn.close();
                }catch (Exception e2){

                }
            }
        }

    }
    public static Connection getCollection(){
        try {
            String url = "jdbc:mysql://47.95.243.131:3306/stream?characterEncoding=utf8";
            String name = "fighting";//将要连接数据库的账户
            String password = "albb!123";//将要连接数据库的密码
            return DriverManager.getConnection(url, name, password);
        }catch (SQLException e){
            System.out.println("获取Connection失败");
        }
        return null;

    }
    public static String doResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null){
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (resultSet.next()){
            long id = resultSet.getLong("id");
            stringBuffer.append(id + " ");
        }
        return stringBuffer.toString();

    }
}
