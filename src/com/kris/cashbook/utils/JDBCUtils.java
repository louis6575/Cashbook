package com.kris.cashbook.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 获取数据库连接的工具类
 * 实现DBCP连接池
 *
 * @author: Kris
 * Date: 2020/2/14 12:44
 */
public class JDBCUtils {
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;
    private static int maxTotal;  // 连接池最大激活连接数
    private static int maxIdle;  // 连接池最大空闲连接数
    private static int minIdle;  // 连接池最小空闲连接数
    private static int initialSize;  // 连接池初始连接数
    // 创建BasicDataSource对象
    private static BasicDataSource dataSource = new BasicDataSource();

    // 静态代码块，实现必要的参数配置
    static {
        try {
            loadConfig();
            appConfig();
//            System.out.println("数据库连接成功！");
        } catch (Exception e) {
            throw new RuntimeException("数据库连接失败！");
        }
    }

    /**
     * 使用loafConfig加载得到的配置信息进行数据库连接池的配置
     */
    private static void appConfig() {
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxTotal(maxTotal);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMinIdle(minIdle);
        dataSource.setInitialSize(initialSize);
    }

    /**
     * 从配置文件database.properties中加载数据库连接信息
     * @throws IOException
     */
    private static void loadConfig() throws IOException {
        InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("database.properties");
        Properties properties = new Properties();
        properties.load(in);
        driverClass = properties.getProperty("driverClass");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        maxTotal = Integer.parseInt(properties.getProperty("maxTotal"));
        maxIdle = Integer.parseInt(properties.getProperty("maxIdle"));
        minIdle = Integer.parseInt(properties.getProperty("minIdle"));
        initialSize = Integer.parseInt(properties.getProperty("initialSize"));
    }

    /**
     * 用于外部类使用dataSource连接数据库
     * @return dataSource
     */
    public static DataSource getDataSource(){
        return dataSource;
    }
}
