package com.ahheng.hdsl.tool;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.ahheng.hdsl.CtxProvider;
import com.ahheng.hdsl.ReturnMe;
import com.ahheng.hdsl.ref.Reference;

// 安卓控件工具
public class ViewTool <V extends View, T extends ViewTool<V, T>>
        implements CtxProvider, ReturnMe<V, T>, Reference<V> {

    private final V ref; // 原始引用

    public ViewTool(V ref) {
        this.ref = ref;
    }

    @Override
    public Context ctx() {
        return ref.getContext(); // 直接复用
    }

    @Override
    public V ref() {
        return ref;
    }

    // 布局参数

    // 设置布局参数
    public T params(ViewGroup.LayoutParams pms) {
        ref().setLayoutParams(pms);
        return me();
    }

    // DP 转 PX，但是无小数
    public int dp2px(float dp) {
        if (dp < 0) {
            // 可能是 -1/-2
            return Math.round(dp);
        }
        return (int) (dpToPx(dp) + 0.5f);
    }

    // DP 转 PX
    public float dpToPx(float dp) {
        if (dp < 0) {
            // 可能是 -1/-2
            return dp;
        }
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, ctx().getResources().getDisplayMetrics());
    }

    // 尺寸属性

    // 设置宽度。像素
    public T width(int width) {
        ViewGroup.LayoutParams pms = layoutParams();
        pms.width = width;
        return params(pms);
    }

    // 设置高度。像素
    public T height(int height) {
        ViewGroup.LayoutParams pms = layoutParams();
        pms.height = height;
        return params(pms);
    }

    // 设置大小。像素
    public T size(int size) {
        return size(size, size);
    }

    // 设置大小。像素
    public T size(int width, int height) {
        ViewGroup.LayoutParams pms = layoutParams();
        pms.width = width;
        pms.height = height;
        return params(pms);
    }

    // 设置宽度。DP
    public T widthDp(float width) {
        return width(dp2px(width));
    }

    // 设置高度。DP
    public T heightDp(float height) {
        return height(dp2px(height));
    }

    // 设置大小。DP
    public T sizeDp(float size) {
        return size(dp2px(size));
    }

    // 设置大小。DP
    public T sizeDp(float width, float height) {
        return size(dp2px(width), dp2px(height));
    }

    // 设置最小宽度。像素
    public T minWidth(int width) {
        ref().setMinimumWidth(width);
        return me();
    }

    // 设置最小宽度。DP
    public T minWidthDp(float width) {
        return minWidth(dp2px(width));
    }

    // 设置最小高度。像素
    public T minHeight(int height) {
        ref().setMinimumHeight(height);
        return me();
    }

    // 设置最小高度。DP
    public T minHeightDp(float height) {
        return minHeight(dp2px(height));
    }

    // 边距属性

    // 设置边距。像素
    public T margin(int left, int top, int right, int bottom) {
        ViewGroup.LayoutParams pms = layoutParams();
        if (pms instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) pms).setMargins(left, top, right, bottom);
            params(pms);
        }
        return me();
    }

    // 设置边距。像素
    public T margin(int margin) {
        return margin(margin, margin, margin, margin);
    }

    // 设置边距。DP
    public T marginDp(float left, float top, float right, float bottom) {
        return margin(dp2px(left), dp2px(top), dp2px(right), dp2px(bottom));
    }

    // 设置边距。DP
    public T marginDp(float margin) {
        return marginDp(margin, margin, margin, margin);
    }

    // 设置左边距。像素
    public T marginLeft(int margin) {
        ViewGroup.LayoutParams pms = layoutParams();
        if (pms instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) pms).leftMargin = margin;
            params(pms);
        }
        return me();
    }

    // 设置左边距。DP
    public T marginLeftDp(float margin) {
        return marginLeft(dp2px(margin));
    }

    // 设置上边距。像素
    public T marginTop(int margin) {
        ViewGroup.LayoutParams pms = layoutParams();
        if (pms instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) pms).topMargin = margin;
            params(pms);
        }
        return me();
    }

    // 设置上边距。DP
    public T marginTopDp(float margin) {
        return marginTop(dp2px(margin));
    }

    // 设置右边距。像素
    public T marginRight(int margin) {
        ViewGroup.LayoutParams pms = layoutParams();
        if (pms instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) pms).rightMargin = margin;
            params(pms);
        }
        return me();
    }

    // 设置右边距。DP
    public T marginRightDp(float margin) {
        return marginRight(dp2px(margin));
    }

    // 设置下边距。像素
    public T marginBottom(int margin) {
        ViewGroup.LayoutParams pms = layoutParams();
        if (pms instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) pms).bottomMargin = margin;
            params(pms);
        }
        return me();
    }

    // 设置下边距。DP
    public T marginBottomDp(float margin) {
        return marginBottom(dp2px(margin));
    }

    // 设置水平边距。像素
    public T marginHorizontal(int margin) {
        return margin(margin, 0, margin, 0);
    }

    // 设置水平边距。DP
    public T marginHorizontalDp(float margin) {
        return marginHorizontal(dp2px(margin));
    }

    // 设置垂直边距。像素
    public T marginVertical(int margin) {
        return margin(0, margin, 0, margin);
    }

    // 设置垂直边距。DP
    public T marginVerticalDp(float margin) {
        return marginVertical(dp2px(margin));
    }

    // 设置起始边距。像素
    public T marginStart(int margin) {
        ViewGroup.LayoutParams pms = layoutParams();
        if (pms instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) pms).setMarginStart(margin);
            params(pms);
        }
        return me();
    }

    // 设置起始边距。DP
    public T marginStartDp(float margin) {
        return marginStart(dp2px(margin));
    }

    // 设置结束边距。像素
    public T marginEnd(int margin) {
        ViewGroup.LayoutParams pms = layoutParams();
        if (pms instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) pms).setMarginEnd(margin);
            params(pms);
        }
        return me();
    }

    // 设置结束边距。DP
    public T marginEndDp(float margin) {
        return marginEnd(dp2px(margin));
    }

    // 内边距属性

    // 设置内边距。像素
    public T padding(int left, int top, int right, int bottom) {
        ref().setPadding(left, top, right, bottom);
        return me();
    }

    // 设置内边距。像素
    public T padding(int padding) {
        return padding(padding, padding, padding, padding);
    }

    // 设置内边距。DP
    public T paddingDp(float left, float top, float right, float bottom) {
        return padding(dp2px(left), dp2px(top), dp2px(right), dp2px(bottom));
    }

    // 设置内边距。DP
    public T paddingDp(float padding) {
        return paddingDp(padding, padding, padding, padding);
    }

    // 设置左内边距。像素
    public T paddingLeft(int padding) {
        ref().setPadding(padding, ref().getPaddingTop(), ref().getPaddingRight(), ref().getPaddingBottom());
        return me();
    }

    // 设置左内边距。DP
    public T paddingLeftDp(float padding) {
        return paddingLeft(dp2px(padding));
    }

    // 设置上内边距。像素
    public T paddingTop(int padding) {
        ref().setPadding(ref().getPaddingLeft(), padding, ref().getPaddingRight(), ref().getPaddingBottom());
        return me();
    }

    // 设置上内边距。DP
    public T paddingTopDp(float padding) {
        return paddingTop(dp2px(padding));
    }

    // 设置右内边距。像素
    public T paddingRight(int padding) {
        ref().setPadding(ref().getPaddingLeft(), ref().getPaddingTop(), padding, ref().getPaddingBottom());
        return me();
    }

    // 设置右内边距。DP
    public T paddingRightDp(float padding) {
        return paddingRight(dp2px(padding));
    }

    // 设置下内边距。像素
    public T paddingBottom(int padding) {
        ref().setPadding(ref().getPaddingLeft(), ref().getPaddingTop(), ref().getPaddingRight(), padding);
        return me();
    }

    // 设置下内边距。DP
    public T paddingBottomDp(float padding) {
        return paddingBottom(dp2px(padding));
    }

    // 设置水平内边距。像素
    public T paddingHorizontal(int padding) {
        return padding(padding, ref().getPaddingTop(), padding, ref().getPaddingBottom());
    }

    // 设置水平内边距。DP
    public T paddingHorizontalDp(float padding) {
        return paddingHorizontal(dp2px(padding));
    }

    // 设置垂直内边距。像素
    public T paddingVertical(int padding) {
        return padding(ref().getPaddingLeft(), padding, ref().getPaddingRight(), padding);
    }

    // 设置垂直内边距。DP
    public T paddingVerticalDp(float padding) {
        return paddingVertical(dp2px(padding));
    }

    // 设置起始内边距。像素
    public T paddingStart(int padding) {
        ref().setPaddingRelative(padding, ref().getPaddingTop(), ref().getPaddingEnd(), ref().getPaddingBottom());
        return me();
    }

    // 设置起始内边距。DP
    public T paddingStartDp(float padding) {
        return paddingStart(dp2px(padding));
    }

    // 设置结束内边距。像素
    public T paddingEnd(int padding) {
        ref().setPaddingRelative(ref().getPaddingStart(), ref().getPaddingTop(), padding, ref().getPaddingBottom());
        return me();
    }

    // 设置结束内边距。DP
    public T paddingEndDp(float padding) {
        return paddingEnd(dp2px(padding));
    }

    // 标识属性

    // 设置 ID
    public T id(int id) {
        ref().setId(id);
        return me();
    }

    // 自动生成 ID
    public T nextId() {
        return id(View.generateViewId());
    }

    // 设置标签
    public T tag(Object tag) {
        ref().setTag(tag);
        return me();
    }

    // 背景属性

    // 设置背景
    public T background(Drawable background) {
        ref().setBackground(background);
        return me();
    }

    // 设置背景
    public T background(int id) {
        return background(drawable(id));
    }

    // 设置背景颜色
    public T backgroundColor(int color) {
        ref().setBackgroundColor(color);
        return me();
    }

    // 设置背景着色
    public T backgroundTint(ColorStateList list) {
        ref().setBackgroundTintList(list);
        return me();
    }

    // 设置背景着色
    public T backgroundTint(int color) {
        return backgroundTint(ColorStateList.valueOf(color));
    }

    // 设置水波纹背景
    public T touchBackground() {
        return background(drawable(android.R.attr.selectableItemBackground));
    }

    // 设置圆形水波纹背景
    public T touchCircleBackground() {
        return background(drawable(android.R.attr.selectableItemBackgroundBorderless));
    }

    // 前景属性

    // 设置前景
    public T foreground(Drawable foreground) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ref().setForeground(foreground);
        }
        return me();
    }

    // 设置前景
    public T foreground(int id) {
        return foreground(drawable(id));
    }

    // 设置前景颜色
    public T foregroundColor(int color) {
        return foreground(new ColorDrawable(color));
    }

    // 设置前景着色
    public T foregroundTint(ColorStateList list) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ref().setForegroundTintList(list);
        }
        return me();
    }

    // 设置前景着色
    public T foregroundTint(int color) {
        return foregroundTint(ColorStateList.valueOf(color));
    }

    // 设置水波纹前景
    public T touchForeground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ref().setForeground(drawable(android.R.attr.selectableItemBackground));
        }
        return me();
    }

    // 设置圆形水波纹前景
    public T touchCircleForeground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ref().setForeground(drawable(android.R.attr.selectableItemBackgroundBorderless));
        }
        return me();
    }

    // 显示属性

    // 设置可见性
    public T visibility(int visibility) {
        ref().setVisibility(visibility);
        return me();
    }

    // 设置可见性
    public T visibility(boolean visible) {
        return visibility(visible ? View.VISIBLE : View.GONE);
    }

    // 显示
    public T visible() {
        return visibility(View.VISIBLE);
    }

    // 隐藏
    public T gone() {
        return visibility(View.GONE);
    }

    // 交互属性

    // 设置可用
    public T enabled(boolean enabled) {
        ref().setEnabled(enabled);
        return me();
    }

    // 设置点击监听
    public T onClick(View.OnClickListener listener) {
        ref().setOnClickListener(listener);
        return me();
    }

    // 设置可点击
    public T onClick(boolean clickable) {
        ref().setClickable(clickable);
        return me();
    }

    // 设置长按监听
    public T onLongClick(View.OnLongClickListener listener) {
        ref().setOnLongClickListener(listener);
        return me();
    }

    // 设置可长按
    public T onLongClick(boolean longClickable) {
        ref().setLongClickable(longClickable);
        return me();
    }

    // 设置触摸监听
    public T onTouch(View.OnTouchListener listener) {
        ref().setOnTouchListener(listener);
        return me();
    }

    // 设置焦点监听
    public T onFocusChange(View.OnFocusChangeListener listener) {
        ref().setOnFocusChangeListener(listener);
        return me();
    }

    // 变换属性

    // 设置 X 坐标
    public T x(float x) {
        ref().setX(x);
        return me();
    }

    // 设置 Y 坐标
    public T y(float y) {
        ref().setY(y);
        return me();
    }

    // 设置 Z 坐标
    public T z(float z) {
        ref().setZ(z);
        return me();
    }

    // 设置 X 平移
    public T dx(float x) {
        ref().setTranslationX(x);
        return me();
    }

    // 设置 Y 平移
    public T dy(float y) {
        ref().setTranslationY(y);
        return me();
    }

    // 设置 Z 平移
    public T dz(float z) {
        ref().setTranslationZ(z);
        return me();
    }

    // 阴影属性

    // 设置阴影。像素
    public T elevation(float elevation) {
        ref().setElevation(elevation);
        return me();
    }

    // 设置阴影。DP
    public T elevationDp(float elevation) {
        return elevation(dp2px(elevation));
    }

    // 透明度属性

    // 设置透明度
    public T alpha(float alpha) {
        ref().setAlpha(alpha);
        return me();
    }

    // 获取布局参数，没有就创建一个
    private ViewGroup.LayoutParams layoutParams() {
        ViewGroup.LayoutParams pms = ref().getLayoutParams();
        if (pms == null) {
            // 居然没有！？
            // 我才不会写一长串常量引用呢，该有人治治谷歌了
            pms = new ViewGroup.MarginLayoutParams(-2, -2);
            ref().setLayoutParams(pms);
        }
        return pms;
    }

    // 获取 Drawable，自动识别 drawable/mipmap/attr 资源
    protected Drawable drawable(int id) {
        Resources res = ctx().getResources();
        String type = res.getResourceTypeName(id);
        if ("attr".equals(type)) {
            // 属性资源，交给主题解析
            TypedValue value = new TypedValue();
            if (ctx().getTheme().resolveAttribute(id, value, true)) {
                if (value.type >= TypedValue.TYPE_FIRST_COLOR_INT && value.type <= TypedValue.TYPE_LAST_COLOR_INT) {
                    // 解析出的是颜色，包装成纯色图
                    return new ColorDrawable(value.data);
                }
                if (value.resourceId != 0) {
                    // 解析出的是资源，再确认一下类型
                    String real = res.getResourceTypeName(value.resourceId);
                    if ("drawable".equals(real) || "mipmap".equals(real)) {
                        return ctx().getDrawable(value.resourceId);
                    }
                }
            }
            return null; // 主题里没找到这个属性
        }
        return ctx().getDrawable(id); // drawable/mipmap 直接加载
    }

}