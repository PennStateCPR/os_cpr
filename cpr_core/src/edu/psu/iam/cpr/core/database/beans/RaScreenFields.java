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
@Table(name="ra_screen_fields")
public class RaScreenFields implements Serializable {

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

        /** Contains a flag to indicate if the user interface screen field is required.  The valid values are Y or N.  The default value is Y. */
        @Column(name="required_flag", nullable=false, length=1)
        private String requiredFlag;

        /** Contains the user id or system identifier that last updated the record. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains a unique number that identifies a user interface screen field. */
        @Column(name="ui_screen_name", nullable=false, length=30)
        private String uiScreenName;

        /** Contains a flag to indicate if, by default, the registration authority wants the field to be displayed on the user interface screen.  The valid values are Y or N.  The default value is Y. */
        @Column(name="display_flag", nullable=false, length=1)
        private String displayFlag;

        /** Contains a unique number that identifies a registration authority screen. */
        @Column(name="ra_screen_key", nullable=false)
        private Long raScreenKey;

        /** Contains a user interface field name. */
        @Column(name="ui_field_name", nullable=false, length=30)
        private String uiFieldName;

        /** Contains a unique number that identifies a registration authority screen field.  It is populated by the seq_ra_screen_field sequence. */
        @Id
        @Column(name="ra_screen_field_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_ra_screen_fields")
        @SequenceGenerator(name="seq_ra_screen_fields", sequenceName="seq_ra_screen_fields", allocationSize = 1, initialValue= 1)
        private Long raScreenFieldKey;

        /**
         * Constructor
         */
        public RaScreenFields() {
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
         * @return the requiredFlag
         */
        public String getRequiredFlag() {
                return requiredFlag;
        }

        /**
         * @param requiredFlag the requiredFlag to set.
         */
        public void setRequiredFlag(String requiredFlag) {
                this.requiredFlag = requiredFlag;
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
         * @return the uiScreenName
         */
        public String getUiScreenName() {
                return uiScreenName;
        }

        /**
         * @param uiScreenName the uiScreenName to set.
         */
        public void setUiScreenName(String uiScreenName) {
                this.uiScreenName = uiScreenName;
        }

        /**
         * @return the displayFlag
         */
        public String getDisplayFlag() {
                return displayFlag;
        }

        /**
         * @param displayFlag the displayFlag to set.
         */
        public void setDisplayFlag(String displayFlag) {
                this.displayFlag = displayFlag;
        }

        /**
         * @return the raScreenKey
         */
        public Long getRaScreenKey() {
                return raScreenKey;
        }

        /**
         * @param raScreenKey the raScreenKey to set.
         */
        public void setRaScreenKey(Long raScreenKey) {
                this.raScreenKey = raScreenKey;
        }

        /**
         * @return the uiFieldName
         */
        public String getUiFieldName() {
                return uiFieldName;
        }

        /**
         * @param uiFieldName the uiFieldName to set.
         */
        public void setUiFieldName(String uiFieldName) {
                this.uiFieldName = uiFieldName;
        }

        /**
         * @return the raScreenFieldKey
         */
        public Long getRaScreenFieldKey() {
                return raScreenFieldKey;
        }

        /**
         * @param raScreenFieldKey the raScreenFieldKey to set.
         */
        public void setRaScreenFieldKey(Long raScreenFieldKey) {
                this.raScreenFieldKey = raScreenFieldKey;
        }

}
