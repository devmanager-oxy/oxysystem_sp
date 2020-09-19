/* Generated by Together */

package com.project.util.jsp;

// import java

// import dimata
import com.project.util.*;

public class Control {

     private int start = 0;

     public int actionList(int listCmd, int start, int vectSize, int recordToGet) {
     
     switch(listCmd){                
         case JSPCommand.FIRST :                
             this.start = 0;
             break;

         case JSPCommand.PREV :                
             this.start = start - recordToGet;
             if(this.start < 0){
                 this.start = 0;
             }                
             break;

         case JSPCommand.NEXT :
             this.start = start + recordToGet;
             if(this.start >= vectSize){
                 this.start = this.start - recordToGet;
             }                
             break;
             
         case JSPCommand.LAST :
             int mdl = vectSize % recordToGet;
             if(mdl>0){
                 this.start = vectSize - mdl;
             }
             else{
                 this.start = vectSize - recordToGet;
             }                
             
             break;
             
         default:
             this.start = start;
             if(vectSize<1)
                 this.start = 0;                
             
             if(start > vectSize){
                 mdl = vectSize % recordToGet;
                 if(mdl>0){
                     this.start = vectSize - mdl;
                 }
                 else{
                     this.start = vectSize - recordToGet;
                 }                
             }                
             break;
     }
     return this.start;
 }
}
