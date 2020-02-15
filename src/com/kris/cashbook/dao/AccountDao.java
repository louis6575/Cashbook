package com.kris.cashbook.dao;

import com.kris.cashbook.domain.Account;
import com.kris.cashbook.utils.JDBCUtils;
import com.kris.cashbook.utils.JDBCUtilsByCon;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.StatementConfiguration;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * dao层类
 *      接收service层的数据，对数据库进行操作
 *
 * @author: Kris
 * Date: 2020/2/14 13:39
 */
public class AccountDao {
    private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

    /**
     * 查询全部账务
     * 由service层调用，返回结果集，将所有账务数据存储到Bean对象中，存储到集合中
     *
     * @return List结果集
     */
    public List<Account> selectAll() {
        try {
            // 查询所有账务数据的sql语句
            String sql = "SELECT * FROM accounts";
            // 调用qr对象的方法query，结果集BeanListHandler
            List<Account> list = qr.query(sql, new BeanListHandler<>(Account.class));
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("数据库查询失败！");
        }
    }

    /**
     * 条件查询
     *      根据service层传递的参数startDate和endDate，查询相应时间段的结果集
     * @param startDate 开始日期
     * @param endDate 截止日期
     * @return List结果集
     */
    public List<Account> select(String startDate, String endDate){
        try {
            // 条件查询sql语句
            String sql = "SELECT * FROM accounts WHERE time BETWEEN ? AND ?";
            // 定义对象数组，存储?占位符
            Object[] params = {startDate, endDate};
            // 调用qr对象的方法query查询数据库，获得结果集
            return qr.query(sql, new BeanListHandler<>(Account.class), params);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("条件查询失败！");
        }
    }

    /**
     * 添加账务
     * @param account Account对象
     */
    public void add(Account account) {
        try {
            // 添加账务sql语句
            String sql = "INSERT INTO accounts(name,money,account,time,description) VALUES (?,?,?,?,?)";
            // 定义对象数组，存储?占位符
            Object[] params = {account.getName(),account.getMoney(),
                    account.getAccount(),account.getTime(),account.getDescription()};
            // 调用qr对象的方法update进行添加账务记录
            qr.update(sql, params);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("添加账务记录失败！");
        }
    }

    /**
     * 修改账务
     * @param account Account对象
     */
    public void edit(Account account) {
        try {
            // 修改账务sql语句
            String sql = "UPDATE accounts SET name=?,money=?,account=?,time=?,description=? WHERE id=?";
            // 定义对象数组，存储?占位符
            Object[] params = {account.getName(),account.getMoney(),account.getAccount(),
                    account.getTime(),account.getDescription(),account.getId()};
            // 调用qr对象的update方法进行修改账务记录
            qr.update(sql,params);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("修改账务记录失败！");
        }
    }

    /**
     * 删除账务
     * @param account Account对象
     */
    public void delete(Account account) {
        try {
            // 删除账务sql语句
            String sql = "DELETE FROM accounts WHERE id=?";
            // 定义对象参数，存储?占位符
            Object param = account.getId();
            // 调用qr对象的方法
            qr.update(sql, param);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("删除账务失败！");
        }
    }
}
