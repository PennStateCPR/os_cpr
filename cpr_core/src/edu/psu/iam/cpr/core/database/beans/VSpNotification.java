/* SVN FILE: $Id: VSpNotification.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name="v_sp_notification")
public class VSpNotification implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the serviceProvisionerQueue. */
        @Column(name="service_provisioner_queue", nullable=true, length=128)
        private String serviceProvisionerQueue;

        /** Contains the serviceProvisionerKey. */
        @Id
        @Column(name="service_provisioner_key", nullable=false)
        private Long serviceProvisionerKey;

        /** Contains the webServiceKey. */
        @Column(name="web_service_key", nullable=false)
        private Long webServiceKey;

        /** Contains the webService. */
        @Column(name="web_service", nullable=false, length=30)
        private String webService;

        /** Contains the serviceProvisioner. */
        @Column(name="service_provisioner", nullable=false, length=50)
        private String serviceProvisioner;

        /**
         * Constructor
         */
        public VSpNotification() {
            super();
        }

        /**
         * @return the serviceProvisionerQueue
         */
        public String getServiceProvisionerQueue() {
                return serviceProvisionerQueue;
        }

        /**
         * @param serviceProvisionerQueue the serviceProvisionerQueue to set.
         */
        public void setServiceProvisionerQueue(String serviceProvisionerQueue) {
                this.serviceProvisionerQueue = serviceProvisionerQueue;
        }

        /**
         * @return the serviceProvisionerKey
         */
        public Long getServiceProvisionerKey() {
                return serviceProvisionerKey;
        }

        /**
         * @param serviceProvisionerKey the serviceProvisionerKey to set.
         */
        public void setServiceProvisionerKey(Long serviceProvisionerKey) {
                this.serviceProvisionerKey = serviceProvisionerKey;
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
         * @return the webService
         */
        public String getWebService() {
                return webService;
        }

        /**
         * @param webService the webService to set.
         */
        public void setWebService(String webService) {
                this.webService = webService;
        }

        /**
         * @return the serviceProvisioner
         */
        public String getServiceProvisioner() {
                return serviceProvisioner;
        }

        /**
         * @param serviceProvisioner the serviceProvisioner to set.
         */
        public void setServiceProvisioner(String serviceProvisioner) {
                this.serviceProvisioner = serviceProvisioner;
        }

}
