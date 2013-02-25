/* SVN FILE: $Id: Names.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
@Table(name="names")
public class Names implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the dataTypeKey. */
        @Column(name="data_type_key", nullable=false)
        private Long dataTypeKey;

        /** Contains the suffix. */
        @Column(name="suffix", nullable=true, length=30)
        private String suffix;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains the nameMatchCode. */
        @Column(name="name_match_code", nullable=true, length=60)
        private String nameMatchCode;

        /** Contains the documentTypeKey. */
        @Column(name="document_type_key", nullable=true)
        private Long documentTypeKey;

        /** Contains the nameKey. */
        @Id
        @Column(name="name_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_names")
        @SequenceGenerator(name="seq_names", sequenceName="seq_names", allocationSize = 1, initialValue= 1)
        private Long nameKey;

        /** Contains the lastName. */
        @Column(name="last_name", nullable=true, length=60)
        private String lastName;

        /** Contains the startDate. */
        @Column(name="start_date", nullable=false)
        private Date startDate;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the firstName. */
        @Column(name="first_name", nullable=true, length=60)
        private String firstName;

        /** Contains the personId. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** Contains the middleNames. */
        @Column(name="middle_names", nullable=true, length=60)
        private String middleNames;

        /**
         * Constructor
         */
        public Names() {
            super();
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
         * @return the suffix
         */
        public String getSuffix() {
                return suffix;
        }

        /**
         * @param suffix the suffix to set.
         */
        public void setSuffix(String suffix) {
                this.suffix = suffix;
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
         * @return the endDate
         */
        public Date getEndDate() {
                return endDate;
        }

        /**
         * @param endDate the endDate to set.
         */
        public void setEndDate(Date endDate) {
                this.endDate = endDate;
        }

        /**
         * @return the nameMatchCode
         */
        public String getNameMatchCode() {
                return nameMatchCode;
        }

        /**
         * @param nameMatchCode the nameMatchCode to set.
         */
        public void setNameMatchCode(String nameMatchCode) {
                this.nameMatchCode = nameMatchCode;
        }

        /**
         * @return the documentTypeKey
         */
        public Long getDocumentTypeKey() {
                return documentTypeKey;
        }

        /**
         * @param documentTypeKey the documentTypeKey to set.
         */
        public void setDocumentTypeKey(Long documentTypeKey) {
                this.documentTypeKey = documentTypeKey;
        }

        /**
         * @return the nameKey
         */
        public Long getNameKey() {
                return nameKey;
        }

        /**
         * @param nameKey the nameKey to set.
         */
        public void setNameKey(Long nameKey) {
                this.nameKey = nameKey;
        }

        /**
         * @return the lastName
         */
        public String getLastName() {
                return lastName;
        }

        /**
         * @param lastName the lastName to set.
         */
        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        /**
         * @return the startDate
         */
        public Date getStartDate() {
                return startDate;
        }

        /**
         * @param startDate the startDate to set.
         */
        public void setStartDate(Date startDate) {
                this.startDate = startDate;
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
         * @return the firstName
         */
        public String getFirstName() {
                return firstName;
        }

        /**
         * @param firstName the firstName to set.
         */
        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        /**
         * @return the personId
         */
        public Long getPersonId() {
                return personId;
        }

        /**
         * @param personId the personId to set.
         */
        public void setPersonId(Long personId) {
                this.personId = personId;
        }

        /**
         * @return the middleNames
         */
        public String getMiddleNames() {
                return middleNames;
        }

        /**
         * @param middleNames the middleNames to set.
         */
        public void setMiddleNames(String middleNames) {
                this.middleNames = middleNames;
        }

}
