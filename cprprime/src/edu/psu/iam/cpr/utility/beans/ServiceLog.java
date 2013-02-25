/* SVN FILE: $Id: ServiceLog.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
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
@Table(name="service_log")
public class ServiceLog implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the requestEnd. */
        @Column(name="request_end", nullable=true)
        private Date requestEnd;

        /** Contains the requestStart. */
        @Column(name="request_start", nullable=false)
        private Date requestStart;

        /** Contains the requestUserid. */
        @Column(name="request_userid", nullable=true, length=30)
        private String requestUserid;

        /** Contains the webServiceKey. */
        @Column(name="web_service_key", nullable=false)
        private Long webServiceKey;

        /** Contains the serviceLogKey. */
        @Id
        @Column(name="service_log_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_service_log")
        @SequenceGenerator(name="seq_service_log", sequenceName="seq_service_log", allocationSize = 1, initialValue= 1)
        private Long serviceLogKey;

        /** Contains the requestString. */
        @Column(name="request_string", nullable=true, length=2000)
        private String requestString;

        /** Contains the resultString. */
        @Column(name="result_string", nullable=true, length=1000)
        private String resultString;

        /** Contains the requestDuration. */
        @Column(name="request_duration", nullable=true)
        private Long requestDuration;

        /** Contains the ipAddress. */
        @Column(name="ip_address", nullable=true, length=50)
        private String ipAddress;

        /**
         * Constructor
         */
        public ServiceLog() {
            super();
        }

        /**
         * @return the requestEnd
         */
        public Date getRequestEnd() {
                return requestEnd;
        }

        /**
         * @param requestEnd the requestEnd to set.
         */
        public void setRequestEnd(Date requestEnd) {
                this.requestEnd = requestEnd;
        }

        /**
         * @return the requestStart
         */
        public Date getRequestStart() {
                return requestStart;
        }

        /**
         * @param requestStart the requestStart to set.
         */
        public void setRequestStart(Date requestStart) {
                this.requestStart = requestStart;
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
         * @return the webServiceKey
         */
        public Long getWebServiceKey() {
                return webServiceKey;
        }

        /**
         * @param webServiceKey the webServiceKey to set.
         */
        public void setWebServiceKey(Long webServiceKey) {
                this.webServiceKey = webServiceKey;
        }

        /**
         * @return the serviceLogKey
         */
        public Long getServiceLogKey() {
                return serviceLogKey;
        }

        /**
         * @param serviceLogKey the serviceLogKey to set.
         */
        public void setServiceLogKey(Long serviceLogKey) {
                this.serviceLogKey = serviceLogKey;
        }

        /**
         * @return the requestString
         */
        public String getRequestString() {
                return requestString;
        }

        /**
         * @param requestString the requestString to set.
         */
        public void setRequestString(String requestString) {
                this.requestString = requestString;
        }

        /**
         * @return the resultString
         */
        public String getResultString() {
                return resultString;
        }

        /**
         * @param resultString the resultString to set.
         */
        public void setResultString(String resultString) {
                this.resultString = resultString;
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
         * @return the ipAddress
         */
        public String getIpAddress() {
                return ipAddress;
        }

        /**
         * @param ipAddress the ipAddress to set.
         */
        public void setIpAddress(String ipAddress) {
                this.ipAddress = ipAddress;
        }

}
