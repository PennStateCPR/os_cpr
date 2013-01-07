/* SVN FILE: $Id: BuildBean.java 5970 2013-01-04 15:50:31Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.beans;

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
 * @version $Rev: 5970 $
 * @lastrevision $Date: 2013-01-04 10:50:31 -0500 (Fri, 04 Jan 2013) $
 */

@Entity
@Table(name="ui_screen_fields")
public class UiScreenFields implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the maxLength. */
        @Column(name="max_length", nullable=true)
        private Long maxLength;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the fieldWidth. */
        @Column(name="field_width", nullable=true)
        private Long fieldWidth;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the fieldHeight. */
        @Column(name="field_height", nullable=true)
        private Long fieldHeight;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the requiredFlag. */
        @Column(name="required_flag", nullable=false, length=1)
        private String requiredFlag;

        /** Contains the uiFieldType. */
        @Column(name="ui_field_type", nullable=false, length=25)
        private String uiFieldType;

        /** Contains the displayFlag. */
        @Column(name="display_flag", nullable=false, length=1)
        private String displayFlag;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the uiScreenName. */
        @Id
        @Column(name="ui_screen_name", nullable=false, length=30)
        private String uiScreenName;

        /** Contains the activeFlag. */
        @Column(name="active_flag", nullable=false, length=1)
        private String activeFlag;

        /** Contains the uiFieldName. */
        @Id
        @Column(name="ui_field_name", nullable=false, length=30)
        private String uiFieldName;

        /**
         * Constructor
         */
        public UiScreenFields() {
            super();
        }

        /**
         * @return the maxLength
         */
        public Long getMaxLength() {
                return maxLength;
        }

        /**
         * @param maxLength the maxLength to set.
         */
        public void setMaxLength(Long maxLength) {
                this.maxLength = maxLength;
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
         * @return the fieldWidth
         */
        public Long getFieldWidth() {
                return fieldWidth;
        }

        /**
         * @param fieldWidth the fieldWidth to set.
         */
        public void setFieldWidth(Long fieldWidth) {
                this.fieldWidth = fieldWidth;
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
         * @return the fieldHeight
         */
        public Long getFieldHeight() {
                return fieldHeight;
        }

        /**
         * @param fieldHeight the fieldHeight to set.
         */
        public void setFieldHeight(Long fieldHeight) {
                this.fieldHeight = fieldHeight;
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
         * @return the uiFieldType
         */
        public String getUiFieldType() {
                return uiFieldType;
        }

        /**
         * @param uiFieldType the uiFieldType to set.
         */
        public void setUiFieldType(String uiFieldType) {
                this.uiFieldType = uiFieldType;
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
         * @return the uiScreenName
         */
        public String getUiScreenName() {
                return uiScreenName;
        }

        /**
         * @param uiScreenName the uiScreenName to set.
         */
        public void setUiScreenName(String uiScreenName) {
                this.uiScreenName = uiScreenName;
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
         * @return the uiFieldName
         */
        public String getUiFieldName() {
                return uiFieldName;
        }

        /**
         * @param uiFieldName the uiFieldName to set.
         */
        public void setUiFieldName(String uiFieldName) {
                this.uiFieldName = uiFieldName;
        }

}
