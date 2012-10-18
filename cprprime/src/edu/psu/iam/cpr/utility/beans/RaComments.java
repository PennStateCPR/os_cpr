/* SVN FILE: $Id: RaComments.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
@Table(name="ra_comments")
public class RaComments implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=true)
        private Date lastUpdateOn;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the registrationAuthorityKey. */
        @Column(name="registration_authority_key", nullable=false)
        private Long registrationAuthorityKey;

        /** Contains the comments. */
        @Column(name="comments", nullable=false, length=2000)
        private String comments;

        /** Contains the raCommentKey. */
        @Id
        @Column(name="ra_comment_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_ra_comments")
        @SequenceGenerator(name="seq_ra_comments", sequenceName="seq_ra_comments", allocationSize = 1, initialValue= 1)
        private Long raCommentKey;

        /**
         * Constructor
         */
        public RaComments() {
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
         * @return the registrationAuthorityKey
         */
        public Long getRegistrationAuthorityKey() {
                return registrationAuthorityKey;
        }

        /**
         * @param registrationAuthorityKey the registrationAuthorityKey to set.
         */
        public void setRegistrationAuthorityKey(Long registrationAuthorityKey) {
                this.registrationAuthorityKey = registrationAuthorityKey;
        }

        /**
         * @return the comments
         */
        public String getComments() {
                return comments;
        }

        /**
         * @param comments the comments to set.
         */
        public void setComments(String comments) {
                this.comments = comments;
        }

        /**
         * @return the raCommentKey
         */
        public Long getRaCommentKey() {
                return raCommentKey;
        }

        /**
         * @param raCommentKey the raCommentKey to set.
         */
        public void setRaCommentKey(Long raCommentKey) {
                this.raCommentKey = raCommentKey;
        }

}
