/* SVN FILE: $Id: IamGroups.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.beans;

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
@Table(name="iam_groups")
public class IamGroups implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the parentIamGroupKey. */
        @Column(name="parent_iam_group_key", nullable=true)
        private Long parentIamGroupKey;

        /** Contains the iamGroupDesc. */
        @Column(name="iam_group_desc", nullable=false, length=100)
        private String iamGroupDesc;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the activeFlag. */
        @Column(name="active_flag", nullable=false, length=1)
        private String activeFlag;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the iamGroupKey. */
        @Id
        @Column(name="iam_group_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_iam_groups")
        @SequenceGenerator(name="seq_iam_groups", sequenceName="seq_iam_groups", allocationSize = 1, initialValue= 1)
        private Long iamGroupKey;

        /** Contains the iamGroup. */
        @Column(name="iam_group", nullable=false, length=60)
        private String iamGroup;

        /** Contains the suspendFlag. */
        @Column(name="suspend_flag", nullable=false, length=1)
        private String suspendFlag;

        /**
         * Constructor
         */
        public IamGroups() {
            super();
        }

        /**
         * @return the parentIamGroupKey
         */
        public Long getParentIamGroupKey() {
                return parentIamGroupKey;
        }

        /**
         * @param parentIamGroupKey the parentIamGroupKey to set.
         */
        public void setParentIamGroupKey(Long parentIamGroupKey) {
                this.parentIamGroupKey = parentIamGroupKey;
        }

        /**
         * @return the iamGroupDesc
         */
        public String getIamGroupDesc() {
                return iamGroupDesc;
        }

        /**
         * @param iamGroupDesc the iamGroupDesc to set.
         */
        public void setIamGroupDesc(String iamGroupDesc) {
                this.iamGroupDesc = iamGroupDesc;
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
         * @return the activeFlag
         */
        public String getActiveFlag() {
                return activeFlag;
        }

        /**
         * @param activeFlag the activeFlag to set.
         */
        public void setActiveFlag(String activeFlag) {
                this.activeFlag = activeFlag;
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
         * @return the iamGroupKey
         */
        public Long getIamGroupKey() {
                return iamGroupKey;
        }

        /**
         * @param iamGroupKey the iamGroupKey to set.
         */
        public void setIamGroupKey(Long iamGroupKey) {
                this.iamGroupKey = iamGroupKey;
        }

        /**
         * @return the iamGroup
         */
        public String getIamGroup() {
                return iamGroup;
        }

        /**
         * @param iamGroup the iamGroup to set.
         */
        public void setIamGroup(String iamGroup) {
                this.iamGroup = iamGroup;
        }

        /**
         * @return the suspendFlag
         */
        public String getSuspendFlag() {
                return suspendFlag;
        }

        /**
         * @param suspendFlag the suspendFlag to set.
         */
        public void setSuspendFlag(String suspendFlag) {
                this.suspendFlag = suspendFlag;
        }

}
