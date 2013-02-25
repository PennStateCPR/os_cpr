/* SVN FILE: $Id$ */
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
@Table(name="ui_log")
public class UiLog implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the date/time stamp that a request was made in the user interface application. */
        @Column(name="request_timestamp", nullable=false)
        private Date requestTimestamp;

        /** Contains the userid of the person that made the request to the user interface application. */
        @Column(name="request_userid", nullable=true, length=30)
        private String requestUserid;

        /** Contains a unique number that identifies a user interface log record.  It is populated by the seq_ui_log sequence. */
        @Id
        @Column(name="ui_log_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_ui_log")
        @SequenceGenerator(name="seq_ui_log", sequenceName="seq_ui_log", allocationSize = 1, initialValue= 1)
        private Long uiLogKey;

        /** Contains the request string sent by the user interface application. */
        @Column(name="request_string", nullable=true, length=2000)
        private String requestString;

        /** Contains the browser information of the person that made the request to the user interface application. */
        @Column(name="browser_type", nullable=true, length=200)
        private String browserType;

        /** Contains the result string of a request from the user interface application. */
        @Column(name="result_string", nullable=true, length=1000)
        private String resultString;

        /** Contains a flag to indicate if an error occured in the request to the user interface application.  The valid values are Y or N.  The default value is Y. */
        @Column(name="error_flag", nullable=false, length=1)
        private String errorFlag;

        /** Contains the duration of a successful request made in a user interface application.  If the request fails and/or an error occurs, this value should be null. */
        @Column(name="request_duration", nullable=true)
        private Long requestDuration;

        /** Contains a unique number that identifies a user interface application. */
        @Column(name="ui_application_key", nullable=false)
        private Long uiApplicationKey;

        /** Contains the IP address that made that request to the user interface application. */
        @Column(name="ip_address", nullable=true, length=50)
        private String ipAddress;

        /**
         * Constructor
         */
        public UiLog() {
            super();
        }

        /**
         * @return the requestTimestamp
         */
        public Date getRequestTimestamp() {
                return requestTimestamp;
        }

        /**
         * @param requestTimestamp the requestTimestamp to set.
         */
        public void setRequestTimestamp(Date requestTimestamp) {
                this.requestTimestamp = requestTimestamp;
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
         * @return the uiLogKey
         */
        public Long getUiLogKey() {
                return uiLogKey;
        }

        /**
         * @param uiLogKey the uiLogKey to set.
         */
        public void setUiLogKey(Long uiLogKey) {
                this.uiLogKey = uiLogKey;
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
         * @return the browserType
         */
        public String getBrowserType() {
                return browserType;
        }

        /**
         * @param browserType the browserType to set.
         */
        public void setBrowserType(String browserType) {
                this.browserType = browserType;
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
         * @return the errorFlag
         */
        public String getErrorFlag() {
                return errorFlag;
        }

        /**
         * @param errorFlag the errorFlag to set.
         */
        public void setErrorFlag(String errorFlag) {
                this.errorFlag = errorFlag;
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
         * @return the uiApplicationKey
         */
        public Long getUiApplicationKey() {
                return uiApplicationKey;
        }

        /**
         * @param uiApplicationKey the uiApplicationKey to set.
         */
        public void setUiApplicationKey(Long uiApplicationKey) {
                this.uiApplicationKey = uiApplicationKey;
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
