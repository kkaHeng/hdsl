# HDSL

Born out of the narrowest crack, I build my road from history.

[![](https://jitpack.io/v/kkaHeng/hdsl.svg)](https://jitpack.io/#kkaHeng/hdsl)

English | [中文](README.md)

---

Build Android UIs with **Java code**. Zero XML, fluent API, type-safe — the layout tree grows directly out of your code.

Prerequisite: **Java 8**.

> One-liner division of labor: **`build` builds structure, `Tools` configures properties, `Factory` creates widgets**.
> Make your `Activity implements HDSL` and you get all three capabilities at once.

---

## Ways to Build a UI

Every way to build with HDSL is the same recipe: **pick a layout as root → drop in child widgets/layouts → configure each node**.
The only difference is *where the widgets come from* — there are three sources, hence three styles.

### Style 1: Instance (you `new` it)

```java
setContentView(build(new LinearLayout(this), root -> {
    root.view(new TextView(this), tv -> {
        text(tv).text("Hello, World").textSizeDp(16);
    });
}));
```

Create the widget instance yourself. Good for reusing an existing instance, or widgets whose constructors need arguments (e.g. `MaterialButton`, `MaterialCardView`).

### Style 2: Class (reflective creation)

```java
setContentView(build(LinearLayout.class, root -> {
    root.view(TextView.class, tv -> {
        text(tv).text("Hello, World").textSizeDp(16);
    });
}));
```

Just pass the `Class`; the framework reflects an instance. Context is handled automatically — HDSL senses it from the implementer (an Activity is itself a Context).

### Style 3: Creator (factory constants, the most concise)

```java
setContentView(build(LINEAR, root -> {
    root.view(TEXT, tv -> {
        text(tv).text("Hello, World").textSizeDp(16);
    });
}));
```

Use the **constants** in `Factory` (method references under the hood) — no `.class`, no `new`. They **only create, never configure**; configuration goes into the trailing `Method` callback. This is the idiomatic style.

### Style 4: Mixing + `ref()` Escape Hatch

The three sources mix freely. Whenever you need a native API, grab the raw View with `ref()`:

```java
setContentView(build(LINEAR, root -> {
    root.layout(new MaterialCardView(this), card -> {
        card.ref().setRadius(view(card).dp2px(16f)); // native call, escape anytime
        view(card).elevationDp(1);
    });
}));
```

The framework is **build-only**. Runtime operations (remove, read values, animations…) always escape to native APIs via `ref()`.

---

## Adding Child Widgets

`RefLayout` aggregates all **5 `addView` overloads**, each exposing two entry points: `view(...)` (widget) and `layout(...)` (layout, infinitely nestable):

| addView overload | Entry methods |
|---|---|
| `addView(View)` | `view(...)` / `layout(...)` |
| `addView(View, int)` | `view(..., index)` / `layout(..., index)` |
| `addView(View, LayoutParams)` | `view(..., params)` / `layout(..., params)` |
| `addView(View, int, LayoutParams)` | `view(..., index, params)` / `layout(..., index, params)` |
| `addView(View, int, int)` | `view(..., width, height)` / `layout(..., width, height)` |

Each entry expands by "source × configure?":

```java
root.view(new TextView(this));                 // instance, no config
root.view(new TextView(this), tv -> {...});    // instance, config
root.view(TextView.class);                     // Class, no config
root.view(TextView.class, tv -> {...});        // Class, config
root.view(TEXT);                               // Creator, no config
root.view(TEXT, tv -> {...});                  // Creator, config ← most common
```

Same for layouts: inside `layout(LINEAR, inner -> { ... })` you keep calling `inner.view(...)` — **nest forever**.

---

## Configuring Properties

The `Tools` factory offers per-widget property tools, all fluent and returning `this`:

```java
text(tv)                                    // TextView properties
    .text("Hello").textSizeDp(16).textColor(0xFF3D5A80)
    .marginTopDp(8).background(R.drawable.bg).onClick(v -> {...});
```

| Factory method | Widget | Factory method | Widget |
|---|---|---|---|
| `view(...)` | View (generic) | `progress(...)` | ProgressBar |
| `text(...)` | TextView | `seek(...)` | SeekBar |
| `edit(...)` | EditText | `rating(...)` | RatingBar |
| `image(...)` | ImageView | `linear(...)` | LinearLayout |
| `check(...)` | CompoundButton | `frame(...)` | FrameLayout |
| `switch_button(...)` | Switch | `relative(...)` | RelativeLayout |
| | | `scroll(...)` | ScrollView |
| | | `grid(...)` | GridLayout |

Generic properties (size/margin/padding/background/foreground/visibility/interaction/transform/elevation/alpha) live in `ViewTool`, shared down the whole tool inheritance chain; `TextViewTool` branches into `EditTextTool`, `CompoundButtonTool`, `SwitchTool`; `ProgressBarTool` branches into `SeekBarTool`, `RatingBarTool`; layout tools carry their own layout-specific properties.

> Were it not for those typical statically-scanning IDEs, the generics could reach even further, and the code could be written all the more elegantly.

---

## Code Example

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
                    box.view(TEXT, t -> text(t).text("Features").textSizeDp(16).typeface(Typeface.BOLD));
                });
            });

            root.view(new MaterialButton(this), primary -> {
                view(primary).size(-1, -2).marginTopDp(24).onClick(v -> toast("Primary tapped"));
                text(primary).text("Primary").textSizeDp(15);
            });
        }));
    }
}
```

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Contact

- Author: Ah Heng
- Email: kkaheng163@163.com
- GitHub: [https://github.com/kkaHeng](https://github.com/kkaHeng)

---

God declared the road ends here, yet life finds a way.