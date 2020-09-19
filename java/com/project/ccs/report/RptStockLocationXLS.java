/*
 * Report Donor to IFC by Group XLS.java
 *
 * Created on March 30, 2008, 1:33 AM
 */

package com.project.ccs.report;

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
//import com.project.fms.master.*;
import com.project.payroll.*;
import com.project.util.jsp.*;
import com.project.fms.session.*;
import com.project.fms.activity.*;
import com.project.general.*;

public class RptStockLocationXLS extends HttpServlet {
    
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
        
        SrcStockReport rptKonstan = new SrcStockReport();
        try{
            HttpSession session = request.getSession();
            rptKonstan = (SrcStockReport)session.getValue("KONSTAN");
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
        wb.println("<LastAuthor>Victor</LastAuthor>");
        wb.println("<LastPrinted>2009-09-08T06:59:29Z</LastPrinted>");
        wb.println("<Created>1996-10-14T23:33:28Z</Created>");
        wb.println("<LastSaved>2009-09-08T08:06:42Z</LastSaved>");
        wb.println("<Version>11.5606</Version>");
        wb.println("</DocumentProperties>");
        wb.println("<ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">");
        wb.println("<WindowHeight>9300</WindowHeight>");
        wb.println("<WindowWidth>15135</WindowWidth>");
        wb.println("<WindowTopX>120</WindowTopX>");
        wb.println("<WindowTopY>120</WindowTopY>");
        wb.println("<AcceptLabelsInFormulas/>");
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
        wb.println("<Style ss:ID=\"s22\">");
        wb.println("<Alignment ss:Vertical=\"Bottom\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s25\">");
        wb.println("<Alignment ss:Vertical=\"Center\" ss:WrapText=\"1\"/>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s26\">");
        wb.println("<Alignment ss:Vertical=\"Bottom\"/>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s27\">");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s28\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("<Interior ss:Color=\"#CCFFCC\" ss:Pattern=\"Solid\"/>");
        wb.println("</Style>");
        
        wb.println("<Style ss:ID=\"s31\">");
        wb.println("<Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Bottom\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\"/>");
        wb.println("</Style>");
        
        wb.println("<Style ss:ID=\"s32\">");
        wb.println("<Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Bottom\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\"/>");
        wb.println("</Style>");
        
        wb.println("<Style ss:ID=\"s33\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\"/>");
        wb.println("</Style>");
        
        wb.println("<Style ss:ID=\"s36\">");
        wb.println("<Borders/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s40\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("</Style>");
        
        wb.println("<Style ss:ID=\"s41\">");
        wb.println("<Alignment ss:Vertical=\"Bottom\"/>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s50\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
        wb.println("<Font x:Family=\"Swiss\" ss:Bold=\"1\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s51\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"9\" ss:Bold=\"1\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s52\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\"/>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("</Style>");
        
        wb.println("<Style ss:ID=\"s61\">");
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
        
        wb.println("<Style ss:ID=\"s68\">");
        wb.println("<Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Bottom\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("<Interior ss:Color=\"#FFFF99\" ss:Pattern=\"Solid\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s70\">");
        wb.println("<Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Bottom\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("<Interior ss:Color=\"#FFFF99\" ss:Pattern=\"Solid\"/>");
        wb.println("<NumberFormat ss:Format=\"#,##0\"/>");
        wb.println("</Style>");
        wb.println("<Style ss:ID=\"s71\">");
        wb.println("<Alignment ss:Vertical=\"Bottom\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("<Interior ss:Color=\"#FFFF99\" ss:Pattern=\"Solid\"/>");
        wb.println("<NumberFormat ss:Format=\"#,##0\"/>");
        wb.println("</Style>");
        
        wb.println("<Style ss:ID=\"s72\">");
        wb.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\" ss:Bold=\"1\"/>");
        wb.println("<Interior ss:Color=\"#FFFF99\" ss:Pattern=\"Solid\"/>");
        wb.println("</Style>");
        
        wb.println("<Style ss:ID=\"s73\">");
        wb.println("<Alignment ss:Vertical=\"Bottom\"/>");
        wb.println("<Borders>");
        wb.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>");
        wb.println("</Borders>");
        wb.println("<Font x:Family=\"Swiss\" ss:Size=\"8\"/>");
        wb.println("</Style>");
        wb.println("</Styles>");
        wb.println("<Worksheet ss:Name=\"Sheet1\">");
        
        wb.println("<Table ss:ExpandedColumnCount=\"11\" x:FullColumns=\"1\"");
        wb.println("x:FullRows=\"1\">");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"4.5\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"18\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"66.75\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"171.75\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"35.25\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"81\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"82.5\"/>");
        wb.println("<Column ss:AutoFitWidth=\"0\" ss:Width=\"4.5\"/>");
        
        wb.println("<Row ss:Index=\"2\">");
        wb.println("<Cell ss:Index=\"2\" ss:MergeAcross=\"5\" ss:StyleID=\"s50\"><Data ss:Type=\"String\">STOCK REPORT BY LOCATION</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s22\"/>");
        wb.println("<Cell ss:StyleID=\"s22\"/>");
        wb.println("<Cell ss:StyleID=\"s22\"/>");
        wb.println("<Cell ss:StyleID=\"s22\"/>");
        wb.println("</Row>");
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:MergeAcross=\"5\" ss:StyleID=\"s51\"><Data ss:Type=\"String\">"+cmp.getName()+"</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s22\"/>");
        wb.println("<Cell ss:StyleID=\"s22\"/>");
        wb.println("<Cell ss:StyleID=\"s22\"/>");
        wb.println("<Cell ss:StyleID=\"s22\"/>");
        wb.println("</Row>");
        wb.println("<Row ss:AutoFitHeight=\"0\">");
        wb.println("<Cell ss:Index=\"2\" ss:MergeAcross=\"5\" ss:StyleID=\"s52\"><Data ss:Type=\"String\">"+cmp.getAddress()+"</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s25\"/>");
        wb.println("<Cell ss:StyleID=\"s25\"/>");
        wb.println("<Cell ss:StyleID=\"s25\"/>");
        wb.println("<Cell ss:StyleID=\"s25\"/>");
        wb.println("</Row>");
        
        wb.println("<Row ss:Index=\"6\">");
        wb.println("<Cell ss:Index=\"2\" ss:MergeAcross=\"1\" ss:StyleID=\"s26\"><Data ss:Type=\"String\">From </Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s26\"><Data ss:Type=\"String\">: "+rptKonstan.getLocation()+"</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("</Row>");
        
        wb.println("<Row ss:AutoFitHeight=\"0\" ss:Height=\"5.25\">");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("</Row>");
        
        wb.println("<Row ss:Index=\"9\">");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s28\"><Data ss:Type=\"String\">No</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s28\"><Data ss:Type=\"String\">Code</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s28\"><Data ss:Type=\"String\">Description</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s28\"><Data ss:Type=\"String\">Qty</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s28\"><Data ss:Type=\"String\" x:Ticked=\"1\">@ Prices</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s28\"><Data ss:Type=\"String\">Amount</Data></Cell>");
        wb.println("</Row>");
       
        double amount = 0.0;
        double ttAmount = 0.0;
        if(vDetail!=null && vDetail.size()>0){
            for(int i=0;i<vDetail.size();i++){
                SrcStockReportL detail = (SrcStockReportL)vDetail.get(i);
                
                wb.println("<Row>");
                wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s33\"><Data ss:Type=\"Number\">"+(i+1)+"</Data></Cell>");
                wb.println("<Cell ss:StyleID=\"s32\"><Data ss:Type=\"Number\">"+detail.getCode()+"</Data></Cell>");
                wb.println("<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\" x:Ticked=\"1\">"+detail.getDescription()+"</Data></Cell>");
                wb.println("<Cell ss:StyleID=\"s31\"><Data ss:Type=\"Number\">"+detail.getQty()+"</Data></Cell>");
                wb.println("<Cell ss:StyleID=\"s61\"><Data ss:Type=\"Number\">"+JSPFormater.formatNumber(detail.getPrice(), "##,##0.00")+"</Data></Cell>");
                
                amount = (detail.getQty()*detail.getPrice());
                ttAmount = ttAmount + amount;
                wb.println("<Cell ss:StyleID=\"s61\"><Data ss:Type=\"Number\">"+JSPFormater.formatNumber(amount, "##,##0.00")+"</Data></Cell>");
                wb.println("</Row>");
                
            }
        }
        
        wb.println("<Row ss:AutoFitHeight=\"0\" ss:Height=\"3.75\">");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s36\"/>");
        wb.println("<Cell ss:StyleID=\"s36\"/>");
        wb.println("<Cell ss:StyleID=\"s36\"/>");
        wb.println("<Cell ss:StyleID=\"s36\"/>");
        wb.println("<Cell ss:StyleID=\"s36\"/>");
        wb.println("<Cell ss:StyleID=\"s36\"/>");
        wb.println("</Row>");
        
        
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"6\" ss:StyleID=\"s68\"><Data ss:Type=\"String\">Total </Data></Cell>");
        //wb.println("<Cell ss:StyleID=\"s72\"><Data ss:Type=\"Number\">10</Data></Cell>");
        //wb.println("<Cell ss:StyleID=\"s70\"><Data ss:Type=\"Number\">1000000</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s71\"><Data ss:Type=\"Number\">"+JSPFormater.formatNumber(ttAmount, "##,##0.00")+"</Data></Cell>");
        wb.println("</Row>");
        
        
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("</Row>");
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("</Row>");
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("</Row>");
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:MergeAcross=\"1\" ss:StyleID=\"s40\"><Data ss:Type=\"String\">Prepared By</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s41\"/>");
        wb.println("<Cell ss:StyleID=\"s41\"/>");
        wb.println("<Cell ss:StyleID=\"s41\"/>");
        wb.println("<Cell ss:StyleID=\"s40\"><Data ss:Type=\"String\">Approvad By</Data></Cell>");
        wb.println("</Row>");
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("</Row>");
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("</Row>");
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("</Row>");
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:MergeAcross=\"1\" ss:StyleID=\"s40\"><Data ss:Type=\"String\">(..............................)</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s40\"><Data ss:Type=\"String\">(..............................)</Data></Cell>");
        wb.println("<Cell ss:StyleID=\"s26\"/>");
        wb.println("</Row>");
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("</Row>");
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("</Row>");
        wb.println("<Row>");
        wb.println("<Cell ss:Index=\"2\" ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s26\"/>");
        wb.println("<Cell ss:StyleID=\"s27\"/>");
        wb.println("</Row>");
        wb.println("</Table>");
        wb.println("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
        wb.println("<DisplayPageBreak/>");
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
        wb.println("<ActiveRow>5</ActiveRow>");
        wb.println("<ActiveCol>1</ActiveCol>");
        wb.println("<RangeSelection>R6C2:R6C3</RangeSelection>");
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
