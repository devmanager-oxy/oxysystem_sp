package com.project.ccs.postransaction.receiving;

import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.*;
import com.project.util.lang.*;
import com.project.util.jsp.*;
import com.project.main.entity.*;
import com.project.main.db.*;
import com.project.*;
import com.project.ccs.postransaction.stock.*;


public class CmdReceive extends Control implements I_Language {

    public static int RSLT_OK = 0;
    public static int RSLT_UNKNOWN_ERROR = 1;
    public static int RSLT_EST_CODE_EXIST = 2;
    public static int RSLT_FORM_INCOMPLETE = 3;
    public static String[][] resultText = {
        {"Berhasil", "Tidak dapat diproses", "NoPerkiraan sudah ada", "Data tidak lengkap"},
        {"Succes", "Can not process", "Duplicate Entry", "Data incomplete"}};
    private int start;
    private String msgString;
    private Receive receive;
    private DbReceive dbReceive;
    private JspReceive jspReceive;
    int language = LANGUAGE_DEFAULT;

    public CmdReceive(HttpServletRequest request) {
        msgString = "";
        receive = new Receive();
        try {
            dbReceive = new DbReceive(0);
        } catch (Exception e) {
            
        }
        jspReceive = new JspReceive(request, receive);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_CONExceptionInfo.MULTIPLE_ID:
                //this.jspReceive.addError(jspReceive.JSP_FIELD_receive_id, resultText[language][RSLT_EST_CODE_EXIST] );
                return resultText[language][RSLT_EST_CODE_EXIST];
            default:
                return resultText[language][RSLT_UNKNOWN_ERROR];
        }
    }

    private int getControlMsgId(int msgCode) {
        switch (msgCode) {
            case I_CONExceptionInfo.MULTIPLE_ID:
                return RSLT_EST_CODE_EXIST;
            default:
                return RSLT_UNKNOWN_ERROR;
        }
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public Receive getReceive() {
        return receive;
    }

    public JspReceive getForm() {
        return jspReceive;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidReceive) {
        msgString = "";
        int excCode = I_CONExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case JSPCommand.ADD:
                if (oidReceive != 0) {
                    try {
                        receive = DbReceive.fetchExc(oidReceive);
                    } catch (Exception exc) {
                    }
                }
                break;
            
            case JSPCommand.BACK:
                if (oidReceive != 0) {
                    try {
                        receive = DbReceive.fetchExc(oidReceive);
                    } catch (Exception exc) {
                    }
                }
                break;    

            case JSPCommand.SAVE:
                
                if (oidReceive != 0) {
                    try {
                        receive = DbReceive.fetchExc(oidReceive);                        
                    } catch (Exception exc) {
                    }
                }

                jspReceive.requestEntityObject(receive);
                
                if(receive.getStatus().equals(I_Project.DOC_STATUS_CLOSE)){
                    if(receive.getClosedReason()==null || receive.getClosedReason().length()<1){
                        jspReceive.addError(jspReceive.JSP_CLOSED_REASON, "Entry Required");
                    }
                }
                
                if(receive.getPurchaseId()!=0){
                    if(receive.getDoNumber().length()==0){
                        jspReceive.addError(jspReceive.JSP_DO_NUMBER, "Entry Required");
                    }
                    
                    if(receive.getInvoiceNumber().length()==0){
                        jspReceive.addError(jspReceive.JSP_INVOICE_NUMBER, "Entry Required");
                    }
                }
                
                if (jspReceive.errorSize() > 0) {
                    msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (receive.getOID() == 0) {
                    try {
                        
                        int ctr = DbReceive.getNextCounter();
                        receive.setCounter(ctr);
                        receive.setPrefixNumber(DbReceive.getNumberPrefix());
                        receive.setNumber(DbReceive.getNextNumber(ctr));                        
                        
                        long oid = dbReceive.insertExc(this.receive);
                        
                        //proses penambahan stock
                        //if(oid!=0 && receive.getStatus().equals(I_Project.DOC_STATUS_APPROVED)){
                        //    DbReceiveItem.proceedStock(receive);
                        //}
                        
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                        return getControlMsgId(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                        return getControlMsgId(I_CONExceptionInfo.UNKNOWN);
                    }

                } else {
                    try {
                        long oid = dbReceive.updateExc(this.receive);
                        
                        //proses penambahan stock                        
                        if(DbStock.getCount(DbStock.colNames[DbStock.COL_INCOMING_ID]+"="+receive.getOID())==0 && oid!=0 && receive.getStatus().equals(I_Project.DOC_STATUS_APPROVED)){
                            DbReceiveItem.proceedStock(receive);
                        } 
                        
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }

                }
                break;
            
            case JSPCommand.POST:
                
                long userId = 0;
                long app1Id = 0;
                long app2Id = 0;
                long app3Id = 0;
                
                if (oidReceive != 0) {
                    try {
                        receive = DbReceive.fetchExc(oidReceive);
                        
                        userId = receive.getUserId();
                        app1Id = receive.getApproval1();
                        app2Id = receive.getApproval2();
                        app3Id = receive.getApproval3();
                        
                    } catch (Exception exc) {
                    }
                }

                jspReceive.requestEntityObject(receive);
                
                //approval check ----------------
                if(receive.getStatus().equals(I_Project.DOC_STATUS_DRAFT)){
                    //approved status
                    receive.setApproval1(0);
                    //check status
                    receive.setApproval2(0);
                    //close status
                    receive.setApproval3(0);
                }
                else if(receive.getStatus().equals(I_Project.DOC_STATUS_APPROVED)){
                    //approved status
                    receive.setApproval1(receive.getUserId());
                    receive.setApproval1Date(new Date());
                    //draft status
                    receive.setUserId(userId);
                    //check status
                    receive.setApproval2(0);
                    //close status
                    receive.setApproval3(0);
                }
                else if(receive.getStatus().equals(I_Project.DOC_STATUS_CHECKED)){                                        
                    //close statusc
                    receive.setApproval2(receive.getUserId());
                    receive.setApproval2Date(new Date());
                    //draft status
                    receive.setUserId(userId);
                    //close
                    receive.setApproval3(0);
                }
                else if(receive.getStatus().equals(I_Project.DOC_STATUS_CLOSE)){                                        
                    //close status
                    receive.setApproval3(receive.getUserId());
                    receive.setApproval3Date(new Date());
                    //draft status
                    receive.setUserId(userId);
                }
                //--------------------------------
                
                if(receive.getStatus().equals(I_Project.DOC_STATUS_CLOSE)){
                    if(receive.getClosedReason()==null || receive.getClosedReason().length()<1){
                        jspReceive.addError(jspReceive.JSP_CLOSED_REASON, "Entry Required");
                    }
                }
                
                if(receive.getPurchaseId()!=0){
                    if(receive.getDoNumber().length()==0){
                        jspReceive.addError(jspReceive.JSP_DO_NUMBER, "Entry Required");
                    }
                    
                    if(receive.getInvoiceNumber().length()==0){
                        jspReceive.addError(jspReceive.JSP_INVOICE_NUMBER, "Entry Required");
                    }
                }
                
                if (jspReceive.errorSize() > 0) {
                    msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (receive.getOID() == 0) {
                    try {
                        
                        int ctr = DbReceive.getNextCounter();
                        receive.setCounter(ctr);
                        receive.setPrefixNumber(DbReceive.getNumberPrefix());
                        receive.setNumber(DbReceive.getNextNumber(ctr));
                        
                        long oid = dbReceive.insertExc(this.receive);
                        
                        //proses penambahan stock
                        if(DbStock.getCount(DbStock.colNames[DbStock.COL_INCOMING_ID]+"="+receive.getOID())==0 && oid!=0 && receive.getStatus().equals(I_Project.DOC_STATUS_APPROVED)){
                            DbReceiveItem.proceedStock(receive);
                        }
                        
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                        return getControlMsgId(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                        return getControlMsgId(I_CONExceptionInfo.UNKNOWN);
                    }

                } else {
                    try {
                        long oid = dbReceive.updateExc(this.receive);
                        
                        //proses penambahan stock
                        if(DbStock.getCount(DbStock.colNames[DbStock.COL_INCOMING_ID]+"="+receive.getOID())==0 && oid!=0 && receive.getStatus().equals(I_Project.DOC_STATUS_APPROVED)){
                            DbReceiveItem.proceedStock(receive);
                        }
                        
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }

                }
                break;    
                
            case JSPCommand.ACTIVATE:
                
                userId = 0;
                app1Id = 0;
                app2Id = 0;
                app3Id = 0;
                
                if (oidReceive != 0) {
                    try {
                        receive = DbReceive.fetchExc(oidReceive);
                        
                        userId = receive.getUserId();
                        app1Id = receive.getApproval1();
                        app2Id = receive.getApproval2();
                        app3Id = receive.getApproval3();
                        
                    } catch (Exception exc) {
                    }
                }

                jspReceive.requestEntityObject(receive);
                
                //approval check ----------------
                if(receive.getStatus().equals(I_Project.DOC_STATUS_DRAFT)){
                    //approved status
                    receive.setApproval1(0);
                    //check status
                    receive.setApproval2(0);
                    //close status
                    receive.setApproval3(0);
                }
                else if(receive.getStatus().equals(I_Project.DOC_STATUS_APPROVED)){
                    //approved status
                    receive.setApproval1(receive.getUserId());
                    receive.setApproval1Date(new Date());
                    //draft status
                    receive.setUserId(userId);
                    //check status
                    receive.setApproval2(0);
                    //close status
                    receive.setApproval3(0);
                }
                else if(receive.getStatus().equals(I_Project.DOC_STATUS_CHECKED)){                                        
                    //close statusc
                    receive.setApproval2(receive.getUserId());
                    receive.setApproval2Date(new Date());
                    //draft status
                    receive.setUserId(userId);
                    //close
                    receive.setApproval3(0);
                }
                else if(receive.getStatus().equals(I_Project.DOC_STATUS_CLOSE)){                                        
                    //close status
                    receive.setApproval3(receive.getUserId());
                    receive.setApproval3Date(new Date());
                    //draft status
                    receive.setUserId(userId);
                }
                //--------------------------------
                
                if(receive.getStatus().equals(I_Project.DOC_STATUS_CLOSE)){
                    if(receive.getClosedReason()==null || receive.getClosedReason().length()<1){
                        jspReceive.addError(jspReceive.JSP_CLOSED_REASON, "Entry Required");
                    }
                }
                
                if(receive.getPurchaseId()!=0){
                    if(receive.getDoNumber().length()==0){
                        jspReceive.addError(jspReceive.JSP_DO_NUMBER, "Entry Required");
                    }
                    
                    if(receive.getInvoiceNumber().length()==0){
                        jspReceive.addError(jspReceive.JSP_INVOICE_NUMBER, "Entry Required");
                    }
                }
                
                if (jspReceive.errorSize() > 0) {
                    msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (receive.getOID() == 0) {
                    try {
                        
                        int ctr = DbReceive.getNextCounter();
                        receive.setCounter(ctr);
                        receive.setPrefixNumber(DbReceive.getNumberPrefix());
                        receive.setNumber(DbReceive.getNextNumber(ctr));
                        
                        long oid = dbReceive.insertExc(this.receive);
                        
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                        return getControlMsgId(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                        return getControlMsgId(I_CONExceptionInfo.UNKNOWN);
                    }

                } else {
                    try {
                        long oid = dbReceive.updateExc(this.receive);
                        
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }

                }
                break;    
                

            case JSPCommand.EDIT:
                if (oidReceive != 0) {
                    try {
                        receive = DbReceive.fetchExc(oidReceive);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;
            
            //hanya untuk loading    
            case JSPCommand.ASK:
                if (oidReceive != 0) {
                    try {
                        receive = DbReceive.fetchExc(oidReceive);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break; 
            
            //hanya untuk loading
            case JSPCommand.DELETE:
                if (oidReceive != 0) {
                    try {
                        receive = DbReceive.fetchExc(oidReceive);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;     
            
            case JSPCommand.LOAD:
                if (oidReceive != 0) {
                    try {
                        receive = DbReceive.fetchExc(oidReceive);
                        
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                
                jspReceive.requestEntityObject(receive);
                
                int count = DbReceiveItem.getCount(DbReceiveItem.colNames[DbReceiveItem.COL_RECEIVE_ID]+"="+receive.getOID());
                
                if (oidReceive != 0) {
                    DbReceive.validateReceiveItem(receive); 
                    //setelah diupdate- save purchse
                    try{
                        DbReceive.updateExc(receive);
                    }
                    catch(Exception e){
                        
                    }
                    //update total amount
                    DbReceive.fixGrandTotalAmount(oidReceive);
                }
                        
                break;    

            case JSPCommand.SUBMIT:
                if (oidReceive != 0) {
                    try {
                        receive = DbReceive.fetchExc(oidReceive);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;
                

            case JSPCommand.CONFIRM:
                if (oidReceive != 0) {
                    try {
                        DbReceiveItem.deleteAllItem(oidReceive);
                        long oid = DbReceive.deleteExc(oidReceive);
                        if (oid != 0) {
                            msgString = JSPMessage.getMessage(JSPMessage.MSG_DELETED);
                            excCode = RSLT_OK;
                        } else {
                            msgString = JSPMessage.getMessage(JSPMessage.ERR_DELETED);
                            excCode = RSLT_FORM_INCOMPLETE;
                        }
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            default:

        }
        return rsCode;
    }
}
