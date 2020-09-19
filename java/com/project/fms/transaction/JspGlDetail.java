/* 
 * Form Name  	:  JspGlDetail.java 
 * Created on 	:  [date] [time] AM/PM 
 * 
 * @author  	:  [authorName] 
 * @version  	:  [version] 
 */

/*******************************************************************
 * Class Description 	: [project description ... ] 
 * Imput Parameters 	: [input parameter ...] 
 * Output 		: [output ...] 
 *******************************************************************/

package com.project.fms.transaction;

/* java package */ 
import java.io.*; 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
/* qdep package */ 
import com.project.util.jsp.*;
import com.project.fms.transaction.*;
import com.project.util.*;

public class JspGlDetail extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private GlDetail glDetail;

	public static final String JSP_NAME_GLDETAIL		=  "JSP_NAME_GLDETAIL" ;

	public static final int JSP_GL_ID			=  0 ;
	public static final int JSP_GL_DETAIL_ID			=  1 ;
	public static final int JSP_COA_ID			=  2 ;
	public static final int JSP_DEBET			=  3 ;
	public static final int JSP_CREDIT			=  4 ;
	public static final int JSP_FOREIGN_CURRENCY_ID			=  5 ;
	public static final int JSP_FOREIGN_CURRENCY_AMOUNT			=  6 ;
	public static final int JSP_MEMO			=  7 ;
	public static final int JSP_BOOKED_RATE			=  8 ;
        public static final int JSP_IS_DEBET			=  9 ;
        public static final int JSP_DEPARTMENT_ID   		=  10 ;

	public static String[] colNames = {
		"detailJSP_GL_ID",  "detailJSP_GL_DETAIL_ID",
		"detailJSP_COA_ID",  "detailJSP_DEBET",
		"detailJSP_CREDIT",  "detailJSP_FOREIGN_CURRENCY_ID",
		"detailJSP_FOREIGN_CURRENCY_AMOUNT",  "detailJSP_MEMO",
		"detailJSP_BOOKED_RATE", "JSP_IS_DEBET",
                "detailDEPARTMENT_ID"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_LONG,
		TYPE_LONG + ENTRY_REQUIRED,  TYPE_FLOAT,
		TYPE_FLOAT,  TYPE_LONG,
		TYPE_FLOAT + ENTRY_REQUIRED,  TYPE_STRING,
		TYPE_FLOAT, TYPE_INT,
                TYPE_LONG
	} ;

	public JspGlDetail(){
	}
	public JspGlDetail(GlDetail glDetail){
		this.glDetail = glDetail;
	}

	public JspGlDetail(HttpServletRequest request, GlDetail glDetail){
		super(new JspGlDetail(glDetail), request);
		this.glDetail = glDetail;
	}

	public String getFormName() { return JSP_NAME_GLDETAIL; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public GlDetail getEntityObject(){ return glDetail; }

	public void requestEntityObject(GlDetail glDetail) {
		try{
			this.requestParam();
			glDetail.setGlId(getLong(JSP_GL_ID));
			glDetail.setCoaId(getLong(JSP_COA_ID));
			glDetail.setDebet(getDouble(JSP_DEBET));
			glDetail.setCredit(getDouble(JSP_CREDIT));
			glDetail.setForeignCurrencyId(getLong(JSP_FOREIGN_CURRENCY_ID));
			glDetail.setForeignCurrencyAmount(getDouble(JSP_FOREIGN_CURRENCY_AMOUNT));
			glDetail.setMemo(getString(JSP_MEMO));
			glDetail.setBookedRate(getDouble(JSP_BOOKED_RATE));
                        glDetail.setIsDebet(getInt(JSP_IS_DEBET));
                        glDetail.setDepartmentId(getLong(JSP_DEPARTMENT_ID));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
