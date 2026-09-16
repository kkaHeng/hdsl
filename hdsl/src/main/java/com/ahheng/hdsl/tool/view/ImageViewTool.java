package com.ahheng.hdsl.tool.view;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.ahheng.hdsl.tool.ViewTool;

// 图片工具
public class ImageViewTool<V extends ImageView, T extends ImageViewTool<V, T>>
        extends ViewTool<V, T> {

    public ImageViewTool(V view) {
        super(view);
    }

    // 图片属性

    // 设置图片
    public T src(Drawable drawable) {
        ref().setImageDrawable(drawable);
        return me();
    }

    // 设置图片
    public T src(int id) {
        ref().setImageResource(id);
        return me();
    }

    // 设置缩放方式
    public T scaleType(ImageView.ScaleType scaleType) {
        ref().setScaleType(scaleType);
        return me();
    }

    // 设置自动调整边界
    public T adjustViewBounds(boolean adjustViewBounds) {
        ref().setAdjustViewBounds(adjustViewBounds);
        return me();
    }

    // 设置自动调整边界
    public T adjustViewBounds() {
        return adjustViewBounds(true);
    }

    // 设置裁剪内边距
    public T cropToPadding(boolean cropToPadding) {
        ref().setCropToPadding(cropToPadding);
        return me();
    }

    // 设置裁剪内边距
    public T cropToPadding() {
        return cropToPadding(true);
    }

    // 设置图片着色
    public T tint(ColorStateList list) {
        ref().setImageTintList(list);
        return me();
    }

    // 设置图片着色
    public T tint(int color) {
        return tint(ColorStateList.valueOf(color));
    }

    // 设置图片透明度。0-255
    public T imageAlpha(int alpha) {
        ref().setImageAlpha(alpha);
        return me();
    }

    // 设置最大宽度。像素
    public T maxWidth(int width) {
        ref().setMaxWidth(width);
        return me();
    }

    // 设置最大宽度。DP
    public T maxWidthDp(float width) {
        return maxWidth(dp2px(width));
    }

    // 设置最大高度。像素
    public T maxHeight(int height) {
        ref().setMaxHeight(height);
        return me();
    }

    // 设置最大高度。DP
    public T maxHeightDp(float height) {
        return maxHeight(dp2px(height));
    }

}
