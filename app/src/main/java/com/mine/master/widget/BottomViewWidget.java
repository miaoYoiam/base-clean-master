package com.mine.master.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mine.master.App;
import com.mine.master.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomViewWidget extends LinearLayout {
    @BindView(R.id.bottom_home_container)
    LinearLayout homeContainer;
    @BindView(R.id.bottom_home_icon)
    ImageView homeIcon;
    @BindView(R.id.bottom_mine_icon)
    ImageView mineIcon;
    @BindView(R.id.bottom_mine_container)
    LinearLayout mineContainer;
    @BindView(R.id.bottom_home_text)
    TextView homeTitle;
    @BindView(R.id.bottom_mine_text)
    TextView mineTitle;

    private ArrayList<ImageView> icons = new ArrayList<>();
    private static final int TAB_HOME = 0;
    private static final int TAB_MINE = TAB_HOME + 1;
    private int currentIndex = 0;
    private BottomTabClickListener clickListener;


    public BottomViewWidget(Context context) {
        super(context);
    }


    public BottomViewWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomViewWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
        initView();
    }

    private void initView() {
        icons.add(homeIcon);
        icons.add(mineIcon);
        homeContainer.setOnClickListener(v -> {
            if (currentIndex != TAB_HOME) {
                currentIndex = TAB_HOME;
                homeIcon.setImageResource(R.mipmap.icon_bottom_home_select);
                mineIcon.setImageResource(R.mipmap.icon_bottom_mine_unselect);
                homeTitle.setTextColor(ContextCompat.getColor(App.app, R.color.bg_light_main));
                mineTitle.setTextColor(ContextCompat.getColor(App.app, R.color.text_black));
                if (clickListener != null) {
                    clickListener.onClickTab(currentIndex);
                }
            }
        });

        mineContainer.setOnClickListener(v -> {
            if (currentIndex != TAB_MINE) {
                currentIndex = TAB_MINE;
                homeIcon.setImageResource(R.mipmap.icon_bottom_home_unselect);
                mineIcon.setImageResource(R.mipmap.icon_bottom_mine_select);
                homeTitle.setTextColor(ContextCompat.getColor(App.app, R.color.text_black));
                mineTitle.setTextColor(ContextCompat.getColor(App.app, R.color.bg_light_main));
                if (clickListener != null) {
                    clickListener.onClickTab(currentIndex);
                }
            }
        });
    }

    public void setTabClickListener(BottomTabClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface BottomTabClickListener {
        void onClickTab(int currentTab);
    }
}
