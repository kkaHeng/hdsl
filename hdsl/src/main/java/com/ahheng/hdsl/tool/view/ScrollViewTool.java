package com.ahheng.hdsl.tool.view;

import android.widget.ScrollView;

import com.ahheng.hdsl.tool.ViewTool;

// 滚动布局工具
public class ScrollViewTool<V extends ScrollView, T extends ScrollViewTool<V, T>>
        extends FrameLayoutTool<V, T> {

    public ScrollViewTool(V view) {
        super(view);
    }

    // 滚动属性

    // 设置填满视口
    public T fillViewport(boolean fillViewport) {
        ref().setFillViewport(fillViewport);
        return me();
    }

    // 设置填满视口
    public T fillViewport() {
        return fillViewport(true);
    }

    // 设置平滑滚动
    public T smoothScrollingEnabled(boolean smoothScrollingEnabled) {
        ref().setSmoothScrollingEnabled(smoothScrollingEnabled);
        return me();
    }

    // 设置平滑滚动
    public T smoothScrollingEnabled() {
        return smoothScrollingEnabled(true);
    }

}