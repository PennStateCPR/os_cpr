/* SVN FILE: $Id: BuildBean.java 5970 2013-01-04 15:50:31Z jvuccolo $ */
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
 * @version $Rev: 5970 $
 * @lastrevision $Date: 2013-01-04 10:50:31 -0500 (Fri, 04 Jan 2013) $
 */

@Entity
@Table(name="ra_screens")
public class RaScreens implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the raScreenOrder. */
        @Column(name="ra_screen_order", nullable=false)
        private Long raScreenOrder;

        /** Contains the raApplicationKey. */
        @Column(name="ra_application_key", nullable=false)
        private Long raApplicationKey;

        /** Contains the raScreenKey. */
        @Id
        @Column(name="ra_screen_key", nullable=false)
        private Long raScreenKey;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the uiScreenName. */
        @Column(name="ui_screen_name", nullable=false, length=30)
        private String uiScreenName;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /**
         * Constructor
         */
        public RaScreens() {
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
         * @return the raScreenOrder
         */
        public Long getRaScreenOrder() {
                return raScreenOrder;
        }

        /**
         * @param raScreenOrder the raScreenOrder to set.
         */
        public void setRaScreenOrder(Long raScreenOrder) {
                this.raScreenOrder = raScreenOrder;
        }

        /**
         * @return the raApplicationKey
         */
        public Long getRaApplicationKey() {
                return raApplicationKey;
        }

        /**
         * @param raApplicationKey the raApplicationKey to set.
         */
        public void setRaApplicationKey(Long raApplicationKey) {
                this.raApplicationKey = raApplicationKey;
        }

        /**
         * @return the raScreenKey
         */
        public Long getRaScreenKey() {
                return raScreenKey;
        }

        /**
         * @param raScreenKey the raScreenKey to set.
         */
        public void setRaScreenKey(Long raScreenKey) {
                this.raScreenKey = raScreenKey;
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
         * @return the uiScreenName
         */
        public String getUiScreenName() {
                return uiScreenName;
        }

        /**
         * @param uiScreenName the uiScreenName to set.
         */
        public void setUiScreenName(String uiScreenName) {
                this.uiScreenName = uiScreenName;
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

}
