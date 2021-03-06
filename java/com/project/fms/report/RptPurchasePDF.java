/*
 * RptPurchasePDF.java
 *
 * Created on December 21, 2007, 11:28 AM
 */

package com.project.fms.report;

import com.lowagie.text.*;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Document;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.Vector;
import java.util.Date;
import java.net.MalformedURLException;
import java.net.URL;

import com.lowagie.text.Font;
import com.project.util.jsp.*;
import com.project.fms.master.*;
import com.project.fms.journal.*;
import com.project.fms.transaction.*;
import com.project.payroll.*;
import com.project.util.*;
import com.project.general.Currency;
import com.project.general.DbCurrency;
import com.project.general.DbVendor;
import com.project.general.Vendor;
import com.project.system.*;

import com.project.general.Company;
import com.project.general.DbCompany;


/**
 *
 * @author  Suarjaya
 */
public class RptPurchasePDF extends HttpServlet {
    
    /** Creates a new instance of RptSampleFlatPDF */
    public RptPurchasePDF() {
    }
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    /** Destroys the servlet.
     */
    public void destroy() {
        
    }
    
    // setting session
    public static String SESS_PRINT_OPNAME = "SESS_PRINT_OPNAME";
    // setting the color values
    /*        
        public static Color blackColor = new Color(170, 170, 170);
        public static Color whiteColor = new Color(255, 255, 255);
        public static Color selectedColor = new Color(102,102,153);
        public static Color titleColor = new Color(58,149,97);    
        public static Color headerColor = new Color(108,163,90);
        public static Color contentColor = new Color(198,220,190);
    */
    public static Color border = new Color(0x00, 0x00, 0x00);    
    public static Color blackColor = new Color(0, 0, 0);
    public static Color whiteColor = new Color(255, 255, 255);
    public static Color titleColor = new Color(0, 0, 0);    
    public static Color headerColor = new Color(232, 232, 238);
    public static Color contentColor = new Color(255, 255, 255);
    public static String formatDate = "dd MMMM yyyy";
    public static String formatNumber = "#,###";
       
    // Header Fonts
    public static Font fontCompany = FontFactory.getFont(FontFactory.HELVETICA,14,Font.BOLD,blackColor);
    public static Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD,titleColor);
    public static Font fontAddress = FontFactory.getFont(FontFactory.HELVETICA,10,Font.NORMAL,blackColor);
    public static Font fontContact = FontFactory.getFont(FontFactory.HELVETICA,12,Font.NORMAL,blackColor);
    public static Font fontDate = FontFactory.getFont(FontFactory.HELVETICA,8,Font.NORMAL,blackColor);      
    public static Font fontDate1 = FontFactory.getFont(FontFactory.HELVETICA,9,Font.BOLD,blackColor);      
    public static Font fontDate2 = FontFactory.getFont(FontFactory.HELVETICA,10,Font.BOLD,blackColor);      
    public static Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD,blackColor); 
    public static Font fontContent = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL,Color.BLACK); 
    public static Font fontContentBold = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD,Color.BLACK); 
            
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        
        Rectangle rectangle = new Rectangle(20, 20, 20, 20);
        rectangle.rotate();
        Document document = new Document(PageSize.A4, 20, 20, 20, 20);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, baos);
                        
            // Add Header/Footer
            HeaderFooter footer = new HeaderFooter(new Phrase("Date : _________________                                         Date : _________________                                         Date : _________________\n            Approve by                                                                   Review by                                                                     Prepare by\n\n\n\n______________________                                          ______________________                                          ______________________", fontDate1), false);
            //HeaderFooter footer = new HeaderFooter(new Phrase("          Date : _________________                                                                     Date : _________________\n    Finance Manager                                                                                         Employee\n\n\n\n        _____________________                                                                       _____________________", fontDate1), false);
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setBorder(Rectangle.BOTTOM);
            footer.setBorderColor(whiteColor);
            document.setFooter(footer);
                        
            document.open();
            
            // Load Company
            Company company = DbCompany.getCompany();
            long oidCompany = company.getOID();           
            System.out.println("oidCompany : "+oidCompany);
            
            // Load User Login
            String loginId = JSPRequestValue.requestString(request, "oid");
            System.out.println("UserId : "+loginId);
            
            // Load Purchase
            String PoId = JSPRequestValue.requestString(request, "po_id");
            long poId = Long.parseLong(PoId);
            Purchase po = DbPurchase.fetchExc(poId);
            System.out.println("POId : "+PoId);
                        
            // Load Purchase Detail
            Vector vectorList = DbPurchaseItem.list(0, DbPurchaseItem.getCount("")," purchase_id = "+poId,"");

            // Load Path Images           
            String pathImage = DbSystemProperty.getValueByName("IMAGE_PRINT_PATH")+"\\report_logo.jpg";
            System.out.println("PATH IMAGE = "+pathImage);
            
            // Load Structure                      
            document.add(getTableHeader(oidCompany, pathImage, writer, document, vectorList, po));                          
            document.add(getTableHeaderContent1(writer, document, po));                                            
            Table tblContent = getTableHeaderContent(writer, document);            
            getTableContent(tblContent, writer, document, vectorList, po);
                                                
        } catch (Exception e) {
            System.out.println("Exception Draw pdf : " + e.toString());
        }
        
        // step 5: closing the document
        document.close();
        
        // we have written the pdfstream to a ByteArrayOutputStream,
        // now we are going to write this outputStream to the ServletOutputStream
        // after we have set the contentlength (see http://www.lowagie.com/iText/faq.html#msie)
        response.setContentType("application/pdf");
        response.setContentLength(baos.size());
        ServletOutputStream out = response.getOutputStream();
        baos.writeTo(out);
        out.flush();
    }
    
    public static Table getTableSpace(long poId, Vector vectorList) throws BadElementException, DocumentException {        
        Table tableSpace = new Table(7);     
        
        Purchase po = new Purchase();
        try{	
            po = DbPurchase.fetchExc(poId);
        }
        catch(Exception e){} 
       
        int widthHead[] = {13,13,30,8,15,15,15};
        tableSpace.setWidths(widthHead);
        tableSpace.setWidth(100);
        tableSpace.setBorderColor(whiteColor);
        tableSpace.setCellpadding(0);
        tableSpace.setCellspacing(1);
        
         /*Add header row 0*/
        Cell cellSpace = new Cell(new Chunk("",fontDate));
        cellSpace.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellSpace.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellSpace.setBorderColor(blackColor);
        cellSpace.setBorder(Cell.TOP);
        cellSpace.setBackgroundColor(Color.WHITE);        
        cellSpace.setColspan(7);
        tableSpace.addCell(cellSpace);
        
        /*Add header row 1 Col 1*/
        cellSpace = new Cell(new Chunk("TOTAL",fontHeader));
        cellSpace.setHorizontalAlignment(Cell.ALIGN_RIGHT);
        cellSpace.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellSpace.setBorderColor(whiteColor);
        //cellSpace.setBorder(Cell.TOP);
        cellSpace.setBackgroundColor(Color.WHITE);
        cellSpace.setColspan(5);
        tableSpace.addCell(cellSpace);

        /*Add header row 1 Col 2*/       
        cellSpace = new Cell(new Chunk("",fontHeader));        
        cellSpace.setHorizontalAlignment(Cell.ALIGN_RIGHT);
        cellSpace.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellSpace.setBorderColor(headerColor);
        //cellSpace.setBorder(Cell.TOP);
        cellSpace.setBackgroundColor(headerColor);
        tableSpace.addCell(cellSpace);

        /*Add header row 1 Col 3*/       
        cellSpace = new Cell(new Chunk(JSPFormater.formatNumber(po.getTotal(), "#,###.##"),fontHeader));        
        cellSpace.setHorizontalAlignment(Cell.ALIGN_RIGHT);
        cellSpace.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellSpace.setBorderColor(headerColor);
        //cellSpace.setBorder(Cell.TOP);
        cellSpace.setBackgroundColor(headerColor);
        tableSpace.addCell(cellSpace);
        
        /*Add header row 2 Col 1*/
        cellSpace = new Cell(new Chunk("DISCOUNT",fontHeader));
        cellSpace.setHorizontalAlignment(Cell.ALIGN_RIGHT);
        cellSpace.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellSpace.setBorderColor(whiteColor);
        //cellSpace.setBorder(Cell.TOP);
        cellSpace.setBackgroundColor(Color.WHITE);
        cellSpace.setColspan(5);
        tableSpace.addCell(cellSpace);

        /*Add header row 2 Col 2*/       
        cellSpace = new Cell(new Chunk(JSPFormater.formatNumber(po.getDiscountPercent(), "#,###.##") + " %",fontHeader));        
        cellSpace.setHorizontalAlignment(Cell.ALIGN_RIGHT);
        cellSpace.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellSpace.setBorderColor(headerColor);
        //cellSpace.setBorder(Cell.TOP);
        cellSpace.setBackgroundColor(headerColor);
        tableSpace.addCell(cellSpace);
        
        /*Add header row 2 Col 3*/       
        cellSpace = new Cell(new Chunk(JSPFormater.formatNumber(po.getDiscount(), "#,###.##"),fontHeader));        
        cellSpace.setHorizontalAlignment(Cell.ALIGN_RIGHT);
        cellSpace.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellSpace.setBorderColor(headerColor);
        //cellSpace.setBorder(Cell.TOP);
        cellSpace.setBackgroundColor(headerColor);
        tableSpace.addCell(cellSpace);

        /*Add header row 3 Col 1*/
        cellSpace = new Cell(new Chunk("VAT",fontHeader));
        cellSpace.setHorizontalAlignment(Cell.ALIGN_RIGHT);
        cellSpace.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellSpace.setBorderColor(whiteColor);
        //cellSpace.setBorder(Cell.TOP);
        cellSpace.setBackgroundColor(Color.WHITE);
        cellSpace.setColspan(5);
        tableSpace.addCell(cellSpace);

        /*Add header row 3 Col 2*/       
        cellSpace = new Cell(new Chunk(JSPFormater.formatNumber(po.getVatPercent(), "#,###.##") + " %",fontHeader));        
        cellSpace.setHorizontalAlignment(Cell.ALIGN_RIGHT);
        cellSpace.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellSpace.setBorderColor(headerColor);
        //cellSpace.setBorder(Cell.TOP);
        cellSpace.setBackgroundColor(headerColor);
        tableSpace.addCell(cellSpace);
        
        /*Add header row 3 Col 3*/       
        cellSpace = new Cell(new Chunk(JSPFormater.formatNumber(po.getVatAmount(), "#,###.##"),fontHeader));        
        cellSpace.setHorizontalAlignment(Cell.ALIGN_RIGHT);
        cellSpace.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellSpace.setBorderColor(headerColor);
        //cellSpace.setBorder(Cell.TOP);
        cellSpace.setBackgroundColor(headerColor);
        tableSpace.addCell(cellSpace);

        /*Add header row 4 Col 1*/
        cellSpace = new Cell(new Chunk("GRAND TOTAL",fontHeader));
        cellSpace.setHorizontalAlignment(Cell.ALIGN_RIGHT);
        cellSpace.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellSpace.setBorderColor(whiteColor);
        //cellSpace.setBorder(Cell.TOP);
        cellSpace.setBackgroundColor(Color.WHITE);
        cellSpace.setColspan(5);
        tableSpace.addCell(cellSpace);

        /*Add header row 4 Col 2*/       
        cellSpace = new Cell(new Chunk("",fontHeader));        
        cellSpace.setHorizontalAlignment(Cell.ALIGN_RIGHT);
        cellSpace.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellSpace.setBorderColor(headerColor);
        //cellSpace.setBorder(Cell.TOP);
        cellSpace.setBackgroundColor(headerColor);
        tableSpace.addCell(cellSpace);
 
        /*Add header row 4 Col 3*/       
        cellSpace = new Cell(new Chunk(JSPFormater.formatNumber((po.getTotal()-po.getDiscount()+po.getVatAmount()), "#,###.##"),fontHeader));        
        cellSpace.setHorizontalAlignment(Cell.ALIGN_RIGHT);
        cellSpace.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellSpace.setBorderColor(headerColor);
        //cellSpace.setBorder(Cell.TOP);
        cellSpace.setBackgroundColor(headerColor);
        tableSpace.addCell(cellSpace);

        return tableSpace;        
     }
    
    public static Table getTableFooter(long companyOID, String pathImage, PdfWriter writer, Document document, String loginId) throws BadElementException, DocumentException {
        Table tableFooter = new Table(3);      
        
        int widthHead[] = {20,5,20};
        tableFooter.setWidths(widthHead);
        tableFooter.setWidth(100);
        tableFooter.setBorderColor(whiteColor);
        tableFooter.setCellpadding(0);
        tableFooter.setCellspacing(2);
                     
        /*Add header row 1 Col 1*/
        Cell cellFooter = new Cell(new Chunk("Finance Manager",fontDate2));
        cellFooter.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellFooter.setVerticalAlignment(Cell.ALIGN_TOP);
        cellFooter.setBorderColor(whiteColor);
        cellFooter.setBackgroundColor(Color.WHITE);        
        tableFooter.addCell(cellFooter);

        /*Add header row 1 Col 2*/
        cellFooter = new Cell(new Chunk("",fontDate2));
        cellFooter.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellFooter.setVerticalAlignment(Cell.ALIGN_TOP);
        cellFooter.setBorderColor(whiteColor);
        cellFooter.setBackgroundColor(Color.WHITE);
        tableFooter.addCell(cellFooter);
        
        /*Add header row 1 Col 3*/
        cellFooter = new Cell(new Chunk("Clerk",fontDate2));
        cellFooter.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellFooter.setVerticalAlignment(Cell.ALIGN_TOP);
        cellFooter.setBorderColor(whiteColor);
        cellFooter.setBackgroundColor(Color.WHITE);
        tableFooter.addCell(cellFooter);
        
        /*Add header row 2*/
        cellFooter = new Cell(new Chunk("",fontDate2));
        cellFooter.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellFooter.setVerticalAlignment(Cell.ALIGN_TOP);
        cellFooter.setBorderColor(whiteColor);
        cellFooter.setBackgroundColor(Color.WHITE);
        cellFooter.setRowspan(6);        
        cellFooter.setColspan(3);
        tableFooter.addCell(cellFooter);
        
        /*Add header row 3 Col 1*/
        cellFooter = new Cell(new Chunk("(                          )",fontDate2));
        cellFooter.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellFooter.setVerticalAlignment(Cell.ALIGN_TOP);
        cellFooter.setBorderColor(whiteColor);
        cellFooter.setBackgroundColor(Color.WHITE);
        tableFooter.addCell(cellFooter);
        
        /*Add header row 3 Col 2*/
        cellFooter = new Cell(new Chunk("",fontDate2));
        cellFooter.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellFooter.setVerticalAlignment(Cell.ALIGN_TOP);
        cellFooter.setBorderColor(whiteColor);
        cellFooter.setBackgroundColor(Color.WHITE);
        tableFooter.addCell(cellFooter);
        
        /*Add header row 3 Col 3*/        
        cellFooter = new Cell(new Chunk("(                          )",fontDate2));
        cellFooter.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellFooter.setVerticalAlignment(Cell.ALIGN_TOP);
        cellFooter.setBorderColor(whiteColor);
        cellFooter.setBackgroundColor(Color.WHITE);
        tableFooter.addCell(cellFooter);
        
        return tableFooter;
    }
    
    public static Table getTableHeader(long companyOID, String pathImage, PdfWriter writer, Document document, Vector vectorList, Purchase po) throws BadElementException, DocumentException {         
        Table tableHeader = new Table(3);             
        int widthHead[] = {20,55,25};
        tableHeader.setWidths(widthHead);
        tableHeader.setWidth(100);
        tableHeader.setBorderColor(whiteColor);
        tableHeader.setCellpadding(0);
        tableHeader.setCellspacing(2);
        
        Company company = new Company();
        try{
            company = DbCompany.fetchExc(companyOID);
        }
        catch(Exception e){
        }
          
        //fontLsHeader
        /*Add header row 1 Col 1*/        
        Image gambar = null;
        try {
            gambar = Image.getInstance(pathImage);
        } 
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
       
        Cell cellHeader = new Cell(new Chunk("",fontCompany));
        cellHeader.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeader.setVerticalAlignment(Cell.ALIGN_TOP);
        cellHeader.setBorderColor(whiteColor);
        cellHeader.setBackgroundColor(Color.WHITE);
        cellHeader.setRowspan(3);
        cellHeader.add(gambar);
        tableHeader.addCell(cellHeader);

        /*Add header row 1 Col 2*/
        cellHeader = new Cell(new Chunk(company.getName().toUpperCase(),fontCompany));
        cellHeader.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellHeader.setVerticalAlignment(Cell.ALIGN_TOP);
        cellHeader.setBorderColor(whiteColor);
        cellHeader.setBackgroundColor(Color.WHITE);
        cellHeader.setColspan(1);
        tableHeader.addCell(cellHeader);

        /*Add header row 1 Col 3*/
        cellHeader = new Cell(new Chunk("Printed : "+ JSPFormater.formatDate(new Date(), formatDate),fontDate));
        cellHeader.setHorizontalAlignment(Cell.ALIGN_RIGHT);
        cellHeader.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeader.setBorderColor(whiteColor);
        cellHeader.setBackgroundColor(Color.WHITE);
        cellHeader.setColspan(1);
        tableHeader.addCell(cellHeader);
      
        /*Add header row 2 Col 1*/        
        cellHeader = new Cell(new Chunk("PURCHASE ORDER", fontTitle));
        cellHeader.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellHeader.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeader.setBorderColor(whiteColor);
        cellHeader.setBackgroundColor(Color.WHITE);
        cellHeader.setColspan(1);
        tableHeader.addCell(cellHeader);

        /*Add header row 2 Col 2*/        
        cellHeader = new Cell(new Chunk("", fontCompany));
        cellHeader.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeader.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeader.setBorderColor(whiteColor);
        cellHeader.setBackgroundColor(Color.WHITE);
        cellHeader.setColspan(1);
        tableHeader.addCell(cellHeader);       
        
        /*Add header row 3 Col 1*/      
        Periode periode = DbPeriode.getPeriodByTransDate(po.getTransDate());
        String openPeriod = JSPFormater.formatDate(periode.getStartDate(), "dd MMM yyyy")+ " - " + JSPFormater.formatDate(periode.getEndDate(), "dd MMM yyyy");
        cellHeader = new Cell(new Chunk("PERIOD "+ openPeriod.toUpperCase(), fontContact));
        cellHeader.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellHeader.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeader.setBorderColor(whiteColor);
        cellHeader.setBackgroundColor(Color.WHITE);
        cellHeader.setColspan(1);
        tableHeader.addCell(cellHeader);

        /*Add header row 3 Col 2*/        
        cellHeader = new Cell(new Chunk("",fontCompany));
        cellHeader.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeader.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeader.setBorderColor(whiteColor);
        cellHeader.setBackgroundColor(Color.WHITE);
        cellHeader.setColspan(1);
        tableHeader.addCell(cellHeader);              

        return tableHeader;        
    }
    
    public static Table getTableHeaderContent(PdfWriter writer, Document document) throws BadElementException, DocumentException {
        Table tableHeaderContent = new Table(8);      
               
        int widthHead[] = {14,9,18,18,5,13,10,13};
        tableHeaderContent.setWidths(widthHead);
        tableHeaderContent.setWidth(100);        
        tableHeaderContent.setBorderColor(whiteColor);
        tableHeaderContent.setCellpadding(1);
        tableHeaderContent.setCellspacing(1);                
     
        /*Add header row 1 Col 1*/
        Cell cellHeaderContent = new Cell(new Chunk("Item",fontHeader));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBackgroundColor(headerColor);               
        cellHeaderContent.setColspan(4);
        tableHeaderContent.addCell(cellHeaderContent);

        /*Add header row 1 Col 2*/
        /*cellHeaderContent = new Cell(new Chunk("Type",fontHeader));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBackgroundColor(headerColor);        
        tableHeaderContent.addCell(cellHeaderContent);
        
        cellHeaderContent = new Cell(new Chunk("Account - Description",fontHeader));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBackgroundColor(headerColor);        
        tableHeaderContent.addCell(cellHeaderContent);
        
        
        cellHeaderContent = new Cell(new Chunk("Department",fontHeader));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBackgroundColor(headerColor);        
        tableHeaderContent.addCell(cellHeaderContent);
         */
        
        
        cellHeaderContent = new Cell(new Chunk("Qty",fontHeader));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBackgroundColor(headerColor);
        tableHeaderContent.addCell(cellHeaderContent);       

        /*Add header row 1 Col 5*/
        cellHeaderContent = new Cell(new Chunk("@Price",fontHeader));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBackgroundColor(headerColor);             
        tableHeaderContent.addCell(cellHeaderContent);       
        
        /*Add header row 1 Col 6*/
        cellHeaderContent = new Cell(new Chunk("Discount",fontHeader));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBackgroundColor(headerColor);             
        tableHeaderContent.addCell(cellHeaderContent);       
        
        /*Add header row 1 Col 7*/
        cellHeaderContent = new Cell(new Chunk("Total",fontHeader));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBackgroundColor(headerColor);             
        tableHeaderContent.addCell(cellHeaderContent);       
               
        return tableHeaderContent;               
    }
    
    public static Table getTableHeaderContent1(PdfWriter writer, Document document, Purchase po) throws BadElementException, DocumentException {
        Table tableHeaderContent = new Table(5);                         
        int widthHead[] = {10,20,5,10,15};
        tableHeaderContent.setWidths(widthHead);
        tableHeaderContent.setWidth(100);  
        tableHeaderContent.setBorderColor(whiteColor);
        tableHeaderContent.setCellpadding(0);
        tableHeaderContent.setCellspacing(0);        

        /*Add header row 0 Col 1*/
        Cell cellHeaderContent = new Cell(new Chunk("Vendor",fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);
        tableHeaderContent.addCell(cellHeaderContent);

        /*Add header row 0 Col 2*/
        Vendor v = new Vendor();
        try{
            v = DbVendor.fetchExc(po.getVendorId());
        }
        catch(Exception e){}        
        cellHeaderContent = new Cell(new Chunk(v.getName(),fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);        
        tableHeaderContent.addCell(cellHeaderContent);
        
        /*Add header row 0 Col 3*/
        cellHeaderContent = new Cell(new Chunk("",fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);        
        tableHeaderContent.addCell(cellHeaderContent);
        
        /*Add header row 0 Col 4*/
        cellHeaderContent = new Cell(new Chunk("PO Status",fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);        
        tableHeaderContent.addCell(cellHeaderContent);
        
        /*Add header row 0 Col 5*/
        cellHeaderContent = new Cell(new Chunk(po.getStatus(),fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);        
        tableHeaderContent.addCell(cellHeaderContent);
        
       
        /*Add header row 00 Col 1*/
        cellHeaderContent = new Cell(new Chunk("",fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);            
        tableHeaderContent.addCell(cellHeaderContent);

        /*Add header row 00 Col 2*/
        cellHeaderContent = new Cell(new Chunk(v.getAddress(),fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);     
        cellHeaderContent.setColspan(4);
        tableHeaderContent.addCell(cellHeaderContent);
        
        /*Add header row 1 Col 1*/
        //cellHeaderContent = new Cell(new Chunk("PO Currency",fontContent));
        cellHeaderContent = new Cell(new Chunk("",fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);
        tableHeaderContent.addCell(cellHeaderContent);

        /*Add header row 1 Col 2*/
        Currency cx = new Currency();
        try{
            cx = DbCurrency.fetchExc(po.getCurrencyId());
        }
        catch(Exception e){}
        cellHeaderContent = new Cell(new Chunk("",fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);
        tableHeaderContent.addCell(cellHeaderContent);

        /*Add header row 1 Col 3*/
        cellHeaderContent = new Cell(new Chunk("",fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);     
        tableHeaderContent.addCell(cellHeaderContent); 
        
        /*Add header row 1 Col 4*/
        cellHeaderContent = new Cell(new Chunk("Purchase Number",fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);            
        tableHeaderContent.addCell(cellHeaderContent);

        /*Add header row 1 Col 5*/
        cellHeaderContent = new Cell(new Chunk(po.getJournalNumber(),fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);     
        tableHeaderContent.addCell(cellHeaderContent);
        
        /*Add header row 2 Col 1*/
        cellHeaderContent = new Cell(new Chunk("Deliver To",fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);
        tableHeaderContent.addCell(cellHeaderContent);

        /*Add header row 2 Col 2*/
        cellHeaderContent = new Cell(new Chunk((po.getShipAddress().length()<1) ? "-" : po.getShipAddress(),fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);
        tableHeaderContent.addCell(cellHeaderContent);

        /*Add header row 2 Col 3*/
        cellHeaderContent = new Cell(new Chunk("",fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);     
        tableHeaderContent.addCell(cellHeaderContent);

        /*Add header row 2 Col 4*/
        cellHeaderContent = new Cell(new Chunk("Transaction Date",fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);            
        tableHeaderContent.addCell(cellHeaderContent);

        /*Add header row 2 Col 5*/
        cellHeaderContent = new Cell(new Chunk(JSPFormater.formatDate(po.getTransDate(), "dd-MMM-yyyy"),fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);     
        tableHeaderContent.addCell(cellHeaderContent);
        
        /*Add header row 3 Col 1*/
        //cellHeaderContent = new Cell(new Chunk("Apply FAT",fontContent));
        cellHeaderContent = new Cell(new Chunk("PO Currency",fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);
        tableHeaderContent.addCell(cellHeaderContent);

        /*Add header row 3 Col 2*/
        //cellHeaderContent = new Cell(new Chunk((po.getVat()==1) ? "YES" :"NO",fontContent));
        cellHeaderContent = new Cell(new Chunk(cx.getCurrencyCode(),fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);
        tableHeaderContent.addCell(cellHeaderContent);

        /*Add header row 3 Col 3*/
        cellHeaderContent = new Cell(new Chunk("",fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);
        tableHeaderContent.addCell(cellHeaderContent);
        
        /*Add header row 3 Col 4*/
        cellHeaderContent = new Cell(new Chunk("Term Of Payment",fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);
        tableHeaderContent.addCell(cellHeaderContent);
        
        /*Add header row 3 Col 5*/
        TermOfPayment top = new TermOfPayment();
        try{
            top = DbTermOfPayment.fetchExc(po.getTermOfPaymentId());
        }
        catch(Exception e){}
        cellHeaderContent = new Cell(new Chunk(top.getDescription(),fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);
        tableHeaderContent.addCell(cellHeaderContent);
        
        /*Add header row 4 Col 1*/
        cellHeaderContent = new Cell(new Chunk("Memo",fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);
        tableHeaderContent.addCell(cellHeaderContent);
        
        /*Add header row 4 Col 2*/
        cellHeaderContent = new Cell(new Chunk(po.getMemo(),fontContent));
        cellHeaderContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
        cellHeaderContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
        cellHeaderContent.setBorderColor(Color.WHITE);
        cellHeaderContent.setBackgroundColor(contentColor);
        cellHeaderContent.setColspan(4);
        tableHeaderContent.addCell(cellHeaderContent);
        
        return tableHeaderContent;
    }
        
    public static void getTableContent(Table tableContent, PdfWriter writer, Document document, Vector vectorList, Purchase po) throws BadElementException, DocumentException {
        for(int i=0; i<vectorList.size(); i++){
            PurchaseItem poItem = (PurchaseItem)vectorList.get(i);

            /*Add header row 1 Col 1*/
            Cell cellContent = new Cell(new Chunk(poItem.getItemName(),fontContent));
            cellContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
            cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cellContent.setBackgroundColor(contentColor);
            cellContent.setColspan(4);
            tableContent.addCell(cellContent);
            
            /*Add header row 1 Col 2*/
            /*cellContent = new Cell(new Chunk(poItem.getItemType(),fontContent));
            cellContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
            cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cellContent.setBackgroundColor(contentColor);            
            tableContent.addCell(cellContent);
            
            
                Coa c = new Coa();
                try{	
                    c = DbCoa.fetchExc(poItem.getCoaId());
                }
                catch(Exception e){}                

            if (c.getStatus().trim().equalsIgnoreCase("Header"))
            {
                cellContent = new Cell(new Chunk(c.getCode()+" - "+c.getName(),fontContentBold));
                cellContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBackgroundColor(contentColor);                
                tableContent.addCell(cellContent);
             }
            else
            {
                cellContent = new Cell(new Chunk(c.getCode()+" - "+c.getName(),fontContent));
                cellContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBackgroundColor(contentColor);                
                tableContent.addCell(cellContent);            
            }

            
            Department dx = new Department();
            try{    
            dx = DbDepartment.fetchExc(poItem.getDepartmentId());
            }
            catch(Exception e){ System.out.println(e); }
            cellContent = new Cell(new Chunk((poItem.getDepartmentId()==0) ? "0.0.0.0 - Total Corporate" : dx.getCode()+" - "+dx.getName(),fontContent));
            cellContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
            cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cellContent.setBackgroundColor(contentColor);            
            tableContent.addCell(cellContent);
            */
            
            
            cellContent = new Cell(new Chunk(JSPFormater.formatNumber(poItem.getQty(), "#,###.##"),fontContent));
            cellContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
            cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cellContent.setBackgroundColor(contentColor);            
            tableContent.addCell(cellContent);
            
            /*Add header row 1 Col 5*/
            cellContent = new Cell(new Chunk(JSPFormater.formatNumber(poItem.getPrice(), "#,###.##"),fontContent));
            cellContent.setHorizontalAlignment(Cell.ALIGN_RIGHT);
            cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cellContent.setBackgroundColor(contentColor);            
            tableContent.addCell(cellContent);

            /*Add header row 1 Col 6*/
            cellContent = new Cell(new Chunk(JSPFormater.formatNumber(poItem.getDiscount(), "#,###.##"),fontContent));
            cellContent.setHorizontalAlignment(Cell.ALIGN_RIGHT);
            cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cellContent.setBackgroundColor(contentColor);            
            tableContent.addCell(cellContent);

            /*Add header row 1 Col 7*/
            cellContent = new Cell(new Chunk(JSPFormater.formatNumber(poItem.getTotalAmount(), "#,###.##"),fontContent));
            cellContent.setHorizontalAlignment(Cell.ALIGN_RIGHT);
            cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cellContent.setBackgroundColor(contentColor);            
            tableContent.addCell(cellContent);
                       
            if(!writer.fitsPage(tableContent)){
                i=i-2;
                tableContent.deleteLastRow();                
                tableContent.deleteLastRow();
                System.out.println("============TIDAK FIT============");
                document.add(tableContent);
                document.newPage();
                tableContent = getTableHeaderContent(writer,document);                                    
            }      
        }
        boolean endRows = false;
        int blankNum = 0;
        
        while(!endRows){
            /*Add header row 1 Col 1*/
            Cell cellContent = new Cell(new Chunk("",fontContent));
            cellContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
            cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cellContent.setBackgroundColor(contentColor);            
            cellContent.setColspan(4);
            tableContent.addCell(cellContent);
            
            /*
            cellContent = new Cell(new Chunk("",fontContent));
            cellContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
            cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cellContent.setBackgroundColor(contentColor);            
            tableContent.addCell(cellContent);
            
            
            cellContent = new Cell(new Chunk("",fontContent));
            cellContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
            cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cellContent.setBackgroundColor(contentColor);                
            tableContent.addCell(cellContent);            

            
            cellContent = new Cell(new Chunk("",fontContent));
            cellContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
            cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cellContent.setBackgroundColor(contentColor);            
            tableContent.addCell(cellContent);
            */
            
            /*Add header row 1 Col 4*/
            cellContent = new Cell(new Chunk("",fontContent));
            cellContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
            cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cellContent.setBackgroundColor(contentColor);            
            tableContent.addCell(cellContent);
            
            /*Add header row 1 Col 5*/
            cellContent = new Cell(new Chunk("",fontContent));
            cellContent.setHorizontalAlignment(Cell.ALIGN_RIGHT);
            cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cellContent.setBackgroundColor(contentColor);            
            tableContent.addCell(cellContent);

            /*Add header row 1 Col 6*/
            cellContent = new Cell(new Chunk("",fontContent));
            cellContent.setHorizontalAlignment(Cell.ALIGN_RIGHT);
            cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cellContent.setBackgroundColor(contentColor);            
            tableContent.addCell(cellContent);

            /*Add header row 1 Col 7*/
            cellContent = new Cell(new Chunk("",fontContent));
            cellContent.setHorizontalAlignment(Cell.ALIGN_RIGHT);
            cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cellContent.setBackgroundColor(contentColor);            
            tableContent.addCell(cellContent);
            
            blankNum = blankNum + 1;
            
            if(!writer.fitsPage(tableContent)){
                if(blankNum>3){
                    tableContent.deleteLastRow();                    
                    tableContent.deleteLastRow();                    
                    tableContent.deleteLastRow();                
                    tableContent.deleteLastRow();                
                    
                    tableContent.deleteLastRow();                
                    tableContent.deleteLastRow();                
                    tableContent.deleteLastRow();               
                }
                else{
                    tableContent.deleteLastRow();                
                }
                endRows = true;
            }
            
            if(endRows){
            
                cellContent = new Cell(new Chunk("",fontContent));            
                cellContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                cellContent.setBorder(Cell.TOP);
                cellContent.setBackgroundColor(contentColor);
                cellContent.setColspan(5);
                tableContent.addCell(cellContent);            

                /*Add header row 1 Col 5*/
                cellContent = new Cell(new Chunk("",fontContent));
                cellContent.setHorizontalAlignment(Cell.ALIGN_RIGHT);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                cellContent.setBorder(Cell.TOP);
                cellContent.setBackgroundColor(contentColor);            
                tableContent.addCell(cellContent);

                /*Add header row 1 Col 6*/
                cellContent = new Cell(new Chunk("",fontContent));
                cellContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                cellContent.setBorder(Cell.TOP);
                cellContent.setBackgroundColor(contentColor);  
                cellContent.setColspan(2);
                tableContent.addCell(cellContent);
                
                //-----------
                
                /*Add header row 1 Col 1*/
                cellContent = new Cell(new Chunk("",fontContent));            
                cellContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                cellContent.setBorder(Cell.RIGHT);
                cellContent.setBackgroundColor(contentColor);
                cellContent.setColspan(3);
                tableContent.addCell(cellContent);            

                /*Add header row 1 Col 2*/
                cellContent = new Cell(new Chunk("TOTAL",fontDate1));
                cellContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                //cellContent.setBorder(Cell.BOTTOM | Cell.TOP | Cell.RIGHT);
                cellContent.setColspan(2);
                cellContent.setBackgroundColor(headerColor);            
                tableContent.addCell(cellContent);

                /*Add header row 1 Col 3*/
                cellContent = new Cell(new Chunk(JSPFormater.formatNumber(po.getTotal(), "#,###.##"),fontDate1));
                cellContent.setHorizontalAlignment(Cell.ALIGN_RIGHT);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                //cellContent.setBorder(Cell.BOTTOM | Cell.TOP | Cell.RIGHT);
                cellContent.setBackgroundColor(headerColor);  
                cellContent.setColspan(3);
                tableContent.addCell(cellContent);
                
                /*Add header row 2 Col 1*/
                cellContent = new Cell(new Chunk("",fontContent));            
                cellContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                cellContent.setBorder(Cell.RIGHT);
                cellContent.setBackgroundColor(contentColor);
                cellContent.setColspan(3);
                tableContent.addCell(cellContent);            

                /*Add header row 2 Col 2*/
                cellContent = new Cell(new Chunk("DISCOUNT",fontDate1));
                cellContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                //cellContent.setBorder(Cell.BOTTOM | Cell.TOP | Cell.RIGHT);
                cellContent.setColspan(2);
                cellContent.setBackgroundColor(headerColor);            
                tableContent.addCell(cellContent);

                /*Add header row 2 Col 3*/
                cellContent = new Cell(new Chunk(JSPFormater.formatNumber(po.getDiscountPercent(), "#,###.##") + " %",fontDate1));
                cellContent.setHorizontalAlignment(Cell.ALIGN_RIGHT);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                //cellContent.setBorder(Cell.BOTTOM | Cell.TOP | Cell.RIGHT);
                cellContent.setBackgroundColor(headerColor);                  
                tableContent.addCell(cellContent);
                
                /*Add header row 2 Col 4*/
                cellContent = new Cell(new Chunk(JSPFormater.formatNumber(po.getDiscount(), "#,###.##"),fontDate1));
                cellContent.setHorizontalAlignment(Cell.ALIGN_RIGHT);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                //cellContent.setBorder(Cell.BOTTOM | Cell.TOP | Cell.RIGHT);
                cellContent.setBackgroundColor(headerColor);  
                cellContent.setColspan(2);
                tableContent.addCell(cellContent);
                
                /*Add header row 3 Col 1*/
                cellContent = new Cell(new Chunk("",fontContent));            
                cellContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                cellContent.setBorder(Cell.RIGHT);
                cellContent.setBackgroundColor(contentColor);
                cellContent.setColspan(3);
                tableContent.addCell(cellContent);            

                /*Add header row 3 Col 2*/
                cellContent = new Cell(new Chunk("VAT",fontDate1));
                cellContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                //cellContent.setBorder(Cell.BOTTOM | Cell.TOP | Cell.RIGHT);
                cellContent.setColspan(2);
                cellContent.setBackgroundColor(headerColor);            
                tableContent.addCell(cellContent);

                /*Add header row 3 Col 3*/
                cellContent = new Cell(new Chunk(JSPFormater.formatNumber(po.getVatPercent(), "#,###.##") + " %",fontDate1));
                cellContent.setHorizontalAlignment(Cell.ALIGN_RIGHT);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                //cellContent.setBorder(Cell.BOTTOM | Cell.TOP | Cell.RIGHT);
                cellContent.setBackgroundColor(headerColor);                  
                tableContent.addCell(cellContent);
                
                /*Add header row 3 Col 4*/
                cellContent = new Cell(new Chunk(JSPFormater.formatNumber(po.getVatAmount(), "#,###.##"),fontDate1));
                cellContent.setHorizontalAlignment(Cell.ALIGN_RIGHT);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                //cellContent.setBorder(Cell.BOTTOM | Cell.TOP | Cell.RIGHT);
                cellContent.setBackgroundColor(headerColor);  
                cellContent.setColspan(2);
                tableContent.addCell(cellContent);

                /*Add header row 4 Col 1*/
                cellContent = new Cell(new Chunk("",fontContent));            
                cellContent.setHorizontalAlignment(Cell.ALIGN_LEFT);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                cellContent.setBorder(Cell.RIGHT);
                cellContent.setBackgroundColor(contentColor);
                cellContent.setColspan(3);
                tableContent.addCell(cellContent);            

                /*Add header row 4 Col 2*/
                cellContent = new Cell(new Chunk("GRAND TOTAL",fontDate1));
                cellContent.setHorizontalAlignment(Cell.ALIGN_CENTER);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                //cellContent.setBorder(Cell.BOTTOM | Cell.TOP | Cell.RIGHT);
                cellContent.setColspan(2);
                cellContent.setBackgroundColor(headerColor);            
                tableContent.addCell(cellContent);

                /*Add header row 4 Col 3*/
                cellContent = new Cell(new Chunk(JSPFormater.formatNumber((po.getTotal()-po.getDiscount()+po.getVatAmount()), "#,###.##"),fontDate1));
                cellContent.setHorizontalAlignment(Cell.ALIGN_RIGHT);
                cellContent.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                cellContent.setBorderColor(Color.BLACK);
                //cellContent.setBorder(Cell.BOTTOM | Cell.TOP | Cell.RIGHT);
                cellContent.setBackgroundColor(headerColor);  
                cellContent.setColspan(3);
                tableContent.addCell(cellContent);

            }
            
        }
        document.add(tableContent);        
    }
     
     /** Handles the HTTP <code>GET</code> method.
     * @param request servle t request
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

    public static double getTotalDetail(Vector listx, int typex){
        double result = 0;
        if(listx!=null && listx.size()>0){
            for(int i=0; i<listx.size(); i++){
                GlDetail crd = (GlDetail)listx.get(i);
                //debet
                if(typex==0){
                    result = result + crd.getDebet();
                }
                //credit
                else{
                    result = result + crd.getCredit();
                }				
            }
        }
        return result;
    }
}
