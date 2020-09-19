/* 
 * Ctrl Name  		:  CtrlPettycashPayment.java 
 * Created on 	:  [date] [time] AM/PM 
 * 
 * @author  		:  [authorName] 
 * @version  		:  [version] 
 */

/*******************************************************************
 * Class Description 	: [project description ... ] 
 * Imput Parameters 	: [input parameter ...] 
 * Output 		: [output ...] 
 *******************************************************************/

package com.project.fms.transaction;

/* java package */ 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.*;
import com.project.util.lang.*;
import com.project.system.*;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*;
import com.project.*;
import com.project.fms.transaction.*;
import com.project.fms.master.*;

public class CmdPettycashPayment extends Control implements I_Language 
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
	private PettycashPayment pettycashPayment;
	private DbPettycashPayment dbPettycashPayment;
	private JspPettycashPayment jspPettycashPayment;
	int language = LANGUAGE_DEFAULT;

	public CmdPettycashPayment(HttpServletRequest request){
		msgString = "";
		pettycashPayment = new PettycashPayment();
		try{
			dbPettycashPayment = new DbPettycashPayment(0);
		}catch(Exception e){;}
		jspPettycashPayment = new JspPettycashPayment(request, pettycashPayment);
	}

	private String getSystemMessage(int msgCode){
		switch (msgCode){
			case I_CONExceptionInfo.MULTIPLE_ID :
				this.jspPettycashPayment.addError(jspPettycashPayment.JSP_PETTYCASH_PAYMENT_ID, resultText[language][RSLT_EST_CODE_EXIST] );
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

	public PettycashPayment getPettycashPayment() { return pettycashPayment; } 

	public JspPettycashPayment getForm() { return jspPettycashPayment; }

	public String getMessage(){ return msgString; }

	public int getStart() { return start; }

	public int action(int cmd , long oidPettycashPayment){
		msgString = "";
		int excCode = I_CONExceptionInfo.NO_EXCEPTION;
		int rsCode = RSLT_OK;
		switch(cmd){
			case JSPCommand.ADD :
                                jspPettycashPayment.requestEntityObject(pettycashPayment);
                                
                                /*String where = "'"+JSPFormater.formatDate(pettycashPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                Vector v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                 */
                                
                                if(pettycashPayment.getAmount()>pettycashPayment.getAccountBalance()){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_AMOUNT, "amount over the account balance");
                                }

                                if(jspPettycashPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
				break;
                        
                        case JSPCommand.BACK :
                                jspPettycashPayment.requestEntityObject(pettycashPayment);
                                
                                /*where = "'"+JSPFormater.formatDate(pettycashPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                 */
                                
                                if(pettycashPayment.getAmount()>pettycashPayment.getAccountBalance()){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_AMOUNT, "amount over the account balance");
                                }

                                if(jspPettycashPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
				break;        

			case JSPCommand.SAVE :
				if(oidPettycashPayment != 0){
					try{
						pettycashPayment = DbPettycashPayment.fetchExc(oidPettycashPayment);
					}catch(Exception exc){
					}
				}

				jspPettycashPayment.requestEntityObject(pettycashPayment);
                                
                                /*
                                 where = "'"+JSPFormater.formatDate(pettycashPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                 */
                                
                                Coa coa = new Coa();
                                try{
                                    coa = DbCoa.fetchExc(pettycashPayment.getCoaId());
                                    
                                    System.out.println("coa.getStatus() : "+coa.getStatus());
                                    System.out.println("I_Project.ACCOUNT_LEVEL_POSTABLE : "+I_Project.ACCOUNT_LEVEL_POSTABLE);
                                    
                                    if(!coa.getStatus().equals(I_Project.ACCOUNT_LEVEL_POSTABLE)){
                                        jspPettycashPayment.addError(jspPettycashPayment.JSP_COA_ID, "postable account required");
                                    }
                                }
                                catch(Exception ex){
                                }
                                
                                if(pettycashPayment.getAmount()>pettycashPayment.getAccountBalance()){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_AMOUNT, "amount over the account balance");
                                }

				if(jspPettycashPayment.errorSize()>0) {
					msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
					return RSLT_FORM_INCOMPLETE ;
				}

				if(pettycashPayment.getOID()==0){
					try{
                                                pettycashPayment.setDate(new Date());
                                                pettycashPayment.setJournalCounter(DbPettycashPayment.getNextCounter());
                                                pettycashPayment.setJournalPrefix(DbPettycashPayment.getNumberPrefix());
                                                pettycashPayment.setJournalNumber(DbPettycashPayment.getNextNumber(pettycashPayment.getJournalCounter()));                                            
                                                pettycashPayment.setActivityStatus(I_Project.STATUS_NOT_POSTED); 
                                                
                                                if(!(DbSystemProperty.getValueByName("APPLY_ACTIVITY")).equals("Y")){
                                                    pettycashPayment.setActivityStatus(I_Project.STATUS_POSTED);
                                                }
                                            
						long oid = dbPettycashPayment.insertExc(this.pettycashPayment);
                                                
						msgString = JSPMessage.getMessage(JSPMessage.MSG_SAVED);
                                                
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
						long oid = dbPettycashPayment.updateExc(this.pettycashPayment);
                                                msgString = JSPMessage.getMessage(JSPMessage.MSG_SAVED);
					}catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					}catch (Exception exc){
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN); 
					}

				}
				break;
                                
                        case JSPCommand.SUBMIT :
                            
                            jspPettycashPayment.requestEntityObject(pettycashPayment);
                                
                                /*where = "'"+JSPFormater.formatDate(pettycashPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                 */
                                
                                if(pettycashPayment.getAmount()>pettycashPayment.getAccountBalance()){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_AMOUNT, "amount over the account balance");
                                }

                                if(jspPettycashPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                            
                            break;
                            
                        case JSPCommand.POST :
                            
                            jspPettycashPayment.requestEntityObject(pettycashPayment);
                                
                                /*where = "'"+JSPFormater.formatDate(pettycashPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                 */
                                
                                if(pettycashPayment.getAmount()>pettycashPayment.getAccountBalance()){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_AMOUNT, "amount over the account balance");
                                }

                                if(jspPettycashPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                            
                            break;         
                                
                                
			case JSPCommand.EDIT :
				if (oidPettycashPayment != 0) {
					try {
						pettycashPayment = DbPettycashPayment.fetchExc(oidPettycashPayment);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
                            
                                jspPettycashPayment.requestEntityObject(pettycashPayment);
                                
                                /*where = "'"+JSPFormater.formatDate(pettycashPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                 */
                                
                                if(pettycashPayment.getAmount()>pettycashPayment.getAccountBalance()){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_AMOUNT, "amount over the account balance");
                                }

                                if(jspPettycashPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                                
				break;

			case JSPCommand.ASK :
				/*if (oidPettycashPayment != 0) {
					try {
						pettycashPayment = DbPettycashPayment.fetchExc(oidPettycashPayment);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
                                 */
                            
                                jspPettycashPayment.requestEntityObject(pettycashPayment);
                                
                                /*where = "'"+JSPFormater.formatDate(pettycashPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                 */
                                
                                if(pettycashPayment.getAmount()>pettycashPayment.getAccountBalance()){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_AMOUNT, "amount over the account balance");
                                }

                                if(jspPettycashPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }  
                            
				break;

			case JSPCommand.DELETE :
				/*if (oidPettycashPayment != 0){
					try{
						long oid = DbPettycashPayment.deleteExc(oidPettycashPayment);
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
                                 */
                            
                                jspPettycashPayment.requestEntityObject(pettycashPayment);
                                
                                /*where = "'"+JSPFormater.formatDate(pettycashPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                 */
                                
                                if(pettycashPayment.getAmount()>pettycashPayment.getAccountBalance()){
                                    jspPettycashPayment.addError(jspPettycashPayment.JSP_AMOUNT, "amount over the account balance");
                                }

                                if(jspPettycashPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                                
				break;
                                
                         case JSPCommand.START :
				if (oidPettycashPayment != 0) {
					try {
						pettycashPayment = DbPettycashPayment.fetchExc(oidPettycashPayment);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
                                 
				break;       
                                
                         case JSPCommand.STOP :
				if (oidPettycashPayment != 0){
					try{
						long oid = DbPettycashPayment.deleteExc(oidPettycashPayment);
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
