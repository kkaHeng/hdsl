package com.ahheng.hdsl;

import android.content.Context;
import android.view.View;

import com.ahheng.hdsl.md.Element;
import com.ahheng.hdsl.md.Method;
import com.ahheng.hdsl.ref.RefView;

import java.util.Objects;

// 阿恒的 DSL 布局框架
public interface HDSL extends CtxProvider, Factory {

    // 构建控件
    default <R extends View> R build(Element<R> element) {
        Objects.requireNonNull(element, "无法利用空气创建控件");
        R view = element.create(ctx());
        element.configure(view);
        return view;
    }

    // 构建控件，并且配置属性
    default <R extends View> R build(R view, Method<RefView<R, ?>> method) {
        Objects.requireNonNull(view, "无法给空气配置属性");
        Objects.requireNonNull(method, "无法执行空气");
        method.call(new RefView<>(view));
        return view;
    }

    // 创建并构建控件，并且配置属性
    default <R extends View> R build(Class<R> clazz, Method<RefView<R, ?>> method) {
        Objects.requireNonNull(clazz, "无法利用空气创建控件");
        Objects.requireNonNull(method, "无法执行空气");
        try {
            return build(clazz.getConstructor(Context.class).newInstance(ctx()), method);
        } catch(Exception e) {
            throw new IllegalStateException("创建不了 " + clazz.getName());
        }
    }

}