package com.ahheng.hdsl.tool.view;

import android.widget.RatingBar;

import com.ahheng.hdsl.tool.ViewTool;

// 评分条工具
public class RatingBarTool<V extends RatingBar, T extends RatingBarTool<V, T>>
        extends ProgressBarTool<V, T> {

    public RatingBarTool(V view) {
        super(view);
    }

    // 评分属性

    // 设置星星数量
    public T numStars(int numStars) {
        ref().setNumStars(numStars);
        return me();
    }

    // 设置评分
    public T rating(float rating) {
        ref().setRating(rating);
        return me();
    }

    // 设置步长
    public T stepSize(float stepSize) {
        ref().setStepSize(stepSize);
        return me();
    }

    // 设置只读
    public T isIndicator(boolean isIndicator) {
        ref().setIsIndicator(isIndicator);
        return me();
    }

    // 设置只读
    public T isIndicator() {
        return isIndicator(true);
    }

    // 设置评分监听
    public T onRatingChange(RatingBar.OnRatingBarChangeListener listener) {
        ref().setOnRatingBarChangeListener(listener);
        return me();
    }

}