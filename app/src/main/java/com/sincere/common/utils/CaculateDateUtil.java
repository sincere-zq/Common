package com.sincere.common.utils;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class CaculateDateUtil {
    /**
     * 己算天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int caculateDays(Date date1, Date date2) {

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);

        if (year1 != year2) {  //bu同年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {  //闰年
                    timeDistance += 366;
                } else {  //平年
                    timeDistance += 365;
                }
            }
            Log.e("bu同年差", (timeDistance + (day2 - day1)) + "");
            return (timeDistance + (day2 - day1));
        } else { //同一年
            Log.e("同一年差", (day2 - day1) + "");
            return day2 - day1;
        }
    }
}
