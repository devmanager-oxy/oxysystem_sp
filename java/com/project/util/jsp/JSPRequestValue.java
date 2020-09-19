

package com.project.util.jsp;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class JSPRequestValue {

    public static final String YR = "_yr";
    public static final String MN = "_mn";
    public static final String DY = "_dy";
    public static final String MM = "_mm";
    public static final String HH = "_hh";    
    
    public static final String CMD = "command";
    
    //added by eka,
    //secure from any injection
    private static String[] specialChar = {"+or+", "+and+", "<", ">", "'", "/*", "\"", "--", ";"};
    private static String[] specialCharReplace = {" or ", " and ", "&lt;", "&gt;", "", "", "", "", ""};
    
    
    /** Creates new FRMControl */
    public JSPRequestValue() {
    }
    

    public static String requestString(HttpServletRequest req, String paramName) {
        String sParam = (((String)req.getParameter(paramName)) == null) ? "" : ((String)(req.getParameter(paramName)));
//		System.out.println("--FRMQueryString sParam : " + sParam);
        sParam = checkValueForInjection(sParam);
//        System.out.println("--FRMQueryString sParam : " + sParam);
        return sParam;
    }

    public static String checkValueForInjection(String value){
        if(value!=null && value.length()>0){
            String specChar = "";
            int index = -1;
            boolean stop = false;
            for(int i=0; i<specialChar.length; i++){
                specChar =  specialChar[i];
                stop = false;

                //System.out.println("specChar : "+specChar);

                while(!stop){
	                index = value.indexOf(specChar);
                    //System.out.println("index : "+index);
	                //if exist
	                if(index>-1){
						value = replaceString(index, value, specChar, specialCharReplace[i]);
	                }
                    else{
                        stop = true;
                    }
                }
            }
        }

        return value;
    }

    public static String replaceString(int index, String value, String specChar, String charReplace){

        //System.out.println("--index : "+index);
        //System.out.println("--value : "+value);
        //System.out.println("--specChar : "+specChar);
        //System.out.println("--charReplace : "+charReplace);

        if(index==0){
            value = charReplace + value.substring(index+specChar.length(), value.length());
        }else{
            String st1 = "";
	        String st2 = "";
            st1 = value.substring(0, index);
            st2 = value.substring(index + specChar.length(), value.length());
            value = st1 + charReplace + st2;
            //System.out.println("--st1 : "+st1);
	        //System.out.println("--st2 : "+st2);
        }

        return value;
    }

    
    public static int requestInt(HttpServletRequest req, String paramName) {
        String sParam = (((String)req.getParameter(paramName)) == null) ? "0" : ((String)(req.getParameter(paramName)));
        try {
            int iParam = Integer.parseInt(sParam);        
            return iParam;
        }catch(Exception e) {
            return 0;
        }
    }

    public static long requestLong(HttpServletRequest req, String paramName) {
        String sParam = (((String)req.getParameter(paramName)) == null) ? "0" : ((String)(req.getParameter(paramName)));
        try {
            long lParam = Long.parseLong(sParam);        
            return lParam;
        }catch(Exception e) {
            return 0;
        }
    }

    public static float requestFloat(HttpServletRequest req, String paramName) {
        String sParam = (((String)req.getParameter(paramName)) == null) ? "0" : ((String)(req.getParameter(paramName)));
        try {
            float lParam = Float.parseFloat(sParam);
            return lParam;
        }catch(Exception e) {
            return 0;
        }
    }

    public static double requestDouble(HttpServletRequest req, String paramName) {
        String sParam = (((String)req.getParameter(paramName)) == null) ? "0" : ((String)(req.getParameter(paramName)));
        sParam = JSPHandler.deFormatStringDecimal(sParam);
        try {
            double lParam = Double.parseDouble(sParam);
            return lParam;
        }catch(Exception e) {
            return 0;
        }
    }

    public static boolean requestBoolean(HttpServletRequest req, String paramName) {
        String sParam = (((String)req.getParameter(paramName)) == null) ? "false" : ((String)(req.getParameter(paramName)));
        try {
            sParam = sParam.trim();
            
            if(sParam.equalsIgnoreCase("true") || sParam.equals("1")) {
               return true;
            }else {
                return false;
            }           
            
        }catch(Exception e) {
            return false;
        }
    }
    

    public static Date requestDate(HttpServletRequest req, String paramName) {
        try {    
            int yr = requestInt(req, paramName + YR);
            int mn = requestInt(req, paramName + MN);
            int dy = requestInt(req, paramName + DY);
            int hh = requestInt(req, paramName + HH);
            int mm = requestInt(req, paramName + MM);

            Date dt = new Date(yr - 1900, mn -1, dy, hh, mm);
            return dt;
            
        }catch(Exception e) {
            return new Date();
        }
    }


    public static long requestDateLong(HttpServletRequest req, String paramName) {
        Date dt = requestDate(req, paramName);        
        return dt.getTime();
    }

    
    public static String requestDateString(HttpServletRequest req, String paramName) {
        Date dt = requestDate(req, paramName);        
        return String.valueOf(dt.getTime());
    }
    
    
    
    public static int requestCommand(HttpServletRequest req) {
        return requestInt(req, CMD);
    }
    
    
}
