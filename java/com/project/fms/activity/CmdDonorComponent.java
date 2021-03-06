
package com.project.fms.activity;

import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.*;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*;

public class CmdDonorComponent extends Control 
{
	public static int RSLT_OK = 0;
	public static int RSLT_UNKNOWN_ERROR = 1;
	public static int RSLT_EST_CODE_EXIST = 2;
	public static int RSLT_FORM_INCOMPLETE = 3;

	public static String[][] resultText = {
		{"Succes", "Can not process", "Estimation code exist", "Data incomplete"},//{"Berhasil", "Tidak dapat diproses", "NoPerkiraan sudah ada", "Data tidak lengkap"},
		{"Succes", "Can not process", "Estimation code exist", "Data incomplete"}
	};

	private int start;
	private String msgString;
	private DonorComponent donorComponent;
	private DbDonorComponent dbDonorComponent;
	private JspDonorComponent jspDonorComponent;
	
	public CmdDonorComponent(HttpServletRequest request){
		msgString = "";
		donorComponent = new DonorComponent();
		try{
			dbDonorComponent = new DbDonorComponent(0);
		}catch(Exception e){;}
		jspDonorComponent = new JspDonorComponent(request, donorComponent);
	}

	private String getSystemMessage(int msgCode){
                switch (msgCode){
			case I_CONExceptionInfo.MULTIPLE_ID :
				//this.jspCashCount.addError(jspCashCount.JSP_FIELD_cash_count_id, resultText[language][RSLT_EST_CODE_EXIST] );
				return resultText[1][RSLT_EST_CODE_EXIST];
			default:
				return resultText[1][RSLT_UNKNOWN_ERROR]; 
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

	public DonorComponent getDonorComponent() { return donorComponent; } 

	public JspDonorComponent getForm() { return jspDonorComponent; }

	public String getMessage(){ return msgString; }

	public int getStart() { return start; }

	public int action(int cmd , long oidDonorComponent){
		msgString = "";
		int excCode = I_CONExceptionInfo.NO_EXCEPTION;
		int rsCode = RSLT_OK;
		switch(cmd){
			case JSPCommand.ADD :
				break;

			case JSPCommand.SAVE :
				if(oidDonorComponent != 0){
					try{
						donorComponent = DbDonorComponent.fetchExc(oidDonorComponent);
					}catch(Exception exc){
					}
				}

				jspDonorComponent.requestEntityObject(donorComponent);

				if(jspDonorComponent.errorSize()>0) {
					msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
					return RSLT_FORM_INCOMPLETE ;
				}

				if(donorComponent.getOID()==0){
					try{
						long oid = dbDonorComponent.insertExc(this.donorComponent);
                                                if(oid!=0){
                                                    rsCode = RSLT_OK;
                                                    msgString = JSPMessage.getMessage(JSPMessage.MSG_SAVED);
                                                }
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
						long oid = dbDonorComponent.updateExc(this.donorComponent);
                                                 if(oid!=0){
                                                    rsCode = RSLT_OK;
                                                    msgString = JSPMessage.getMessage(JSPMessage.MSG_UPDATED);
                                                }
					}catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					}catch (Exception exc){
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN); 
					}

				}
				break;

			case JSPCommand.EDIT :
				if (oidDonorComponent != 0) {
					try {
						donorComponent = DbDonorComponent.fetchExc(oidDonorComponent);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.ASK :
				if (oidDonorComponent != 0) {
					try {
						donorComponent = DbDonorComponent.fetchExc(oidDonorComponent);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.DELETE :
				if (oidDonorComponent != 0){
					try{
						long oid = DbDonorComponent.deleteExc(oidDonorComponent);
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
