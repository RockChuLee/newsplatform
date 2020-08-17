自己做的毕设和大家分享一下，东西很烂，主要是为了记录自己的成长，不喜勿喷。

# 技术栈

前端：HTML、CSS、JS、BootStrap
后端：Spring，SpringMVC，Mybatis，SpringBoot
数据库：MySQL，Druid连接池
其他：Maven、Thymeleaf

# 简介
此系统是基于Java EE的校园新闻平台。在后端方面选择以Spring Boot 为主整合Spring、Spring MVC和MyBatis的结构，实现了后台对整个系统的全方位管理，包括普通用户的注册、登录，新闻资讯信息的编辑、修改与发布，以及管理员对新闻资讯信息监管、完善与审核等一系列操作。在数据库方面选择MySQL作为数据库，使用MyBatis + Druid简化多源数据库的配置以及数据库操作。在前台方面，为了Web页面的展示的更加充分，采用基于HTML5+CSS3技术的Bootstrap框架和SpringBoot推荐的Thymeleaf模板引擎，使得Web开发更加方便快捷，实现了普通用户的注册、登录界面，以及快速浏览、功能分类、信息交互等功能。

# 环境搭建

第一步：新建Spring项目

选择Spring initialize，点击Next，输入公司名+项目名生成包名，点击Next，添加依赖，连续点击Next即可。

![](https://upload-images.jianshu.io/upload_images/21656169-c742a3abb899f9d6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](https://upload-images.jianshu.io/upload_images/21656169-fdb4f72901ce12f8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

第二步：导入额外依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

<!--    版本仲裁-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.3.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

<!--    项目信息-->
    <groupId>com.tyust</groupId>
    <artifactId>testdemo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>testdemo</name>
    <description>Demo project for Spring Boot</description>

<!--    JDK版本-->
    <properties>
<!--        编码信息-->
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>

        <java.version>1.8</java.version>

        <!--切换thymeleaf版本-->
        <thymeleaf.version>3.0.11.RELEASE</thymeleaf.version>
        <!-- 布局功能的支持程序  thymeleaf3主程序  layout2以上版本 -->
        <!-- thymeleaf2   layout1-->
        <thymeleaf-layout-dialect.version>2.4.1</thymeleaf-layout-dialect.version>
    </properties>

    <dependencies>
        <!--web 模块的快速开发-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--thymeleaf默认 2.1.6版本-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>

        <!--jquery-->
        <dependency>
            <groupId>org.webjars</groupId>
            <artifactId>jquery</artifactId>
            <version>3.3.1</version>
        </dependency>
        <!--bootstrap-->
        <dependency>
            <groupId>org.webjars</groupId>
            <artifactId>bootstrap</artifactId>
            <version>4.0.0</version>
        </dependency>

<!--        jdbc启动器-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
<!--        mybatis-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.3</version>
        </dependency>
<!--mysql-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!--引入druid-->
        <!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.8</version>
        </dependency>

        <dependency>
            <groupId>net.minidev</groupId>
            <artifactId>json-smart</artifactId>
            <version>2.2.1</version>
        </dependency>

        <!--test-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

<!--    这个插件，可以将应用打包成一个可执行的jar包-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```

第三步：配置数据库连接池

```yaml
spring:
  datasource:
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/newsplatform?characterEncoding=utf-8&serverTimezone=GMT%2B8
    type: com.alibaba.druid.pool.DruidDataSource
#   数据源其他配置
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true
#   配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
    filters: stat,wall,log4j2
    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
```
第四步：整合MyBatis

首先使用MyBatis的逆向工程生成相应的Mappar，注意实体类的命名规则，要和数据库一一对应。

在配置文件中配置MyBatis
```YAML
mybatis:
  # 指定全局配置文件位置
  config-location: classpath:mybatis/mybatis-config.xml
  # 指定sql映射文件位置
  mapper-locations: classpath:mybatis/mapper/*.xml
```

在Mapper类上面加@Repository注解

在主类上面加@MapperScan(value = "xxx")注解，xxx是mapper的包名

第五步：其他配置
```properties
//环境路径
server.servlet.context-path=/schoolnews
spring.mvc.hiddenmethod.filter.enabled=true
//时间格式
spring.mvc.date-format=yyyy-MM-dd
//国际化消息
spring.messages.basename=i18n.message.login,i18n.message.signup
//thymeleaf缓存，热部署
spring.thymeleaf.cache=false
```

# 功能

1.	主页
2.	登录以及注册
3.	普通用户对新闻咨询的编辑、发布和删除
4.	普通用户对新闻资讯的浏览以及点赞
5.	普通用户对新闻资讯的按分类浏览
6.	普通用户对个人信息的更改
7.	管理员对新闻的审核发布和撤销取消发布



# 展示
1.	主页

![](https://upload-images.jianshu.io/upload_images/21656169-3bde83bf897132f3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

使用BootStrap的框架，有左边栏和上边栏，主页大部分是文字，有一个轮播图是使用JS实现的。

左边栏和上边栏：bar.html和baradmin.html。处理的不是很好，代码复用性不高，有普通bar和管理员bar，其实差距不大，有待优化。

2.	登录以及注册

![](https://upload-images.jianshu.io/upload_images/21656169-10b02aed46b89bb4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

虽然登录界面只是一个非常简单的表单，但是为了让程序不出问题，在后台需要进行繁杂的操作。其中包括控制层的对用户名密码的校验，如果用户名不存在、密码错误等，根据情况返回提示。同时为了减少表单重复提交问题和越权进入主界面的问题，为登录加入了拦截器和相关配置。

并且通过默认管理员账号密码来实现管理员和用户的分开登录，管理员密码默认admin和123456。

注册页面代码与登录几乎无异，但是复杂之处在于提交之前的数据校验，通过鼠标焦点的位置触发提示，当鼠标离开触发校验。当点击提交按钮的时候检查校验结果，如果通过将用户名与数据库进行比对，没有重复在数据库中提交新的数据，如果有则返回报错。数据校验使用JavaScript编写，账号的存入由控制层调用服务层的方法实现对持久层的操作。

为了与国际接轨，本项目还实现了国际化，可以根据系统的语言实现切换。主要有国际化配置文件和本地语言的配置实现。

文件主要集中在login和signup命名的
拦截器：LoginHandlerInterceptor.java
国际化配置文件：MyLocaleResolver.java、i18n.message

3.	普通用户对新闻咨询的编辑、发布和删除

通过首页的“我的新闻”按钮即可查看自己所有状态的新闻，并且在“我的新闻”界面设置了三个按钮，分别是“添加新闻”，“修改新闻”和“删除新闻”。页面的展示通过控制层和视图层的联动生成动态界面，控制层通过遍历数据库获取新闻资讯，再通过前端控制器传递给视图层，视图层运用Thymeleaf语法循环遍历展示。

为了保证表格的完整性和美观，将每个按钮绑定事件，减少HTML中form表单的数量，这就是JavaScript的优势所在。因为新闻属性的固定，添加和修改功能十分相似，本项目采用编辑和添加二合一界面，提高代码复用性，减小维护成本。而实现这一功能的关键在于对新闻id的判断，如果新闻id为空，说明没有内容，是添加新闻，如果不为空则是修改新闻。根据这一原理，再配合RESTful的开发风格，将修改和添加的http传递风格分别设为post和put，通过判断和隐藏标签实现交互方式的转换。

删除功能与添加修改功能所差无几，所以在此不做赘述。但是此功能还有几个细节，便是生成新闻的时间由系统提供，与当前使用此网页的本地主机时间一致。发布的文章会处于一个等待发布的状态，需要管理员审核才能被其他用户浏览，已发布的文章在修改后会重新进入等待发布的状态，需要重新审核。

![我的新闻页面](https://upload-images.jianshu.io/upload_images/21656169-aff3528f5413bea3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![添加新闻页面](https://upload-images.jianshu.io/upload_images/21656169-def3a9d1caf97ee7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![修改新闻页面](https://upload-images.jianshu.io/upload_images/21656169-da4bc2d5c716fa4e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



4.	普通用户对新闻资讯的浏览以及点赞

浏览新闻同样是通过控制层和视图层的联动生成动态界面来完成页面的展示，控制层通过遍历数据库获取新闻资讯，再通过前端控制器传递给视图层，视图层运用Thymeleaf语法循环遍历展示。

其中为了美观，使用style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis;"的样式，解决了超出部分缩略展示的问题。
整个浏览功能的逻辑是，用户提出发布新闻的请求，管理员审核通过，展示在浏览新闻页面中，供所有用户浏览。用户除了可以通过浏览新闻页面观看所有新闻的概览，还可以点击进入新闻的详细页面，这个过程会为新闻的点击率增加1，如果用户对新闻十分满意的话，还可以对新闻进行点赞，提高新闻的热度。

![浏览新闻页面](https://upload-images.jianshu.io/upload_images/21656169-263d52b09097ff2f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![新闻详细页面](https://upload-images.jianshu.io/upload_images/21656169-20ed0c57231db445.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

5.	普通用户对新闻资讯的按分类浏览

(一)	普通用户对重大资讯的按时间顺序浏览
在用户浏览新闻时，为了方便用户可以尽快的了解到最近发生的事情，所以提供此功能，用户可以根据新闻的发布时间，优先浏览最近发布的新闻。实现此功能的方法就是注入一个排序的SQL。
(二)	普通用户对热点资讯的按热点算法浏览
在用户浏览新闻时，为了可以让有质量的新闻排在更靠前的位置，本系统特意设计了一个简单的算法，即点赞率乘以十加上浏览量在于浏览量的比值作为一个排序的依据，进行排序。这样的情况下，可以让同等浏览量下点记录高的排在前面，让高浏览低点赞的没有营养的文章排在后面，提高用户的体验。
(三)	普通用户对新闻资讯的按分类浏览
在用户浏览新闻时，为了方便用户根据自己的喜好发布内容，实现了分类型浏览模块。主要实现原理与全部浏览所差无几，唯一的区别在于数据库查询的限定。

![新闻类型](https://upload-images.jianshu.io/upload_images/21656169-76402cca91740832.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


6.	管理员对新闻的审核发布和撤销取消发布

管理员对新闻资讯的操作主要体现在对新闻的审核，包括审核通过，将未发布的文章进行发布，审核不通过驳回发布请求。同时还能对已发布的新闻进行撤销处理。而其中重要功能的实现都以说明，这里主要对前后端的交互进行阐述。因为项目采用Spring Boot的分布式框架，整合了SSM框架，在项目的最底层有新闻的实体类，里面存放着和数据库字段相同的属性，然后为了实现对数据库的操作，又不增加太多的耦合，所有再抽象出一个映射层，传统的开发方式是Dao开发，但是MyBatis整合了更加方便的mapper开放，每个实体类都有对应的mapper类和xml文件，在mapper类中声明操作方法，在xml文件中实现相应的SQL语句。再上一层就是服务层，将对数据库具体的操作再封装成功能。然后在控制层对服务层的功能进行调用，即可实现。最后视图层通过url的映射关系，完成请求。整个流程结束，虽然整个过程十分复杂，但是得益于Spring Boot的自动配置，封装和整合，只需要简单的注解就可以实现。

![管理员管理新闻页面](https://upload-images.jianshu.io/upload_images/21656169-8f13343eec7cea0a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

3-6 这些部分都主要集中在news相关文件，这部分其实写的非常不好，耦合性太高，没有分开，而且代码复用性不好，重复代码太多。水平有限，多担待。

7. 普通用户对个人信息的更改

为了增加用户体验，本项目提供用户个人信息的编辑和修改功能，在本界面用户可以清晰的看到自己的用户id，以及设置自己的个性化页面。提高本项目用户的归属感和忠诚度。实现方法与上无异，再次不多做赘述。

主要文件集中在带userInfo相关文件

![个人信息页面](https://upload-images.jianshu.io/upload_images/21656169-80607bde156c31dd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


