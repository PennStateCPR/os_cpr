/* SVN FILE: $Id: PersonPhoto.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
@Table(name="person_photo")
public class PersonPhoto implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the personId. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** Contains the personPhotoKey. */
        @Id
        @Column(name="person_photo_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_person_photo")
        @SequenceGenerator(name="seq_person_photo", sequenceName="seq_person_photo", allocationSize = 1, initialValue= 1)
        private Long personPhotoKey;

        /** Contains the dateTaken. */
        @Column(name="date_taken", nullable=false)
        private Date dateTaken;

        /** Contains the photo. */
        @Column(name="photo", nullable=false)
        private byte[] photo;

        /**
         * Constructor
         */
        public PersonPhoto() {
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
         * @return the personPhotoKey
         */
        public Long getPersonPhotoKey() {
                return personPhotoKey;
        }

        /**
         * @param personPhotoKey the personPhotoKey to set.
         */
        public void setPersonPhotoKey(Long personPhotoKey) {
                this.personPhotoKey = personPhotoKey;
        }

        /**
         * @return the dateTaken
         */
        public Date getDateTaken() {
                return dateTaken;
        }

        /**
         * @param dateTaken the dateTaken to set.
         */
        public void setDateTaken(Date dateTaken) {
                this.dateTaken = dateTaken;
        }

        /**
         * @return the photo
         */
        public byte[] getPhoto() {
                return photo;
        }

        /**
         * @param photo the photo to set.
         */
        public void setPhoto(byte[] photo) {
                this.photo = photo;
        }

}
