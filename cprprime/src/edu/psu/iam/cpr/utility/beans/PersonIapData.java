/* SVN FILE: $Id: PersonIapData.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
@Table(name="person_iap_data")
public class PersonIapData implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the dataElementDocTypeKey. */
        @Column(name="data_element_doc_type_key", nullable=false)
        private Long dataElementDocTypeKey;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the iapDataValueKey. */
        @Column(name="iap_data_value_key", nullable=false)
        private Long iapDataValueKey;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the personIapVpKey. */
        @Column(name="person_iap_vp_key", nullable=false)
        private Long personIapVpKey;

        /** Contains the personIapDataKey. */
        @Id
        @Column(name="person_iap_data_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_person_iap_data")
        @SequenceGenerator(name="seq_person_iap_data", sequenceName="seq_person_iap_data", allocationSize = 1, initialValue= 1)
        private Long personIapDataKey;

        /** Contains the iapDocumentKey. */
        @Column(name="iap_document_key", nullable=false)
        private Long iapDocumentKey;

        /**
         * Constructor
         */
        public PersonIapData() {
            super();
        }

        /**
         * @return the dataElementDocTypeKey
         */
        public Long getDataElementDocTypeKey() {
                return dataElementDocTypeKey;
        }

        /**
         * @param dataElementDocTypeKey the dataElementDocTypeKey to set.
         */
        public void setDataElementDocTypeKey(Long dataElementDocTypeKey) {
                this.dataElementDocTypeKey = dataElementDocTypeKey;
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
         * @return the iapDataValueKey
         */
        public Long getIapDataValueKey() {
                return iapDataValueKey;
        }

        /**
         * @param iapDataValueKey the iapDataValueKey to set.
         */
        public void setIapDataValueKey(Long iapDataValueKey) {
                this.iapDataValueKey = iapDataValueKey;
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
         * @return the personIapVpKey
         */
        public Long getPersonIapVpKey() {
                return personIapVpKey;
        }

        /**
         * @param personIapVpKey the personIapVpKey to set.
         */
        public void setPersonIapVpKey(Long personIapVpKey) {
                this.personIapVpKey = personIapVpKey;
        }

        /**
         * @return the personIapDataKey
         */
        public Long getPersonIapDataKey() {
                return personIapDataKey;
        }

        /**
         * @param personIapDataKey the personIapDataKey to set.
         */
        public void setPersonIapDataKey(Long personIapDataKey) {
                this.personIapDataKey = personIapDataKey;
        }

        /**
         * @return the iapDocumentKey
         */
        public Long getIapDocumentKey() {
                return iapDocumentKey;
        }

        /**
         * @param iapDocumentKey the iapDocumentKey to set.
         */
        public void setIapDocumentKey(Long iapDocumentKey) {
                this.iapDocumentKey = iapDocumentKey;
        }

}
