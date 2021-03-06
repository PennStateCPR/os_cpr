/* SVN FILE: $Id: EmployeeStatusCs.java 7729 2013-07-16 17:43:10Z slk24 $ */
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
 * @author $Author: slk24 $
 * @version $Rev: 7729 $
 * @lastrevision $Date: 2013-07-16 13:43:10 -0400 (Tue, 16 Jul 2013) $
 */

@Entity
@Table(name="employee_status_cs")
public class EmployeeStatusCs implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the user id or system identifier that created the record. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the date and time that the record was last updated. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Flag indicating whether employee status is active or not. Valid values are Y or N. Default value is N. */
        @Column(name="active_flag", nullable=false, length=1)
        private String activeFlag;

        /** Contains the date and time that the record was created. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the user id or system identifier that last updated the record. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Description of the emplyoee status. */
        @Column(name="status_desc", nullable=true, length=40)
        private String statusDesc;

        /** Unique three character employee status code.  Examples include ACT = ACTIVE, TER = TERMINATED... */
        @Id
        @Column(name="status_code", nullable=false, length=3)
        private String statusCode;

        /**
         * Constructor
         */
        public EmployeeStatusCs() {
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
         * @return the statusDesc
         */
        public String getStatusDesc() {
                return statusDesc;
        }

        /**
         * @param statusDesc the statusDesc to set.
         */
        public void setStatusDesc(String statusDesc) {
                this.statusDesc = statusDesc;
        }

        /**
         * @return the statusCode
         */
        public String getStatusCode() {
                return statusCode;
        }

        /**
         * @param statusCode the statusCode to set.
         */
        public void setStatusCode(String statusCode) {
                this.statusCode = statusCode;
        }

}
