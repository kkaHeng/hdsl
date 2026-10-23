package com.ahheng.hdsl.ref;

import android.widget.GridLayout;

import com.ahheng.hdsl.ref.RefLayout;

// 网格布局引用
public class RefGrid<V extends GridLayout, T extends RefGrid<V, T>>
        extends RefLayout<V, T> {

    public RefGrid(V view) {
        super(view);
    }

    // 网格属性

    // 设置列数
    public T columnCount(int count) {
        ref().setColumnCount(count);
        return me();
    }

    // 设置行数
    public T rowCount(int count) {
        ref().setRowCount(count);
        return me();
    }

    // 设置方向
    public T orientation(int orientation) {
        ref().setOrientation(orientation);
        return me();
    }

    // 垂直
    public T vertical() {
        return orientation(GridLayout.VERTICAL);
    }

    // 水平
    public T horizontal() {
        return orientation(GridLayout.HORIZONTAL);
    }

    // 设置对齐模式
    public T alignmentMode(int alignmentMode) {
        ref().setAlignmentMode(alignmentMode);
        return me();
    }

    // 设置使用默认边距
    public T useDefaultMargins(boolean useDefaultMargins) {
        ref().setUseDefaultMargins(useDefaultMargins);
        return me();
    }

    // 设置使用默认边距
    public T useDefaultMargins() {
        return useDefaultMargins(true);
    }

}