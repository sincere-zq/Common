package com.netson.canlenderdemo.calendar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netson.canlenderdemo.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarSelectLayout extends LinearLayout implements DatePickerController {
    private TextView checkIn, checkOut;
    private DayPickerView dayPicker;
    private OnSelectedTimeListener onSelectedTimeListener;
    private boolean isConfiguration;

    public void setOnSelectedTimeListener(OnSelectedTimeListener onSelectedTimeListener) {
        this.onSelectedTimeListener = onSelectedTimeListener;
    }

    public CalendarSelectLayout(Context context) {
        this(context, null);
    }

    public CalendarSelectLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarSelectLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.canlendar_select_layout, null);
        checkIn = (TextView) view.findViewById(R.id.check_in);
        checkOut = (TextView) view.findViewById(R.id.check_out);

        dayPicker = (DayPickerView) view.findViewById(R.id.day_picker);

        dayPicker.setController(this);

        addView(view);

    }


    @Override
    public int getMaxYear() {
        return Calendar.getInstance().get(Calendar.YEAR) + 1;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day) {

    }

    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {
        //获取第一个选择的日期
        SimpleMonthAdapter.CalendarDay firstDay = selectedDays.getFirst();
        //获取第二个选择日期
        SimpleMonthAdapter.CalendarDay lastDay = selectedDays.getLast();
        //根据需求做对应的逻辑处理，
        //我这里是做了一个往返日期逻辑，得到两个选中的日期后需要进行判断出小的日期肯定是出发日期。
        if (firstDay != null) {
            Calendar firstCalendar = Calendar.getInstance();
            firstCalendar.set(firstDay.year, firstDay.month, firstDay.day);
            checkIn.setText("入住时间:" + getYYYY_MM_dd(new Date(firstCalendar.getTimeInMillis())));
            checkIn.setTag(firstCalendar);
        } else {
            checkIn.setText(null);
            checkIn.setTag(null);
        }
        if (lastDay != null) {
            Calendar lastCalendar = Calendar.getInstance();
            lastCalendar.set(lastDay.year, lastDay.month, lastDay.day);
            checkOut.setText("离店时间:" + getYYYY_MM_dd(new Date(lastCalendar.getTimeInMillis())));
            checkOut.setTag(lastCalendar);
        } else {
            checkOut.setText(null);
            checkOut.setTag(null);
        }
        Calendar goCalendar = (Calendar) checkIn.getTag();
        Calendar backCalendar = (Calendar) checkOut.getTag();
        if (goCalendar != null && backCalendar != null
                && goCalendar.getTimeInMillis() > backCalendar.getTimeInMillis()) {
            checkIn.setText("入住时间:" + getYYYY_MM_dd(new Date(backCalendar.getTimeInMillis())));
            checkIn.setTag(backCalendar);
            selectedDays.setFirst(lastDay);
            selectedDays.setLast(firstDay);
            checkOut.setText("退房时间:" + getYYYY_MM_dd(new Date(goCalendar.getTimeInMillis())));
            checkOut.setTag(goCalendar);
        }
        if (goCalendar != null && backCalendar != null) {
            if (onSelectedTimeListener != null && !isConfiguration) {
                onSelectedTimeListener.onTimeSelected(selectedDays.getFirst(), selectedDays.getLast());
            }
            isConfiguration = false;
        }
    }

    public String getYYYY_MM_dd(Date date) {
        return new SimpleDateFormat("MM月dd日", Locale.getDefault()).format(date);
    }

    public void setCheckedDays(SimpleMonthAdapter.CalendarDay firstDay, SimpleMonthAdapter.CalendarDay lastDay) {
        isConfiguration = true;
        dayPicker.getSelectedDays().setFirst(firstDay);
        dayPicker.getSelectedDays().setLast(lastDay);
        dayPicker.setUpAdapter();
        onDateRangeSelected(dayPicker.getSelectedDays());
    }

    public interface OnSelectedTimeListener {
        void onTimeSelected(SimpleMonthAdapter.CalendarDay firstDay, SimpleMonthAdapter.CalendarDay lastDay);
    }
}
