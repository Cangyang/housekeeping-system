/*
 * ������Date��Calendar
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
 * ʵ�ֵĽӿ�����: 
 * 1---���ݿ�ʼʱ��ͼ�������������ʱ�� Date calculateEndDate(Date sDate, int days) 
 * 2---���ݿ�ʼʱ�䡢����ʱ�䡢������ͣ��ꡢ�¡��գ�������ʱ�� int calInterval(Date sDate, Date eDate, String type) 
 * 3---�������ʵ������ǰʱ�䣩�ĸ����ֶ�ֵ void printFields(Calendar cNow) 
 * 4---�ж�ĳ������Ƿ������� boolean isLeapYear(int year) 
 * 5---�Ƚ��������ڵĴ�С int compareDate(Date sDate, Date eDate) 
 */ 
public class calDate {
	/** 
     * ������ʼ���ںͼ��ʱ������������ 
     *  
     * @param sDate��ʼʱ�� 
     *  
     * @param days���ʱ�� 
     *  
     * @return ����ʱ�� 
     * */  
    public static Date calculateEndDate(Date sDate, int days)  
    {  
        //����ʼʱ�丳������ʵ��  
        Calendar sCalendar = Calendar.getInstance();  
        sCalendar.setTime(sDate);  
        //�������ʱ��  
        sCalendar.add(Calendar.DATE, days);  
        //����Date���ͽ���ʱ��  
        return sCalendar.getTime();  
    } 
    /** 
     * �����������ڵ�ʱ���� 
     *  
     * @param sDate��ʼʱ�� 
     *  
     * @param eDate����ʱ�� 
     *  
     * @param type�������("Y/y"--��  "M/m"--��  "D/d"--��) 
     *  
     * @return intervalʱ���� 
     * */  
    public static int calInterval(Date sDate, Date eDate, String type)  
    {  
        //ʱ��������ʼΪ0  
        int interval = 0;  
          
        /*�Ƚ��������ڵĴ�С�������ʼ���ڸ����򽻻���������*/  
        //��־���������Ƿ񽻻���  
        boolean reversed = false;  
//        if(compareDate(sDate, eDate) > 0)  
//        {  
//            Date dTest = sDate;  
//            sDate = eDate;  
//            eDate = dTest;  
//            //�޸Ľ�����־  
//            reversed = true;  
//        }  
          
        /*���������ڸ�������ʵ��������ȡ�ꡢ�¡�������ֶ�ֵ*/  
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
          
        //��  
        if(cTrim(type).equals("Y") || cTrim(type).equals("y"))  
        {  
            interval = eYears - sYears;  
            if(eMonths < sMonths)  
            {  
                --interval;  
            }  
        }  
        //��  
        else if(cTrim(type).equals("M") || cTrim(type).equals("m"))  
        {  
            interval = 12 * (eYears - sYears);  
            interval += (eMonths - sMonths);  
        }  
        //��  
        else if(cTrim(type).equals("D") || cTrim(type).equals("d"))  
        {  
            interval = 365 * (eYears - sYears);  
            interval += (eDays - sDays);  
            //��ȥ��������  
            while(sYears < eYears)  
            {  
                if(isLeapYear(sYears))  
                {  
                    --interval;  
                }  
                ++sYears;  
            }  
        }  
        //�����ʼ���ڸ����򷵻ظ�ֵ  
        if(reversed)  
        {  
            interval = -interval;  
        }  
        //���ؼ�����  
        return interval;  
    }
    /** 
     * �����������ֶΣ���ǰ���ڣ� 
     *  
     * @param cNow��ǰʱ�� 
     *  
     * @return null 
     *  
     * �����ֶ�ֵ��������get(field)�õ���Ҳ������set(field, value)�����޸� 
     * */  
     public static void printFields(Calendar cNow)  
    {  
        //����Date���������֤  
        SimpleDateFormat df = (SimpleDateFormat)DateFormat.getInstance();  
        df.applyPattern("yyyy-MM-dd  HH:mm:ss");  
        System.out.println("��׼����:" + df.format(new Date()));  
        //����������ֶ�ֵ  
        System.out.print("���:" + cNow.get(Calendar.YEAR) + "\t");  
        //�·�������(������·ݿ�ʼ����Ϊ0)  
        System.out.print("�·�:" + cNow.get(Calendar.MONTH) + "\t");  
        System.out.print("����:" + cNow.get(Calendar.DATE) + "\t");  
        System.out.print("Сʱ:" + cNow.get(Calendar.HOUR) + "\t");  
        System.out.print("����:" + cNow.get(Calendar.MINUTE) + "\t");  
        System.out.println("����:" + cNow.get(Calendar.SECOND));  
        //����ĵڼ���,�ڼ���ʱ������ʱ������  
        System.out.println("һ���е�����:" + cNow.get(Calendar.DAY_OF_YEAR));  
        System.out.println("һ���е�����:" + cNow.get(Calendar.WEEK_OF_YEAR));  
        //�����µĵڼ���  
        System.out.println("һ���е�����:" + cNow.get(Calendar.WEEK_OF_MONTH));  
        //��һ���еĵڼ���(������������Ϊ��һ���)  
        System.out.println("һ���е�����:" + cNow.get(Calendar.DAY_OF_WEEK));  
    } 
     /** 
      * �ж�ĳ������Ƿ������� 
      *  
      * @param year���ж������ 
      *  
      * @return �ж���� 
      * */  
     public static boolean isLeapYear(int year)  
     {  
         return (year%400 == 0 || (year%4 == 0 && year%100 != 0));  
     }  
       
    /** 
    *  
    * �ַ���ȥ����ͷ�ո����Ϊ�գ��򷵻�""��������գ��򷵻ظ��ַ���ȥ��ǰ��ո� 
    *  
    * @param tStr�����ַ��� 
    *  
    * @return ���Ϊ�գ��򷵻�""��������գ��򷵻ظ��ַ���ȥ��ǰ��ո� 
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
     * �Ƚ�����Date���͵����ڴ�С 
     *  
     * @param sDate��ʼʱ�� 
     *  
     * @param eDate����ʱ�� 
     *  
     * @return result���ؽ��(0--��ͬ  1--ǰ�ߴ�  2--���ߴ�) 
     * */  
    public static int compareDate(Date sDate, Date eDate)  
    {  
        int result = 0;  
        //����ʼʱ�丳������ʵ��  
        Calendar sC = Calendar.getInstance();  
        sC.setTime(sDate);  
        //������ʱ�丳������ʵ��  
        Calendar eC = Calendar.getInstance();  
        eC.setTime(eDate);  
        //�Ƚ�  
        result = sC.compareTo(eC);  
        //���ؽ��  
        return result;  
    }  
}

