# HDSL

从狭缝中诞生，以历史筑道途。

[![](https://jitpack.io/v/kkaHeng/hdsl.svg)](https://jitpack.io/#kkaHeng/hdsl)

汉语 | [English](README.en.md)

---

用 **Java 代码**构建 Android UI 的 DSL 框架。零 XML、链式 API、类型安全，布局结构直接从代码里长出来。

使用前提：**Java 8**。

---

## 引入依赖

在 **settings.gradle** 里声明 JitPack 仓库：

```groovy
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }   // ← 加这一行
    }
}
```

仓库也可以写在根目录 **build.gradle** 的 `allprojects` 里：

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

然后在模块的 **build.gradle** 里添加依赖：

```groovy
dependencies {
    implementation 'com.github.kkaHeng:hdsl:26.9.1'
}
```

> Kotlin DSL（`settings.gradle.kts`）写法为 `maven("https://jitpack.io")`。
> 版本号以顶部徽章为准，或查看 [JitPack](https://jitpack.io/#kkaHeng/hdsl)。

---

## 构建 UI 的方式

### 一、来源方法：创建带上配置，一次给全

方法名就是控件名。第一个参数永远是配置器，里面拿到的就是该控件的引用，属性直接往上写。

```java
@Override
protected void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    setContentView(build(scroll(root -> {
        root.size(-1, -1).fillViewport();

        root.layout(linear(box -> {
            box.paddingDp(16).vertical();

            box.view(text(tv -> {
                tv.text("你好，世界").textSizeDp(16);
            }));
        }));
    })));
}
```

每个来源方法都有三种创建来源：

```java
text(tv -> {...})                        // 默认构造
text(new TextView(this), tv -> {...})    // 给定实例
text(TextView.class, tv -> {...})        // 反射构造
```

### 二、实例版与 Class 版

入口同样吃三种来源。实例 / 类来源的配置器只有通用能力：`build` 与 `view` 给到 `RefView`，`layout` 给到 `RefLayout`。想要控件专属属性，就让来源方法吃下实例或类。

```java
// 来源方法
setContentView(build(scroll(root -> {...})));

// 实例 + 配置
setContentView(build(new LinearLayout(this), root -> {
    root.paddingDp(16);
}));

// 专属属性：来源方法吃下实例
setContentView(build(linear(new LinearLayout(this), root -> {
    root.view(new TextView(this));
})));

// 类 + 配置
setContentView(build(LinearLayout.class, root -> {
    root.paddingDp(16);
}));
```

### 三、混用与 ref() 逃逸

三种来源任意混搭。任何时刻用 `ref()` 拿到原始 View，原生 API 随便调。

```java
build(linear(root -> {
    root.layout(frame(new MaterialCardView(this), card -> {
        card.ref().setRadius(card.dp2px(16f));  // 原生方法
        card.elevationDp(1);                    // 属性挂在引用上
    }));
}));
```

> 要不是那些典型的静态扫描 IDE，泛型这套东西本可以写得更优雅。

---

## 添加子控件

5 种 `addView` 重载，每种都有三种来源。构件（Element）占第一位，其余参数与原生一致：

```java
// 构件
root.view(text(tv -> {...}));                      // addView(Element)

// 实例
root.view(new TextView(this));                     // addView(View)
root.view(new TextView(this), tv -> {...});        // addView(View, Method)

// 类
root.view(TextView.class);                         // addView(Class)
root.view(TextView.class, tv -> {...});            // addView(Class, Method)

// 附加参数
root.view(text(tv -> {...}), 0);                   // addView(Element, int)
root.view(text(tv -> {...}), params);              // addView(Element, LayoutParams)
root.view(text(tv -> {...}), 0, params);           // addView(Element, int, LayoutParams)
root.view(text(tv -> {...}), -2, 32);              // addView(Element, int, int)
```

布局用 `layout(...)`，控件用 `view(...)`，可无限嵌套：

```java
root.layout(linear(box -> {
    box.view(text(tv -> {...}));
    box.layout(frame(f -> {
        f.view(text(tv -> {...}), new FrameLayout.LayoutParams(-2, -2, Gravity.CENTER));
    }));
}));
```

---

## 配置属性

属性方法全部挂在引用对象上，链式调用、返回自身：

```java
root.view(edit(e -> {
    e.size(-1, -2).paddingDp(14, 10, 14, 10)
     .hint("请输入昵称").maxLength(10)
     .inputType(InputType.TYPE_CLASS_TEXT);
}));

root.view(seek(sb -> {
    sb.size(-1, -2).max(100).progress(50)
      .thumbTint(getColor(R.color.md_primary))
      .onSeekBarChange(listener);
}));
```

单位规则：**长度量**（尺寸 / 边距 / 内边距 / 阴影 / 最大宽高）都有 `xxx()` 像素与 `xxxDp()` 双版本；`textSize()` 遵循原生语义（SP），另有 `textSizeDp` 与 `textSizePx`。

---

## 构件一览

```java
// 控件
view  text  button  edit  image  progress  check  radio  switch_button
space  seek  rating  spinner  chrono  web  list  grid_view  search

// 布局
linear  frame  relative  scroll  hscroll  grid
radio_group  table  table_row  switcher
```

---

## 许可证

MIT License

---

## 联系方式

- 作者：阿恒
- 邮箱：kkaheng163@163.com
- GitHub：https://github.com/kkaHeng

---

神说前方无路，生命自有出路。
