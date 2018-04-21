# JmeterBook

 [http://lihao.cf](http://lihao.cf)


## 1.前言
最近的工作中使用Jmeter比较多，为了记录平时遇到的一些问题，决定写一些东西。本文仅供个人学习使用，个人或者企业未授权不得有任何形式的发布或者转载，作者保留著作版权，有侵权部分请联系作者删除修改。 qq:83798029 nighteblis@gmail.com，未经许可禁止转载和下载者的二次分享。任何问题或者建议随时与作者联系  nighteblis@gmail.com。 

这里向大家说明的是，本文并不是针对官方文档一字不差的翻译行为。官方文档非常全面，虽然是英文相信大家就算英文一般借助翻译工具去阅读应该没有任何问题。这里主要还是以分享实际的经验和问题难点为主。为了照顾初学者，写了简单的服务端的代码，可以直接运行配合测试工具使用。同时开篇讲解了基本的应用。
```
本教程使用的Jmeter版本为4.0
```
## 2.简单的例子开始
###  2.1 Jmeter 介绍
Jmeter是一个开源的性能测试工具，同时可以用来进行功能Debug，功能测试，自动化测试。支持多种多样的测试对象，例如http协议，jdbc，jms等，因为Jmeter支持beanshell，javascript，groovy等脚本和自行开发java插件，所以Jmeter支持通常我们软件行业碰到的一切内容。但因为每个工具或者测试框架都有自己的侧重点，所以我们还是要根据不同的测试对象选用最适用的工具。
1.下载Jmeter 
```
Jmeter官方下载地址: http://jmeter.apache.org/download__jmeter.cgi
下载之前，请线安装Java 执行环境，即我们所说的jre
```
2.打开Jmeter工具
```
下载Jmeter解压后，进入bin目录。windows环境可以双击jmeter.bat， linux中可以在命令行中执行者jmeter.sh 或者 jmeter
```
```
测试之前请确认被测服务器已经正常运行。
```
[如何运行被测服务器?](targetServer/README.md)

![alt text][startup]

3.在测试计划内添加线程组，http请求(采样器Sampler)和查看结果树(监听器)。。如下图设置http服务器地址，端口号，请求参数。
![alt text][sample]

4.点击绿色的启动按钮。查看结果树，查看测试结果。
![alt text][2.1testresult]


## 3.开始学习
###  3.1 了解Jmeter测试结构
Jmeter打开后，我们可以看到一个空的"__测试计划__"。"__测试计划__"就是我们测试脚本的顶级目录，里面可以设置用户自定义变量，还有个别的设置。值得大家注意的是，我们做功能测试时，建议可以勾选"Functional Test Mode"。 其他的保持默认选项即可。 选中测试计划，右键，可以添加的内容如下"__线程__"，"__配置元件__"，"__监听器__"，"__事件器__"，"__前置处理器__"，"__后置处理器__"，"__断言__"，"__测试片段__"，"__非测试元件__"。 
* 其中”__线程组__“是测试必须存在的，因为所有的测试逻辑均需要在线程组中进行实现。
* __配置元件__，是进行我们一些测试相关设置，用户自定义变量配置等需要，通常也是必不可少的。例如可以定义我们的数据库链接配置，http默认链接配置，http header配置等等。这些配置是全局的。
* __监听器__，监听我们的测试过程，测试结果，主要用于结果报告展示等。
* __前置和后置处理器__，是一个对好兄弟。用于出发前置的行为和后置的行为。在测试中使用，可以减少脚本测试逻辑的复杂性。
* ”__断言__“不必多说，是为了校验我们的测试结果是否是发我们期望的结果。正确的编写断言，才可以让我们真正的依赖这个工具的输出结果。
* "__测试片段__ (__Test Fragment__)"，我们在本章的末尾处再重点介绍。 协作开发时可以使用测试片段，可以大大的减少我们合脚本的冲突。当然了最重要的采样器，更是我们全篇的重点，所有的真正的测试都是由sampler(__采样器__) 发起的。
![alt text][3.1testplan]

###  3.2 为什么使用Jmeter
Jmeter 是一个开源免费的软件，功能强大，具有很强扩展性，可以用来功能测试，性能测试。debug，功能测试时开发的脚本，可以直接用在性能测试上，仅需要进行些数据的准备即可。 Jmeter界面看起来仍然还有些粗糙(java 写ui本来可能就不擅长)，但这不能掩盖其功能的强大。 同时支持命令行的执行，可以进行高强度的性能压力测试，也可以无缝的和Jenkins等持续集成平台结合。总的来说，jmeter跨平台，开源免费，有众多的使用者可以分享经验，且不需要承担使用盗版软件的风险。Jmeter还可以扩展开发插件，所以Jmeter可以支持任何类型的测试，包括selenium 的web ui测试。虽然并不是适合所有测试类型的最好工具，但强大的扩展性决定了其有无限大的潜力。

###  3.3 Jmeter 基础功能
####      3.3.1 线程组
如开篇的介绍，线程组是我们测试计划中必须存在的。针对与功能测试， 线程组的线程数设置为1。性能测试则根据自己的测试需要，设置对应的线程数（一个线程可以代表有一个用户的并发测试， 有10个线程组则就是10个用户并发测试）。针对与功能测试， 通常我们的设定如下： 线程数是1, 爬升peried是1,循环次数是1。 线程组遇到测试错误的动作， 则可以根据你的期望， 可以设定为”继续执行“，”发起下一个线程（功能测试时通常不需要选择此项，因为我们只跑一个线程，遇到错误需要debug，再执行一遍意义不大）“，”停止线程","停止测试“，“立即停止测试”。 针对于性能测试， 我们通常设定如下：线程数设置为本次期望的并发用户数, 爬升peried根据期望的用户爬升速度机型设置，即每秒爬升的用户,循环次数设定为永久循环（则需要手动停止性能测试）， 或者设定一个期望的循环数（循环数不能太小， 因为可能用户还没有爬升到最大时，已经有达到最大的循环次数的线程退出）。 线程组遇到测试错误的动作， 则可以根据你的期望， 可以设定为”继续执行“，”发起下一个线程“，”停止线程（性能测试时通常不会选择此项，debug除外）","停止测试（性能测试时通常不会选择此项，debug除外）“，“立即停止测试（性能测试时通常不会选择此项，debug除外）”。 
![alt text][3.3.1.threadgroup]
```
线程组除了Jmeter自带的线程组外， Jmeter有其他一些插件提供的线程组可以有其他更丰富的设置项。 Jmeter安装插件，目前可以通过插件管理器进行安装。 所以我们可以安装“插件管理器” 然后再安装我们期望的三方插件。 安装方法详见https://jmeter-plugins.org/install/Install/。 安装之后，我们可以通过插件管理器安装我们希望使用的三方threadgroup插件。 https://jmeter-plugins.org/wiki/Start/
```
####      3.3.2 采样器
采样器，是实际发起测试的核心单元。 例如我们要发起一个http请求， 一个jdbc请求。 这些全部被Jmeter放在采样器中。 如果我们要测试一个http接口， 则我们无意要有一个http采样器。 下图是一个jdbc请求采样器。
![alt text][3.3.1.sampler]
我们可以添加不同的采样器， 可以很明显的因为采样器的协议和类型不同，每一种请求（采样器） 的设置定义都完全不同。 例如http请求，除了需要定义ip端口号外，需要定义http方法，路径，以及http请求数据。而jdbc请求则需要定义要执行的sql脚本。
####      3.3.3 监听器 
###  3.4 jmeter elements
####      3.4.1 controllers
####      3.4.2 configuration
####      3.4.3 timer
####      3.4.4 preProcessor
####      3.4.5 postProcessor
####      3.4.6 assert
### 3.5 Jmeter functions & varviables (property vs vars)
### 3.6 Jmeter scripts  
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
	
	
[startup]: images/startup.png "启动Jmeter"
[sample]: images/sample.png "开始测试"
[2.1testresult]: images/2.1testResult.png "测试结果"
[3.1testplan]: images/3.1testplan.png "测试计划"
[3.1.1.threadgroup]: images/3.1.1.threadgroup.png "线程组"
[3.1.2.sampler]: images/3.1.2.sampler.png "采样器"
