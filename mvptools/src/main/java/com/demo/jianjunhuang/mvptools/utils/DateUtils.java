package com.demo.jianjunhuang.mvptools.utils;

import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/5/24.
 *
 * 日期工具
 */

public class DateUtils {

  private static final String TAG = "DateUtils";

  /**
   * yyyy年MM月dd日 格式
   */
  public static final String CHINA_DATE = "yyyy年MM月dd日";

  /**
   * yyyy-MM-dd 格式
   */
  public static final String CONTECTION_BY_DASH = "yyyy-MM-dd";

  private static final String weeks[] = { "", "一", "二", "三", "四", "五", "六", "七" };

  /**
   * 获取格式化的字符串
   *
   * @param format 格式
   * @param date 日期
   * @return 格式化字符串
   */
  public static String formatDate(String format, Date date) {
    if (format == null) {
      if (date != null) {
        Log.e(TAG, "formatDate: format string is null !!");
        return date.toString();
      }
    }
    if (date == null) {
      date = new Date();
      Log.e(TAG, "formatDate: date is null");
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    return simpleDateFormat.format(date);
  }

  /**
   * 获取当前日期格式化后的字符串
   *
   * @param format 格式
   * @return 格式化的字符串
   */
  public static String formateDate(String format) {
    return formatDate(format, new Date());
  }

  /**
   * 获取与当前时间相差的天数
   *
   * @param timeStr 格式化的时间
   * @param format 时间格式
   * @return long 天数
   */
  public static long timeSub(String timeStr, String format) throws ParseException {
    if (timeStr == null || format == null) {
      throw new ParseException("you param is null , please check the timeSub method", -1);
    }
    Date timeDate;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    timeDate = simpleDateFormat.parse(timeStr);
    return timeSub(new Date(), timeDate);
  }

  /**
   * 获取两个日前相差的天数
   *
   * @param beginTimeStr 格式化的开始的时间
   * @param endTimeStr 格式化的结束的时间
   * @param format 时间格式
   * @return 相差天数
   */
  public static long timeSub(String beginTimeStr, String endTimeStr, String format)
      throws ParseException {
    if (beginTimeStr == null || endTimeStr == null || format == null) {
      throw new ParseException("you param is null , please check the timeSub method", -1);
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

    Date beginDate = simpleDateFormat.parse(beginTimeStr);
    Date endDate = simpleDateFormat.parse(endTimeStr);

    return timeSub(beginDate, endDate);
  }

  /**
   * 获取两个日前相差的天数
   *
   * @param beginDate 开始 Date
   * @param endDate 结束 Date
   * @return 相差天数
   */
  public static long timeSub(Date beginDate, Date endDate) {
    Calendar beginCal = Calendar.getInstance();
    Calendar endCal = Calendar.getInstance();

    beginCal.setTime(beginDate);
    endCal.setTime(endDate);

    return endCal.get(Calendar.DAY_OF_YEAR) - beginCal.get(Calendar.DAY_OF_YEAR) - 1;
  }

  /**
   * 获取中文星期
   * @param dateStr 格式化的时间字符串
   * @param format 时间格式
   * @return 中文星期字符串
   * @throws ParseException
   */
  public static String getChinaWeek(String dateStr, String format) throws ParseException {
    Date date = null;
    if (format == null) {
      throw new ParseException("DateUtils - getChinaWeek: format string is null",-1);
    } else {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
      date = simpleDateFormat.parse(dateStr);
    }
    return getChinaWeek(date);
  }

  /**
   * 获取中文星期
   * @param date 时间 Date
   * @return 中文星期字符串
   */
  public static String getChinaWeek(Date date) {
    if (date == null) {
      date = new Date();
      Log.e(TAG, "getChinaWeek: date is null !!");
    }
    SimpleDateFormat dateToWeek = new SimpleDateFormat("u");
    int w = Integer.parseInt(dateToWeek.format(date));
    return "星期" + weeks[w];
  }
}
