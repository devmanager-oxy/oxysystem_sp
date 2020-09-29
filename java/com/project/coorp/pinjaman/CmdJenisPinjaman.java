package com.project.coorp.pinjaman;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.project.util.*;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*;
import com.project.util.lang.*;

public class CmdJenisPinjaman extends Control implements I_Language {
 
    public static int RSLT_OK = 0;
    public static int RSLT_UNKNOWN_ERROR = 1;
    public static int RSLT_EST_CODE_EXIST = 2;
    public static int RSLT_FORM_INCOMPLETE = 3;
    public static String[][] resultText = {
        {"Berhasil", "Tidak dapat diproses", "NoPerkiraan sudah ada", "Data tidak lengkap"},
        {"Succes", "Can not process", "Estimation code exist", "Data incomplete"}};
    private int start;
    private String msgString;
    private JenisPinjaman jenisPinjaman;
    private DbJenisPinjaman pstJenisPinjaman;
    private JspJenisPinjaman jspJenisPinjaman;
    int language = LANGUAGE_DEFAULT;

    public CmdJenisPinjaman(HttpServletRequest request) {
        msgString = "";
        jenisPinjaman = new JenisPinjaman();
        try {
            pstJenisPinjaman = new DbJenisPinjaman(0);
        } catch (Exception e) {
            ;
        }
        jspJenisPinjaman = new JspJenisPinjaman(request, jenisPinjaman);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_CONExceptionInfo.MULTIPLE_ID:
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

    public JenisPinjaman getJenisPinjaman() {
        return jenisPinjaman;
    }

    public JspJenisPinjaman getForm() {
        return jspJenisPinjaman;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidJenisPinjaman) {
        msgString = "";
        int excCode = I_CONExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case JSPCommand.ADD:
                break;

            case JSPCommand.SAVE:
                if (oidJenisPinjaman != 0) {
                    try {
                        jenisPinjaman = DbJenisPinjaman.fetchExc(oidJenisPinjaman);
                    } catch (Exception exc) {
                    }
                }

                jspJenisPinjaman.requestEntityObject(jenisPinjaman);

                if (jenisPinjaman.getOID() == 0) {
                    try {
                        long oid = pstJenisPinjaman.insertExc(this.jenisPinjaman);
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
                        long oid = pstJenisPinjaman.updateExc(this.jenisPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }

                }
                break;

            case JSPCommand.SUBMIT:
                if (oidJenisPinjaman != 0) {
                    try {
                        jenisPinjaman = DbJenisPinjaman.fetchExc(oidJenisPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.POST:
                if (oidJenisPinjaman != 0) {
                    try {
                        jenisPinjaman = DbJenisPinjaman.fetchExc(oidJenisPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.START:
                if (oidJenisPinjaman != 0) {
                    try {
                        jenisPinjaman = DbJenisPinjaman.fetchExc(oidJenisPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.RESET:
                if (oidJenisPinjaman != 0) {
                    try {
                        jenisPinjaman = DbJenisPinjaman.fetchExc(oidJenisPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.EDIT:
                if (oidJenisPinjaman != 0) {
                    try {
                        jenisPinjaman = DbJenisPinjaman.fetchExc(oidJenisPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.PRINT:
                if (oidJenisPinjaman != 0) {
                    try {
                        jenisPinjaman = DbJenisPinjaman.fetchExc(oidJenisPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.UPDATE:
                if (oidJenisPinjaman != 0) {
                    try {
                        jenisPinjaman = DbJenisPinjaman.fetchExc(oidJenisPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.ASK:
                if (oidJenisPinjaman != 0) {
                    try {
                        jenisPinjaman = DbJenisPinjaman.fetchExc(oidJenisPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.DELETE:
                if (oidJenisPinjaman != 0) {
                    try {
                        long oid = DbJenisPinjaman.deleteExc(oidJenisPinjaman);
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
