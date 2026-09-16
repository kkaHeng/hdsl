# HDSL

从狭缝中诞生，以历史筑道途。

[![](https://jitpack.io/v/kkaHeng/hdsl.svg)](https://jitpack.io/#kkaHeng/hdsl)

中文 | [英文](README.en.md)

---

用 **Java 代码**构建 Android UI 的 DSL 框架。零 XML、链式 API、类型安全，布局结构直接从代码里长出来。

使用前提：**Java8**。

> 核心分工一句话：**`build` 管结构、`Tools` 管属性、`Factory` 管创建**。
> 让 `Activity implements HDSL`，三个能力全部到手。

---

## 构建 UI 的方式

HDSL 的所有构建方式，本质上都是同一个套路：**选一个布局当根 → 往里面塞子控件/子布局 → 给每个节点配属性**。
区别只在于"控件从哪来"——有三种来源，对应三种写法。

### 方式一：实例版（自己 new）

```java
setContentView(build(new LinearLayout(this), root -> {
    root.view(new TextView(this), tv -> {
        text(tv).text("你好，世界").textSizeDp(16);
    });
}));
```

直接 `new` 出控件实例，适合复用已有实例、或者构造需要传参的控件（如 `MaterialButton`、`MaterialCardView`）。

### 方式二：Class 版（反射创建）

```java
setContentView(build(LinearLayout.class, root -> {
    root.view(TextView.class, tv -> {
        text(tv).text("你好，世界").textSizeDp(16);
    });
}));
```

只传 `Class`，框架反射创建实例。上下文不用管——HDSL 自动从实现者身上感知（Activity 本身就是 Context）。

### 方式三：Creator 版（工厂常量，最简洁）

```java
setContentView(build(LINEAR, root -> {
    root.view(TEXT, tv -> {
        text(tv).text("你好，世界").textSizeDp(16);
    });
}));
```

用 `Factory` 里的**常量**（本质是方法引用），连 `.class` 和 `new` 都省了。**只负责创建，不负责配置**——配置交给后面的 `Method` 回调。这是最常用的写法。

### 方式四：混用 + ref() 逃逸

三种来源可以任意混搭。任何时候需要原生 API，用 `ref()` 直接拿到原始 View：

```java
setContentView(build(LINEAR, root -> {
    root.layout(new MaterialCardView(this), card -> {
        card.ref().setRadius(view(card).dp2px(16f)); // 原生方法，随时逃逸
        view(card).elevationDp(1);
    });
}));
```

框架只管**构建**。运行期操作（移除、读值、动画……）一律 `ref()` 逃逸到原生 API。

---

## 添加子控件

`RefLayout` 聚合了 **5 种 `addView` 重载**，每种都有 `view(...)`（控件）和 `layout(...)`（布局，可无限嵌套）两个入口：

| addView 重载 | 入口方法 |
|---|---|
| `addView(View)` | `view(...)` / `layout(...)` |
| `addView(View, int)` | `view(..., index)` / `layout(..., index)` |
| `addView(View, LayoutParams)` | `view(..., params)` / `layout(..., params)` |
| `addView(View, int, LayoutParams)` | `view(..., index, params)` / `layout(..., index, params)` |
| `addView(View, int, int)` | `view(..., width, height)` / `layout(..., width, height)` |

而每种入口内部又按"来源 × 是否配置"展开：

```java
root.view(new TextView(this));                 // 实例，不配置
root.view(new TextView(this), tv -> {...});    // 实例，配置
root.view(TextView.class);                     // Class，不配置
root.view(TextView.class, tv -> {...});        // Class，配置
root.view(TEXT);                               // Creator，不配置
root.view(TEXT, tv -> {...});                  // Creator，配置 ← 最常用
```

布局同理，`layout(LINEAR, inner -> { ... })` 里继续 `inner.view(...)`，**无限嵌套**。

---

## 配置属性

`Tools` 工厂按控件类型提供属性工具，全部链式调用、返回自身：

```java
text(tv)                                    // TextView 属性
    .text("你好").textSizeDp(16).textColor(0xFF3D5A80)
    .marginTopDp(8).background(R.drawable.bg).onClick(v -> {...});
```

| 工厂方法 | 控件 | 工厂方法 | 控件 |
|---|---|---|---|
| `view(...)` | View（通用） | `progress(...)` | ProgressBar |
| `text(...)` | TextView | `seek(...)` | SeekBar |
| `edit(...)` | EditText | `rating(...)` | RatingBar |
| `image(...)` | ImageView | `linear(...)` | LinearLayout |
| `check(...)` | CompoundButton | `frame(...)` | FrameLayout |
| `switch_button(...)` | Switch | `relative(...)` | RelativeLayout |
| | | `scroll(...)` | ScrollView |
| | | `grid(...)` | GridLayout |

通用属性（尺寸/边距/内边距/背景/前景/显示/交互/变换/阴影/透明度）都在 `ViewTool` 里，所有工具类继承链共享；`TextViewTool` 往下还有 `EditTextTool`、`CompoundButtonTool`、`SwitchTool`，`ProgressBarTool` 往下有 `SeekBarTool`、`RatingBarTool`，布局工具也有各自专属属性。

> 如果不是某些典型的静态扫描思维 IDE，泛型复杂度还能提高，还能进一步把代码写的更优雅。

---

## 代码示例

```java
public class MainActivity extends AppCompatActivity implements HDSL {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(build(LINEAR, root -> {
            linear(root).size(-1, -1).paddingDp(20, 48, 20, 20).vertical(); // -1=MATCH, -2=WRAP

            root.view(TEXT, title -> {
                text(title).size(-1, -2).text("HDSL").textSizeDp(34)
                        .textColor(getColor(R.color.md_primary)).typeface(Typeface.BOLD);
            });

            root.layout(new MaterialCardView(this), card -> {
                view(card).size(-1, -2).marginTopDp(24).elevationDp(1);
                card.layout(LINEAR, box -> {
                    linear(box).size(-1, -2).paddingDp(20).vertical();
                    box.view(TEXT, t -> text(t).text("框架特性").textSizeDp(16).typeface(Typeface.BOLD));
                });
            });

            root.view(new MaterialButton(this), primary -> {
                view(primary).size(-1, -2).marginTopDp(24).onClick(v -> toast("点了主要操作"));
                text(primary).text("主要操作").textSizeDp(15);
            });
        }));
    }
}
```

---

## 许可证

本项目采用 [MIT License](LICENSE) 开源协议。

---

## 联系方式

- 作者：阿恒
- 邮箱：kkaheng163@163.com
- GitHub：[https://github.com/kkaHeng](https://github.com/kkaHeng)

---

神说前方无路，生命自有出路。