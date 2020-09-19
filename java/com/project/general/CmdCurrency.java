

package com.project.general;

/* java package */ 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.*;
import com.project.main.db.*;
import com.project.util.jsp.*;
import com.project.main.entity.*;
import com.project.general.*;

public class CmdCurrency extends Control
{
	public static int RSLT_OK = 0;
	public static int RSLT_UNKNOWN_ERROR = 1;
	public static int RSLT_EST_CODE_EXIST = 2;
	public static int RSLT_FORM_INCOMPLETE = 3;

	public static String[][] resultText = {
            {"Succes", "Can not process", "Estimation code exist", "Data incomplete"},
		{"Berhasil", "Tidak dapat diproses", "NoPerkiraan sudah ada", "Data tidak lengkap"}
		
	};

	private int start;
	private String msgString;
	private Currency currency;
	private DbCurrency pstCurrency;
	private JspCurrency frmCurrency;
	int language = 0;

	public CmdCurrency(HttpServletRequest request){
		msgString = "";
		currency = new Currency();
		try{
			pstCurrency = new DbCurrency(0);
		}catch(Exception e){;}
		frmCurrency = new JspCurrency(request, currency);
	}

	private String getSystemMessage(int msgCode){
		switch (msgCode){
			case I_CONExceptionInfo.MULTIPLE_ID :
				this.frmCurrency.addError(frmCurrency.JSP_CURRENCY_ID, resultText[language][RSLT_EST_CODE_EXIST] );
				return resultText[language][RSLT_EST_CODE_EXIST];
			default:
				return resultText[language][RSLT_UNKNOWN_ERROR]; 
		}
	}

	private int getControlMsgId(int msgCode){
		switch (msgCode){
			case I_CONExceptionInfo.MULTIPLE_ID :
				return RSLT_EST_CODE_EXIST;
			default:
				return RSLT_UNKNOWN_ERROR;
		}
	}

	public int getLanguage(){ return language; }

	public void setLanguage(int language){ this.language = language; }

	public Currency getCurrency() { return currency; } 

	public JspCurrency getForm() { return frmCurrency; }

	public String getMessage(){ return msgString; }

	public int getStart() { return start; }

	public int action(int cmd , long oidCurrency){
		msgString = "";
		int excCode = I_CONExceptionInfo.NO_EXCEPTION;
		int rsCode = RSLT_OK;
		switch(cmd){
			case JSPCommand.ADD :
				break;

			case JSPCommand.SAVE :
				if(oidCurrency != 0){
					try{
						currency = DbCurrency.fetchExc(oidCurrency);
					}catch(Exception exc){
					}
				}

				frmCurrency.requestEntityObject(currency);

				if(frmCurrency.errorSize()>0) {
					msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
					return RSLT_FORM_INCOMPLETE ;
				}
                                
                                if(oidCurrency == 0){
                                    if(pstCurrency.getCount("CURRENCY_CODE='"+currency.getCurrencyCode()+"'")>0){
                                        frmCurrency.addError(frmCurrency.JSP_CURRENCY_CODE, "Duplicat entry");
                                        return RSLT_FORM_INCOMPLETE ;
                                    }
                                }
                                else{
                                    if(pstCurrency.getCount("CURRENCY_CODE='"+currency.getCurrencyCode()+"' AND CURRENCY_ID<>"+oidCurrency)>0){
                                        frmCurrency.addError(frmCurrency.JSP_CURRENCY_CODE, "Duplicat entry");
                                        return RSLT_FORM_INCOMPLETE ;
                                    }
                                }

				if(currency.getOID()==0){
					try{
						long oid = pstCurrency.insertExc(this.currency);
					}catch(CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
						return getControlMsgId(excCode);
					}catch (Exception exc){
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
						return getControlMsgId(I_CONExceptionInfo.UNKNOWN);
					}

				}else{
					try {
						long oid = pstCurrency.updateExc(this.currency);
					}catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					}catch (Exception exc){
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN); 
					}

				}
				break;

			case JSPCommand.EDIT :
				if (oidCurrency != 0) {
					try {
						currency = DbCurrency.fetchExc(oidCurrency);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.ASK :
				if (oidCurrency != 0) {
					try {
						currency = DbCurrency.fetchExc(oidCurrency);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.DELETE :
				if (oidCurrency != 0){
					try{
						long oid = DbCurrency.deleteExc(oidCurrency);
						if(oid!=0){
							msgString = JSPMessage.getMessage(JSPMessage.MSG_DELETED);
							excCode = RSLT_OK;
						}else{
							msgString = JSPMessage.getMessage(JSPMessage.ERR_DELETED);
							excCode = RSLT_FORM_INCOMPLETE;
						}
					}catch(CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					}catch(Exception exc){	
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			default :

		}
		return rsCode;
	}
}
