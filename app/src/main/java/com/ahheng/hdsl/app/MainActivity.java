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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

// HDSL Demo
// 像渲染 Markdown 一样构建整个页面：ScrollView 当根，标题/段落/代码块/引用块/列表应有尽有。
// 每一章都在演示一种构建 UI 的方式，整页覆盖框架全部能力。
public class MainActivity extends AppCompatActivity implements HDSL {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(build(SCROLL, root -> {
            // 根布局：ScrollView
            scroll(root).size(-1, -1).fillViewport();

            root.layout(LINEAR, box -> {
                linear(box).size(-1, -2).paddingDp(24, 48, 24, 24).vertical();

                // ============ 标题区 ============
                h1(box, "HDSL");
                para(box, "用 Java 代码构建 Android UI 的 DSL 框架。零 XML、链式 API、类型安全，布局结构直接从代码里长出来。");
                badge(box, "使用前提：Java 8");
                quote(box, "核心分工：build 管结构 · Tools 管属性 · Factory 管创建\n让 Activity implements HDSL，三个能力全部到手");

                // ============ 一、构建 UI 的方式 ============
                h2(box, "一、构建 UI 的方式");
                para(box, "控件从哪来？三种来源，三种写法。");
                h3(box, "1. 实例版：自己 new");
                para(box, "适合复用已有实例、或构造需要传参的控件（MaterialButton、MaterialCardView 等）。");
                code(box, "setContentView(build(new LinearLayout(this), root -> {\n" +
                        "    root.view(new TextView(this), tv -> {\n" +
                        "        text(tv).text(\"你好，世界\").textSizeDp(16);\n" +
                        "    });\n" +
                        "}));");
                h3(box, "2. Class 版：反射创建");
                para(box, "只传 Class，框架反射创建实例。上下文自动感知，Activity 本身就是 Context。");
                code(box, "setContentView(build(LinearLayout.class, root -> {\n" +
                        "    root.view(TextView.class, tv -> {\n" +
                        "        text(tv).text(\"你好，世界\").textSizeDp(16);\n" +
                        "    });\n" +
                        "}));");
                h3(box, "3. Creator 版：工厂常量（最简洁）");
                para(box, "用 Factory 里的常量，连 .class 和 new 都省了。只负责创建，不负责配置。");
                code(box, "setContentView(build(LINEAR, root -> {\n" +
                        "    root.view(TEXT, tv -> {\n" +
                        "        text(tv).text(\"你好，世界\").textSizeDp(16);\n" +
                        "    });\n" +
                        "}));");
                h3(box, "4. 混用 + ref() 逃逸");
                para(box, "三种来源任意混搭。任何时刻用 ref() 拿到原始 View，原生 API 随便调。下面这张卡片就是实例版 + ref() 的实拍：");
                code(box, "root.layout(new MaterialCardView(this), card -> {\n" +
                        "    card.ref().setRadius(view(card).dp2px(16f)); // 原生方法\n" +
                        "    view(card).elevationDp(1);\n" +
                        "});");
                demoCard(box);

                // ============ 二、添加子控件 ============
                h2(box, "二、添加子控件");
                para(box, "RefLayout 聚合了 5 种 addView 重载，每种都有 view()（控件）/ layout()（布局）两个入口，可无限嵌套：");
                code(box, "root.view(TEXT);                    // Creator，不配置\n" +
                        "root.view(TEXT, tv -> {...});       // Creator，配置 ← 最常用\n" +
                        "root.view(TextView.class);           // Class，不配置\n" +
                        "root.view(new TextView(this));       // 实例，不配置\n" +
                        "root.view(TEXT, 0);                  // 索引版\n" +
                        "root.view(TEXT, -2, 32);             // 尺寸版\n" +
                        "root.view(TEXT, params);             // 参数版\n" +
                        "root.view(TEXT, 0, params);          // 索引 + 参数版");
                para(box, "下面这组标签，每一行都用不同的 addView 重载添加：");
                demoAddViews(box);

                // ============ 三、配置属性 ============
                h2(box, "三、配置属性");
                para(box, "Tools 工厂按控件类型提供属性工具，全部链式调用、返回自身。从输入到进度，挨个演示：");

                demoInput(box);
                demoSelect(box);
                demoProgress(box);
                demoImage(box);
                demoLayouts(box);

                // ============ 页脚 ============
                box.view(VIEW, line -> view(line).size(-1, 1).marginTopDp(32).backgroundColor(getColor(R.color.md_outline_variant)));
                para(box, "本项目采用 MIT License 开源协议");
                para(box, "作者：阿恒 · kkaheng163@163.com");
            });
        }));
    }

    // ============ 方式四演示：实例版 + ref() 逃逸 ============
    private void demoCard(RefLayout<?, ?> box) {
        box.layout(new MaterialCardView(this), card -> {
            card.ref().setRadius(view(card).dp2px(16f)); // ref() 逃逸，原生方法
            card.ref().setCardBackgroundColor(getColor(R.color.md_primary_container));
            view(card).size(-1, -2).marginTopDp(12).elevationDp(1);

            card.layout(LINEAR, area -> {
                linear(area).size(-1, -2).paddingDp(16).vertical();
                area.view(TEXT, t -> {
                    text(t).size(-1, -2).text("实际演示：实例版 + ref() 逃逸")
                            .textSizeDp(14).typeface(Typeface.BOLD)
                            .textColor(getColor(R.color.md_on_primary_container));
                });
                area.view(new MaterialButton(this), btn -> {
                    view(btn).size(-1, -2).marginTopDp(10).onClick(v -> toast("原生 API 随便调"));
                    text(btn).text("点我 · ref() 逃逸").textSizeDp(14);
                });
            });
        });
    }

    // ============ 添加子控件演示：每行一个 addView 重载 ============
    private void demoAddViews(RefLayout<?, ?> box) {
        box.layout(LINEAR, area -> {
            linear(area).size(-1, -2).marginTopDp(12).paddingDp(14).vertical();
            GradientDrawable bg = new GradientDrawable();
            bg.setColor(getColor(R.color.md_surface_container_low));
            bg.setCornerRadius(view(area).dp2px(12f));
            area.ref().setBackground(bg); // ref() 逃逸

            addViewRow(area, "addView(View)", false);
            addViewRow(area, "addView(Class)", false);
            addViewRow(area, "addView(Creator)", false);
            addViewRow(area, "addView(View, w, h)", true);
            addViewRow(area, "addView(View, Params)", true);
            addViewRow(area, "addView(View, idx, Params)", true);

            // Class 版真实调用：addView(Class) 反射创建
            area.layout(LINEAR, row -> {
                linear(row).size(-1, -2).marginTopDp(8).horizontal();
                row.view(TEXT, sigTv -> {
                    text(sigTv).size(-2, -2).text("真实 Class 版：").textSizeDp(11)
                            .textColor(getColor(R.color.md_outline)).typeface(Typeface.MONOSPACE)
                            .gravity(Gravity.CENTER_VERTICAL);
                });
                row.view(TextView.class, tv -> { // 反射创建，上下文自动感知
                    text(tv).size(-2, -2).paddingDp(8, 3, 8, 3)
                            .text("Class 标签").textSizeDp(12).gravity(Gravity.CENTER)
                            .textColor(getColor(R.color.md_on_secondary_container));
                    GradientDrawable g = new GradientDrawable();
                    g.setColor(getColor(R.color.md_secondary_container));
                    g.setCornerRadius(view(tv).dp2px(12f));
                    tv.ref().setBackground(g);
                });
            });

            // 尺寸版：addView(View, int, int) 指定宽高
            area.layout(LINEAR, row -> {
                linear(row).size(-1, -2).marginTopDp(8).horizontal();
                row.view(TEXT, view(row).dp2px(96f), view(row).dp2px(30f), tv -> { // 尺寸版
                    text(tv).text("尺寸版 96x30").textSizeDp(11).gravity(Gravity.CENTER)
                            .textColor(getColor(R.color.md_on_secondary_container));
                    GradientDrawable g = new GradientDrawable();
                    g.setColor(getColor(R.color.md_secondary_container));
                    g.setCornerRadius(view(tv).dp2px(10f));
                    tv.ref().setBackground(g);
                });
            });

            // 索引版插队演示：先放 A、B，再把"索引0"插到最前
            area.layout(LINEAR, row -> {
                linear(row).size(-1, -2).marginTopDp(8).horizontal();
                tag(row, "A", R.color.md_secondary_container, R.color.md_on_secondary_container);
                tag(row, "B", R.color.md_tertiary_container, R.color.md_on_tertiary_container);
                row.view(TEXT, 0, tv -> { // addView(View, int) 插到 index 0
                    text(tv).size(-2, -2).marginLeftDp(8).paddingDp(8, 3, 8, 3)
                            .text("← 索引0插入").textSizeDp(12)
                            .textColor(getColor(R.color.md_on_primary_container));
                    GradientDrawable g = new GradientDrawable();
                    g.setColor(getColor(R.color.md_primary_container));
                    g.setCornerRadius(view(tv).dp2px(12f));
                    tv.ref().setBackground(g);
                });
            });
        });
    }

    // 一行：左签名 + 右标签，行本身用对应 addView 重载添加
    private void addViewRow(RefLayout<?, ?> box, String sig, boolean withParams) {
        box.layout(LINEAR, row -> {
            linear(row).size(-1, -2).marginTopDp(8).horizontal();

            if (withParams) {
                // 参数版：weight=1 撑满剩余宽度
                row.view(TEXT, new LinearLayout.LayoutParams(0, -2, 1f), sigTv -> {
                    text(sigTv).size(0, -2).text(sig).textSizeDp(11)
                            .textColor(getColor(R.color.md_outline)).typeface(Typeface.MONOSPACE)
                            .gravity(Gravity.CENTER_VERTICAL);
                });
            } else {
                row.view(TEXT, sigTv -> {
                    text(sigTv).size(-2, -2).text(sig).textSizeDp(11)
                            .textColor(getColor(R.color.md_outline)).typeface(Typeface.MONOSPACE)
                            .gravity(Gravity.CENTER_VERTICAL);
                });
            }
            tag(row, "实例", R.color.md_primary_container, R.color.md_on_primary_container);
            tag(row, "Class", R.color.md_secondary_container, R.color.md_on_secondary_container);
            tag(row, "Creator", R.color.md_tertiary_container, R.color.md_on_tertiary_container);
        });
    }

    // ============ 输入控件演示 ============
    private void demoInput(RefLayout<?, ?> box) {
        card(box, area -> {
            h3(area, "输入控件");
            area.view(EDIT, edit -> {
                edit(edit).size(-1, -2).marginTopDp(10).paddingDp(14, 10, 14, 10)
                        .hint("请输入昵称（最多 10 字）").textSizeDp(14)
                        .maxLength(10).inputType(InputType.TYPE_CLASS_TEXT)
                        .imeOptions(EditorInfo.IME_ACTION_DONE);
                GradientDrawable bg = new GradientDrawable();
                bg.setColor(getColor(R.color.md_surface));
                bg.setCornerRadius(view(edit).dp2px(12f));
                bg.setStroke(view(edit).dp2px(1f), getColor(R.color.md_outline_variant));
                edit.ref().setBackground(bg); // ref() 逃逸
            });
            area.view(BUTTON, btn -> {
                view(btn).size(-2, -2).marginTopDp(10).touchBackground()
                        .onClick(v -> toast("提交了"));
                text(btn).text("提交").textSizeDp(14);
            });
        });
    }

    // ============ 选择控件演示 ============
    private void demoSelect(RefLayout<?, ?> box) {
        card(box, area -> {
            h3(area, "选择控件");

            area.view(CHECK, cb -> {
                check(cb).checked(true).buttonTint(getColor(R.color.md_primary));
                text(cb).text("记住我").textSizeDp(14);
                check(cb).onCheckedChange((b, checked) -> toast(checked ? "记住了" : "取消了"));
            });

            area.layout(RADIO_GROUP, rg -> {
                rg.view(RADIO, r1 -> {
                    check(r1).checked(true).buttonTint(getColor(R.color.md_primary));
                    text(r1).text("选项 A").textSizeDp(14);
                });
                rg.view(RADIO, r2 -> {
                    check(r2).buttonTint(getColor(R.color.md_primary));
                    text(r2).text("选项 B").textSizeDp(14);
                });
            });

            area.view(SWITCH, sw -> {
                switch_button(sw).thumbTint(getColor(R.color.md_primary))
                        .trackTint(getColor(R.color.md_primary_container)).showText();
                text(sw).text("深色模式").textSizeDp(14);
            });
        });
    }

    // ============ 进度控件演示 ============
    private void demoProgress(RefLayout<?, ?> box) {
        card(box, area -> {
            h3(area, "进度控件");

            area.view(PROGRESS, pb -> {
                progress(pb).size(-1, view(pb).dp2px(6f)).marginTopDp(10)
                        .max(100).progress(60)
                        .progressTint(getColor(R.color.md_primary))
                        .progressBackgroundTint(getColor(R.color.md_primary_container));
            });

            // SeekBar 拖动实时更新数值（ref() 逃逸做运行期操作）
            final TextView[] value = new TextView[1];
            area.layout(LINEAR, row -> {
                linear(row).size(-1, -2).marginTopDp(12).horizontal();
                row.view(TEXT, tv -> {
                    text(tv).size(-2, -2).text("音量").textSizeDp(14)
                            .textColor(getColor(R.color.md_on_surface_variant))
                            .gravity(Gravity.CENTER_VERTICAL);
                });
                row.view(TEXT, new LinearLayout.LayoutParams(0, -2, 1f), tv -> {
                    text(tv).size(0, -2).text("50").textSizeDp(14)
                            .textColor(getColor(R.color.md_primary))
                            .gravity(Gravity.END | Gravity.CENTER_VERTICAL);
                    value[0] = tv.ref(); // ref() 逃逸，存原生引用
                });
            });
            area.view(SEEK, seek -> {
                seek(seek).size(-1, -2).marginTopDp(4).max(100).progress(50)
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
            });

            area.view(RATING, rb -> {
                rating(rb).numStars(5).rating(3.5f).stepSize(0.5f)
                        .onRatingChange((bar, r, fromUser) -> toast("评分：" + r));
            });
        });
    }

    // ============ 图片控件演示 ============
    private void demoImage(RefLayout<?, ?> box) {
        card(box, area -> {
            h3(area, "图片控件");
            area.view(IMAGE, iv -> {
                image(iv).size(-1, view(iv).dp2px(140f)).marginTopDp(10)
                        .src(android.R.drawable.ic_menu_gallery)
                        .scaleType(android.widget.ImageView.ScaleType.CENTER_CROP)
                        .tint(getColor(R.color.md_primary)).imageAlpha(200);
            });
            area.view(TEXT, t -> {
                text(t).size(-1, -2).marginTopDp(6).text("src 支持 drawable / mipmap / attr 资源")
                        .textSizeDp(12).textColor(getColor(R.color.md_outline));
            });
        });
    }

    // ============ 布局控件演示 ============
    private void demoLayouts(RefLayout<?, ?> box) {
        card(box, area -> {
            h3(area, "布局控件");

            // GridLayout：2 列网格
            area.layout(GRID, grid -> {
                grid(grid).size(-1, -2).marginTopDp(10).columnCount(2).useDefaultMargins();
                cell(grid, "1", R.color.md_primary_container, R.color.md_on_primary_container);
                cell(grid, "2", R.color.md_secondary_container, R.color.md_on_secondary_container);
                cell(grid, "3", R.color.md_tertiary_container, R.color.md_on_tertiary_container);
                cell(grid, "4", R.color.md_primary_fixed_dim, R.color.md_on_primary_fixed);
            });

            // FrameLayout：子控件用 LayoutParams 居中
            area.layout(FRAME, frame -> {
                frame(frame).size(-1, view(frame).dp2px(72f)).marginTopDp(10)
                        .backgroundColor(getColor(R.color.md_surface_container_high))
                        .foregroundGravity(Gravity.CENTER);
                frame.view(TEXT, new FrameLayout.LayoutParams(-2, -2, Gravity.CENTER), tv -> {
                    text(tv).text("FrameLayout 居中").textSizeDp(13)
                            .textColor(getColor(R.color.md_on_surface));
                });
            });

            // RelativeLayout：容器 gravity 控制子控件
            area.layout(RELATIVE, rel -> {
                relative(rel).size(-1, view(rel).dp2px(64f)).marginTopDp(10)
                        .gravity(Gravity.CENTER)
                        .backgroundColor(getColor(R.color.md_secondary_container));
                rel.view(TEXT, tv -> {
                    text(tv).text("RelativeLayout 居中").textSizeDp(13)
                            .textColor(getColor(R.color.md_on_secondary_container));
                });
            });
        });
    }

    // ============ Markdown 组件 ============

    // H1 标题
    private void h1(RefLayout<?, ?> box, String text) {
        box.view(TEXT, tv -> {
            text(tv).size(-1, -2).text(text).textSizeDp(40)
                    .textColor(getColor(R.color.md_primary)).typeface(Typeface.BOLD);
        });
    }

    // H2 章节标题
    private void h2(RefLayout<?, ?> box, String text) {
        box.view(TEXT, tv -> {
            text(tv).size(-1, -2).marginTopDp(28).text(text).textSizeDp(22)
                    .textColor(getColor(R.color.md_on_surface)).typeface(Typeface.BOLD);
        });
    }

    // H3 小节标题
    private void h3(RefLayout<?, ?> box, String text) {
        box.view(TEXT, tv -> {
            text(tv).size(-1, -2).marginTopDp(18).text(text).textSizeDp(16)
                    .textColor(getColor(R.color.md_secondary)).typeface(Typeface.BOLD);
        });
    }

    // 段落
    private void para(RefLayout<?, ?> box, String text) {
        box.view(TEXT, tv -> {
            text(tv).size(-1, -2).marginTopDp(8).text(text).textSizeDp(14)
                    .textColor(getColor(R.color.md_on_surface_variant));
        });
    }

    // 徽章
    private void badge(RefLayout<?, ?> box, String text) {
        box.view(TEXT, tv -> {
            text(tv).size(-2, -2).marginTopDp(12).paddingDp(12, 4, 12, 4)
                    .text(text).textSizeDp(12).gravity(Gravity.CENTER)
                    .textColor(getColor(R.color.md_on_primary_container));
            GradientDrawable bg = new GradientDrawable();
            bg.setColor(getColor(R.color.md_primary_container));
            bg.setCornerRadius(view(tv).dp2px(20f));
            tv.ref().setBackground(bg); // ref() 逃逸
        });
    }

    // 引用块
    private void quote(RefLayout<?, ?> box, String text) {
        box.layout(LINEAR, q -> {
            linear(q).size(-1, -2).marginTopDp(16).paddingDp(14).horizontal();
            GradientDrawable bg = new GradientDrawable();
            bg.setColor(getColor(R.color.md_surface_container_low));
            bg.setCornerRadius(view(q).dp2px(12f));
            q.ref().setBackground(bg); // ref() 逃逸

            q.view(TEXT, gt -> {
                text(gt).size(-2, -2).text("›").textSizeDp(18).typeface(Typeface.BOLD)
                        .textColor(getColor(R.color.md_primary)).gravity(Gravity.CENTER_VERTICAL);
            });
            q.view(TEXT, new LinearLayout.LayoutParams(0, -2, 1f), t -> {
                text(t).size(0, -2).paddingLeftDp(10)
                        .text(text).textSizeDp(14).textColor(getColor(R.color.md_on_surface))
                        .gravity(Gravity.CENTER_VERTICAL);
            });
        });
    }

    // 代码块：深色圆角 + 等宽字体
    private void code(RefLayout<?, ?> box, String code) {
        box.view(TEXT, tv -> {
            text(tv).size(-1, -2).marginTopDp(10).paddingDp(14, 10, 14, 10)
                    .text(code).textSizeDp(12).textIsSelectable()
                    .textColor(getColor(R.color.md_inverse_on_surface))
                    .typeface(Typeface.MONOSPACE);
            GradientDrawable bg = new GradientDrawable();
            bg.setColor(getColor(R.color.md_inverse_surface));
            bg.setCornerRadius(view(tv).dp2px(10f));
            tv.ref().setBackground(bg); // ref() 逃逸
        });
    }

    // 小标签
    private void tag(RefLayout<?, ?> box, String text, int bgColor, int fgColor) {
        box.view(TEXT, tv -> {
            text(tv).size(-2, -2).marginLeftDp(8).paddingDp(8, 3, 8, 3)
                    .text(text).textSizeDp(12).gravity(Gravity.CENTER)
                    .textColor(getColor(fgColor));
            GradientDrawable g = new GradientDrawable();
            g.setColor(getColor(bgColor));
            g.setCornerRadius(view(tv).dp2px(12f));
            tv.ref().setBackground(g); // ref() 逃逸
        });
    }

    // 网格色块
    private void cell(RefLayout<?, ?> box, String text, int bgColor, int fgColor) {
        box.view(TEXT, tv -> {
            text(tv).size(-1, view(tv).dp2px(48f)).text(text).textSizeDp(14)
                    .gravity(Gravity.CENTER).textColor(getColor(fgColor));
            GradientDrawable g = new GradientDrawable();
            g.setColor(getColor(bgColor));
            g.setCornerRadius(view(tv).dp2px(10f));
            tv.ref().setBackground(g); // ref() 逃逸
        });
    }

    // 通用卡片容器
    private void card(RefLayout<?, ?> box, Method<RefLayout<?, ?>> content) {
        box.layout(new MaterialCardView(this), card -> {
            card.ref().setRadius(view(card).dp2px(16f));
            card.ref().setCardBackgroundColor(getColor(R.color.md_surface_container_low));
            view(card).size(-1, -2).marginTopDp(14).elevationDp(0);

            card.layout(LINEAR, area -> {
                linear(area).size(-1, -2).paddingDp(16).vertical();
                content.call(area);
            });
        });
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}