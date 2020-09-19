/* 
 * Ctrl Name  		:  CtrlBanknonpoPayment.java 
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
import com.project.util.*;
import com.project.system.*;

public class CmdBanknonpoPayment extends Control implements I_Language 
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
	private BanknonpoPayment banknonpoPayment;
	private DbBanknonpoPayment dbBanknonpoPayment;
	private JspBanknonpoPayment jspBanknonpoPayment;
	int language = LANGUAGE_DEFAULT;

	public CmdBanknonpoPayment(HttpServletRequest request){
		msgString = "";
		banknonpoPayment = new BanknonpoPayment();
		try{
			dbBanknonpoPayment = new DbBanknonpoPayment(0);
		}catch(Exception e){;}
		jspBanknonpoPayment = new JspBanknonpoPayment(request, banknonpoPayment);
	}

	private String getSystemMessage(int msgCode){
		switch (msgCode){
			case I_CONExceptionInfo.MULTIPLE_ID :
				this.jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_BANKNONPO_PAYMENT_ID, resultText[language][RSLT_EST_CODE_EXIST] );
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

	public BanknonpoPayment getBanknonpoPayment() { return banknonpoPayment; } 

	public JspBanknonpoPayment getForm() { return jspBanknonpoPayment; }

	public String getMessage(){ return msgString; }

	public int getStart() { return start; }

	public int action(int cmd , long oidBanknonpoPayment){
		msgString = "";
		int excCode = I_CONExceptionInfo.NO_EXCEPTION;
		int rsCode = RSLT_OK;
		switch(cmd){
			case JSPCommand.ADD :
                                jspBanknonpoPayment.requestEntityObject(banknonpoPayment);
                                
                                String where = "'"+JSPFormater.formatDate(banknonpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                Vector v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                if(banknonpoPayment.getAmount()>banknonpoPayment.getAccountBalance()){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_AMOUNT, "amount over the account balance");
                                }

                                if(jspBanknonpoPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
				break;
                                
                        case JSPCommand.PRINT :
                                jspBanknonpoPayment.requestEntityObject(banknonpoPayment);
                                
                                where = "'"+JSPFormater.formatDate(banknonpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                if(banknonpoPayment.getAmount()>banknonpoPayment.getAccountBalance()){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_AMOUNT, "amount over the account balance");
                                }

                                if(jspBanknonpoPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
				break;        
                        
                        case JSPCommand.BACK :
                                jspBanknonpoPayment.requestEntityObject(banknonpoPayment);
                                
                                where = "'"+JSPFormater.formatDate(banknonpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                if(banknonpoPayment.getAmount()>banknonpoPayment.getAccountBalance()){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_AMOUNT, "amount over the account balance");
                                }

                                if(jspBanknonpoPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
				break;  

			case JSPCommand.SAVE :
				if(oidBanknonpoPayment != 0){
					try{
						banknonpoPayment = DbBanknonpoPayment.fetchExc(oidBanknonpoPayment);
					}catch(Exception exc){
					}
				}

				jspBanknonpoPayment.requestEntityObject(banknonpoPayment);
                                
                                where = "'"+JSPFormater.formatDate(banknonpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                if(banknonpoPayment.getAmount()>banknonpoPayment.getAccountBalance()){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_AMOUNT, "amount over the account balance");
                                }

				if(jspBanknonpoPayment.errorSize()>0) {
					msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
					return RSLT_FORM_INCOMPLETE ;
				}

				if(banknonpoPayment.getOID()==0){
					try{
                                                banknonpoPayment.setDate(new Date());
                                                banknonpoPayment.setJournalCounter(DbBanknonpoPayment.getNextCounter(banknonpoPayment.getCoaId()));
                                                banknonpoPayment.setJournalPrefix(DbBanknonpoPayment.getNumberPrefix(banknonpoPayment.getCoaId()));
                                                banknonpoPayment.setJournalNumber(DbBanknonpoPayment.getNextNumber(banknonpoPayment.getJournalCounter(), banknonpoPayment.getCoaId()));  
                                                
                                                if(!(DbSystemProperty.getValueByName("APPLY_ACTIVITY")).equals("Y")){
                                                    banknonpoPayment.setActivityStatus(I_Project.STATUS_POSTED);
                                                }
                                                
                                                //jika advance tidak mengecek activity
                                                if(banknonpoPayment.getType()==I_Project.TYPE_INT_EMPLOYEE_ADVANCE){
                                                    banknonpoPayment.setActivityStatus(I_Project.STATUS_POSTED);
                                                }
                                                else{
                                                    banknonpoPayment.setActivityStatus(I_Project.STATUS_NOT_POSTED);
                                                }
                                            
						long oid = dbBanknonpoPayment.insertExc(this.banknonpoPayment);
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
						long oid = dbBanknonpoPayment.updateExc(this.banknonpoPayment);
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

                        case JSPCommand.SUBMIT :
                            
                            jspBanknonpoPayment.requestEntityObject(banknonpoPayment);
                                
                                where = "'"+JSPFormater.formatDate(banknonpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                if(banknonpoPayment.getAmount()>banknonpoPayment.getAccountBalance()){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_AMOUNT, "amount over the account balance");
                                }

                                if(jspBanknonpoPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                            
                            break;
                            
                        case JSPCommand.POST :
                            
                            jspBanknonpoPayment.requestEntityObject(banknonpoPayment);
                                
                                where = "'"+JSPFormater.formatDate(banknonpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                if(banknonpoPayment.getAmount()>banknonpoPayment.getAccountBalance()){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_AMOUNT, "amount over the account balance");
                                }

                                if(jspBanknonpoPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                            
                            break;             
                                
			/*case JSPCommand.EDIT :
				if (oidBanknonpoPayment != 0) {
					try {
						banknonpoPayment = DbBanknonpoPayment.fetchExc(oidBanknonpoPayment);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.ASK :
				if (oidBanknonpoPayment != 0) {
					try {
						banknonpoPayment = DbBanknonpoPayment.fetchExc(oidBanknonpoPayment);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.DELETE :
				if (oidBanknonpoPayment != 0){
					try{
						long oid = DbBanknonpoPayment.deleteExc(oidBanknonpoPayment);
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
                         */
                         case JSPCommand.EDIT :
				/*if (oidPettycashPayment != 0) {
					try {
						banknonpoPayment = DbBanknonpoPayment.fetchExc(oidPettycashPayment);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}*/
                            
                                jspBanknonpoPayment.requestEntityObject(banknonpoPayment);
                                
                                where = "'"+JSPFormater.formatDate(banknonpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                if(banknonpoPayment.getAmount()>banknonpoPayment.getAccountBalance()){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_AMOUNT, "amount over the account balance");
                                }

                                if(jspBanknonpoPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                                
				break;

			case JSPCommand.ASK :
				/*if (oidPettycashPayment != 0) {
					try {
						banknonpoPayment = DbBanknonpoPayment.fetchExc(oidPettycashPayment);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
                                 */
                            
                                jspBanknonpoPayment.requestEntityObject(banknonpoPayment);
                                
                                where = "'"+JSPFormater.formatDate(banknonpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                if(banknonpoPayment.getAmount()>banknonpoPayment.getAccountBalance()){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_AMOUNT, "amount over the account balance");
                                }

                                if(jspBanknonpoPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }  
                            
				break;

			case JSPCommand.DELETE :
				/*if (oidPettycashPayment != 0){
					try{
						long oid = DbBanknonpoPayment.deleteExc(oidPettycashPayment);
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
                            
                                jspBanknonpoPayment.requestEntityObject(banknonpoPayment);
                                
                                where = "'"+JSPFormater.formatDate(banknonpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                if(banknonpoPayment.getAmount()>banknonpoPayment.getAccountBalance()){
                                    jspBanknonpoPayment.addError(jspBanknonpoPayment.JSP_AMOUNT, "amount over the account balance");
                                }

                                if(jspBanknonpoPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                                
				break;   

			default :

		}
		return rsCode;
	}
}
