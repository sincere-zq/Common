package com.sincere.common.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.netson.canlenderdemo.calendar.SimpleMonthAdapter;
import com.sincere.common.R;
import com.sincere.common.activity.CalendarActivity;
import com.sincere.common.base.BaseFragment;
import com.sincere.common.contract.ReserveContract;
import com.sincere.common.holder.BannerHolder;
import com.sincere.common.model.ReserveModel;
import com.sincere.common.presenter.ReservePresenter;
import com.sincere.common.utils.CaculateDateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.sincere.common.activity.CalendarActivity.CHECK_IN_DAY;
import static com.sincere.common.activity.CalendarActivity.CHECK_OUT_DAY;

/**
 * 预定
 */
public class ReserveFragment extends BaseFragment<ReserveContract.IReserveView, ReserveContract.IReservePresenter> implements ReserveContract.IReserveView {


    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.btn_hotel_reserve)
    Button btnHotelReserve;
    @BindView(R.id.btn_hour_room)
    Button btnHourRoom;
    @BindView(R.id.tv_check_in_time)
    TextView tvCheckInTime;
    @BindView(R.id.tv_total_days)
    TextView tvTotalDays;
    @BindView(R.id.tv_check_out_time)
    TextView tvCheckOutTime;
    @BindView(R.id.calendar_layout)
    LinearLayout calendarLayout;
    @BindView(R.id.btn_immeidate_reserve)
    Button btnImmeidateReserve;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public ReserveContract.IReservePresenter createPresenter() {
        return new ReservePresenter(new ReserveModel());
    }

    @Override
    public ReserveContract.IReserveView createView() {
        return this;
    }

    @Override
    public void init() {
        banner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL).startTurning(2000L);
        getPresenter().initReserveDate();
        getPresenter().banner();
    }

    @Override
    public void setReserveDate(SimpleMonthAdapter.CalendarDay checkInDay, SimpleMonthAdapter.CalendarDay checkOutDay) {
        tvTotalDays.setText("共" + CaculateDateUtil.caculateDays(checkInDay.getDate(), checkOutDay.getDate()) + "晚");
        tvCheckInTime.setText(checkInDay.month + 1 + "月" + checkInDay.day + "日");
        tvCheckOutTime.setText(checkOutDay.month + 1 + "月" + checkOutDay.day + "日");
    }

    @Override
    public void selectLiveDate(Bundle bundle) {
        Intent intent = new Intent(getActivity(), CalendarActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 100);
    }

    @Override
    public void banner(List<String> bannerList) {
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new BannerHolder(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.view_image_banner;
            }
        }, bannerList);
    }

    @OnClick({R.id.btn_hotel_reserve, R.id.btn_hour_room, R.id.calendar_layout, R.id.btn_immeidate_reserve})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_hotel_reserve:
                break;
            case R.id.btn_hour_room:
                break;
            case R.id.calendar_layout:
                getPresenter().selectLiveDate();
                break;
            case R.id.btn_immeidate_reserve:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case -1:
                if (requestCode == 100) {
                    getPresenter().selectLiveDateBack((SimpleMonthAdapter.CalendarDay) data.getSerializableExtra(CHECK_IN_DAY),
                            (SimpleMonthAdapter.CalendarDay) data.getSerializableExtra(CHECK_OUT_DAY));
                }
                break;
        }
    }
}
