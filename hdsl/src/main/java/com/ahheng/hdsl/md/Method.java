package com.ahheng.hdsl.md;

import com.ahheng.hdsl.ref.RefView;

// 方法
public interface Method <T extends RefView<?, ?>> {

    // 启动！
    void call(T t);

}
