package com.ahheng.hdsl.app;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ahheng.hdsl.HDSL;
import com.ahheng.hdsl.md.Method;
import com.ahheng.hdsl.ref.RefLayout;
import com.ahheng.hdsl.ref.RefLinear;
import com.ahheng.hdsl.ref.RefText;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

// HDSL Demo
// 像渲染 Markdown 一样构建整个页面：ScrollView 当根，标题/段落/代码块/引用块/标签应有尽有。
// 每一章都在演示一种构建 UI 的方式，整页覆盖框架全部能力。
public class MainActivity extends AppCompatActivity implements HDSL {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(build(scroll(root -> {
            // 根布局：ScrollView
            root.size(-1, -1).fillViewport();

            root.layout(linear(box -> {
                box.size(-1, -2).paddingDp(24, 48, 24, 24).vertical();

                // ============ 标题区 ============
                h1(box, "HDSL");
                para(box, "用 Java 代码构建 Android UI 的 DSL 框架。零 XML、链式 API、类型安全，布局结构直接从代码里长出来。");
                badge(box, "使用前提：Java 8");
                quote(box, "核心分工：build 管结构 · Element 管构件 · Ref 管属性\n让 Activity implements HDSL，三项能力一次到手");

                // ============ 一、构建 UI 的方式 ============
                h2(box, "一、构建 UI 的方式");
                para(box, "控件从哪来？三种来源，任选一种，都能直接起一棵树。");

                h3(box, "1. 来源方法：创建带上配置，一次给全");
                para(box, "方法名就是控件名。第一个参数永远是配置器，里面拿到的就是该控件的引用，属性直接往上写。");
                code(box, "setContentView(build(scroll(root -> {\n" +
                        "    root.size(-1, -1).fillViewport();\n" +
                        "\n" +
                        "    root.layout(linear(box -> {\n" +
                        "        box.paddingDp(16).vertical();\n" +
                        "        box.view(text(tv -> {\n" +
                        "            tv.text(\"你好，世界\").textSizeDp(16);\n" +
                        "        }));\n" +
                        "    }));\n" +
                        "})));");

                h3(box, "2. 实例版：自己 new");
                para(box, "适合复用已有实例、或构造需要传参的控件（MaterialButton、MaterialCardView 等）。实例 / 类来源的配置器只有通用能力：build 与 view 给到 View 层，layout 给到 ViewGroup 层。");
                code(box, "// 实例 + 配置\n" +
                        "setContentView(build(new LinearLayout(this), root -> {\n" +
                        "    root.paddingDp(16);            // 通用属性随便配\n" +
                        "}));\n" +
                        "\n" +
                        "// 专属属性：让来源方法吃下实例\n" +
                        "setContentView(build(linear(new LinearLayout(this), root -> {\n" +
                        "    root.view(new TextView(this));\n" +
                        "})));");

                h3(box, "3. Class 版：反射创建");
                para(box, "只传 Class，框架反射创建实例。上下文自动感知，Activity 本身就是 Context。需要专属属性时，改用 linear(LinearLayout.class, ...) 这类来源方法。");
                code(box, "// 类 + 配置\n" +
                        "setContentView(build(LinearLayout.class, root -> {\n" +
                        "    root.paddingDp(16);\n" +
                        "}));\n" +
                        "\n" +
                        "// 专属属性：来源方法吃下 Class\n" +
                        "setContentView(build(linear(LinearLayout.class, root -> {\n" +
                        "    root.view(TextView.class);\n" +
                        "})));");

                h3(box, "4. 混用 + ref() 逃逸");
                para(box, "三种来源任意混搭。任何时刻用 ref() 拿到原始 View，原生 API 随便调。下面这张卡片就是实例版 + ref() 的实拍：");
                code(box, "root.layout(frame(new MaterialCardView(this), card -> {\n" +
                        "    card.ref().setRadius(card.dp2px(16f)); // 原生方法\n" +
                        "    card.elevationDp(1);\n" +
                        "}));");
                demoCard(box);

                // ============ 二、添加子控件 ============
                h2(box, "二、添加子控件");
                para(box, "5 种 addView 重载，构件（Element）占第一个参数位，其余参数与原生一致：");
                code(box, "root.view(text(tv -> {...}));                     // 构件\n" +
                        "root.view(new TextView(this));                    // 实例：裸\n" +
                        "root.view(new TextView(this), tv -> {...});       // 实例 + 配置\n" +
                        "root.view(TextView.class);                        // 类：裸\n" +
                        "root.view(TextView.class, tv -> {...});           // 类 + 配置\n" +
                        "\n" +
                        "root.view(text(tv -> {...}), 0);                  // 索引版\n" +
                        "root.view(text(tv -> {...}), params);             // 参数版\n" +
                        "root.view(text(tv -> {...}), 0, params);          // 索引 + 参数版\n" +
                        "root.view(text(tv -> {...}), -2, 32);             // 尺寸版");
                para(box, "下面每一行，都用不同的 addView 重载真实添加：");
                demoAddViews(box);

                // ============ 三、配置属性 ============
                h2(box, "三、配置属性");
                para(box, "属性方法全部挂在引用对象上，链式调用、返回自身。从输入到布局，挨个演示：");
                demoInput(box);
                demoSelect(box);
                demoProgress(box);
                demoImage(box);
                demoLayouts(box);

                // ============ 页脚 ============
                box.view(view(line -> line.size(-1, 1).marginTopDp(32).backgroundColor(getColor(R.color.md_outline_variant))));
                para(box, "本项目采用 MIT License 开源协议");
                para(box, "作者：阿恒 · kkaheng163@163.com");
            }));
        })));
    }

    // ============ 方式四演示：实例版 + ref() 逃逸 ============
    private void demoCard(RefLinear<LinearLayout, ?> box) {
        box.layout(frame(new MaterialCardView(this), card -> {
            card.ref().setRadius(card.dp2px(16f)); // ref() 逃逸，原生方法
            card.ref().setCardBackgroundColor(getColor(R.color.md_primary_container));
            card.size(-1, -2).marginTopDp(12).elevationDp(1);

            card.layout(linear(area -> {
                area.size(-1, -2).paddingDp(16).vertical();
                area.view(text(t -> {
                    t.size(-1, -2).text("实际演示：实例版 + ref() 逃逸")
                            .textSizeDp(14).typeface(Typeface.BOLD)
                            .textColor(getColor(R.color.md_on_primary_container));
                }));
                area.view(text(new MaterialButton(this), btn -> {
                    btn.size(-1, -2).marginTopDp(10).onClick(v -> toast("原生 API 随便调"));
                    btn.text("点我 · ref() 逃逸").textSizeDp(14);
                }));
            }));
        }));
    }

    // ============ 添加子控件演示：每行一种 addView 重载 ============
    private void demoAddViews(RefLinear<LinearLayout, ?> box) {
        box.layout(linear(area -> {
            area.size(-1, -2).marginTopDp(12).paddingDp(14).vertical();
            GradientDrawable bg = new GradientDrawable();
            bg.setColor(getColor(R.color.md_surface_container_low));
            bg.setCornerRadius(area.dp2px(12f));
            area.ref().setBackground(bg); // ref() 逃逸

            addViewRow(area, 0, "addView(Element)");
            addViewRow(area, 1, "addView(View)");
            addViewRow(area, 2, "addView(Class)");
            addViewRow(area, 3, "addView(Element, 0)");
            addViewRow(area, 4, "addView(Element, params)");
            addViewRow(area, 5, "addView(Element, 0, params)");
            addViewRow(area, 6, "addView(Element, w, h)");
        }));
    }

    // 一行：用对应的 addView 重载添加
    private void addViewRow(RefLinear<LinearLayout, ?> box, int kind, String sig) {
        box.layout(linear(row -> {
            row.size(-1, -2).marginTopDp(8).horizontal();

            switch (kind) {
                case 0: // 构件添加：addView(Element)
                    row.view(text(tv -> mono(tv, sig)));
                    break;
                case 1: { // 实例 + 配置：addView(View, Method)
                    TextView tv = new TextView(this);
                    tv.setText(sig); // 具体属性走原生 API
                    tv.setTextSize(11);
                    tv.setTextColor(getColor(R.color.md_outline));
                    tv.setTypeface(Typeface.MONOSPACE);
                    tv.setGravity(Gravity.CENTER_VERTICAL);
                    row.view(tv, t -> t.marginLeftDp(2)); // 通用属性走配置器
                    break;
                }
                case 2: // 类 + 配置：addView(Class, Method)
                    row.view(TextView.class, t -> {
                        TextView tv = t.ref(); // 具体属性靠 ref() 逃逸
                        tv.setText(sig);
                        tv.setTextSize(11);
                        tv.setTextColor(getColor(R.color.md_outline));
                        tv.setTypeface(Typeface.MONOSPACE);
                        tv.setGravity(Gravity.CENTER_VERTICAL);
                        t.marginLeftDp(2); // 通用属性走配置器
                    });
                    break;
                case 3: // 索引版：插到最前面
                    row.view(text(tv -> mono(tv, sig)), 0);
                    break;
                case 4: // 参数版
                    row.view(text(tv -> mono(tv, sig)), new LinearLayout.LayoutParams(0, -2, 1f));
                    break;
                case 5: // 索引 + 参数版
                    row.view(text(tv -> mono(tv, sig)), 0, new LinearLayout.LayoutParams(-2, -2));
                    break;
                case 6: // 尺寸版
                    row.view(text(tv -> mono(tv, sig)), -2, row.dp2px(30f));
                    break;
            }

            // 右侧标记这一行用的是哪种来源
            tag(row, kind == 1 ? "实例" : kind == 2 ? "Class" : "Element",
                    R.color.md_secondary_container, R.color.md_on_secondary_container);
        }));
    }

    // 等宽小字签名
    private void mono(RefText<TextView, ?> tv, String sig) {
        tv.size(-2, -2).text(sig).textSizeDp(11)
                .textColor(getColor(R.color.md_outline))
                .typeface(Typeface.MONOSPACE)
                .gravity(Gravity.CENTER_VERTICAL);
    }

    // ============ 输入控件演示 ============
    private void demoInput(RefLinear<LinearLayout, ?> box) {
        card(box, area -> {
            h3(area, "输入控件");
            area.view(edit(e -> {
                e.size(-1, -2).marginTopDp(10).paddingDp(14, 10, 14, 10)
                        .hint("请输入昵称（最多 10 字）").textSizeDp(14)
                        .maxLength(10).inputType(InputType.TYPE_CLASS_TEXT)
                        .imeOptions(EditorInfo.IME_ACTION_DONE);
                GradientDrawable bg = new GradientDrawable();
                bg.setColor(getColor(R.color.md_surface));
                bg.setCornerRadius(e.dp2px(12f));
                bg.setStroke(e.dp2px(1f), getColor(R.color.md_outline_variant));
                e.ref().setBackground(bg); // ref() 逃逸
            }));
            area.view(button(btn -> {
                btn.size(-2, -2).marginTopDp(10).touchBackground()
                        .onClick(v -> toast("提交了"));
                btn.text("提交").textSizeDp(14);
            }));
        });
    }

    // ============ 选择控件演示 ============
    private void demoSelect(RefLinear<LinearLayout, ?> box) {
        card(box, area -> {
            h3(area, "选择控件");

            area.view(check(cb -> {
                cb.checked(true).buttonTint(getColor(R.color.md_primary));
                cb.text("记住我").textSizeDp(14);
                cb.onCheckedChange((b, checked) -> toast(checked ? "记住了" : "取消了"));
            }));

            area.layout(radio_group(rg -> {
                rg.view(radio(r1 -> {
                    r1.checked(true).buttonTint(getColor(R.color.md_primary));
                    r1.text("选项 A").textSizeDp(14);
                }));
                rg.view(radio(r2 -> {
                    r2.buttonTint(getColor(R.color.md_primary));
                    r2.text("选项 B").textSizeDp(14);
                }));
            }));

            area.view(switch_button(sw -> {
                sw.thumbTint(getColor(R.color.md_primary))
                        .trackTint(getColor(R.color.md_primary_container)).showText();
                sw.text("深色模式").textSizeDp(14);
            }));
        });
    }

    // ============ 进度控件演示 ============
    private void demoProgress(RefLinear<LinearLayout, ?> box) {
        card(box, area -> {
            h3(area, "进度控件");

            area.view(progress(pb -> {
                pb.size(-1, pb.dp2px(6f)).marginTopDp(10).max(100).progress(60)
                        .progressTint(getColor(R.color.md_primary))
                        .progressBackgroundTint(getColor(R.color.md_primary_container));
            }));

            // 数值文本 + SeekBar：拖动时用 ref() 逃逸实时改文本
            final TextView[] value = new TextView[1];
            area.layout(linear(row -> {
                row.size(-1, -2).marginTopDp(12).horizontal();
                row.view(text(tv -> {
                    tv.size(-2, -2).text("音量").textSizeDp(14)
                            .textColor(getColor(R.color.md_on_surface_variant))
                            .gravity(Gravity.CENTER_VERTICAL);
                }));
                row.view(text(tv -> {
                    tv.size(0, -2).text("50").textSizeDp(14)
                            .textColor(getColor(R.color.md_primary))
                            .gravity(Gravity.END | Gravity.CENTER_VERTICAL);
                    value[0] = tv.ref(); // ref() 逃逸，存原生引用
                }), new LinearLayout.LayoutParams(0, -2, 1f)); // 参数版：weight 撑满
            }));
            area.view(seek(sb -> {
                sb.size(-1, -2).marginTopDp(4).max(100).progress(50)
                        .thumbTint(getColor(R.color.md_primary))
                        .onSeekBarChange(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar s, int p, boolean fromUser) {
                                if (value[0] != null) value[0].setText(String.valueOf(p)); // 原生 API
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar s) {
                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar s) {
                            }
                        });
            }));

            area.view(rating(rb -> {
                rb.numStars(5).rating(3.5f).stepSize(0.5f)
                        .onRatingChange((bar, r, fromUser) -> toast("评分：" + r));
            }));
        });
    }

    // ============ 图片控件演示 ============
    private void demoImage(RefLinear<LinearLayout, ?> box) {
        card(box, area -> {
            h3(area, "图片控件");
            area.view(image(iv -> {
                iv.size(-1, iv.dp2px(140f)).marginTopDp(10)
                        .src(android.R.drawable.ic_menu_gallery)
                        .scaleType(android.widget.ImageView.ScaleType.CENTER_CROP)
                        .tint(getColor(R.color.md_primary)).imageAlpha(200);
            }));
            area.view(text(t -> {
                t.size(-1, -2).marginTopDp(6).text("src 支持 drawable / mipmap / attr 资源")
                        .textSizeDp(12).textColor(getColor(R.color.md_outline));
            }));
        });
    }

    // ============ 布局控件演示 ============
    private void demoLayouts(RefLinear<LinearLayout, ?> box) {
        card(box, area -> {
            h3(area, "布局控件");

            // GridLayout：2 列网格
            area.layout(grid(g -> {
                g.size(-1, -2).marginTopDp(10).columnCount(2).useDefaultMargins();
                cell(g, "1", R.color.md_primary_container, R.color.md_on_primary_container);
                cell(g, "2", R.color.md_secondary_container, R.color.md_on_secondary_container);
                cell(g, "3", R.color.md_tertiary_container, R.color.md_on_tertiary_container);
                cell(g, "4", R.color.md_primary_fixed_dim, R.color.md_on_primary_fixed);
            }));

            // FrameLayout：居中
            area.layout(frame(f -> {
                f.size(-1, f.dp2px(72f)).marginTopDp(10)
                        .backgroundColor(getColor(R.color.md_surface_container_high))
                        .foregroundGravity(Gravity.CENTER);
                f.view(text(tv -> {
                    tv.text("FrameLayout 居中").textSizeDp(13)
                            .textColor(getColor(R.color.md_on_surface));
                }), new FrameLayout.LayoutParams(-2, -2, Gravity.CENTER));
            }));

            // RelativeLayout：容器 gravity 控制子控件
            area.layout(relative(rel -> {
                rel.size(-1, rel.dp2px(64f)).marginTopDp(10)
                        .gravity(Gravity.CENTER)
                        .backgroundColor(getColor(R.color.md_secondary_container));
                rel.view(text(tv -> {
                    tv.text("RelativeLayout 居中").textSizeDp(13)
                            .textColor(getColor(R.color.md_on_secondary_container));
                }));
            }));
        });
    }

    // ============ Markdown 组件 ============

    // H1 标题
    private void h1(RefLayout<?, ?> box, String text) {
        box.view(text(tv -> {
            tv.size(-1, -2).text(text).textSizeDp(40)
                    .textColor(getColor(R.color.md_primary)).typeface(Typeface.BOLD);
        }));
    }

    // H2 章节标题
    private void h2(RefLayout<?, ?> box, String text) {
        box.view(text(tv -> {
            tv.size(-1, -2).marginTopDp(28).text(text).textSizeDp(22)
                    .textColor(getColor(R.color.md_on_surface)).typeface(Typeface.BOLD);
        }));
    }

    // H3 小节标题
    private void h3(RefLayout<?, ?> box, String text) {
        box.view(text(tv -> {
            tv.size(-1, -2).marginTopDp(18).text(text).textSizeDp(16)
                    .textColor(getColor(R.color.md_secondary)).typeface(Typeface.BOLD);
        }));
    }

    // 段落
    private void para(RefLayout<?, ?> box, String text) {
        box.view(text(tv -> {
            tv.size(-1, -2).marginTopDp(8).text(text).textSizeDp(14)
                    .textColor(getColor(R.color.md_on_surface_variant));
        }));
    }

    // 徽章
    private void badge(RefLayout<?, ?> box, String text) {
        box.view(text(tv -> {
            tv.size(-2, -2).marginTopDp(12).paddingDp(12, 4, 12, 4)
                    .text(text).textSizeDp(12).gravity(Gravity.CENTER)
                    .textColor(getColor(R.color.md_on_primary_container));
            GradientDrawable bg = new GradientDrawable();
            bg.setColor(getColor(R.color.md_primary_container));
            bg.setCornerRadius(tv.dp2px(20f));
            tv.ref().setBackground(bg); // ref() 逃逸
        }));
    }

    // 引用块
    private void quote(RefLayout<?, ?> box, String text) {
        box.layout(linear(q -> {
            q.size(-1, -2).marginTopDp(16).paddingDp(14).horizontal();
            GradientDrawable bg = new GradientDrawable();
            bg.setColor(getColor(R.color.md_surface_container_low));
            bg.setCornerRadius(q.dp2px(12f));
            q.ref().setBackground(bg); // ref() 逃逸

            q.view(text(gt -> {
                gt.size(-2, -2).text("›").textSizeDp(18).typeface(Typeface.BOLD)
                        .textColor(getColor(R.color.md_primary)).gravity(Gravity.CENTER_VERTICAL);
            }));
            q.view(text(t -> {
                t.size(0, -2).paddingLeftDp(10)
                        .text(text).textSizeDp(14).textColor(getColor(R.color.md_on_surface))
                        .gravity(Gravity.CENTER_VERTICAL);
            }), new LinearLayout.LayoutParams(0, -2, 1f));
        }));
    }

    // 代码块：深色圆角 + 等宽字体
    private void code(RefLayout<?, ?> box, String code) {
        box.view(text(tv -> {
            tv.size(-1, -2).marginTopDp(10).paddingDp(14, 10, 14, 10)
                    .text(code).textSizeDp(12).textIsSelectable()
                    .textColor(getColor(R.color.md_inverse_on_surface))
                    .typeface(Typeface.MONOSPACE);
            GradientDrawable bg = new GradientDrawable();
            bg.setColor(getColor(R.color.md_inverse_surface));
            bg.setCornerRadius(tv.dp2px(10f));
            tv.ref().setBackground(bg); // ref() 逃逸
        }));
    }

    // 小标签
    private void tag(RefLayout<?, ?> box, String text, int bgColor, int fgColor) {
        box.view(text(tv -> {
            tv.size(-2, -2).marginLeftDp(8).paddingDp(8, 3, 8, 3)
                    .text(text).textSizeDp(12).gravity(Gravity.CENTER)
                    .textColor(getColor(fgColor));
            GradientDrawable g = new GradientDrawable();
            g.setColor(getColor(bgColor));
            g.setCornerRadius(tv.dp2px(12f));
            tv.ref().setBackground(g); // ref() 逃逸
        }));
    }

    // 网格色块
    private void cell(RefLayout<?, ?> box, String text, int bgColor, int fgColor) {
        box.view(text(tv -> {
            tv.size(-1, tv.dp2px(48f)).text(text).textSizeDp(14)
                    .gravity(Gravity.CENTER).textColor(getColor(fgColor));
            GradientDrawable g = new GradientDrawable();
            g.setColor(getColor(bgColor));
            g.setCornerRadius(tv.dp2px(10f));
            tv.ref().setBackground(g); // ref() 逃逸
        }));
    }

    // 通用卡片容器
    private void card(RefLinear<LinearLayout, ?> box, Method<RefLinear<LinearLayout, ?>> content) {
        box.layout(frame(new MaterialCardView(this), card -> {
            card.ref().setRadius(card.dp2px(16f)); // ref() 逃逸
            card.ref().setCardBackgroundColor(getColor(R.color.md_surface_container_low));
            card.size(-1, -2).marginTopDp(14).elevationDp(0);

            card.layout(linear(area -> {
                area.size(-1, -2).paddingDp(16).vertical();
                content.call(area);
            }));
        }));
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}