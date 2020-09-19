
package com.project.fms.master;

import java.util.*; 
import java.sql.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.*;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
//import com.project.general.*;
import com.project.fms.master.*;
import com.project.fms.transaction.*;
import com.project.I_Project;
import com.project.general.*;
import com.project.system.*;

public class CmdCoa extends Control 
{
	public static int RSLT_OK = 0;
	public static int RSLT_UNKNOWN_ERROR = 1;
	public static int RSLT_EST_CODE_EXIST = 2;
	public static int RSLT_FORM_INCOMPLETE = 3;

	public static String[][] resultText = {
		{"Succes", "Can not process", "Estimation code exist", "Data incomplete"},//{"Berhasil", "Tidak dapat diproses", "NoPerkiraan sudah ada", "Data tidak lengkap"},
		{"Succes", "Can not process duplicate entry", "Can not process duplicate entry on code or account name", "Data incomplete"}
	};

	private int start;
	private String msgString;
	private Coa coa;
	private DbCoa dbCoa;
	private JspCoa jspCoa;

	public CmdCoa(HttpServletRequest request){
		msgString = "";
		coa = new Coa();
		try{
			dbCoa = new DbCoa(0);
		}catch(Exception e){;}
		jspCoa = new JspCoa(request, coa);
	}

	private String getSystemMessage(int msgCode){
                switch (msgCode){
			case I_CONExceptionInfo.MULTIPLE_ID :
				//this.jspCoa.addError(jspCoa.JSP_CODE, resultText[1][RSLT_EST_CODE_EXIST] );
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

	public Coa getCoa() { return coa; } 

	public JspCoa getForm() { return jspCoa; }

	public String getMessage(){ return msgString; }

	public int getStart() { return start; }

	public int action(int cmd , long oidCoa, double budget, long periodId, long loginId, long companyId){
		msgString = "";
		int excCode = I_CONExceptionInfo.NO_EXCEPTION;
		int rsCode = RSLT_OK;
		switch(cmd){
			case JSPCommand.ADD :
				break;

			case JSPCommand.SAVE :
				double oldOpeningBalance = 0;
                                if(oidCoa != 0){
					try{
						coa = DbCoa.fetchExc(oidCoa);
                                                oldOpeningBalance = coa.getOpeningBalance();
					}catch(Exception exc){
					}
				}

				jspCoa.requestEntityObject(coa);
                                
                                coa.setUserId(loginId);
                                
                                if(coa.getAccountGroup().equals(I_Project.ACC_GROUP_COST_OF_SALES) || coa.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE) && !coa.getAccountGroup().equals(I_Project.ACC_GROUP_REVENUE) ||
                                   coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE) || coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_REVENUE)					
				){
                                    coa.setOpeningBalance(0);
                                }
                                    
                                if(DbSystemProperty.getValueByName("APPLY_ACTIVITY").equals("Y")){
                                    if(coa.getCoaCategoryId()==0 && coa.getCoaGroupAliasId()!=0){
                                           jspCoa.addError(jspCoa.JSP_COA_CATEGORY_ID, "Data required");                                     
                                    }

                                    if(coa.getCoaCategoryId()!=0 && coa.getCoaGroupAliasId()==0){
                                           jspCoa.addError(jspCoa.JSP_COA_GROUP_ALIAS_ID, "Data required");                                     
                                    }
                                }

                                //return jika sudah ada companynya.
                                /*
				if(companyId==0) {					
                                        msgString = "No company data available, please relogin again.";
					return RSLT_FORM_INCOMPLETE ;                                        
				}
                                else{
                                    coa.setCompanyId(companyId);
                                }
                                 */
                                    
                                
                                if(jspCoa.errorSize()>0) {
					msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
					return RSLT_FORM_INCOMPLETE ;
				}
                                
                                
                                if(coa.getLevel()<2){
                                        coa.setAccRefId(0);  
                                }
                                else{
                                        if(coa.getAccRefId()==0){
                                            jspCoa.addError(jspCoa.JSP_ACC_REF_ID, "please fill in");
                                            msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                            return RSLT_FORM_INCOMPLETE ;
                                        }
                                        else{
                                            Coa coaParent = new Coa();
                                            try{
                                                 coaParent = DbCoa.fetchExc(coa.getAccRefId());
                                                 if(coa.getLevel()-1 != coaParent.getLevel()){
                                                        jspCoa.addError(jspCoa.JSP_LEVEL, "invalid level for ref. account level "+coaParent.getLevel());
                                                        msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                                                        return RSLT_FORM_INCOMPLETE ;
                                                 }
                                            }
                                            catch(Exception ez){
                                                
                                            }
                                        }
                                }
                                
                                String where = "code='"+coa.getCode()+"' and name='"+coa.getName()+"' and department_id="+coa.getDepartmentId();
                                if(coa.getOID()!=0){
                                    where = where + " and coa_id<>"+coa.getOID();
                                }
                                
                                if(DbCoa.getCount(where)>0){
                                    msgString = "Can not save duplicate entry for combination of code, name and department";
                                    return RSLT_FORM_INCOMPLETE ;
                                }
                                
                                
                                long oid = 0;

				if(coa.getOID()==0){
					try{
                                                coa.setRegDate(new java.util.Date());
						oid = dbCoa.insertExc(this.coa);
                                                
                                                if(oid!=0){
                                                    Vector pers = DbPeriode.list(0,0, "", "");
                                                    if(pers!=null && pers.size()>0){
                                                        for(int i=0; i<pers.size(); i++){
                                                            Periode p = (Periode)pers.get(i);
                                                            
                                                            CoaOpeningBalance ob = new CoaOpeningBalance();
                                                            ob.setCoaId(oid);
                                                            ob.setOpeningBalance(0);
                                                            ob.setPeriodeId(p.getOID());
                                                            
                                                            DbCoaOpeningBalance.insertExc(ob);
                                                            
                                                        }
                                                    }
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
						oid = dbCoa.updateExc(this.coa);
					}catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
                                                return getControlMsgId(excCode);
					}catch (Exception exc){
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN); 
                                                return getControlMsgId(I_CONExceptionInfo.UNKNOWN);
					}

				}
                                
                                
                                System.out.println("\n\nCmdCoa ...");
                                System.out.println("oid :  ..."+oid);
                                System.out.println("periodId :  ..."+periodId);
                                
                                //proses budget
                                if(oid!=0 && periodId!=0){
                                        System.out.println("proses budget ...");
                                        try{                                            
                                            Vector v = DbCoaBudget.list(0,0, "coa_id="+oid+" and periode_id="+periodId, "");
                                            if(v!=null && v.size()>0){
                                                
                                                System.out.println("try to update budget ..");
                                                
                                                CoaBudget cb = (CoaBudget)v.get(0);
                                                cb.setAmount(budget);
                                                DbCoaBudget.updateExc(cb);
                                                
                                                System.out.println("updated ..");
                                            }
                                            else{
                                                System.out.println("try to inser budget ..");
                                                
                                                CoaBudget cb = new CoaBudget();
                                                cb.setAmount(budget);
                                                cb.setCoaId(oid);
                                                cb.setPeriodeId(periodId);
                                                DbCoaBudget.insertExc(cb);
                                                
                                                System.out.println("insert ..");
                                            }
                                        }
                                        catch(Exception ex){
                                            System.out.println(ex.toString());
                                        }                                        
                                }
                                
                                //proses opening balance
                                //dilakukan jika baru periode pertama dan bukan expence, cogs, revenue
                                //jika periode yang pertama, proses opening balance
                                if(DbPeriode.getCount("")==1 && !coa.getAccountGroup().equals(I_Project.ACC_GROUP_COST_OF_SALES)
                                    && !coa.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE) && !coa.getAccountGroup().equals(I_Project.ACC_GROUP_REVENUE)
                                    && !coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE) && !coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_REVENUE)					
				){
                                    
                                    if(oid!=0 && periodId!=0){
                                            System.out.println("proses opening ...");
                                            
                                            String code = DbSystemProperty.getValueByName("ID_RETAINED_EARNING");
                                            
                                            CoaOpeningBalance REBalance = DbCoaOpeningBalance.getObjectByCodePeriod(code, periodId); 
                                            
                                            double add = 0;
                                            
                                            try{
                                                Vector v = DbCoaOpeningBalance.list(0,0, "coa_id="+oid+" and periode_id="+periodId, "");
                                                if(v!=null && v.size()>0){

                                                    System.out.println("try to update open ..");

                                                    CoaOpeningBalance cb = (CoaOpeningBalance)v.get(0);
                                                    //jika hanya ada 1 dan periodenya sama maka update
                                                    //jika tidak do nothing
                                                    if(v.size()==1 && cb.getPeriodeId()==periodId){
                                                        cb.setOpeningBalance(coa.getOpeningBalance());
                                                        DbCoaOpeningBalance.updateExc(cb);
                                                        System.out.println("updated ..");
                                                        
                                                        if(REBalance.getOID()==0){
                                                            add = cb.getOpeningBalance();
                                                        }
                                                        else{
                                                            add = cb.getOpeningBalance() - oldOpeningBalance;
                                                        }
                                                    }
                                                }
                                                else{

                                                    System.out.println("try to inser open ..");

                                                    CoaOpeningBalance cb = new CoaOpeningBalance();
                                                    cb.setOpeningBalance(coa.getOpeningBalance());
                                                    cb.setCoaId(oid);
                                                    cb.setPeriodeId(periodId);
                                                    DbCoaOpeningBalance.insertExc(cb);
                                                    System.out.println("inserted ..");
                                                    
                                                    add = cb.getOpeningBalance();
                                                    
                                                }
                                            }
                                            catch(Exception ex){
                                                System.out.println(ex.toString());
                                            }
                                            
                                            /*
                                            
                                            //update retained earning
                                            //insert
                                            try{
                                                if(REBalance.getOID()==0){

                                                    Vector vx = DbCoa.list(0,0, DbCoa.colNames[DbCoa.COL_CODE]+"='"+code+"'", "");

                                                    if(vx!=null && vx.size()>0){
                                                        Coa c = (Coa)vx.get(0);
                                                        oid = c.getOID();
                                                    }

                                                    CoaOpeningBalance cb = new CoaOpeningBalance();
                                                    cb.setOpeningBalance(add);
                                                    cb.setCoaId(oid);
                                                    cb.setPeriodeId(periodId);
                                                    DbCoaOpeningBalance.insertExc(cb);
                                                    System.out.println("inserted ..");
                                                    
                                                }
                                                //update
                                                else{
                                                    
                                                    REBalance.setOpeningBalance(REBalance.getOpeningBalance()+add);
                                                    DbCoaOpeningBalance.updateExc(REBalance);
                                                    
                                                }
                                            }
                                            catch(Exception e){
                                                
                                            }
                                             */
                                            
                                    }
                                }
                            /*    
                                //proses input to GL
                                if(oid!=0 && periodId!=0){     
                                        Company comp = new Company();                                        
                                        System.out.println("proses GL ...");
                                        int counter = DbGl.getNextCounter();
                                        String strNumber = DbGl.getNextNumber(counter);
                                        String strPrefix = DbGl.getNumberPrefix();
                                        long oidx = 0;
                                        ExchangeRate er = DbExchangeRate.getStandardRate();
                                        double excRate = er.getValueIdr();
                                        String codeBaginBalance = DbSystemProperty.getValueByName("ID_BEGINING_BALANCE");
                                        
                                        Vector baginBalance = DbCoa.list(0,1, "code='"+codeBaginBalance+"'", "");
                                        Coa acBaginBalance = new Coa();
                                        if(baginBalance!=null && baginBalance.size()>0){
                                            acBaginBalance = (Coa)baginBalance.get(0);
                                        }                               

                                        Vector glList = DbGl.list(0,0, "", "");
                                       
                                        //Get first periode id
                                        Vector vPeriode = new Vector();
                                        vPeriode = DbPeriode.list(0,0,"","start_date");
                                        Periode p = new Periode();
                                        if(vPeriode!=null && vPeriode.size()>0)
                                        {
                                            p = (Periode)vPeriode.get(0);
                                        }
                                        
                                        long firstPeriodId = p.getOID();
                                       //dan periode = first period
                                        if(periodId==firstPeriodId)
                                        {
                                            System.out.println("First Period");
                                            //get GL list by coaid, period
                                            Vector vGl = new Vector();															
                                            CONResultSet crs = null;
                                            String sql = "select gld.* from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                                                    " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+ acBaginBalance.getOID() +
                                                    " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+firstPeriodId+" and gld.credit="+oldOpeningBalance;
                                            System.out.println(sql);
                                            try{
                                                crs = CONHandler.execQueryResult(sql);
                                                ResultSet rs = crs.getResultSet();
                                                while(rs.next()){
                                                    GlDetail g = new GlDetail();
                                                    DbGlDetail.resultToObject(rs, g);
                                                    vGl.add(g);
                                                }
                                            }catch(Exception ex){
                                            }finally{
                                                    CONResultSet.close(crs);
                                            }
                                            
                                            GlDetail gld = new GlDetail();
                                            if(vGl!=null && vGl.size()>0)
                                            {
                                                for(int iy=0; iy<vGl.size(); iy++)
                                                {
                                                    try{
                                                        gld = (GlDetail)vGl.get(iy);								
                                                    }catch(Exception e) {
                                                        System.out.println(e);
                                                    }
                                                }
                                            }

                                            if(gld.getCredit()!=coa.getOpeningBalance())
                                            {
                                                if(oldOpeningBalance!=0)
                                                {                   
                                                    // Buat jurnal balik
                                                    oidx = DbGl.postJournalMain(comp.getBookingCurrencyId(),new java.util.Date(),counter,strNumber,strPrefix,I_Project.JOURNAL_TYPE_GENERAL_LEDGER,"Automatic Begining Balance",loginId,"",0,"",new java.util.Date());
                                                    if(oidx!=0){
                                                        DbGl.postJournalDetail(excRate, acBaginBalance.getOID(),0, excRate * oldOpeningBalance, excRate * oldOpeningBalance, comp.getBookingCurrencyId(), oidx, "Begining balance", 0);                                        
                                                        DbGl.postJournalDetail(excRate, oidCoa, 0, 0, 0, comp.getBookingCurrencyId(), oidx, "Begining Balance", 0);
                                                    }
                                                }
                                                // add new GL record   
                                                counter = DbGl.getNextCounter();
                                                strNumber = DbGl.getNextNumber(counter);
                                                strPrefix = DbGl.getNumberPrefix();
                                                oidx = DbGl.postJournalMain(comp.getBookingCurrencyId(),new java.util.Date(),counter,strNumber,strPrefix,I_Project.JOURNAL_TYPE_GENERAL_LEDGER,"Automatic Begining Balance",loginId,"",0,"",new java.util.Date());
                                                if(oidx!=0){
                                                    DbGl.postJournalDetail(excRate, acBaginBalance.getOID(), excRate * coa.getOpeningBalance(),0, excRate * coa.getOpeningBalance(), comp.getBookingCurrencyId(), oidx, "Begining balance", 0);                                        
                                                    DbGl.postJournalDetail(excRate, oidCoa, 0, 0, 0, comp.getBookingCurrencyId(), oidx, "Begining Balance", 0);
                                                }
                                            }
                                        }
                                }
                             */
				break;

                        case JSPCommand.SUBMIT :
                            jspCoa.requestEntityObject(coa);
                            break;
                        
                                
			case JSPCommand.EDIT :
				if (oidCoa != 0) {
					try {
						coa = DbCoa.fetchExc(oidCoa);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.ASK :
				if (oidCoa != 0) {
					try {
						coa = DbCoa.fetchExc(oidCoa);
					} catch (CONException dbexc){
						excCode = dbexc.getErrorCode();
						msgString = getSystemMessage(excCode);
					} catch (Exception exc){ 
						msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
					}
				}
				break;

			case JSPCommand.DELETE :
				if (oidCoa != 0){
					try{
						
                                            
                                            oid = DbCoa.deleteExc(oidCoa);
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
