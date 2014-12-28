/*
 * 日期类Date、Calendar
 */
package com.ytf.tool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * @author wkupaochuan 
 * @time Jun 14, 2012 
 * @version V 1.0 
 *  
 * 实现的接口如下: 
 * 1---根据开始时间和间隔天数计算结束时间 Date calculateEndDate(Date sDate, int days) 
 * 2---根据开始时间、结束时间、间隔类型（年、月、日）计算间隔时间 int calInterval(Date sDate, Date eDate, String type) 
 * 3---输出日历实例（当前时间）的各个字段值 void printFields(Calendar cNow) 
 * 4---判定某个年份是否是闰年 boolean isLeapYear(int year) 
 * 5---比较两个日期的大小 int compareDate(Date sDate, Date eDate) 
 */ 
public class calDate {
	/** 
     * 根据起始日期和间隔时间计算结束日期 
     *  
     * @param sDate开始时间 
     *  
     * @param days间隔时间 
     *  
     * @return 结束时间 
     * */  
    public static Date calculateEndDate(Date sDate, int days)  
    {  
        //将开始时间赋给日历实例  
        Calendar sCalendar = Calendar.getInstance();  
        sCalendar.setTime(sDate);  
        //计算结束时间  
        sCalendar.add(Calendar.DATE, days);  
        //返回Date类型结束时间  
        return sCalendar.getTime();  
    } 
    /** 
     * 计算两个日期的时间间隔 
     *  
     * @param sDate开始时间 
     *  
     * @param eDate结束时间 
     *  
     * @param type间隔类型("Y/y"--年  "M/m"--月  "D/d"--日) 
     *  
     * @return interval时间间隔 
     * */  
    public static int calInterval(Date sDate, Date eDate, String type)  
    {  
        //时间间隔，初始为0  
        int interval = 0;  
          
        /*比较两个日期的大小，如果开始日期更大，则交换两个日期*/  
        //标志两个日期是否交换过  
        boolean reversed = false;  
//        if(compareDate(sDate, eDate) > 0)  
//        {  
//            Date dTest = sDate;  
//            sDate = eDate;  
//            eDate = dTest;  
//            //修改交换标志  
//            reversed = true;  
//        }  
          
        /*将两个日期赋给日历实例，并获取年、月、日相关字段值*/  
        Calendar sCalendar = Calendar.getInstance();  
        sCalendar.setTime(sDate);  
        int sYears = sCalendar.get(Calendar.YEAR);  
        int sMonths = sCalendar.get(Calendar.MONTH);  
        int sDays = sCalendar.get(Calendar.DAY_OF_YEAR);  
          
        Calendar eCalendar = Calendar.getInstance();  
        eCalendar.setTime(eDate);  
        int eYears = eCalendar.get(Calendar.YEAR);  
        int eMonths = eCalendar.get(Calendar.MONTH);  
        int eDays = eCalendar.get(Calendar.DAY_OF_YEAR);  
          
        //年  
        if(cTrim(type).equals("Y") || cTrim(type).equals("y"))  
        {  
            interval = eYears - sYears;  
            if(eMonths < sMonths)  
            {  
                --interval;  
            }  
        }  
        //月  
        else if(cTrim(type).equals("M") || cTrim(type).equals("m"))  
        {  
            interval = 12 * (eYears - sYears);  
            interval += (eMonths - sMonths);  
        }  
        //日  
        else if(cTrim(type).equals("D") || cTrim(type).equals("d"))  
        {  
            interval = 365 * (eYears - sYears);  
            interval += (eDays - sDays);  
            //除去闰年天数  
            while(sYears < eYears)  
            {  
                if(isLeapYear(sYears))  
                {  
                    --interval;  
                }  
                ++sYears;  
            }  
        }  
        //如果开始日期更大，则返回负值  
        if(reversed)  
        {  
            interval = -interval;  
        }  
        //返回计算结果  
        return interval;  
    }
    /** 
     * 输出日历相关字段（当前日期） 
     *  
     * @param cNow当前时间 
     *  
     * @return null 
     *  
     * 各个字段值都可以用get(field)得到，也可以用set(field, value)函数修改 
     * */  
     public static void printFields(Calendar cNow)  
    {  
        //先用Date类型输出验证  
        SimpleDateFormat df = (SimpleDateFormat)DateFormat.getInstance();  
        df.applyPattern("yyyy-MM-dd  HH:mm:ss");  
        System.out.println("标准日期:" + df.format(new Date()));  
        //逐个输出相关字段值  
        System.out.print("年份:" + cNow.get(Calendar.YEAR) + "\t");  
        //月份有问题(这里的月份开始计数为0)  
        System.out.print("月份:" + cNow.get(Calendar.MONTH) + "\t");  
        System.out.print("日期:" + cNow.get(Calendar.DATE) + "\t");  
        System.out.print("小时:" + cNow.get(Calendar.HOUR) + "\t");  
        System.out.print("分钟:" + cNow.get(Calendar.MINUTE) + "\t");  
        System.out.println("秒钟:" + cNow.get(Calendar.SECOND));  
        //本年的第几天,在计算时间间隔的时候有用  
        System.out.println("一年中的天数:" + cNow.get(Calendar.DAY_OF_YEAR));  
        System.out.println("一年中的周数:" + cNow.get(Calendar.WEEK_OF_YEAR));  
        //即本月的第几周  
        System.out.println("一月中的周数:" + cNow.get(Calendar.WEEK_OF_MONTH));  
        //即一周中的第几天(这里是以周日为第一天的)  
        System.out.println("一周中的天数:" + cNow.get(Calendar.DAY_OF_WEEK));  
    } 
     /** 
      * 判定某个年份是否是闰年 
      *  
      * @param year待判定的年份 
      *  
      * @return 判定结果 
      * */  
     public static boolean isLeapYear(int year)  
     {  
         return (year%400 == 0 || (year%4 == 0 && year%100 != 0));  
     }  
       
    /** 
    *  
    * 字符串去除两头空格，如果为空，则返回""，如果不空，则返回该字符串去掉前后空格 
    *  
    * @param tStr输入字符串 
    *  
    * @return 如果为空，则返回""，如果不空，则返回该字符串去掉前后空格 
    *  
    */  
    public static String cTrim(String tStr)   
    {  
        String ttStr = "";  
        if (tStr == null)   
        {}   
        else   
        {  
            ttStr = tStr.trim();  
        }  
        return ttStr;  
    }
    /** 
     * 比较两个Date类型的日期大小 
     *  
     * @param sDate开始时间 
     *  
     * @param eDate结束时间 
     *  
     * @return result返回结果(0--相同  1--前者大  2--后者大) 
     * */  
    public static int compareDate(Date sDate, Date eDate)  
    {  
        int result = 0;  
        //将开始时间赋给日历实例  
        Calendar sC = Calendar.getInstance();  
        sC.setTime(sDate);  
        //将结束时间赋给日历实例  
        Calendar eC = Calendar.getInstance();  
        eC.setTime(eDate);  
        //比较  
        result = sC.compareTo(eC);  
        //返回结果  
        return result;  
    }  
}

