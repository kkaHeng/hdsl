package com.ahheng.hdsl.tool.view;

import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.widget.EditText;

import com.ahheng.hdsl.tool.ViewTool;

// 输入框工具
public class EditTextTool<V extends EditText, T extends EditTextTool<V, T>>
        extends TextViewTool<V, T> {

    public EditTextTool(V view) {
        super(view);
    }

    // 输入属性

    // 设置输入类型
    public T inputType(int inputType) {
        ref().setInputType(inputType);
        return me();
    }

    // 设置 IME 选项
    public T imeOptions(int imeOptions) {
        ref().setImeOptions(imeOptions);
        return me();
    }

    // 设置最大长度
    public T maxLength(int maxLength) {
        ref().setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        return me();
    }

    // 设置全选聚焦
    public T selectAllOnFocus(boolean selectAllOnFocus) {
        ref().setSelectAllOnFocus(selectAllOnFocus);
        return me();
    }

    // 设置全选聚焦
    public T selectAllOnFocus() {
        return selectAllOnFocus(true);
    }

    // 设置允许输入的字符
    public T digits(CharSequence digits) {
        ref().setKeyListener(DigitsKeyListener.getInstance(digits.toString()));
        return me();
    }

}
