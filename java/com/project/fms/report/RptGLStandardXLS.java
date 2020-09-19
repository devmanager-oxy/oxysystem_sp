/*
 * Report RptGLStandardXLS.java
 *
 * Created on May 6, 2008, 3:33 AM
 */

package com.project.fms.report;

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
import com.project.fms.master.*;
import com.project.payroll.*;
import com.project.util.jsp.*;
import com.project.fms.session.*;

import com.project.general.Company;
import com.project.general.DbCompany;


public class RptGLStandardXLS extends HttpServlet {
    
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
        
        // Load User Login
        String loginId = JSPRequestValue.requestString(request, "oid");
        System.out.println("UserId : "+loginId);

        // Load Date
        String strPeriod = JSPRequestValue.requestString(request, "period");
        
        // Load Company
        Company company = DbCompany.getCompany();
        long oidCompany = company.getOID();           
        System.out.println("oidCompany : "+oidCompany);        

        // Load Invoice Item
        Vector vectorList = new Vector(1,1);
        try{
            HttpSession session = request.getSession();
            vectorList = (Vector)session.getValue("GL_REPORT");
        } catch (Exception e) { System.out.println(e); }
        //System.out.println(vectorList);              
        
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

        wb.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" ) ;        
        //open if you want to open this xml file by excel
        wb.println("<?mso-application progid=\"Excel.Sheet\"?>");
        
        wb.println("<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"");
        wb.println(" xmlns:o=\"urn:schemas-microsoft-com:office:office\"");
        wb.println(" xmlns:x=\"urn:schemas-microsoft-com:office:excel\"");
        wb.println(" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"");
        wb.println(" xmlns:html=\"http://www.w3.org/TR/REC-html40\">");
        
        wb.println(" <DocumentProperties xmlns=\"urn:schemas-microsoft-com:office:office\">");
        wb.println("  <Version>11.5606</Version>");
        wb.println(" </DocumentProperties>");
        
        wb.println(" <OfficeDocumentSettings xmlns=\"urn:schemas-microsoft-com:office:office\">");
        wb.println("  <Colors>");
        wb.println("   <Color>");
        wb.println("    <Index>16</Index>");
        wb.println("    <RGB>#EBECED</RGB>");
        wb.println("   </Color>");
        wb.println("   <Color>");
        wb.println("    <Index>24</Index>");
        wb.println("    <RGB>#6CA35A</RGB>");
        wb.println("   </Color>");
        wb.println("  </Colors>");
        wb.println(" </OfficeDocumentSettings>");
        
        wb.println(" <ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">");
        wb.println("  <WindowHeight>10005</WindowHeight>");
        wb.println("  <WindowWidth>10005</WindowWidth>");
        wb.println("  <WindowTopX>120</WindowTopX>");
        wb.println("  <WindowTopY>135</WindowTopY>");
        wb.println("  <ProtectStructure>False</ProtectStructure>");
        wb.println("  <ProtectWindows>False</ProtectWindows>");
        wb.println(" </ExcelWorkbook>");
        
        // Style region
        wb.println("    <Styles>");
        wb.println("     <Style ss:ID=\"Default\" ss:Name=\"Normal\">");
        wb.println("      <Alignment ss:Vertical=\"Bottom\"/>");
        wb.println("      <Borders/>");
        wb.println("      <Font/>");
        wb.println("      <Interior/>");
        wb.println("      <NumberFormat/>");
        wb.println("      <Protection/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"m16929468\">");
        wb.println("      <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\" ss:Indent=\"1\"/>");
        wb.println("      <Borders>");
        wb.println("       <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("      </Borders>");
        wb.println("      <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>");
        wb.println("      <Interior ss:Color=\"#EBECED\" ss:Pattern=\"Solid\"/>");
        wb.println("      <NumberFormat/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"m16929478\">");
        wb.println("      <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Center\"/>");
        wb.println("      <Borders>");
        wb.println("       <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("      </Borders>");
        wb.println("      <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>");
        wb.println("      <Interior ss:Color=\"#C0C0C0\" ss:Pattern=\"Solid\"/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"m16929488\">");
        wb.println("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\"/>");
        wb.println("      <Borders>");
        wb.println("       <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("      </Borders>");
        wb.println("      <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>");
        wb.println("      <Interior ss:Color=\"#C0C0C0\" ss:Pattern=\"Solid\"/>");
        wb.println("      <NumberFormat ss:Format=\"#,##0.00_);\\(#,##0.00\\)\"/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"m16929498\">");
        wb.println("      <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\" ss:Indent=\"1\"/>");
        wb.println("      <Borders>");
        wb.println("       <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("      </Borders>");
        wb.println("      <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>");
        wb.println("      <Interior ss:Color=\"#C6DCBE\" ss:Pattern=\"Solid\"/>");
        wb.println("      <NumberFormat/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"m16943730\">");
        wb.println("      <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Center\"/>");
        wb.println("      <Borders>");
        wb.println("       <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("      </Borders>");
        wb.println("      <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>");
        wb.println("      <Interior ss:Color=\"#C0C0C0\" ss:Pattern=\"Solid\"/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"m16943740\">");
        wb.println("      <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\"/>");
        wb.println("      <Borders>");
        wb.println("       <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("      </Borders>");
        wb.println("      <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>");
        wb.println("      <Interior ss:Color=\"#C0C0C0\" ss:Pattern=\"Solid\"/>");
        wb.println("      <NumberFormat ss:Format=\"#,##0.00_);\\(#,##0.00\\)\"/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s23\">");
        wb.println("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
        wb.println("      <Font x:Family=\"Swiss\" ss:Size=\"18\" ss:Bold=\"1\"/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s25\">");
        wb.println("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
        wb.println("      <Font x:Family=\"Swiss\" ss:Size=\"16\" ss:Bold=\"1\"/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s27\">");
        wb.println("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
        wb.println("      <Borders>");
        wb.println("       <Border ss:Position=\"Bottom\" ss:LineStyle=\"Double\" ss:Weight=\"3\"/>");
        wb.println("      </Borders>");
        wb.println("      <Font ss:Size=\"12\"/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s42\">");
        wb.println("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s43\">");
        wb.println("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\"/>");
        wb.println("      <Borders/>");
        wb.println("      <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s44\">");
        wb.println("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\"/>");
        wb.println("      <Borders>");
        wb.println("       <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("      </Borders>");
        wb.println("      <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>");
        wb.println("      <Interior ss:Color=\"#EBECED\" ss:Pattern=\"Solid\"/>");
        wb.println("      <NumberFormat/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s45\">");
        wb.println("      <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Center\"/>");
        wb.println("      <Borders/>");
        wb.println("      <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s46\">");
        wb.println("      <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\" ss:Indent=\"1\"/>");
        wb.println("      <Borders>");
        wb.println("       <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("      </Borders>");
        wb.println("      <Font x:Family=\"Swiss\"/>");
        wb.println("      <NumberFormat ss:Format=\"[ENG][$-409]d\\-mmm\\-yyyy;@\"/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s48\">");
        wb.println("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\"/>");
        wb.println("      <Borders>");
        wb.println("       <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("      </Borders>");
        wb.println("      <Font x:Family=\"Swiss\"/>");
        wb.println("      <NumberFormat/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s50\">");
        wb.println("      <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Center\"/>");
        wb.println("      <Borders>");
        wb.println("       <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("      </Borders>");
        wb.println("      <Font x:Family=\"Swiss\"/>");
        wb.println("      <NumberFormat/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s51\">");
        wb.println("      <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\" ss:Indent=\"1\"/>");
        wb.println("      <Borders>");
        wb.println("       <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("      </Borders>");
        wb.println("      <Font x:Family=\"Swiss\"/>");
        wb.println("      <NumberFormat ss:Format=\"#,##0.00_);\\(#,##0.00\\)\"/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s59\">");
        wb.println("      <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\" ss:Indent=\"1\"/>");
        wb.println("      <Borders>");
        wb.println("       <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("       <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("      </Borders>");
        wb.println("      <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>");
        wb.println("      <Interior ss:Color=\"#EBECED\" ss:Pattern=\"Solid\"/>");
        wb.println("      <NumberFormat ss:Format=\"#,##0.00_);\\(#,##0.00\\)\"/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s60\">");
        wb.println("      <Interior/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s61\">");
        wb.println("      <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Center\"/>");
        wb.println("      <Borders/>");
        wb.println("      <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>");
        wb.println("      <Interior/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s62\">");
        wb.println("      <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\" ss:Indent=\"1\"/>");
        wb.println("      <Borders>");
        wb.println("       <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("      </Borders>");
        wb.println("      <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>");
        wb.println("      <Interior/>");
        wb.println("      <NumberFormat/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s63\">");
        wb.println("      <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\" ss:Indent=\"1\"/>");
        wb.println("      <Borders/>");
        wb.println("      <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>");
        wb.println("      <Interior/>");
        wb.println("      <NumberFormat ss:Format=\"#,##0.00_);\\(#,##0.00\\)\"/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s71\">");
        wb.println("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
        wb.println("      <Borders/>");
        wb.println("      <NumberFormat/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s73\">");
        wb.println("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
        wb.println("      <NumberFormat/>");
        wb.println("     </Style>");
        wb.println("     <Style ss:ID=\"s74\">");
        wb.println("      <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Bottom\"/>");
        wb.println("     </Style>");
        wb.println("    </Styles>");
        wb.flush() ;       
        
        wb.println("  <Worksheet ss:Name=\"ProfitLoss\">");
        wb.println("    <Table>");
        wb.println("      <Column ss:AutoFitWidth=\"0\" ss:Width=\"14.25\"/>");
        wb.println("      <Column ss:AutoFitWidth=\"0\" ss:Width=\"19.5\"/>");
        wb.println("      <Column ss:AutoFitWidth=\"0\" ss:Width=\"98.25\" ss:Span=\"1\"/>");
        wb.println("      <Column ss:Index=\"5\" ss:AutoFitWidth=\"0\" ss:Width=\"318.75\"/>");
        wb.println("      <Column ss:AutoFitWidth=\"0\" ss:Width=\"119.25\" ss:Span=\"2\"/>");
        wb.println("      <Column ss:Index=\"9\" ss:AutoFitWidth=\"0\" ss:Width=\"14.25\"/>");
        wb.println("      <Column ss:Hidden=\"1\" ss:AutoFitWidth=\"0\" ss:Span=\"246\"/>");

       
        //Top Header
        company = new Company();
        try{
            company = DbCompany.fetchExc(oidCompany);
        }
        catch(Exception e){
        }                        
        wb.println("      <Row>");
        wb.println("       <Cell ss:Index=\"8\" ss:StyleID=\"s74\"><Data ss:Type=\"String\">Printed : "+JSPFormater.formatDate(new Date(),formatDate)+"</Data></Cell>");
        wb.println("      </Row>");
        wb.println("      <Row ss:AutoFitHeight=\"0\" ss:Height=\"23.25\">");
        wb.println("       <Cell ss:Index=\"2\" ss:MergeAcross=\"6\" ss:StyleID=\"s23\"><Data ss:Type=\"String\" x:Ticked=\"1\">"+company.getName().toUpperCase()+"</Data></Cell>");
        wb.println("      </Row>");
        wb.println("      <Row ss:AutoFitHeight=\"0\" ss:Height=\"20.25\">");
        wb.println("       <Cell ss:Index=\"2\" ss:MergeAcross=\"6\" ss:StyleID=\"s25\"><Data ss:Type=\"String\">GENERAL LEDGER</Data></Cell>");
        wb.println("      </Row>");
        wb.println("      <Row ss:AutoFitHeight=\"0\" ss:Height=\"18\">");
        //Periode periode = DbPeriode.getOpenPeriod();
        //String openPeriod = JSPFormater.formatDate(periode.getStartDate(), "dd MMM yyyy")+ " - " + JSPFormater.formatDate(periode.getEndDate(), "dd MMM yyyy");
        //wb.println("       <Cell ss:Index=\"2\" ss:MergeAcross=\"6\" ss:StyleID=\"s27\"><Data ss:Type=\"String\">PERIOD "+ openPeriod.toUpperCase()+"</Data></Cell>");
        wb.println("       <Cell ss:Index=\"2\" ss:MergeAcross=\"6\" ss:StyleID=\"s27\"><Data ss:Type=\"String\">"+ strPeriod +"</Data></Cell>");        
        wb.println("      </Row>");

       
        //Content Header        
        wb.println("      <Row ss:AutoFitHeight=\"0\" ss:Height=\"15\"/>");        


        //content
        if(vectorList!=null && vectorList.size()>0){
            for(int i=0; i<vectorList.size(); i++){
                SesReportBs srb = (SesReportBs)vectorList.get(i);
            
                wb.println("      <Row>");
                wb.println("       <Cell ss:Index=\"2\" ss:MergeAcross=\"4\" ss:StyleID=\"m16943730\"><Data");
                wb.println("         ss:Type=\"String\">"+srb.getDescription()+"</Data></Cell>");
                wb.println("       <Cell ss:MergeAcross=\"1\" ss:StyleID=\"m16943740\"><Data ss:Type=\"String\">"+srb.getStrAmount()+"</Data></Cell>");
                wb.println("      </Row>");
                
                wb.println("      <Row ss:StyleID=\"s42\">");
                wb.println("       <Cell ss:Index=\"2\" ss:StyleID=\"s43\"/>");
                wb.println("       <Cell ss:StyleID=\"s44\"><Data ss:Type=\"String\">Transaction Date</Data></Cell>");
                wb.println("       <Cell ss:StyleID=\"s44\"><Data ss:Type=\"String\">Journal Number</Data></Cell>");
                wb.println("       <Cell ss:StyleID=\"s44\"><Data ss:Type=\"String\">Memo</Data></Cell>");
                wb.println("       <Cell ss:StyleID=\"s44\"><Data ss:Type=\"String\">Debet</Data></Cell>");
                wb.println("       <Cell ss:StyleID=\"s44\"><Data ss:Type=\"String\">Credit</Data></Cell>");
                wb.println("       <Cell ss:StyleID=\"s44\"><Data ss:Type=\"String\">Balance</Data></Cell>");
                wb.println("      </Row>");

                Vector listDetail = new Vector();
                listDetail = srb.getDepartment();
                if(listDetail!=null && listDetail.size()>0){
                    for(int i2=0; i2<listDetail.size(); i2++){
                        SesReportGl gld = (SesReportGl)listDetail.get(i2);
                        
                        if(!gld.getMemo().equals("Total")){
                            wb.println("      <Row>");
                            wb.println("       <Cell ss:Index=\"2\" ss:StyleID=\"s45\"/>");
                            wb.println("       <Cell ss:StyleID=\"s48\"><Data ss:Type=\"String\" x:Ticked=\"1\">"+gld.getStrTransDate()+"</Data></Cell>");
                            wb.println("       <Cell ss:StyleID=\"s48\"><Data ss:Type=\"String\" x:Ticked=\"1\">"+gld.getJournalNumber()+"</Data></Cell>");
                            wb.println("       <Cell ss:StyleID=\"s50\"><Data ss:Type=\"String\">"+gld.getMemo()+"</Data></Cell>");
                            wb.println("       <Cell ss:StyleID=\"s51\"><Data ss:Type=\"Number\">"+gld.getDebet()+"</Data></Cell>");
                            wb.println("       <Cell ss:StyleID=\"s51\"><Data ss:Type=\"Number\">"+gld.getCredit()+"</Data></Cell>");
                            wb.println("       <Cell ss:StyleID=\"s51\"><Data ss:Type=\"Number\">"+gld.getBalance()+"</Data></Cell>");
                            wb.println("      </Row>");
                        }                        
                        if(gld.getMemo().equals("Total")){
                            wb.println("      <Row>");
                            wb.println("       <Cell ss:Index=\"2\" ss:StyleID=\"s45\"/>");
                            wb.println("       <Cell ss:MergeAcross=\"2\" ss:StyleID=\"m16929468\"><Data ss:Type=\"String\">Total</Data></Cell>");
                            wb.println("       <Cell ss:StyleID=\"s59\"><Data ss:Type=\"Number\">"+gld.getDebet()+"</Data></Cell>");
                            wb.println("       <Cell ss:StyleID=\"s59\"><Data ss:Type=\"Number\">"+gld.getCredit()+"</Data></Cell>");
                            wb.println("       <Cell ss:StyleID=\"s59\"><Data ss:Type=\"Number\">"+gld.getBalance()+"</Data></Cell>");
                            wb.println("      </Row>");
                        }
                    }
                }

                wb.println("      <Row ss:AutoFitHeight=\"0\" ss:Height=\"5.25\" ss:StyleID=\"s60\">");
                wb.println("       <Cell ss:Index=\"2\" ss:StyleID=\"s61\"/>");
                wb.println("       <Cell ss:StyleID=\"s62\"/>");
                wb.println("       <Cell ss:StyleID=\"s62\"/>");
                wb.println("       <Cell ss:StyleID=\"s62\"/>");
                wb.println("       <Cell ss:StyleID=\"s63\"/>");
                wb.println("       <Cell ss:StyleID=\"s63\"/>");
                wb.println("       <Cell ss:StyleID=\"s63\"/>");
                wb.println("      </Row>");
            }
        }
        //footer
        wb.println("      <Row>");
        wb.println("       <Cell ss:Index=\"2\" ss:MergeAcross=\"6\" ss:StyleID=\"s71\"><Data ss:Type=\"String\"></Data></Cell>");
        wb.println("      </Row>");
        wb.println("      <Row>");
        wb.println("       <Cell ss:Index=\"2\" ss:MergeAcross=\"6\" ss:StyleID=\"s73\"><Data ss:Type=\"String\"></Data></Cell>");
        wb.println("      </Row>");
        wb.println("      <Row>");
        wb.println("       <Cell ss:Index=\"2\" ss:MergeAcross=\"6\" ss:StyleID=\"s73\"><Data ss:Type=\"String\">Date : _________________                                                                         Date : _________________                                                                         Date : _________________</Data></Cell>");
        wb.println("      </Row>");
        wb.println("      <Row>");
        wb.println("       <Cell ss:Index=\"2\" ss:MergeAcross=\"6\" ss:StyleID=\"s73\"><Data ss:Type=\"String\">Approve by                                                                                                   Review by                                                                                                      Prepare by</Data></Cell>");
        wb.println("      </Row>");
        wb.println("      <Row>");
        wb.println("       <Cell ss:Index=\"2\" ss:MergeAcross=\"6\" ss:StyleID=\"s73\"><Data ss:Type=\"String\"></Data></Cell>");
        wb.println("      </Row>");
        wb.println("      <Row>");
        wb.println("       <Cell ss:Index=\"2\" ss:MergeAcross=\"6\" ss:StyleID=\"s73\"><Data ss:Type=\"String\"></Data></Cell>");
        wb.println("      </Row>");
        wb.println("      <Row>");
        wb.println("       <Cell ss:Index=\"2\" ss:MergeAcross=\"6\" ss:StyleID=\"s73\"><Data ss:Type=\"String\"></Data></Cell>");
        wb.println("      </Row>");
        wb.println("      <Row>");
        wb.println("       <Cell ss:Index=\"2\" ss:MergeAcross=\"6\" ss:StyleID=\"s73\"><Data ss:Type=\"String\"></Data></Cell>");
        wb.println("      </Row>");
        wb.println("      <Row>");
        wb.println("       <Cell ss:Index=\"2\" ss:MergeAcross=\"6\" ss:StyleID=\"s73\"><Data ss:Type=\"String\">______________________                                                                          ______________________                                                                          ______________________</Data></Cell>");
        wb.println("      </Row>");
        
        
        wb.println("     </Table>");
        wb.println("     <WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
        wb.println("      <Print>");
        wb.println("       <ValidPrinterInfo/>");
        wb.println("       <PaperSizeIndex>9</PaperSizeIndex>");
        wb.println("       <HorizontalResolution>-4</HorizontalResolution>");
        wb.println("       <VerticalResolution>0</VerticalResolution>");
        wb.println("      </Print>");
        wb.println("      <Selected/>");
        wb.println("      <DoNotDisplayGridlines/>");
        wb.println("      <DoNotDisplayZeros/>");
        wb.println("      <FreezePanes/>");
        wb.println("      <FrozenNoSplit/>");
        wb.println("      <SplitHorizontal>5</SplitHorizontal>");
        wb.println("      <TopRowBottomPane>5</TopRowBottomPane>");
        wb.println("      <ActivePane>2</ActivePane>");
        wb.println("      <Panes>");
        wb.println("       <Pane>");
        wb.println("        <Number>3</Number>");
        wb.println("       </Pane>");
        wb.println("       <Pane>");
        wb.println("        <Number>2</Number>");
        wb.println("       </Pane>");
        wb.println("      </Panes>");
        wb.println("      <ProtectObjects>False</ProtectObjects>");
        wb.println("      <ProtectScenarios>False</ProtectScenarios>");
        wb.println("     </WorksheetOptions>");
        wb.println("    </Worksheet>");

        wb.println("   </Workbook>");
        
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
