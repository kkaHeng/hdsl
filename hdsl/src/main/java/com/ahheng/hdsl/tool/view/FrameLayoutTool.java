package com.ahheng.hdsl.tool.view;

import android.widget.FrameLayout;

import com.ahheng.hdsl.tool.ViewTool;

// 帧布局工具
public class FrameLayoutTool<V extends FrameLayout, T extends FrameLayoutTool<V, T>>
        extends ViewTool<V, T> {

    public FrameLayoutTool(V view) {
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