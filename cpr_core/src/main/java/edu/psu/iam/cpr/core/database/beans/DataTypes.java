/* SVN FILE: $Id: DataTypes.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
@Table(name="data_types")
public class DataTypes implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the dataTypeDesc. */
        @Column(name="data_type_desc", nullable=true, length=200)
        private String dataTypeDesc;

        /** Contains the activeFlag. */
        @Column(name="active_flag", nullable=false, length=1)
        private String activeFlag;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the dataTypeKey. */
        @Id
        @Column(name="data_type_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_data_types")
        @SequenceGenerator(name="seq_data_types", sequenceName="seq_data_types", allocationSize = 1, initialValue= 1)
        private Long dataTypeKey;

        /** Contains the parentDataTypeKey. */
        @Column(name="parent_data_type_key", nullable=true)
        private Long parentDataTypeKey;

        /** Contains the canAssignFlag. */
        @Column(name="can_assign_flag", nullable=false, length=1)
        private String canAssignFlag;

        /** Contains the enumString. */
        @Column(name="enum_string", nullable=true, length=200)
        private String enumString;

        /** Contains the dataType. */
        @Column(name="data_type", nullable=false, length=50)
        private String dataType;

        /**
         * Constructor
         */
        public DataTypes() {
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
         * @return the dataTypeDesc
         */
        public String getDataTypeDesc() {
                return dataTypeDesc;
        }

        /**
         * @param dataTypeDesc the dataTypeDesc to set.
         */
        public void setDataTypeDesc(String dataTypeDesc) {
                this.dataTypeDesc = dataTypeDesc;
        }

        /**
         * @return the activeFlag
         */
        public String getActiveFlag() {
                return activeFlag;
        }

        /**
         * @param activeFlag the activeFlag to set.
         */
        public void setActiveFlag(String activeFlag) {
                this.activeFlag = activeFlag;
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
         * @return the dataTypeKey
         */
        public Long getDataTypeKey() {
                return dataTypeKey;
        }

        /**
         * @param dataTypeKey the dataTypeKey to set.
         */
        public void setDataTypeKey(Long dataTypeKey) {
                this.dataTypeKey = dataTypeKey;
        }

        /**
         * @return the parentDataTypeKey
         */
        public Long getParentDataTypeKey() {
                return parentDataTypeKey;
        }

        /**
         * @param parentDataTypeKey the parentDataTypeKey to set.
         */
        public void setParentDataTypeKey(Long parentDataTypeKey) {
                this.parentDataTypeKey = parentDataTypeKey;
        }

        /**
         * @return the canAssignFlag
         */
        public String getCanAssignFlag() {
                return canAssignFlag;
        }

        /**
         * @param canAssignFlag the canAssignFlag to set.
         */
        public void setCanAssignFlag(String canAssignFlag) {
                this.canAssignFlag = canAssignFlag;
        }

        /**
         * @return the enumString
         */
        public String getEnumString() {
                return enumString;
        }

        /**
         * @param enumString the enumString to set.
         */
        public void setEnumString(String enumString) {
                this.enumString = enumString;
        }

        /**
         * @return the dataType
         */
        public String getDataType() {
                return dataType;
        }

        /**
         * @param dataType the dataType to set.
         */
        public void setDataType(String dataType) {
                this.dataType = dataType;
        }

}
