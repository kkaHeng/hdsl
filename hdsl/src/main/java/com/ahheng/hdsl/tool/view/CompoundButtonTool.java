package com.ahheng.hdsl.tool.view;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;

import com.ahheng.hdsl.tool.ViewTool;

// 复合按钮工具
public class CompoundButtonTool<V extends CompoundButton, T extends CompoundButtonTool<V, T>>
        extends TextViewTool<V, T> {

    public CompoundButtonTool(V view) {
        super(view);
    }

    // 选择属性

    // 设置选中
    public T checked(boolean checked) {
        ref().setChecked(checked);
        return me();
    }

    // 设置选中
    public T checked() {
        return checked(true);
    }

    // 切换选中
    public T toggle() {
        ref().toggle();
        return me();
    }

    // 设置按钮图标
    public T buttonDrawable(Drawable drawable) {
        ref().setButtonDrawable(drawable);
        return me();
    }

    // 设置按钮图标
    public T buttonDrawable(int id) {
        ref().setButtonDrawable(id);
        return me();
    }

    // 设置按钮着色
    public T buttonTint(ColorStateList list) {
        ref().setButtonTintList(list);
        return me();
    }

    // 设置按钮着色
    public T buttonTint(int color) {
        return buttonTint(ColorStateList.valueOf(color));
    }

    // 设置选中监听
    public T onCheckedChange(CompoundButton.OnCheckedChangeListener listener) {
        ref().setOnCheckedChangeListener(listener);
        return me();
    }

}
