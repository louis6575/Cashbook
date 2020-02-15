# 记账本系统

>	采用DBCP连接池技术，通过加载配置文件database.properties，获取数据库连接信息，以及连接池最大连接数maxTotal，最大空闲数maxIdle，最小空闲数minIdle，初始连接数initialSize等。  
### 项目文件结构

配置文件database.properties一般存放于src目录下，存放的是键值对，使用Properties的getProperty(String key)方法进行读取。注意：等号两侧不要有空格，即左右键值对不要有多余的空格；另外，在配置url时要注意服务时区应当设定为UTC。详情配置参见**[database.properities](./src/database.properities)**
`url=jdbc:mysql://localhost:3306/database_name?serverTimezone=UTC`  

> * src.com.kris.cashbook
> 	> + app
> 	> 	
> 	> 	> - MainApp.java	主程序类，调用view层方法 
> 	> + controller
> 	> 	
> 	> 	> - AccountController.java	类内方法由view层，调用service层方法
> 	> + dao
> 	> 	
> 	> 	> - AccountDao.java	类内方法由service层调用，操作数据库	
> 	> + database
> 	> 	> - cashbook.sql		仅表结构
> 	> 	> - cashbookDB.sql	含部分数据
> 	> + domain
> 	> 	
> 	> 	> - Account.java	对应的数据库表的实体类，管理javaBean实体对象
> 	> + service	业务层
> 	> 	
> 	> 	> - AccountService.java	类内方法由controller层调用，调用dao层方法
> 	> + utils
> 	> 	> - JDBCUtils.java	采用DBCP连接池技术连接数据库
> 	> 	> - JDBCUtilsByCon.java	实现DriverManager连接数据库
> 	> + view
> 	> 	
> 	> 	> - MainView.java		用户界面(UI)设计,调用controller层方法
> 	> + test
> 	> 	
> 	> 	> - JDBCUtilsTest.java	测试数据库连接

### 第三方jar包

| jar包                           | 官方描述                                                     | 个人理解                                              |
| ------------------------------- | ------------------------------------------------------------ | ----------------------------------------------------- |
| mysql-connector-java-8.0.17.jar | JDBC Type 4 driver for MySQL                                 | mysql数据库连接的工具包，可选用本机已有版本。         |
| commons-pool2-2.8.0.jar         | The Apache Commons Object Pooling Library.                   | 连接池主体类                                          |
| commons-dbcp2-2.7.0.jar         | Apache Commons DBCP software implements Database Connection Pooling | DBCP连接池工具包，是pool2包的实现类。                 |
| commons-logging-1.2.jar         | Apache Commons Logging is a thin adapter allowing configurable bridging to other, well known logging systems. | 使用dbcp2+pool2时，需要添加此工具包，这是我踩过的坑。 |
| commons-dbutils-1.7.jar         | The Apache Commons DbUtils package is a set of Java utility classes for easing JDBC development. | 数据库工具包，常用QueryRunner等。                     |

### 本项目引用说明

需要注入项目所依赖的第三方jar包，此项目所用的jar包大都是最新版本，如果您的包版本过低，可能会出现少许版本不一致所带来的问题。您可以到这里下载相应的jar包，网址是：<https://mvnrepository.com/>  

### 项目反思与总结

在开始本项目之前，自己曾跟着《Spring MVC + Mybatis 快速开发与项目实战》尝试过一个Spring相关的项目案例当看到pom.xml的时候，我都是跟着把那些groupId、atifactId、version等给输入进去（qwq）,然后，发现很多红色提示，【Alt+Enter】也没反应，我没有导入相关的jar包，肯定不能import的，也没法【Ctrl+左键】查看源码，之前还是对JVM的理解不够，对于jre内lib只知道存储了一些java常用的jar包。通过本次项目的学习，我能够进行分层设计这样的需要对数据库操作的任意系统，当然仅限于本机，网络编程，访问远程数据库等，有相应的资源也能很快能适应。另外，此项目之后，便可以开始学习ssm框架相关的基础知识了。    
