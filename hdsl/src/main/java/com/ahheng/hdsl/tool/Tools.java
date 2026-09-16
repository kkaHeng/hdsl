package com.ahheng.hdsl.tool;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.ahheng.hdsl.ref.RefView;
import com.ahheng.hdsl.tool.view.CompoundButtonTool;
import com.ahheng.hdsl.tool.view.EditTextTool;
import com.ahheng.hdsl.tool.view.FrameLayoutTool;
import com.ahheng.hdsl.tool.view.GridLayoutTool;
import com.ahheng.hdsl.tool.view.ImageViewTool;
import com.ahheng.hdsl.tool.view.LinearLayoutTool;
import com.ahheng.hdsl.tool.view.ProgressBarTool;
import com.ahheng.hdsl.tool.view.RatingBarTool;
import com.ahheng.hdsl.tool.view.RelativeLayoutTool;
import com.ahheng.hdsl.tool.view.ScrollViewTool;
import com.ahheng.hdsl.tool.view.SeekBarTool;
import com.ahheng.hdsl.tool.view.SwitchTool;
import com.ahheng.hdsl.tool.view.TextViewTool;

public interface Tools {

    // View 属性工具

    // 从 View 创建 View 属性工具
    default <R extends View> ViewTool<R, ?> view(R view) {
        return new ViewTool<>(view);
    }

    // 从 RefView 创建 View 属性工具
    default <R extends View> ViewTool<R, ?> view(RefView<R, ?> ref) {
        return new ViewTool<>(ref.ref());
    }

    // TextView 属性工具

    // 从 View 创建 TextView 属性工具
    default <R extends TextView> TextViewTool<R, ?> text(R view) {
        return new TextViewTool<>(view);
    }

    // 从 RefView 创建 TextView 属性工具
    default <R extends TextView> TextViewTool<R, ?> text(RefView<R, ?> ref) {
        return new TextViewTool<>(ref.ref());
    }

    // EditText 属性工具

    // 从 View 创建 EditText 属性工具
    default <R extends EditText> EditTextTool<R, ?> edit(R view) {
        return new EditTextTool<>(view);
    }

    // 从 RefView 创建 EditText 属性工具
    default <R extends EditText> EditTextTool<R, ?> edit(RefView<R, ?> ref) {
        return new EditTextTool<>(ref.ref());
    }

    // ImageView 属性工具

    // 从 View 创建 ImageView 属性工具
    default <R extends ImageView> ImageViewTool<R, ?> image(R view) {
        return new ImageViewTool<>(view);
    }

    // 从 RefView 创建 ImageView 属性工具
    default <R extends ImageView> ImageViewTool<R, ?> image(RefView<R, ?> ref) {
        return new ImageViewTool<>(ref.ref());
    }

    // CompoundButton 属性工具

    // 从 View 创建 CompoundButton 属性工具
    default <R extends CompoundButton> CompoundButtonTool<R, ?> check(R view) {
        return new CompoundButtonTool<>(view);
    }

    // 从 RefView 创建 CompoundButton 属性工具
    default <R extends CompoundButton> CompoundButtonTool<R, ?> check(RefView<R, ?> ref) {
        return new CompoundButtonTool<>(ref.ref());
    }

    // Switch 属性工具

    // 从 View 创建 Switch 属性工具
    default <R extends Switch> SwitchTool<R, ?> switch_button(R view) {
        return new SwitchTool<>(view);
    }

    // 从 RefView 创建 Switch 属性工具
    default <R extends Switch> SwitchTool<R, ?> switch_button(RefView<R, ?> ref) {
        return new SwitchTool<>(ref.ref());
    }

    // ProgressBar 属性工具

    // 从 View 创建 ProgressBar 属性工具
    default <R extends ProgressBar> ProgressBarTool<R, ?> progress(R view) {
        return new ProgressBarTool<>(view);
    }

    // 从 RefView 创建 ProgressBar 属性工具
    default <R extends ProgressBar> ProgressBarTool<R, ?> progress(RefView<R, ?> ref) {
        return new ProgressBarTool<>(ref.ref());
    }

    // SeekBar 属性工具

    // 从 View 创建 SeekBar 属性工具
    default <R extends SeekBar> SeekBarTool<R, ?> seek(R view) {
        return new SeekBarTool<>(view);
    }

    // 从 RefView 创建 SeekBar 属性工具
    default <R extends SeekBar> SeekBarTool<R, ?> seek(RefView<R, ?> ref) {
        return new SeekBarTool<>(ref.ref());
    }

    // RatingBar 属性工具

    // 从 View 创建 RatingBar 属性工具
    default <R extends RatingBar> RatingBarTool<R, ?> rating(R view) {
        return new RatingBarTool<>(view);
    }

    // 从 RefView 创建 RatingBar 属性工具
    default <R extends RatingBar> RatingBarTool<R, ?> rating(RefView<R, ?> ref) {
        return new RatingBarTool<>(ref.ref());
    }

    // LinearLayout 属性工具

    // 从 View 创建 LinearLayout 属性工具
    default <R extends LinearLayout> LinearLayoutTool<R, ?> linear(R view) {
        return new LinearLayoutTool<>(view);
    }

    // 从 RefView 创建 LinearLayout 属性工具
    default <R extends LinearLayout> LinearLayoutTool<R, ?> linear(RefView<R, ?> ref) {
        return new LinearLayoutTool<>(ref.ref());
    }

    // FrameLayout 属性工具

    // 从 View 创建 FrameLayout 属性工具
    default <R extends FrameLayout> FrameLayoutTool<R, ?> frame(R view) {
        return new FrameLayoutTool<>(view);
    }

    // 从 RefView 创建 FrameLayout 属性工具
    default <R extends FrameLayout> FrameLayoutTool<R, ?> frame(RefView<R, ?> ref) {
        return new FrameLayoutTool<>(ref.ref());
    }

    // RelativeLayout 属性工具

    // 从 View 创建 RelativeLayout 属性工具
    default <R extends RelativeLayout> RelativeLayoutTool<R, ?> relative(R view) {
        return new RelativeLayoutTool<>(view);
    }

    // 从 RefView 创建 RelativeLayout 属性工具
    default <R extends RelativeLayout> RelativeLayoutTool<R, ?> relative(RefView<R, ?> ref) {
        return new RelativeLayoutTool<>(ref.ref());
    }

    // ScrollView 属性工具

    // 从 View 创建 ScrollView 属性工具
    default <R extends ScrollView> ScrollViewTool<R, ?> scroll(R view) {
        return new ScrollViewTool<>(view);
    }

    // 从 RefView 创建 ScrollView 属性工具
    default <R extends ScrollView> ScrollViewTool<R, ?> scroll(RefView<R, ?> ref) {
        return new ScrollViewTool<>(ref.ref());
    }

    // GridLayout 属性工具

    // 从 View 创建 GridLayout 属性工具
    default <R extends GridLayout> GridLayoutTool<R, ?> grid(R view) {
        return new GridLayoutTool<>(view);
    }

    // 从 RefView 创建 GridLayout 属性工具
    default <R extends GridLayout> GridLayoutTool<R, ?> grid(RefView<R, ?> ref) {
        return new GridLayoutTool<>(ref.ref());
    }

}