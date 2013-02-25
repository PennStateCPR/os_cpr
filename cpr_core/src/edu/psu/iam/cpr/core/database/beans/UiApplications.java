/* SVN FILE: $Id$ */
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
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database.beans
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */

@Entity
@Table(name="ui_applications")
public class UiApplications implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the user id or system identifier that created the record. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the date and time that the record was last updated. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the date and time that the record was created. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the user id or system identifier that last updated the record. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the name of a user interface application. */
        @Column(name="application_name", nullable=false, length=200)
        private String applicationName;

        /** Contains the description of a user interface application. */
        @Column(name="application_desc", nullable=false, length=1000)
        private String applicationDesc;

        /** Contains a flag to indicate if a user interface application is suspended.  The valid values are Y or N.  The default value is Y. */
        @Column(name="suspend_flag", nullable=false, length=1)
        private String suspendFlag;

        /** Contains a unique number that identifies a user interface application.  It is populated by the seq_ui_application sequence. */
        @Id
        @Column(name="ui_application_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_ui_applications")
        @SequenceGenerator(name="seq_ui_applications", sequenceName="seq_ui_applications", allocationSize = 1, initialValue= 1)
        private Long uiApplicationKey;

        /**
         * Constructor
         */
        public UiApplications() {
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

        /**
         * @return the uiApplicationKey
         */
        public Long getUiApplicationKey() {
                return uiApplicationKey;
        }

        /**
         * @param uiApplicationKey the uiApplicationKey to set.
         */
        public void setUiApplicationKey(Long uiApplicationKey) {
                this.uiApplicationKey = uiApplicationKey;
        }

}
