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

// 添加控件操作
// 组合：
// addView(View, Method?)
// addView(Class, Method?)
// addView(Creator, Method?)
public interface AddViewAction <V extends ViewGroup, T extends AddViewAction<V, T>>
        extends Reference<V>, ReturnMe<V, T>, CtxProvider {

    // 添加一个控件
    default T view(View view) {
        ref().addView(view);
        return me();
    }

    // 创建添加一个控件
    default T view(Class<? extends View> clazz) {
        Objects.requireNonNull(clazz, "无法利用空气创建控件");
        try {
            return view(clazz.getConstructor(Context.class).newInstance(ctx()));
        } catch(Exception e) {
            throw new IllegalStateException("创建不了 " + clazz.getName());
        }
    }

    // 创建添加一个控件，并且配置属性
    default <R extends View> T view(Class<R> clazz, Method<RefView<R,?>> method) {
        Objects.requireNonNull(clazz, "无法利用空气创建控件");
        try {
            return view(clazz.getConstructor(Context.class).newInstance(ctx()), method);
        } catch(Exception e) {
            throw new IllegalStateException("创建不了 " + clazz.getName());
        }
    }

    // 创建添加一个控件
    default <R extends View> T view(Factory.Creator<R> creator) {
        Objects.requireNonNull(creator, "无法利用空气创建控件");
        return view(creator.create(ctx()));
    }

    // 创建添加一个控件，并且配置属性
    default <R extends View> T view(Factory.Creator<R> creator, Method<RefView<R,?>> method) {
        Objects.requireNonNull(creator, "无法利用空气创建控件");
        Objects.requireNonNull(method, "无法执行空气");
        return view(creator.create(ctx()), method);
    }

    // 添加一个控件，并且配置属性
    default <R extends View> T view(R view, Method<RefView<R,?>> method) {
        Objects.requireNonNull(view, "无法给空气配置属性");
        Objects.requireNonNull(method, "无法执行空气");
        ref().addView(view);
        method.call(new RefView<>(view));
        return me();
    }

    // 添加一个布局
    default T layout(ViewGroup view) {
        ref().addView(view);
        return me();
    }

    // 创建添加一个布局
    default T layout(Class<? extends ViewGroup> clazz) {
        Objects.requireNonNull(clazz, "无法利用空气创建控件");
        try {
            return layout(clazz.getConstructor(Context.class).newInstance(ctx()));
        } catch(Exception e) {
            throw new IllegalStateException("创建不了 " + clazz.getName());
        }
    }

    // 创建添加一个布局，并且配置属性
    default <R extends ViewGroup> T layout(Class<R> clazz, Method<RefLayout<R,?>> method) {
        Objects.requireNonNull(clazz, "无法利用空气创建控件");
        try {
            return layout(clazz.getConstructor(Context.class).newInstance(ctx()), method);
        } catch(Exception e) {
            throw new IllegalStateException("创建不了 " + clazz.getName());
        }
    }

    // 创建添加一个布局
    default <R extends ViewGroup> T layout(Factory.Creator<R> creator) {
        Objects.requireNonNull(creator, "无法利用空气创建控件");
        return layout(creator.create(ctx()));
    }

    // 创建添加一个布局，并且配置属性
    default <R extends ViewGroup> T layout(Factory.Creator<R> creator, Method<RefLayout<R,?>> method) {
        Objects.requireNonNull(creator, "无法利用空气创建控件");
        Objects.requireNonNull(method, "无法执行空气");
        return layout(creator.create(ctx()), method);
    }

    // 添加一个布局，并且配置属性
    default <R extends ViewGroup> T layout(R view, Method<RefLayout<R,?>> method) {
        Objects.requireNonNull(view, "无法给空气配置属性");
        Objects.requireNonNull(method, "无法执行空气");
        ref().addView(view);
        method.call(new RefLayout<>(view));
        return me();
    }

}