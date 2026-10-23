package com.ahheng.hdsl.ref;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;

import com.ahheng.hdsl.ref.RefText;

// 复合按钮引用
public class RefCheck<V extends CompoundButton, T extends RefCheck<V, T>>
        extends RefText<V, T> {

    public RefCheck(V view) {
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
