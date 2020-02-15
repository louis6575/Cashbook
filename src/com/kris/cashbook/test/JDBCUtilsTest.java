package com.kris.cashbook.test;

import com.kris.cashbook.utils.JDBCUtils;
import com.kris.cashbook.utils.JDBCUtilsByCon;

/**
 * 未添加描述！
 *
 * @author: Kris
 * Date: 2020/2/14 14:35
 */
public class JDBCUtilsTest {
    public static void main(String[] args) {
        // 连接方式一：
        System.out.println(JDBCUtils.getDataSource());
//        // 连接方式二：
//        System.out.println(JDBCUtilsByCon.getCon());
    }
}
