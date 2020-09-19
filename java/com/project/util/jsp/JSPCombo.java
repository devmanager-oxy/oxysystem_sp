/* Generated by Together */

package com.project.util.jsp;

import java.io.*;
import java.util.*;
import java.text.*;
import javax.servlet.http.*;

import com.project.util.*;

public class JSPCombo
    {
    /**
     *	@param 	String name, combobox control name
     *  @param 	String blankSel, string that displayed when no value selected
     *  @param 	int selectVal, value merked with "select" option in html
     *  @param 	int stVal, start value
     *	@param 	int endVal, end value
     * 	@param 	int interval, interval value
     *  @param  String attTag : atttribute in the start tag like event handlers etc.
     *	@return String, html (select object) string
     */
    public static String draw(String name, String blankSel, int selectVal, int stVal, int endVal, int interval, String attTag)
        {
        if (name == null) name = new String("cbbox");
        if (stVal > endVal)
            {
            stVal = 0; endVal = 1;
            }
        if ((interval >= (endVal - stVal)) || interval <= 0)
            interval = 1;
        String htmlSelect = "<select name=\"" + name + "\"" + attTag + " >\n";
        String s = "";
        if (blankSel != null)
            htmlSelect = htmlSelect + "\t<option value=\"\" selected >" + blankSel + "</option>\n";
        for (int i = stVal; i <= endVal; i++)
            {
            s = (i == selectVal) ? "selected" : "";
            htmlSelect = htmlSelect + "\t<option value=\"" + i + "\"" + s + ">" + zeroFormat(i) + "</option>\n";
            i += interval - 1;
            }
        htmlSelect = htmlSelect + "</select>\n";
        return htmlSelect;
        }



    public static String draw(String name, String blankSel, int selectVal, int stVal, int endVal, int interval,
        String attTag, String style)
            {
            return draw( name,  blankSel,  selectVal,  stVal,  endVal,  interval,
                         attTag,  style, "");
            }

    public static String draw(String name, String blankSel, int selectVal, int stVal, int endVal, int interval,
        String attTag, String style, String attribute)
            {
            if (name == null) name = new String("cbbox");
            if (stVal > endVal)
                {
                stVal = 0; endVal = 1;
                }
            if ((interval >= (endVal - stVal)) || interval <= 0)
                interval = 1;
            String htmlSelect = "<select name=\"" + name + "\"" + attTag + " class=\"" + style + "\" "+attribute+">\n";
            String s = "";
            if (blankSel != null)
                htmlSelect = htmlSelect + "\t<option value=\"\" selected >" + blankSel + "</option>\n";
            for (int i = stVal; i <= endVal; i++)
                {
                s = (i == selectVal) ? "selected" : "";
                htmlSelect = htmlSelect + "\t<option value=\"" + i + "\"" + s + ">" + zeroFormat(i) + "</option>\n";
                i += interval - 1;
                }
            htmlSelect = htmlSelect + "</select>\n";
            return htmlSelect;
            }

    public static String draw(String name, String blankSel, int selectVal, int stVal, int endVal, int interval)
        {
        return draw(name, blankSel, selectVal, stVal, endVal, interval, "");
        }

    public static String drawWithStyle(String name, String blankSel, int selectVal, int stVal, int endVal, int interval,String style)
        {
        return draw(name, blankSel, selectVal, stVal, endVal, interval, "",style);
        }

    public static String drawWithStyle(String name, String blankSel, int selectVal, int stVal, int endVal, int interval,String style,String attribute)
        {
        return draw(name, blankSel, selectVal, stVal, endVal, interval, "",style,attribute);
        }

    public static String draw(String name, String style, String blankSel, int selectVal, int stVal, int endVal, int interval)
        {
        return draw(name, blankSel, selectVal, stVal, endVal, interval, "", style);
        }

    public static String draw(String name, int selectVal, int stVal, int endVal)
        {
        return draw(name, null, selectVal, stVal, endVal, 1);
        }

    public static String drawWithStyle(String name, int selectVal, int stVal, int endVal, String style)
        {
            return draw(name, null, selectVal, stVal, endVal, 1, "", style);
        }

    public static String draw(String name, String blank, int selectVal, int stVal, int endVal)
        {
        return draw(name, blank, selectVal, stVal, endVal, 1);
        }

    public static String draw(String name, String blank, int selectVal, int stVal, int endVal, String style)
        {
        return draw(name, blank, selectVal, stVal, endVal, 1, style);
        }

    public static String draw(String name, String selectVal, String stVal, String endVal)
        {
        int selVal = Integer.parseInt(selectVal);
        int sVal = Integer.parseInt(stVal);
        int eVal = Integer.parseInt(endVal);
        return draw(name, null, selVal, sVal, eVal, 1);
        }

    public static String drawWithStyle(String name, String selectVal, String stVal, String endVal, String style)
        {
        int selVal = Integer.parseInt(selectVal);
        int sVal = Integer.parseInt(stVal);
        int eVal = Integer.parseInt(endVal);
        return drawWithStyle(name, null, selVal, sVal, eVal, 1,style);
        }

     public static String drawWithStyle(String name, String selectVal, String stVal, String endVal, String style,String attribute)
        {
        int selVal = Integer.parseInt(selectVal);
        int sVal = Integer.parseInt(stVal);
        int eVal = Integer.parseInt(endVal);
        return drawWithStyle(name, null, selVal, sVal, eVal, 1,style,attribute);
        }

    public static String draw(String name, String blank, String selectVal, String stVal, String endVal)
        {
        int selVal = 0;
        try
            {
            selVal = Integer.parseInt(selectVal);
            }
        catch (Exception e)
            {
            selVal = Integer.parseInt(stVal);
            }
        int sVal = Integer.parseInt(stVal);
        int eVal = Integer.parseInt(endVal);
        return draw(name, blank, selVal, sVal, eVal, 1);
        }

    public static String draw(String name, String blank, String selectVal, String stVal, String endVal, String style)
        {
        int selVal = 0;
        try
            {
            selVal = Integer.parseInt(selectVal);
            }
        catch (Exception e)
            {
            selVal = Integer.parseInt(stVal);
            }
        int sVal = Integer.parseInt(stVal);
        int eVal = Integer.parseInt(endVal);
        return draw(name, style, blank, selVal, sVal, eVal, 1);
        }

    public static String draw(String name, String blankSel, String selectVal, Hashtable data)
        {
        return draw(name, blankSel, selectVal, data, "");
        }

    public static String draw(String name, String blankSel, String selectVal, Hashtable data, String attTag)
        {
        if (name == null) name = new String("cbbox");
        String s = "";
        String htmlSelect = "<select name=\"" + name + "\" " + attTag + " >\n";
        boolean hasSel = false;
        if (blankSel != null)
            htmlSelect = htmlSelect + "\t<option value=\"\" selected >" + blankSel + "</option>\n";
        Iterator iter = data.keySet().iterator();
        while (iter.hasNext())
            {
            String key = (String)iter.next();
            String val = (String)data.get(key);
            s = (key.equals(selectVal)) ? "selected" : "";
            htmlSelect = htmlSelect + "\t<option value=\"" + key + "\"" + s + ">" + val + "</option>\n";
            }
        htmlSelect = htmlSelect + "</select>\n";
        return htmlSelect;
        }

     public static String drawWithStyle(String name, String blankSel, String selectVal, Hashtable data, String attTag, String style)
        {
        if (name == null) name = new String("cbbox");
        String s = "";
        String htmlSelect = "<select name=\"" + name + "\" " + attTag + " class=\""+style+"\">\n";
        boolean hasSel = false;
        if (blankSel != null)
            htmlSelect = htmlSelect + "\t<option value=\"\" selected >" + blankSel + "</option>\n";
        Iterator iter = data.keySet().iterator();
        while (iter.hasNext())
            {
            String key = (String)iter.next();
            String val = (String)data.get(key);
            s = (key.equals(selectVal)) ? "selected" : "";
            htmlSelect = htmlSelect + "\t<option value=\"" + key + "\"" + s + ">" + val + "</option>\n";
            }
        htmlSelect = htmlSelect + "</select>\n";
        return htmlSelect;
    }

    public static String draw(String name, String blankSel, String selectVal, Vector keys, Vector vals)
        {
            System.out.println("blankSel "+blankSel);
        return draw(name, blankSel, selectVal, keys, vals, "");
        }
//======
    public static String draw(String name, String blankSel, String selectVal, Vector keys, String inCondision, Vector vals)
        {
        return draw(name, blankSel, selectVal,keys,inCondision, vals, "");
        }

    public static String drawWithStyle(String name, String blankSel, String selectVal, Vector keys, Vector vals, String style)
        {
        return draw(name, blankSel, selectVal, keys, vals, "", style);
        }

    public static String draw(String name, String style, String blankSel, String selectVal, Vector keys, Vector vals)
        {
        return draw(name, style, blankSel, selectVal, keys, vals, "");
        }

    public static String draw(String name, String blankSel, String selectVal, Vector keys, Vector vals, String attTag)
        {
        if (name == null) name = new String("cbbox");
        String s = "";
        String htmlSelect = "<select name=\"" + name + "\" " + attTag + ">\n";
        boolean hasSel = false;

        if (blankSel != null)
            htmlSelect = htmlSelect + "\t<option value=\"\" selected >" + blankSel + "</option>\n";
        for (int i = 0; i < keys.size(); i++)
            {
            String key = (String)keys.get(i);
            String val = (String)vals.get(i);
            s = (key.equals(selectVal)) ? "selected" : "";
            htmlSelect = htmlSelect + "\t<option value=\"" + key + "\"" + s + ">" + val + "</option>\n";
            }
        htmlSelect = htmlSelect + "</select>\n";
        return htmlSelect;
        }

	//==========
    public static String draw(String name, String blankSel, String selectVal, Vector keys, String inCondition, Vector vals, String attTag)
        {
        if (name == null) name = new String("cbbox");
        String s = "";
        String htmlSelect = "<select name=\"" + name + "\" " + attTag + " "+ inCondition + ">\n";
        boolean hasSel = false;
        if (blankSel != null)
            htmlSelect = htmlSelect + "\t<option value=\"\" selected >" + blankSel + "</option>\n";
        for (int i = 0; i < keys.size(); i++)
            {
            String key = (String)keys.get(i);
            String val = (String)vals.get(i);
            s = (key.equals(selectVal)) ? "selected" : "";
            htmlSelect = htmlSelect + "\t<option value=\"" + key + "\"" + s + ">" + val + "</option>\n";
            }
        htmlSelect = htmlSelect + "</select>\n";
        return htmlSelect;
        }


      public static String draw(String name, String blankSel, String selectVal, Vector keys, Vector vals, String attTag, String style)
        {
        if (name == null) name = new String("cbbox");
        String s = "";
        String htmlSelect = "<select name=\"" + name + "\" " + attTag + " class=\""+style+"\">\n";
        boolean hasSel = false;
        if (blankSel != null)
            htmlSelect = htmlSelect + "\t<option value=\"\" selected >" + blankSel + "</option>\n";
        for (int i = 0; i < keys.size(); i++)
            {
            String key = (String)keys.get(i);
            String val = (String)vals.get(i);
            s = (key.equals(selectVal)) ? "selected" : "";
            htmlSelect = htmlSelect + "\t<option value=\"" + key + "\"" + s + ">" + val + "</option>\n";
            }
        htmlSelect = htmlSelect + "</select>\n";
        return htmlSelect;
    }


  /*  public static String draw(String name, String blankSel, String selectVal, Vector keys, Vector vals, String attTag, String style)
        {
        if (name == null) name = new String("cbbox");
        String s = "";
        String htmlSelect = "<select name=\"" + name + "\" " + attTag + " class=\""+style+"\">\n";
        boolean hasSel = false;
        if (blankSel != null)
            htmlSelect = htmlSelect + "\t<option value=\"\" selected >" + blankSel + "</option>\n";
        for (int i = 0; i < keys.size(); i++)
            {
            String key = (String)keys.get(i);
            String val = (String)vals.get(i);
            s = (key.equals(selectVal)) ? "selected" : "";
            htmlSelect = htmlSelect + "\t<option value=\"" + key + "\"" + s + ">" + val + "</option>\n";
            }
        htmlSelect = htmlSelect + "</select>\n";
        return htmlSelect;
    }
    */

    public static String draw(String name, String style, String blankSel, String selectVal, Vector keys,
        Vector vals, String attTag)
            {
            if (name == null) name = new String("cbbox");
            String s = "";
            String htmlSelect = "<select name=\"" + name + "\" " + attTag + " class=\"" + style + "\">\n";
            boolean hasSel = false;
            if (blankSel != null)
                htmlSelect = htmlSelect + "\t<option value=\"\" selected >" + blankSel + "</option>\n";
            for (int i = 0; i < keys.size(); i++)
                {
                String key = (String)keys.get(i);
                String val = (String)vals.get(i);
                s = (key.equals(selectVal)) ? "selected" : "";
                htmlSelect = htmlSelect + "\t<option value=\"" + key + "\"" + s + ">" + val + "</option>\n";
                }
            htmlSelect = htmlSelect + "</select>\n";
            return htmlSelect;
            }

    public static String draw(String name, String blankSel, String selectVal, Vector vals)
        {
        return draw(name, blankSel, selectVal, vals, vals);
        }

    public static String drawWithStyle(String name, String blankSel, String selectVal, Vector vals, String style)
        {
        return draw(name, blankSel, selectVal, vals, vals);
        }

    public static String draw(String name, String blankSel, String selectVal, Vector vals, String style)
        {
        return draw(name, style, blankSel, selectVal, vals, vals);
        }

   /* public static String drawWithStyle(String name, String blankSel, String selectVal, Vector vals, String style)
	    {
	    return draw(name, style, blankSel, selectVal, vals, vals);
	    }
     */

    public static String draw(String name, String blankSel, String selectVal, String[] vals)
        {
        Vector vvals = new Vector();
        for (int i = 0; i < vals.length; i++)
            {
            vvals.add(vals[i]);
            }
        return draw(name, blankSel, selectVal, vvals, vvals);
        }

    public static int getInt(String name, HttpServletRequest req)
        {
        int value = 0;
        try
            {
            String val = req.getParameter(name);
            value = Integer.parseInt(val);
            }
        catch (Exception e)
            {
            }
        return value;
        }

    public static String getStr(String name, HttpServletRequest req)
        {
        String value = "";
        try
            {
            value = req.getParameter(name);
            }
        catch (Exception e)
            {
            }
        return value;
        }

    private static String zeroFormat(int i)
        {
        String num = String.valueOf(i);
        if (i > -1 && num.length() == 1)
            num = "0" + i;
        return num;
        }
    } // end of WP_ControlDate()