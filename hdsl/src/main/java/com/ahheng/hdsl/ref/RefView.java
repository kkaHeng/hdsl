package com.ahheng.hdsl.ref;

import android.content.Context;
import android.view.View;

import com.ahheng.hdsl.CtxProvider;
import com.ahheng.hdsl.ReturnMe;

// 引用安卓控件
public class RefView<V extends View, T extends RefView<V, T>>
        implements Reference<V>, ReturnMe<V, T>, CtxProvider {

    private final V ref; // 原始引用

    public RefView(V ref) {
        this.ref = ref;
    }

    @Override
    public Context ctx() {
        return ref.getContext(); // 直接复用
    }

    @Override
    public V ref() {
        return ref;
    }

}
