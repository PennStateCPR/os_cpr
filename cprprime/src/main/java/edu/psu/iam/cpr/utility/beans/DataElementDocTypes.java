/* SVN FILE: $Id: DataElementDocTypes.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
@Table(name="data_element_doc_types")
public class DataElementDocTypes implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the dataElementDocTypeKey. */
        @Id
        @Column(name="data_element_doc_type_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_data_element_doc_types")
        @SequenceGenerator(name="seq_data_element_doc_types", sequenceName="seq_data_element_doc_types", allocationSize = 1, initialValue= 1)
        private Long dataElementDocTypeKey;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the documentTypeKey. */
        @Column(name="document_type_key", nullable=true)
        private Long documentTypeKey;

        /** Contains the dataElementTypeKey. */
        @Column(name="data_element_type_key", nullable=false)
        private Long dataElementTypeKey;

        /**
         * Constructor
         */
        public DataElementDocTypes() {
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
         * @return the dataElementTypeKey
         */
        public Long getDataElementTypeKey() {
                return dataElementTypeKey;
        }

        /**
         * @param dataElementTypeKey the dataElementTypeKey to set.
         */
        public void setDataElementTypeKey(Long dataElementTypeKey) {
                this.dataElementTypeKey = dataElementTypeKey;
        }

}
