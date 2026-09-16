package com.ahheng.hdsl.ref;

import android.view.View;

// 控件引用
public interface Reference <V extends View> {

    // 获取原始引用
    V ref();

}
