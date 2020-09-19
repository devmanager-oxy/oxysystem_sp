
package com.project.fms.activity;

import java.io.*; 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.jsp.*;
import com.project.general.*;
import com.project.util.*;

public class JspModule extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private Module module;

	public static final String JSP_NAME_MODULE		=  "module" ;

	public static final int JSP_MODULE_ID			=  0 ;
	public static final int JSP_PARENT_ID			=  1 ;
	public static final int JSP_CODE			=  2 ;
	public static final int JSP_LEVEL			=  3 ;
	public static final int JSP_DESCRIPTION			=  4 ;
	public static final int JSP_OUTPUT_DELIVER			=  5 ;
	public static final int JSP_PERFORM_INDICATOR			=  6 ;
	public static final int JSP_ASSUM_RISK			=  7 ;
	public static final int JSP_STATUS			=  8 ;
	public static final int JSP_TYPE			=  9 ;
        public static final int JSP_COST_IMPLICATION            = 10;
        
        public static final int JSP_PARENT_ID_M            = 11;
        public static final int JSP_PARENT_ID_S            = 12;
        public static final int JSP_PARENT_ID_SH            = 13;
        public static final int JSP_PARENT_ID_A            = 14;
        public static final int JSP_STATUS_POST            = 15;
        
        public static final int JSP_INITIAL           = 16;
        public static final int JSP_EXPIRED_DATE            = 17;
        public static final int JSP_POSITION_LEVEL            = 18;
        public static final int JSP_ACTIVITY_PERIOD_ID            = 19;
        
        public static final int JSP_PARENT_ID_SA            = 20;


	public static String[] colNames = {
		"x_module_id",  "x_parent_id",
		"x_code",  "x_level",
		"x_description",  "x_output_deliver",
		"x_perform_indicator",  "x_assum_risk",
		"x_status",  "x_type",
                "x_cimp",
                
                "M", "S", "SH", "A", "postab",
                
                "JSP_INITIAL", "JSP_EXPIRED_DATE",
                "JSP_POSITION_LEVEL", "JSP_ACTIVITY_PERIOD_ID",
                
                "SA"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_LONG,
		TYPE_STRING + ENTRY_REQUIRED,  TYPE_STRING + ENTRY_REQUIRED,
		TYPE_STRING,  TYPE_STRING,
		TYPE_STRING,  TYPE_STRING,
		TYPE_STRING,  TYPE_STRING,
                TYPE_STRING,
                TYPE_LONG, TYPE_LONG, TYPE_LONG, TYPE_LONG, TYPE_STRING,
                
                TYPE_STRING, TYPE_STRING,
                TYPE_STRING, TYPE_LONG,
                
                TYPE_LONG
	} ;

	public JspModule(){
	}
	public JspModule(Module module){
		this.module = module;
	}

	public JspModule(HttpServletRequest request, Module module){
		super(new JspModule(module), request);
		this.module = module;
	}

	public String getFormName() { return JSP_NAME_MODULE; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public Module getEntityObject(){ return module; }

	public void requestEntityObject(Module module) {
		try{
			this.requestParam();
			module.setParentId(getLong(JSP_PARENT_ID));
			module.setCode(getString(JSP_CODE));
			module.setLevel(getString(JSP_LEVEL));
			module.setDescription(getString(JSP_DESCRIPTION));
			module.setOutputDeliver(getString(JSP_OUTPUT_DELIVER));
			module.setPerformIndicator(getString(JSP_PERFORM_INDICATOR));
			module.setAssumRisk(getString(JSP_ASSUM_RISK));
			module.setStatus(getString(JSP_STATUS));
			module.setType(getString(JSP_TYPE));
                        module.setCostImplication(getString(JSP_COST_IMPLICATION));
                        
                        module.setParentIdM(getLong(JSP_PARENT_ID_M));
                        module.setParentIdS(getLong(JSP_PARENT_ID_S));
                        module.setParentIdSH(getLong(JSP_PARENT_ID_SH));
                        module.setParentIdA(getLong(JSP_PARENT_ID_A));
                        module.setStatusPost(getString(JSP_STATUS_POST));                        
                        
                        module.setInitial(getString(JSP_INITIAL));                        
                        module.setExpiredDate(JSPFormater.formatDate(getString(JSP_EXPIRED_DATE), "dd/MM/yyyy"));                        
                        module.setPositionLevel(getString(JSP_POSITION_LEVEL));                        
                        module.setActivityPeriodId(getLong(JSP_ACTIVITY_PERIOD_ID));                        
                        
                        module.setParentIdSA(getLong(JSP_PARENT_ID_SA));
                        
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
