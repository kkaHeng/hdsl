package com.ahheng.hdsl;

import android.content.Context;
import android.view.ViewGroup;

import com.ahheng.hdsl.md.Method;
import com.ahheng.hdsl.ref.RefLayout;
import com.ahheng.hdsl.tool.Tools;

import java.util.Objects;

// 阿恒的 DSL 布局框架
public interface HDSL extends CtxProvider, Tools, Factory {

    // 构建布局
    default <R extends ViewGroup> R build(R layout, Method<RefLayout<R, ?>> method) {
        Objects.requireNonNull(layout, "无法给空气配置属性");
        Objects.requireNonNull(method, "无法执行空气");
        method.call(new RefLayout<>(layout));
        return layout;
    }

    // 创建并构建布局
    default <R extends ViewGroup> R build(Class<R> clazz, Method<RefLayout<R, ?>> method) {
        Objects.requireNonNull(clazz, "无法利用空气创建控件");
        Objects.requireNonNull(method, "无法执行空气");
        try {
            return build(clazz.getConstructor(Context.class).newInstance(ctx()), method);
        } catch(Exception e) {
            throw new IllegalStateException("创建不了 " + clazz.getName());
        }
    }

    // 创建并构建布局
    default <R extends ViewGroup> R build(Creator<R> creator, Method<RefLayout<R, ?>> method) {
        Objects.requireNonNull(creator, "无法利用空气创建控件");
        Objects.requireNonNull(method, "无法执行空气");
        return build(creator.create(ctx()), method);
    }

}