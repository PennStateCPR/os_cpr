/* SVN FILE: $Id: TransEmpl.java 7685 2013-06-27 17:36:39Z llg5 $ */
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
 * @author $Author: llg5 $
 * @version $Rev: 7685 $
 * @lastrevision $Date: 2013-06-27 13:36:39 -0400 (Thu, 27 Jun 2013) $
 */

@Entity
@Table(name="trans_empl")
public class TransEmpl implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** null */
        @Column(name="code_appt_type", nullable=true, length=100)
        private String codeApptType;

        /** null */
        @Column(name="name_empl_department", nullable=true, length=100)
        private String nameEmplDepartment;

        /** null */
        @Column(name="psu_id", nullable=true, length=100)
        private String psuId;

        /** null */
        @Column(name="addr_empl_zip_home_second", nullable=true, length=100)
        private String addrEmplZipHomeSecond;

        /** null */
        @Column(name="code_empl_direct_phone", nullable=true, length=100)
        private String codeEmplDirectPhone;

        /** null */
        @Column(name="addr_campus_addr_line3", nullable=true, length=100)
        private String addrCampusAddrLine3;

        /** null */
        @Column(name="addr_empl_state_home", nullable=true, length=100)
        private String addrEmplStateHome;

        /** null */
        @Column(name="addr_campus_addr_line2", nullable=true, length=100)
        private String addrCampusAddrLine2;

        /** null */
        @Column(name="addr_campus_addr_line1", nullable=true, length=100)
        private String addrCampusAddrLine1;

        /** null */
        @Column(name="code_budg_location_home", nullable=true, length=100)
        private String codeBudgLocationHome;

        /** null */
        @Column(name="addr_empl_st2_home", nullable=true, length=100)
        private String addrEmplSt2Home;

        /** null */
        @Column(name="name_empl_admin_area", nullable=true, length=100)
        private String nameEmplAdminArea;

        /** null */
        @Column(name="name_empl_first_legal", nullable=true, length=100)
        private String nameEmplFirstLegal;

        /** null */
        @Column(name="code_empl_layoff", nullable=true, length=100)
        private String codeEmplLayoff;

        /** null */
        @Column(name="date_empl_hired", nullable=true, length=100)
        private String dateEmplHired;

        /** null */
        @Column(name="addr_empl_zip_home_prime", nullable=true, length=100)
        private String addrEmplZipHomePrime;

        /** null */
        @Column(name="name_empl_pm_preferred_name", nullable=true, length=100)
        private String nameEmplPmPreferredName;

        /** null */
        @Column(name="code_budg_numb_home", nullable=true, length=100)
        private String codeBudgNumbHome;

        /** null */
        @Column(name="name_empl_sfx_legal", nullable=true, length=100)
        private String nameEmplSfxLegal;

        /** null */
        @Column(name="code_empl_title_class", nullable=true, length=100)
        private String codeEmplTitleClass;

        /** Pay Frequency */
        @Column(name="code_empl_pay_calc", nullable=true, length=100)
        private String codeEmplPayCalc;

        /** null */
        @Column(name="student_status", nullable=true, length=1)
        private String studentStatus;

        /** null */
        @Column(name="filler", nullable=true, length=100)
        private String filler;

        /** null */
        @Column(name="addr_empl_city_home", nullable=true, length=100)
        private String addrEmplCityHome;

        /** null */
        @Column(name="code_empl_bnfts_rate", nullable=true, length=100)
        private String codeEmplBnftsRate;

        /** null */
        @Column(name="alt_job_title", nullable=true, length=128)
        private String altJobTitle;

        /** null */
        @Column(name="addr_empl_offc_state", nullable=true, length=100)
        private String addrEmplOffcState;

        /** null */
        @Column(name="name_jobd_title_long", nullable=true, length=100)
        private String nameJobdTitleLong;

        /** null */
        @Column(name="date_empl_paid", nullable=true, length=100)
        private String dateEmplPaid;

        /** null */
        @Column(name="addr_empl_campus_name", nullable=true, length=100)
        private String addrEmplCampusName;

        /** null */
        @Column(name="job_family", nullable=true, length=100)
        private String jobFamily;

        /** null */
        @Column(name="addr_empl_street_home", nullable=true, length=100)
        private String addrEmplStreetHome;

        /** null */
        @Column(name="addr_empl_offc_zip", nullable=true, length=100)
        private String addrEmplOffcZip;

        /** null */
        @Column(name="numb_pers_access_acct_id", nullable=true, length=100)
        private String numbPersAccessAcctId;

        /** null */
        @Column(name="numb_empl_phone_offc", nullable=true, length=100)
        private String numbEmplPhoneOffc;

        /** null */
        @Column(name="code_empl_sex", nullable=true, length=100)
        private String codeEmplSex;

        /** null */
        @Column(name="code_empl_class", nullable=true, length=100)
        private String codeEmplClass;

        /** null */
        @Column(name="name_empl_last_legal", nullable=true, length=100)
        private String nameEmplLastLegal;

        /** null */
        @Column(name="code_empl_title2_class", nullable=true, length=100)
        private String codeEmplTitle2Class;

        /** null */
        @Column(name="code_admin_area", nullable=true, length=100)
        private String codeAdminArea;

        /** null */
        @Column(name="code_empl_status", nullable=true, length=100)
        private String codeEmplStatus;

        /** null */
        @Column(name="code_appt", nullable=true, length=100)
        private String codeAppt;

        /** null */
        @Column(name="date_empl_birth", nullable=true, length=100)
        private String dateEmplBirth;

        /** null */
        @Column(name="code_empl_rank", nullable=true, length=100)
        private String codeEmplRank;

        /** null */
        @Column(name="addr_empl_office_street", nullable=true, length=100)
        private String addrEmplOfficeStreet;

        /** null */
        @Column(name="name_empl_mid_legal", nullable=true, length=100)
        private String nameEmplMidLegal;

        /** null */
        @Column(name="numb_empl_phone_home", nullable=true, length=100)
        private String numbEmplPhoneHome;

        /** null */
        @Column(name="code_empl_appt_special", nullable=true, length=100)
        private String codeEmplApptSpecial;

        /** null */
        @Column(name="code_empl_fnat_visa_type", nullable=true, length=100)
        private String codeEmplFnatVisaType;

        /** null */
        @Column(name="code_campus", nullable=true, length=100)
        private String codeCampus;

        /** null */
        @Column(name="addr_empl_building_name", nullable=true, length=100)
        private String addrEmplBuildingName;

        /** null */
        @Column(name="addr_empl_office_city", nullable=true, length=100)
        private String addrEmplOfficeCity;

        /** null */
        @Column(name="code_mnemonic", nullable=true, length=100)
        private String codeMnemonic;

        /** null */
        @Column(name="date_empl_termn", nullable=true, length=100)
        private String dateEmplTermn;

        /** null */
        @Column(name="name_jobd_title_short", nullable=true, length=100)
        private String nameJobdTitleShort;

        /** null */
        @Column(name="addr_empl_room_number", nullable=true, length=100)
        private String addrEmplRoomNumber;

        /** Contains a unique number that identifies a trans_empl record. It is poulated during processing of input file. */
        @Id
        @Column(name="trans_empl_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_trans_empl")
        @SequenceGenerator(name="seq_trans_empl", sequenceName="seq_trans_empl", allocationSize = 1, initialValue= 1)
        private Long transEmplKey;

        /** null */
        @Column(name="code_empl_country_home", nullable=true, length=100)
        private String codeEmplCountryHome;

        /**
         * Constructor
         */
        public TransEmpl() {
            super();
        }

        /**
         * @return the codeApptType
         */
        public String getCodeApptType() {
                return codeApptType;
        }

        /**
         * @param codeApptType the codeApptType to set.
         */
        public void setCodeApptType(String codeApptType) {
                this.codeApptType = codeApptType;
        }

        /**
         * @return the nameEmplDepartment
         */
        public String getNameEmplDepartment() {
                return nameEmplDepartment;
        }

        /**
         * @param nameEmplDepartment the nameEmplDepartment to set.
         */
        public void setNameEmplDepartment(String nameEmplDepartment) {
                this.nameEmplDepartment = nameEmplDepartment;
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
         * @return the addrEmplZipHomeSecond
         */
        public String getAddrEmplZipHomeSecond() {
                return addrEmplZipHomeSecond;
        }

        /**
         * @param addrEmplZipHomeSecond the addrEmplZipHomeSecond to set.
         */
        public void setAddrEmplZipHomeSecond(String addrEmplZipHomeSecond) {
                this.addrEmplZipHomeSecond = addrEmplZipHomeSecond;
        }

        /**
         * @return the codeEmplDirectPhone
         */
        public String getCodeEmplDirectPhone() {
                return codeEmplDirectPhone;
        }

        /**
         * @param codeEmplDirectPhone the codeEmplDirectPhone to set.
         */
        public void setCodeEmplDirectPhone(String codeEmplDirectPhone) {
                this.codeEmplDirectPhone = codeEmplDirectPhone;
        }

        /**
         * @return the addrCampusAddrLine3
         */
        public String getAddrCampusAddrLine3() {
                return addrCampusAddrLine3;
        }

        /**
         * @param addrCampusAddrLine3 the addrCampusAddrLine3 to set.
         */
        public void setAddrCampusAddrLine3(String addrCampusAddrLine3) {
                this.addrCampusAddrLine3 = addrCampusAddrLine3;
        }

        /**
         * @return the addrEmplStateHome
         */
        public String getAddrEmplStateHome() {
                return addrEmplStateHome;
        }

        /**
         * @param addrEmplStateHome the addrEmplStateHome to set.
         */
        public void setAddrEmplStateHome(String addrEmplStateHome) {
                this.addrEmplStateHome = addrEmplStateHome;
        }

        /**
         * @return the addrCampusAddrLine2
         */
        public String getAddrCampusAddrLine2() {
                return addrCampusAddrLine2;
        }

        /**
         * @param addrCampusAddrLine2 the addrCampusAddrLine2 to set.
         */
        public void setAddrCampusAddrLine2(String addrCampusAddrLine2) {
                this.addrCampusAddrLine2 = addrCampusAddrLine2;
        }

        /**
         * @return the addrCampusAddrLine1
         */
        public String getAddrCampusAddrLine1() {
                return addrCampusAddrLine1;
        }

        /**
         * @param addrCampusAddrLine1 the addrCampusAddrLine1 to set.
         */
        public void setAddrCampusAddrLine1(String addrCampusAddrLine1) {
                this.addrCampusAddrLine1 = addrCampusAddrLine1;
        }

        /**
         * @return the codeBudgLocationHome
         */
        public String getCodeBudgLocationHome() {
                return codeBudgLocationHome;
        }

        /**
         * @param codeBudgLocationHome the codeBudgLocationHome to set.
         */
        public void setCodeBudgLocationHome(String codeBudgLocationHome) {
                this.codeBudgLocationHome = codeBudgLocationHome;
        }

        /**
         * @return the addrEmplSt2Home
         */
        public String getAddrEmplSt2Home() {
                return addrEmplSt2Home;
        }

        /**
         * @param addrEmplSt2Home the addrEmplSt2Home to set.
         */
        public void setAddrEmplSt2Home(String addrEmplSt2Home) {
                this.addrEmplSt2Home = addrEmplSt2Home;
        }

        /**
         * @return the nameEmplAdminArea
         */
        public String getNameEmplAdminArea() {
                return nameEmplAdminArea;
        }

        /**
         * @param nameEmplAdminArea the nameEmplAdminArea to set.
         */
        public void setNameEmplAdminArea(String nameEmplAdminArea) {
                this.nameEmplAdminArea = nameEmplAdminArea;
        }

        /**
         * @return the nameEmplFirstLegal
         */
        public String getNameEmplFirstLegal() {
                return nameEmplFirstLegal;
        }

        /**
         * @param nameEmplFirstLegal the nameEmplFirstLegal to set.
         */
        public void setNameEmplFirstLegal(String nameEmplFirstLegal) {
                this.nameEmplFirstLegal = nameEmplFirstLegal;
        }

        /**
         * @return the codeEmplLayoff
         */
        public String getCodeEmplLayoff() {
                return codeEmplLayoff;
        }

        /**
         * @param codeEmplLayoff the codeEmplLayoff to set.
         */
        public void setCodeEmplLayoff(String codeEmplLayoff) {
                this.codeEmplLayoff = codeEmplLayoff;
        }

        /**
         * @return the dateEmplHired
         */
        public String getDateEmplHired() {
                return dateEmplHired;
        }

        /**
         * @param dateEmplHired the dateEmplHired to set.
         */
        public void setDateEmplHired(String dateEmplHired) {
                this.dateEmplHired = dateEmplHired;
        }

        /**
         * @return the addrEmplZipHomePrime
         */
        public String getAddrEmplZipHomePrime() {
                return addrEmplZipHomePrime;
        }

        /**
         * @param addrEmplZipHomePrime the addrEmplZipHomePrime to set.
         */
        public void setAddrEmplZipHomePrime(String addrEmplZipHomePrime) {
                this.addrEmplZipHomePrime = addrEmplZipHomePrime;
        }

        /**
         * @return the nameEmplPmPreferredName
         */
        public String getNameEmplPmPreferredName() {
                return nameEmplPmPreferredName;
        }

        /**
         * @param nameEmplPmPreferredName the nameEmplPmPreferredName to set.
         */
        public void setNameEmplPmPreferredName(String nameEmplPmPreferredName) {
                this.nameEmplPmPreferredName = nameEmplPmPreferredName;
        }

        /**
         * @return the codeBudgNumbHome
         */
        public String getCodeBudgNumbHome() {
                return codeBudgNumbHome;
        }

        /**
         * @param codeBudgNumbHome the codeBudgNumbHome to set.
         */
        public void setCodeBudgNumbHome(String codeBudgNumbHome) {
                this.codeBudgNumbHome = codeBudgNumbHome;
        }

        /**
         * @return the nameEmplSfxLegal
         */
        public String getNameEmplSfxLegal() {
                return nameEmplSfxLegal;
        }

        /**
         * @param nameEmplSfxLegal the nameEmplSfxLegal to set.
         */
        public void setNameEmplSfxLegal(String nameEmplSfxLegal) {
                this.nameEmplSfxLegal = nameEmplSfxLegal;
        }

        /**
         * @return the codeEmplTitleClass
         */
        public String getCodeEmplTitleClass() {
                return codeEmplTitleClass;
        }

        /**
         * @param codeEmplTitleClass the codeEmplTitleClass to set.
         */
        public void setCodeEmplTitleClass(String codeEmplTitleClass) {
                this.codeEmplTitleClass = codeEmplTitleClass;
        }

        /**
         * @return the codeEmplPayCalc
         */
        public String getCodeEmplPayCalc() {
                return codeEmplPayCalc;
        }

        /**
         * @param codeEmplPayCalc the codeEmplPayCalc to set.
         */
        public void setCodeEmplPayCalc(String codeEmplPayCalc) {
                this.codeEmplPayCalc = codeEmplPayCalc;
        }

        /**
         * @return the studentStatus
         */
        public String getStudentStatus() {
                return studentStatus;
        }

        /**
         * @param studentStatus the studentStatus to set.
         */
        public void setStudentStatus(String studentStatus) {
                this.studentStatus = studentStatus;
        }

        /**
         * @return the filler
         */
        public String getFiller() {
                return filler;
        }

        /**
         * @param filler the filler to set.
         */
        public void setFiller(String filler) {
                this.filler = filler;
        }

        /**
         * @return the addrEmplCityHome
         */
        public String getAddrEmplCityHome() {
                return addrEmplCityHome;
        }

        /**
         * @param addrEmplCityHome the addrEmplCityHome to set.
         */
        public void setAddrEmplCityHome(String addrEmplCityHome) {
                this.addrEmplCityHome = addrEmplCityHome;
        }

        /**
         * @return the codeEmplBnftsRate
         */
        public String getCodeEmplBnftsRate() {
                return codeEmplBnftsRate;
        }

        /**
         * @param codeEmplBnftsRate the codeEmplBnftsRate to set.
         */
        public void setCodeEmplBnftsRate(String codeEmplBnftsRate) {
                this.codeEmplBnftsRate = codeEmplBnftsRate;
        }

        /**
         * @return the altJobTitle
         */
        public String getAltJobTitle() {
                return altJobTitle;
        }

        /**
         * @param altJobTitle the altJobTitle to set.
         */
        public void setAltJobTitle(String altJobTitle) {
                this.altJobTitle = altJobTitle;
        }

        /**
         * @return the addrEmplOffcState
         */
        public String getAddrEmplOffcState() {
                return addrEmplOffcState;
        }

        /**
         * @param addrEmplOffcState the addrEmplOffcState to set.
         */
        public void setAddrEmplOffcState(String addrEmplOffcState) {
                this.addrEmplOffcState = addrEmplOffcState;
        }

        /**
         * @return the nameJobdTitleLong
         */
        public String getNameJobdTitleLong() {
                return nameJobdTitleLong;
        }

        /**
         * @param nameJobdTitleLong the nameJobdTitleLong to set.
         */
        public void setNameJobdTitleLong(String nameJobdTitleLong) {
                this.nameJobdTitleLong = nameJobdTitleLong;
        }

        /**
         * @return the dateEmplPaid
         */
        public String getDateEmplPaid() {
                return dateEmplPaid;
        }

        /**
         * @param dateEmplPaid the dateEmplPaid to set.
         */
        public void setDateEmplPaid(String dateEmplPaid) {
                this.dateEmplPaid = dateEmplPaid;
        }

        /**
         * @return the addrEmplCampusName
         */
        public String getAddrEmplCampusName() {
                return addrEmplCampusName;
        }

        /**
         * @param addrEmplCampusName the addrEmplCampusName to set.
         */
        public void setAddrEmplCampusName(String addrEmplCampusName) {
                this.addrEmplCampusName = addrEmplCampusName;
        }

        /**
         * @return the jobFamily
         */
        public String getJobFamily() {
                return jobFamily;
        }

        /**
         * @param jobFamily the jobFamily to set.
         */
        public void setJobFamily(String jobFamily) {
                this.jobFamily = jobFamily;
        }

        /**
         * @return the addrEmplStreetHome
         */
        public String getAddrEmplStreetHome() {
                return addrEmplStreetHome;
        }

        /**
         * @param addrEmplStreetHome the addrEmplStreetHome to set.
         */
        public void setAddrEmplStreetHome(String addrEmplStreetHome) {
                this.addrEmplStreetHome = addrEmplStreetHome;
        }

        /**
         * @return the addrEmplOffcZip
         */
        public String getAddrEmplOffcZip() {
                return addrEmplOffcZip;
        }

        /**
         * @param addrEmplOffcZip the addrEmplOffcZip to set.
         */
        public void setAddrEmplOffcZip(String addrEmplOffcZip) {
                this.addrEmplOffcZip = addrEmplOffcZip;
        }

        /**
         * @return the numbPersAccessAcctId
         */
        public String getNumbPersAccessAcctId() {
                return numbPersAccessAcctId;
        }

        /**
         * @param numbPersAccessAcctId the numbPersAccessAcctId to set.
         */
        public void setNumbPersAccessAcctId(String numbPersAccessAcctId) {
                this.numbPersAccessAcctId = numbPersAccessAcctId;
        }

        /**
         * @return the numbEmplPhoneOffc
         */
        public String getNumbEmplPhoneOffc() {
                return numbEmplPhoneOffc;
        }

        /**
         * @param numbEmplPhoneOffc the numbEmplPhoneOffc to set.
         */
        public void setNumbEmplPhoneOffc(String numbEmplPhoneOffc) {
                this.numbEmplPhoneOffc = numbEmplPhoneOffc;
        }

        /**
         * @return the codeEmplSex
         */
        public String getCodeEmplSex() {
                return codeEmplSex;
        }

        /**
         * @param codeEmplSex the codeEmplSex to set.
         */
        public void setCodeEmplSex(String codeEmplSex) {
                this.codeEmplSex = codeEmplSex;
        }

        /**
         * @return the codeEmplClass
         */
        public String getCodeEmplClass() {
                return codeEmplClass;
        }

        /**
         * @param codeEmplClass the codeEmplClass to set.
         */
        public void setCodeEmplClass(String codeEmplClass) {
                this.codeEmplClass = codeEmplClass;
        }

        /**
         * @return the nameEmplLastLegal
         */
        public String getNameEmplLastLegal() {
                return nameEmplLastLegal;
        }

        /**
         * @param nameEmplLastLegal the nameEmplLastLegal to set.
         */
        public void setNameEmplLastLegal(String nameEmplLastLegal) {
                this.nameEmplLastLegal = nameEmplLastLegal;
        }

        /**
         * @return the codeEmplTitle2Class
         */
        public String getCodeEmplTitle2Class() {
                return codeEmplTitle2Class;
        }

        /**
         * @param codeEmplTitle2Class the codeEmplTitle2Class to set.
         */
        public void setCodeEmplTitle2Class(String codeEmplTitle2Class) {
                this.codeEmplTitle2Class = codeEmplTitle2Class;
        }

        /**
         * @return the codeAdminArea
         */
        public String getCodeAdminArea() {
                return codeAdminArea;
        }

        /**
         * @param codeAdminArea the codeAdminArea to set.
         */
        public void setCodeAdminArea(String codeAdminArea) {
                this.codeAdminArea = codeAdminArea;
        }

        /**
         * @return the codeEmplStatus
         */
        public String getCodeEmplStatus() {
                return codeEmplStatus;
        }

        /**
         * @param codeEmplStatus the codeEmplStatus to set.
         */
        public void setCodeEmplStatus(String codeEmplStatus) {
                this.codeEmplStatus = codeEmplStatus;
        }

        /**
         * @return the codeAppt
         */
        public String getCodeAppt() {
                return codeAppt;
        }

        /**
         * @param codeAppt the codeAppt to set.
         */
        public void setCodeAppt(String codeAppt) {
                this.codeAppt = codeAppt;
        }

        /**
         * @return the dateEmplBirth
         */
        public String getDateEmplBirth() {
                return dateEmplBirth;
        }

        /**
         * @param dateEmplBirth the dateEmplBirth to set.
         */
        public void setDateEmplBirth(String dateEmplBirth) {
                this.dateEmplBirth = dateEmplBirth;
        }

        /**
         * @return the codeEmplRank
         */
        public String getCodeEmplRank() {
                return codeEmplRank;
        }

        /**
         * @param codeEmplRank the codeEmplRank to set.
         */
        public void setCodeEmplRank(String codeEmplRank) {
                this.codeEmplRank = codeEmplRank;
        }

        /**
         * @return the addrEmplOfficeStreet
         */
        public String getAddrEmplOfficeStreet() {
                return addrEmplOfficeStreet;
        }

        /**
         * @param addrEmplOfficeStreet the addrEmplOfficeStreet to set.
         */
        public void setAddrEmplOfficeStreet(String addrEmplOfficeStreet) {
                this.addrEmplOfficeStreet = addrEmplOfficeStreet;
        }

        /**
         * @return the nameEmplMidLegal
         */
        public String getNameEmplMidLegal() {
                return nameEmplMidLegal;
        }

        /**
         * @param nameEmplMidLegal the nameEmplMidLegal to set.
         */
        public void setNameEmplMidLegal(String nameEmplMidLegal) {
                this.nameEmplMidLegal = nameEmplMidLegal;
        }

        /**
         * @return the numbEmplPhoneHome
         */
        public String getNumbEmplPhoneHome() {
                return numbEmplPhoneHome;
        }

        /**
         * @param numbEmplPhoneHome the numbEmplPhoneHome to set.
         */
        public void setNumbEmplPhoneHome(String numbEmplPhoneHome) {
                this.numbEmplPhoneHome = numbEmplPhoneHome;
        }

        /**
         * @return the codeEmplApptSpecial
         */
        public String getCodeEmplApptSpecial() {
                return codeEmplApptSpecial;
        }

        /**
         * @param codeEmplApptSpecial the codeEmplApptSpecial to set.
         */
        public void setCodeEmplApptSpecial(String codeEmplApptSpecial) {
                this.codeEmplApptSpecial = codeEmplApptSpecial;
        }

        /**
         * @return the codeEmplFnatVisaType
         */
        public String getCodeEmplFnatVisaType() {
                return codeEmplFnatVisaType;
        }

        /**
         * @param codeEmplFnatVisaType the codeEmplFnatVisaType to set.
         */
        public void setCodeEmplFnatVisaType(String codeEmplFnatVisaType) {
                this.codeEmplFnatVisaType = codeEmplFnatVisaType;
        }

        /**
         * @return the codeCampus
         */
        public String getCodeCampus() {
                return codeCampus;
        }

        /**
         * @param codeCampus the codeCampus to set.
         */
        public void setCodeCampus(String codeCampus) {
                this.codeCampus = codeCampus;
        }

        /**
         * @return the addrEmplBuildingName
         */
        public String getAddrEmplBuildingName() {
                return addrEmplBuildingName;
        }

        /**
         * @param addrEmplBuildingName the addrEmplBuildingName to set.
         */
        public void setAddrEmplBuildingName(String addrEmplBuildingName) {
                this.addrEmplBuildingName = addrEmplBuildingName;
        }

        /**
         * @return the addrEmplOfficeCity
         */
        public String getAddrEmplOfficeCity() {
                return addrEmplOfficeCity;
        }

        /**
         * @param addrEmplOfficeCity the addrEmplOfficeCity to set.
         */
        public void setAddrEmplOfficeCity(String addrEmplOfficeCity) {
                this.addrEmplOfficeCity = addrEmplOfficeCity;
        }

        /**
         * @return the codeMnemonic
         */
        public String getCodeMnemonic() {
                return codeMnemonic;
        }

        /**
         * @param codeMnemonic the codeMnemonic to set.
         */
        public void setCodeMnemonic(String codeMnemonic) {
                this.codeMnemonic = codeMnemonic;
        }

        /**
         * @return the dateEmplTermn
         */
        public String getDateEmplTermn() {
                return dateEmplTermn;
        }

        /**
         * @param dateEmplTermn the dateEmplTermn to set.
         */
        public void setDateEmplTermn(String dateEmplTermn) {
                this.dateEmplTermn = dateEmplTermn;
        }

        /**
         * @return the nameJobdTitleShort
         */
        public String getNameJobdTitleShort() {
                return nameJobdTitleShort;
        }

        /**
         * @param nameJobdTitleShort the nameJobdTitleShort to set.
         */
        public void setNameJobdTitleShort(String nameJobdTitleShort) {
                this.nameJobdTitleShort = nameJobdTitleShort;
        }

        /**
         * @return the addrEmplRoomNumber
         */
        public String getAddrEmplRoomNumber() {
                return addrEmplRoomNumber;
        }

        /**
         * @param addrEmplRoomNumber the addrEmplRoomNumber to set.
         */
        public void setAddrEmplRoomNumber(String addrEmplRoomNumber) {
                this.addrEmplRoomNumber = addrEmplRoomNumber;
        }

        /**
         * @return the transEmplKey
         */
        public Long getTransEmplKey() {
                return transEmplKey;
        }

        /**
         * @param transEmplKey the transEmplKey to set.
         */
        public void setTransEmplKey(Long transEmplKey) {
                this.transEmplKey = transEmplKey;
        }

        /**
         * @return the codeEmplCountryHome
         */
        public String getCodeEmplCountryHome() {
                return codeEmplCountryHome;
        }

        /**
         * @param codeEmplCountryHome the codeEmplCountryHome to set.
         */
        public void setCodeEmplCountryHome(String codeEmplCountryHome) {
                this.codeEmplCountryHome = codeEmplCountryHome;
        }

}
