/* SVN FILE: $Id: ApplicationProperties.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
@Table(name="application_properties")
public class ApplicationProperties implements Serializable {

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

        /** Contains the keyName. */
        @Column(name="key_name", nullable=false, length=200)
        private String keyName;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the keyValue. */
        @Column(name="key_value", nullable=false, length=200)
        private String keyValue;

        /** Contains the raUiApplicationKey. */
        @Column(name="ra_ui_application_key", nullable=false)
        private Long raUiApplicationKey;

        /** Contains the applicationPropertiesKey. */
        @Id
        @Column(name="application_properties_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_application_properties")
        @SequenceGenerator(name="seq_application_properties", sequenceName="seq_application_properties", allocationSize = 1, initialValue= 1)
        private Long applicationPropertiesKey;

        /**
         * Constructor
         */
        public ApplicationProperties() {
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
         * @return the keyName
         */
        public String getKeyName() {
                return keyName;
        }

        /**
         * @param keyName the keyName to set.
         */
        public void setKeyName(String keyName) {
                this.keyName = keyName;
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
         * @return the keyValue
         */
        public String getKeyValue() {
                return keyValue;
        }

        /**
         * @param keyValue the keyValue to set.
         */
        public void setKeyValue(String keyValue) {
                this.keyValue = keyValue;
        }

        /**
         * @return the raUiApplicationKey
         */
        public Long getRaUiApplicationKey() {
                return raUiApplicationKey;
        }

        /**
         * @param raUiApplicationKey the raUiApplicationKey to set.
         */
        public void setRaUiApplicationKey(Long raUiApplicationKey) {
                this.raUiApplicationKey = raUiApplicationKey;
        }

        /**
         * @return the applicationPropertiesKey
         */
        public Long getApplicationPropertiesKey() {
                return applicationPropertiesKey;
        }

        /**
         * @param applicationPropertiesKey the applicationPropertiesKey to set.
         */
        public void setApplicationPropertiesKey(Long applicationPropertiesKey) {
                this.applicationPropertiesKey = applicationPropertiesKey;
        }

}
