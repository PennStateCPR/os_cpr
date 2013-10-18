/* SVN FILE: $Id: TransHershey.java 7823 2013-08-09 21:24:37Z nsr11 $ */
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
 * @author $Author: nsr11 $
 * @version $Rev: 7823 $
 * @lastrevision $Date: 2013-08-09 17:24:37 -0400 (Fri, 09 Aug 2013) $
 */

@Entity
@Table(name="trans_hershey")
public class TransHershey implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** null */
        @Column(name="hr_hire_date", nullable=true, length=8)
        private String hrHireDate;

        /** null */
        @Column(name="home_zipcode", nullable=true, length=100)
        private String homeZipcode;

        /** null */
        @Column(name="preferred_name", nullable=true, length=240)
        private String preferredName;

        /** null */
        @Column(name="home_country", nullable=true, length=3)
        private String homeCountry;

        /** null */
        @Column(name="dept_name", nullable=true, length=100)
        private String deptName;

        /** null */
        @Column(name="mail_code", nullable=true, length=100)
        private String mailCode;

        /** null */
        @Column(name="work_state", nullable=true, length=100)
        private String workState;

        /** null */
        @Column(name="work_zipcode", nullable=true, length=100)
        private String workZipcode;

        /** null */
        @Column(name="appt_type_code", nullable=true, length=100)
        private String apptTypeCode;

        /** null */
        @Column(name="work_address2", nullable=true, length=100)
        private String workAddress2;

        /** null */
        @Column(name="first_name", nullable=true, length=100)
        private String firstName;

        /** null */
        @Column(name="psu_id", nullable=true, length=16)
        private String psuId;

        /** null */
        @Column(name="work_address1", nullable=true, length=100)
        private String workAddress1;

        /** null */
        @Column(name="work_city", nullable=true, length=100)
        private String workCity;

        /** null */
        @Column(name="home_state", nullable=true, length=2)
        private String homeState;

        /** null */
        @Column(name="middle_names", nullable=true, length=100)
        private String middleNames;

        /** null */
        @Column(name="work_phone_ext", nullable=true, length=10)
        private String workPhoneExt;

        /** null */
        @Column(name="work_phone", nullable=true, length=100)
        private String workPhone;

        /** null */
        @Column(name="hr_term_date", nullable=true, length=8)
        private String hrTermDate;

        /** null */
        @Column(name="campus_name", nullable=true, length=100)
        private String campusName;

        /** null */
        @Column(name="suffix", nullable=true, length=100)
        private String suffix;

        /** null */
        @Column(name="home_address2", nullable=true, length=100)
        private String homeAddress2;

        /** null */
        @Column(name="last_name", nullable=true, length=100)
        private String lastName;

        /** null */
        @Column(name="home_address1", nullable=true, length=100)
        private String homeAddress1;

        /** null */
        @Id
        @Column(name="trans_hershey_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_trans_hershey")
        @SequenceGenerator(name="seq_trans_hershey", sequenceName="seq_trans_hershey", allocationSize = 1, initialValue= 1)
        private Long transHersheyKey;

        /** null */
        @Column(name="gender", nullable=true, length=1)
        private String gender;

        /** null */
        @Column(name="date_of_birth", nullable=true, length=100)
        private String dateOfBirth;

        /** null */
        @Column(name="home_city", nullable=true, length=100)
        private String homeCity;

        /** null */
        @Column(name="title", nullable=true, length=100)
        private String title;

        /**
         * Constructor
         */
        public TransHershey() {
            super();
        }

        /**
         * @return the hrHireDate
         */
        public String getHrHireDate() {
                return hrHireDate;
        }

        /**
         * @param hrHireDate the hrHireDate to set.
         */
        public void setHrHireDate(String hrHireDate) {
                this.hrHireDate = hrHireDate;
        }

        /**
         * @return the homeZipcode
         */
        public String getHomeZipcode() {
                return homeZipcode;
        }

        /**
         * @param homeZipcode the homeZipcode to set.
         */
        public void setHomeZipcode(String homeZipcode) {
                this.homeZipcode = homeZipcode;
        }

        /**
         * @return the preferredName
         */
        public String getPreferredName() {
                return preferredName;
        }

        /**
         * @param preferredName the preferredName to set.
         */
        public void setPreferredName(String preferredName) {
                this.preferredName = preferredName;
        }

        /**
         * @return the homeCountry
         */
        public String getHomeCountry() {
                return homeCountry;
        }

        /**
         * @param homeCountry the homeCountry to set.
         */
        public void setHomeCountry(String homeCountry) {
                this.homeCountry = homeCountry;
        }

        /**
         * @return the deptName
         */
        public String getDeptName() {
                return deptName;
        }

        /**
         * @param deptName the deptName to set.
         */
        public void setDeptName(String deptName) {
                this.deptName = deptName;
        }

        /**
         * @return the mailCode
         */
        public String getMailCode() {
                return mailCode;
        }

        /**
         * @param mailCode the mailCode to set.
         */
        public void setMailCode(String mailCode) {
                this.mailCode = mailCode;
        }

        /**
         * @return the workState
         */
        public String getWorkState() {
                return workState;
        }

        /**
         * @param workState the workState to set.
         */
        public void setWorkState(String workState) {
                this.workState = workState;
        }

        /**
         * @return the workZipcode
         */
        public String getWorkZipcode() {
                return workZipcode;
        }

        /**
         * @param workZipcode the workZipcode to set.
         */
        public void setWorkZipcode(String workZipcode) {
                this.workZipcode = workZipcode;
        }

        /**
         * @return the apptTypeCode
         */
        public String getApptTypeCode() {
                return apptTypeCode;
        }

        /**
         * @param apptTypeCode the apptTypeCode to set.
         */
        public void setApptTypeCode(String apptTypeCode) {
                this.apptTypeCode = apptTypeCode;
        }

        /**
         * @return the workAddress2
         */
        public String getWorkAddress2() {
                return workAddress2;
        }

        /**
         * @param workAddress2 the workAddress2 to set.
         */
        public void setWorkAddress2(String workAddress2) {
                this.workAddress2 = workAddress2;
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
         * @return the workAddress1
         */
        public String getWorkAddress1() {
                return workAddress1;
        }

        /**
         * @param workAddress1 the workAddress1 to set.
         */
        public void setWorkAddress1(String workAddress1) {
                this.workAddress1 = workAddress1;
        }

        /**
         * @return the workCity
         */
        public String getWorkCity() {
                return workCity;
        }

        /**
         * @param workCity the workCity to set.
         */
        public void setWorkCity(String workCity) {
                this.workCity = workCity;
        }

        /**
         * @return the homeState
         */
        public String getHomeState() {
                return homeState;
        }

        /**
         * @param homeState the homeState to set.
         */
        public void setHomeState(String homeState) {
                this.homeState = homeState;
        }

        /**
         * @return the middleNames
         */
        public String getMiddleNames() {
                return middleNames;
        }

        /**
         * @param middleNames the middleNames to set.
         */
        public void setMiddleNames(String middleNames) {
                this.middleNames = middleNames;
        }

        /**
         * @return the workPhoneExt
         */
        public String getWorkPhoneExt() {
                return workPhoneExt;
        }

        /**
         * @param workPhoneExt the workPhoneExt to set.
         */
        public void setWorkPhoneExt(String workPhoneExt) {
                this.workPhoneExt = workPhoneExt;
        }

        /**
         * @return the workPhone
         */
        public String getWorkPhone() {
                return workPhone;
        }

        /**
         * @param workPhone the workPhone to set.
         */
        public void setWorkPhone(String workPhone) {
                this.workPhone = workPhone;
        }

        /**
         * @return the hrTermDate
         */
        public String getHrTermDate() {
                return hrTermDate;
        }

        /**
         * @param hrTermDate the hrTermDate to set.
         */
        public void setHrTermDate(String hrTermDate) {
                this.hrTermDate = hrTermDate;
        }

        /**
         * @return the campusName
         */
        public String getCampusName() {
                return campusName;
        }

        /**
         * @param campusName the campusName to set.
         */
        public void setCampusName(String campusName) {
                this.campusName = campusName;
        }

        /**
         * @return the suffix
         */
        public String getSuffix() {
                return suffix;
        }

        /**
         * @param suffix the suffix to set.
         */
        public void setSuffix(String suffix) {
                this.suffix = suffix;
        }

        /**
         * @return the homeAddress2
         */
        public String getHomeAddress2() {
                return homeAddress2;
        }

        /**
         * @param homeAddress2 the homeAddress2 to set.
         */
        public void setHomeAddress2(String homeAddress2) {
                this.homeAddress2 = homeAddress2;
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
         * @return the homeAddress1
         */
        public String getHomeAddress1() {
                return homeAddress1;
        }

        /**
         * @param homeAddress1 the homeAddress1 to set.
         */
        public void setHomeAddress1(String homeAddress1) {
                this.homeAddress1 = homeAddress1;
        }

        /**
         * @return the transHersheyKey
         */
        public Long getTransHersheyKey() {
                return transHersheyKey;
        }

        /**
         * @param transHersheyKey the transHersheyKey to set.
         */
        public void setTransHersheyKey(Long transHersheyKey) {
                this.transHersheyKey = transHersheyKey;
        }

        /**
         * @return the gender
         */
        public String getGender() {
                return gender;
        }

        /**
         * @param gender the gender to set.
         */
        public void setGender(String gender) {
                this.gender = gender;
        }

        /**
         * @return the dateOfBirth
         */
        public String getDateOfBirth() {
                return dateOfBirth;
        }

        /**
         * @param dateOfBirth the dateOfBirth to set.
         */
        public void setDateOfBirth(String dateOfBirth) {
                this.dateOfBirth = dateOfBirth;
        }

        /**
         * @return the homeCity
         */
        public String getHomeCity() {
                return homeCity;
        }

        /**
         * @param homeCity the homeCity to set.
         */
        public void setHomeCity(String homeCity) {
                this.homeCity = homeCity;
        }

        /**
         * @return the title
         */
        public String getTitle() {
                return title;
        }

        /**
         * @param title the title to set.
         */
        public void setTitle(String title) {
                this.title = title;
        }

}
