## 前言
写在前面的话，致每一位读者！<br>
任何事物的诞生总有她的必然性和偶然性，就如牛顿当年坐在树下被水果砸中头后发明了万有引力，是坐下树下的偶然，也是历史推动的必然。<br>
JFDF也不例外，作为一个游走在职场数十年的老兵程序员，出于对程序事业的尊敬也出于对程序员职业的热爱，让我总想在我的当打之年留下点什么。<br>
回顾过去，纵使开始这个项目的理由有上千种，但由于本人天生慵懒也导致了项目的胎死腹中。然而由于吃鸡外挂的泛滥，让我们这些循规蹈矩的首批玩家完全没有一点生存空间，要么遇见闪电侠见光死，要么就是喝一梭子弹立马变邮包(ps：本人吃鸡账号"包发虎"，欢迎组队吃鸡!!!)。没有了精神食粮的我决定痛定思痛，开始做一些有意义的事情，以寻找灵魂的依偎(逼格就是这样出来的，哈哈！！！)。<br>
其实对我来说做这样的工作不是第一次，肯定也不会是最后一次，但每次脑海中的想法总会有千丝万缕的差异。依稀记得第一次动手做研发框架，总想凭借这自己的能力力挽狂澜，为这个世界的改变能做点贡献，但事实总是那么的无情。当我卷起袖子管，徒手写了无数的代码、设计模式、工具类等等以后，却发现我引以为豪的优秀作品缺无人问津，Every body还是按照自己的习惯该干嘛就干嘛，世界还是保持着原样，地球还是那样的在自转着。总结了那么多的历史经验，我准备在尽量不改变的大家开发习惯的前提下，来完成本开源项目。简单说我不是什么发明家，我只是代码的搬运工。

## JFDF介绍
JFDF的全称是JAVA Fast Development Framework，即JAVA快速开发框架。<br>
* **JFDF快速构建应用系统：** JFDF可以帮助大家快速投入到web项目的开发当中，从而避免了许多程序开发初期阶段不必要的麻烦，诸如各种开源技术的整合、开发规范、项目结构、访问权限控制、用户角色管理、系统菜单管理、必要数据的初始化等等。相信每一个独立研发过产品的攻城狮来说，都经历过以上的这些工作，繁琐、枯燥、乏味，每天伴随着的是数不清的调试和问题以及有兼容性错误，久而久之让我们不得不怀疑人生，常常认为自己已然成为了一个问题青年。总之，JFDF建立的初衷是想能够帮助你迅速构建起你的业务系统，让每一位开发人员的视角聚焦在业务功能上，从而避免不必要的麻烦。
* **JFDF是一套严格的开发规范：** 曾几何时的我，初出茅庐涉世未深。我显然不是什么明星学霸也不是嘴里喊着金钥匙出生的明日之星，犯过所有程序猿所犯过的所有错误，也经历过每一位新人程序猿的惶恐不安。有时为了缅怀过去，会不经意间翻开曾经年少时做过的项目，看着一坨坨的代码片段，满目疮痍，不忍直视。可能系统没有崩溃是上天对我的眷顾，但是让我再去拾起它继续后面的工作那绝对是打死我一百个不愿意。然而，本着自己的一份执着，在历史的某个时间点上，开始对自己过去犯过的过错进行总结，简单说好的项目必须要有严格的管理规范，逼格高一点的说法就是，“要让你的代码会说话”，可能这也就是所谓的Code as data。当然，归根结底好的开发规范能帮助我们提升我们的项目质量、确保项目整体的可维护性，等等等等（此处省略一万字...）。这也是我做这个开源项目的另一个初衷。
* **JFDF能帮我们提升工作效率：** 记得曾经的面试经常会遇到这么一些问题：你以前的团队有多少人？你最多负责过几个人的团队？你能管理几十人的团队吗？等等等等。。这些问题让我不得不怀疑，我们日以继夜，年复一年不断的努力工作、努力学习，我们所积累的知识和学识仅仅是为了面对这么一个以量取胜的数字游戏吗？显然不是。我觉问题的根本还是我们应该怎么样来提高我们的工作效率。然后聚焦到程序员这个职业上就是，我们怎么样提高我们的研发效率。不知从何时开始，我们国内养成了这样一种习惯，即所谓的前后分离，暂且不管是真分还是假分，反正就是前后分离。当然，概念的本质是美好的，但我相信每一位实际操作过的攻城狮肯定遇到了各种对接的烦恼，轻则谩骂撕逼、重则拳脚相加，让程序猿这么一个老实本分的职业披上了一层危险色彩。当然，回到如何提升研发效率这个课题上来说的话，这是一门科学、一门学科，会有各种各样的权威解释，然而，作为我片面的认为，全栈就是提升产品研发效率的一种方式。正所谓你说服不了别人，但是你可以轻易的说服你自己。当你一个人面对全栈的所有问题，也没有了其他让你说服的人，那顺其自然的你只能说服你自己了。然而，JFDF正是这样一套全栈框架。（ps：关于全栈提升效率的话题，这里再次强调，只是代表个人片面的理解）。
* **JFDF未来的畅想：** 今天是2018年1月份，在我用了一个多月的闲暇时间的努力后，JFDF的1.0.0版本终于可以见天日了。当然，对于这样一个初生的“婴儿”来说目前的功能还很薄弱，但是正是这样一个初生的“婴儿”，她未来的发展潜力也是无限的。她就好比是平行时空中的一个奇点，会迎来属于她自己的大爆炸，从而孕育出宇宙和生命。未来我希望有更多的业务应用基于JFDF来构建，同时在构建的过程中不断的反馈不断完善，也为了我们的开源事业尽自己的一份微薄之力。
## 作者

* **甜心的超级奶爸** 13482477@qq.com (我是一名程序猿，我为程序猿代言！！！)
## JFDF当前的主要功能
* **系统必要数据的初始化：** JFDF内置了一个XML的配置文件，首次运行系统时，只需写入配置即可完成所有的数据初始化工作。
* **系统登录：**<br>
![image](https://github.com/13482477/JFDF/blob/master/screen/login.png)
* **系统首页：**<br>
![image](https://github.com/13482477/JFDF/blob/master/screen/index.png)
* **用户管理：**
![image](https://github.com/13482477/JFDF/blob/master/screen/user.png)
* **角色管理：**
![image](https://github.com/13482477/JFDF/blob/master/screen/role.png)
* **菜单管理：**
![image](https://github.com/13482477/JFDF/blob/master/screen/menu.png)
* **资源管理：**
![image](https://github.com/13482477/JFDF/blob/master/screen/resource.png)
## 必要的依赖
作为一名Spring社区的一名死忠粉，JFDF的核心技术也主要都是围绕着以Spring技术来构建的。
### 前端技术
* **依赖管理** `Bower`
* **核心技术** `jquery 3.2.1` 为了上手方便，前端核心技术采用jquery直接操作DOM树的方式，简单粗暴，堪称暴力美学。当然后续我会逐步退出Angular、Vue、React的版本。
* **Bootstrap** `3.3.7`
* **页面样式** `Admin-lte 3.2.1`
* **树** `zTree 3.5.29`
* **表单验证** `formvalidation.io 0.0.3`
* **数据表格** `bootstrap-table 1.11.1`
* **过度效果** `jquery-loading 1.2.0`
* **提示框** `sweetalert 2.0.7`
* **图形报表** `flotjs morrisjs`

### 后端技术
* **JDK版本** `JDK版本必须>=1.8`，可能是作为处女座的程序员的天性使然，我对于程序代码的要求还是有洁癖的，能简洁的尽量简洁，能少写一行的绝不会写第二行。所以过多的依赖了JDK1.8里面的新特性。
* **依赖管理** `Apache Maven 3.5.0`
* **数据库** `MSQL 5.7.17`，反正我是在这个版本下开发的，其它版本大家可做尝试。
* **启动方式** `Spring Boot`
* **持久化层** `Spring JPA`
* **系统权限** `Spring Security`
* **模板引擎** `Freemark` 为了不依赖与容器，保证项目的独立启动，且由于Volicty的停止维护以及Thymeleaf的不友好，所以选择了Freemarker
* **接口文档** `Springfox & Swagger2`
* **数据校验** `Spring Validation`
* **Json序列化** `Jackson Json`
* **数据库连接池** `Tomcat Jdbc`
* **还有许多就不一一列举了。。。**
## JFDF目录结构
JDFD采用标准的Maven构建，目录结构也是标准的Maven项目结构。<br>
犹豫内容比较多，这里我罗列了一些项目主要文件的目录结构，经供参考。
```
JFDF/                                         /**系统更目录**/
├──assembly/
| └──bin.xml                                  /**打包配置文件**/
├──screen/                                    /**系统截图**/
├──src/                          
| ├──main/                                    /**源代码目录**/
| | ├──java/                                  /**java源代码**/
| | | ├──com.jhonelee.jfdf.Application.java   /**项目启动文件**/
| | | └──com.jhonelee.jfdf.conf.**            /**Java config**/
| | └──resource/
| |   ├──public/                              /**静态资源目录**/
| |   ├──templates/                           /**freemark模板文件**/
| |   ├──application.preperties               /**spring boot配置文件**/
| |   ├──messages.preperties                  /**本地化文件**/
| |   └──resource.xml                         /**系统初始化文件**/
| └──test/                                    /**单元测试代码目录**/
├──target/                                    /**编译后的可执行文件**/
├──.gitingore                                 /**git的ingore文件**/
├──pom.xml                                    /**maven配置文件**/
└──README.md
```
## JFDF配置说明
### 数据库配置
由于JFDF的数据持久化层采用了Spring JPA(关于JPA的使用，请自行翻阅相关资料，这里不做赘述。)，那么我们可以很方便的在系统初始化的时候，根据我们定义的业务实体类自行创建数据库表、关联表、主键、外检、索引等。这种方便的操作完全符合我这种懒人的习惯。

默认数据库配置
```
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/jfdf?characterEncoding=utf8
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.tomcat.max-active=100
spring.datasource.tomcat.initial-size=4
spring.datasource.tomcat.max-idle=10
spring.datasource.tomcat.min-idle=10
spring.datasource.tomcat.jdbc-interceptors=ConnectionState;StatementFinalizer;StatementDecoratorInterceptor;ResetAbandonedTimer;SlowQueryReport(threshold=500);SlowQueryReportJmx(notifyPool=false)
spring.datasource.tomcat.validation-query=select CURRENT_USER
spring.datasource.tomcat.test-on-borrow=true
spring.datasource.tomcat.default-auto-commit=false
```
可以在application.properties中修改。
### 初始化配置文件
resource.xml
JFDF提供了一个系统必要数据的初始化文件，包括系统访问资源初始化和系统菜单的初始化。JFDF在首次启动时会读解析resource.xml,将相关配置数据载入到系统中。内容如下：
```xml
<resource name="JFDF" code="JFDF" type="SYSTEM" iconType="ICON" iconPath="fa-sitemap">
	<resource name="首页" code="index" type="MENU" url="/index/page" httpMethod="GET" iconType="ICON" iconPath="fa-dashboard">
		<resource name="首页页面" code="index_page" type="RESOURCE" url="/index/page" httpMethod="GET" />
	</resource>
	<resource name="系统管理" code="system_management" type="MENU" httpMethod="GET" iconType="ICON" iconPath="fa-cog">
		<resource name="用户管理" code="user_management" type="MENU" url="/user/page" httpMethod="GET" iconType="ICON" iconPath="fa-user">
			<resource name="用户页面" code="user_page" type="RESOURCE" url="/user/page" httpMethod="GET" />
			<resource name="用户新增" code="user_create" type="RESOURCE" url="/user" httpMethod="POST" />
			<resource name="用户详情" code="user_read" type="RESOURCE" url="/user/{id}" httpMethod="GET" />
			<resource name="用户编辑" code="user_update" type="RESOURCE" url="/user/{id}" httpMethod="PUT" />
			<resource name="用户删除" code="user_delete" type="RESOURCE" url="/user/{id}" httpMethod="DELETE" />
			<resource name="用户查询" code="user_find" type="RESOURCE" url="/users" httpMethod="GET" />
			<resource name="用户校验" code="user_validation" type="RESOURCE" url="/user/validation" httpMethod="GET" />
			<resource name="用户角色查询校验" code="user_roles_find" type="RESOURCE" url="/roles" httpMethod="GET" />
		</resource>
		<resource name="角色管理" code="role_management" type="MENU" url="/role/page" httpMethod="GET" iconType="ICON" iconPath="fa-group">
			<resource name="角色页面" code="role_page" type="RESOURCE" url="/role/page" httpMethod="GET" />
			<resource name="角色新增" code="role_create" type="RESOURCE" url="/role" httpMethod="POST" />
			<resource name="角色详情" code="role_read" type="RESOURCE" url="/role/{id}" httpMethod="GET" />
			<resource name="角色编辑" code="role_update" type="RESOURCE" url="/role/{id}" httpMethod="PUT" />
			<resource name="角色删除" code="role_delete" type="RESOURCE" url="/role/{id}" httpMethod="DELETE" />
			<resource name="角色查询" code="role_find" type="RESOURCE" url="/roles" httpMethod="GET" />
			<resource name="角色校验" code="role_validation" type="RESOURCE" url="/role/validation" httpMethod="GET" />
			<resource name="角色资源数加载" code="role_menu_load" type="RESOURCE" url="/role/menu/children" httpMethod="GET" />
			<resource name="角色授权" code="role_menu_load" type="RESOURCE" url="/role/{roleId}/resource" httpMethod="PUT" />
		</resource>
		<resource name="菜单管理" code="menu_management" type="MENU" url="/menu/page" httpMethod="GET" iconType="ICON" iconPath="fa-bars">
			<resource name="菜单页面" code="menu_page" type="RESOURCE" url="/menu/page" httpMethod="GET" />
			<resource name="菜单加载" code="menu_child_load" type="RESOURCE" url="/menu/children" httpMethod="GET" />
			<resource name="菜单新增" code="menu_create" type="RESOURCE" url="/menu" httpMethod="POST" />
			<resource name="菜单详情" code="menu_read" type="RESOURCE" url="/menu/{id}" httpMethod="GET" />
			<resource name="菜单编辑" code="menu_update" type="RESOURCE" url="/menu/{id}" httpMethod="PUT" />
			<resource name="菜单删除" code="menu_delete" type="RESOURCE" url="/menu/{id}" httpMethod="DELETE" />
			<resource name="菜单查询" code="menu_find" type="RESOURCE" url="/menus" httpMethod="GET" />
			<resource name="已选中资源查询" code="menu_find" type="RESOURCE" url="/menu/{menuId}/selectedMenus" httpMethod="GET" />
			<resource name="资源更新" code="menu_resource_update" type="RESOURCE" url="/menu/{menuId}/selectedMenus" httpMethod="PUT" />
		</resource>
		<resource name="资源管理" code="resource_management" type="MENU" url="/resource/page" httpMethod="GET" iconType="ICON" iconPath="fa-tree">
			<resource name="资源页面" code="resource_page" type="RESOURCE" url="/resource/page" httpMethod="GET" />
			<resource name="资源校验" code="resource_validation" type="RESOURCE" url="/resource/validation" httpMethod="GET" />
			<resource name="资源新增" code="resource_create" type="RESOURCE" url="/resource" httpMethod="POST" />
			<resource name="资源详情" code="resource_read" type="RESOURCE" url="/resource/{id}" httpMethod="GET" />
			<resource name="资源编辑" code="resource_update" type="RESOURCE" url="/resource/{id}" httpMethod="PUT" />
			<resource name="资源删除" code="resource_delete" type="RESOURCE" url="/resource/{id}" httpMethod="DELETE" />
			<resource name="资源查询" code="resource_find" type="RESOURCE" url="/resources" httpMethod="GET" />
		</resource>
		<resource name="权限管理" code="authority_management" type="MENU" url="/authority/page" httpMethod="GET" iconType="ICON" iconPath="fa-user-secret">
			<resource name="权限页面" code="authority_page" type="RESOURCE" url="/authority/page" httpMethod="GET" />
			<resource name="权限查询" code="authority_find" type="RESOURCE" url="/authorities" httpMethod="GET" />
		</resource>
	</resource>
</resource>
```
注意这里的type属性，type=SYSTEM|MENU|RESOURCE，SYSTEM代表系统，MENU代表导航菜单，RESOURCE代表访问资源即系统功能。当然有人可能会问这里为什么会引入SYSTEM的概念，我是为了后面OATH2的开发做准备。

### 默认的系统管理员账号
系统初始化成功后会提供一个默认的管理员<br>
用户名:admin<br>
密码：password<br>
该账号拥有系统中的最高权限，可以访问所有功能。另外，如需更多其它账号，请使用系统功能进行添加。

## JFDF开发手册
### JFDF的半前后分离模式
要说JFDF的开发模式，其实在我开始这个项目之前就一直在考虑，是采用前后分离呢，还是采用mvc，是采用MVC呢，还是采用前后分离，我真的想了很久很久，想的脚进脑之，脑袋爆炸貌似也没有一个很好的结果，索性不想了，先鲁码再说。如果硬是要给JDFD的开发模式定个性的话，我只能说是半前后分离模式。那什么是半前后分离模式呢，就好比我们日常生活中的汽车，大家都知道汽车有自动挡汽车和手动挡汽车，当然也有介于它们之间的手自一体的模式，其承载了部分手动挡的特性和自动挡的特性而形成了自己特有的模式，JFDF的半前后分离模式也是这么一种情况。<br>
JFDF的页面跳转路由采用Srping MVC和Freemarker来实现，直观一点来说的话，也就是导航菜单中的每一个导航项即对应一个单页面的应用（熟悉Angular的同学应该非常熟悉什么叫单页面应用），这个过程采用的是Spring MVC。<br>
当我们进入功能页面之后能，所有的增删改查操作都在这一个页面完成，没有任何页面刷新操作，这样就是我们的前后分离模式。<br>
总的来说结合起来的话就是半前后分离模式(一个笑脸)。

### JFDF功能接口
说完了页面跳转，接着我们来说收系统接口。JFDF所有的数据接口都是采用标准的RESTFul接口来构建，介绍的时候我们就提到过，好的项目必须要有好的标准，所以所有的系统接口开发必须RESTFul,必须RESTFul,必须RESTFul，重要的事情说三遍(关于什么是RESTFul接口，自己翻阅文档），别问我为什么那么霸道，我有我后面的考虑，暂且保密。
#### 接口文档访问地址
http://localhost:8080/swagger.html
#### RESTFul接口样例
##### 查询接口
* **请求：** `HTTPMETHOD=GET`
```
http://localhost:8080/[EntityName]?page=[pageNumber]&size=[sizeNumber]&[param1]=[value1].... 
```
* **响应：**
```
{

  "content": {
  	...'json data'
  },
  "first": true,
  "last": true,
  "number": 0,
  "numberOfElements": 0,
  "size": 0,
  "sort": {},
  "totalElements": 0,
  "totalPages": 0
}
```

### 功能开发N步走!
* **功能配置**

* **创建业务实体**

* **配置业务实体**

* **配置业务实体**

## 版本

## 开发计划





