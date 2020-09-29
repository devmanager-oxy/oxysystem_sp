<%@ page import="com.project.system.*"%>
<%@ page import="com.project.general.Currency"%>
<%@ page import="com.project.general.DbCurrency"%>
<%@ page import="com.project.fms.master.*"%>
<%@ page import="com.project.payroll.*"%>

<%@ page import="com.project.general.Company"%>
<%@ page import="com.project.general.DbCompany"%>
<%@ page import="com.project.general.DbLocation"%>
<%@ page import="com.project.general.Location"%>

<%

//offline
            String approot = "/oxysystem_sp";
            String approotx = "/oxysystem_sp";
            String imageroot = "/oxysystem_sp/";
            String printroot = "/oxysystem_sp/servlet/com.project.fms";
            String printrootsp = "/oxysystem_sp/servlet/com.project.coorp";
            String printrootinv = "/oxysystem_sp/servlet/com.project.ccs";            
            String spTitle = "Simpan Pinjam";


            String sSystemDecimalSymbol = ".";
            String sUserDecimalSymbol = ".";
            String sUserDigitGroup = ",";

            int menuIdx = JSPRequestValue.requestInt(request, "menu_idx");

            boolean includeActivity = true;//((DbSystemProperty.getValueByName("APPLAY_ACTIVITY").equals("Y")) ? true : false);

            final int LANG_EN = 1;
            final int LANG_ID = 2;
            int lang = 0;

            String strLang = "";
            if (session.getValue("APP_LANG") != null) {
                strLang = String.valueOf(session.getValue("APP_LANG"));
                lang = Integer.parseInt(strLang);
            } else {
                lang = 2;
            }

            Company sysCompany = new Company();
            Currency baseCurrency = new Currency();
            Location sysLocation = new Location();

            try {
                sysCompany = DbCompany.getCompany();
            } catch (Exception e) {
                System.out.println("Exc get company : " + e.toString());
                response.sendRedirect(approot + "/index.jsp");
            }

            try {
                baseCurrency = DbCurrency.getCurrencyById(sysCompany.getBookingCurrencyId());
            } catch (Exception e) {
                System.out.println("Exc get currency : " + e.toString());
                response.sendRedirect(approot + "/index.jsp");
            }

            try {
                sysLocation = DbLocation.getLocationById(sysCompany.getSystemLocation());
            } catch (Exception e) {
                System.out.println("Exc get location : " + e.toString());
                response.sendRedirect(approot + "/index.jsp");
            }

            String baseCurrencyCode = baseCurrency.getCurrencyCode();

            String IDRCODE = "IDR";
            String USDCODE = "USD";
            String EURCODE = "EUR";
            boolean isPostableOnly = false;
            try {
                IDRCODE = DbSystemProperty.getValueByName("CURRENCY_CODE_IDR");
                USDCODE = DbSystemProperty.getValueByName("CURRENCY_CODE_USD");
                EURCODE = DbSystemProperty.getValueByName("CURRENCY_CODE_EUR");
                String postableOnly = DbSystemProperty.getValueByName("ACCOUNT_POSTABLE_ONLY");
                if (postableOnly.equals("Y")) {
                    isPostableOnly = true;
                }
            } catch (Exception e) {                
                IDRCODE = "IDR";
                USDCODE = "USD";
                EURCODE = "EUR";
                isPostableOnly = false;
            }
%>

<script language=JavaScript src="<%=approot%>/main/common.js"></script>





