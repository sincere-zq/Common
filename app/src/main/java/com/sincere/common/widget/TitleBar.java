package com.sincere.common.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sincere.common.R;


public class TitleBar extends RelativeLayout implements View.OnClickListener {
    private ImageView img_back, right_icon;
    private TextView tv_title;
    private String title;
    private int rightImgId;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
        init();
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar);
        title = typedArray.getString(R.styleable.TitleBar_title);
        rightImgId = typedArray.getResourceId(R.styleable.TitleBar_right_img, 0);
        typedArray.recycle();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_title_bar, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        img_back = view.findViewById(R.id.img_back);
        tv_title = view.findViewById(R.id.tv_title_bar_title);
        img_back.setOnClickListener(this);
        right_icon = view.findViewById(R.id.right_icon);
        tv_title.setText(title);
        if (rightImgId != 0) {
            right_icon.setImageResource(rightImgId);
        }
        addView(view);
    }

    public void setTitle(String title) {
        this.title = title;
        tv_title.setText(title);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_back) {
            try {
                ((Activity) getContext()).onBackPressed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
