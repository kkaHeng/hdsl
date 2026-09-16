package com.ahheng.hdsl.tool.view;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.TextView;

import com.ahheng.hdsl.tool.ViewTool;

// 文本框工具
public class TextViewTool <V extends TextView, T extends TextViewTool<V, T>>
        extends ViewTool<V, T> {

    public TextViewTool(V view) {
        super(view);
    }

    // 文本属性

    // 设置文本
    public T text(CharSequence text) {
        ref().setText(text);
        return me();
    }

    // 设置提示文本
    public T hint(CharSequence hint) {
        ref().setHint(hint);
        return me();
    }

    // 设置文本大小。SP
    public T textSize(float size) {
        ref().setTextSize(size);
        return me();
    }

    // 设置文本大小。DP
    public T textSizeDp(float size) {
        ref().setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        return me();
    }

    // 设置文本大小。PX
    public T textSizePx(float size) {
        ref().setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        return me();
    }

    // 设置文本颜色
    public T textColor(int color) {
        ref().setTextColor(color);
        return me();
    }

    // 设置文本颜色
    public T textColor(ColorStateList colors) {
        ref().setTextColor(colors);
        return me();
    }

    // 设置提示文本颜色
    public T hintColor(int color) {
        ref().setHintTextColor(color);
        return me();
    }

    // 设置提示文本颜色
    public T hintColor(ColorStateList colors) {
        ref().setHintTextColor(colors);
        return me();
    }

    // 设置对齐方式
    public T gravity(int g) {
        ref().setGravity(g);
        return me();
    }

    // 设置省略方式
    public T ellipsize(TextUtils.TruncateAt ellipsize) {
        ref().setEllipsize(ellipsize);
        return me();
    }

    // 设置单行
    public T singleLine(boolean singleLine) {
        ref().setSingleLine(singleLine);
        return me();
    }

    // 设置单行
    public T singleLine() {
        ref().setSingleLine();
        return me();
    }

    // 设置文本可选中
    public T textIsSelectable(boolean selectable) {
        ref().setTextIsSelectable(selectable);
        return me();
    }

    // 设置文本可选中
    public T textIsSelectable() {
        return textIsSelectable(true);
    }

    // 设置字体
    public T typeface(Typeface typeface) {
        ref().setTypeface(typeface);
        return me();
    }

    // 设置字体样式
    public T typeface(int style) {
        ref().setTypeface(ref().getTypeface(), style);
        return me();
    }

    // 设置行数
    public T lines(int lines) {
        ref().setLines(lines);
        return me();
    }

    // 设置最小行数
    public T minLines(int lines) {
        ref().setMinLines(lines);
        return me();
    }

    // 设置最大行数
    public T maxLines(int lines) {
        ref().setMaxLines(lines);
        return me();
    }

    // 设置宽度
    public T ems(int ems) {
        ref().setEms(ems);
        return me();
    }

    // 设置最小宽度
    public T minEms(int ems) {
        ref().setMinEms(ems);
        return me();
    }

    // 设置最大宽度
    public T maxEms(int ems) {
        ref().setMaxEms(ems);
        return me();
    }

    // 设置最大宽度。像素
    public T maxWidth(int width) {
        ref().setMaxWidth(width);
        return me();
    }

    // 设置最大高度。像素
    public T maxHeight(int height) {
        ref().setMaxHeight(height);
        return me();
    }

    // 设置最大宽度。DP
    public T maxWidthDp(float width) {
        return maxWidth(dp2px(width));
    }

    // 设置最大高度。DP
    public T maxHeightDp(float height) {
        return maxHeight(dp2px(height));
    }

}