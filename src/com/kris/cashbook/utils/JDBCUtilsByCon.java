package com.kris.cashbook.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 获取数据库连接的工具类
 * 实现DBCP连接池
 *
 * @author: Kris
 * Date: 2020/2/14 12:44
 */
public class JDBCUtilsByCon {
    private static Connection con;
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;

    // 创建BasicDataSource对象
    private static BasicDataSource dataSource = new BasicDataSource();

    // 静态代码块，实现必要的参数配置
    static {
        try {

            loadConfig();
            Class.forName(driverClass);
            con = DriverManager.getConnection(url, username, password);
//            System.out.println("数据库连接成功！");
        } catch (Exception e) {
            throw new RuntimeException("数据库连接失败！");
        }
    }

    /**
     * 从配置文件database.properties中加载数据库连接信息
     * @throws IOException
     */
    private static void loadConfig() throws IOException {
        InputStream in = JDBCUtilsByCon.class.getClassLoader().getResourceAsStream("database.properties");
        Properties properties = new Properties();
        properties.load(in);
        driverClass = properties.getProperty("driverClass");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    /**
     * 用于外部类使用dataSource连接数据库
     * @return dataSource
     */
    public static Connection getCon(){
        return con;
    }
}
