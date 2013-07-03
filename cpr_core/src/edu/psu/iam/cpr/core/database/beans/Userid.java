/* SVN FILE: $Id: Userid.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * Copyright 2012 The Pennsylvania State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @package edu.psu.iam.cpr.core.database.beans
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */

@Entity
@Table(name="userid")
public class Userid implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the numPart. */
        @Column(name="num_part", nullable=true)
        private Long numPart;

        /** Contains the psuIdLetter. */
        @Column(name="psu_id_letter", nullable=true, length=1)
        private String psuIdLetter;

        /** Contains the primaryFlag. */
        @Column(name="primary_flag", nullable=false, length=1)
        private String primaryFlag;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the charPart. */
        @Column(name="char_part", nullable=true, length=30)
        private String charPart;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains the startDate. */
        @Column(name="start_date", nullable=false)
        private Date startDate;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the personId. */
        @Id
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** Contains the displayNameFlag. */
        @Column(name="display_name_flag", nullable=false, length=1)
        private String displayNameFlag;

        /** Contains the userid. */
        @Id
        @Column(name="userid", nullable=false, length=30)
        private String userid;
        
        /** Contains the scoped UUID */
        @Column(name="scoped_uuid", nullable=true, length=100)
        private String scopedUuid;

        /**
         * Constructor
         */
        public Userid() {
            super();
        }

        /**
         * @return the numPart
         */
        public Long getNumPart() {
                return numPart;
        }

        /**
         * @param numPart the numPart to set.
         */
        public void setNumPart(Long numPart) {
                this.numPart = numPart;
        }

        /**
         * @return the psuIdLetter
         */
        public String getPsuIdLetter() {
                return psuIdLetter;
        }

        /**
         * @param psuIdLetter the psuIdLetter to set.
         */
        public void setPsuIdLetter(String psuIdLetter) {
                this.psuIdLetter = psuIdLetter;
        }

        /**
         * @return the primaryFlag
         */
        public String getPrimaryFlag() {
                return primaryFlag;
        }

        /**
         * @param primaryFlag the primaryFlag to set.
         */
        public void setPrimaryFlag(String primaryFlag) {
                this.primaryFlag = primaryFlag;
        }

        /**
         * @return the lastUpdateBy
         */
        public String getLastUpdateBy() {
                return lastUpdateBy;
        }

        /**
         * @param lastUpdateBy the lastUpdateBy to set.
         */
        public void setLastUpdateBy(String lastUpdateBy) {
                this.lastUpdateBy = lastUpdateBy;
        }

        /**
         * @return the charPart
         */
        public String getCharPart() {
                return charPart;
        }

        /**
         * @param charPart the charPart to set.
         */
        public void setCharPart(String charPart) {
                this.charPart = charPart;
        }

        /**
         * @return the endDate
         */
        public Date getEndDate() {
                return endDate;
        }

        /**
         * @param endDate the endDate to set.
         */
        public void setEndDate(Date endDate) {
                this.endDate = endDate;
        }

        /**
         * @return the startDate
         */
        public Date getStartDate() {
                return startDate;
        }

        /**
         * @param startDate the startDate to set.
         */
        public void setStartDate(Date startDate) {
                this.startDate = startDate;
        }

        /**
         * @return the lastUpdateOn
         */
        public Date getLastUpdateOn() {
                return lastUpdateOn;
        }

        /**
         * @param lastUpdateOn the lastUpdateOn to set.
         */
        public void setLastUpdateOn(Date lastUpdateOn) {
                this.lastUpdateOn = lastUpdateOn;
        }

        /**
         * @return the createdBy
         */
        public String getCreatedBy() {
                return createdBy;
        }

        /**
         * @param createdBy the createdBy to set.
         */
        public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
        }

        /**
         * @return the createdOn
         */
        public Date getCreatedOn() {
                return createdOn;
        }

        /**
         * @param createdOn the createdOn to set.
         */
        public void setCreatedOn(Date createdOn) {
                this.createdOn = createdOn;
        }

        /**
         * @return the personId
         */
        public Long getPersonId() {
                return personId;
        }

        /**
         * @param personId the personId to set.
         */
        public void setPersonId(Long personId) {
                this.personId = personId;
        }

        /**
         * @return the displayNameFlag
         */
        public String getDisplayNameFlag() {
                return displayNameFlag;
        }

        /**
         * @param displayNameFlag the displayNameFlag to set.
         */
        public void setDisplayNameFlag(String displayNameFlag) {
                this.displayNameFlag = displayNameFlag;
        }

        /**
         * @return the userid
         */
        public String getUserid() {
                return userid;
        }

        /**
         * @param userid the userid to set.
         */
        public void setUserid(String userid) {
                this.userid = userid;
        }

		/**
		 * @param scopedUuid the scopedUuid to set
		 */
		public void setScopedUuid(String scopedUuid) {
			this.scopedUuid = scopedUuid;
		}

		/**
		 * @return the scopedUuid
		 */
		public String getScopedUuid() {
			return scopedUuid;
		}

}
