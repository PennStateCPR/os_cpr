/* SVN FILE: $Id: PersonAffiliation.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@Table(name="person_affiliation")
public class PersonAffiliation implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the startDate. */
        @Column(name="start_date", nullable=false)
        private Date startDate;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the primaryFlag. */
        @Column(name="primary_flag", nullable=false, length=1)
        private String primaryFlag;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the affiliationKey. */
        @Column(name="affiliation_key", nullable=false)
        private Long affiliationKey;

        /** Contains the personId. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains the personAffiliationKey. */
        @Id
        @Column(name="person_affiliation_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_person_affiliation")
        @SequenceGenerator(name="seq_person_affiliation", sequenceName="seq_person_affiliation", allocationSize = 1, initialValue= 1)
        private Long personAffiliationKey;

        /** Contains the exceptionFlag. */
        @Column(name="exception_flag", nullable=false, length=1)
        private String exceptionFlag;

        /** Contains the exceptionComments. */
        @Column(name="exception_comments", nullable=true, length=500)
        private String exceptionComments;

        /**
         * Constructor
         */
        public PersonAffiliation() {
            super();
        }

        /**
         * COPY Constructor, make sure you include this when you rebuild the bean.
         * @param personAffiliation contains the the affiliation to be copied.
         */
        public PersonAffiliation(final PersonAffiliation personAffiliation) {
        	
        	if (personAffiliation == null) {
        		return;
        	}
        	
        	setAffiliationKey(personAffiliation.getAffiliationKey());
        	setCreatedBy(personAffiliation.getCreatedBy());
        	setCreatedOn(personAffiliation.getCreatedOn());
        	setEndDate(personAffiliation.getEndDate());
        	setExceptionComments(personAffiliation.getExceptionComments());
        	setExceptionFlag(personAffiliation.getExceptionFlag());
        	setLastUpdateBy(personAffiliation.getLastUpdateBy());
        	setLastUpdateOn(personAffiliation.getLastUpdateOn());
        	setPersonAffiliationKey(personAffiliation.getPersonAffiliationKey());
        	setPersonId(personAffiliation.getPersonId());
        	setPrimaryFlag(personAffiliation.getPrimaryFlag());
        	setStartDate(personAffiliation.getStartDate());        
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
         * @return the affiliationKey
         */
        public Long getAffiliationKey() {
                return affiliationKey;
        }

        /**
         * @param affiliationKey the affiliationKey to set.
         */
        public void setAffiliationKey(Long affiliationKey) {
                this.affiliationKey = affiliationKey;
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
         * @return the personAffiliationKey
         */
        public Long getPersonAffiliationKey() {
                return personAffiliationKey;
        }

        /**
         * @param personAffiliationKey the personAffiliationKey to set.
         */
        public void setPersonAffiliationKey(Long personAffiliationKey) {
                this.personAffiliationKey = personAffiliationKey;
        }

        /**
         * @return the exceptionFlag
         */
        public String getExceptionFlag() {
                return exceptionFlag;
        }

        /**
         * @param exceptionFlag the exceptionFlag to set.
         */
        public void setExceptionFlag(String exceptionFlag) {
                this.exceptionFlag = exceptionFlag;
        }

        /**
         * @return the exceptionComments
         */
        public String getExceptionComments() {
                return exceptionComments;
        }

        /**
         * @param exceptionComments the exceptionComments to set.
         */
        public void setExceptionComments(String exceptionComments) {
                this.exceptionComments = exceptionComments;
        }

}
