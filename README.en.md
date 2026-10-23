# HDSL

Born out of the narrowest crack, I build my road from history.

[![](https://jitpack.io/v/kkaHeng/hdsl.svg)](https://jitpack.io/#kkaHeng/hdsl)

English | [汉语](README.md)

---

Write Android UIs with **Java code**, no XML.

Prerequisite: **Java 8**.

---

## Installation

### 1. Add the repository

Open the project-level `settings.gradle` and add one line to `repositories`:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }   // add this line
    }
}
```

### 2. Add the dependency

**Option 1**: put the coordinates directly in your module's `build.gradle`:

```gradle
dependencies {
    implementation 'com.github.kkaHeng:hdsl:26.10.1'
}
```

**Option 2**: use the version catalog (`gradle/libs.versions.toml`) — pick one of the two.

Declare it first:

```toml
[versions]
hdsl = "26.10.1"

[libraries]
hdsl = { group = "com.github.kkaHeng", name = "hdsl", version.ref = "hdsl" }
```

Then reference it in your module's `build.gradle`:

```gradle
dependencies {
    implementation libs.hdsl
}
```

The latest version is shown in the badge above, or [here](https://jitpack.io/#kkaHeng/hdsl).

---

## Your first screen

Create an Activity, implement `HDSL`, and write the following in `onCreate`:

```java
public class MainActivity extends AppCompatActivity implements HDSL {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(build(text(tv -> {
            tv.text("Hello, world")
              .textSizeDp(24);
        })));
    }
}
```

`build(...)` returns the created View, ready for `setContentView`.

---

## Adding several widgets

Build a vertical layout with `linear(...)`, then add children to it:

```java
setContentView(build(linear(root -> {
    root.vertical()
        .paddingDp(24);

    root.view(text(tv -> {
        tv.text("Title")
          .textSizeDp(24);
    }));

    root.view(text(tv -> {
        tv.text("A line of body text.")
          .textSizeDp(16);
    }));
})));
```

`root.view(...)` adds a widget, `root.layout(...)` adds a layout.

---

## Nesting layouts

Layouts can be nested:

```java
root.layout(linear(row -> {
    row.horizontal();

    row.view(text(tv -> {
        tv.text("Left");
    }));

    row.view(text(tv -> {
        tv.text("Right");
    }));
}));
```

---

## Setting properties

Inside the configurator you get that widget's reference — write properties straight onto it:

```java
root.view(text(tv -> {
    tv.text("Submit")
      .textSizeDp(16)
      .paddingDp(12, 8, 12, 8)
      .marginTopDp(16)
      .textColor(0xFFFFFFFF)
      .backgroundColor(0xFF3D5A80);
}));
```

Every length comes in pixels and DP:

```java
padding(12)      paddingDp(12)
size(48)         sizeDp(48)
margin(8)        marginDp(8)
```

`textSize` uses SP by default; `textSizeDp` and `textSizePx` are also available.

---

## Handling interaction

Listeners go straight into the chain:

```java
root.view(text(tv -> {
    tv.text("Tap me")
      .paddingDp(12, 8, 12, 8)
      .onClick(v -> Toast.makeText(this, "Tapped", Toast.LENGTH_SHORT).show());
}));
```

---

## Reaching the native widget

When you need a native method, use `ref()`:

```java
root.view(text(tv -> {
    tv.text("Rounded background")
      .paddingDp(12);

    GradientDrawable bg = new GradientDrawable();
    bg.setColor(0xFFE8F0FE);
    bg.setCornerRadius(tv.dp2px(12)); // dp to px

    tv.ref().setBackground(bg);       // the raw View
}));
```

---

## Using an existing instance

If the widget is already constructed, just hand it over:

```java
TextView title = new TextView(this);
title.setText("An existing TextView");

setContentView(build(linear(root -> {
    root.view(title);
})));
```

Or pass only the `Class` and let the framework create it:

```java
setContentView(build(linear(root -> {
    root.view(TextView.class);
})));
```

> Both forms give the configurator generic properties only (size, margins, background, listeners…). For widget-specific properties use `text(...)`, `linear(...)` and friends.

---

## Full example

A scrollable page:

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
                    tv.text("My page")
                      .textSizeDp(28)
                      .textColor(0xFF1A1C1E);
                }));

                page.view(text(tv -> {
                    tv.text("Body text goes here.")
                      .textSizeDp(16)
                      .marginTopDp(8)
                      .textColor(0xFF44474E);
                }));

                page.layout(frame(card -> {
                    card.paddingDp(16)
                        .marginTopDp(16)
                        .backgroundColor(0xFFE8F0FE);

                    card.view(text(tv -> {
                        tv.text("Card content")
                          .textSizeDp(16);
                    }));
                }));

                page.view(text(button -> {
                    button.text("Button")
                          .textSizeDp(16)
                          .paddingDp(12, 8, 12, 8)
                          .marginTopDp(16)
                          .textColor(0xFFFFFFFF)
                          .backgroundColor(0xFF3D5A80)
                          .onClick(v -> Toast.makeText(this, "Tapped", Toast.LENGTH_SHORT).show());
                }));
            }));
        })));
    }
}
```

---

> Were it not for those typical statically-scanning IDEs, the generics could be written all the more elegantly.

---

## License

MIT License

## Contact

- Author: Ah Heng
- Email: kkaheng163@163.com
- GitHub: https://github.com/kkaHeng

---

God declared the road ends here, yet life finds a way.