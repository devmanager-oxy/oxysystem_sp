
package com.project.ccs.posmaster;

import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.*;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.util.lang.*;
import com.project.ccs.posmaster.*;

public class CmdRecipe extends Control implements I_Language 
{
	public static int RSLT_OK = 0;
	public static int RSLT_UNKNOWN_ERROR = 1;
	public static int RSLT_EST_CODE_EXIST = 2;
	public static int RSLT_FORM_INCOMPLETE = 3;

	public static String[][] resultText = {
		{"Berhasil", "Tidak dapat diproses", "NoPerkiraan sudah ada", "Data tidak lengkap"},
		{"Succes", "Can not process", "Estimation code exist", "Data incomplete"}
	};

	private int start;
	private String msgString;
	private Recipe recipe;
	private DbRecipe pstRecipe;
	private JspRecipe jspRecipe;
	int language = LANGUAGE_DEFAULT;

	public CmdRecipe(HttpServletRequest request){
		msgString = "";
		recipe = new Recipe();
		try{
			pstRecipe = new DbRecipe(0);
		}catch(Exception e){;}
		jspRecipe = new JspRecipe(request, recipe);
	}

	private String getSystemMessage(int msgCode){
		switch (msgCode){
			case I_CONExceptionInfo.MULTIPLE_ID :
				//this.jspRecipe.addError(jspRecipe.JSP_FIELD_recipe_id, resultText[language][RSLT_EST_CODE_EXIST] );
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

	public Recipe getRecipe() { return recipe; } 

	public JspRecipe getForm() { return jspRecipe; }

	public String getMessage(){ return msgString; }

	public int getStart() { return start; }

	public int action(int cmd , long oidRecipe){
		msgString = "";
		int excCode = I_CONExceptionInfo.NO_EXCEPTION;
		int rsCode = RSLT_OK;
		switch(cmd){
			case JSPCommand.ADD :
				break;

			case JSPCommand.SAVE :
				if(oidRecipe != 0){
					try{
						recipe = DbRecipe.fetchExc(oidRecipe);
					}catch(Exception exc){
					}
				}

				jspRecipe.requestEntityObject(recipe);

				if(jspRecipe.errorSize()>0) {
					msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
					return RSLT_FORM_INCOMPLETE ;
				}

				if(recipe.getOID()==0){
					try{
                                            
                                                recipe.setLastUpdate(new Date());
						long oid = pstRecipe.insertExc(this.recipe);
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
                                                recipe.setLastUpdate(new Date());
						long oid = pstRecipe.updateExc(this.recipe);
					}catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
						return getControlMsgId(excCode);
					}catch (Exception exc){
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
						return getControlMsgId(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.EDIT :
				if (oidRecipe != 0) {
					try {
						recipe = DbRecipe.fetchExc(oidRecipe);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.ASK :
				if (oidRecipe != 0) {
					try {
						recipe = DbRecipe.fetchExc(oidRecipe);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.DELETE :
				if (oidRecipe != 0){
					try{
						long oid = DbRecipe.deleteExc(oidRecipe);
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
