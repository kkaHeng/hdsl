package com.ahheng.hdsl.tool.view;

import android.widget.RelativeLayout;

import com.ahheng.hdsl.tool.ViewTool;

// 相对布局工具
public class RelativeLayoutTool<V extends RelativeLayout, T extends RelativeLayoutTool<V, T>>
        extends ViewTool<V, T> {

    public RelativeLayoutTool(V view) {
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