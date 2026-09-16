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

// 控件工厂
// 只负责创建控件，不负责配置属性
public interface Factory {

    // 控件创建器
    interface Creator<T extends View> {

        // 创建控件
        T create(Context ctx);

    }

    // 常用控件

    Creator<View> VIEW = View::new;
    Creator<TextView> TEXT = TextView::new;
    Creator<Button> BUTTON = Button::new;
    Creator<EditText> EDIT = EditText::new;
    Creator<ImageView> IMAGE = ImageView::new;
    Creator<ProgressBar> PROGRESS = ProgressBar::new;
    Creator<CheckBox> CHECK = CheckBox::new;
    Creator<RadioButton> RADIO = RadioButton::new;
    Creator<Switch> SWITCH = Switch::new;
    Creator<Space> SPACE = Space::new;
    Creator<SeekBar> SEEK = SeekBar::new;
    Creator<RatingBar> RATING = RatingBar::new;
    Creator<Spinner> SPINNER = Spinner::new;
    Creator<Chronometer> CHRONO = Chronometer::new;
    Creator<WebView> WEB = WebView::new;
    Creator<ListView> LIST = ListView::new;
    Creator<GridView> GRID_VIEW = GridView::new;
    Creator<SearchView> SEARCH = SearchView::new;

    // 常用布局

    Creator<LinearLayout> LINEAR = LinearLayout::new;
    Creator<FrameLayout> FRAME = FrameLayout::new;
    Creator<RelativeLayout> RELATIVE = RelativeLayout::new;
    Creator<ScrollView> SCROLL = ScrollView::new;
    Creator<HorizontalScrollView> HSCROLL = HorizontalScrollView::new;
    Creator<GridLayout> GRID = GridLayout::new;
    Creator<RadioGroup> RADIO_GROUP = RadioGroup::new;
    Creator<TableLayout> TABLE = TableLayout::new;
    Creator<TableRow> TABLE_ROW = TableRow::new;
    Creator<ViewSwitcher> SWITCHER = ViewSwitcher::new;

}