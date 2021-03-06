
package com.project.fms.activity;

import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.*;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*;
import com.project.util.lang.I_Language;

public class CmdCoaCategory extends Control implements I_Language 
{
	public static int RSLT_OK = 0;
	public static int RSLT_UNKNOWN_ERROR = 1;
	public static int RSLT_EST_CODE_EXIST = 2;
	public static int RSLT_FORM_INCOMPLETE = 3;

	public static String[][] resultText = {
		{"Berhasil", "Tidak dapat diproses", "NoPerkiraan sudah ada", "Data tidak lengkap"},
		{"Succes", "Duplicate data entry", "Duplicate data entry", "Data incomplete"}
	};

	private int start;
	private String msgString;
	private CoaCategory expenseCategory;
	private DbCoaCategory pstCoaCategory;
	private JspCoaCategory jspCoaCategory;
	int language = LANGUAGE_DEFAULT;

	public CmdCoaCategory(HttpServletRequest request){
		msgString = "";
		expenseCategory = new CoaCategory();
		try{
			pstCoaCategory = new DbCoaCategory(0);
		}catch(Exception e){;}
		jspCoaCategory = new JspCoaCategory(request, expenseCategory);
	}

	private String getSystemMessage(int msgCode){
		switch (msgCode){
			case I_CONExceptionInfo.MULTIPLE_ID :
				//this.jspCoaCategory.addError(jspCoaCategory.JSP_FIELD_coa_expense_category_id, resultText[language][RSLT_EST_CODE_EXIST] );
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

	public CoaCategory getCoaCategory() { return expenseCategory; } 

	public JspCoaCategory getForm() { return jspCoaCategory; }

	public String getMessage(){ return msgString; }

	public int getStart() { return start; }

	public int action(int cmd , long oidCoaCategory, long userId, long companyId){
		msgString = "";
		int excCode = I_CONExceptionInfo.NO_EXCEPTION;
		int rsCode = RSLT_OK;
		switch(cmd){
			case JSPCommand.ADD :
				break;

			case JSPCommand.SAVE :
				if(oidCoaCategory != 0){
					try{
						expenseCategory = DbCoaCategory.fetchExc(oidCoaCategory);
					}catch(Exception exc){
					}
				}

				jspCoaCategory.requestEntityObject(expenseCategory);
                                
                                if(userId==0 || companyId==0){
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
					return RSLT_FORM_INCOMPLETE ;
                                }
                                else{
                                        expenseCategory.setUserId(userId);
                                        expenseCategory.setCompanyId(companyId);
                                }

				if(jspCoaCategory.errorSize()>0) {
					msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
					return RSLT_FORM_INCOMPLETE ;
				}

				if(expenseCategory.getOID()==0){
					try{
						long oid = pstCoaCategory.insertExc(this.expenseCategory);
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
						long oid = pstCoaCategory.updateExc(this.expenseCategory);
					}catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					}catch (Exception exc){
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN); 
					}

				}
				break;

			case JSPCommand.EDIT :
				if (oidCoaCategory != 0) {
					try {
						expenseCategory = DbCoaCategory.fetchExc(oidCoaCategory);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.ASK :
				if (oidCoaCategory != 0) {
					try {
						expenseCategory = DbCoaCategory.fetchExc(oidCoaCategory);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.DELETE :
				if (oidCoaCategory != 0){
					try{
						long oid = DbCoaCategory.deleteExc(oidCoaCategory);
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
