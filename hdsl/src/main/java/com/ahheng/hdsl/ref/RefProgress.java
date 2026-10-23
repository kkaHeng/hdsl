package com.ahheng.hdsl.ref;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.ProgressBar;

import com.ahheng.hdsl.ref.RefView;

// 进度条引用
public class RefProgress<V extends ProgressBar, T extends RefProgress<V, T>>
        extends RefView<V, T> {

    public RefProgress(V view) {
        super(view);
    }

    // 进度属性

    // 设置最大值
    public T max(int max) {
        ref().setMax(max);
        return me();
    }

    // 设置进度
    public T progress(int progress) {
        ref().setProgress(progress);
        return me();
    }

    // 设置第二进度
    public T secondaryProgress(int secondaryProgress) {
        ref().setSecondaryProgress(secondaryProgress);
        return me();
    }

    // 设置不确定模式
    public T indeterminate(boolean indeterminate) {
        ref().setIndeterminate(indeterminate);
        return me();
    }

    // 设置不确定模式
    public T indeterminate() {
        return indeterminate(true);
    }

    // 设置不确定进度图
    public T indeterminateDrawable(Drawable drawable) {
        ref().setIndeterminateDrawable(drawable);
        return me();
    }

    // 设置不确定进度图
    public T indeterminateDrawable(int id) {
        return indeterminateDrawable(drawable(id));
    }

    // 设置进度图
    public T progressDrawable(Drawable drawable) {
        ref().setProgressDrawable(drawable);
        return me();
    }

    // 设置进度图
    public T progressDrawable(int id) {
        return progressDrawable(drawable(id));
    }

    // 设置进度着色
    public T progressTint(ColorStateList list) {
        ref().setProgressTintList(list);
        return me();
    }

    // 设置进度着色
    public T progressTint(int color) {
        return progressTint(ColorStateList.valueOf(color));
    }

    // 设置进度背景着色
    public T progressBackgroundTint(ColorStateList list) {
        ref().setProgressBackgroundTintList(list);
        return me();
    }

    // 设置进度背景着色
    public T progressBackgroundTint(int color) {
        return progressBackgroundTint(ColorStateList.valueOf(color));
    }

    // 设置不确定进度着色
    public T indeterminateTint(ColorStateList list) {
        ref().setIndeterminateTintList(list);
        return me();
    }

    // 设置不确定进度着色
    public T indeterminateTint(int color) {
        return indeterminateTint(ColorStateList.valueOf(color));
    }

}
