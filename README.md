
# 引言
绿色软件search ”安安安安卓“ learn knowledge nocharge
# 1 github 代码地址

https://github.com/ymeddmn/Jetpack-Navigation-Demo

# 2 Navigation 简单使用

Navigation 是 google Jetpack 组件库的应用导航解决方案，用于 Activity、Fragment 之间进行页面跳转。Navigation 会提供一个 xml 负责管理各个导航组件直接的关系

## 2.1 使用入门

### 2.1.1 简单实现两个 fragment 的跳转

代码所在分支：simple-use

###### 1、添加依赖

```
dependencies {
  def nav_version = "2.3.5"

  // Java language implementation
  implementation "androidx.navigation:navigation-fragment:$nav_version"
  implementation "androidx.navigation:navigation-ui:$nav_version"

  // Kotlin
  implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
  implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

  // Feature module Support
  implementation "androidx.navigation:navigation-dynamic-features-fragment:$nav_version"

  // Testing Navigation
  androidTestImplementation "androidx.navigation:navigation-testing:$nav_version"

  // Jetpack Compose Integration
  implementation "androidx.navigation:navigation-compose:1.0.0-alpha10"
}

```

###### 2、创建导航关系图

在 xml 文件夹下面创建一个 navigation1.xml 文件，用来存放两个 fragment 之间的路由关系，代码如下：

```
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/jjj" <!---->
    app:startDestination="@id/originFragment">

    <fragment
        android:id="@+id/originFragment"
        android:name="com.mage.navigationdemo.OriginFragment"
        android:label="fragment_origin"
        tools:layout="@layout/fragment_origin" >
        <action
            android:id="@+id/action_originFragment_to_destiationFragment"
            app:destination="@id/destiationFragment" />
    </fragment>
    <fragment
        android:id="@+id/destiationFragment"
        android:name="com.mage.navigationdemo.DestiationFragment"
        android:label="fragment_destiation"
        tools:layout="@layout/fragment_destiation" />
</navigation>
```

下面表格是各个重要标签属性的说明

| 属性标签名         |             作用              |
| :----------------- | :---------------------------: |
| navigation         |       导航组件的根标签        |
| startDestination   | 第一次进入页面展示的组件标签  |
| fragment-id        | 视图中 fragment 的 id，导航用 |
| fragment-name      | Fragment 或 Activity 的全类名 |
| action-destination |        目的地的视图 id        |

###### 3、Activity 布局文件配置

在 activity 的布局文件中加入导航组件容器

```
<androidx.fragment.app.FragmentContainerView
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"

        app:defaultNavHost="true"
        app:navGraph="@navigation/nav_graph" />
```

| 属性标签名                     |                            作用                             |
| :----------------------------- | :---------------------------------------------------------: |
| FragmentContainerView-name     |                          固定写法                           |
| FragmentContainerView-navGraph |                      所关联的 xml 视图                      |
| FragmentContainerView-navGraph | 确保可以拦截系统的返回键，如果设置 false 就无法进行回退操作 |

###### 4、创建两个 Fragment

创建 OriginFragment.kt
DestiationFragment.kt 两个 Fragment，这两个 fragment 就是常规的 fragment 创建
在第二条的 navigation 中我们将 OriginFragment 设置为了主 Fragment，既 activity 打开后首次展示的 Fragment

###### 5、实现 OriginFragment 跳转到 DestiationFrament

OriginFragment 中添加按钮点击事件，navigate 可以实现跳转的 DestiationFragment 的效果

```
 btn.setOnClickListener {
            val findNavController = findNavController()
            findNavController.navigate(R.id.destiationFragment)
        }
```

R.id.destiationFragment 即是第 2 步中我们在 navigation 中给 DestiationFrgment 设置的 id

###### 6、最终实现效果展示

![](https://files.mdnice.com/user/15648/c34a950d-13c5-40e0-a9f8-e53379fcb9ab.gif)

### 2.1.2 实现 activity 跳转到 activity 操作

代码实现在分支：activity2activity

2.1.1 中实现的是 Fragment 和 Fragment 直接的路由，本例中我们要实现从一个 Activity 跳转到另一个 Activity 的操作

###### 1、基本配置

本例中实现 MainActivity 跳转到 DesActivity 的操作，MainActivity 中依然要添加一个 FragmentContainerView 控件以及一个 OriginFragment。

###### 2、导航图代码

本例导航图中需要配置 MainActivity 和 DesActivity，DesActivity 就是我们要跳转到的 Activity

```
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/navigation1"
    app:startDestination="@id/originFragment">
    <activity
        android:id="@+id/mainActivity"
        android:name="com.mage.navigationdemo.MainActivity"
        android:label="activity_main"
        tools:layout="@layout/activity_main" />
    <activity
        android:id="@+id/desActivity"
        android:name="com.mage.navigationdemo.DesActivity"
        android:label="activity_des"
        tools:layout="@layout/activity_des" />
    <fragment
        android:id="@+id/originFragment"
        android:name="com.mage.navigationdemo.OriginFragment"
        android:label="fragment_origin"
        tools:layout="@layout/fragment_origin" />
</navigation>
```

###### 3、MainActivity 中点击跳转的代码

MainActivity 中添加按钮点击进行跳转操作：
R.id.nav_host_fragment 就是 MainActivity 布局文件中 FragmentContainerView 的 id，我们通过 id 拿到 NavController，通过 Controller 实现跳转到 DesActivity 的操作

```
 findViewById<Button>(R.id.btn_des).setOnClickListener {
            findNavController(R.id.nav_host_fragment).navigate(R.id.desActivity)
        }
```

###### 4、实现效果

![](https://files.mdnice.com/user/15648/117bfb6d-42f8-4ad4-8cdc-4ed2df7a21e5.gif)

# 3 导航间数据传递

导航间数据传递有两种方式 Bundle 和 Safe Args 两种方式，其中 Safe Args 需要基于 gradle 插件，下面分别介绍一下两种方式：

## 3.1 Bundle 方式传递数据

这个很简单直接上代码

```
 btn.setOnClickListener {
            val findNavController = findNavController()
            findNavController.navigate(R.id.destiationFragment, bundleOf("param1" to "我是AFragment传过来的参数"))
        }
```

## 3.2 Safe Args 方式传递数据

这种方式需要基础插件

该插件可以生成简单的 object 和 builder 类，以便以类型安全的方式浏览和访问任何关联的参数
### 3.2.1 插件集成方法

###### 1 添加跟 gradle 依赖

```
def nav_version = "2.3.5"
classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
```

###### 2 app.gradle中加入插件

```
plugins {
    id 'androidx.navigation.safeargs'
}
```

## 3.2.2 插件说明（参考官方）

启用 Safe Args 后，生成的代码会为每个操作包含以下类型安全的类和方法，以及每个发送和接收目的地。

- 为生成操作的每一个目的地创建一个类。该类的名称是在源目的地的名称后面加上“Directions”。例如，如果源目的地是名为 SpecifyAmountFragment 的 Fragment，则生成的类的名称为 SpecifyAmountFragmentDirections。
  该类会为源目的地中定义的每个操作提供一个方法。

- 对于用于传递参数的每个操作，都会创建一个 inner 类，该类的名称根据操作的名称确定。例如，如果操作名称为 confirmationAction,，则类名称为 ConfirmationAction。如果您的操作包含不带 defaultValue 的参数，则您可以使用关联的 action 类来设置参数值。

- 为接收目的地创建一个类。该类的名称是在目的地的名称后面加上“Args”。例如，如果目的地 Fragment 的名称为 ConfirmationFragment,，则生成的类的名称为 ConfirmationFragmentArgs。可以使用该类的 fromBundle() 方法检索参数。

## 3.2.3 代码
代码所在分支：deliverparams

###### 1、导航图代码

CFragment 的标签体中的<argument>标签就是 CFragment 要接收的数据

```
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/jjj"
    app:startDestination="@id/AFragment">

    <fragment
        android:id="@+id/AFragment"
        android:name="com.mage.navigationdemo.AFragment"
        android:label="fragment_origin"
        tools:layout="@layout/fragment_origin" >
        <action
            android:id="@+id/action_AFragment_to_destiationFragment"
            app:destination="@id/destiationFragment" />

    </fragment>
    <fragment
        android:id="@+id/destiationFragment"
        android:name="com.mage.navigationdemo.BFragment"
        android:label="fragment_destiation"
        tools:layout="@layout/fragment_destiation" />
    <fragment
        android:id="@+id/CFragment"
        android:name="com.mage.navigationdemo.CFragment"
        android:label="fragment_c"
        tools:layout="@layout/fragment_c" >
        <argument
            android:name="data"
            app:argType="string"
            android:defaultValue="1" />
    </fragment>
</navigation>
```

###### 2、源 Fragment 跳转代码

插件会自动生成 CFragmentArgs 参数类

```
btnTC.setOnClickListener {
            AFragmentDirections.actionAFragmentToDestiationFragment()
            val args = CFragmentArgs.Builder().setData("AFragment传输到CFragment的数据").build().toBundle()
            val findNavController = findNavController()
            findNavController.navigate(R.id.CFragment,args)
        }
```

###### 3、目的地 Fragment 解析参数代码

```
  var cFragmentArgs: CFragmentArgs? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let{
            cFragmentArgs = CFragmentArgs.fromBundle(it)
        }
    }
```

###### 4、实现效果

![](https://files.mdnice.com/user/15648/547ebb6f-b4b0-4443-8a41-73a382d9be13.gif)

  后续还会有进阶玩法的文章

