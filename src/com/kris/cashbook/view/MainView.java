package com.kris.cashbook.view;

import com.kris.cashbook.controller.AccountController;
import com.kris.cashbook.domain.Account;

import java.util.List;
import java.util.Scanner;

/**
 * 视图层类
 * 用户看到和操作的界面
 * 数据传递给controller层实现
 * 成员位置，创建Controller对象
 *
 * @author: Kris
 * Date: 2020/2/14 13:46
 */
public class MainView {
    private AccountController controller = new AccountController();

    /**
     * 实现界面效果
     * 接收用户的输入
     * 根据数据，调用不同的功能方法
     */
    public void run() {
        // 创建一个Scanner类对象，反复键盘输入
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("--------------------------- 记账软件系统 ---------------------------");
            System.out.println("\t1.查询账务\t2.添加账务\t3.修改账务\t4.删除账务\t5.退出系统");
            System.out.println("-------------------------------------------------------------------");
            System.out.println(">> 请输入要操作的功能序号[1-5]:");

            // 接收用户指令
            String choose = sc.next();
            // 对选择的菜单指令进行判断，调用不同的功能
            switch (choose) {
                case "1":
                    // 查询账务功能，调用查询账务的方法
                    select();
                    break;
                case "2":
                    // 添加账务功能，调用添加账务的方法
                    add();
                    break;
                case "3":
                    // 修改账务功能，调用编辑账务的方法
                    edit();
                    break;
                case "4":
                    // 删除账务功能，调用删除账务的方法
                    delete();
                    break;
                case "5":
                    // 退出系统
                    System.out.println(">> 欢迎您下次使用本系统！再见！");
                    System.exit(0);
                    break;
                default:
                    System.out.println(">> 输入有误！请重新输入！");
                    break;
            }
        }
    }

    /**
     * 创建Account对象，用于执行添加账务与修改账务功能
     * @param account Account对象
     * @param sc Scanner对象
     */
    private void setAccount(Account account, Scanner sc) {
        System.out.print(">> 输入记账主题：");
        account.setName(sc.next());
        System.out.print(">> 输入记账金额：");
        account.setMoney(sc.nextDouble());
        System.out.print(">> 输入记账类型：");
        account.setAccount(sc.next());
        System.out.print(">> 输入记账时间（yyyy-mm-dd）：");
        account.setTime(sc.next());
        System.out.print(">> 输入记账详情：");
        account.setDescription(sc.next());
    }

    /**
     * 4.删除账务功能
     */
    private void delete() {
        Account account = new Account();
        Scanner sc = new Scanner(System.in);
        selectAll();
        System.out.print(">> 请输入要删除的账务id：");
        int tempId = sc.nextInt();
        account.setId(tempId);
        System.out.print(">> 是否要删掉编号为"+tempId+"的账务(y/n)?\t");
        if (sc.next().equals("y")){
            controller.delete(account);
            System.out.println("删除账务成功！");
        }
    }

    /**
     * 3.修改账务功能
     */
    private void edit() {
        Account account = new Account();
        Scanner sc = new Scanner(System.in);
        selectAll();
        System.out.println(">> 请选择要修改的账务id:");
        account.setId(sc.nextInt());
        setAccount(account, sc);
        controller.edit(account);
        System.out.println("修改账务成功！");
    }

    /**
     * 2.添加账务功能
     */
    private void add() {
        Account account = new Account();
        Scanner sc = new Scanner(System.in);
        setAccount(account, sc);
        controller.add(account);
        System.out.println("添加账务成功！");
    }

    /**
     * 1.查询账务功能
     */
    private void select() {
        System.out.println("1.查询所有\t2.条件查询");
        Scanner sc = new Scanner(System.in);
        String choose = sc.next();
        // 根据用户指令，调用不同的功能
        switch (choose) {
            case "1":
                // 查询所有，调用查询所有的方法
                selectAll();
                break;
            case "2":
                // 条件查询，调用条件查询的方法
                selectBySql();
                break;
            default:
                System.out.println(">> 输入有误！请重新输入！");
                select();
        }
    }

    /**
     * 实现条件查询方法
     *      将用户提供的起止日期传递到controller层，传递两个参数
     *      获得controller层查询的结果集，并打印出来
     */
    private void selectBySql() {
        System.out.println(">> 条件查询功能使用说明：输入日期格式为 yyyy-mm-dd");
        Scanner sc = new Scanner(System.in);
        System.out.println(">> 请输入开始日期：");
        String startDate = sc.nextLine();
        System.out.println(">> 请输入截止日期：");
        String endDate = sc.nextLine();
        // 调用controller层的方法，传递日期，获取查询结果集
        List<Account> list = controller.select(startDate, endDate);
        if (list.size() != 0) {
            showSelectMenu(list);
        } else {
            System.out.println(">> 查找时段记录为空！");
        }
    }

    /**
     * 查询全部账务
     */
    private void selectAll() {
        // 调用控制层中的方法，查询所有账务数据
        List<Account> list = controller.selectAll();
        if (list.size() != 0) {
            showSelectMenu(list);
        } else {
            System.out.println(">> 您还没有开始记录哦！");
        }
    }

    /**
     * 打印查询的结果集
     * @param list 查询获得的结果集
     */
    private void showSelectMenu(List<Account> list) {
        // 输出表头
        System.out.println("--------------------------- 账务记录清单 ---------------------------");
        System.out.println("ID\t类别\t账户\t金额\t时间\t\t说明");
        // 遍历集合，结果输出控制台
        for (Account account : list) {
            System.out.println(account.getId() + "\t" + account.getName() + "\t" + account.getAccount() + "\t" + account.getMoney() + "\t" + account.getTime() + "\t" + account.getDescription());
        }
        System.out.println("-------------------------------------------------------------------");

    }
}
