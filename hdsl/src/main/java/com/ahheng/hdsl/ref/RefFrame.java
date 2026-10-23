package com.ahheng.hdsl.ref;

import android.widget.FrameLayout;

import com.ahheng.hdsl.ref.RefLayout;

// 帧布局引用
public class RefFrame<V extends FrameLayout, T extends RefFrame<V, T>>
        extends RefLayout<V, T> {

    public RefFrame(V view) {
        super(view);
    }

    // 帧布局属性

    // 设置前景对齐方式
    public T foregroundGravity(int gravity) {
        ref().setForegroundGravity(gravity);
        return me();
    }

    // 设置测量所有子控件
    public T measureAllChildren(boolean measureAllChildren) {
        ref().setMeasureAllChildren(measureAllChildren);
        return me();
    }

    // 设置测量所有子控件
    public T measureAllChildren() {
        return measureAllChildren(true);
    }

}