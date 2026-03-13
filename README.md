# JavaWeb图书管理系统

此项目为JavaWeb课程的课程设计，实现了一个基础的图书管理系统。系统功能较为完整，适合用于学习和二次开发扩展。

## 技术栈

- **后端**: Java Servlet
- **前端**: JSP + Bootstrap
- **数据库**: MySQL
- **依赖库**: 
  - `mysql-connector-java-8.0.30.jar` (MySQL数据库驱动)
  - `servlet-api.jar` (Servlet API支持)

## 功能模块

- **用户管理**
  - 用户注册/登录
  - 修改密码
  - 用户借阅记录查看
- **图书管理**
  - 图书信息展示
  - 图书借阅与归还
  - 图书搜索、新增、修改、删除
- **借阅管理**
  - 借阅记录查询
  - 图书归还操作

## 系统架构

### 核心类说明

#### 控制器层（Servlet）

#### 数据访问层（DAO）

#### 实体类

## 部署教程

### 1. 数据库准备
- 创建MySQL数据库，并导入项目所需的表结构。
- 修改数据库连接配置（在`GetCorn.java`中）：
  ```java
  // 示例配置
  String url = "jdbc:mysql://localhost:3306/library_db";
  String username = "root";
  String password = "your_password";
  ```

### 2. 项目部署
- 使用IDEA导入项目。
- 配置Tomcat服务器，将项目部署到服务器上。
- 确保`WEB-INF/lib`目录下包含以下JAR包：
  - `mysql-connector-java-8.0.30.jar`
  - `servlet-api.jar`

### 3. 启动服务
- 启动Tomcat服务器。
- 访问首页：`http://localhost:8080/Library/index.jsp`
- 注意：此处浏览器访问地址如果更改了Tomcat配置需要按更改的url进行访问

### 登录页面
- 用户输入用户名和密码进行登录。

### 用户界面
- 用户可查看自己的借阅记录。
- 可进行图书搜索、借阅、归还操作。
- 可修改密码。

### 管理员界面
- 管理员可查看所有用户信息。
- 可添加、修改、删除图书信息。
- 可查看所有借阅记录。

### 图书管理
- 图书信息展示（书名、数量、位置等）。
- 支持按书名搜索图书。
- 支持新增图书、修改图书信息、删除图书。

## 项目截图

![输入图片说明](%E5%9B%BE%E7%89%87/%E9%A1%B5%E9%9D%A21.png)
![输入图片说明](%E5%9B%BE%E7%89%87/%E9%A1%B5%E9%9D%A22.png)
![输入图片说明](%E5%9B%BE%E7%89%87/%E9%A1%B5%E9%9D%A23.png)
![输入图片说明](%E5%9B%BE%E7%89%87/%E9%A1%B5%E9%9D%A24.png)
![输入图片说明](%E5%9B%BE%E7%89%87/%E9%A1%B5%E9%9D%A25.png)
![输入图片说明](%E5%9B%BE%E7%89%87/%E9%A1%B5%E9%9D%A26.png)

## 开源协议

本项目采用 MIT License 协议，欢迎自由使用和二次开发。

---

**如有任何问题或建议，欢迎提交Issue或Pull Request！**