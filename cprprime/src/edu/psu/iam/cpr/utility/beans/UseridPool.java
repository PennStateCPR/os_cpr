/* SVN FILE: $Id: UseridPool.java 5113 2012-09-14 11:59:10Z jvuccolo $ */
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
 * @version $Rev: 5113 $
 * @lastrevision $Date: 2012-09-14 07:59:10 -0400 (Fri, 14 Sep 2012) $
 */

@Entity
@Table(name="userid_pool")
public class UseridPool implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the numPart. */
        @Column(name="num_part", nullable=false)
        private Long numPart;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the charPart. */
        @Column(name="char_part", nullable=false, length=30)
        private String charPart;

        /** Contains the useridPoolKey. */
        @Id
        @Column(name="userid_pool_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_userid_pool")
        @SequenceGenerator(name="seq_userid_pool", sequenceName="seq_userid_pool", allocationSize = 1, initialValue= 1)
        private Long useridPoolKey;

        /** Contains the userid. */
        @Column(name="userid", nullable=false, length=30)
        private String userid;

        /**
         * Constructor
         */
        public UseridPool() {
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
         * @return the useridPoolKey
         */
        public Long getUseridPoolKey() {
                return useridPoolKey;
        }

        /**
         * @param useridPoolKey the useridPoolKey to set.
         */
        public void setUseridPoolKey(Long useridPoolKey) {
                this.useridPoolKey = useridPoolKey;
        }

        /**
         * @return the userid
         */
        public String getUserid() {
                return userid;
        }

        /**
         * @param userid the userid to set.
         */
        public void setUserid(String userid) {
                this.userid = userid;
        }

}
