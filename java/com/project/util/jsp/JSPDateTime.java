/* Generated by Together */

package com.project.util.jsp;

import java.io.*;
import java.util.*;
import java.text.*;

import javax.servlet.http.*;

public class JSPDateTime
{
    private String mmControlName = "month";
    private String ddControlName = "day";
    private String yyControlName = "year";
    private String hourControlName = "hour";
    private String minuteControlName = "minute";
    private String meridiemControlName = "ampm";

    private String monthFormat = "MMM";
    private int hourFormat = 12;
    private int minuteInterval = 5;

    private String cssClass = "";
    private String mdDelim = "";
    private String dyDelim = ",";

    private String dtDelim = "";
    private String hmDelim = ":";


    private int startYear	= getIntYear(new Date());
    private int endYear = getIntYear(new Date()) + 1;

    private int mmValue = 0;
    private int ddValue = 0;
    private int yyValue = 0;
    private int hourValue = 0;
    private int minuteValue = 0;
    private String ampmValue = "am";


    public JSPDateTime() { }

    public JSPDateTime(String mmCtrlNm, String ddCtrlNm, String yyCtrlNm, String mnFormat, int startDt, int endDt) {
	    mmControlName = mmCtrlNm;
	    ddControlName = ddCtrlNm;
	    yyControlName = yyCtrlNm;
	    monthFormat = mnFormat;
	    startYear	= startDt;
	    endYear = endDt;
    }

    public JSPDateTime(String mmCtrlNm, String ddCtrlNm, String yyCtrlNm, String hrCtrlNm, String mnCtrlNm) {
	    mmControlName = mmCtrlNm;
	    ddControlName = ddCtrlNm;
	    yyControlName = yyCtrlNm;
        hourControlName = hrCtrlNm;
        minuteControlName = mnCtrlNm;
    }

    public JSPDateTime(String mnFormat, int startDt, int endDt) {
	    monthFormat = mnFormat;
	    startYear	= startDt;
	    endYear = endDt;
    }

    public JSPDateTime(int startDt, int endDt) {
	    startYear	= startDt;
	    endYear = endDt;
    }

    public JSPDateTime(String mnFormat) {
		monthFormat = mnFormat;
    }

    public JSPDateTime(Date relatif, int mailstone, int range) {
    	int yy = getIntYear(relatif);
		if(mailstone >= 0) {
        	startYear = yy + mailstone;
            endYear = startYear + range;
        }else {
            endYear = yy + mailstone;
            startYear = endYear - range;
        }
    }


    public String draw() {
		String htmlSelect = "<select name=\"" + mmControlName + "\">\n";
        int monthFormatLength = monthFormat.length();
        String s = "";

        // month combobox
        switch(monthFormatLength) {
            case 2 : // number format
		        for(int i = 1; i <= 12; i++) {
                    s = (i == mmValue) ? "selected" :  "";
		         	htmlSelect = htmlSelect + "\t<option value=\"" + i + "\"" + s + " >" + zeroFormat(i) + "</option>\n";
		        }
		        htmlSelect = htmlSelect + "</select>\n";
                break;

        	case 4: // long name January, Febuary, ...
		        for(int i = 1; i <= 12; i++) {
            		s = (i == mmValue) ? "selected" :  "";
		         	htmlSelect = htmlSelect + "\t<option value=\"" + i + "\""+ s +">" + getLongMonthName(i) + "</option>\n";
		        }
		        htmlSelect = htmlSelect + "</select>\n";
                break;

            // case 3 or others, as default - short name, Jan, Feb, ...
        	default :
		        for(int i = 1; i <= 12; i++) {
            		s = (i == mmValue) ? "selected" :  "";
		         	htmlSelect = htmlSelect + "\t<option value=\"" + i + "\""+ s +">" + getShortMonthName(i) + "</option>\n";
		        }
		        htmlSelect = htmlSelect + "</select>" + mdDelim + "\n";
                break;
        } //end switch

        // day combobox
        htmlSelect = htmlSelect + "\n<select name=\"" + ddControlName + "\">\n";
        for(int i = 1; i <= 31; i++) {
            s = (i == ddValue) ? "selected" :  "";
         	htmlSelect = htmlSelect + "\t<option value=\"" + i + "\""+ s +">" + zeroFormat(i) + "</option>\n";
        }
        htmlSelect = htmlSelect + "</select>"+ dyDelim +"\n";

        //year combobox
        htmlSelect = htmlSelect  + "\n<select name=\"" + yyControlName + "\">\n";
        for(int i = startYear; i <= endYear; i++) {
            s = (i == yyValue) ? "selected" :  "";
         	htmlSelect = htmlSelect + "\t<option value=\"" + i + "\""+ s +">" + i + "</option>\n";
        }
        htmlSelect = htmlSelect + "</select>"+ dtDelim +"\n";

        return htmlSelect;
    }



    public String drawTime() {
        String s = "";
        String htmlSelect = "";

        // hours combobox
	    htmlSelect = htmlSelect + "\n<select name=\"" + hourControlName + "\">\n";
        int ampmHourValue = 0;
        int maxHour = hourValue;
        if(hourFormat <= 12) {
            if(hourValue >= 12)
            	ampmHourValue = hourValue - 12;
            else
                ampmHourValue = hourValue;
            maxHour = hourFormat + 1;
        }else {
        	ampmHourValue = hourValue;
        }

	    for(int i = 0; i < maxHour; i++) {
	    	s = (i == ampmHourValue) ? "selected" :  "";
	        htmlSelect = htmlSelect + "\t<option value=\"" + i + "\""+ s +">" + zeroFormat(i) + "</option>\n";
	    }
	    htmlSelect = htmlSelect + "</select>"+ hmDelim +"\n";

        //minutes combobox
        htmlSelect = htmlSelect  + "\n<select name=\"" + minuteControlName + "\">\n";
        for(int i = 0; i < 60; i++) {
            s = (i == minuteValue) ? "selected" :  "";
         	htmlSelect = htmlSelect + "\t<option value=\"" + i + "\""+ s +">" + zeroFormat(i) + "</option>\n";
            i += minuteInterval - 1;
        }
        htmlSelect = htmlSelect + "</select>\n";

        //am-pm combobox
        if(hourFormat <= 12) {
	        htmlSelect = htmlSelect  + "\n<select name=\"" + meridiemControlName + "\">\n";
	        s = (ampmValue.equals("am")) ? "selected" :  "";
	        	htmlSelect = htmlSelect + "\t<option value=\"am\""+ s +">AM</option>\n";
	        s = (ampmValue.equals("pm")) ? "selected" :  "";
	        	htmlSelect = htmlSelect + "\t<option value=\"pm\""+ s +">PM</option>\n";
	        htmlSelect = htmlSelect + "</select>\n";
        }

        return htmlSelect;
    }



    public Date getDate(HttpServletRequest req) {
        Date dt = null;
        try {
	        String mm = req.getParameter(mmControlName);
	        String dd = req.getParameter(ddControlName);
	        String yy = req.getParameter(yyControlName);
	
	        dt = composeDate(yy, mm, dd);
        }catch(Exception e) {
         	System.out.println("Invalid getDate() : " + e.toString());
        }
     	return dt;
    }

    public Date getDateTime(HttpServletRequest req) {
        Date dt = null;
        try {
	        String mm = req.getParameter(mmControlName);
	        String dd = req.getParameter(ddControlName);
	        String yy = req.getParameter(yyControlName);
            String hr = (((String)req.getParameter(hourControlName))==null) ? "0" : ((String)(req.getParameter(hourControlName))) ;
	        String mi = (((String)req.getParameter(minuteControlName))==null) ? "0" : ((String)(req.getParameter(minuteControlName))) ;

            ampmValue = (((String)req.getParameter(meridiemControlName))==null) ? "" : ((String)(req.getParameter(meridiemControlName))) ;

	        dt = composeDateTime(yy, mm, dd, hr, mi);
        }catch(Exception e) {
         	System.out.println("Invalid getDateTime() : " + e.toString());
        }
     	return dt;
    }

    
    public long getDateLong(HttpServletRequest req) {
        try {
            Date dt = getDateTime(req);
            return dt.getTime();
        }catch(Exception e) {
            System.out.println("Invalid getDateLong() : " + e.toString());
        }
     	return new Date().getTime();
    }

    public String getDateLongString(HttpServletRequest req) {
     	return String.valueOf(getDateLong(req));
    }
    

    public Date composeDate(String yyyy, String mm, String dd) {
    	ddValue = Integer.parseInt(dd);
        mmValue = Integer.parseInt(mm) - 1;
        yyValue = Integer.parseInt(yyyy) - 1900;

		Date db = new Date(yyValue, mmValue, ddValue);
        return db;
    }


    public Date composeDateTime(String yyyy, String mm, String dd, String hr, String mn) {
    	ddValue = Integer.parseInt(dd);
        mmValue = Integer.parseInt(mm) - 1;
        yyValue = Integer.parseInt(yyyy) - 1900;

        if(ampmValue.equals("pm"))
        	hourValue = 12 + Integer.parseInt(hr);
        else
            hourValue = Integer.parseInt(hr);

        minuteValue = Integer.parseInt(mn);

		Date db = new Date(yyValue, mmValue, ddValue, hourValue, minuteValue);
        return db;
    }


    public void initDate(Date dt) {
	    mmValue = getIntMonth(dt);
	    ddValue = getIntDate(dt);
	    yyValue = getIntYear(dt);
    }

    public void initDateTime(Date dt) {
	    mmValue = getIntMonth(dt);
	    ddValue = getIntDate(dt);
	    yyValue = getIntYear(dt);
        hourValue = dt.getHours();
        minuteValue = dt.getMinutes();
    }

    public void initDate(String yyyy, String mm, String dd) {
        Date dt = composeDate(yyyy, mm, dd);
        initDate(dt);
    }

    public void initDateTime(String yyyy, String mm, String dd, String hr, String mn) {
        Date dt = composeDateTime(yyyy, mm, dd, hr, mn);
        initDateTime(dt);
    }

    public void setCssClass(String s) { cssClass = s;   }
    public void setMontDelim(String s) { mdDelim = s;  }
    public void setDayDelim(String s) {	dyDelim = s; }

    public void setMonthFormat(String s) {	monthFormat = s; }
    public void setHourFormat(int i) {	hourFormat = i; }
    public void setMinuteInterval(int i) { minuteInterval = i; }

    public void setControlName(String mmCtrlNm, String ddCtrlNm, String yyCtrlNm, String hrCtrlNm, String mnCtrlNm) {
	    mmControlName = mmCtrlNm;
	    ddControlName = ddCtrlNm;
	    yyControlName = yyCtrlNm;
        hourControlName = hrCtrlNm;
        minuteControlName = mnCtrlNm;
    }


    public int getIntDate(Date dt) {
        return dt.getDate();
    }

    public int getIntMonth(Date dt) {
        return dt.getMonth() + 1;
    }

    public int getIntYear(Date dt) {
        return dt.getYear() + 1900;
    }


    public String getStrDate(Date dt) {
        String s = String.valueOf(dt.getDate());
        return s.trim();
    }

    public String getStrMonth(Date dt) {
        String s = String.valueOf(dt.getMonth() + 1);
        return s.trim();
    }

    public String getStrYear(Date dt) {
        String s = String.valueOf(dt.getYear() + 1900);
        return s.trim();
    }


    public String getLongMonthName(int intMonth) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June",
                               "July", "August", "September", "October","November","December"};
        if(intMonth < 1 || intMonth > 12 ) {
            return "Invalid month number, use 1 to 12";
        }
        return monthNames[intMonth - 1];
    }


    public String getShortMonthName(int intMonth) {
        String strMonth = new String();
        strMonth = getLongMonthName(intMonth);
        if(strMonth.length() >= 3) {
            if(strMonth.substring(0,3).equalsIgnoreCase("Invalid")) {
                return strMonth;
            }
        }
        return strMonth.substring(0,3);
    }





    public static Date formatDate(String strDate, String inFormat) {
        Date dt = null;
        try {
            SimpleDateFormat inDF = (SimpleDateFormat)DateFormat.getDateInstance();
            inDF.applyPattern(inFormat);
            dt = inDF.parse(strDate);
        } catch(Exception e) {
            System.out.println("ERROR::" + e.toString());
        }
        return dt;
    }


    private String zeroFormat(int i) {
        String num = String.valueOf(i);
        if(i > -1 && num.length() == 1)
            num = "0" + i;
        return num;
    }

} // end of WP_ControlDate()
