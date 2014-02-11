/* SVN FILE: $Id: JavaExceptions.java 5096 2012-09-13 18:30:09Z jvuccolo $ */
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
@Table(name="java_exceptions")
public class JavaExceptions implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the javaExceptionKey. */
        @Id
        @Column(name="java_exception_key", nullable=false)
        private Long javaExceptionKey;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the javaExceptionEnum. */
        @Column(name="java_exception_enum", nullable=false, length=50)
        private String javaExceptionEnum;

        /** Contains the javaExceptionText. */
        @Column(name="java_exception_text", nullable=false, length=100)
        private String javaExceptionText;

        /**
         * Constructor
         */
        public JavaExceptions() {
            super();
        }

        /**
         * @return the javaExceptionKey
         */
        public Long getJavaExceptionKey() {
                return javaExceptionKey;
        }

        /**
         * @param javaExceptionKey the javaExceptionKey to set.
         */
        public void setJavaExceptionKey(Long javaExceptionKey) {
                this.javaExceptionKey = javaExceptionKey;
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
         * @return the javaExceptionEnum
         */
        public String getJavaExceptionEnum() {
                return javaExceptionEnum;
        }

        /**
         * @param javaExceptionEnum the javaExceptionEnum to set.
         */
        public void setJavaExceptionEnum(String javaExceptionEnum) {
                this.javaExceptionEnum = javaExceptionEnum;
        }

        /**
         * @return the javaExceptionText
         */
        public String getJavaExceptionText() {
                return javaExceptionText;
        }

        /**
         * @param javaExceptionText the javaExceptionText to set.
         */
        public void setJavaExceptionText(String javaExceptionText) {
                this.javaExceptionText = javaExceptionText;
        }

}
