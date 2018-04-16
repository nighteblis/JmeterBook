# JmeterBook
```
 [http://lihao.cf](http://lihao.cf)
```

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
![alt text][startup]

3. 在测试计划内添加线程组,http请求(采样器Sampler)和查看结果树(监听器). 如下图设置http服务器地址,端口号,请求参数.
![alt text][sample]

4. 点击绿色的启动按钮. 查看结果树, 查看测试结果.
![alt text][2.1testresult]

```
测试之前请确认被测服务器已经正常运行, [如何运行被测服务器请看这里](targetServer/)
```

## 3. 开始学习
###  3.1 Jmeter 介绍
###  3.2 为什么使用Jmeter
###  3.3 Jmeter 基础功能
####      3.3.1 线程组
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
