package com.ahheng.hdsl.ref;

import android.widget.RelativeLayout;

import com.ahheng.hdsl.ref.RefLayout;

// 相对布局引用
public class RefRelative<V extends RelativeLayout, T extends RefRelative<V, T>>
        extends RefLayout<V, T> {

    public RefRelative(V view) {
        super(view);
    }

    // 相对布局属性

    // 设置对齐方式
    public T gravity(int gravity) {
        ref().setGravity(gravity);
        return me();
    }

    // 设置忽略对齐的控件
    public T ignoreGravity(int viewId) {
        ref().setIgnoreGravity(viewId);
        return me();
    }

}