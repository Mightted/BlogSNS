package com.mightted.blogsns.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mightted.blogsns.R;
import com.mightted.blogsns.Utils.LogUtil;

/**
 * Created by 晓深 on 2017/4/11.
 */

public class TotalFragment extends Fragment {

    private boolean isPrepare;
    private boolean isVisible;

    private SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_total,container,false);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);

        initListener();
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
            LogUtil.i(getTag(),"Now fragment1 is visible ");
            onVisible();
        } else {
            isVisible = false;
            LogUtil.i(getTag(),"Now fragment1 is invisible");
            onInVisible();
        }
    }

    private void initListener() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                refreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private void lazyLoad() {

    }

    private void onVisible() {

    }

    private void onInVisible() {

    }
}
