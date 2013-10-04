/* SVN FILE: $Id: TransCidr.java 7747 2013-07-23 17:48:37Z smc1 $ */
package edu.psu.iam.cpr.core.database.beans;

import java.io.Serializable;
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
 * @author $Author: smc1 $
 * @version $Rev: 7747 $
 * @lastrevision $Date: 2013-07-23 13:48:37 -0400 (Tue, 23 Jul 2013) $
 */

@Entity
@Table(name="trans_cidr")
public class TransCidr implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** null */
        @Column(name="last_update", nullable=true, length=8)
        private String lastUpdate;

        /** null */
        @Column(name="state", nullable=true, length=2)
        private String state;

        /** null */
        @Column(name="dob", nullable=true, length=8)
        private String dob;
        
        /** null */
        @Column(name="gender", nullable=true, length=1)
        private String gender;

        /** null */
        @Column(name="country", nullable=true, length=3)
        private String country;

        /** null */
        @Id
        @Column(name="trans_cidr_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_trans_cidr")
        @SequenceGenerator(name="seq_trans_cidr", sequenceName="seq_trans_cidr", allocationSize = 1, initialValue= 1)
        private Long transCidrKey;

        /** null */
        @Column(name="status", nullable=false, length=4)
        private String status;

        /** null */
        @Column(name="last_name", nullable=true, length=30)
        private String lastName;

        /** null */
        @Column(name="street2", nullable=true, length=40)
        private String street2;

        /** null */
        @Column(name="street1", nullable=true, length=40)
        private String street1;

        /** null */
        @Column(name="zip", nullable=true, length=9)
        private String zip;

        /** null */
        @Column(name="psu_id", nullable=false, length=9)
        private String psuId;

        /** null */
        @Column(name="affiliate", nullable=true, length=8)
        private String affiliate;

        /** null */
        @Column(name="first_name", nullable=true, length=25)
        private String firstName;

        /** null */
        @Column(name="middle_name", nullable=true, length=25)
        private String middleName;

        /** null */
        @Column(name="city", nullable=true, length=35)
        private String city;

        /**
         * Constructor
         */
        public TransCidr() {
            super();
        }

        /**
         * @return the lastUpdate
         */
        public String getLastUpdate() {
                return lastUpdate;
        }

        /**
         * @param lastUpdate the lastUpdate to set.
         */
        public void setLastUpdate(String lastUpdate) {
                this.lastUpdate = lastUpdate;
        }

        /**
         * @return the state
         */
        public String getState() {
                return state;
        }

        /**
         * @param state the state to set.
         */
        public void setState(String state) {
                this.state = state;
        }

        /**
         * @return the dob
         */
        public String getDob() {
                return dob;
        }

        /**
         * @param dob the dob to set.
         */
        public void setDob(String dob) {
                this.dob = dob;
        }

        /**
         * @return the gender
         */
        public String getGender() {
                return gender;
        }

        /**
         * @param dob the gender to set.
         */
        public void setGender(String gender) {
                this.gender = gender;
        }

        
        /**
         * @return the country
         */
        public String getCountry() {
                return country;
        }

        /**
         * @param country the country to set.
         */
        public void setCountry(String country) {
                this.country = country;
        }

        /**
         * @return the transCidrKey
         */
        public Long getTransCidrKey() {
                return transCidrKey;
        }

        /**
         * @param transCidrKey the transCidrKey to set.
         */
        public void setTransCidrKey(Long transCidrKey) {
                this.transCidrKey = transCidrKey;
        }

        /**
         * @return the status
         */
        public String getStatus() {
                return status;
        }

        /**
         * @param status the status to set.
         */
        public void setStatus(String status) {
                this.status = status;
        }

        /**
         * @return the lastName
         */
        public String getLastName() {
                return lastName;
        }

        /**
         * @param lastName the lastName to set.
         */
        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        /**
         * @return the street2
         */
        public String getStreet2() {
                return street2;
        }

        /**
         * @param street2 the street2 to set.
         */
        public void setStreet2(String street2) {
                this.street2 = street2;
        }

        /**
         * @return the street1
         */
        public String getStreet1() {
                return street1;
        }

        /**
         * @param street1 the street1 to set.
         */
        public void setStreet1(String street1) {
                this.street1 = street1;
        }

        /**
         * @return the zip
         */
        public String getZip() {
                return zip;
        }

        /**
         * @param zip the zip to set.
         */
        public void setZip(String zip) {
                this.zip = zip;
        }

        /**
         * @return the psuId
         */
        public String getPsuId() {
                return psuId;
        }

        /**
         * @param psuId the psuId to set.
         */
        public void setPsuId(String psuId) {
                this.psuId = psuId;
        }

        /**
         * @return the affiliate
         */
        public String getAffiliate() {
                return affiliate;
        }

        /**
         * @param affiliate the affiliate to set.
         */
        public void setAffiliate(String affiliate) {
                this.affiliate = affiliate;
        }

        /**
         * @return the firstName
         */
        public String getFirstName() {
                return firstName;
        }

        /**
         * @param firstName the firstName to set.
         */
        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        /**
         * @return the middleName
         */
        public String getMiddleName() {
                return middleName;
        }

        /**
         * @param middleName the middleName to set.
         */
        public void setMiddleName(String middleName) {
                this.middleName = middleName;
        }

        /**
         * @return the city
         */
        public String getCity() {
                return city;
        }

        /**
         * @param city the city to set.
         */
        public void setCity(String city) {
                this.city = city;
        }

}
