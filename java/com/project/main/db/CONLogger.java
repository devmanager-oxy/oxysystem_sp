
package com.project.main.db;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import com.project.main.db.*;
import com.project.system.*;
import com.project.fms.master.*;

public class CONLogger
{
    public static int OUT_TARGET_STDIO = 0;
    public static int OUT_TARGET_FILE  = 1;
    public static int OUT_TARGET_RDBMS = 2;
    

    static final int CRITICAL_ERROR = 1;
    static final int ERROR = 2;
    static final int WARNING = 3;
    static final int INFO = 4;
    static final int TRACE = 5;
    
    public static String LOGS_STRING_DELIMETER = "^";

    private static final String KEY_DBLAYER_LOG = "dblayer_log";
    private static final String KEY_LEVEL_LOG 	= "loglevel";
    private static final String DATE_LOG_FORMAT = "dd.MM.yyyyy hh:mm:ss";


    private static CONLogger logger = null;
    private OutputStream stream;
    private PrintStream print;
    private int outputTarget = OUT_TARGET_FILE;
    
    String logfile;
    int loglevel;
    

    /*public CONLogger()
    {
        logfile = null;
        loglevel = 0;
        try
        {
            CONConfigReader xmlconfigreader = new CONConfigReader(CONHandler.CONFIG_FILE);
            logfile = xmlconfigreader.getConfigValue(KEY_DBLAYER_LOG);
            SimpleDateFormat simpledateformat = new SimpleDateFormat(".MM.yyyyy");
            String s = simpledateformat.format(new Date());
            logfile += s;
            String s1 = xmlconfigreader.getConfigValue(KEY_LEVEL_LOG);
            loglevel = (new Integer(s1)).intValue();
            stream = new FileOutputStream(logfile, true);
        }
        catch(Exception _ex) { }
        print = new PrintStream(stream);
    }*/
    
    public CONLogger(){    
    }
    
    public void insertLogs(String s, String tableName, long companyId){
        
        
        String syncTables = DbSystemProperty.getValueByName("NON_SYNCHRONIZED_TABLES");
        StringTokenizer strTok = new StringTokenizer(syncTables, ",");
        
        boolean ok = true;
        Vector temp = new Vector();
        while(strTok.hasMoreElements()){
            String str = (String)strTok.nextToken().trim();
            if(str.equalsIgnoreCase(tableName)){
                ok = false;
            }
        }
        
        if(ok){
            try{

                s = replaceString(s);
                s = replaceString(s);
                
                Periode per = DbPeriode.getOpenPeriod();

                //String sql = "insert into logs (log_id, query_string, periode_id, company_id) values ("+OIDGenerator.generateOID()+", '"+s+"',"+per.getOID()+","+companyId+")";
                //System.out.println("LOGS : "+sql);

                //CONHandler.execUpdate(sql);

            }
            catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }
    
    public String replaceString(String s){
       
        String result = "";
        if(s!=null && s.length()>0){
            
            for(int i=0; i<s.length(); i++){
                String x = ""+s.charAt(i);
                //System.out.println(x);
                if(x.equals("'") || x.equals("\"")){
                    result = result + LOGS_STRING_DELIMETER;
                    //System.out.println("result : "+result+"\n");
                }
                else{
                    result = result + x;
                }
            }
            
            //System.out.println("result : "+result+"\n");
                        
        }
        
        return result;
        
    }
    
    public static String reverseToQuery(String s){
       
        String result = "";
        if(s!=null && s.length()>0){
            
            for(int i=0; i<s.length(); i++){
                String x = ""+s.charAt(i);
                //System.out.println(x);
                if(x.equals(LOGS_STRING_DELIMETER)){
                    result = result + "\'";
                    //System.out.println("result : "+result+"\n");
                }
                else{
                    result = result + x;
                }
            }
            
            //System.out.println("result : "+result+"\n");
                        
        }
        
        return result;
        
    }

    public CONLogger(String s)
    {
        logfile = null;
        loglevel = 0;
        try
        {
            CONConfigReader xmlconfigreader = new CONConfigReader(CONHandler.CONFIG_FILE);
            logfile = xmlconfigreader.getConfigValue(KEY_DBLAYER_LOG);
            logfile = logfile.substring(0, logfile.lastIndexOf(47)) + "/" + s;
            SimpleDateFormat simpledateformat = new SimpleDateFormat(".MM.yyyyy");
            String s1 = simpledateformat.format(new Date());
            logfile += s1;
            String s2 = xmlconfigreader.getConfigValue(KEY_LEVEL_LOG);
            loglevel = (new Integer(s2)).intValue();
            stream = new FileOutputStream(logfile, true);
        }
        catch(Exception _ex) { }
        print = new PrintStream(stream);
    }

    public void close()
    {
        if(print != null)
            print.close();
    }

    public void critical_error(Exception exception)
    {
        if(loglevel < 1)
        {
            return;
        } else
        {
            SimpleDateFormat simpledateformat = new SimpleDateFormat(DATE_LOG_FORMAT);
            String s = simpledateformat.format(new Date());
            print.println(s + " CRITICAL_ERROR STACK TRACE");
            exception.printStackTrace(print);
            print.println('\0');
            return;
        }
    }

    public void critical_error(String s)
    {
        if(loglevel < 1)
        {
            return;
        } else
        {
            SimpleDateFormat simpledateformat = new SimpleDateFormat(DATE_LOG_FORMAT);
            String s1 = simpledateformat.format(new Date());
            print.println(s1 + " CRITICAL_ERROR " + s);
            return;
        }
    }

    public void error(String s)
    {
        if(loglevel < 2)
        {
            return;
        } else
        {
            SimpleDateFormat simpledateformat = new SimpleDateFormat(DATE_LOG_FORMAT);
            String s1 = simpledateformat.format(new Date());
            print.println(s1 + " ERROR " + s);
            return;
        }
    }

    public static CONLogger getLogger()
    {
        if(logger == null) {
            logger = new CONLogger();
        }
        return logger;
    }

    public void info(String s)
    {
        if(loglevel < 4)
        {
            return;
        } else
        {
            SimpleDateFormat simpledateformat = new SimpleDateFormat(DATE_LOG_FORMAT);
            String s1 = simpledateformat.format(new Date());
            print.println(s1 + " INFO " + s);
            return;
        }
    }

    public static void main(String[] args)
    {
        //System.out.println("Logger main!");
        String s = null;
        try
        {
            CONConfigReader xmlconfigreader = new CONConfigReader(CONHandler.CONFIG_FILE);
            s = xmlconfigreader.getConfigValue(KEY_DBLAYER_LOG);
        }
        catch(Exception _ex) { }
        //System.out.println(s);
    }

    public void trace(String s)
    {
        if(loglevel < 5)
        {
            return;
        } else
        {
            SimpleDateFormat simpledateformat = new SimpleDateFormat(DATE_LOG_FORMAT);
            String s1 = simpledateformat.format(new Date());
            print.println(s1 + " TRACE " + s + "\n");
            return;
        }
    }

    public void warning(String s)
    {
        if(loglevel < 3)
        {
            return;
        } else
        {
            SimpleDateFormat simpledateformat = new SimpleDateFormat(DATE_LOG_FORMAT);
            String s1 = simpledateformat.format(new Date());
            print.println(s1 + " WARNING " + s);
            return;
        }
    }

}
