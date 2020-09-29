

package com.project.coorp.pinjaman;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.project.util.jsp.*;
import com.project.general.*;
import com.project.util.*;

public class JspJenisPinjaman extends JSPHandler implements I_JSPInterface, I_JSPType
{
	private JenisPinjaman jenisPinjaman;

	public static final String JSP_NAME_JENIS_PINJAMAN		=  "JSP_NAME_JENIS_PINJAMAN" ;

	public static final int JSP_JENIS_PINJAMAN_ID			=  0 ;
	public static final int JSP_JENIS_PINJAMAN			=  1 ;

	public static String[] colNames = {
		"JSP_JENIS_PINJAMAN_ID",  "JSP_JENIS_PINJAMAN"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_STRING
	} ;

	public JspJenisPinjaman(){
	}
	public JspJenisPinjaman(JenisPinjaman jenisPinjaman){
		this.jenisPinjaman = jenisPinjaman;
	}

	public JspJenisPinjaman(HttpServletRequest request, JenisPinjaman jenisPinjaman){
		super(new JspJenisPinjaman(jenisPinjaman), request);
		this.jenisPinjaman = jenisPinjaman;
	}

	public String getFormName() { return JSP_NAME_JENIS_PINJAMAN; }

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; }

	public int getFieldSize() { return colNames.length; }

	public JenisPinjaman getEntityObject(){ return jenisPinjaman; }

	public void requestEntityObject(JenisPinjaman jenisPinjaman) {
		try{
			this.requestParam();
			jenisPinjaman.setJenisPinjaman(getString(JSP_JENIS_PINJAMAN));

		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
