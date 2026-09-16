package com.ahheng.hdsl.tool.view;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.SeekBar;

import com.ahheng.hdsl.tool.ViewTool;

// 拖动条工具
public class SeekBarTool<V extends SeekBar, T extends SeekBarTool<V, T>>
        extends ProgressBarTool<V, T> {

    public SeekBarTool(V view) {
        super(view);
    }

    // 拖动属性

    // 设置滑块
    public T thumb(Drawable drawable) {
        ref().setThumb(drawable);
        return me();
    }

    // 设置滑块
    public T thumb(int id) {
        return thumb(drawable(id));
    }

    // 设置滑块着色
    public T thumbTint(ColorStateList list) {
        ref().setThumbTintList(list);
        return me();
    }

    // 设置滑块着色
    public T thumbTint(int color) {
        return thumbTint(ColorStateList.valueOf(color));
    }

    // 设置按键增量
    public T keyProgressIncrement(int increment) {
        ref().setKeyProgressIncrement(increment);
        return me();
    }

    // 设置进度监听
    public T onSeekBarChange(SeekBar.OnSeekBarChangeListener listener) {
        ref().setOnSeekBarChangeListener(listener);
        return me();
    }

}