用作 Apache shiro 的学习项目
====
项目使用 maven
##Apache shiro 简介
用于<strong>身份验证</strong>和<strong>授权</strong>的框架。
摘自：[IBM开发者社区](http://www.ibm.com/developerworks/cn/web/wa-apacheshiro/ "Apache Shiro 简介")<br>
官方文档翻译：[中文文档](http://greycode.github.io/shiro/doc/reference.html "Apache Shiro 中文文档")
##了解 shiro
Shiro 的 Session 对象允许无需 HttpSession 即可使用一个用户会话。<br>
通过使用一个通用的 Session 对象，即便该代码没有在一个 Web 应用程序中运行，仍可以使用相同的代码。<br>
没有对应用服务器或 Web 应用服务器会话管理的依赖，您甚至可以在命令行环境中使用 Shiro。<br>
换言之，使用 Shiro 的 API 编写的代码让您可以构建连接到 LDAP 服务器的命令行应用程序并且与 web 应用程序内用来访问 LDAP 服务器的代码相同。<br>

### shiro 的三个主要核心
Subject，SecurityManager，Realms，来自InfoQ.com：[Application Security with Apache Shiro](https://www.infoq.com/articles/apache-shiro "full intro article on InfoQ.com")<br>

## 10 Minute Tutorial on Apache Shiro
快速开始：[10-Minute Tutorial](http://shiro.apache.org/10-minute-tutorial.html "10-Minute Tutorial")<br>