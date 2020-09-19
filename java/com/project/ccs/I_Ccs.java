/*
 * I_Ims.java
 *
 * Created on November 13, 2008, 9:37 AM
 */

package com.project.ccs;

/**
 *
 * @author  Suarjaya
 */
public class I_Ccs {
    
    //Product Type    
    public static final int TYPE_CATEGORY_FINISH_GOODS          = 0;       //raw material
    public static final int TYPE_CATEGORY_RAW_MATERIAL          = 1;   //finish good
    public static final int TYPE_CATEGORY_PLUMBING_MATERIAL     = 2;    //component
    public static final int TYPE_CATEGORY_ELECTRICAL_MATERIAL   = 3;
    public static final int TYPE_CATEGORY_BUILDING_MATERIAL     = 4;
    public static final int TYPE_CATEGORY_CIVIL_WORK            = 5;
    public static final int TYPE_CATEGORY_COMPONENT             = 6;
    public static final int TYPE_CATEGORY_SPAREPART             = 7;
    public static final int TYPE_CATEGORY_ASSET                          = 8;
    
    public static final String[] strCategoryType = {
        "Product/Recipe", "Raw Material", "Plumbing Material", "Electrical Material",
        "Building Material", "Civil Work", "Component", "Sparepart",
        "Asset"
        
    };
    public static final String[] strType = {"0", "1", "2", "3","4", "5", "6", "7"};
    public static final String[] strTypeCode = {
        "FG","RM","PM", "EM",
        "BM", "CW", "CP", "SP"
    };
    
              
    //UOM Type
    public static final int UOM_VOLUME = 0;
    public static final int UOM_LENGTH = 1;
    public static final int UOM_WEIGHT = 2;
    public static final int UOM_SQUARE = 3;
    public static final int UOM_TIME = 4;
    public static final int UOM_QUANTITY = 5;
    public static final int UOM_NOT_AVAILABLE = 6;    
    public static final String[] strUomType = {"UOM Volume", "UOM Length", "UOM Weight", "UOM Square", "UOM Time", "UOM Quantity", "Not Available"};
    
    //Group Type
    public static final int NO_GROUP = 0;    
    public static final int GROUP_1 = 1;    
    public static final int GROUP_2 = 2;    
    public static final int GROUP_3 = 3;    
    public static final int GROUP_4 = 4;    
    public static final int GROUP_5 = 5;    
    public static final int GROUP_6 = 6;    
    public static final int GROUP_7 = 7;    
    public static final int GROUP_8 = 8;    
    public static final int GROUP_9 = 9;    
    public static final int GROUP_10 = 10;    
    public static final String[] strGroupType = {"No Group", "Group 1", "Group 2", "Group 3", "Group 4", "Group 5", "Group 6", "Group 7", "Group 8", "Group 9", "Group 10"};
    
    //Table Type
    public static final int NO_TABLE = 0;    
    public static final int TABLE_1 = 1;    
    public static final int TABLE_2 = 2;    
    public static final String[] strTableType = {"No Table", "Table 1", "Table 2"};
    
    //Alignment Type
    public static final int LEFT = 0;    
    public static final int CENTER = 1;    
    public static final int RIGHT = 2;    
    public static final String[] strAlignType = {"Left", "Center", "Right"};
}
