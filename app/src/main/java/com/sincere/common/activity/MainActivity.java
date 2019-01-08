package com.sincere.common.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.sincere.common.R;
import com.sincere.common.adapter.MainPagerAdapter;
import com.sincere.common.base.BaseActivity;
import com.sincere.common.contract.MainContract;
import com.sincere.common.fragment.MarketFragment;
import com.sincere.common.fragment.OrderFragment;
import com.sincere.common.fragment.PersonFragment;
import com.sincere.common.fragment.ReserveFragment;
import com.sincere.common.model.MainModel;
import com.sincere.common.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainContract.IMainView, MainContract.IMainPresenter> implements MainContract.IMainView {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<Fragment> fragmentList;
    private MainPagerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainContract.IMainPresenter createPresenter() {
        return new MainPresenter(new MainModel());
    }

    @Override
    public MainContract.IMainView createView() {
        return this;
    }

    @Override
    public void init() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new ReserveFragment());
        fragmentList.add(new OrderFragment());
        fragmentList.add(new MarketFragment());
        fragmentList.add(new PersonFragment());
        adapter = new MainPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
    }
}
