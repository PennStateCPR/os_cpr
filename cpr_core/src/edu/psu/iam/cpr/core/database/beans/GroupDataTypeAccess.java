/* SVN FILE: $Id: GroupDataTypeAccess.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
@Table(name="group_data_type_access")
public class GroupDataTypeAccess implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the readFlag. */
        @Column(name="read_flag", nullable=false, length=1)
        private String readFlag;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the dataTypeKey. */
        @Column(name="data_type_key", nullable=false)
        private Long dataTypeKey;

        /** Contains the archiveFlag. */
        @Column(name="archive_flag", nullable=false, length=1)
        private String archiveFlag;

        /** Contains the iamGroupKey. */
        @Column(name="iam_group_key", nullable=false)
        private Long iamGroupKey;

        /** Contains the groupDataTypeAccessKey. */
        @Id
        @Column(name="group_data_type_access_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_group_data_type_access")
        @SequenceGenerator(name="seq_group_data_type_access", sequenceName="seq_group_data_type_access", allocationSize = 1, initialValue= 1)
        private Long groupDataTypeAccessKey;

        /** Contains the writeFlag. */
        @Column(name="write_flag", nullable=false, length=1)
        private String writeFlag;

        /**
         * Constructor
         */
        public GroupDataTypeAccess() {
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
         * @return the readFlag
         */
        public String getReadFlag() {
                return readFlag;
        }

        /**
         * @param readFlag the readFlag to set.
         */
        public void setReadFlag(String readFlag) {
                this.readFlag = readFlag;
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
         * @return the dataTypeKey
         */
        public Long getDataTypeKey() {
                return dataTypeKey;
        }

        /**
         * @param dataTypeKey the dataTypeKey to set.
         */
        public void setDataTypeKey(Long dataTypeKey) {
                this.dataTypeKey = dataTypeKey;
        }

        /**
         * @return the archiveFlag
         */
        public String getArchiveFlag() {
                return archiveFlag;
        }

        /**
         * @param archiveFlag the archiveFlag to set.
         */
        public void setArchiveFlag(String archiveFlag) {
                this.archiveFlag = archiveFlag;
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
         * @return the groupDataTypeAccessKey
         */
        public Long getGroupDataTypeAccessKey() {
                return groupDataTypeAccessKey;
        }

        /**
         * @param groupDataTypeAccessKey the groupDataTypeAccessKey to set.
         */
        public void setGroupDataTypeAccessKey(Long groupDataTypeAccessKey) {
                this.groupDataTypeAccessKey = groupDataTypeAccessKey;
        }

        /**
         * @return the writeFlag
         */
        public String getWriteFlag() {
                return writeFlag;
        }

        /**
         * @param writeFlag the writeFlag to set.
         */
        public void setWriteFlag(String writeFlag) {
                this.writeFlag = writeFlag;
        }

}
