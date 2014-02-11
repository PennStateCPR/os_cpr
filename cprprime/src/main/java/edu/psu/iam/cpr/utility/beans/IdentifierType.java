/* SVN FILE: $Id: IdentifierType.java 5096 2012-09-13 18:30:09Z jvuccolo $ */
package edu.psu.iam.cpr.utility.beans;

import java.io.Serializable;
import java.util.Date;
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
 * @version $Rev: 5096 $
 * @lastrevision $Date: 2012-09-13 14:30:09 -0400 (Thu, 13 Sep 2012) $
 */

@Entity
@Table(name="identifier_type")
public class IdentifierType implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the canAssignFlag. */
        @Column(name="can_assign_flag", nullable=false, length=1)
        private String canAssignFlag;

        /** Contains the typeDesc. */
        @Column(name="type_desc", nullable=true, length=200)
        private String typeDesc;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the typeName. */
        @Column(name="type_name", nullable=false, length=50)
        private String typeName;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the typeKey. */
        @Id
        @Column(name="type_key", nullable=false)
        private Long typeKey;

        /**
         * Constructor
         */
        public IdentifierType() {
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
         * @return the canAssignFlag
         */
        public String getCanAssignFlag() {
                return canAssignFlag;
        }

        /**
         * @param canAssignFlag the canAssignFlag to set.
         */
        public void setCanAssignFlag(String canAssignFlag) {
                this.canAssignFlag = canAssignFlag;
        }

        /**
         * @return the typeDesc
         */
        public String getTypeDesc() {
                return typeDesc;
        }

        /**
         * @param typeDesc the typeDesc to set.
         */
        public void setTypeDesc(String typeDesc) {
                this.typeDesc = typeDesc;
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
         * @return the typeName
         */
        public String getTypeName() {
                return typeName;
        }

        /**
         * @param typeName the typeName to set.
         */
        public void setTypeName(String typeName) {
                this.typeName = typeName;
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
         * @return the typeKey
         */
        public Long getTypeKey() {
                return typeKey;
        }

        /**
         * @param typeKey the typeKey to set.
         */
        public void setTypeKey(Long typeKey) {
                this.typeKey = typeKey;
        }

}
