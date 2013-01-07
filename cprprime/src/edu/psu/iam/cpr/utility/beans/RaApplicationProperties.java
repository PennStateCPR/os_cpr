/* SVN FILE: $Id: BuildBean.java 5970 2013-01-04 15:50:31Z jvuccolo $ */
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
 * @version $Rev: 5970 $
 * @lastrevision $Date: 2013-01-04 10:50:31 -0500 (Fri, 04 Jan 2013) $
 */

@Entity
@Table(name="ra_application_properties")
public class RaApplicationProperties implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the raApplicationPropertiesKey. */
        @Id
        @Column(name="ra_application_properties_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_ra_application_properties")
        @SequenceGenerator(name="seq_ra_application_properties", sequenceName="seq_ra_application_properties", allocationSize = 1, initialValue= 1)
        private Long raApplicationPropertiesKey;

        /** Contains the raApplicationKey. */
        @Column(name="ra_application_key", nullable=false)
        private Long raApplicationKey;

        /** Contains the keyName. */
        @Column(name="key_name", nullable=false, length=200)
        private String keyName;

        /** Contains the uiApplicationKey. */
        @Column(name="ui_application_key", nullable=false)
        private Long uiApplicationKey;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the keyValue. */
        @Column(name="key_value", nullable=false, length=200)
        private String keyValue;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /**
         * Constructor
         */
        public RaApplicationProperties() {
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
         * @return the raApplicationPropertiesKey
         */
        public Long getRaApplicationPropertiesKey() {
                return raApplicationPropertiesKey;
        }

        /**
         * @param raApplicationPropertiesKey the raApplicationPropertiesKey to set.
         */
        public void setRaApplicationPropertiesKey(Long raApplicationPropertiesKey) {
                this.raApplicationPropertiesKey = raApplicationPropertiesKey;
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

}
