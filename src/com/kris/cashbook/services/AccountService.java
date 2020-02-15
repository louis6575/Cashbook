package com.kris.cashbook.services;

import com.kris.cashbook.dao.AccountDao;
import com.kris.cashbook.domain.Account;

import java.util.List;

/**
 * 业务层类
 * 接收上一层，即控制层controller的数据
 * 经过计算，传递给dao层，来进行操作数据库
 * 调用dao层中的类，类成员位置，创建Dao类对象
 *
 * @author: Kris
 * Date: 2020/2/14 13:42
 */
public class AccountService {
    private AccountDao dao = new AccountDao();

    /**
     * 定义方法，实现查询所有账务数据
     * 此方法，由控制层调用，调用dao层的方法
     * @return
     */
    public List<Account> selectAll(){
        return dao.selectAll();
    }

    public List<Account> select(String startDate, String endDate){
        return dao.select(startDate, endDate);
    }

    public void add(Account account) {
        dao.add(account);
    }

    public void edit(Account account) {
        dao.edit(account);
    }

    public void delete(Account account) {
        dao.delete(account);
    }
}
