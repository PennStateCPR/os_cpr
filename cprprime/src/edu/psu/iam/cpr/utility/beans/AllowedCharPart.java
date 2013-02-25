/* SVN FILE: $Id: AllowedCharPart.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
@Table(name="allowed_char_part")
public class AllowedCharPart implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the charPart. */
        @Id
        @Column(name="char_part", nullable=false, length=30)
        private String charPart;

        /**
         * Constructor
         */
        public AllowedCharPart() {
            super();
        }

        /**
         * @return the charPart
         */
        public String getCharPart() {
                return charPart;
        }

        /**
         * @param charPart the charPart to set.
         */
        public void setCharPart(String charPart) {
                this.charPart = charPart;
        }

}
