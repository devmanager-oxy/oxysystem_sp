/*
 * DataUploader.java
 *
 * Created on March 20, 2008, 5:41 PM
 */

package com.project.datasync;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*;
//import com.project.fms.master.*;
//import com.project.fms.transaction.*;
import com.project.util.*;
import com.project.system.*;
import com.project.util.encript.*;

/**
 *
 * @author  Valued Customer
 */
public class DataUploader {
    
    /** Creates a new instance of DataUploader */
    public DataUploader() {
    }
    
    public static String uplaodData(String str){
        
        System.out.println("\n---- START UPLOAD ----- \n");
        
        String message = "";
        
        try{
            
            boolean stop = false;
            int i = 0;
            StringReader sr = new StringReader(str);
            LineNumberReader ln = new LineNumberReader(sr);
            Vector strBuffer = new Vector(1,1);
            while(!stop){
                String s = ln.readLine();
                
                System.out.println("upload line : "+i);
                
                //jika string tidak null dan bukan comment dan panjangnya > 1
                if(s!=null && s.length()>0){
                    message = insertToDB(s, message);
                }					

                i = i+1;
                ln.setLineNumber(i);
                if(s==null){
                        stop = true;
                }
            }

            sr.close();
            ln.close();
        }
        catch(Exception e){
            message = "Err - exception on starting upload";
            System.out.println("Exception e : "+e.toString());
        }
        
        if(message.length()>0){
            message = "Start uploading data :\n"+ message;
        }
        else{
            message = "Upload process complete.";
        }
        
        System.out.println("\n---- END UPLOAD ----- \n");
        
        try{
            BackupHistory bh = new BackupHistory();
            bh.setDate(new Date());
            //bh.setStartDate(startDate);
            //bh.setEndDate(endDate);
            bh.setNote("Upload data");
            bh.setType(DbBackupHistory.TYPE_UPLOAD);
            
            DbBackupHistory.insertExc(bh);
        }
        catch(Exception e){
        }
        
        return message;
        
    }
    
    private static String insertToDB(String s, String message){
        
        if(s!=null && s.length()>1){
        
            //System.out.println("s : "+s);

            String enType = s.substring(s.length()-1, s.length());

            //System.out.println("enType : "+enType);

            String data = s.substring(0, s.length()-1);

            //System.out.println("data : "+data);
            
            StringTokenizer strTok = new StringTokenizer(data, "|");
            Vector content = new Vector();
            while(strTok.hasMoreTokens()){  
                String stx = strTok.nextToken();
                if(stx.equals(TextLibrary.strNull)){
                    content.add("");  
                }
                else{
                    content.add(stx);  
                }
            }

            if(enType!=null && enType.length()>0){ 

                //System.out.println("\ncontent : "+content);
                //System.out.println("\ncontent size : "+content.size()+"");

                if(content!=null && content.size()>0){                                
                    try{
                        
                        //System.out.println("in uploading ..");
                        
                        String sql = TextDecriptor.decriptText(enType,(String)content.get(0));
                        
                        sql = CONLogger.reverseToQuery(sql);
                        
                        //System.out.println(sql+"\n");
                        
                        try{
                            CONHandler.execUpdate(sql);
                            System.out.println("--- upload success ---");
                        }
                        catch(Exception ex){
                           //System.out.println("--- FAILED ---");
                           message = message + "<br><font color='green'>data already exist, ok</font>";
                           //message = message + "<br><font color='red'>Err - Can't process data, "+(String)content.get(0)+"</font>"; 
                        }

                    }
                    catch(Exception e){
                        System.out.println(e.toString());
                        message = message + "<br><font color='red'>Err - Can't parse data to object, "+(String)content.get(0)+"</font>"; 
                    }
                }
                else{
                    message = message + "<br><font color='red'>Err - Not a valid data count, "+(String)content.get(0)+"</font>";                    
                }
            }
            else{
                message = message + "<br><font color='red'>Err - Not a valid encript type, "+(String)content.get(0)+"</font>"; 
            }
        }
        
        return message;
    }
    
    
}
