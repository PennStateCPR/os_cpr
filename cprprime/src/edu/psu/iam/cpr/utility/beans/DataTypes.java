/* SVN FILE: $Id: DataTypes.java 5096 2012-09-13 18:30:09Z jvuccolo $ */
package edu.psu.iam.cpr.utility.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database.beans
 * @author $Author: jvuccolo $
 * @version $Rev: 5096 $
 * @lastrevision $Date: 2012-09-13 14:30:09 -0400 (Thu, 13 Sep 2012) $
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
