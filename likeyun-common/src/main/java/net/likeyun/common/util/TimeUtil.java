package net.likeyun.common.util;

/**
 * @Description: 时间工具类
 * @Author: lfy
 * @Date: 2019/12/6 16:35
 */
public final class TimeUtil {
    private TimeUtil(){}

    /**
     * 获取当前时间戳
     *
     * @return 当前时间戳
     */
    public static Integer currentTimeStamp() {
        Long currentTimestamp = System.currentTimeMillis() / 1000;
        return currentTimestamp.intValue();
    }

    /**
     * 获取当天零点时间戳
     *
     * @return 当天零点时间戳
     */
    public static Integer currentZeroTimeStamp() {
        Long currentTimestamps = System.currentTimeMillis();
        Long oneDayTimestamps = Long.valueOf(60 * 60 * 24 * 1000);
        Long currentZeroTimestamps = (currentTimestamps - (currentTimestamps + 60 * 60 * 8 * 1000) % oneDayTimestamps) / 1000;
        return currentZeroTimestamps.intValue();
    }

    /**
     * String类型时间转当天秒数
     * 例如"08:00"转成31500
     *
     * @param time 时间
     * @return 时间秒值
     */
    public static Integer stringToSecond(String time) {
        String[] strings = time.split(":");
        int second = 0;
        for (int i = 0; i < strings.length; i++) {
            if (i == 0) {
                int hour = Integer.parseInt(strings[i]);
                second += hour * 60 * 60;
            } else if (i == 1) {
                int minute = Integer.parseInt(strings[i]);
                second += minute * 60;
            }
        }
        return second;
    }

    /**
     * 秒数转String类型时间
     *
     * @param second 时间秒值
     * @return 时间
     */
    public static String secondToString(Integer second) {
        int h = second / (60 * 60);
        int m = (second % (60 * 60)) / 60;
        StringBuilder time = new StringBuilder();

        if (h < 10 && m != 0 && m < 10) {
            time.append("0").append(h).append(":").append("0").append(m);
        } else if (h >= 10 && m != 0 && m < 10) {
            time.append(h).append(":").append("0").append(m);
        } else if (h < 10 && m != 0) {
            time.append("0").append(h).append(":").append(m);
        } else if (h >= 10 && m != 0) {
            time.append(h).append(":").append(m);
        } else if (h < 10 && m == 0) {
            time.append("0").append(h).append(":").append(m).append("0");
        } else if (h >= 10 && m == 0) {
            time.append(h).append(":").append(m).append("0");
        }

        return time.toString();
    }
}
