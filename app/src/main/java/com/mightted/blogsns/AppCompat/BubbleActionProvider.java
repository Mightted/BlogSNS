package com.mightted.blogsns.AppCompat;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mightted.blogsns.R;

/**
 * Created by 晓深 on 2017/4/13.
 */

public class BubbleActionProvider extends ActionProvider {

    private TextView mBubbleText;

    private int clickWhat;
    private OnBubbleClickListener clickListener;

    public BubbleActionProvider(Context context) {
        super(context);
    }


    @Override
    public View onCreateActionView() {
        int size = getContext().getResources().getDimensionPixelSize(android.support.design.R.dimen.abc_action_bar_default_height_material);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(size,size);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_bubble_provider,null,false);
        view.setLayoutParams(layoutParams);
        mBubbleText = (TextView)view.findViewById(R.id.item_bubble);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener != null) {
                    clickListener.onClick(clickWhat);
                }
            }
        });

        return view;
    }

    public void setOnBubbleClickListener(int what, OnBubbleClickListener clickListener) {
        this.clickWhat = what;
        this.clickListener = clickListener;
    }

    public interface OnBubbleClickListener {
        void onClick(int what);
    }
}
