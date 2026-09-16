package com.ahheng.hdsl.ref;

import android.view.ViewGroup;

import com.ahheng.hdsl.ref.layout.AddViewAction;
import com.ahheng.hdsl.ref.layout.AddViewIndexAction;
import com.ahheng.hdsl.ref.layout.AddViewIndexParamsAction;
import com.ahheng.hdsl.ref.layout.AddViewParamsAction;
import com.ahheng.hdsl.ref.layout.AddViewSizeAction;

// 引用安卓布局
public class RefLayout <V extends ViewGroup, T extends RefLayout<V, T>> extends RefView<V, T>
        implements AddViewAction<V, T>, AddViewIndexAction<V, T>, AddViewParamsAction<V, T>,
        AddViewIndexParamsAction<V, T>, AddViewSizeAction<V, T> {

    public RefLayout(V ref) {
        super(ref);
    }

}
