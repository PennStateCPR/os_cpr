/* SVN FILE: $Id: DatabaseLog.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
@Table(name="database_log")
public class DatabaseLog implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the requestUserid. */
        @Column(name="request_userid", nullable=true, length=30)
        private String requestUserid;

        /** Contains the entryType. */
        @Column(name="entry_type", nullable=false, length=5)
        private String entryType;

        /** Contains the entryTimestamp. */
        @Column(name="entry_timestamp", nullable=false)
        private Date entryTimestamp;

        /** Contains the databaseLogKey. */
        @Id
        @Column(name="database_log_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_database_log")
        @SequenceGenerator(name="seq_database_log", sequenceName="seq_database_log", allocationSize = 1, initialValue= 1)
        private Long databaseLogKey;

        /** Contains the instanceNumber. */
        @Column(name="instance_number", nullable=true)
        private Long instanceNumber;

        /** Contains the requestDuration. */
        @Column(name="request_duration", nullable=true)
        private Long requestDuration;

        /** Contains the packageName. */
        @Column(name="package_name", nullable=false, length=30)
        private String packageName;

        /** Contains the programName. */
        @Column(name="program_name", nullable=false, length=30)
        private String programName;

        /** Contains the outputString. */
        @Column(name="output_string", nullable=true, length=1000)
        private String outputString;

        /**
         * Constructor
         */
        public DatabaseLog() {
            super();
        }

        /**
         * @return the requestUserid
         */
        public String getRequestUserid() {
                return requestUserid;
        }

        /**
         * @param requestUserid the requestUserid to set.
         */
        public void setRequestUserid(String requestUserid) {
                this.requestUserid = requestUserid;
        }

        /**
         * @return the entryType
         */
        public String getEntryType() {
                return entryType;
        }

        /**
         * @param entryType the entryType to set.
         */
        public void setEntryType(String entryType) {
                this.entryType = entryType;
        }

        /**
         * @return the entryTimestamp
         */
        public Date getEntryTimestamp() {
                return entryTimestamp;
        }

        /**
         * @param entryTimestamp the entryTimestamp to set.
         */
        public void setEntryTimestamp(Date entryTimestamp) {
                this.entryTimestamp = entryTimestamp;
        }

        /**
         * @return the databaseLogKey
         */
        public Long getDatabaseLogKey() {
                return databaseLogKey;
        }

        /**
         * @param databaseLogKey the databaseLogKey to set.
         */
        public void setDatabaseLogKey(Long databaseLogKey) {
                this.databaseLogKey = databaseLogKey;
        }

        /**
         * @return the instanceNumber
         */
        public Long getInstanceNumber() {
                return instanceNumber;
        }

        /**
         * @param instanceNumber the instanceNumber to set.
         */
        public void setInstanceNumber(Long instanceNumber) {
                this.instanceNumber = instanceNumber;
        }

        /**
         * @return the requestDuration
         */
        public Long getRequestDuration() {
                return requestDuration;
        }

        /**
         * @param requestDuration the requestDuration to set.
         */
        public void setRequestDuration(Long requestDuration) {
                this.requestDuration = requestDuration;
        }

        /**
         * @return the packageName
         */
        public String getPackageName() {
                return packageName;
        }

        /**
         * @param packageName the packageName to set.
         */
        public void setPackageName(String packageName) {
                this.packageName = packageName;
        }

        /**
         * @return the programName
         */
        public String getProgramName() {
                return programName;
        }

        /**
         * @param programName the programName to set.
         */
        public void setProgramName(String programName) {
                this.programName = programName;
        }

        /**
         * @return the outputString
         */
        public String getOutputString() {
                return outputString;
        }

        /**
         * @param outputString the outputString to set.
         */
        public void setOutputString(String outputString) {
                this.outputString = outputString;
        }

}
