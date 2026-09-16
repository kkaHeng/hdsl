package com.ahheng.hdsl.tool.view;

import android.content.res.ColorStateList;
import android.widget.Switch;

import com.ahheng.hdsl.tool.ViewTool;

// 开关工具
public class SwitchTool<V extends Switch, T extends SwitchTool<V, T>>
        extends CompoundButtonTool<V, T> {

    public SwitchTool(V view) {
        super(view);
    }

    // 开关属性

    // 设置滑块着色
    public T thumbTint(ColorStateList list) {
        ref().setThumbTintList(list);
        return me();
    }

    // 设置滑块着色
    public T thumbTint(int color) {
        return thumbTint(ColorStateList.valueOf(color));
    }

    // 设置轨道着色
    public T trackTint(ColorStateList list) {
        ref().setTrackTintList(list);
        return me();
    }

    // 设置轨道着色
    public T trackTint(int color) {
        return trackTint(ColorStateList.valueOf(color));
    }

    // 设置显示文字
    public T showText(boolean showText) {
        ref().setShowText(showText);
        return me();
    }

    // 设置显示文字
    public T showText() {
        return showText(true);
    }

    // 设置最小宽度。像素
    public T switchMinWidth(int width) {
        ref().setSwitchMinWidth(width);
        return me();
    }

    // 设置最小宽度。DP
    public T switchMinWidthDp(float width) {
        return switchMinWidth(dp2px(width));
    }

    // 设置开关内边距。像素
    public T switchPadding(int padding) {
        ref().setSwitchPadding(padding);
        return me();
    }

    // 设置开关内边距。DP
    public T switchPaddingDp(float padding) {
        return switchPadding(dp2px(padding));
    }

}
