# JmeterBook

 [个人博客：http://lihao.cf](http://lihao.cf) 
 
 [Jmeter学习资料：http://www.hissummer.com](http://www.hissummer.com) 
 
## 1. 前言
最近的工作中使用Jmeter比较多，为了记录平时遇到的一些问题，决定写一些东西。本文仅供个人学习使用，个人或者企业未授权不得有任何形式的发布或者转载，作者保留著作版权，有侵权部分请联系作者删除修改。 qq:83798029 nighteblis@gmail.com，未经许可禁止转载和下载者的二次分享。任何问题或者建议随时与作者联系  nighteblis@gmail.com。 

这里向大家说明的是，本文并不是针对官方文档一字不差的翻译行为。官方文档非常全面，虽然是英文相信大家就算英文一般借助翻译工具去阅读应该没有任何问题。这里主要还是以分享实际的经验和问题难点为主。为了照顾初学者，写了简单的服务端的代码，可以直接运行配合测试工具使用。同时开篇讲解了基本的应用。
```
本教程使用的Jmeter版本为4.0
```
## 2. 简单的例子开始
###  2.1 Jmeter 介绍
Jmeter是一个开源的性能测试工具，同时可以用来进行功能Debug，功能测试，自动化测试。支持多种多样的测试对象，例如http协议，jdbc，jms等，因为Jmeter支持beanshell，javascript，groovy等脚本和自行开发java插件，所以Jmeter支持通常我们软件行业碰到的一切内容。但因为每个工具或者测试框架都有自己的侧重点，所以我们还是要根据不同的测试对象选用最适用的工具。
1. 下载Jmeter 
```
Jmeter官方下载地址: http://jmeter.apache.org/download__jmeter.cgi
下载之前，请线安装Java 执行环境，即我们所说的jre
```
2. 打开Jmeter工具
```
下载Jmeter解压后，进入bin目录。windows环境可以双击jmeter.bat， linux中可以在命令行中执行者jmeter.sh 或者 jmeter
```
```
开始测试之前请确认被测服务器已经正常运行。
```
[如何运行被测服务器?](targetServer/README.md)

![jmeterBookImage][startup]

3. 在测试计划内添加线程组，http请求(采样器Sampler)和查看结果树(监听器)。。如下图设置http服务器地址，端口号，请求参数。
![jmeterBookImage][sample]

4. 点击绿色的启动按钮。查看结果树，查看测试结果。
![jmeterBookImage][2.1testresult]


## 3. 开始学习
###  3.1 了解Jmeter测试结构
Jmeter打开后，我们可以看到一个空的"__测试计划__"。"__测试计划__"就是我们测试脚本的顶级目录，里面可以设置用户自定义变量，还有个别的设置。值得大家注意的是，我们做功能测试时，建议可以勾选"Functional Test Mode"。 其他的保持默认选项即可。 选中测试计划，右键，可以添加的内容如下"__线程__"，"__配置元件__"，"__监听器__"，"__事件器__"，"__前置处理器__"，"__后置处理器__"，"__断言__"，"__测试片段__"，"__非测试元件__"。 
* 其中”__线程组__“是测试必须存在的，因为所有的测试逻辑均需要在线程组中进行实现。
* __配置元件__，是进行我们一些测试相关设置，用户自定义变量配置等需要，通常也是必不可少的。例如可以定义我们的数据库链接配置，http默认链接配置，http header配置等等。这些配置是全局的。
* __监听器__，监听我们的测试过程，测试结果，主要用于结果报告展示等。
* __前置和后置处理器__，是一个对好兄弟。用于触发前置的行为和后置的行为。在测试中使用，可以减少脚本测试逻辑的复杂性。
* ”__断言__“不必多说，是为了校验我们的测试结果是否是发我们期望的结果。正确的编写断言，才可以让我们真正的依赖这个工具的输出结果。
* "__测试片段__ (__Test Fragment__)"，我们在本章的末尾处再重点介绍。 协作开发时可以使用测试片段，可以大大的减少我们合脚本的冲突。
* “__采样器__”当然了最重要的采样器，更是我们全篇的重点，所有的真正的测试都是由sampler(__采样器__) 完成的， 可以说是核心模块。
![jmeterBookImage][3.1testplan]

###  3.2 为什么使用Jmeter
Jmeter 是一个开源免费的软件，功能强大，具有很强扩展性，可以用来功能测试，性能测试。debug，功能测试时开发的脚本，可以直接用在性能测试上，仅需要进行些数据的准备即可。 Jmeter界面看起来仍然还有些粗糙(java 写ui本来可能就不擅长)，但这不能掩盖其功能的强大。 同时支持命令行的执行，可以进行高强度的性能压力测试，也可以无缝的和Jenkins等持续集成平台结合。总的来说，jmeter跨平台，开源免费，有众多的使用者可以分享经验，且不需要承担使用盗版软件的风险。Jmeter还可以扩展开发插件，所以Jmeter可以支持任何类型的测试，包括selenium 的web ui测试。虽然并不是适合所有测试类型的最好工具，但强大的扩展性决定了其有无限大的潜力。  （这里顺便提一下 postman, postman的介绍是用于api快速开发的工具， 所以当然可以用来http restfull 的api debug和测试，同时他支持cmdline的执行， 所以也可以用来实现自动化。 but！！，重要的事情说一遍，  简单的阅读了下文档postman不支持其他的协议. 例如我要进行jdbc的调用，那该怎么办呢？ 兴许可以通过script的方式（postman官方文档提到这基于nodejs）。但是肯定需要二次开发了。 包括其他的一些高级使用方法，都必须通过script的方式进行pre 和 post 执行。  所以给postman下的定义是， 针对于开发使用的http api 开发工具， 还可以自动生成调用代码，可以搭建部署mockserver 可以实现一些不需要其他协议参与的api自动化。  如果你只是关注api不关心底层数据库，redis，及一些外延的东西，那么使用postman同样是一个不错的选择 ）

###  3.3 Jmeter 基础功能
####      3.3.1 线程组
如开篇的介绍，线程组是我们测试计划中必须存在的。针对与功能测试， 线程组的线程数设置为1。 性能测试则根据自己的测试需要，设置对应的线程数（一个线程可以代表一个用户的并发测试， 有10个线程组则就是10个用户并发测试）。针对与功能测试， 通常我们的设定如下： 线程数是1, 爬升peried是1,循环次数是1。 线程组遇到测试错误的动作， 则可以根据你的期望， 可以设定为”继续执行“，”发起下一个线程（备注：功能测试时通常不需要选择此项，因为我们只跑一个线程，遇到错误需要debug，再执行一遍意义不大）“，”停止线程","停止测试“，“立即停止测试”。 
```
备注： 通常我们debug性能测试脚本时，可以勾选或者停止测试选项 这样遇到的问题测试立即停止下来，可以及时解决该脚本问题。 
```
针对于性能测试， 我们通常设定如下：线程数设置为本次期望的并发用户数, 爬升peried根据期望的用户爬升速度机型设置，即每秒爬升的用户,循环次数设定为永久循环（则需要手动停止性能测试）， 或者设定一个期望的循环数（循环数不能太小， 因为可能用户还没有爬升到最大时，已经有达到最大的循环次数的线程退出）。 线程组遇到测试错误的动作， 则可以根据你的期望， 可以设定为”继续执行“，”发起下一个线程“，”停止线程（性能测试时通常不会选择此项，debug除外）","停止测试（性能测试时通常不会选择此项，debug除外）“，“立即停止测试（性能测试时通常不会选择此项，debug除外）”。 
![jmeterBookImage][3.3.1.threadgroup]
```
线程组除了Jmeter自带的线程组外， Jmeter有其他一些插件提供的线程组可以有其他更丰富的设置项。 Jmeter安装插件，目前可以通过插件管理器进行安装。 
所以我们可以安装“插件管理器” 然后再安装我们期望的三方插件。 安装方法详见https://jmeter-plugins.org/install/Install/。 
安装之后，我们可以通过插件管理器安装我们希望使用的三方threadgroup插件。 https://jmeter-plugins.org/wiki/Start/
```
####      3.3.2 采样器
采样器，是实际发起测试的核心单元。 例如我们要发起一个http请求， 一个jdbc请求。 这些全部包含在Jmeter放在采样器中。 如果我们要测试一个http接口， 则我们无意要有一个http采样器。 下图是一个jdbc请求采样器。
![jmeterBookImage][3.3.2.sampler]
我们可以添加不同的采样器， 可以很明显的因为采样器的协议和类型不同，每一种请求（采样器） 的设置定义都完全不同。 例如http请求，除了需要定义ip端口号外，需要定义http方法，路径，以及http请求数据。而jdbc请求则需要定义要执行的sql脚本。

本章我们将会主要介绍http和jdbc， 通常互联网服务通常测试用到最多的是这2种类型的协议。 除此之外，还有mq，内存kv服务器（如redis），dubbo集群服务， 最后我们会粗略的讨论下此类测试对象如何使用jmeter进行测试。  这里还有一个debug sampler，用于debug时，可以返回所有的用户变量定义 

#####	3.3.2.1 http sampler
这个是用来发起http和https请求。 如果你的脚本中会发起多个http请求，那么你可能需要“HTTP Request Defaults”（http 请求默认设置），这样多个http请求会使用同样的设置参数。
* http请求实现，推荐使用HTTPClient4。
* Server:设定我们要请求的服务器。（如果你设定了http request defaults）则不需要设定。
* Port:设定端口号	
* timeout,设定相应的timeout时间. 超时后会提示超时错误,不会继续等待.
* 协议,通常我们会用到get,post,put,delete等等方法. 依据我们的测试服务器提供的api文档设定即可.
* Content Encoding 通常我们设定为utf-8 , 如果需要修改则根据实际情况修改.
* Use multipart/form-data for HTTP POST, 这个需要multipart提交时才会用到. 
```
什么是multipart, multipart是指我们提交的请求内容包含多个部分"part"的意思,每一个部分都有自己特定的http请求头. 例如我们需要上传一个文件和一个json报文. 则文件部分的content-type是一个二进制文件类型, 而json报文的content-type是一个application/json 类型. 这样,我们就可以上传文件或者多种不同类型部分的数据请求. 通常我们使用多是带有参数报文和文件同时上传(也就是请求到)服务器的情况. 
```
* Path就是我们请求的uri path路径. 
* 通常我们可以直接使用body类型输入我们要post的body内容. 这样比一个一个书写Parameters要方便的多.(但需要注意的是, 针对于body的content-type需要设置正确,否则服务器可能不能正确解析请求的内容数据).

#####	3.3.2.2 jdbc sampler
这个采样器用来在某个数据服务器上执行一个sql. 在使用此之前,必须要进行数据库的链接设置(这个是必须的).Jmeter 数据库链接的配置为"JDBC Connection Configuration ". 这里设置了我们的链接名称, 则在jdbc sampler中使用我们需要链接的数据库即可. 
* Variable Name of Pool declared in JDBC Connection Configuration , 这个就是我们在jdbc链接设置中的变量. 这个必须填写正确,否则无法连接到数据库. 
* Query Type: 通常我们经常用的是select, update. (查询和更新).  delete和update语句使用update. select使用select类型. 
* SQL Query: 编写我们的sql语句即可, 经过测试多条语句不支持. 仅支持一条语句.  该语句最后可以用"；"来表示语句的结束. 
* Variable Names: 用于我们select 后,从数据库的查询返回放置到变量里, 以在后续的测试内容中使用. 
```
select a from table;
如果variable name设定为a(如果多个name时,需要用逗号分隔),则a则是该查询结果的变量. a_1 则是第一条记录的结果, a_2 是第二条记录结果. 依次类推.  a_#则是查询到的结果数. 
```

#####  3.3.2.3 beanshell&jsr223 sampler
我们将在嵌入式脚本专门针对beanshell和jsr223 进行讲解.  beanshell, jsr223 是用来进行自定义编写一些脚本. 可以在jmeter执行时,现场运行.  当所有的sampler不能满足我们的需求时,通常我们可以考虑使用此类型的sampler.  这里我们更建议使用jsr223 sampler 而不是beanshell. (我们也还可以选择java sampler, 个人建议如果使用beanshell和jsr223 能实现的情况, 先不要使用java sampler, 因为比较重， 后续将会针对java sampler进行专门例子讲解)
```
beanshell vs jsr223 vs java sampler的区别:
https://www.blazemeter.com/blog/beanshell-vs-jsr223-vs-java-jmeter-scripting-its-performance

根据性能对比, 建议选择jsr223/groovy 和 java sampler  不要使用 beanshell . 

```

#####  3.3.2.4 Debug sampler

Debug sampler 看名字便知是用来debug使用的。他进行的请求时 JMeterVariables， 即列出所有当前执行环境中的所有定义的Jmeter 变量。 Jmeter变量是我们串联我们的测试脚本最重要的内容，用来完成传递我们的测试业务逻辑内容。 Jmeter变量主要是有系统默认的变量， 用户自定义变量vars 和 properties。在3.4.2.1 会对用户自定义配置变量介绍， 在3.5章中，会专门针对vars和properties 进行介绍。  需要你注意的是 这些不同的变量设置的区别。  这里简单的区分下  用户自定义变量是全局的  vars的是在同一个线程组内共享， properties也是全局，可以通过执行时外部传入，而不需要在jmeter脚本中事先硬编码。 

####      3.3.3 监听器 
监听器主要用来测试结果展示和问题Debug。 例如我们记录所有采样器执行的请求和结果，便于我们debug使用。 经常用的，功能测试使用"查看结果树" （性能测试中，建议此监听器关闭或者设置为仅日志错误）。  性能测试中使用 samary report （汇总结果报告） 当然还有各种各样的结果报表和图形展示，利于分析性能测试结果 如TPS ， cpu/内存监控等。


###  3.4 jmeter elements

####      3.4.2 configuration
#####     3.4.2.1 用户自定义的变量

用户自定义的变量。 用法很简单定义变量名称， 定义变量的值。（变量的值可以用变量或者函数来表达） 。
如下图
![jmeterBookImage][3.4.2.1.userdefinedvars]

#####     3.4.2.2 http默认设置
http默认设置以后，脚本里所有（包括所有线程组里面的）http请求均会默认使用这里的配置，除非自己进行了覆盖。 http默认设置是为了让我们的后续脚本减少填写请求服务地址和端口号， 利于提高开发效率。
#####     3.4.2.3 jdbc链接配置
jdbc链接配置，即初始化一个数据库连接池（包括设置数据库访问的地址，用户名密码，链接池的大小等等）。 后续使用jdbc 的请求时， 输入我们要是用的jdbc连接池即可。 
如下图示例：
![jmeterBookImage][3.4.2.3.jdbcconfiguration]
####      3.4.3 timer
##### 3.4.3.1 Synchronizing Timer(集结点)
中文我们可以称为集结点或者集合点（loadrunner的专业叫法）：简单来理解一下，虽然我们的“性能测试”理解为“多用户并发测试”，但真正意义上并发是不存在的，为了更真实的实现瞬时并发，我们可以在需要压力的地方设置集合点。（线程池在创建准备线程时是需要一定的处理时间，因此处理时间的不等长，会使得线程准备好发起测试的时间不能保证为同一时间）
那么设置集结点的意义，比如用户登录场景。输入用户名和密码后，所有的用户都喊1,2,3后同时点击登录按钮（假设所有的人反射弧都一样长呵呵）。

注意的地方：

1.  集结的group用户数量一定要比线程组的数量小。 否则因为无法集结到设定的用户，导致测试无法进行。
2.  集结的用户数和线程组并发用户数的关系。
```
例如线程组设定的用户是50，  集结点设置的用户组数量是25， 那么当集结了25个用户后，就会立即开始测试。 当集结了下一组25个用户后，会立即开始这25个用户的测试。
```
下图是当集结点没有启用的时候， 发起的http请求的发起时间，是相差比较大的。

下图是集结点启用的时候， 可以发现http请求的发起时间都在100-200毫秒内发起。这就是我们集结点的意义所在。


####      3.4.4 preProcessor 
####      3.4.5 postProcessor
####      3.4.6 assert
### 3.5 Jmeter functions & varviables 
这里有必要单独一节，介绍一下Properties和vars的区别 
(Properties vs vars)
1. property 是全局变量， var作用空间则是线程内。 线程间的资源变量共享，必须使用property来实现。 
2. property可以通过jmeter 命令行执行时通过参数传入， 或者通过在jmeter.properties 中定义。

vars - ( JMeterVariables) - gives read/write access to variables:

vars.get(key);
vars.put(key,val);

vars.putObject("OBJ1",new Object());

vars.getObject("OBJ2");

props - (JMeterProperties - class java.util.Properties):

props.get("START.HMS");
props.put("PROP1","1234");

reference: https://www.vskills.in/certification/tutorial/software-testing/jmeter-properties-and-variables/
https://stackoverflow.com/questions/38845168/what-is-different-between-props-and-vars-object-in-jmeter

### 3.6 Jmeter scripts  
#### 3.6.1 （待编辑）
groovy vs beanshell
reference: https://dzone.com/articles/groovy-vs-beanshell-making-the-right-decision 
### 3.7 Jmeter plugins
 
## 4.Practice
###  4.1 http protocal
###  4.2 jmeter http sampler
###  4.3 http related configuration
####    4.3.1 coockies
####    4.3.2 header
  
## 5.advanced
 
###  5.1 Creating Modular / Reusable Test Scripts  
        http://www.testautomationguru.com/jmeter-modularizing-test-scripts/
###  5.2 jmeter java sampler
###	5.3 Jmeter scripts practice
###	5.4 jmeter distributed
###	5.5 jmeter automation & jenkins
	
	
[startup]: ./docs/images/startup.png "启动Jmeter"
[sample]: docs/images/sample.png "开始测试"
[2.1testresult]: docs/images/2.1testResult.png "测试结果"
[3.1testplan]: docs/images/3.1testplan.png "测试计划"
[3.3.1.threadgroup]: docs/images/3.3.1.threadgroup.png "线程组"
[3.3.2.sampler]: docs/images/3.1.2.sampler.png "采样器"
[3.4.2.1.userdefinedvars]: docs/images/3.4.2.1.userdefinedvars.png "用户自定义的变量"
[3.4.2.3.jdbcconfiguration]: docs/images/3.4.2.3.jdbcconfiguration.png "JDBC链接配置"
