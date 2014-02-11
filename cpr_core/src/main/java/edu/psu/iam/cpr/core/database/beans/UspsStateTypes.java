/* SVN FILE: $Id: UspsStateTypes.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@Table(name="usps_state_types")
public class UspsStateTypes implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the uspsStateType. */
        @Column(name="usps_state_type", nullable=false, length=50)
        private String uspsStateType;

        /** Contains the uspsStateTypeKey. */
        @Id
        @Column(name="usps_state_type_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_usps_state_types")
        @SequenceGenerator(name="seq_usps_state_types", sequenceName="seq_usps_state_types", allocationSize = 1, initialValue= 1)
        private Long uspsStateTypeKey;

        /**
         * Constructor
         */
        public UspsStateTypes() {
            super();
        }

        /**
         * @return the uspsStateType
         */
        public String getUspsStateType() {
                return uspsStateType;
        }

        /**
         * @param uspsStateType the uspsStateType to set.
         */
        public void setUspsStateType(String uspsStateType) {
                this.uspsStateType = uspsStateType;
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
