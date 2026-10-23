package com.ahheng.hdsl.ref.layout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ahheng.hdsl.CtxProvider;
import com.ahheng.hdsl.ReturnMe;
import com.ahheng.hdsl.md.Element;
import com.ahheng.hdsl.md.Method;
import com.ahheng.hdsl.ref.RefLayout;
import com.ahheng.hdsl.ref.RefView;
import com.ahheng.hdsl.ref.Reference;

import java.util.Objects;

// 添加控件操作。索引版
// 组合：
// addView(Element, int)
// addView(View, int)
// addView(View, int, Method)
// addView(Class, int)
// addView(Class, int, Method)
public interface AddViewIndexAction <V extends ViewGroup, T extends AddViewIndexAction<V, T>>
        extends Reference<V>, ReturnMe<V, T>, CtxProvider {

    // 添加一个控件
    default T view(View view, int index) {
        ref().addView(view, index);
        return me();
    }

    // 添加一个控件，并且配置属性
    default <R extends View> T view(R view, int index, Method<RefView<R, ?>> method) {
        Objects.requireNonNull(view, "无法给空气配置属性");
        Objects.requireNonNull(method, "无法执行空气");
        ref().addView(view, index);
        method.call(new RefView<>(view));
        return me();
    }

    // 创建添加一个控件
    default T view(Class<? extends View> clazz, int index) {
        Objects.requireNonNull(clazz, "无法利用空气创建控件");
        try {
            return view(clazz.getConstructor(Context.class).newInstance(ctx()), index);
        } catch(Exception e) {
            throw new IllegalStateException("创建不了 " + clazz.getName());
        }
    }

    // 创建添加一个控件，并且配置属性
    default <R extends View> T view(Class<R> clazz, int index, Method<RefView<R, ?>> method) {
        Objects.requireNonNull(clazz, "无法利用空气创建控件");
        Objects.requireNonNull(method, "无法执行空气");
        try {
            return view(clazz.getConstructor(Context.class).newInstance(ctx()), index, method);
        } catch(Exception e) {
            throw new IllegalStateException("创建不了 " + clazz.getName());
        }
    }

    // 构建添加一个控件
    default <R extends View> T view(Element<R> element, int index) {
        Objects.requireNonNull(element, "无法利用空气创建控件");
        R view = element.create(ctx());
        ref().addView(view, index);
        element.configure(view);
        return me();
    }

    // 添加一个布局
    default T layout(ViewGroup layout, int index) {
        ref().addView(layout, index);
        return me();
    }

    // 添加一个布局，并且配置属性
    default <R extends ViewGroup> T layout(R layout, int index, Method<RefLayout<R, ?>> method) {
        Objects.requireNonNull(layout, "无法给空气配置属性");
        Objects.requireNonNull(method, "无法执行空气");
        ref().addView(layout, index);
        method.call(new RefLayout<>(layout));
        return me();
    }

    // 创建添加一个布局
    default T layout(Class<? extends ViewGroup> clazz, int index) {
        Objects.requireNonNull(clazz, "无法利用空气创建控件");
        try {
            return layout(clazz.getConstructor(Context.class).newInstance(ctx()), index);
        } catch(Exception e) {
            throw new IllegalStateException("创建不了 " + clazz.getName());
        }
    }

    // 创建添加一个布局，并且配置属性
    default <R extends ViewGroup> T layout(Class<R> clazz, int index, Method<RefLayout<R, ?>> method) {
        Objects.requireNonNull(clazz, "无法利用空气创建控件");
        Objects.requireNonNull(method, "无法执行空气");
        try {
            return layout(clazz.getConstructor(Context.class).newInstance(ctx()), index, method);
        } catch(Exception e) {
            throw new IllegalStateException("创建不了 " + clazz.getName());
        }
    }

    // 构建添加一个布局
    default <R extends ViewGroup> T layout(Element<R> element, int index) {
        Objects.requireNonNull(element, "无法利用空气创建控件");
        R layout = element.create(ctx());
        ref().addView(layout, index);
        element.configure(layout);
        return me();
    }

}