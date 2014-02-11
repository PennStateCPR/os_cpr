/* SVN FILE: $Id: GeneratedIdentity.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
@Table(name="generated_identity")
public class GeneratedIdentity implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the numPart. */
        @Column(name="num_part", nullable=true)
        private Long numPart;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the generatedIdentityKey. */
        @Id
        @Column(name="generated_identity_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_generated_identity")
        @SequenceGenerator(name="seq_generated_identity", sequenceName="seq_generated_identity", allocationSize = 1, initialValue= 1)
        private Long generatedIdentityKey;

        /** Contains the charPart. */
        @Column(name="char_part", nullable=true, length=30)
        private String charPart;

        /** Contains the personId. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** Contains the setId. */
        @Column(name="set_id", nullable=false)
        private Long setId;

        /** Contains the generatedIdentity. */
        @Column(name="generated_identity", nullable=false, length=30)
        private String generatedIdentity;

        /**
         * Constructor
         */
        public GeneratedIdentity() {
            super();
        }

        /**
         * @return the numPart
         */
        public Long getNumPart() {
                return numPart;
        }

        /**
         * @param numPart the numPart to set.
         */
        public void setNumPart(Long numPart) {
                this.numPart = numPart;
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
         * @return the generatedIdentityKey
         */
        public Long getGeneratedIdentityKey() {
                return generatedIdentityKey;
        }

        /**
         * @param generatedIdentityKey the generatedIdentityKey to set.
         */
        public void setGeneratedIdentityKey(Long generatedIdentityKey) {
                this.generatedIdentityKey = generatedIdentityKey;
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

        /**
         * @return the personId
         */
        public Long getPersonId() {
                return personId;
        }

        /**
         * @param personId the personId to set.
         */
        public void setPersonId(Long personId) {
                this.personId = personId;
        }

        /**
         * @return the setId
         */
        public Long getSetId() {
                return setId;
        }

        /**
         * @param setId the setId to set.
         */
        public void setSetId(Long setId) {
                this.setId = setId;
        }

        /**
         * @return the generatedIdentity
         */
        public String getGeneratedIdentity() {
                return generatedIdentity;
        }

        /**
         * @param generatedIdentity the generatedIdentity to set.
         */
        public void setGeneratedIdentity(String generatedIdentity) {
                this.generatedIdentity = generatedIdentity;
        }

}
