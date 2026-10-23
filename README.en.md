# HDSL

Born out of the narrowest crack, I build my road from history.

[![](https://jitpack.io/v/kkaHeng/hdsl.svg)](https://jitpack.io/#kkaHeng/hdsl)

English | [汉语](README.md)

---

Build Android UIs with **Java code**. Zero XML, fluent API, type-safe — the layout tree grows directly out of your code.

Prerequisite: **Java 8**.

---

## Installation

Declare the JitPack repository in **settings.gradle**:

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
        maven { url 'https://jitpack.io' }   // ← add this line
    }
}
```

Or put it in the `allprojects` block of the root **build.gradle**:

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Then add the dependency in your module's **build.gradle**:

```groovy
dependencies {
    implementation 'com.github.kkaHeng:hdsl:26.9.1'
}
```

> With Kotlin DSL (`settings.gradle.kts`) use `maven("https://jitpack.io")`.
> The version is always shown in the badge above, or check [JitPack](https://jitpack.io/#kkaHeng/hdsl).

---

## Ways to Build a UI

### 1. Source methods: creation plus configuration in one go

The method name is the widget name. The first parameter is always the configurator, and what you get inside it is that widget's reference — write properties straight onto it.

```java
@Override
protected void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    setContentView(build(scroll(root -> {
        root.size(-1, -1).fillViewport();

        root.layout(linear(box -> {
            box.paddingDp(16).vertical();

            box.view(text(tv -> {
                tv.text("Hello, world").textSizeDp(16);
            }));
        }));
    })));
}
```

Every source method accepts three kinds of origin:

```java
text(tv -> {...})                        // default constructor
text(new TextView(this), tv -> {...})    // given instance
text(TextView.class, tv -> {...})        // reflective construction
```

### 2. Instance and Class forms

The entry point eats the same three origins. Instance / class origins only get the generic configurator: `build` and `view` hand you `RefView`, `layout` hands you `RefLayout`. For widget-specific properties, let a source method take the instance or class instead.

```java
// source method
setContentView(build(scroll(root -> {...})));

// instance + configuration
setContentView(build(new LinearLayout(this), root -> {
    root.paddingDp(16);
}));

// widget-specific properties: let a source method take the instance
setContentView(build(linear(new LinearLayout(this), root -> {
    root.view(new TextView(this));
})));

// class + configuration
setContentView(build(LinearLayout.class, root -> {
    root.paddingDp(16);
}));
```

### 3. Mixing, and escaping through ref()

Mix the three origins freely. Any time, `ref()` hands you the raw View — call native APIs as you please.

```java
build(linear(root -> {
    root.layout(frame(new MaterialCardView(this), card -> {
        card.ref().setRadius(card.dp2px(16f));  // native method
        card.elevationDp(1);                    // property on the reference
    }));
}));
```

> Were it not for those typical statically-scanning IDEs, the generics could be written all the more elegantly.

---

## Adding Children

All 5 `addView` overloads accept three origins. The element takes the first slot, the remaining parameters match the native signature:

```java
// element
root.view(text(tv -> {...}));                      // addView(Element)

// instance
root.view(new TextView(this));                     // addView(View)
root.view(new TextView(this), tv -> {...});        // addView(View, Method)

// class
root.view(TextView.class);                         // addView(Class)
root.view(TextView.class, tv -> {...});            // addView(Class, Method)

// extra parameters
root.view(text(tv -> {...}), 0);                   // addView(Element, int)
root.view(text(tv -> {...}), params);              // addView(Element, LayoutParams)
root.view(text(tv -> {...}), 0, params);           // addView(Element, int, LayoutParams)
root.view(text(tv -> {...}), -2, 32);              // addView(Element, int, int)
```

Use `layout(...)` for layouts and `view(...)` for widgets; nest them without limit:

```java
root.layout(linear(box -> {
    box.view(text(tv -> {...}));
    box.layout(frame(f -> {
        f.view(text(tv -> {...}), new FrameLayout.LayoutParams(-2, -2, Gravity.CENTER));
    }));
}));
```

---

## Configuring Properties

Property methods live on the reference object, chainable and self-returning:

```java
root.view(edit(e -> {
    e.size(-1, -2).paddingDp(14, 10, 14, 10)
     .hint("Enter a nickname").maxLength(10)
     .inputType(InputType.TYPE_CLASS_TEXT);
}));

root.view(seek(sb -> {
    sb.size(-1, -2).max(100).progress(50)
      .thumbTint(getColor(R.color.md_primary))
      .onSeekBarChange(listener);
}));
```

Unit rules: every **length** (size / margin / padding / elevation / max width & height) comes in both `xxx()` pixels and `xxxDp()` variants; `textSize()` follows the native semantics (SP), with `textSizeDp` and `textSizePx` alongside it.

---

## Elements at a Glance

```java
// widgets
view  text  button  edit  image  progress  check  radio  switch_button
space  seek  rating  spinner  chrono  web  list  grid_view  search

// layouts
linear  frame  relative  scroll  hscroll  grid
radio_group  table  table_row  switcher
```

---

## License

MIT License

---

## Contact

- Author: Ah Heng
- Email: kkaheng163@163.com
- GitHub: https://github.com/kkaHeng

---

God declared the road ends here, yet life finds a way.
