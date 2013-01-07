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
@Table(name="ra_applications")
public class RaApplications implements Serializable {

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

        /** Contains a unique number that identifies a registration authority. */
        @Column(name="registration_authority_key", nullable=false)
        private Long registrationAuthorityKey;

        /** Contains a flag to indicate if a registration authority is allowed to collect the social security number in the application.  The valid values are Y or N.  The default value is N. */
        @Column(name="allow_ssn_collection_flag", nullable=false, length=1)
        private String allowSsnCollectionFlag;

        /** Contains a flag to indicate if a registration authority access to an application has been suspended.  The valid values are Y or N.  The default value is Y. */
        @Column(name="suspend_flag", nullable=false, length=1)
        private String suspendFlag;

        /** Contains a unique number that identifies a user interface application. */
        @Column(name="ui_application_key", nullable=false)
        private Long uiApplicationKey;

        /** Contains a unique number that identifies an application that a registration authority can access.  It is populated by the seq_ra_applications sequence. */
        @Id
        @Column(name="ra_application_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_ra_applications")
        @SequenceGenerator(name="seq_ra_applications", sequenceName="seq_ra_applications", allocationSize = 1, initialValue= 1)
        private Long raApplicationKey;

        /**
         * Constructor
         */
        public RaApplications() {
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
         * @return the allowSsnCollectionFlag
         */
        public String getAllowSsnCollectionFlag() {
                return allowSsnCollectionFlag;
        }

        /**
         * @param allowSsnCollectionFlag the allowSsnCollectionFlag to set.
         */
        public void setAllowSsnCollectionFlag(String allowSsnCollectionFlag) {
                this.allowSsnCollectionFlag = allowSsnCollectionFlag;
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

        /**
         * @return the raApplicationKey
         */
        public Long getRaApplicationKey() {
                return raApplicationKey;
        }

        /**
         * @param raApplicationKey the raApplicationKey to set.
         */
        public void setRaApplicationKey(Long raApplicationKey) {
                this.raApplicationKey = raApplicationKey;
        }

}
