package com.project.ccs.postransaction.receiving;

import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.*;
import com.project.util.lang.*;
import com.project.util.jsp.*;
import com.project.main.entity.*;
import com.project.main.db.*;
import com.project.ccs.posmaster.*;

public class CmdReturItem extends Control implements I_Language {

    public static int RSLT_OK = 0;
    public static int RSLT_UNKNOWN_ERROR = 1;
    public static int RSLT_EST_CODE_EXIST = 2;
    public static int RSLT_FORM_INCOMPLETE = 3;
    public static String[][] resultText = {
        {"Berhasil", "Tidak dapat diproses", "NoPerkiraan sudah ada", "Data tidak lengkap"},
        {"Succes", "Can not process", "Duplicate Entry", "Data incomplete"}};
    private int start;
    private String msgString;
    private ReturItem returItem;
    private DbReturItem dbReturItem;
    private JspReturItem jspReturItem;
    int language = LANGUAGE_DEFAULT;

    public CmdReturItem(HttpServletRequest request) {
        msgString = "";
        returItem = new ReturItem();
        try {
            dbReturItem = new DbReturItem(0);
        } catch (Exception e) {
            ;
        }
        jspReturItem = new JspReturItem(request, returItem);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_CONExceptionInfo.MULTIPLE_ID:
                //this.jspReturItem.addError(jspReturItem.JSP_FIELD_returItem_id, resultText[language][RSLT_EST_CODE_EXIST] );
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

    public ReturItem getReturItem() {
        return returItem;
    }

    public JspReturItem getForm() {
        return jspReturItem;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidReturItem, long oidRetur) {
        msgString = "";
        int excCode = I_CONExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case JSPCommand.ADD:
                break;

            case JSPCommand.SAVE:
                if (oidReturItem != 0) {
                    try {
                        returItem = DbReturItem.fetchExc(oidReturItem);
                    } catch (Exception exc) {
                    }
                }

                jspReturItem.requestEntityObject(returItem);
                
                returItem.setReturId(oidRetur);
                
                ItemMaster im = new ItemMaster();
                try{
                    im = DbItemMaster.fetchExc(returItem.getItemMasterId());
                    returItem.setUomId(im.getUomPurchaseId());
                }
                catch(Exception e){
                    
                }

                if (jspReturItem.errorSize() > 0) {
                    msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (returItem.getOID() == 0) {
                    try {
                        long oid = dbReturItem.insertExc(this.returItem);
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
                        long oid = dbReturItem.updateExc(this.returItem);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }

                }
                break;

            case JSPCommand.EDIT:
                if (oidReturItem != 0) {
                    try {
                        returItem = DbReturItem.fetchExc(oidReturItem);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.ASK:
                if (oidReturItem != 0) {
                    try {
                        returItem = DbReturItem.fetchExc(oidReturItem);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.DELETE:
                if (oidReturItem != 0) {
                    try {
                        long oid = DbReturItem.deleteExc(oidReturItem);
                        if (oid != 0) {
                            
                            //fixing grand total amount - karena di delete
                            DbRetur.fixGrandTotalAmount(oidRetur); 
                            
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
