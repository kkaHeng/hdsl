package com.ahheng.hdsl.md;

import android.view.View;

// 配置器
public interface Configurer <V extends View> {

    // 配置属性
    void configure(V view);

}