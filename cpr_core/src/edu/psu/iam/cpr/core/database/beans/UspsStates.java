/* SVN FILE: $Id: UspsStates.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * Copyright 2012 The Pennsylvania State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @package edu.psu.iam.cpr.core.database.beans
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
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
