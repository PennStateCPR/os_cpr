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
@Table(name="ra_application_properties")
public class RaApplicationProperties implements Serializable {

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

        /** Contains an application property selected by a registration authority. */
        @Column(name="key_name", nullable=false, length=200)
        private String keyName;

        /** Contains the user id or system identifier that last updated the record. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the value for application property selected by a registration authority. */
        @Column(name="key_value", nullable=false, length=200)
        private String keyValue;

        /** Contains a unique number that identifies a user interface application. */
        @Column(name="ui_application_key", nullable=false)
        private Long uiApplicationKey;

        /** Contains a unique number that identifies a registration authority application. */
        @Column(name="ra_application_key", nullable=false)
        private Long raApplicationKey;

        /** Contains a unique number that identifies an application property for a registration authority.  It is populated by the seq_ra_application_properties sequence. */
        @Id
        @Column(name="ra_application_properties_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_ra_application_properties")
        @SequenceGenerator(name="seq_ra_application_properties", sequenceName="seq_ra_application_properties", allocationSize = 1, initialValue= 1)
        private Long raApplicationPropertiesKey;

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

}
