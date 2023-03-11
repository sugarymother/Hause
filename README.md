# 蛤租房 Hause 网站项目
毕业设计实践，采用 SpringBoot3.0 + SpringMVC + Mybatis-plus + FreeMaker 模板引擎。

### 待解问题
- [X] publish页 radio单选框取得数据不是选中的项。
- [X] 主页、search页、myhouse页的房源列表下拉刷新时随机出现表项被添加两次的情况。
- [ ] 房源删除后，在 主页、search页的房源列表点击已被删项跳转错误。
- [ ] 受XSS影响，来源为发布房源中的用户输入内容，但是懒得管了。
- [X] 密码仍以明文传输，需要在前端进行非对称加密。(最后直接改为md5简单处理了下，生成mac，这样的处理方式仍然不安全，仅能保证明文不泄露，但是懒得管了)
- [ ] 多点登录问题，未实现单点登录，仅使用JWT鉴权无法主动的取消token有效性，但是懒得管了。 

### 问题记录
1. 打包出现：**无效的目标发行版：17**
> IDEA 的设置 maven > runner 中把 jre 改为 Use Project JDK 解决
2. 配置 **server.servlet.context-path=/hause** 后访问静态资源出现404
> 之前模板文件中静态资源的uri一般配为：/static/...
> 
> 后改为：/hause/static/...  解决
> 
> 此方法会导致IDEA识别不了uri的位置，但影响不大
3. CVM上运行jar包报错字节码版本过高，jre版本过低
> 将 jdk8 升为 jdk17 解决
4. Mybatis-plus产生警告信息：**Unable to make field private final java.lang.Class java.lang.invoke.SerializedLambda.capturingClass accessible**
> 根据网上的说法，添加启动参数：
> 
> --add-opens=java.base/java.lang=ALL-UNNAMED
> 
> 并未见到效果，警告日志仍然出现。