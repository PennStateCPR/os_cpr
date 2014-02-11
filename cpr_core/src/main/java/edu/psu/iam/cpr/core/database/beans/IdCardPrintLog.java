/* SVN FILE: $Id: IdCardPrintLog.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
@Table(name="id_card_print_log")
public class IdCardPrintLog implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the personIdCardKey. */
        @Column(name="person_id_card_key", nullable=false)
        private Long personIdCardKey;

        /** Contains the workStationName. */
        @Column(name="work_station_name", nullable=false, length=30)
        private String workStationName;

        /** Contains the printedOn. */
        @Column(name="printed_on", nullable=false)
        private Date printedOn;

        /** Contains the workStationIpAddress. */
        @Column(name="work_station_ip_address", nullable=false, length=50)
        private String workStationIpAddress;

        /** Contains the printedBy. */
        @Column(name="printed_by", nullable=false, length=30)
        private String printedBy;

        /** Contains the idCardPrintLogKey. */
        @Id
        @Column(name="id_card_print_log_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_id_card_print_log")
        @SequenceGenerator(name="seq_id_card_print_log", sequenceName="seq_id_card_print_log", allocationSize = 1, initialValue= 1)
        private Long idCardPrintLogKey;

        /**
         * Constructor
         */
        public IdCardPrintLog() {
            super();
        }

        /**
         * @return the personIdCardKey
         */
        public Long getPersonIdCardKey() {
                return personIdCardKey;
        }

        /**
         * @param personIdCardKey the personIdCardKey to set.
         */
        public void setPersonIdCardKey(Long personIdCardKey) {
                this.personIdCardKey = personIdCardKey;
        }

        /**
         * @return the workStationName
         */
        public String getWorkStationName() {
                return workStationName;
        }

        /**
         * @param workStationName the workStationName to set.
         */
        public void setWorkStationName(String workStationName) {
                this.workStationName = workStationName;
        }

        /**
         * @return the printedOn
         */
        public Date getPrintedOn() {
                return printedOn;
        }

        /**
         * @param printedOn the printedOn to set.
         */
        public void setPrintedOn(Date printedOn) {
                this.printedOn = printedOn;
        }

        /**
         * @return the workStationIpAddress
         */
        public String getWorkStationIpAddress() {
                return workStationIpAddress;
        }

        /**
         * @param workStationIpAddress the workStationIpAddress to set.
         */
        public void setWorkStationIpAddress(String workStationIpAddress) {
                this.workStationIpAddress = workStationIpAddress;
        }

        /**
         * @return the printedBy
         */
        public String getPrintedBy() {
                return printedBy;
        }

        /**
         * @param printedBy the printedBy to set.
         */
        public void setPrintedBy(String printedBy) {
                this.printedBy = printedBy;
        }

        /**
         * @return the idCardPrintLogKey
         */
        public Long getIdCardPrintLogKey() {
                return idCardPrintLogKey;
        }

        /**
         * @param idCardPrintLogKey the idCardPrintLogKey to set.
         */
        public void setIdCardPrintLogKey(Long idCardPrintLogKey) {
                this.idCardPrintLogKey = idCardPrintLogKey;
        }

}
