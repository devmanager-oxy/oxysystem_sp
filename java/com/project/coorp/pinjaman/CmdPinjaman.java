package com.project.coorp.pinjaman;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.project.util.*;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*;
import com.project.util.lang.*;

public class CmdPinjaman extends Control implements I_Language {

    public static int RSLT_OK = 0;
    public static int RSLT_UNKNOWN_ERROR = 1;
    public static int RSLT_EST_CODE_EXIST = 2;
    public static int RSLT_FORM_INCOMPLETE = 3;
    public static String[][] resultText = {
        {"Berhasil", "Tidak dapat diproses", "NoPerkiraan sudah ada", "Data tidak lengkap"},
        {"Succes", "Can not process", "Estimation code exist", "Data incomplete"}};
    private int start;
    private String msgString;
    private Pinjaman pinjaman;
    private DbPinjaman pstPinjaman;
    private JspPinjaman jspPinjaman;
    int language = LANGUAGE_DEFAULT;

    public CmdPinjaman(HttpServletRequest request) {
        msgString = "";
        pinjaman = new Pinjaman();
        try {
            pstPinjaman = new DbPinjaman(0);
        } catch (Exception e) {
            ;
        }
        jspPinjaman = new JspPinjaman(request, pinjaman);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_CONExceptionInfo.MULTIPLE_ID:
                return resultText[language][RSLT_EST_CODE_EXIST];
            default:
                return resultText[language][RSLT_UNKNOWN_ERROR];
        }
    }

    private int getControlMsgId(int msgCode) {
        switch (msgCode) {
            case I_CONExceptionInfo.MULTIPLE_ID:
                return RSLT_EST_CODE_EXIST;
            default:
                return RSLT_UNKNOWN_ERROR;
        }
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public Pinjaman getPinjaman() {
        return pinjaman;
    }

    public JspPinjaman getForm() {
        return jspPinjaman;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidPinjaman) {
        msgString = "";
        int excCode = I_CONExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case JSPCommand.ADD:
                break;

            case JSPCommand.SAVE:
                if (oidPinjaman != 0) {
                    try {
                        pinjaman = DbPinjaman.fetchExc(oidPinjaman);
                    } catch (Exception exc) {
                    }
                }

                jspPinjaman.requestEntityObject(pinjaman);

                if (pinjaman.getType() == DbPinjaman.TYPE_PINJAMAN_BANK) {
                    if (pinjaman.getBankId() == 0) {
                        jspPinjaman.addError(jspPinjaman.JSP_BANK_ID, JSPMessage.errString[JSPMessage.ERR_REQUIRED]);
                    }
                    if (pinjaman.getBungaBank() == 0) {
                        jspPinjaman.addError(jspPinjaman.JSP_BUNGA_BANK, JSPMessage.errString[JSPMessage.ERR_REQUIRED]);
                    }
                }

                if (pinjaman.getType() != DbPinjaman.TYPE_PINJAMAN_KOPERASI_KE_BANK) {
                    if (pinjaman.getMemberId() == 0) {
                        jspPinjaman.addError(jspPinjaman.JSP_MEMBER_ID, JSPMessage.errString[JSPMessage.ERR_REQUIRED]);
                    }
                }

                if (jspPinjaman.errorSize() > 0) {
                    msgString = JSPMessage.getMsg(JSPMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (pinjaman.getOID() == 0) {
                    try {
                        pinjaman.setCreateDate(new Date());
                        int cnt = DbPinjaman.getNextCounter(pinjaman.getType());
                        String prefix = DbPinjaman.getNumberPrefix(pinjaman.getType());
                        String number = DbPinjaman.getNextNumber(cnt, pinjaman.getType());
                        if (!number.equalsIgnoreCase(pinjaman.getNumber())) {
                            number = pinjaman.getNumber();
                        }

                        pinjaman.setCounter(cnt);
                        pinjaman.setPrefixNumber(prefix);
                        pinjaman.setNumber(number);

                        pinjaman.setApproveById(0);

                        long oid = pstPinjaman.insertExc(this.pinjaman);

                        //delete detail dulu
                        if (oid != 0 && pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT) {
                            try {
                                CONHandler.execUpdate("delete from " + DbPinjamanDetail.DB_PINJAMAN_DETAIL + " where " + DbPinjamanDetail.colNames[DbPinjamanDetail.COL_PINJAMAN_ID] + "=" + oid);
                            } catch (Exception e) {
                            }
                            
                            if (pinjaman.getJenisCicilan() == DbPinjaman.JENIS_CICILAN_TETAP) {

                                Date jatuhTempo = pinjaman.getDate();
                                jatuhTempo.setMonth(jatuhTempo.getMonth() + 1);
                                jatuhTempo.setDate(pinjaman.getTanggalJatuhTempo());

                                double saldo = pinjaman.getTotalPinjaman();

                                int angsuranTerbayar = pinjaman.getAngsuranTerakhir();

                                if (pinjaman.getOID() != 0) {
                                    for (int i = 0; i < pinjaman.getLamaCicilan(); i++) {

                                        PinjamanDetail pd = new PinjamanDetail();
                                        pd.setMemberId(pinjaman.getMemberId());
                                        pd.setPinjamanId(pinjaman.getOID());
                                        pd.setAmount(pinjaman.getTotalPinjaman() / pinjaman.getLamaCicilan());                                        
                                        pd.setBunga(((pinjaman.getTotalPinjaman()) * pinjaman.getBunga()) / 100);
                                        pd.setTotalKoperasi(pd.getAmount() + pd.getBunga());
                                        pd.setSaldoKoperasi(saldo - pd.getAmount());
                                        saldo = saldo - pd.getAmount();

                                        if (pinjaman.getType() == DbPinjaman.TYPE_PINJAMAN_BANK) {
                                            pd.setBungaBank(((pinjaman.getTotalPinjaman() / pinjaman.getLamaCicilan()) * pinjaman.getBungaBank()) / 100);
                                            pd.setTotalBank(pd.getAmount() + pd.getBungaBank());
                                        }
                                        pd.setCicilanKe(i + 1);
                                        pd.setJatuhTempo(jatuhTempo);

                                        //proses untuk data awal
                                        if (angsuranTerbayar > 0) {
                                            if (i < angsuranTerbayar) {
                                                pd.setStatus(DbPinjaman.PAYMENT_STATUS_LUNAS);
                                            }
                                        }

                                        try {
                                            long oidx = DbPinjamanDetail.insertExc(pd);

                                            //jika sudah terbayar buatkan paymentnya
                                            if (oidx != 0 && pd.getStatus() == DbPinjaman.PAYMENT_STATUS_LUNAS) {
                                                DbBayarPinjaman.insertAndPostPaymentAuto(pinjaman, pd);
                                            }
                                        } catch (Exception e) {
                                        }

                                        jatuhTempo = (Date) jatuhTempo.clone();
                                        jatuhTempo.setMonth(jatuhTempo.getMonth() + 1);
                                    }
                                }
                            }
                        
                        }
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                        return getControlMsgId(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                        return getControlMsgId(I_CONExceptionInfo.UNKNOWN);
                    }

                } else {
                    try {
                        if (pinjaman.getStatus() != DbPinjaman.STATUS_DRAFT) {                            
                            pinjaman.setApproveDate(new Date());
                        } else if (pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT) {
                            pinjaman.setApproveById(0);         
                            pinjaman.setApproveDate(null);
                        }

                        long oid = pstPinjaman.updateExc(this.pinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }

                }
                break;

            case JSPCommand.SUBMIT:
                if (oidPinjaman != 0) {
                    try {
                        pinjaman = DbPinjaman.fetchExc(oidPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.POST:
                if (oidPinjaman != 0) {
                    try {
                        pinjaman = DbPinjaman.fetchExc(oidPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.START:
                if (oidPinjaman != 0) {
                    try {
                        pinjaman = DbPinjaman.fetchExc(oidPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.RESET:
                if (oidPinjaman != 0) {
                    try {
                        pinjaman = DbPinjaman.fetchExc(oidPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.EDIT:
                if (oidPinjaman != 0) {
                    try {
                        pinjaman = DbPinjaman.fetchExc(oidPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.PRINT:
                if (oidPinjaman != 0) {
                    try {
                        pinjaman = DbPinjaman.fetchExc(oidPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.UPDATE:
                if (oidPinjaman != 0) {
                    try {
                        pinjaman = DbPinjaman.fetchExc(oidPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.ASK:
                if (oidPinjaman != 0) {
                    try {
                        pinjaman = DbPinjaman.fetchExc(oidPinjaman);
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case JSPCommand.DELETE:
                if (oidPinjaman != 0) {
                    try {
                        long oid = DbPinjaman.deleteExc(oidPinjaman);
                        if (oid != 0) {
                            msgString = JSPMessage.getMessage(JSPMessage.MSG_DELETED);
                            excCode = RSLT_OK;
                        } else {
                            msgString = JSPMessage.getMessage(JSPMessage.ERR_DELETED);
                            excCode = RSLT_FORM_INCOMPLETE;
                        }
                    } catch (CONException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_CONExceptionInfo.UNKNOWN);
                    }
                }
                break;

            default:

        }
        return rsCode;
    }
}
