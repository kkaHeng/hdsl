package com.ahheng.hdsl;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

// 上下文提供者
// 不兼容任何外部库，谷歌的也不例外
public interface CtxProvider {

    // 获取安卓上下文(自动感知)
    default Context ctx() {
        if (this instanceof Context) {
            // 自己就是上下文
            return (Context) this;
        }
        if (this instanceof View) {
            // 自己是安卓控件
            return ((View) this).getContext();
        }
        if (this instanceof Dialog) {
            // 自己是对话框
            return ((Dialog) this).getContext();
        }
        // 自己屁都不是
        throw new IllegalStateException("我提前帮你报错了，记得实现 ctx() 方法返回上下文，因为我不知道你的类是什么玩意");
    }

}
