
package com.project.fms.activity;

import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.*;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*;
import com.project.*;

public class CmdModule extends Control
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
	private Module module;
	private DbModule dbModule;
	private JspModule jspModule;
	
	public CmdModule(HttpServletRequest request){
		msgString = "";
		module = new Module();
		try{
			dbModule = new DbModule(0);
		}catch(Exception e){;}
		jspModule = new JspModule(request, module);
	}

	private String getSystemMessage(int msgCode){
		switch (msgCode){
			case I_CONExceptionInfo.MULTIPLE_ID :
				//this.frmAdjustment.addError(frmAdjustment.FRM_FIELD_ADJUSTMENT_ID, resultText[language][RSLT_EST_CODE_EXIST] );
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

	public Module getModule() { return module; } 

	public JspModule getForm() { return jspModule; }

	public String getMessage(){ return msgString; }

	public int getStart() { return start; }

	public int action(int cmd , long oidModule){
		msgString = "";
		int excCode = I_CONExceptionInfo.NO_EXCEPTION;
		int rsCode = RSLT_OK;
		switch(cmd){
			case JSPCommand.ADD :
				break;

			case JSPCommand.SAVE :
				if(oidModule != 0){
					try{
						module = DbModule.fetchExc(oidModule);
					}catch(Exception exc){
					}
				}

				jspModule.requestEntityObject(module);

				if(jspModule.errorSize()>0) {
					msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
					return RSLT_FORM_INCOMPLETE ;
				}
                                
                                if(module.getLevel().equals(I_Project.ACTIVITY_CODE_S)){
                                    module.setParentId(module.getParentIdM());
                                }
                                else if(module.getLevel().equals(I_Project.ACTIVITY_CODE_H)){
                                    module.setParentId(module.getParentIdS());
                                }
                                else if(module.getLevel().equals(I_Project.ACTIVITY_CODE_A)){
                                    module.setParentId(module.getParentIdSH());
                                    module.setPositionLevel(module.getStatusPost());
                                }
                                else if(module.getLevel().equals(I_Project.ACTIVITY_CODE_SA)){
                                    module.setParentId(module.getParentIdA());
                                    module.setPositionLevel(module.getStatusPost());
                                }
                                else if(module.getLevel().equals(I_Project.ACTIVITY_CODE_SSA)){
                                    module.setParentId(module.getParentIdSA()); 
                                    module.setPositionLevel(module.getStatusPost());
                                }

				if(module.getOID()==0){
					try{
						long oid = dbModule.insertExc(this.module);
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
						long oid = dbModule.updateExc(this.module);
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
				if (oidModule != 0) {
					try {
						module = DbModule.fetchExc(oidModule);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.ASK :
				if (oidModule != 0) {
					try {
						module = DbModule.fetchExc(oidModule);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.DELETE :
				if (oidModule != 0){
					try{
						long oid = DbModule.deleteExc(oidModule);
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
