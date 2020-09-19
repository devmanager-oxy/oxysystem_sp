
package com.project.ccs.posmaster; 
 
import java.util.Date;
import com.project.main.entity.*;

public class Shift extends Entity { 

	private String name = "";
	private Date startTime;
	private Date endTime;

        /**
         * Holds value of property startHours.
         */
        private int startHours;
        
        /**
         * Holds value of property startMinutes.
         */
        private int startMinutes;
        
        /**
         * Holds value of property endMinutes.
         */
        private int endMinutes;
        
        /**
         * Holds value of property endHours.
         */
        private int endHours;
        
	public String getName(){ 
		return name; 
	} 

	public void setName(String name){ 
		if ( name == null ) {
			name = ""; 
		} 
		this.name = name; 
	} 

	public Date getStartTime(){ 
		return startTime; 
	} 

	public void setStartTime(Date startTime){ 
		this.startTime = startTime; 
	} 

	public Date getEndTime(){ 
		return endTime; 
	} 

	public void setEndTime(Date endTime){ 
		this.endTime = endTime; 
	} 

        /**
         * Getter for property startHour.
         * @return Value of property startHour.
         */
        public int getStartHours() {
            return this.startHours;
        }
        
        /**
         * Setter for property startHour.
         * @param startHour New value of property startHour.
         */
        public void setStartHours(int startHours) {
            this.startHours = startHours;
        }
        
        /**
         * Getter for property startMinutes.
         * @return Value of property startMinutes.
         */
        public int getStartMinutes() {
            return this.startMinutes;
        }
        
        /**
         * Setter for property startMinutes.
         * @param startMinutes New value of property startMinutes.
         */
        public void setStartMinutes(int startMinutes) {
            this.startMinutes = startMinutes;
        }
        
        /**
         * Getter for property endMinutes.
         * @return Value of property endMinutes.
         */
        public int getEndMinutes() {
            return this.endMinutes;
        }
        
        /**
         * Setter for property endMinutes.
         * @param endMinutes New value of property endMinutes.
         */
        public void setEndMinutes(int endMinutes) {
            this.endMinutes = endMinutes;
        }
        
        /**
         * Getter for property endHours.
         * @return Value of property endHours.
         */
        public int getEndHours() {
            return this.endHours;
        }
        
        /**
         * Setter for property endHours.
         * @param endHours New value of property endHours.
         */
        public void setEndHours(int endHours) {
            this.endHours = endHours;
        }
        
}
