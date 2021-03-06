/* SVN FILE: $Id: AppointmentCs.java 7846 2013-08-14 15:42:11Z smc1 $ */
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
 * @author $Author: smc1 $
 * @version $Rev: 7846 $
 * @lastrevision $Date: 2013-08-14 11:42:11 -0400 (Wed, 14 Aug 2013) $
 */

@Entity
@Table(name="appointment_cs")
public class AppointmentCs implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the user id or system identifier that created the record. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the date and time that the record was last updated. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Flag indicating whether appointment is active or not.  Valid values are 'Y' or 'N'. Default value is 'N'. */
        @Column(name="active_flag", nullable=false, length=1)
        private String activeFlag;

        /** Contains the date and time that the record was created. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the user id or system identifier that last updated the record. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Employee appointment code.  Three character code representing an employees appointment status. */
        @Id
        @Column(name="appt_code", nullable=false, length=3)
        private String apptCode;

        /** Appointment name or brief description. */
        @Column(name="appt_desc", nullable=true, length=40)
        private String apptDesc;

        /** Contains the source of the record IBIS or HMC. */
        @Column(name="appt_source", nullable=false)
        private String apptSource;
        
        /**
         * Constructor
         */
        public AppointmentCs() {
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
         * @return the apptSource
         */
        public String getApptSource() {
     			return apptSource;
     		}

        
        /**
         * @param apptSource the apptSource to set.
         */
     		public void setApptSource(String apptSource) {
     			this.apptSource = apptSource;
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
         * @return the apptCode
         */
        public String getApptCode() {
                return apptCode;
        }

        /**
         * @param apptCode the apptCode to set.
         */
        public void setApptCode(String apptCode) {
                this.apptCode = apptCode;
        }

        /**
         * @return the apptDesc
         */
        public String getApptDesc() {
                return apptDesc;
        }

        /**
         * @param apptDesc the apptDesc to set.
         */
        public void setApptDesc(String apptDesc) {
                this.apptDesc = apptDesc;
        }

}
