package com.mightted.blogsns.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mightted.blogsns.R;
import com.mightted.blogsns.Utils.LogUtil;

/**
 * Created by 晓深 on 2017/4/11.
 */

public class Fragment2 extends Fragment {

    private boolean isPrepare;
    private boolean isVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page2,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepare = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            LogUtil.i(getTag(),"Now fragment2 is visible ");
            onVisible();
        } else {
            isVisible = false;
            LogUtil.i(getTag(),"Now fragment2 is invisible");
            onInVisible();
        }
    }

    private void lazyLoad() {

    }

    private void onVisible() {

    }

    private void onInVisible() {

    }
}
