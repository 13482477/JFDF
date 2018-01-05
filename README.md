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
作为一名Spring社区的一名死忠粉，JFDF和核心技术也主要都是围绕着以Spring技术为核心来构建的。
### 前端技术
* **依赖管理** `Bower`
* **JQuery** `Bower`

### 后端技术
* **JDK版本** `JDK版本必须>=1.8`，可能是作为处女座的程序员的天性使然，我对于程序代码的要求还是有洁癖的，能简洁的尽量简洁，能少写一行的绝不会写第二行。所以过多的依赖了JDK1.8里面的新特性。
* **依赖管理** `Apache Maven 3.5.0`
* **数据库** `MSQL 5.7.17`，反正我是在这个版本下开发的，其它版本大家可做尝试。
* **启动方式** `Spring Boot`
* **持久化层** `Spring JPA`
* **系统权限** `Spring Security`
* **模板引擎** `Freemark` 为了不依赖与容器，保证项目的独立启动由于Volicty的停止维护以及Thymeleaf的不友好，所以选择了Freemark
* **接口文档** `Springfox & Swagger2`
* **数据校验** `Spring Validation`
* **Json序列化** `Jackson Json`
* **数据库连接池** `Tomcat Jdbc`
* 还有许多就不一一列举了。。。
## JFDF目录结构

## 版本



## JFDF配置说明

## JFDF开发手册
