package com.kris.cashbook.controller;

import com.kris.cashbook.domain.Account;
import com.kris.cashbook.services.AccountService;

import java.util.List;

/**
 * 控制层类
 * 接收view层的数据，数据传递给service层
 * 成员位置，创建Service对象
 *
 * @author: Kris
 * Date: 2020/2/14 13:46
 */
public class AccountController {
    private AccountService service = new AccountService();

    /**
     * 控制层类定义方法，实现查询所有的账务数据
     * 方法由view层调用，方法调用service层
     */
    public List<Account> selectAll(){
        return service.selectAll();
    }

    public List<Account> select(String startDate, String endDate) {
        return service.select(startDate, endDate);
    }

    public void add(Account account) {
        service.add(account);
    }

    public void edit(Account account) {
        service.edit(account);
    }

    public void delete(Account account) {
        service.delete(account);
    }
}
