/* 
 * Ctrl Name  		:  CtrlGl.java 
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
import com.project.util.*;
import com.project.*;
import com.project.fms.master.*;

public class CmdGl extends Control implements I_Language 
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
	private Gl gl;
	private DbGl dbGl;
	private JspGl jspGl;
	int language = LANGUAGE_DEFAULT;

	public CmdGl(HttpServletRequest request){
		msgString = "";
		gl = new Gl();
		try{
			dbGl = new DbGl(0);
		}catch(Exception e){;}
		jspGl = new JspGl(request, gl);
	}

	private String getSystemMessage(int msgCode){
		switch (msgCode){
			case I_CONExceptionInfo.MULTIPLE_ID :
				this.jspGl.addError(jspGl.JSP_GL_ID, resultText[language][RSLT_EST_CODE_EXIST] );
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

	public Gl getGl() { return gl; } 

	public JspGl getForm() { return jspGl; }

	public String getMessage(){ return msgString; }

	public int getStart() { return start; }

	public int action(int cmd , long oidGl){
		msgString = "";
		int excCode = I_CONExceptionInfo.NO_EXCEPTION;
		int rsCode = RSLT_OK;
		switch(cmd){
			case JSPCommand.ADD :
                                jspGl.requestEntityObject(gl);
                                
                                String where = "'"+JSPFormater.formatDate(gl.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                Vector v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspGl.addError(jspGl.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }

                                if(jspGl.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                                
				break;
                                
                        case JSPCommand.BACK :
                                jspGl.requestEntityObject(gl);
                                
                                where = "'"+JSPFormater.formatDate(gl.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspGl.addError(jspGl.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }

                                if(jspGl.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                                
				break;  

			case JSPCommand.SAVE :
				if(oidGl != 0){
					try{
						gl = DbGl.fetchExc(oidGl);
					}catch(Exception exc){
					}
				}

				jspGl.requestEntityObject(gl);
                                
                                where = "'"+JSPFormater.formatDate(gl.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspGl.addError(jspGl.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }

				if(jspGl.errorSize()>0) {
					msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
					return RSLT_FORM_INCOMPLETE ;
				}

				if(gl.getOID()==0){
					try{
                                                Periode p = DbPeriode.getOpenPeriod();
                                                
                                                gl.setDate(new Date());
                                                gl.setJournalCounter(DbGl.getNextCounter());
                                                gl.setJournalPrefix(DbGl.getNumberPrefix());
                                                gl.setJournalNumber(DbGl.getNextNumber(gl.getJournalCounter())); 
                                                gl.setPeriodId(p.getOID());
                                                gl.setActivityStatus(I_Project.STATUS_NOT_POSTED);  
                                                
                                                if(!(DbSystemProperty.getValueByName("APPLY_ACTIVITY")).equals("Y")){
                                                    gl.setActivityStatus(I_Project.STATUS_POSTED);
                                                }
                                            
						long oid = dbGl.insertExc(this.gl);
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
                                                if(!(DbSystemProperty.getValueByName("APPLY_ACTIVITY")).equals("Y")){
                                                    gl.setActivityStatus(I_Project.STATUS_POSTED);
                                                }
                                            
						long oid = dbGl.updateExc(this.gl);
                                                
					}catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					}catch (Exception exc){
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN); 
					}

				}
				break;

			/*case JSPCommand.EDIT :
				if (oidGl != 0) {
					try {
						gl = DbGl.fetchExc(oidGl);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.ASK :
				if (oidGl != 0) {
					try {
						gl = DbGl.fetchExc(oidGl);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.DELETE :
				if (oidGl != 0){
					try{
						long oid = DbGl.deleteExc(oidGl);
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
                                
                         case JSPCommand.SUBMIT :
                            
                            jspGl.requestEntityObject(gl);
                            
                            where = "'"+JSPFormater.formatDate(gl.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";
                            
                            v = DbPeriode.list(0,0, where, "");
                            if(v==null || v.size()==0){
                                jspGl.addError(jspGl.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                            }
                            
                            if(jspGl.errorSize()>0) {
                                    msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                    return RSLT_FORM_INCOMPLETE ;
                            }
                            
                            break;
                            
                        case JSPCommand.POST :
                            
                            jspGl.requestEntityObject(gl);
                            
                            where = "'"+JSPFormater.formatDate(gl.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";
                            
                            v = DbPeriode.list(0,0, where, "");
                            if(v==null || v.size()==0){
                                jspGl.addError(jspGl.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                            }
                            
                            if(jspGl.errorSize()>0) {
                                    msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                    return RSLT_FORM_INCOMPLETE ;
                            }
                            
                            break;    
                        
                                
			case JSPCommand.EDIT :
				/*if (oidGl != 0) {
					try {
						gl = DbGl.fetchExc(oidGl);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
                                 */
                            
                                jspGl.requestEntityObject(gl); 
                                
                                where = "'"+JSPFormater.formatDate(gl.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspGl.addError(jspGl.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }

                                if(jspGl.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                            
				break;

			case JSPCommand.ASK :
				/*if (oidGl != 0) {
					try {
						gl = DbGl.fetchExc(oidGl);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
                                 */
                            
                                jspGl.requestEntityObject(gl); 
                                
                                where = "'"+JSPFormater.formatDate(gl.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspGl.addError(jspGl.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }

                                if(jspGl.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                                
				break;

			case JSPCommand.DELETE :
				/*if (oidGl != 0){
					try{
						long oid = DbGl.deleteExc(oidGl);
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
                            
                                jspGl.requestEntityObject(gl);  
                                
                                where = "'"+JSPFormater.formatDate(gl.getTransDate(), "yyyy-MM-dd")+"' between start_date and end_date "+
                                    " and status='"+I_Project.STATUS_PERIOD_OPEN+"'";

                                v = DbPeriode.list(0,0, where, "");
                                if(v==null || v.size()==0){
                                    jspGl.addError(jspGl.JSP_TRANS_DATE, "<br>transaction date out of open period range");
                                }

                                if(jspGl.errorSize()>0) {
                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE ;
                                }
                            
				break;       

			default :

		}
		return rsCode;
	}
}
