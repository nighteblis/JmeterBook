这个目录是被测服务的代码, 按照如下步骤在本地运行被测服务.

1. 下载后
2. 运行被测服务器.
  (什么是mvn,如何安装? http://maven.apache.org/)
```
cd ${该代码目录位置}/webFactory && mvn spring-boot:run 
```
3. 浏览器输入http://localhost:8080/hello , 返回{"message":"Hello world!"}, 说明服务器已经启动成功.
