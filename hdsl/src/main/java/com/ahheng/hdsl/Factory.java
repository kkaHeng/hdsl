package com.ahheng.hdsl;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.ahheng.hdsl.md.Element;
import com.ahheng.hdsl.md.Method;
import com.ahheng.hdsl.ref.RefCheck;
import com.ahheng.hdsl.ref.RefEdit;
import com.ahheng.hdsl.ref.RefFrame;
import com.ahheng.hdsl.ref.RefGrid;
import com.ahheng.hdsl.ref.RefImage;
import com.ahheng.hdsl.ref.RefLayout;
import com.ahheng.hdsl.ref.RefLinear;
import com.ahheng.hdsl.ref.RefProgress;
import com.ahheng.hdsl.ref.RefRating;
import com.ahheng.hdsl.ref.RefRelative;
import com.ahheng.hdsl.ref.RefScroll;
import com.ahheng.hdsl.ref.RefSeek;
import com.ahheng.hdsl.ref.RefSwitch;
import com.ahheng.hdsl.ref.RefText;
import com.ahheng.hdsl.ref.RefView;

// 构件工厂
// 只负责组装构件，不负责配置属性
public interface Factory {

    // 控件创建器
    interface Creator<T extends View> {

        // 创建控件
        T create(Context ctx);

    }

    // 反射创建器
    static <T extends View> Creator<T> reflect(Class<T> clazz) {
        return ctx -> {
            try {
                return clazz.getConstructor(Context.class).newInstance(ctx);
            } catch(Exception e) {
                throw new IllegalStateException("创建不了 " + clazz.getName());
            }
        };
    }

    // 常用控件

    // View 构件
    default Element<View> view(Method<RefView<View, ?>> method) {
        return Element.of(View::new, view -> method.call(new RefView<>(view)));
    }

    default <R extends View> Element<R> view(R view, Method<RefView<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefView<>(v)));
    }

    default <R extends View> Element<R> view(Class<R> clazz, Method<RefView<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefView<>(v)));
    }

    // TextView 构件
    default Element<TextView> text(Method<RefText<TextView, ?>> method) {
        return Element.of(TextView::new, view -> method.call(new RefText<>(view)));
    }

    default <R extends TextView> Element<R> text(R view, Method<RefText<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefText<>(v)));
    }

    default <R extends TextView> Element<R> text(Class<R> clazz, Method<RefText<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefText<>(v)));
    }

    // Button 构件
    default Element<Button> button(Method<RefText<Button, ?>> method) {
        return Element.of(Button::new, view -> method.call(new RefText<>(view)));
    }

    default <R extends Button> Element<R> button(R view, Method<RefText<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefText<>(v)));
    }

    default <R extends Button> Element<R> button(Class<R> clazz, Method<RefText<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefText<>(v)));
    }

    // EditText 构件
    default Element<EditText> edit(Method<RefEdit<EditText, ?>> method) {
        return Element.of(EditText::new, view -> method.call(new RefEdit<>(view)));
    }

    default <R extends EditText> Element<R> edit(R view, Method<RefEdit<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefEdit<>(v)));
    }

    default <R extends EditText> Element<R> edit(Class<R> clazz, Method<RefEdit<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefEdit<>(v)));
    }

    // ImageView 构件
    default Element<ImageView> image(Method<RefImage<ImageView, ?>> method) {
        return Element.of(ImageView::new, view -> method.call(new RefImage<>(view)));
    }

    default <R extends ImageView> Element<R> image(R view, Method<RefImage<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefImage<>(v)));
    }

    default <R extends ImageView> Element<R> image(Class<R> clazz, Method<RefImage<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefImage<>(v)));
    }

    // ProgressBar 构件
    default Element<ProgressBar> progress(Method<RefProgress<ProgressBar, ?>> method) {
        return Element.of(ProgressBar::new, view -> method.call(new RefProgress<>(view)));
    }

    default <R extends ProgressBar> Element<R> progress(R view, Method<RefProgress<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefProgress<>(v)));
    }

    default <R extends ProgressBar> Element<R> progress(Class<R> clazz, Method<RefProgress<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefProgress<>(v)));
    }

    // CheckBox 构件
    default Element<CheckBox> check(Method<RefCheck<CheckBox, ?>> method) {
        return Element.of(CheckBox::new, view -> method.call(new RefCheck<>(view)));
    }

    default <R extends CheckBox> Element<R> check(R view, Method<RefCheck<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefCheck<>(v)));
    }

    default <R extends CheckBox> Element<R> check(Class<R> clazz, Method<RefCheck<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefCheck<>(v)));
    }

    // RadioButton 构件
    default Element<RadioButton> radio(Method<RefCheck<RadioButton, ?>> method) {
        return Element.of(RadioButton::new, view -> method.call(new RefCheck<>(view)));
    }

    default <R extends RadioButton> Element<R> radio(R view, Method<RefCheck<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefCheck<>(v)));
    }

    default <R extends RadioButton> Element<R> radio(Class<R> clazz, Method<RefCheck<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefCheck<>(v)));
    }

    // Switch 构件
    default Element<Switch> switch_button(Method<RefSwitch<Switch, ?>> method) {
        return Element.of(Switch::new, view -> method.call(new RefSwitch<>(view)));
    }

    default <R extends Switch> Element<R> switch_button(R view, Method<RefSwitch<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefSwitch<>(v)));
    }

    default <R extends Switch> Element<R> switch_button(Class<R> clazz, Method<RefSwitch<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefSwitch<>(v)));
    }

    // Space 构件
    default Element<Space> space(Method<RefView<Space, ?>> method) {
        return Element.of(Space::new, view -> method.call(new RefView<>(view)));
    }

    default <R extends Space> Element<R> space(R view, Method<RefView<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefView<>(v)));
    }

    default <R extends Space> Element<R> space(Class<R> clazz, Method<RefView<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefView<>(v)));
    }

    // SeekBar 构件
    default Element<SeekBar> seek(Method<RefSeek<SeekBar, ?>> method) {
        return Element.of(SeekBar::new, view -> method.call(new RefSeek<>(view)));
    }

    default <R extends SeekBar> Element<R> seek(R view, Method<RefSeek<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefSeek<>(v)));
    }

    default <R extends SeekBar> Element<R> seek(Class<R> clazz, Method<RefSeek<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefSeek<>(v)));
    }

    // RatingBar 构件
    default Element<RatingBar> rating(Method<RefRating<RatingBar, ?>> method) {
        return Element.of(RatingBar::new, view -> method.call(new RefRating<>(view)));
    }

    default <R extends RatingBar> Element<R> rating(R view, Method<RefRating<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefRating<>(v)));
    }

    default <R extends RatingBar> Element<R> rating(Class<R> clazz, Method<RefRating<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefRating<>(v)));
    }

    // Spinner 构件
    default Element<Spinner> spinner(Method<RefView<Spinner, ?>> method) {
        return Element.of(Spinner::new, view -> method.call(new RefView<>(view)));
    }

    default <R extends Spinner> Element<R> spinner(R view, Method<RefView<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefView<>(v)));
    }

    default <R extends Spinner> Element<R> spinner(Class<R> clazz, Method<RefView<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefView<>(v)));
    }

    // Chronometer 构件
    default Element<Chronometer> chrono(Method<RefText<Chronometer, ?>> method) {
        return Element.of(Chronometer::new, view -> method.call(new RefText<>(view)));
    }

    default <R extends Chronometer> Element<R> chrono(R view, Method<RefText<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefText<>(v)));
    }

    default <R extends Chronometer> Element<R> chrono(Class<R> clazz, Method<RefText<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefText<>(v)));
    }

    // WebView 构件
    default Element<WebView> web(Method<RefLayout<WebView, ?>> method) {
        return Element.of(WebView::new, view -> method.call(new RefLayout<>(view)));
    }

    default <R extends WebView> Element<R> web(R view, Method<RefLayout<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefLayout<>(v)));
    }

    default <R extends WebView> Element<R> web(Class<R> clazz, Method<RefLayout<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefLayout<>(v)));
    }

    // ListView 构件
    default Element<ListView> list(Method<RefLayout<ListView, ?>> method) {
        return Element.of(ListView::new, view -> method.call(new RefLayout<>(view)));
    }

    default <R extends ListView> Element<R> list(R view, Method<RefLayout<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefLayout<>(v)));
    }

    default <R extends ListView> Element<R> list(Class<R> clazz, Method<RefLayout<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefLayout<>(v)));
    }

    // GridView 构件
    default Element<GridView> grid_view(Method<RefLayout<GridView, ?>> method) {
        return Element.of(GridView::new, view -> method.call(new RefLayout<>(view)));
    }

    default <R extends GridView> Element<R> grid_view(R view, Method<RefLayout<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefLayout<>(v)));
    }

    default <R extends GridView> Element<R> grid_view(Class<R> clazz, Method<RefLayout<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefLayout<>(v)));
    }

    // SearchView 构件
    default Element<SearchView> search(Method<RefLinear<SearchView, ?>> method) {
        return Element.of(SearchView::new, view -> method.call(new RefLinear<>(view)));
    }

    default <R extends SearchView> Element<R> search(R view, Method<RefLinear<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefLinear<>(v)));
    }

    default <R extends SearchView> Element<R> search(Class<R> clazz, Method<RefLinear<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefLinear<>(v)));
    }

    // 常用布局

    // LinearLayout 构件
    default Element<LinearLayout> linear(Method<RefLinear<LinearLayout, ?>> method) {
        return Element.of(LinearLayout::new, view -> method.call(new RefLinear<>(view)));
    }

    default <R extends LinearLayout> Element<R> linear(R view, Method<RefLinear<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefLinear<>(v)));
    }

    default <R extends LinearLayout> Element<R> linear(Class<R> clazz, Method<RefLinear<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefLinear<>(v)));
    }

    // FrameLayout 构件
    default Element<FrameLayout> frame(Method<RefFrame<FrameLayout, ?>> method) {
        return Element.of(FrameLayout::new, view -> method.call(new RefFrame<>(view)));
    }

    default <R extends FrameLayout> Element<R> frame(R view, Method<RefFrame<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefFrame<>(v)));
    }

    default <R extends FrameLayout> Element<R> frame(Class<R> clazz, Method<RefFrame<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefFrame<>(v)));
    }

    // RelativeLayout 构件
    default Element<RelativeLayout> relative(Method<RefRelative<RelativeLayout, ?>> method) {
        return Element.of(RelativeLayout::new, view -> method.call(new RefRelative<>(view)));
    }

    default <R extends RelativeLayout> Element<R> relative(R view, Method<RefRelative<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefRelative<>(v)));
    }

    default <R extends RelativeLayout> Element<R> relative(Class<R> clazz, Method<RefRelative<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefRelative<>(v)));
    }

    // ScrollView 构件
    default Element<ScrollView> scroll(Method<RefScroll<ScrollView, ?>> method) {
        return Element.of(ScrollView::new, view -> method.call(new RefScroll<>(view)));
    }

    default <R extends ScrollView> Element<R> scroll(R view, Method<RefScroll<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefScroll<>(v)));
    }

    default <R extends ScrollView> Element<R> scroll(Class<R> clazz, Method<RefScroll<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefScroll<>(v)));
    }

    // HorizontalScrollView 构件
    default Element<HorizontalScrollView> hscroll(Method<RefFrame<HorizontalScrollView, ?>> method) {
        return Element.of(HorizontalScrollView::new, view -> method.call(new RefFrame<>(view)));
    }

    default <R extends HorizontalScrollView> Element<R> hscroll(R view, Method<RefFrame<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefFrame<>(v)));
    }

    default <R extends HorizontalScrollView> Element<R> hscroll(Class<R> clazz, Method<RefFrame<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefFrame<>(v)));
    }

    // GridLayout 构件
    default Element<GridLayout> grid(Method<RefGrid<GridLayout, ?>> method) {
        return Element.of(GridLayout::new, view -> method.call(new RefGrid<>(view)));
    }

    default <R extends GridLayout> Element<R> grid(R view, Method<RefGrid<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefGrid<>(v)));
    }

    default <R extends GridLayout> Element<R> grid(Class<R> clazz, Method<RefGrid<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefGrid<>(v)));
    }

    // RadioGroup 构件
    default Element<RadioGroup> radio_group(Method<RefLinear<RadioGroup, ?>> method) {
        return Element.of(RadioGroup::new, view -> method.call(new RefLinear<>(view)));
    }

    default <R extends RadioGroup> Element<R> radio_group(R view, Method<RefLinear<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefLinear<>(v)));
    }

    default <R extends RadioGroup> Element<R> radio_group(Class<R> clazz, Method<RefLinear<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefLinear<>(v)));
    }

    // TableLayout 构件
    default Element<TableLayout> table(Method<RefLinear<TableLayout, ?>> method) {
        return Element.of(TableLayout::new, view -> method.call(new RefLinear<>(view)));
    }

    default <R extends TableLayout> Element<R> table(R view, Method<RefLinear<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefLinear<>(v)));
    }

    default <R extends TableLayout> Element<R> table(Class<R> clazz, Method<RefLinear<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefLinear<>(v)));
    }

    // TableRow 构件
    default Element<TableRow> table_row(Method<RefLinear<TableRow, ?>> method) {
        return Element.of(TableRow::new, view -> method.call(new RefLinear<>(view)));
    }

    default <R extends TableRow> Element<R> table_row(R view, Method<RefLinear<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefLinear<>(v)));
    }

    default <R extends TableRow> Element<R> table_row(Class<R> clazz, Method<RefLinear<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefLinear<>(v)));
    }

    // ViewSwitcher 构件
    default Element<ViewSwitcher> switcher(Method<RefLayout<ViewSwitcher, ?>> method) {
        return Element.of(ViewSwitcher::new, view -> method.call(new RefLayout<>(view)));
    }

    default <R extends ViewSwitcher> Element<R> switcher(R view, Method<RefLayout<R, ?>> method) {
        return Element.of(ctx -> view, v -> method.call(new RefLayout<>(v)));
    }

    default <R extends ViewSwitcher> Element<R> switcher(Class<R> clazz, Method<RefLayout<R, ?>> method) {
        return Element.of(reflect(clazz), v -> method.call(new RefLayout<>(v)));
    }

}