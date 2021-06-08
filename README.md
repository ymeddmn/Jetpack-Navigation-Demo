
关注公众号学习更多知识
![](https://files.mdnice.com/user/15648/404c2ab2-9a89-40cf-ba1c-02df017a4ae8.jpg)

# 嵌套图
嵌套图顾明思议，就是一个导航图中可以包含其他的导航图。嵌套图的实现方式有两种：

1、直接在navigation导航图中添加<navigation/>标签添加嵌套图

2、使用<include/>标签将其他导航图嵌套到我们的导航图中

嵌套图可以让我们绘制导航图的时候避免重复编写同一个导航图。

举例：

我们的应用第一次进入的时候就会跳转一个登录页面，在个人信息页面也跳转一个登录页面，这个时候我们就需要在SplashActivity的导航图中添加登录页面的导航组件。在个人信息页面也添加导航组件，这样登录组件就被添加了两次

图示如下：

![](https://files.mdnice.com/user/15648/ec81ab8f-8ea4-4b37-a44a-9a1405629cea.png)

###### 使用navigation标签嵌套的方式实现嵌套图

导航图配置：

![](https://files.mdnice.com/user/15648/a0fb9f46-6342-4c0d-870d-9e55560edab9.png)

跳转登录页面代码：


![](https://files.mdnice.com/user/15648/4bae9ac9-bf0a-44f2-8b56-062bb68c2059.png)

###### inclode标签的方式嵌套

主导航图

![主导航图](https://files.mdnice.com/user/15648/b98b4f54-83fd-4e97-9753-4d91732f0518.png)

登录导航图

![登录导航图](https://files.mdnice.com/user/15648/564981f6-ab72-46e3-9643-b65eed1daba2.png)

跳转到登陆页面的代码：
这里navigate

![](https://files.mdnice.com/user/15648/b759e0fa-0669-4bbd-8605-fa863a733f64.png)





