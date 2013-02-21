/* SVN FILE: $Id: BuildBean.java 5970 2013-01-04 15:50:31Z jvuccolo $ */
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
 * @version $Rev: 5970 $
 * @lastrevision $Date: 2013-01-04 10:50:31 -0500 (Fri, 04 Jan 2013) $
 */

@Entity
@Table(name="group_data_type_access")
public class GroupDataTypeAccess implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the groupDataTypeAccessKey. */
        @Id
        @Column(name="group_data_type_access_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_group_data_type_access")
        @SequenceGenerator(name="seq_group_data_type_access", sequenceName="seq_group_data_type_access", allocationSize = 1, initialValue= 1)
        private Long groupDataTypeAccessKey;

        /** Contains the cprAccessGroupsKey. */
        @Column(name="cpr_access_groups_key", nullable=false)
        private Long cprAccessGroupsKey;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the archiveFlag. */
        @Column(name="archive_flag", nullable=false, length=1)
        private String archiveFlag;

        /** Contains the dataTypeKey. */
        @Column(name="data_type_key", nullable=false)
        private Long dataTypeKey;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the writeFlag. */
        @Column(name="write_flag", nullable=false, length=1)
        private String writeFlag;

        /** Contains the readFlag. */
        @Column(name="read_flag", nullable=false, length=1)
        private String readFlag;

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
         * @return the cprAccessGroupsKey
         */
        public Long getCprAccessGroupsKey() {
                return cprAccessGroupsKey;
        }

        /**
         * @param cprAccessGroupsKey the cprAccessGroupsKey to set.
         */
        public void setCprAccessGroupsKey(Long cprAccessGroupsKey) {
                this.cprAccessGroupsKey = cprAccessGroupsKey;
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

}
