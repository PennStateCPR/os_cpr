/* SVN FILE: $Id: GroupAccess.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
@Table(name="group_access")
public class GroupAccess implements Serializable {

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

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the webServiceKey. */
        @Column(name="web_service_key", nullable=false)
        private Long webServiceKey;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains the groupAccessKey. */
        @Id
        @Column(name="group_access_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_group_access")
        @SequenceGenerator(name="seq_group_access", sequenceName="seq_group_access", allocationSize = 1, initialValue= 1)
        private Long groupAccessKey;

        /** Contains the suspendFlag. */
        @Column(name="suspend_flag", nullable=false, length=1)
        private String suspendFlag;

        /** Contains the raGroupKey. */
        @Column(name="ra_group_key", nullable=false)
        private Long raGroupKey;

        /**
         * Constructor
         */
        public GroupAccess() {
            super();
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
         * @return the webServiceKey
         */
        public Long getWebServiceKey() {
                return webServiceKey;
        }

        /**
         * @param webServiceKey the webServiceKey to set.
         */
        public void setWebServiceKey(Long webServiceKey) {
                this.webServiceKey = webServiceKey;
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
         * @return the groupAccessKey
         */
        public Long getGroupAccessKey() {
                return groupAccessKey;
        }

        /**
         * @param groupAccessKey the groupAccessKey to set.
         */
        public void setGroupAccessKey(Long groupAccessKey) {
                this.groupAccessKey = groupAccessKey;
        }

        /**
         * @return the suspendFlag
         */
        public String getSuspendFlag() {
                return suspendFlag;
        }

        /**
         * @param suspendFlag the suspendFlag to set.
         */
        public void setSuspendFlag(String suspendFlag) {
                this.suspendFlag = suspendFlag;
        }

        /**
         * @return the raGroupKey
         */
        public Long getRaGroupKey() {
                return raGroupKey;
        }

        /**
         * @param raGroupKey the raGroupKey to set.
         */
        public void setRaGroupKey(Long raGroupKey) {
                this.raGroupKey = raGroupKey;
        }

}
