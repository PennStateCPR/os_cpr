/* SVN FILE: $Id: UiScreenFields.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
package edu.psu.iam.cpr.utility.beans;

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
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database.beans
 * @author $Author: jvuccolo $
 * @version $Rev: 5084 $
 * @lastrevision $Date: 2012-09-13 10:49:56 -0400 (Thu, 13 Sep 2012) $
 */

@Entity
@Table(name="ui_screen_fields")
public class UiScreenFields implements Serializable {

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
        @Id
        @Column(name="ui_screen_field_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_ui_screen_fields")
        @SequenceGenerator(name="seq_ui_screen_fields", sequenceName="seq_ui_screen_fields", allocationSize = 1, initialValue= 1)
        private Long uiScreenFieldKey;

        /** Contains the displayFlag. */
        @Column(name="display_flag", nullable=false, length=1)
        private String displayFlag;

        /** Contains the uiScreenKey. */
        @Column(name="ui_screen_key", nullable=false)
        private Long uiScreenKey;

        /** Contains the uiFieldName. */
        @Column(name="ui_field_name", nullable=false, length=30)
        private String uiFieldName;

        /**
         * Constructor
         */
        public UiScreenFields() {
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
         * @return the uiScreenKey
         */
        public Long getUiScreenKey() {
                return uiScreenKey;
        }

        /**
         * @param uiScreenKey the uiScreenKey to set.
         */
        public void setUiScreenKey(Long uiScreenKey) {
                this.uiScreenKey = uiScreenKey;
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
