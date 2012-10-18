/* SVN FILE: $Id: UspsStateTypes.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
package edu.psu.iam.cpr.utility.beans;

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
