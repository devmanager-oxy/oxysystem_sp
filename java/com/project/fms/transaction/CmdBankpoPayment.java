/* 
 * Ctrl Name  		:  CtrlBankpoPayment.java 
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

public class CmdBankpoPayment extends Control implements I_Language 
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
	private BankpoPayment bankpoPayment;
	private DbBankpoPayment dbBankpoPayment;
	private JspBankpoPayment jspBankpoPayment;
	int language = LANGUAGE_DEFAULT;

	public CmdBankpoPayment(HttpServletRequest request){
		msgString = "";
		bankpoPayment = new BankpoPayment();
		try{
			dbBankpoPayment = new DbBankpoPayment(0);
		}catch(Exception e){;}
		jspBankpoPayment = new JspBankpoPayment(request, bankpoPayment);
	}

	private String getSystemMessage(int msgCode){
		switch (msgCode){
			case I_CONExceptionInfo.MULTIPLE_ID :
				this.jspBankpoPayment.addError(jspBankpoPayment.JSP_BANKPO_PAYMENT_ID, resultText[language][RSLT_EST_CODE_EXIST] );
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

	public BankpoPayment getBankpoPayment() { return bankpoPayment; } 

	public JspBankpoPayment getForm() { return jspBankpoPayment; }

	public String getMessage(){ return msgString; }

	public int getStart() { return start; }

	public int action(int cmd , long oidBankpoPayment){
		msgString = "";
		int excCode = I_CONExceptionInfo.NO_EXCEPTION;
		int rsCode = RSLT_OK;
		switch(cmd){
                        case JSPCommand.ADD :
                                jspBankpoPayment.requestEntityObject(bankpoPayment);
                                
                                String where = "'"+JSPFormater.formatDate(bankpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                Vector v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBankpoPayment.addError(jspBankpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                //if(bankpoPayment.getAmount()>bankpoPayment.getAccountBalance()){
                                //    jspBankpoPayment.addError(jspBankpoPayment.JSP_AMOUNT, "amount over the account balance");
                                //}

                                if(jspBankpoPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
				break;
                                
                        case JSPCommand.PRINT :
                                jspBankpoPayment.requestEntityObject(bankpoPayment);
                                
                                where = "'"+JSPFormater.formatDate(bankpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBankpoPayment.addError(jspBankpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                //if(bankpoPayment.getAmount()>bankpoPayment.getAccountBalance()){
                                //    jspBankpoPayment.addError(jspBankpoPayment.JSP_AMOUNT, "amount over the account balance");
                                //}

                                if(jspBankpoPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
				break;  
                        case JSPCommand.BACK :
                                jspBankpoPayment.requestEntityObject(bankpoPayment);
                                
                                where = "'"+JSPFormater.formatDate(bankpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBankpoPayment.addError(jspBankpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                //if(bankpoPayment.getAmount()>bankpoPayment.getAccountBalance()){
                                //    jspBankpoPayment.addError(jspBankpoPayment.JSP_AMOUNT, "amount over the account balance");
                                //}

                                if(jspBankpoPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
				break;  
                                
			case JSPCommand.SAVE :
				if(oidBankpoPayment != 0){
					try{
						bankpoPayment = DbBankpoPayment.fetchExc(oidBankpoPayment);
					}catch(Exception exc){
					}
				}

				jspBankpoPayment.requestEntityObject(bankpoPayment);

                                where = "'"+JSPFormater.formatDate(bankpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBankpoPayment.addError(jspBankpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                //if(bankpoPayment.getAmount()>bankpoPayment.getAccountBalance()){
                                //    jspBankpoPayment.addError(jspBankpoPayment.JSP_AMOUNT, "amount over the account balance");
                                //}
                                
				if(jspBankpoPayment.errorSize()>0) {
					msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
					return RSLT_FORM_INCOMPLETE ;
				}

				if(bankpoPayment.getOID()==0){
					try{
                                                bankpoPayment.setDate(new Date());                                                
                                                bankpoPayment.setStatus(I_Project.STATUS_NOT_POSTED);
                                                if(bankpoPayment.getJournalNumber()==null || bankpoPayment.getJournalNumber().length()==0){
                                                    System.out.println("\nini benar2 insert PO payment baru");
                                                    bankpoPayment.setJournalCounter(DbBankpoPayment.getNextCounter(0));
                                                    bankpoPayment.setJournalPrefix(DbBankpoPayment.getNumberPrefix(0));
                                                    bankpoPayment.setJournalNumber(DbBankpoPayment.getNextNumber(bankpoPayment.getJournalCounter(),0)); 
                                                }
                                                else{
                                                    System.out.println("\nini insert PO karena refresh payment baru");
                                                }
						long oid = dbBankpoPayment.insertExc(this.bankpoPayment);
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
						long oid = dbBankpoPayment.updateExc(this.bankpoPayment);
                                                bankpoPayment.setStatus(I_Project.STATUS_NOT_POSTED);
					}catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					}catch (Exception exc){
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN); 
					}

				}
				break;
                           
                        case JSPCommand.SUBMIT :
                                if (oidBankpoPayment != 0) {
                                    try {
                                        bankpoPayment = DbBankpoPayment.fetchExc(oidBankpoPayment);

                                        
                                        // di blok untuk posting karena masalah cek bayar bersama
                                        long oid = DbBankpoPayment.postJournal(bankpoPayment);

                                        if(true){//oid!=0){                                                   
                                            bankpoPayment.setStatus(I_Project.STATUS_POSTED);
                                            DbBankpoPayment.updateExc(bankpoPayment);
                                        }


                                    } catch (CONException dbexc){
                                            excCode = dbexc.getErrorCode();
                                            msgString = getSystemMessage(excCode);
                                    } catch (Exception exc){ 
                                            msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                                    }
                                }
				break;        
                            
			case JSPCommand.POST :
                            
                            jspBankpoPayment.requestEntityObject(bankpoPayment);
                                
                                where = "'"+JSPFormater.formatDate(bankpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBankpoPayment.addError(jspBankpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                //if(bankpoPayment.getAmount()>bankpoPayment.getAccountBalance()){
                                //    jspBankpoPayment.addError(jspBankpoPayment.JSP_AMOUNT, "amount over the account balance");
                                //}

                                if(jspBankpoPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                            
                            break;
                            
                        /*case JSPCommand.EDIT :
				if (oidBanknonpoPayment != 0) {
					try {
						bankpoPayment = DbBanknonpoPayment.fetchExc(oidBanknonpoPayment);
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
						bankpoPayment = DbBanknonpoPayment.fetchExc(oidBanknonpoPayment);
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
						bankpoPayment = DbBanknonpoPayment.fetchExc(oidPettycashPayment);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}*/
                            
                                jspBankpoPayment.requestEntityObject(bankpoPayment);
                                
                                where = "'"+JSPFormater.formatDate(bankpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBankpoPayment.addError(jspBankpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                //if(bankpoPayment.getAmount()>bankpoPayment.getAccountBalance()){
                                //    jspBankpoPayment.addError(jspBankpoPayment.JSP_AMOUNT, "amount over the account balance");
                                //}

                                if(jspBankpoPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                                
				break;

			case JSPCommand.ASK :
				/*if (oidPettycashPayment != 0) {
					try {
						bankpoPayment = DbBanknonpoPayment.fetchExc(oidPettycashPayment);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
                                 */
                            
                                jspBankpoPayment.requestEntityObject(bankpoPayment);
                                
                                where = "'"+JSPFormater.formatDate(bankpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBankpoPayment.addError(jspBankpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                //if(bankpoPayment.getAmount()>bankpoPayment.getAccountBalance()){
                                //    jspBankpoPayment.addError(jspBankpoPayment.JSP_AMOUNT, "amount over the account balance");
                                //}

                                if(jspBankpoPayment.errorSize()>0) {
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
                            
                                jspBankpoPayment.requestEntityObject(bankpoPayment);
                                
                                where = "'"+JSPFormater.formatDate(bankpoPayment.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspBankpoPayment.addError(jspBankpoPayment.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }
                                
                                //if(bankpoPayment.getAmount()>bankpoPayment.getAccountBalance()){
                                //    jspBankpoPayment.addError(jspBankpoPayment.JSP_AMOUNT, "amount over the account balance");
                                //}

                                if(jspBankpoPayment.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                                
				break;   

		}
		return rsCode;
	}
}
