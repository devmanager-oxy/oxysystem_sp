/* Generated by Together */

package com.project.util;

import java.util.*;
import com.project.util.*;

public class Validator
{

    public static boolean isNull(Object param) {
    	if(param == null || param.equals("")) return true;
    	return false;
    }


    public static boolean isNumber(String strNumber) {
       for(int i = 0; i < strNumber.length(); i++) {
          if(((char)strNumber.charAt(i) < '0') || ((char)strNumber.charAt(i) > '9')) {
              return false;
          }
       }
        return true;
    }

    public static boolean isFloat(String strNumber) {
       for(int i = 0; i < strNumber.length(); i++) {
         char c = (char)strNumber.charAt(i);
          if(c < '0' || c > '9') {
              if(c == '.' || c == ',');
              else
              	return false;
          }
       }
        return true;
    }


	public static boolean isValidMailFormat(String email) {

        char validMailChar[] = {'0','1','2','3','4','5','6','7','8','9',
        					    'a','b','c','d','e','f','g','h','i','j','k','l','m',
                                'n','o','p','q','r','s','t','u','v','w','x','y','z',
                                'A','B','C','D','E','F','G','H','I','J','K','L','M',
                                'N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                                '@','-','_','.'
								};


        // I N V A L ! D email format :
        // @gmudiasa@bali-it.com, gmudiasa@bali-it.com@, gmudiasabali-it.com
        int atIndex = email.indexOf("@");
        if(atIndex < 1 || atIndex >= email.length()) {
        	return false;
        }

        if( email.startsWith("-") || email.endsWith("-") ||
            email.startsWith("_") || email.endsWith("_") ||
            email.startsWith(".") || email.endsWith(".") )
        {
         	return false;
        }

        char mailChar[] = email.toCharArray();
        int matchIdx = -1;
        for(int i = 0; i < mailChar.length; i++) {
             matchIdx = -1;
	         for(int j = 0; j < validMailChar.length; j++) {
             	if(mailChar[i] == validMailChar[j]) {
                    matchIdx = j;
                	break;
             	}
	         }
             if(matchIdx < 0) {
             	return false;
             }
        }
        if(matchIdx < 0){
        	return false;
        }else{
            return true;
        }
    }


	public static boolean isRePwdEquals(String pwd, String repwd) {
     	if(!pwd.equals(repwd)) {
            return false;
     	}
        return true;
    }


    public static Date composeDate(String yyyy, String mm, String dd) {
        int d = Integer.parseInt(dd);
        int m = Integer.parseInt(mm) - 1;
        int y = Integer.parseInt(yyyy) - 1900;

		Date db = new Date(y, m, d);
        return db;
    }


    public static Date composeDateTime(String yyyy, String mm, String dd, String hr, String mi) {
        int d = Integer.parseInt(dd);
        int m = Integer.parseInt(mm) - 1;
        int y = Integer.parseInt(yyyy) - 1900;

        int h = Integer.parseInt(hr);
        int mt = Integer.parseInt(mi);

		Date db = new Date(y, m, d, h, mt);
        return db;
    }

    public static Date composeTime( String hr, String mi) {
      return composeTime( hr, mi, "0");
    }

    public static Date composeTime( String hr, String mi, String sec) {
        try{
        int h = Integer.parseInt(hr);
        int mt = Integer.parseInt(mi);
        int sc = Integer.parseInt(sec);

        Date db = new Date();
        db.setHours(h);
        db.setMinutes(mt);
        db.setSeconds(sc);

        return db;
        }catch (Exception exc){
            return null;
        }

    }


    public static int getIntDate(Date dt) {
        return dt.getDate();
    }

    public static int getIntMonth(Date dt) {
        return dt.getMonth() + 1;
    }

    public static int getIntYear(Date dt) {
        return dt.getYear() + 1900;
    }

/*
    public static String outDate(Date dt) {
        if(dt == null) return "";
        String strDt = JSPFormater.formatDate(dt, SystemPropertyInfo.getStrValue(SystemPropertyInfo.TYPE_FORMAT, SystemPropertyInfo.FORMAT_DATE));
        return strDt;
    }

    public static String outDateTime(Date dt) {
        if(dt == null) return "";
        String format = SystemPropertyInfo.getStrValue(SystemPropertyInfo.TYPE_FORMAT, SystemPropertyInfo.FORMAT_DATE) + " hh:mm:ss";
        String strDt = JSPFormater.formatDate(dt, format);
        return strDt;
    }

    public static String outDate(String strDate, String inFrm) {
        if(strDate == null|| strDate.length() < 1) return "";

        String outFrm = SystemPropertyInfo.getStrValue(SystemPropertyInfo.TYPE_FORMAT, SystemPropertyInfo.FORMAT_DATE);
        return JSPFormater.formatDate(strDate, inFrm, outFrm);
    }
*/


    public static String getLongMonthName(int intMonth) {
     	return YearMonth.getLongEngMonthName(intMonth);
    }

    public static String getShortMonthName(int intMonth) {
    	return YearMonth.getShortEngMonthName(intMonth);
    }


    public static Vector tokenToVector(String token, String delim) {
    	Vector tokens = new Vector();
        try {
	        StringTokenizer st = new StringTokenizer(token, delim);
	        while(st.hasMoreTokens()) {
	         	tokens.add(st.nextToken());
	        }
        }catch(Exception e) {
        }

        return tokens;
    }


    public static boolean isContaint(String string, String subString) {
     	string = string.toUpperCase();
        subString = subString.toUpperCase();
        if(string.indexOf(subString) >= 0)
            return true;
        return false;
    }


   public static boolean isContainString(Vector v, String s) {
        if(v == null || s == null) return false;
    	for(int i = 0; i < v.size(); i++) {
        	if(s.equalsIgnoreCase((String)v.get(i))) return true;
    	}
    	return false;
   }

    public static boolean isEqualsDate(Date dt, Date dt2) {
        // make equals format
        if(dt == null || dt2 == null) return false;
        dt = JSPFormater.reFormatDate(dt, "yyyy MM dd");
        dt2 = JSPFormater.reFormatDate(dt2, "yyyy MM dd");

        if(dt.equals(dt2) )
        	return true;

        return false;
    }
    

    public static Vector toVector(String[] ary) {
        Vector vct = new Vector(1,1);
        for(int i = 0; i < ary.length; i++) {
            vct.add(ary[i]);
        }
        return vct;
    } 
    
    
    public static Vector toVector(String[] groups, String[][] subGroups, String nodeStr, String subNodeStr, boolean incParentVal) {
        Vector vct = new Vector(1,1);
        for(int i = 0; i < groups.length; i++) {
            
            // if incParentVal == true, node has a value
            if(incParentVal)
                vct.add(nodeStr + groups[i]);
            else 
                vct.add("");
            
            for(int j = 0; j < subGroups[i].length; j++) {
                vct.add(subNodeStr + subGroups[i][j]);
            }                        
        }
        return vct;
    }          
      

} // end of WP_InputValidator
