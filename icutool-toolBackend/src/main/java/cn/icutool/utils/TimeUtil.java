package cn.icutool.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Locale;


public class TimeUtil {

    /**
     * 获取本周的周一日期
     * @return LocalDate
     */
    public static LocalDate getTheStartDateOfTheWeek(){
        LocalDate today = LocalDate.now();
        // 获取本周的周一日期
        return today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    /**
     * 获取当前是今年第几周
     * @return
     */
    public static int getWeekOfYear() {
        LocalDate date = LocalDate.now();
        // 获取当前默认的区域设置
        Locale locale = Locale.getDefault();
        // 使用WeekFields来根据区域设置获取周字段
        WeekFields weekFields = WeekFields.of(locale);
        // 获取当前日期是今年的第几周
        return date.get(weekFields.weekOfYear());
    }
}
