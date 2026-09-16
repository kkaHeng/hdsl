package com.ahheng.hdsl.tool.view;

import android.widget.LinearLayout;

import com.ahheng.hdsl.tool.ViewTool;

// 线性布局工具
public class LinearLayoutTool <V extends LinearLayout, T extends LinearLayoutTool<V, T>>
        extends ViewTool<V, T> {

    public LinearLayoutTool(V view) {
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
