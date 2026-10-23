package com.ahheng.hdsl.ref;

import android.widget.LinearLayout;

import com.ahheng.hdsl.ref.RefLayout;

// 线性布局引用
public class RefLinear <V extends LinearLayout, T extends RefLinear<V, T>>
        extends RefLayout<V, T> {

    public RefLinear(V view) {
        super(view);
    }

    // 布局属性

    // 设置方向
    public T orientation(int orientation) {
        ref().setOrientation(orientation);
        return me();
    }

    // 垂直
    public T vertical() {
        ref().setOrientation(LinearLayout.VERTICAL);
        return me();
    }

    // 水平
    public T horizontal() {
        ref().setOrientation(LinearLayout.HORIZONTAL);
        return me();
    }

    // 设置对齐方式
    public T gravity(int g) {
        ref().setGravity(g);
        return me();
    }

}