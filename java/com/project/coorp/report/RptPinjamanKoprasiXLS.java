/*
 * Report Donor to IFC by Group XLS.java
 *
 * Created on March 30, 2008, 1:33 AM
 */

package com.project.coorp.report;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPOutputStream;
import java.io.Writer;
import java.util.Vector;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.util.JSPFormater;
import com.project.payroll.*;
import com.project.util.jsp.*;
import com.project.fms.session.*;
import com.project.fms.activity.*;
import com.project.general.*;

public class RptPinjamanKoprasiXLS extends HttpServlet {
    
    /** Initializes the servlet.
    */  
    public static String formatDate = "dd MMMM yyyy";
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }

    /** Destroys the servlet.
    */  
    public void destroy() {

    }

    String XMLSafe ( String in )
    {
        return in;
    	//return HTMLEncoder.encode(in);
    }
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    * 
    * Why not use a DOM? Because we want to be able to create the spreadsheet on-the-fly, without
    * having to use up a lot of memory before hand
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        System.out.println("---===| Excel Report |===---");
        response.setContentType("application/x-msexcel");
        
        Company cmp = new Company();
        try{
            Vector listCompany = DbCompany.list(0,0, "", "");
            if(listCompany!=null && listCompany.size()>0){
                cmp = (Company)listCompany.get(0);
            }
        }catch(Exception ext){
            System.out.println(ext.toString());
        }
        
        RptPinjamanKoprasi rptKonstan = new RptPinjamanKoprasi();
        try{
            HttpSession session = request.getSession();
            rptKonstan = (RptPinjamanKoprasi)session.getValue("KONSTAN");
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
        
        Vector vDetail = new Vector();
        try{
            HttpSession session = request.getSession();
            vDetail = (Vector)session.getValue("DETAIL");
        }catch(Exception e){
            System.out.println(e.toString());
        }
        
        boolean gzip = false ;
        
        //response.setCharacterEncoding( "UTF-8" ) ;
        OutputStream gzo ;
        if ( gzip ) {
            response.setHeader( "Content-Encoding", "gzip" ) ;
            gzo = new GZIPOutputStream( response.getOutputStream() ) ;
        } else {
            gzo = response.getOutputStream() ;
        }
        PrintWriter wb = new PrintWriter( new OutputStreamWriter( gzo, "UTF-8" ) ) ;

        //PrintWriter wb = response.getWriter() ;
        wb.println("<?xml version=\"1.0\"?>");
        wb.println("<?mso-application progid=\"Excel.Sheet\"?>");
        wb.println("<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"");
        wb.println("xmlns:o=\"urn:schemas-microsoft-com:office:office\"");
        wb.println("xmlns:x=\"urn:schemas-microsoft-com:office:excel\"");
        wb.println("xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"");
        wb.println("xmlns:html=\"http://www.w3.org/TR/REC-html40\">");
        wb.println("<DocumentProperties xmlns=\"urn:schemas-microsoft-com:office:office\">");
        wb.println("<Author>Victor</Author>");
        wb.println("<LastAuthor>Victor</LastAuthor>");
        wb.println("<LastPrinted>2009-07-10T07:33:23Z</LastPrinted>");
        wb.println("<Created>2009-07-10T05:56:39Z</Created>");
        wb.println("<LastSaved>2009-07-10T07:34:35Z</LastSaved>");
        wb.println("<Company>Victory</Company>");
        wb.println("<Version>11.5606</Version>");
        wb.println("</DocumentProperties>");
        wb.println("<ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">");
        wb.println("<WindowHeight>9210</WindowHeight>");
        wb.println("<WindowWidth>15195</WindowWidth>");
        wb.println("<WindowTopX>480</WindowTopX>");
        wb.println("<WindowTopY>75</WindowTopY>");
        wb.println("<ProtectStructure>False</ProtectStructure>");
        wb.println("<ProtectWindows>False</ProtectWindows>");
        wb.println("</ExcelWorkbook>");
        
        wb.println("<Styles>");
        wb.println("<Style ss:ID=\"Default\" ss:Name=\"Normal\">");
        wb.println("<Alignment ss:Vertical=\"Bottom\"/>");
        wb.println("<Borders/>");
        wb.println("<Font/>");
        wb.println("<Interior/>");
        wb.println("<NumberFormat/>");
        wb.println("<Protection/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"m15739492\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("<Interior ss:Color=\"#CCFFCC\" ss:Pattern=\"Solid\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"m15739502\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("<Interior ss:Color=\"#CCFFCC\" ss:Pattern=\"Solid\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"m15739512\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("<Interior ss:Color=\"#CCFFCC\" ss:Pattern=\"Solid\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"m15739522\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("<Interior ss:Color=\"#CCFFCC\" ss:Pattern=\"Solid\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"m15739532\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("<Interior ss:Color=\"#CCFFCC\" ss:Pattern=\"Solid\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"m15739542\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("<Interior ss:Color=\"#CCFFCC\" ss:Pattern=\"Solid\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s33\">");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s40\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\"/>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s41\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\"/>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s46\">");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"9\" ss:Bold=\"1\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s53\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"9\" ss:Bold=\"1\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s55\">");
        wb.println("<Alignment ss:Vertical=\"Bottom\"/>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"9\" ss:Bold=\"1\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s80\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("<Interior ss:Color=\"#CCFFCC\" ss:Pattern=\"Solid\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s85\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s86\">");
        wb.println("<Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Bottom\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s87\">");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\"/>");
        wb.println("<NumberFormat ss:Format=\"#,##0\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s89\">");
        wb.println("<Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Bottom\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\"/>");
        wb.println("<NumberFormat ss:Format=\"#,##0\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s90\">");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s92\">");
        wb.println("<Alignment ss:Vertical=\"Bottom\"/>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s93\">");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\"/>");
        wb.println("</Style>");
        wb.println("</Styles>");
        
        
        wb.println("<Worksheet ss:Name=\"Sheet1\">");
        wb.println("<Table ss:ExpandedColumnCount=\"10\" x:FullColumns=\"1\"");
        wb.println("x:FullRows=\"1\">");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"6.75\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"24.75\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"51\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"50.25\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"60\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"57\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"67.5\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"51\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"58.5\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"34.5\"/>");
        wb.println("<Row ss:Index=\"2\">");
        wb.println("<Cell ss:Index=\"2\" ss:MergeAcross=\"8\" ss:StyleID=\"s53\"><Data ss:Type=\"String\">"+cmp.getName()+"</Data></Cell>");
        wb.println("</Row>");
        wb.println("<Row ss:AutoFitHeight=\"0\" ss:Height=\"11.25\">");
        wb.println("<Cell ss:Index=\"2\" ss:MergeAcross=\"8\" ss:StyleID=\"s40\"><Data ss:Type=\"String\">"+cmp.getAddress()+"</Data></Cell>");
        wb.println("</Row>");
        wb.println("<Row ss:AutoFitHeight=\"0\" ss:Height=\"11.25\">");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s41\"/>");
        wb.println("<Cell ss:StyleID=\"s41\"/>");
        wb.println("<Cell ss:StyleID=\"s41\"/>");
        wb.println("<Cell ss:StyleID=\"s41\"/>");
        wb.println("<Cell ss:StyleID=\"s41\"/>");
        wb.println("<Cell ss:StyleID=\"s41\"/>");
        wb.println("<Cell ss:StyleID=\"s41\"/>");
        wb.println("<Cell ss:StyleID=\"s41\"/>");
        wb.println("<Cell ss:StyleID=\"s41\"/>");
        wb.println("</Row>");
        
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s33\"><Data ss:Type=\"String\">Peminjam</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s92\"/>");
        wb.println("<Cell ss:MergeAcross=\"1\" ss:StyleID=\"s92\"><Data ss:Type=\"String\">: "+rptKonstan.getPeminjam()+"</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("</Row>");
        
        wb.println("<Row ss:AutoFitHeight=\"0\" ss:Height=\"5.25\">");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("</Row>");
        
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s33\"><Data ss:Type=\"String\">Bank</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s92\"/>");
        wb.println("<Cell ss:MergeAcross=\"1\" ss:StyleID=\"s92\"><Data ss:Type=\"String\">: "+rptKonstan.getBank()+"</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s92\"><Data ss:Type=\"String\">No Rekening      </Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s92\"><Data ss:Type=\"String\">     : "+rptKonstan.getNoRekening()+"</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s93\"/>");
        wb.println("</Row>");
        
        wb.println("<Row ss:AutoFitHeight=\"0\" ss:Height=\"5.25\">");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("</Row>");
        
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s33\"><Data ss:Type=\"String\">Tanggal</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s92\"/>");
        wb.println("<Cell ss:MergeAcross=\"1\" ss:StyleID=\"s92\"><Data ss:Type=\"String\">: "+JSPFormater.formatDate(rptKonstan.getTanggal(), "dd/MM/yyyy")+"</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s92\"><Data ss:Type=\"String\">Bunga Pinjaman     </Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s92\"><Data ss:Type=\"String\">     : "+rptKonstan.getBungaPinjaman()+" % / tahun</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s93\"/>");
        wb.println("</Row>");
        
        wb.println("<Row ss:AutoFitHeight=\"0\" ss:Height=\"5.25\">");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s33\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("</Row>");
        
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s92\"><Data ss:Type=\"String\">Total Pinjaman</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s92\"/>");
        wb.println("<Cell ss:MergeAcross=\"1\" ss:StyleID=\"s92\"><Data ss:Type=\"String\">: Rp. "+JSPFormater.formatNumber(rptKonstan.getTotalPinjaman(), "#,###.##")+"</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s92\"><Data ss:Type=\"String\">Lama Angsuran                 </Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s92\"><Data ss:Type=\"String\">     : "+rptKonstan.getLamaAngsuran()+" Bulan</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s93\"/>");
        wb.println("</Row>");
        
        wb.println("<Row ss:AutoFitHeight=\"0\" ss:Height=\"6.75\">");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s55\"/>");
        wb.println("<Cell ss:StyleID=\"s55\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:Index=\"10\" ss:StyleID=\"s46\"/>");
        wb.println("</Row>");
        
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("<Cell ss:StyleID=\"s46\"/>");
        wb.println("</Row>");
        
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:MergeDown=\"1\" ss:StyleID=\"s80\"><Data ss:Type=\"String\">Ang.</Data></Cell>");
        wb.println("<Cell ss:MergeDown=\"1\" ss:StyleID=\"m15739492\"><Data ss:Type=\"String\">Angsuran   Pokok     </Data></Cell>");
        wb.println("<Cell ss:MergeDown=\"1\" ss:StyleID=\"s80\"><Data ss:Type=\"String\">Bunga</Data></Cell>");
        wb.println("<Cell ss:MergeDown=\"1\" ss:StyleID=\"m15739502\"><Data ss:Type=\"String\">Kewajiban     Anggsuran</Data></Cell>");
        wb.println("<Cell ss:MergeDown=\"1\" ss:StyleID=\"s80\"><Data ss:Type=\"String\">Saldo</Data></Cell>");
        wb.println("<Cell ss:MergeDown=\"1\" ss:StyleID=\"m15739512\"><Data ss:Type=\"String\">Tanggal                    Jatuh Tempo</Data></Cell>");
        wb.println("<Cell ss:MergeDown=\"1\" ss:StyleID=\"m15739522\"><Data ss:Type=\"String\">Tanggal         Angsuran</Data></Cell>");
        wb.println("<Cell ss:MergeDown=\"1\" ss:StyleID=\"m15739532\"><Data ss:Type=\"String\">Jumlah    Angsuran</Data></Cell>");
        wb.println("<Cell ss:MergeDown=\"1\" ss:StyleID=\"m15739542\"><Data ss:Type=\"String\">Paraf</Data></Cell>");
        wb.println("</Row>");
       
        if(vDetail!=null && vDetail.size()>0){
            for(int i=0;i<vDetail.size();i++){
                RptPinjamanKoprasiL detail = (RptPinjamanKoprasiL)vDetail.get(i);
                
                wb.println("<Row ss:Index=\""+(16+i)+"\">");
                wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s85\"><Data ss:Type=\"Number\">"+detail.getAngsuran()+"</Data></Cell>");
                wb.println("<Cell ss:StyleID=\"s87\"><Data ss:Type=\"Number\">"+((detail.getAngPokok()==0) ? "" : JSPFormater.formatNumber(detail.getAngPokok(), "#,###"))+"</Data></Cell>");
                wb.println("<Cell ss:StyleID=\"s87\"><Data ss:Type=\"Number\">"+((detail.getBunga()==0) ? "" : JSPFormater.formatNumber(detail.getBunga(), "#,###"))+"</Data></Cell>");
                wb.println("<Cell ss:StyleID=\"s87\"><Data ss:Type=\"Number\">"+((detail.getKwjbAngsuran()==0) ? "" : JSPFormater.formatNumber(detail.getKwjbAngsuran(), "#,###"))+"</Data></Cell>");
                wb.println("<Cell ss:StyleID=\"s87\"><Data ss:Type=\"Number\">"+((detail.getSaldo()==0) ? "" : JSPFormater.formatNumber(detail.getSaldo(), "#,###"))+"</Data></Cell>");
                wb.println("<Cell ss:StyleID=\"s86\"><Data ss:Type=\"String\">"+JSPFormater.formatDate(detail.getTglJatuhTempo(),"dd/MM/yyyy")+"</Data></Cell>");
                wb.println("<Cell ss:StyleID=\"s86\"/>");
                wb.println("<Cell ss:StyleID=\"s89\"/>");
                wb.println("<Cell ss:StyleID=\"s90\"/>");
                wb.println("</Row>");
            }
        }
         
        
        wb.println("</Table>");
        wb.println("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
        
        wb.println("<Print>");
        wb.println("<ValidPrinterInfo/>");
        wb.println("<PaperSizeIndex>9</PaperSizeIndex>");
        wb.println("<HorizontalResolution>600</HorizontalResolution>");
        wb.println("<VerticalResolution>600</VerticalResolution>");
        wb.println("</Print>");
        wb.println("<Selected/>");
        wb.println("<DoNotDisplayGridlines/>");
        wb.println("<Panes>");
        wb.println("<Pane>");
        wb.println("<Number>3</Number>");
        wb.println("<ActiveRow>13</ActiveRow>");
        wb.println("<ActiveCol>9</ActiveCol>");
        wb.println("<RangeSelection>R14C10:R15C10</RangeSelection>");
        wb.println("</Pane>");
        wb.println("</Panes>");
        wb.println("<ProtectObjects>False</ProtectObjects>");
        wb.println("<ProtectScenarios>False</ProtectScenarios>");
        wb.println("</WorksheetOptions>");
        wb.println("</Worksheet>");
        wb.println("<Worksheet ss:Name=\"Sheet2\">");
        wb.println("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
        wb.println("<ProtectObjects>False</ProtectObjects>");
        wb.println("<ProtectScenarios>False</ProtectScenarios>");
        wb.println("</WorksheetOptions>");
        wb.println("</Worksheet>");
        wb.println("<Worksheet ss:Name=\"Sheet3\">");
        wb.println("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
        wb.println("<ProtectObjects>False</ProtectObjects>");
        wb.println("<ProtectScenarios>False</ProtectScenarios>");
        wb.println("</WorksheetOptions>");
        wb.println("</Worksheet>");
        wb.println("</Workbook>");
        
        wb.flush() ;
    }
    
    
    /** Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    /** Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }
    
    public static void main(String[] arg){
        try{
            String str = "aku anak cerdas > pandai & rajin";

            System.out.println(URLEncoder.encode(str, "UTF-8"));
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    
}
