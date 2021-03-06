/* SVN FILE: $Id: RaUiApplication.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
@Table(name="ra_ui_application")
public class RaUiApplication implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

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

        /** Contains the applicationName. */
        @Column(name="application_name", nullable=false, length=200)
        private String applicationName;

        /** Contains the registrationAuthorityKey. */
        @Column(name="registration_authority_key", nullable=false)
        private Long registrationAuthorityKey;

        /** Contains the raUiApplicationKey. */
        @Id
        @Column(name="ra_ui_application_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_ra_ui_application")
        @SequenceGenerator(name="seq_ra_ui_application", sequenceName="seq_ra_ui_application", allocationSize = 1, initialValue= 1)
        private Long raUiApplicationKey;

        /** Contains the applicationDesc. */
        @Column(name="application_desc", nullable=false, length=1000)
        private String applicationDesc;

        /** Contains the suspendFlag. */
        @Column(name="suspend_flag", nullable=false, length=1)
        private String suspendFlag;

        /**
         * Constructor
         */
        public RaUiApplication() {
            super();
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
         * @return the applicationName
         */
        public String getApplicationName() {
                return applicationName;
        }

        /**
         * @param applicationName the applicationName to set.
         */
        public void setApplicationName(String applicationName) {
                this.applicationName = applicationName;
        }

        /**
         * @return the registrationAuthorityKey
         */
        public Long getRegistrationAuthorityKey() {
                return registrationAuthorityKey;
        }

        /**
         * @param registrationAuthorityKey the registrationAuthorityKey to set.
         */
        public void setRegistrationAuthorityKey(Long registrationAuthorityKey) {
                this.registrationAuthorityKey = registrationAuthorityKey;
        }

        /**
         * @return the raUiApplicationKey
         */
        public Long getRaUiApplicationKey() {
                return raUiApplicationKey;
        }

        /**
         * @param raUiApplicationKey the raUiApplicationKey to set.
         */
        public void setRaUiApplicationKey(Long raUiApplicationKey) {
                this.raUiApplicationKey = raUiApplicationKey;
        }

        /**
         * @return the applicationDesc
         */
        public String getApplicationDesc() {
                return applicationDesc;
        }

        /**
         * @param applicationDesc the applicationDesc to set.
         */
        public void setApplicationDesc(String applicationDesc) {
                this.applicationDesc = applicationDesc;
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

}
