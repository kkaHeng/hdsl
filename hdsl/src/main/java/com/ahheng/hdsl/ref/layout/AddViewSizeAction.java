package com.ahheng.hdsl.ref.layout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ahheng.hdsl.CtxProvider;
import com.ahheng.hdsl.Factory;
import com.ahheng.hdsl.ReturnMe;
import com.ahheng.hdsl.md.Method;
import com.ahheng.hdsl.ref.RefLayout;
import com.ahheng.hdsl.ref.RefView;
import com.ahheng.hdsl.ref.Reference;

import java.util.Objects;

// 添加控件操作。尺寸版
// 组合：
// addView(View, int, int, Method?)
// addView(Class, int, int, Method?)
// addView(Creator, int, int, Method?)
public interface AddViewSizeAction <V extends ViewGroup, T extends AddViewSizeAction<V, T>>
        extends Reference<V>, ReturnMe<V, T>, CtxProvider {

    // 添加一个控件
    default T view(View view, int width, int height) {
        ref().addView(view, width, height);
        return me();
    }

    // 创建添加一个控件
    default T view(Class<? extends View> clazz, int width, int height) {
        Objects.requireNonNull(clazz, "无法利用空气创建控件");
        try {
            return view(clazz.getConstructor(Context.class).newInstance(ctx()), width, height);
        } catch(Exception e) {
            throw new IllegalStateException("创建不了 " + clazz.getName());
        }
    }

    // 创建添加一个控件，并且配置属性
    default <R extends View> T view(Class<R> clazz, int width, int height, Method<RefView<R,?>> method) {
        Objects.requireNonNull(clazz, "无法利用空气创建控件");
        try {
            return view(clazz.getConstructor(Context.class).newInstance(ctx()), width, height, method);
        } catch(Exception e) {
            throw new IllegalStateException("创建不了 " + clazz.getName());
        }
    }

    // 创建添加一个控件
    default <R extends View> T view(Factory.Creator<R> creator, int width, int height) {
        Objects.requireNonNull(creator, "无法利用空气创建控件");
        return view(creator.create(ctx()), width, height);
    }

    // 创建添加一个控件，并且配置属性
    default <R extends View> T view(Factory.Creator<R> creator, int width, int height, Method<RefView<R,?>> method) {
        Objects.requireNonNull(creator, "无法利用空气创建控件");
        Objects.requireNonNull(method, "无法执行空气");
        return view(creator.create(ctx()), width, height, method);
    }

    // 添加一个控件，并且配置属性
    default <R extends View> T view(R view, int width, int height, Method<RefView<R,?>> method) {
        Objects.requireNonNull(view, "无法给空气配置属性");
        Objects.requireNonNull(method, "无法执行空气");
        ref().addView(view, width, height);
        method.call(new RefView<>(view));
        return me();
    }

    // 添加一个布局
    default T layout(ViewGroup view, int width, int height) {
        ref().addView(view, width, height);
        return me();
    }

    // 创建添加一个布局
    default T layout(Class<? extends ViewGroup> clazz, int width, int height) {
        Objects.requireNonNull(clazz, "无法利用空气创建控件");
        try {
            return layout(clazz.getConstructor(Context.class).newInstance(ctx()), width, height);
        } catch(Exception e) {
            throw new IllegalStateException("创建不了 " + clazz.getName());
        }
    }

    // 创建添加一个布局，并且配置属性
    default <R extends ViewGroup> T layout(Class<R> clazz, int width, int height, Method<RefLayout<R,?>> method) {
        Objects.requireNonNull(clazz, "无法利用空气创建控件");
        try {
            return layout(clazz.getConstructor(Context.class).newInstance(ctx()), width, height, method);
        } catch(Exception e) {
            throw new IllegalStateException("创建不了 " + clazz.getName());
        }
    }

    // 创建添加一个布局
    default <R extends ViewGroup> T layout(Factory.Creator<R> creator, int width, int height) {
        Objects.requireNonNull(creator, "无法利用空气创建控件");
        return layout(creator.create(ctx()), width, height);
    }

    // 创建添加一个布局，并且配置属性
    default <R extends ViewGroup> T layout(Factory.Creator<R> creator, int width, int height, Method<RefLayout<R,?>> method) {
        Objects.requireNonNull(creator, "无法利用空气创建控件");
        Objects.requireNonNull(method, "无法执行空气");
        return layout(creator.create(ctx()), width, height, method);
    }

    // 添加一个布局，并且配置属性
    default <R extends ViewGroup> T layout(R view, int width, int height, Method<RefLayout<R,?>> method) {
        Objects.requireNonNull(view, "无法给空气配置属性");
        Objects.requireNonNull(method, "无法执行空气");
        ref().addView(view, width, height);
        method.call(new RefLayout<>(view));
        return me();
    }

}