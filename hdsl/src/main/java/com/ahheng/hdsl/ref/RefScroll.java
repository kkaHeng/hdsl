package com.ahheng.hdsl.ref;

import android.widget.ScrollView;

import com.ahheng.hdsl.ref.RefFrame;

// 滚动布局引用
public class RefScroll<V extends ScrollView, T extends RefScroll<V, T>>
        extends RefFrame<V, T> {

    public RefScroll(V view) {
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