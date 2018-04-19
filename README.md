# JmeterBook

 [http://lihao.cf](http://lihao.cf)


## 1. 前言
最近的工作中使用Jmeter非常多,为了记录平时遇到的一些问题,决定写一些东西.本文可以自行下载仅供学习使用, 作者保留版权, 仅允许在github和作者的博客和网站提供在线浏览,  qq:83798029 nighteblis@gmail.com, 未经许可禁止转载和下载者的二次分享. 任何问题或者建议随时与作者联系  nighteblis@gmail.com.  
```
本教程使用的Jmeter版本为4.0
```
## 2. 简单的例子开始
###  2.1 Jmeter 介绍
Jmeter是一个开源的性能测试工具,同时可以用来进行功能Debug,功能测试, 自动化测试. 支持多种多样的测试对象,例如http协议,jdbc,jms等, 因为Jmeter支持beanshell,javascript,groovy等脚本和自行开发java插件, 所以Jmeter支持通常我们软件行业碰到的一切内容. 但因为每个工具或者测试框架都有自己的侧重点, 所以我们还是要根据不同的测试对象选用最适用的工具.
1. 下载Jmeter 
```
Jmeter官方下载地址: http://jmeter.apache.org/download_jmeter.cgi
下载之前,请线安装Java 执行环境,即我们所说的jre
```
2. 打开Jmeter工具
```
下载Jmeter解压后,进入bin目录. windows环境可以双击jmeter.bat,  linux中可以在命令行中执行者jmeter.sh 或者 jmeter
```
```
测试之前请确认被测服务器已经正常运行.
```
[如何运行被测服务器?](targetServer/README.md)

![alt text][startup]

3. 在测试计划内添加线程组,http请求(采样器Sampler)和查看结果树(监听器). 如下图设置http服务器地址,端口号,请求参数.
![alt text][sample]

4. 点击绿色的启动按钮. 查看结果树, 查看测试结果.
![alt text][2.1testresult]


## 3. 开始学习
###  3.1 了解Jmeter测试结构
Jmeter打开后,我们可以看到一个空的_测试计划_. _测试计划_就是我们测试脚本的顶级目录, 里面可以设置用户自定义变量, 还有个别的设置. 值得大家注意的是, 我们做功能测试时, 建议可以勾选"Functional Test Mode".  其他的保持默认选项即可.  选中测试计划,右键,可以添加的内容如下"_线程_","_配置元件_","_监听器_","_事件器_","_前置处理器_","_后置处理器_","_断言_","_测试片段_","_非测试元件_".  其中_线程组_是测试必须存在的, 因为所有的测试逻辑均需要在线程组中进行实现. _配置元件_,是进行我们一些测试相关设置, 用户自定义变量配置等需要, 通常也是必不可少的.例如可以定义我们的数据库链接配置, http默认链接配置, http header配置等等. 这些配置是全局的. _监听器_,监听我们的测试过程,测试结果, 主要用于结果报告展示等.  _前置和后置处理器_,是一个对好兄弟. 用于出发前置的行为和后置的行为. 在测试中使用,可以减少脚本测试逻辑的复杂性. _断言_不必多说, 是为了校验我们的测试结果是否是发我们期望的结果. 正确的编写断言, 才可以让我们真正的依赖这个工具的输出结果. "_测试片段_ (_Test Fragment_)",我们在本章的末尾处再重点介绍.  协作开发时可以使用测试片段, 可以大大的减少我们合脚本的冲突. 当然了最重要的采样器, 更是我们全篇的重点, 所有的真正的测试都是由sampler(_采样器_) 发起的. 
![alt text][3.1testplan]

###  3.2 为什么使用Jmeter
Jmeter 是一个开源免费的软件, 功能强大, 具有很强扩展性, 可以用来功能测试,性能测试. debug,功能测试时开发的脚本, 可以直接用在性能测试上,仅需要进行些数据的准备即可.  Jmeter界面看起来仍然还有些粗糙(java 写ui本来可能就不擅长), 但这不能掩盖其功能的强大.  同时支持命令行的执行, 可以进行高强度的性能压力测试, 也可以无缝的和Jenkins等持续集成平台结合. 总的来说, jmeter跨平台, 开源免费, 有众多的使用者可以分享经验, 且不需要承担使用盗版软件的风险. Jmeter还可以扩展开发插件, 所以Jmeter可以支持任何类型的测试,包括selenium 的web ui测试. 虽然并不是适合所有测试类型的最好工具,但强大的扩展性决定了其有无限大的潜力. 

###  3.3 Jmeter 基础功能
####      3.3.1 线程组
如开篇的介绍,线程组是我们测试计划中必须存在的. 
####      3.3.2 采样器
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
 
## 4. Practice
###  4.1 http protocal
###  4.2 jmeter http sampler
###  4.3 http related configuration
####    4.3.1 coockies
####    4.3.2 header
  
## 5. advanced
 
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
