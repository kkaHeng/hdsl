package com.ahheng.hdsl.md;

import android.content.Context;
import android.view.View;

import com.ahheng.hdsl.Factory;

// 构件
// 创建与配置，一次给全
public interface Element <V extends View> {

    // 创建控件
    V create(Context ctx);

    // 配置属性
    void configure(V view);

    // 组装构件
    static <V extends View> Element<V> of(Factory.Creator<V> creator, Configurer<V> configurer) {
        return new Element<V>() {

            @Override
            public V create(Context ctx) {
                return creator.create(ctx);
            }

            @Override
            public void configure(V view) {
                configurer.configure(view);
            }

        };
    }

}
