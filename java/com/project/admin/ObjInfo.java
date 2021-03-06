

package com.project.admin;

import java.util.*;


public class ObjInfo {
    
    /** Creates new AppObjInfo */
    public ObjInfo() {
    }
    
    // filter code
    public static final int FILTER_CODE_G1  = 0xFF000000;
    public static final int FILTER_CODE_G2  = 0x00FF0000;
    public static final int FILTER_CODE_OBJ = 0x0000FF00;
    public static final int FILTER_CODE_CMD = 0x000000FF;
    
    public static final int SHIFT_CODE_G1   = 24;
    public static final int SHIFT_CODE_G2   = 16;
    public static final int SHIFT_CODE_OBJ  = 8;
    //public static final int SHIFT_CODE_CMD=0;
    
    //OBJECT COMMAND
    public static final int COMMAND_VIEW			= 0;
    public static final int COMMAND_ADD                         = 1;
    public static final int COMMAND_UPDATE			= 2;
    public static final int COMMAND_DELETE			= 3;
    public static final int COMMAND_PRINT			= 4;
    public static final int COMMAND_SUBMIT			= 5;
    public static final int COMMAND_START 			= 6;
    public static final int COMMAND_STOP 			= 7;
    public static final int COMMAND_ENTER			= 8;
    public static final int COMMAND_APPROVE			= 9;
    

    public static final String[] strCommand = 
        {"View", "Add New", "Update", "Delete","Print", "Submit","Start", "Stop", "Enter/In", "Approve" };
    
    // *** Application Structure ****** //
    
    public static final int G1_LOGIN  = 0;
    /**/public static final int G2_LOGIN   = 0;
    /****/public static final int OBJ_LOGIN_LOGIN   = 0;
    /****/public static final int OBJ_LOGIN_LOGOUT   = 1;    
    
    
    public static final int G1_ADMIN = 1;  
    
    /**/public static final int G2_ADMIN_USER   = 0;
    /****/public static final int OBJ_ADMIN_USER_PRIVILEGE   = 0;
    /****/public static final int OBJ_ADMIN_USER_GROUP       = 1;
    /****/public static final int OBJ_ADMIN_USER_USER        = 2;
    
    /**/public static final int G2_ADMIN_SYSTEM = 1;
    /****/public static final int OBJ_ADMIN_SYSTEM_BACK_UP   = 0;
    /****/public static final int OBJ_ADMIN_SYSTEM_APP_SET   = 1;
    /****/public static final int OBJ_ADMIN_SYSTEM_OBJ_LOCK  = 2;
    /****/public static final int OBJ_ADMIN_SYSTEM_MONITOR   = 3;
    
    //
    public static final int G1_RESERVATAION = 2;
    //
    /**/public static final int G2_DATA_RESERV          = 0;
    //
    /****/public static final int OBJ_D_RESERV_ROOM_BLOCKING            = 0;
    //for approval
    /****/public static final int OBJ_D_RESERV_ADJUSTMENT 		= 1;
    /****/public static final int OBJ_D_RESERV_COMMISION 		= 2;
    /****/public static final int OBJ_D_RESERV_BREAKFAST 		= 3;
    /****/public static final int OBJ_D_RESERV_TAKEOVER_TRAVEL          = 4;
    //for reservation
    /****/public static final int OBJ_D_RESERV_CHECK_IN 		= 5;
    /****/public static final int OBJ_D_RESERV_CHECK_OUT 		= 6;
    /****/public static final int OBJ_D_RESERV_TRANSFER 		= 7;
    /****/public static final int OBJ_D_RESERV_LIST		 	= 8;
    /****/public static final int OBJ_D_RESERV_ROOM_RATE		= 9;
    
    /**/public static final int G2_DATA_RESERV_CHARGE   = 1;
    //
    /****/public static final int OBJ_D_RESERV_TRAVEL_RATE_EDIT		= 0;
    /****/public static final int OBJ_D_RESERV_WALK_IN_RATE_EDIT	= 1;

    public static final int G1_CASHIER = 3;
    //
    /**/public static final int G2_DATA_CASHIER  = 0;
    //
    /****/public static final int OBJ_D_CASHIER_INVOICE  			= 0;
    /****/public static final int OBJ_D_CASHIER_BILLING_RECEIPT  		= 1;
    /****/public static final int OBJ_D_CASHIER_OUT_GUEST_BILL_RECEIPT  	= 2;
    /****/public static final int OBJ_D_CASHIER_OPERATOR_CALL			= 3;
    /****/public static final int OBJ_D_INVOICE_FOR_TAX				= 4;
    /****/public static final int OBJ_D_BILL_FOR_TAX				= 5;
    //
    /**/public static final int G2_DATA_OUTLET_SYSTEM = 1;
    //
    /****/public static final int OBJ_D_CASHIER_RUN 		= 0;
    /****/public static final int OBJ_D_CASHIER_BILLING 	= 1;
    /****/public static final int OBJ_D_CASHIER_CLOSE		= 2;
    /****/public static final int OBJ_D_WAITRESS_BILLING 	= 3;

    public static final int G1_HOUSE_KEEPING = 4;
    //
    /**/public static final int G2_DATA_HOUSE_KEEP  = 0;
    /****/public static final int OBJ_D_HOUSE_KEEP_ROOM_STATUS  = 0;
    /****/public static final int OBJ_D_HOUSE_KEEP_PROJECT		= 1;

    public static final int G1_MEMBERSHIP = 5;
    //
    /**/public static final int G2_DATA_MEMBERSHIP  = 0;
    /****/public static final int OBJ_D_MEMBERSHIP_DATA				= 0;
    /****/public static final int OBJ_D_INSTALLMENT  				= 1;
    /****/public static final int OBJ_D_MAINTENANCE_FEE  			= 2;
    /****/public static final int OBJ_D_EXCHANGE  				= 3;
    /****/public static final int OBJ_D_MEMBER_RCI  				= 4;
    /****/public static final int OBJ_D_ANNOUNCEMENT  				= 5;
    /****/public static final int OBJ_D_MEMBERSHIP_RESERVATION                  = 6;

    public static final int G1_PAYROLL = 6;
    //
    /**/public static final int G2_DATA_PAYROLL  = 0;
    /****/public static final int OBJ_D_EMP_SALARY  = 0;

    public static final int G1_DATA_MANAGEMENT = 7;
    //
    /**/public static final int G2_DATA_MANAG_HOTEL_DATA   = 0;
    /****/public static final int OBJ_D_MANAG_HOTEL_PROFILE  		= 0;
    /****/public static final int OBJ_D_MANAG_HOTEL_SURCH_COMPUL  	= 1;
    /****/public static final int OBJ_D_MANAG_HOTEL_ROOM  		= 2;
    /****/public static final int OBJ_D_MANAG_HOTEL_ROOM_CLASS  	= 3;
    /****/public static final int OBJ_D_MANAG_HOTEL_ROOM_NOT_AVB  	= 4;
    /****/public static final int OBJ_D_MANAG_HOTEL_CANCEL_PERIODE      = 5;
    /****/public static final int OBJ_D_MANAG_HOTEL_CANCEL_DETAIL  	= 6;
    //
    /**/public static final int G2_DATA_MANAG_GUESS_DATA   = 1;
    /****/public static final int OBJ_D_MANAG_GUESS  = 0;
    //
    /**/public static final int G2_DATA_MANAG_MEMBERSHIP   = 2;
	/****/public static final int OBJ_D_MANAG_MEMBER_ITEM  = 0;
    /****public static final int OBJ_D_MANAG_MEMBER_PACKAGE  = 0;
    /****public static final int OBJ_D_MANAG_MEMBER_BONUS_WEEK  = 1;*/
    //
    /**/public static final int G2_DATA_MANAG_PURCHASING  = 3;
    /****/public static final int OBJ_D_MANAG_PURCHASE_MATERIAL  = 0;
    /****public static final int OBJ_D_MANAG_PURCHASE_CATALOG  = 0;
    /****public static final int OBJ_D_MANAG_PURCHASE_INVENTORY_GRP  = 1;*/
    //
    /**/public static final int G2_DATA_MANAG_PAYROLL  = 4;
    /****/public static final int OBJ_D_MANAG_PAYROLL_EMPLOYEE  		= 0;
    /****/public static final int OBJ_D_MANAG_PAYROLL_RELIGION  		= 1;
    /****/public static final int OBJ_D_MANAG_PAYROLL_HOTEL_DEPT  		= 2;
    /****/public static final int OBJ_D_MANAG_PAYROLL_POSITION  		= 3;
    /****/public static final int OBJ_D_MANAG_PAYROLL_MARITAL  			= 4;
    /****/public static final int OBJ_D_MANAG_PAYROLL_INIT_ALLOWANCE            = 5;
    /****/public static final int OBJ_D_MANAG_PAYROLL_SECTION		  	= 6;
    /****/public static final int OBJ_D_MANAG_PAYROLL_CATEGORY		  	= 7;

    //
    /**/public static final int G2_DATA_MANAG_MASTER_D  = 5;
    /****/public static final int OBJ_D_MANAG_MASTER_COUNTRY  			= 0;
    /****/public static final int OBJ_D_MANAG_MASTER_CONTACT_TRAVEL_AGENT  	= 1;
    /****/public static final int OBJ_D_MANAG_MASTER_BILLING_TYPE  		= 2;
    /****/public static final int OBJ_D_MANAG_MASTER_BILLING_TYPE_ITEM  	= 3;
    /****/public static final int OBJ_D_MANAG_MASTER_MASTER_TYPE  		= 4;
    /****/public static final int OBJ_D_MANAG_MASTER_EXCHANGE_RATE  		= 5;
    /****/public static final int OBJ_D_MANAG_MASTER_CONTACT_CORPORATE  	= 6;
    /****/public static final int OBJ_D_MANAG_MASTER_CONTACT_SUPPLIER  		= 7;
    /****/public static final int OBJ_D_MANAG_MASTER_CONTACT_GUIDE  		= 8;
	/****/public static final int OBJ_D_MANAG_MASTER_BILLING_TYPE_GROUP	= 9;
    //
    /**/public static final int G2_DATA_MANAG_ACTIVITIES  = 6;
    /****/public static final int OBJ_D_MANAG_ACT_BACK_UP_SERVICE  = 0;
    /****/public static final int OBJ_D_MANAG_ACT_GUEST_QUERY  = 1;
    /****/public static final int OBJ_D_MANAG_ACT_RESERV_STATIC  = 2;
    //
    /**/public static final int G2_DATA_MANAG_INTERFACE  = 7;
    /****/public static final int OBJ_D_MANAG_INTERFACE_SET_UP  = 0;
    /****/public static final int OBJ_D_MANAG_INTERFACE_TELP_CHARGE  = 1;
    //
    /**/public static final int G2_DATA_MANAG_MATERIAL  = 8;
    /****/public static final int OBJ_D_MANAG_MATERIAL_UNIT  		= 0;
    /****/public static final int OBJ_D_MANAG_MATERIAL_GROUP  		= 1;
    /****/public static final int OBJ_D_MANAG_MATERIAL_LIST  		= 2;
    /****/public static final int OBJ_D_MANAG_MATERIAL_PERIODE  	= 3;
    /****/public static final int OBJ_D_MANAG_MATERIAL_LOCATION  	= 4;
    //
    /**/public static final int G2_DATA_MANAG_OUTLET_SYSTEM  = 9;
    /****/public static final int OBJ_D_MANAG_OUTLET_COVER  			= 0;
    /****/public static final int OBJ_D_MANAG_OUTLET_OTHER_PAYMENT 		= 1;
    //

    public static final int G1_LEDGER  = 8;
    /**/public static final int G2_LEDGER   = 0;
    /****/public static final int OBJ_LEDGER_ROOM_LEDGER   			= 0;
    /****/public static final int OBJ_LEDGER_BILLING_LEDGER   			= 1;
    /****/public static final int OBJ_LEDGER_EXPENSE_LEDGER   			= 2;
    /****/public static final int OBJ_LEDGER_DAILY_INCOME_REV   		= 3;
    /****/public static final int OBJ_LEDGER_DAILY_SALES_REPORT   		= 4;
    /****/public static final int OBJ_LEDGER_FB_SALES_POTENSIAL   		= 5;
    /****/public static final int OBJ_LEDGER_RSV_STATISTIC   			= 6;
    /****/public static final int OBJ_LEDGER_ROOM_REPORT   			= 7;
    /****/public static final int OBJ_LEDGER_ROOM_COMMISION   			= 8;
    /****/public static final int OBJ_LEDGER_DAILY_TRANSACTION   		= 9;    

    public static final int G1_NIGHT_AUDIT  = 9;
    /**/public static final int G2_NIGHT_AUDIT  = 0;
    /****/public static final int OBJ_NIGHT_AUDIT                               = 0;
    
    
    
    /**================== UNTUK MODUL CRM ======================*/
    
    
    public static final int G1_CRM_LOGIN    = 10;
    /**/public static final int G2_CRM_LOGIN            = 0;
    /****/public static final int OBJ_CRM_LOGIN_LOGIN                           = 0;
    /****/public static final int OBJ_CRM_LOGIN_LOGOUT                          = 1;       
    
    
    /* KONTAK SEWA LAHAN (KSL) */
    public static final int G1_CRM_KSL      = 11;
    /**/public static final int G2_CRM_KSL              = 0;
    /****/public static final int OBJ_CRM_KSL_DATA_KONTRAK                      = 0;
    /****/public static final int OBJ_CRM_KSL_PERUBAHAN_KONTRAK                 = 1;   
    /****/public static final int OBJ_CRM_KSL_RINCIAN_PIUTANG_BUAT_BARU         = 2;   
    /****/public static final int OBJ_CRM_KSL_RINCIAN_PIUTANG_DAFTAR_RINCIAN    = 3;   
    /****/public static final int OBJ_CRM_KSL_BUKU_PEMBANTU_PIUTANG             = 4;   
    
    
    /* INPUT DATA UPAL */
    public static final int G1_CRM_INPUT_UPAL   = 12;
    /**/public static final int G2_CRM_INPUT_UPAL       = 0;
    /****/public static final int OBJ_CRM_INPUT_UPAL_AIR_LIMBAH_BULANAN         = 0;
    /****/public static final int OBJ_CRM_INPUT_UPAL_AIR_IRIGASI_BULANAN        = 1;   
    
    
    /* INPUT DATA INVOICE */
    public static final int G1_CRM_INVOICE      = 13;
    
    /**/public static final int G2_CRM_INVOICE_KOMIN        = 0;
    /****/public static final int OBJ_CRM_INVOICE_KOMIN_INVOICE_BARU            = 0;
    /****/public static final int OBJ_CRM_INVOICE_KOMIN_DAFTAR_INVOICE          = 1;    
    
    /**/public static final int G2_CRM_INVOICE_KOMPER       = 1;
    /****/public static final int OBJ_CRM_INVOICE_KOMPER_PERHITUNGAN_KOMPER     = 0;
    
    /**/public static final int G2_CRM_INVOICE_ASSESSMENT   = 2;
    /****/public static final int OBJ_CRM_INVOICE_ASSESSMENT_INVOICE_BARU       = 0;
    /****/public static final int OBJ_CRM_INVOICE_ASSESSMENT_DAFTAR_INVOICE     = 1;
    
    /**/public static final int G2_CRM_INVOICE_UPAL         = 3;
    /****/public static final int OBJ_CRM_INVOICE_UPAL_AIR_LIMBAH               = 0;
    /****/public static final int OBJ_CRM_INVOICE_UPAL_AIR_IRIGASI              = 1;
    
    
    /* PENERIMAAN */
    public static final int G1_CRM_PENERIMAAN       = 14;
    /**/public static final int G2_CRM_PENERIMAAN               = 0;
    /****/public static final int OBJ_CRM_PENERIMAAN_KOMIN                      = 0;
    /****/public static final int OBJ_CRM_PENERIMAAN_KOMPER                     = 1;
    /****/public static final int OBJ_CRM_PENERIMAAN_ASSESSMENT                 = 2;
    /****/public static final int OBJ_CRM_PENERIMAAN_AIR_LIMBAH                 = 3;
    /****/public static final int OBJ_CRM_PENERIMAAN_AIR_IRIGASI                = 4;   
    /****/public static final int OBJ_CRM_PENERIMAAN_ARSIP                      = 5; 
    
    /* MANAGEMENT DENDA */
    public static final int G1_CRM_MANAGEMENT_DENDA       = 15;
    /**/public static final int G2_CRM_MANAGEMENT_DENDA         = 0;
    /****/public static final int OBJ_CRM_MANAGEMENT_DENDA_HITUNG_DENDA         = 0;
    /****/public static final int OBJ_CRM_MANAGEMENT_DENDA_DAFTAR_DENDA         = 1;
    /****/public static final int OBJ_CRM_MANAGEMENT_DENDA_KEBIJAKAN_DENDA      = 2;
    
    
    /* ACCOUNTING */
    public static final int G1_CRM_ACCOUNTING       = 16;
    
    /**/public static final int G2_CRM_ACCOUNTING_KOMIN         = 0;
    /****/public static final int OBJ_CRM_ACCOUNTING_KOMIN_POSTING_PIUTANG          = 0;
    /****/public static final int OBJ_CRM_ACCOUNTING_KOMIN_POSTING_PENDAPATAN       = 1;
    
    /**/public static final int G2_CRM_ACCOUNTING_KOMPER        = 1;
    /****/public static final int OBJ_CRM_ACCOUNTING_KOMPER_POSTING_JURNAL          = 0;
    /****/public static final int OBJ_CRM_ACCOUNTING_KOMPER_POSTING_PENDAPATAN      = 1;
    
    /**/public static final int G2_CRM_ACCOUNTING_ASSESSMENT    = 2;
    /****/public static final int OBJ_CRM_ACCOUNTING_ASSESSMENT_POSTING_PIUTANG     = 0;
    /****/public static final int OBJ_CRM_ACCOUNTING_ASSESSMENT_POSTING_PENDAPATAN  = 1;
    
    /**/public static final int G2_CRM_ACCOUNTING_UP_LIMBAH     = 3;
    /****/public static final int OBJ_CRM_ACCOUNTING_UP_LIMBAH_POSTING_PIUTANG      = 0;
    /****/public static final int OBJ_CRM_ACCOUNTING_UP_LIMBAH_POSTING_BKM          = 1;    
    
    /* UBAH NOMOR FAKTUR */
    public static final int G1_CRM_UBAH_NOMOR_FAKTUR       = 17;
    /**/public static final int G2_CRM_UBAH_NOMOR_FAKTUR               = 0;
    /****/public static final int OBJ_CRM_UBAH_NOMOR_FAKTUR                     = 0;    
    
    /* DATA MASTER */
    public static final int G1_CRM_DATA_MASTER       = 18;
    
    /**/public static final int G2_CRM_DATA_MASTER_SEWA_LAHAN           = 0;
    /****/public static final int OBJ_CRM_DATA_MASTER_SEWA_LAHAN_INVESTOR           = 0;
    /****/public static final int OBJ_CRM_DATA_MASTER_SEWA_LAHAN_SARANA             = 1;
    /****/public static final int OBJ_CRM_DATA_MASTER_SEWA_LAHAN_LOT                = 2;
    /****/public static final int OBJ_CRM_DATA_MASTER_SEWA_LAHAN_KONTRAK            = 3;
    
    /**/public static final int G2_CRM_DATA_MASTER_LIMBAH_IRIGASI       = 1;
    /****/public static final int OBJ_CRM_DATA_MASTER_LIMBAH_IRIGASI_HRG_LIMBAH     = 0;
    /****/public static final int OBJ_CRM_DATA_MASTER_LIMBAH_IRIGASI_HRG_IRIGASI    = 1;    
    
    /**/public static final int G2_CRM_DATA_MASTER_UMUM                 = 2;
    /****/public static final int OBJ_CRM_DATA_MASTER_UMUM_NEGARA                   = 0;
    /****/public static final int OBJ_CRM_DATA_MASTER_UMUM_MATA_UANG                = 1;
    /****/public static final int OBJ_CRM_DATA_MASTER_UMUM_SATUAN                   = 2;
    /****/public static final int OBJ_CRM_DATA_MASTER_UMUM_PERIODE                  = 3;
    
    /**/public static final int G2_CRM_DATA_MASTER_SYSTEM               = 3;
    /****/public static final int OBJ_CRM_DATA_MASTER_SYSTEM_APPROVAL               = 0;
    /****/public static final int OBJ_CRM_DATA_MASTER_SYSTEM_KODE_DOKUMEN           = 1;    
    
    
    public static final String[] titleG1 = {
        "Login Access", 
        "Admin",  
        "Reservation", 
        "Cashier", 
        "House Keeping", 
        "Membership", 
        "Payroll",
        "Data Management", 
        "Ledger", 
        "Night Audit"
    };
    
    public static final String[][] titleG2 = {
        /* login */ {"Login Access"},
        /* admin*/ {"User Management", "Service Management"},
        /* reservation*/ {"Reservation", "Reservation Charge"},
        /* cashier */ {"Cashier", "Outlet System"},
        /* house keeping*/ {"House Keeping"},
        /* membership*/ {"Membership Data"},
        /* payroll*/ {"Payroll"},
        /* data management */ {"Hotel Data",  "Guest Data", "Membership Master", "Purchasing & Stock",
        		       "Payroll","Master Data", "Activities", "Port/Interface", "Material", "Outlet System"},
        /* ledger */{"Ledger"},
        /* night audit */{"Night Audit"},
        
        /* MODUL CRM */
        /* CRM LOGIN */ {"CRM Login "},
        /* CRM KSL */ {"CRM Kontrak Sewa Lahan"},
        /* CRM KSL */ {"CRM Kontrak Sewa Lahan"},
        /* CRM INPUT UPAL */ {"CRM Input UPAL"},
        /* CRM INVOICE*/ {"Invoice Komin","Invoice Komper","Invoice Assessment","Invoice UPAL"},
        /* PENERIMAAN */ {"Penerimaan"},
        /* MANAGEMENT DENDA */ {"Management Denda"},
        /* ACCOUNTING */ {"Accounting Komin","Accounting Komper","Accounting Assessment","Accounting UP Limbah"},
        /* UBAH NOMOR FAKTUR */ {"Ubah Nomor Faktur"},        
        /* DATA MASTER */{"Master Sewa Lahan","Master Limbah Irigasi","Master Umum","Master System"} 
        
    };
    
    
    public static final String[][][] objectTitles = {
        // Login
        {   // Login
            { "Login Page", "Logout page"}
        },
        // Admin
        {   // user
            { "Pivilege", "Group", "User"},
            // system
            { "Data Backup", "Application Setting", "Data Locking", "System Monitor" }
        },
        //Reservation
        {   //Reservation & approval
            { "Room Blocking", "Reservation Adjustment", "Reservation Commision", "Reservation Breakfast", "Take Over Travel Agent",
            "Check In", "Check Out", "Room Transfer", "Reservation List", "Room Rate"}, 
            //reservation charge
            {"Edit Travel(Contract/Package) Room Charge", "Edit Walk In Room Charge"}
        },
        //Cashier
        {	//Cashier
        	{ "FO Invoice", "Billing Receipt", "Outside Guest Billing", "Operator Call","Invoice for Tax","Bill for Tax" },
            //Outlet System
        	{ "Run Cashier", "Cashier Billing", "Cashier Close", "Waiter Billing"}
        },
        // House Keeping
        {	//House Keeping
        	{"Room Status", "Hotel Project"}
        },
        // Membership
        {	//Membership Master
        	{"Membership Data", "Installment", "Maintenance Fee", "Exchange", "Member RCI Reservation"," Announcement", "Membership Reservation"}
        },
        // Payroll
        {	//Payroll
        	{"Employee Salary"}
        },
        // Data Management
        {   // Hotel Data
              { "Hotel Profile", "Surcharge Compulsory", "Hotel Room", "Room Class", "Room Not Available", "Cancel Periode", "Cancel Detail"},
              // Guest Data
              { "Guest Item"},
              // Membership Item
              { "Membership Master"},// Item Package", "Member Bonus Week"},
              // Purchasing
              { "Purchase & Material Stock" },//, "Inventory Group"},
              // Payrool
              { "Employee", "Religion", "Hotel Department","Position", "Marital", "Payroll Init Allowance", "Section", "Employee Category"},
              // Masterdata
              { "Country", "Travel Agent", "Billing Type","Billing Type Item","Master Type", "Exchange Rate", "Corporate", "Supplier/Vendor", "Guide", "Billing Group"},
              // Activities
              { "Back Up Service", "Guest Query", "Reservation Static"},
              // Interface
              { "Set Up", "Telp Charge"},
              //material
              {	"Material Unit", "Material Group", "Material List", "Material Periode", "Material Location"},
              //outlet system
              { "Cover", "Other Payment"}
        } ,

        //ledger
        {
        	  {"Room Ledger", "Biling Ledger", "Expense Ledger", "Income Revenue", "Sales Report", "F&B Sales Potensial",
        		"Reservation Statistic", "Room Report", "Room Commision", "Daily Transaction"}
        },
        //night audit
        {
            {"Night Audit"}
        },
        
        
        /** ===== MODUL CRM ===== **/
        
        //Login CRM
        {
            {"CRM Login","CRM Logout"}
        },
        //Kontrak sewa lahan
        {
            {"CRM Data Kontrak","CRM Perubahan Kontrak","CRM Rincian Piutang Buat baru","CRM Rincian Piutang Daftar Rincian","CRM Buku Pembantu Piutang"}
        },
        //Input UPAL
        {
            {"Air Limbah Bulanan","Air Irigasi Bulanan"}
        },
        //Invoice 
        {
            {"CRM Invoice Komin Baru","CRM Invoice Komin Daftar Invoice"},
            {"CRM Invoice Komper Perhitungan"},
            {"CRM Invoice Assessment Baru","CRM Invoice Assessment Daftar Invoice"},
            {"CRM Invoice UPAL Limbah","CRM Invoice UPAL Irigasi"},
        },
        //PENERIMAN
        {
            {"CRM Penerimaan Komin","CRM Penerimaan Komper","CRM Penerimaan Assessment","CRM Penerimaan Limbah","CRM Penerimaan Irigasi"}
        },
        //MANAGEMENT DENDA
        {
            {"CRM Hitung Denda","CRM Daftar Denda","CRM Kebijakan Denda"}
        },
        //ACCOUNTING
        {
            {"CRM Komin Posting Piutang","CRM Komin Posting Pendapatan"},
            {"CRM Komper Posting Jurnal","CRM Komper Posting Pendapatan"},
            {"CRM Accounting Assessment Posting Piutang","CRM Accounting Assessment Posting Pendapatan"},      
            {"CRM Accounting UP Limbah Posting Piutang","CRM Accounting UP Limbah Posting Pendapatan"}       
            
        },
        //UBAH NO FAKTUR PAJAK
        {
            {"CRM Ubah Nomor Faktur Pajak"}
        },
        //DATA MASTER
        {
            {"CRM Master Investor","CRM Master Sarana","CRM Master Lot","CRM Master Kontrak"},
            {"CRM Master Harga Limbah","CRM Master Harga Irigasi"},
            {"CRM Master Negara","CRM Mater Mata Uang","CRM Master Satuan","CRM Master Periode"},        
            {"CRM Master Approval","CRM Mater Kode Dokumen"}        
        }
        
    };

     
    public static final int[][][][] objectCommands = {
    /* Semua Object harus memiliki COMMAND_VIEW */
        // Login
        {   // Login
            {
                //Login Page
                {COMMAND_VIEW, COMMAND_SUBMIT} ,
                //"Logout page"
                {COMMAND_VIEW, COMMAND_SUBMIT}
            }
        },
        
        // Admin
        {   // user
            {
                //"Pivilege"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DELETE, COMMAND_UPDATE},
                //"Group"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DELETE, COMMAND_UPDATE},
                //"User"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DELETE, COMMAND_UPDATE}
            },
            // system
            {
                //"Data Backup"
                {COMMAND_VIEW, COMMAND_START, COMMAND_STOP, COMMAND_DELETE, COMMAND_UPDATE},
                //"Application Setting"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Data Locking"
                {COMMAND_VIEW, COMMAND_START, COMMAND_STOP},
                //"System Monitor"
                {COMMAND_VIEW, COMMAND_START, COMMAND_STOP, COMMAND_DELETE}
            }
        },
        // Reservation     "Check In", "Check Out", "Room Transfer", "Reservation List"
        {	//Reservation
        	{   //Room Block
                    {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE, COMMAND_PRINT},
                    //adjustment
                    {COMMAND_VIEW, COMMAND_APPROVE},
                    //commision
                    {COMMAND_VIEW, COMMAND_APPROVE},
                    //breakfast
                    {COMMAND_VIEW, COMMAND_APPROVE},
                    //take over travel
                    {COMMAND_VIEW, COMMAND_APPROVE},
                    //check in
                    {COMMAND_VIEW, COMMAND_APPROVE},
                    //check out
                    {COMMAND_VIEW, COMMAND_APPROVE},
                    //room transfer
                    {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE, COMMAND_APPROVE},
                    //room list
                    {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_PRINT, COMMAND_DELETE},
                    //room rate
                    {COMMAND_VIEW}
        	},
                
                //reservation charge
                {
                    //edit travel room charge
                    {COMMAND_VIEW, COMMAND_UPDATE},
                    //edit walkin room charge
                    {COMMAND_VIEW, COMMAND_UPDATE}
                }
                

        },
        // Cashier
        {	//Cashier
        	{   //Invoice
        		{COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_PRINT, COMMAND_ENTER},
                //Billing Receipt
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE, COMMAND_ENTER},
        		//OutSide Guest Billing Receipt
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE, COMMAND_ENTER},
                //index page
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
		{COMMAND_VIEW, COMMAND_PRINT},
		{COMMAND_VIEW, COMMAND_PRINT}
        	},
                //Outlet System
        	{   //Run Cashier
        		{COMMAND_VIEW, COMMAND_START, COMMAND_ENTER},
                //Cashier Billing
        		{COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE, COMMAND_PRINT, COMMAND_ENTER},
                //Cashier Close
        		{COMMAND_VIEW, COMMAND_UPDATE, COMMAND_ENTER},
                //Waiter Billing
        		{COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE, COMMAND_PRINT, COMMAND_ENTER}
        	}
        },
        // House Keeping
        {	//House Keeping
        	{   //Room Status
        		{COMMAND_VIEW, COMMAND_UPDATE},
                //hotel project
        		{COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE}
        	}
        },
        // Membership
        {	//Membership
        	{   //Membership Data
        		{COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE, COMMAND_PRINT},
                //Installment
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE,COMMAND_PRINT},
                //Maintenance Fee
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE,COMMAND_PRINT},
                //Exchange
        		{COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //Member RCI
        		{COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //Announcement
                {COMMAND_VIEW, COMMAND_PRINT},
        		//Membership Reservation
        		{COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
        	}
        },
        // Payroll
        {	//Payroll
        	{   //Employee Salary
        		{COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE,COMMAND_PRINT}
        	}
        },
        // Data Management
        {   // Hotel Data
            { 	//"Hotel Profile"
                {COMMAND_VIEW, COMMAND_UPDATE},
                //"Surcharge Compulsory"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Hotel Room"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Room Class"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Room Not Available"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
				//"Cancel Periode"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Cancel Detail"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE}
            },
            // Guest Data
            {   //"Guest"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE, COMMAND_PRINT}
            },
            //Membership
            {   //"Member Master Item"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE, COMMAND_PRINT}//,
                //"Member Bonus Week"
               // {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE}
            },
            // Purchasing
            {   //"Material"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE, COMMAND_PRINT}//,
                //"Inventory Group"
               // {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE}
            },
            // Payrool
            {   //"Payroll Employee"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE, COMMAND_PRINT},
                //"Religion"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Hotel Department"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Position"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Marital"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"init allowance"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"section"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Employee Category"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE}

            },
            // Master Data
            {   //"Country"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"travel agent"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Billing Type"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Billing Type Item"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Master Type"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Exchange Rate"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Corporate"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Supplier/Vendor"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Guide"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Billing Group"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE}
            },
            // Activities
            {   //"Back Up Service"
                {COMMAND_VIEW, COMMAND_UPDATE, COMMAND_DELETE,COMMAND_START,COMMAND_STOP},
                //"Guest Query"
                {COMMAND_VIEW},
                //"Reservation Static"
                {COMMAND_VIEW}
            },
            // Interface
            {   //"Setup"
                {COMMAND_UPDATE},
                //"Telephone Charge"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE}
            },
            // material
            {   //"Material Unit"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Material Group"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Material List"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Material Periode"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Material Location"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE}
            },
            // Outlet System
            {   //"Cover"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //"Other Payment"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE}
            }
        },

        //ledger
        {
            //ledger
	        {
                //room ledger
	            {COMMAND_VIEW, COMMAND_PRINT},
                //billing ledger
	            {COMMAND_VIEW, COMMAND_PRINT},
                //expense ledger
	            {COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE, COMMAND_VIEW, COMMAND_PRINT},
                //income revenue
	            {COMMAND_VIEW, COMMAND_PRINT},
                //sales report
	            {COMMAND_VIEW, COMMAND_PRINT},
                //f&b sales pot.
	            {COMMAND_VIEW, COMMAND_PRINT},
                //Reservation Statistic
	            {COMMAND_VIEW, COMMAND_PRINT},
                //room report.
	            {COMMAND_VIEW, COMMAND_PRINT},
                //room commision.
	            {COMMAND_VIEW, COMMAND_PRINT},
                //daily transaction.
	            {COMMAND_VIEW, COMMAND_PRINT}
	        }
        },
        
        //night audit
        {
            //night audit
            {
                //night audit
                {COMMAND_VIEW, COMMAND_SUBMIT}
            }
        },
        
        
        //===== MODUL CRM ============//
        
        // CRM Login
        {   // CRM Login
            {
                //Login Page
                {COMMAND_VIEW, COMMAND_SUBMIT} ,
                //Logout page
                {COMMAND_VIEW, COMMAND_SUBMIT}
            }
        },
        //Kontrak Sewa Lahan
        {
            //Kontrak Sewa Lahan
            {
                //Data Kontrak
                {COMMAND_VIEW},
                //Perubahan Kontrak        
                {COMMAND_VIEW},
                //Rincian Piutang buat buku baru        
                {COMMAND_VIEW},
                //Piutang daftar Rincian        
                {COMMAND_VIEW},        
                //Buku Pembantu Piutang        
                {COMMAND_VIEW}        
            }            
        },
        //Input Data UPAL
        {
            //Input Data UPAL
            {
                //Air Limbah Bulanan
                {COMMAND_VIEW},
                //Air Limbah Bulanan        
                {COMMAND_VIEW}        
            }
               
        },
        //Input data Invoice
        {
            //Invoice Komin
            {
                //Komin Invoice baru
                {COMMAND_VIEW},
                //Komin daftar Invoice        
                {COMMAND_VIEW}
            },
            //Invoice Komper        
            {
                {COMMAND_VIEW}
            },
            //Invoice Assessment        
            {
                //Assessment Invoice baru
                {COMMAND_VIEW},
                //Assessment Daftar Invoice     
                {COMMAND_VIEW}
            },        
            //Invoice UPAL
            {
                //Invoice Air Limbah
                {COMMAND_VIEW},
                //Invoice Air Irigasi
                {COMMAND_VIEW}
            }
        },
        //Penerimaan
        {
            //Penerimaan
            {
                //Penerimaan Komin
                {COMMAND_VIEW},
                //Penerimaan Komper       
                {COMMAND_VIEW},
                //Penerimaan Assessment
                {COMMAND_VIEW},
                //Penerimaan Air Limbah
                {COMMAND_VIEW},
                //Penerimaan Air Irigasi        
                {COMMAND_VIEW}        
            }
            
        },
        //Management Denda
        {
            //management denda
            {
                //Hitung Denda
                {COMMAND_VIEW},
                //Daftar Denda        
                {COMMAND_VIEW},
                //Kebijakan Denda        
                {COMMAND_VIEW}
            }
        },
        //Accounting
        {
            //Accounting Komin
            {
                //Komin Posting Piutang
                {COMMAND_VIEW},
                //Komin Posting pendapatan
                {COMMAND_VIEW}
            },
            //Accounting Komper        
            {
                //Komper Posting Piutang
                {COMMAND_VIEW},
                //Komper Posting pendapatan
                {COMMAND_VIEW}
                
            },
            //Acounting Assessment        
            {
                //Assessment Posting Piutang
                {COMMAND_VIEW},
                //Assessment Posting Pendapatan
                {COMMAND_VIEW}
            },
            //  Accounting UP Limbah     
            {
                //Limbah posting piutang
                {COMMAND_VIEW},
                //Limbah posting BKM
                {COMMAND_VIEW}        
            }        
            
        },
        //Ubah Nomor Faktur
        {
            //Ubah nomor faktur
            {
                //Ubah Nomor Faktur
                {COMMAND_VIEW}
                
            }
        },
        //Data Master
        {
            //Data Master Sewa Lahan
            {
                //Sewa Lahan Investor
                {COMMAND_VIEW},
                //Sewa Lahan Sarana        
                {COMMAND_VIEW},
                //Sewa Lahan lot
                {COMMAND_VIEW},
                //Sewa Lahan kontrak
                {COMMAND_VIEW}
            },
            //Master limbah irigasi        
            {   
                //Harga Limbah
                {COMMAND_VIEW},
                //Harga Irigasi        
                {COMMAND_VIEW}
                
            },
            //Master umum
            {
                //Negara
                {COMMAND_VIEW},
                //Mata uang
                {COMMAND_VIEW},
                //Satuan
                {COMMAND_VIEW},
                //Periode   
                {COMMAND_VIEW}
                
            },
            //System
            {
                //Sistem Approval
                {COMMAND_VIEW},
                //Kode dokumen
                {COMMAND_VIEW}
            }        
            
        }
        
    };
    

    public static String getStrCommand(int command){
        if((command<0) || (command > strCommand.length) ){
            System.out.println(" ERR: getStrCommand - commmand out of range");
            return "";
        }
        return strCommand[command];
        
    }
    
    public static boolean existObject(int g1, int g2, int objIdx){
        if( (g1<0) || (g1> titleG1.length)){
            System.out.println(" ERR: composeCode g1 out of range");
            return false;
        }
             
        if((g2<0) || (g2 > (titleG2[g1]).length))  {
            System.out.println(" ERR: composeCode g2 out of range");
            return false;
        }
        
        if((objIdx<0) || (objIdx> (objectTitles[g1][g2]).length)){
            System.out.println(" ERR: composeCode objIdx out of range");
            return false;
        }
        
        return true;        
    }
    
    public static int composeCode(int g1, int g2, int objIdx, int command) {        
	System.out.println("g1="+g1+" g2="+g2+" objIdx="+objIdx+" command="+command);
        if(!existObject(g1,g2, objIdx))
            return -1;
        
        if((command<0) || (command > strCommand.length)){ 
            System.out.println(" ERR: composeCode commmand out of range");
            return -1;
        }
        
        if(!privExistCommand(g1,g2, objIdx, command)){
            System.out.println(" ERR: composeCode commmand out not exist on object "+ 
            getTitleGroup1(g1)+"-"+getTitleGroup2(g2)+"-"+getTitleObject(objIdx));
            return -1;
        }
        
        return (g1 << SHIFT_CODE_G1) + (g2 << SHIFT_CODE_G2) + (objIdx << SHIFT_CODE_OBJ ) + command;
    }

    public static int composeCode(int objCode, int command) {        
        if((command<0) || (command > strCommand.length) ){
            System.out.println(" ERR: composeCode commmand out of range");
            return -1;
        }
        //System.out.println("objCode + command"+objCode + command);
        return objCode + command;
    }
    
    public static int composeObjCode(int g1, int g2, int objIdx) {        
        if(!existObject(g1,g2, objIdx))
            return -1;
                
        return (g1 << SHIFT_CODE_G1) + (g2 << SHIFT_CODE_G2) + (objIdx << SHIFT_CODE_OBJ );
    }
    
    private static boolean privExistCommand(int g1, int g2, int objIdx, int command){
        for(int i=0; i< objectCommands[g1][g2][objIdx].length;i++){
            if(objectCommands[g1][g2][objIdx][i]==command)
                return true;            
        }
        return false;
    }

    public static boolean existCommand(int g1, int g2, int objIdx, int command){
        if(!existObject(g1,g2, objIdx))
            return false;
        
        return privExistCommand(g1,g2, objIdx, command);
    }
    
    public static int getG1G2ObjIdx(int code){
        return (code & (FILTER_CODE_G1 + FILTER_CODE_G2 + FILTER_CODE_OBJ));
    }

    public static int getCommand(int code){
        return (code & FILTER_CODE_CMD);
    }
    
    public static int getIdxGroup1(int code){
        int g1 = (code & FILTER_CODE_G1) >> SHIFT_CODE_G1;
        if( (g1<0) || (g1> titleG1.length)){
            System.out.println(" ERR: getIdxGroup1 g1="+g1+" on code out of range ");
            return -1;
        }
        return g1;        
    }

    public static String getTitleGroup1(int code){
        int g1 = getIdxGroup1(code);
        if(g1<0)
            return "";

        return titleG1[g1];
    }

    public static int getIdxGroup2(int code){
        int g1 = getIdxGroup1(code);
        if(g1<0)
            return -1;
        
        int g2 = (code & FILTER_CODE_G2) >> SHIFT_CODE_G2;
        if( (g2<0) || (g2> titleG2[g1].length)){
            System.out.println(" ERR: getIdxGroup2 g2 on code out of range");
            return -1;
        }
        return g2;        
    }

    public static String getTitleGroup2(int code){
        int g1 = getIdxGroup1(code);
        if(g1<0)
            return "";
        
        int g2 = getIdxGroup2(code);
        if(g2<0)
            return "";

        return titleG2[g1][g2];
    }

    public static int getIdxObject(int code){
        int g1 = getIdxGroup1(code);
        if(g1<0)
            return -1;
        
        int g2 = getIdxGroup2(code);
        if(g2<0)
            return -1;
        
        int oidx = (code & FILTER_CODE_OBJ) >> SHIFT_CODE_OBJ;
        if( (oidx<0) || (oidx> objectTitles[g1][g2].length)){
            System.out.println(" ERR: getIdxObject, oidx on code out of range");
            return -1;
        }
        return oidx;        
    }

    public static String getTitleObject(int code){
        int g1 = getIdxGroup1(code);
        if(g1<0)
            return "";
        
        int g2 = getIdxGroup2(code);
        if(g2<0)
            return "";
        
        int oidx = getIdxObject(code);
        if(oidx<0)
            return "";
        
        return objectTitles[g1][g2][oidx];
    }
    
    /*
     * parse privobj code into title/string of g1, g2, objidx and command
     * return Vector of String: 0=g1, 1=g, 2=objIdx, 3=command, 4=Integer error code (0=false, -1=falses), 
     * 
     */
    public static Vector parseStringCode(int code){
        Vector titleCodes= new Vector(4,1);
        titleCodes.add(new String(""));
        titleCodes.add(new String(""));
        titleCodes.add(new String(""));
        titleCodes.add(new String(""));
        titleCodes.add(new Integer(0));
        
        int g1 = getIdxGroup1(code);
        if(g1<0){
            titleCodes.set(0, "Invalid G1 Idx");
            titleCodes.set(4, new Integer(-1));
            return titleCodes;
        }
        titleCodes.set(0, titleG1[g1]);

        int g2 = getIdxGroup2(code);
        if(g2<0){
            titleCodes.set(1, "Invalid G2 Idx");
            titleCodes.set(4, new Integer(-1));
            return titleCodes;
        }
        titleCodes.set(1, titleG2[g1][g2]);
        
        int oidx = getIdxObject(code);
        if(oidx<0){
            titleCodes.set(2, "Invalid Obj. Idx");
            titleCodes.set(4, new Integer(-1));
            return titleCodes;
        }
        titleCodes.set(2, objectTitles[g1][g2][oidx]);
        
        int cmd = getCommand(code);
        if(cmd<0){
            titleCodes.set(3, "Invalid Command");
            titleCodes.set(4, new Integer(-1));
            return titleCodes;
        }
        titleCodes.set(3, strCommand[cmd]);
                
        return titleCodes;
    }

    
}
