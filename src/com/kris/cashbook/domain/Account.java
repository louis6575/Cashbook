package com.kris.cashbook.domain;

/**
 * 记账本类定义
 *
 * @author: Kris
 * Date: 2020/2/14 12:03
 */
public class Account {
    private int id;  // 记账编号
    private String name;  // 记账主题
    private double money;  // 记账金额
    private String account;  // 记账类型
    private String time;  // 记账时间
    private String description;  // 记账详情

    public Account(){
        super();
    }

    public Account(int id, String name, double money, String account, String time, String description) {
        super();
        this.id = id;
        this.name = name;
        this.money = money;
        this.account = account;
        this.time = time;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", account='" + account + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
