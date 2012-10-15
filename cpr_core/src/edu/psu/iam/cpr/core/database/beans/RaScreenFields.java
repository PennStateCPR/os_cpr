/* SVN FILE: $Id: RaScreenFields.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
@Table(name="ra_screen_fields")
public class RaScreenFields implements Serializable {

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

        /** Contains the requiredFlag. */
        @Column(name="required_flag", nullable=false, length=1)
        private String requiredFlag;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the uiScreenFieldKey. */
        @Column(name="ui_screen_field_key", nullable=false)
        private Long uiScreenFieldKey;

        /** Contains the displayFlag. */
        @Column(name="display_flag", nullable=false, length=1)
        private String displayFlag;

        /** Contains the raScreenFieldKey. */
        @Id
        @Column(name="ra_screen_field_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_ra_screen_fields")
        @SequenceGenerator(name="seq_ra_screen_fields", sequenceName="seq_ra_screen_fields", allocationSize = 1, initialValue= 1)
        private Long raScreenFieldKey;

        /**
         * Constructor
         */
        public RaScreenFields() {
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
         * @return the requiredFlag
         */
        public String getRequiredFlag() {
                return requiredFlag;
        }

        /**
         * @param requiredFlag the requiredFlag to set.
         */
        public void setRequiredFlag(String requiredFlag) {
                this.requiredFlag = requiredFlag;
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
         * @return the uiScreenFieldKey
         */
        public Long getUiScreenFieldKey() {
                return uiScreenFieldKey;
        }

        /**
         * @param uiScreenFieldKey the uiScreenFieldKey to set.
         */
        public void setUiScreenFieldKey(Long uiScreenFieldKey) {
                this.uiScreenFieldKey = uiScreenFieldKey;
        }

        /**
         * @return the displayFlag
         */
        public String getDisplayFlag() {
                return displayFlag;
        }

        /**
         * @param displayFlag the displayFlag to set.
         */
        public void setDisplayFlag(String displayFlag) {
                this.displayFlag = displayFlag;
        }

        /**
         * @return the raScreenFieldKey
         */
        public Long getRaScreenFieldKey() {
                return raScreenFieldKey;
        }

        /**
         * @param raScreenFieldKey the raScreenFieldKey to set.
         */
        public void setRaScreenFieldKey(Long raScreenFieldKey) {
                this.raScreenFieldKey = raScreenFieldKey;
        }

}
