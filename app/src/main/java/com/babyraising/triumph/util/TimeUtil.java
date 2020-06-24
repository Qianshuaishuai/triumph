package com.babyraising.triumph.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    /**
     *     * 获取两个时间段的分钟差
     * <p>
     *     *
     * <p>
     *     * @param startDate 年月日时分秒
     * <p>
     *     * @param endDate
     * <p>
     *     * @return
     * <p>
     *    
     */
    public static int getGapMinutes(String startDate, String endDate) {

        long start = 0;

        long end = 0;


        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            start = df.parse(startDate).getTime();
            end = df.parse(endDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        CLog.e("开始结束时间1", (end - start) + "");

        int minutes = (int) ((end - start) / (1000 * 60));

        return minutes;

    }

    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

}
