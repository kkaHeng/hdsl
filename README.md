# HDSL

从狭缝中诞生，以历史筑道途。

[![](https://jitpack.io/v/kkaHeng/hdsl.svg)](https://jitpack.io/#kkaHeng/hdsl)

汉语 | [English](README.en.md)

---

用 **Java 代码**写 Android 界面，不用 XML。

使用前提：**Java 8**。

---

## 安装

### 1. 添加仓库

打开项目根目录的 `settings.gradle`，在 `repositories` 里加一行：

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }   // 加这一行
    }
}
```

### 2. 添加依赖

**方式一**：直接写坐标，加在模块的 `build.gradle`：

```gradle
dependencies {
    implementation 'com.github.kkaHeng:hdsl:26.10.1'
}
```

**方式二**：用版本目录（`gradle/libs.versions.toml`），与方式一二选一。

先登记：

```toml
[versions]
hdsl = "26.10.1"

[libraries]
hdsl = { group = "com.github.kkaHeng", name = "hdsl", version.ref = "hdsl" }
```

再在模块的 `build.gradle` 里引用：

```gradle
dependencies {
    implementation libs.hdsl
}
```

版本号看顶部徽章，或[这里](https://jitpack.io/#kkaHeng/hdsl)。

---

## 第一个界面

新建 Activity，实现 `HDSL`，在 `onCreate` 里写：

```java
public class MainActivity extends AppCompatActivity implements HDSL {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(build(text(tv -> {
            tv.text("你好，世界")
              .textSizeDp(24);
        })));
    }
}
```

`build(...)` 返回创建好的 View，直接交给 `setContentView`。

---

## 放多个控件

用 `linear(...)` 建一个竖排布局，再往里加子控件：

```java
setContentView(build(linear(root -> {
    root.vertical()
        .paddingDp(24);

    root.view(text(tv -> {
        tv.text("标题")
          .textSizeDp(24);
    }));

    root.view(text(tv -> {
        tv.text("这是一行正文。")
          .textSizeDp(16);
    }));
})));
```

`root.view(...)` 添加控件，`root.layout(...)` 添加布局。

---

## 嵌套布局

布局可以继续往里套：

```java
root.layout(linear(row -> {
    row.horizontal();

    row.view(text(tv -> {
        tv.text("左");
    }));

    row.view(text(tv -> {
        tv.text("右");
    }));
}));
```

---

## 设置属性

配置器里拿到的就是该控件的引用，属性直接写在它上面：

```java
root.view(text(tv -> {
    tv.text("提交")
      .textSizeDp(16)
      .paddingDp(12, 8, 12, 8)
      .marginTopDp(16)
      .textColor(0xFFFFFFFF)
      .backgroundColor(0xFF3D5A80);
}));
```

尺寸都有像素与 DP 两套写法：

```java
padding(12)      paddingDp(12)
size(48)         sizeDp(48)
margin(8)        marginDp(8)
```

`textSize` 默认按 SP 计算，另有 `textSizeDp`、`textSizePx`。

---

## 处理交互

监听器写在属性链里：

```java
root.view(text(tv -> {
    tv.text("点我")
      .paddingDp(12, 8, 12, 8)
      .onClick(v -> Toast.makeText(this, "点到了", Toast.LENGTH_SHORT).show());
}));
```

---

## 拿到原生控件

需要原生方法时，用 `ref()`：

```java
root.view(text(tv -> {
    tv.text("圆角背景")
      .paddingDp(12);

    GradientDrawable bg = new GradientDrawable();
    bg.setColor(0xFFE8F0FE);
    bg.setCornerRadius(tv.dp2px(12)); // dp 转像素

    tv.ref().setBackground(bg);       // 原生 View
}));
```

---

## 使用已有实例

控件已经 new 好了，可以直接传进去：

```java
TextView title = new TextView(this);
title.setText("现成的 TextView");

setContentView(build(linear(root -> {
    root.view(title);
})));
```

也可以只传 `Class`，由框架创建：

```java
setContentView(build(linear(root -> {
    root.view(TextView.class);
})));
```

> 这两种写法的配置器只能设通用属性（尺寸、边距、背景、监听等）。控件专属属性请用 `text(...)`、`linear(...)` 这类方法。

---

## 完整示例

一个可滚动的页面：

```java
public class MainActivity extends AppCompatActivity implements HDSL {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(build(scroll(root -> {
            root.fillViewport();

            root.layout(linear(page -> {
                page.vertical()
                    .paddingDp(24);

                page.view(text(tv -> {
                    tv.text("我的页面")
                      .textSizeDp(28)
                      .textColor(0xFF1A1C1E);
                }));

                page.view(text(tv -> {
                    tv.text("这里是正文。")
                      .textSizeDp(16)
                      .marginTopDp(8)
                      .textColor(0xFF44474E);
                }));

                page.layout(frame(card -> {
                    card.paddingDp(16)
                        .marginTopDp(16)
                        .backgroundColor(0xFFE8F0FE);

                    card.view(text(tv -> {
                        tv.text("卡片内容")
                          .textSizeDp(16);
                    }));
                }));

                page.view(text(button -> {
                    button.text("按钮")
                          .textSizeDp(16)
                          .paddingDp(12, 8, 12, 8)
                          .marginTopDp(16)
                          .textColor(0xFFFFFFFF)
                          .backgroundColor(0xFF3D5A80)
                          .onClick(v -> Toast.makeText(this, "点到了", Toast.LENGTH_SHORT).show());
                }));
            }));
        })));
    }
}
```

---

> 要不是那些典型的静态扫描 IDE，泛型这套东西本可以写得更优雅。

---

## 许可证

MIT License

## 联系方式

- 作者：阿恒
- 邮箱：kkaheng163@163.com
- GitHub：https://github.com/kkaHeng

---

神说前方无路，生命自有出路。