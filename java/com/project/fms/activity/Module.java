
package com.project.fms.activity; 
 
import java.util.Date;
import com.project.main.entity.*;

public class Module extends Entity { 

	private long parentId = 0;
	private String code = "";
	private String level = "";
	private String description = "";
	private String outputDeliver = "";
	private String performIndicator = "";
	private String assumRisk = "";
	private String status = "";
	private String type = "";

        /**
         * Holds value of property costImplication.
         */
        private String costImplication="";
        
        /**
         * Holds value of property parentIdM.
         */
        private long parentIdM;
        
        /**
         * Holds value of property parentIdS.
         */
        private long parentIdS;
        
        /**
         * Holds value of property parentIdSH.
         */
        private long parentIdSH;
        
        /**
         * Holds value of property parentIdA.
         */
        private long parentIdA;
        
        /**
         * Holds value of property statusPost.
         */
        private String statusPost = "";
        
        /**
         * Holds value of property initial.
         */
        private String initial = "";
        
        /**
         * Holds value of property positionLevel.
         */
        private String positionLevel = "";
        
        /**
         * Holds value of property expiredDate.
         */
        private Date expiredDate = new Date();
        
        /**
         * Holds value of property activityPeriodId.
         */
        private long activityPeriodId;
        
        /**
         * Holds value of property parentIdSA.
         */
        private long parentIdSA;
        
        /**
         * Holds value of property check.
         */
        private int check;
        
	public long getParentId(){ 
		return parentId; 
	} 

	public void setParentId(long parentId){ 
		this.parentId = parentId; 
	} 

	public String getCode(){ 
		return code; 
	} 

	public void setCode(String code){ 
		if ( code == null ) {
			code = ""; 
		} 
		this.code = code; 
	} 

	public String getLevel(){ 
		return level; 
	} 

	public void setLevel(String level){ 
		if ( level == null ) {
			level = ""; 
		} 
		this.level = level; 
	} 

	public String getDescription(){ 
		return description; 
	} 

	public void setDescription(String description){ 
		if ( description == null ) {
			description = ""; 
		} 
		this.description = description; 
	} 

	public String getOutputDeliver(){ 
		return outputDeliver; 
	} 

	public void setOutputDeliver(String outputDeliver){ 
		if ( outputDeliver == null ) {
			outputDeliver = ""; 
		} 
		this.outputDeliver = outputDeliver; 
	} 

	public String getPerformIndicator(){ 
		return performIndicator; 
	} 

	public void setPerformIndicator(String performIndicator){ 
		if ( performIndicator == null ) {
			performIndicator = ""; 
		} 
		this.performIndicator = performIndicator; 
	} 

	public String getAssumRisk(){ 
		return assumRisk; 
	} 

	public void setAssumRisk(String assumRisk){ 
		if ( assumRisk == null ) {
			assumRisk = ""; 
		} 
		this.assumRisk = assumRisk; 
	} 

	public String getStatus(){ 
		return status; 
	} 

	public void setStatus(String status){ 
		if ( status == null ) {
			status = ""; 
		} 
		this.status = status; 
	} 

	public String getType(){ 
		return type; 
	} 

	public void setType(String type){ 
		if ( type == null ) {
			type = ""; 
		} 
		this.type = type; 
	} 

        /**
         * Getter for property costImplication.
         * @return Value of property costImplication.
         */
        public String getCostImplication() {
            return this.costImplication;
        }
        
        /**
         * Setter for property costImplication.
         * @param costImplication New value of property costImplication.
         */
        public void setCostImplication(String costImplication) {
            this.costImplication = costImplication;
        }
        
        /**
         * Getter for property parentIdM.
         * @return Value of property parentIdM.
         */
        public long getParentIdM() {
            return this.parentIdM;
        }
        
        /**
         * Setter for property parentIdM.
         * @param parentIdM New value of property parentIdM.
         */
        public void setParentIdM(long parentIdM) {
            this.parentIdM = parentIdM;
        }
        
        /**
         * Getter for property parentIdS.
         * @return Value of property parentIdS.
         */
        public long getParentIdS() {
            return this.parentIdS;
        }
        
        /**
         * Setter for property parentIdS.
         * @param parentIdS New value of property parentIdS.
         */
        public void setParentIdS(long parentIdS) {
            this.parentIdS = parentIdS;
        }
        
        /**
         * Getter for property parentIdSH.
         * @return Value of property parentIdSH.
         */
        public long getParentIdSH() {
            return this.parentIdSH;
        }
        
        /**
         * Setter for property parentIdSH.
         * @param parentIdSH New value of property parentIdSH.
         */
        public void setParentIdSH(long parentIdSH) {
            this.parentIdSH = parentIdSH;
        }
        
        /**
         * Getter for property parentIdA.
         * @return Value of property parentIdA.
         */
        public long getParentIdA() {
            return this.parentIdA;
        }
        
        /**
         * Setter for property parentIdA.
         * @param parentIdA New value of property parentIdA.
         */
        public void setParentIdA(long parentIdA) {
            this.parentIdA = parentIdA;
        }
        
        /**
         * Getter for property statusPost.
         * @return Value of property statusPost.
         */
        public String getStatusPost() {
            return this.statusPost;
        }
        
        /**
         * Setter for property statusPost.
         * @param statusPost New value of property statusPost.
         */
        public void setStatusPost(String statusPost) {
            this.statusPost = statusPost;
        }
        
        /**
         * Getter for property initial.
         * @return Value of property initial.
         */
        public String getInitial() {
            return this.initial;
        }
        
        /**
         * Setter for property initial.
         * @param initial New value of property initial.
         */
        public void setInitial(String initial) {
            this.initial = initial;
        }
        
        /**
         * Getter for property positionLevel.
         * @return Value of property positionLevel.
         */
        public String getPositionLevel() {
            return this.positionLevel;
        }
        
        /**
         * Setter for property positionLevel.
         * @param positionLevel New value of property positionLevel.
         */
        public void setPositionLevel(String positionLevel) {
            this.positionLevel = positionLevel;
        }
        
        /**
         * Getter for property expiredDate.
         * @return Value of property expiredDate.
         */
        public Date getExpiredDate() {
            return this.expiredDate;
        }
        
        /**
         * Setter for property expiredDate.
         * @param expiredDate New value of property expiredDate.
         */
        public void setExpiredDate(Date expiredDate) {
            this.expiredDate = expiredDate;
        }
        
        /**
         * Getter for property activityPeriodId.
         * @return Value of property activityPeriodId.
         */
        public long getActivityPeriodId() {
            return this.activityPeriodId;
        }
        
        /**
         * Setter for property activityPeriodId.
         * @param activityPeriodId New value of property activityPeriodId.
         */
        public void setActivityPeriodId(long activityPeriodId) {
            this.activityPeriodId = activityPeriodId;
        }
        
        /**
         * Getter for property parentIdSA.
         * @return Value of property parentIdSA.
         */
        public long getParentIdSA() {
            return this.parentIdSA;
        }
        
        /**
         * Setter for property parentIdSA.
         * @param parentIdSA New value of property parentIdSA.
         */
        public void setParentIdSA(long parentIdSA) {
            this.parentIdSA = parentIdSA;
        }
        
        /**
         * Getter for property check.
         * @return Value of property check.
         */
        public int getCheck() {
            return this.check;
        }
        
        /**
         * Setter for property check.
         * @param check New value of property check.
         */
        public void setCheck(int check) {
            this.check = check;
        }
        
}
