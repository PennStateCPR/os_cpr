/* SVN FILE: $Id: UspsStates.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
package edu.psu.iam.cpr.utility.beans;

import java.io.Serializable;
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
 * @author $Author: jvuccolo $
 * @version $Rev: 5084 $
 * @lastrevision $Date: 2012-09-13 10:49:56 -0400 (Thu, 13 Sep 2012) $
 */

@Entity
@Table(name="usps_states")
public class UspsStates implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the stateName. */
        @Column(name="state_name", nullable=false, length=50)
        private String stateName;

        /** Contains the stateCode. */
        @Id
        @Column(name="state_code", nullable=false, length=2)
        private String stateCode;

        /** Contains the uspsStateTypeKey. */
        @Column(name="usps_state_type_key", nullable=false)
        private Long uspsStateTypeKey;

        /**
         * Constructor
         */
        public UspsStates() {
            super();
        }

        /**
         * @return the stateName
         */
        public String getStateName() {
                return stateName;
        }

        /**
         * @param stateName the stateName to set.
         */
        public void setStateName(String stateName) {
                this.stateName = stateName;
        }

        /**
         * @return the stateCode
         */
        public String getStateCode() {
                return stateCode;
        }

        /**
         * @param stateCode the stateCode to set.
         */
        public void setStateCode(String stateCode) {
                this.stateCode = stateCode;
        }

        /**
         * @return the uspsStateTypeKey
         */
        public Long getUspsStateTypeKey() {
                return uspsStateTypeKey;
        }

        /**
         * @param uspsStateTypeKey the uspsStateTypeKey to set.
         */
        public void setUspsStateTypeKey(Long uspsStateTypeKey) {
                this.uspsStateTypeKey = uspsStateTypeKey;
        }

}
