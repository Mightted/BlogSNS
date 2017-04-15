package com.mightted.blogsns.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.mightted.blogsns.R;
import com.mightted.blogsns.Utils.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 晓深 on 2017/4/12.
 */

public class HomeFragment extends Fragment {


    private ViewPager viewPager;
    private FragmentAdapter adapter;
    private PagerSlidingTabStrip tabs;

    private List<Fragment> fragmentList;
    private List<String> titleList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_bottom_home,container,false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);

        initFragment();

        return view;
    }

    private void initFragment() {

        fragmentList = new ArrayList<>();
        fragmentList.add(new TotalFragment());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());

        titleList = new ArrayList<>();
        titleList.add(new String("第一页"));
        titleList.add(new String("第二页"));
        titleList.add(new String("第三页"));


        adapter = new FragmentAdapter(getChildFragmentManager(),fragmentList,titleList);
        viewPager.setAdapter(adapter);
        tabs.setViewPager(viewPager);

    }
}
