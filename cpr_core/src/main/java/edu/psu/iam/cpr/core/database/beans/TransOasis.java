/* SVN FILE: $Id$ */
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
@Table(name="trans_oasis")
public class TransOasis implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** null */
        @Id
        @Column(name="trans_oasis_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_trans_oasis")
        @SequenceGenerator(name="seq_trans_oasis", sequenceName="seq_trans_oasis", allocationSize = 1, initialValue= 1)
        private Long transOasisKey;

        /** null */
        @Column(name="psu_id", nullable=true, length=9)
        private String psuId;

        /** null */
        @Column(name="status", nullable=true, length=1)
        private String status;

        /** null */
        @Column(name="userid", nullable=true, length=30)
        private String userid;

        /**
         * Constructor
         */
        public TransOasis() {
            super();
        }

        /**
         * @return the transOasisKey
         */
        public Long getTransOasisKey() {
                return transOasisKey;
        }

        /**
         * @param transOasisKey the transOasisKey to set.
         */
        public void setTransOasisKey(Long transOasisKey) {
                this.transOasisKey = transOasisKey;
        }

        /**
         * @return the psuId
         */
        public String getPsuId() {
                return psuId;
        }

        /**
         * @param psuId the psuId to set.
         */
        public void setPsuId(String psuId) {
                this.psuId = psuId;
        }

        /**
         * @return the status
         */
        public String getStatus() {
                return status;
        }

        /**
         * @param status the status to set.
         */
        public void setStatus(String status) {
                this.status = status;
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
