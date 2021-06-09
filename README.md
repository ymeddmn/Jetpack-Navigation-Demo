关注公众号学习更多知识
![](https://files.mdnice.com/user/15648/404c2ab2-9a89-40cf-ba1c-02df017a4ae8.jpg)

# github地址： https://github.com/ymeddmn/Jetpack-Navigation-Demo.git 
github分支：navigation_operation


# Navigation堆栈操作

## 导航的出栈操作
本章节中的两个方法都是用来出栈导航组件的，区别popBackStack的方式更加精细
### 1、findNavController().navigateUp()
这个方式对导航组件进行出栈简单粗暴，仅仅是把当前栈顶的组件进行出栈，方法的boolean型返回值表示是否出栈成功。如果如果导航失败的话我们可以主动对组件进行出栈进行逻辑上的补救

###### 代码示例

```
            val navigarionResult = findNavController().navigateUp()
            println("是否导航出栈成功：$navigarionResult")
```


### 2、findNavController().popBackStack(R.id.AFragment,false)
这个方法用于执行返回指定页面的操作，例如本例：
进入的时候我们点击AFragment的按钮跳转到BFragment，在BFragment中执行如下代码：


```
 val popBackStack = findNavController().popBackStack(R.id.AFragment,false)
            println("是否返回：$popBackStack")
```

就可以指定返回AFragment。

popBackStack的第二个参数说明：

true：从B返回A的时候会把A出栈，然后重新打开A

false：从B返回A的时候A不会出栈，保留跳转到B之前的状态

###### !!!讲一个 findNavController().popBackStack(R.id.AFragment,true)的坑
代码如下：


1. 从A跳转到B

```
content.findViewById<Button>(R.id.btn_toBFragment).setOnClickListener {
            findNavController().navigate(R.id.action_AFragment_to_BFragment)
        }
```

2. 从B使用popBackStack（R.id.AFragment,true)返回A

```
 val popBackStack = findNavController().popBackStack(R.id.AFragment,true)
            println("是否返回：$popBackStack")
```

3. 再从A使用导航组件跳转到B
```
content.findViewById<Button>(R.id.btn_toBFragment).setOnClickListener {
            findNavController().navigate(R.id.action_AFragment_to_BFragment)
        }
```

第三部代码会报错：

```
 java.lang.IllegalArgumentException: Navigation action/destination com.mage.navigationdemo:id/action_AFragment_to_BFragment cannot be found from the current destination NavGraph(com.mage.navigationdemo:id/navigation) startDestination={Destination(com.mage.navigationdemo:id/AFragment) label=fragment_a class=com.mage.navigationdemo.AFragment}
```

报错的原因就是找不到导航图，

原因分析：我们返回AFragment的时候AFragment被重新创建，不再是根视图了，也就是说新的AFragment是不与我们的navigation导航图文件进行关联的，所以自然无法使用导航组件跳转到BFragment的。

此种情况应该会有解决方案的，目前先不向这个方向探索
