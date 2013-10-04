package edu.psu.iam.cpr.utility.beans;
/* SVN FILE: $Id: AcademicCollegeCs.java 7368 2013-05-23 20:11:49Z llg5 $ */

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
 * @package edu.psu.iam.cpr.utility.beans
 * @author $Author: llg5 $
 * @version $Rev: 7368 $
 * @lastrevision $Date: 2013-05-23 16:11:49 -0400 (Thu, 23 May 2013) $
 */

@Entity
@Table(name="academic_college_cs")
public class AcademicCollegeCs implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the user id or system identifier that created the record. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the date and time that the record was last updated. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Indicates whether the academic college is active or not. Values are either 'Y" or 'N'.  Default is 'N'. */
        @Column(name="active_flag", nullable=false, length=1)
        private String activeFlag;

        /** Contains the date and time that the record was created. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the user id or system identifier that last updated the record. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Name of the Academic College. */
        @Column(name="college_name", nullable=false, length=100)
        private String collegeName;

        /** Two character code that uniquely identifies an academic college. */
        @Id
        @Column(name="college_code", nullable=false, length=2)
        private String collegeCode;

        /**
         * Constructor
         */
        public AcademicCollegeCs() {
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
         * @return the collegeName
         */
        public String getCollegeName() {
                return collegeName;
        }

        /**
         * @param collegeName the collegeName to set.
         */
        public void setCollegeName(String collegeName) {
                this.collegeName = collegeName;
        }

        /**
         * @return the collegeCode
         */
        public String getCollegeCode() {
                return collegeCode;
        }

        /**
         * @param collegeCode the collegeCode to set.
         */
        public void setCollegeCode(String collegeCode) {
                this.collegeCode = collegeCode;
        }

}
