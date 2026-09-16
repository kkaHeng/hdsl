package com.ahheng.hdsl;

import android.view.View;

// 返回自己
public interface ReturnMe <V extends View, T extends ReturnMe<V, T>> {

    @SuppressWarnings("unchecked")
    default T me() {
        return (T) this;
    }

}
