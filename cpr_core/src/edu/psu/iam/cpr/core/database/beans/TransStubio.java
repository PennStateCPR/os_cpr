/* SVN FILE: $Id: TransStubio.java 6885 2013-04-15 14:06:49Z llg5 $ */
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
 * @version $Rev: 6885 $
 * @lastrevision $Date: 2013-04-15 10:06:49 -0400 (Mon, 15 Apr 2013) $
 */

@Entity
@Table(name="trans_stubio")
public class TransStubio implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** null */
        @Column(name="indc_stud_graduation", nullable=true, length=1000)
        private String indcStudGraduation;

        /** null */
        @Column(name="req_sem", nullable=true, length=1000)
        private String reqSem;

        /** null */
        @Column(name="stud_reg", nullable=true, length=1000)
        private String studReg;

        /** null */
        @Column(name="code_stud_acdt2", nullable=true, length=1000)
        private String codeStudAcdt2;

        /** null */
        @Column(name="preferred_name", nullable=true, length=100)
        private String preferredName;

        /** null */
        @Column(name="code_stud_acdt1", nullable=true, length=1000)
        private String codeStudAcdt1;

        /** null */
        @Column(name="phone_local", nullable=true, length=1000)
        private String phoneLocal;

        /** null */
        @Column(name="addr_locl_zip", nullable=true, length=1000)
        private String addrLoclZip;

        /** null */
        @Column(name="non_psu_email_addr", nullable=true, length=100)
        private String nonPsuEmailAddr;

        /** null */
        @Column(name="code_stud_acdt4", nullable=true, length=1000)
        private String codeStudAcdt4;

        /** null */
        @Column(name="addr_locl_ctry", nullable=true, length=1000)
        private String addrLoclCtry;

        /** null */
        @Column(name="student_aid", nullable=true, length=100)
        private String studentAid;

        /** null */
        @Column(name="code_stud_acdt3", nullable=true, length=1000)
        private String codeStudAcdt3;

        /** null */
        @Column(name="code_stud_coll4", nullable=true, length=1000)
        private String codeStudColl4;

        /** null */
        @Column(name="code_stud_coll3", nullable=true, length=1000)
        private String codeStudColl3;

        /** null */
        @Column(name="code_stud_coll2", nullable=true, length=1000)
        private String codeStudColl2;

        /** null */
        @Column(name="code_stud_coll1", nullable=true, length=1000)
        private String codeStudColl1;

        /** null */
        @Column(name="addr_home_ctry", nullable=true, length=1000)
        private String addrHomeCtry;

        /** null */
        @Column(name="psu_id", nullable=false, length=100)
        private String psuId;

        /** null */
        @Column(name="addr_home_zip", nullable=true, length=1000)
        private String addrHomeZip;

        /** null */
        @Column(name="cell_carrier", nullable=true, length=100)
        private String cellCarrier;

        /** null */
        @Column(name="code_camp", nullable=true, length=1000)
        private String codeCamp;

        /** null */
        @Column(name="addr_home_st3", nullable=true, length=1000)
        private String addrHomeSt3;

        /** null */
        @Column(name="type", nullable=true, length=1000)
        private String type;

        /** null */
        @Column(name="appl_stat", nullable=true, length=1000)
        private String applStat;

        /** null */
        @Column(name="addr_home_city", nullable=true, length=1000)
        private String addrHomeCity;

        /** null */
        @Column(name="psu_id_prev", nullable=true, length=100)
        private String psuIdPrev;

        /** null */
        @Column(name="addr_home_state", nullable=true, length=1000)
        private String addrHomeState;

        /** null */
        @Column(name="code_stud_clsfctn_yrtm", nullable=true, length=1000)
        private String codeStudClsfctnYrtm;

        /** null */
        @Column(name="addr_home_st1", nullable=true, length=1000)
        private String addrHomeSt1;

        /** null */
        @Column(name="loa_start", nullable=true, length=1000)
        private String loaStart;

        /** null */
        @Column(name="addr_home_st2", nullable=true, length=1000)
        private String addrHomeSt2;

        /** null */
        @Column(name="code_stud_lvl", nullable=true, length=1000)
        private String codeStudLvl;

        /** null */
        @Column(name="date_pers_birth", nullable=true, length=1000)
        private String datePersBirth;

        /** null */
        @Column(name="cell_phone", nullable=true, length=100)
        private String cellPhone;

        /** null */
        @Column(name="code_stud_admit_univ_yrterm", nullable=true, length=1000)
        private String codeStudAdmitUnivYrterm;

        /** null */
        @Column(name="sem", nullable=true, length=1000)
        private String sem;

        /** null */
        @Column(name="stud_hold", nullable=true, length=1000)
        private String studHold;

        /** null */
        @Column(name="code_pers_cznshp_st2", nullable=true, length=1000)
        private String codePersCznshpSt2;

        /** null */
        @Column(name="code_pers_residence", nullable=true, length=1000)
        private String codePersResidence;

        /** null */
        @Column(name="appl_type", nullable=true, length=1000)
        private String applType;

        /** null */
        @Column(name="addr_locl_city", nullable=true, length=1000)
        private String addrLoclCity;

        /** null */
        @Column(name="name_pers_last", nullable=true, length=1000)
        private String namePersLast;

        /** null */
        @Column(name="code_stud_majr4", nullable=true, length=1000)
        private String codeStudMajr4;

        /** null */
        @Column(name="code_stud_majr3", nullable=true, length=1000)
        private String codeStudMajr3;

        /** null */
        @Column(name="code_stud_majr2", nullable=true, length=1000)
        private String codeStudMajr2;

        /** null */
        @Column(name="code_stud_majr1", nullable=true, length=1000)
        private String codeStudMajr1;

        /** null */
        @Column(name="fps_id", nullable=true, length=8)
        private String fpsId;

        /** null */
        @Column(name="name_pers_mid", nullable=true, length=1000)
        private String namePersMid;

        /** null */
        @Column(name="code_hotl", nullable=true, length=10)
        private String codeHotl;

        /** null */
        @Column(name="name_pers_sfx", nullable=true, length=1000)
        private String namePersSfx;

        /** null */
        @Column(name="date_scholar", nullable=true, length=1000)
        private String dateScholar;

        /** null */
        @Column(name="addr_locl_state", nullable=true, length=1000)
        private String addrLoclState;

        /** null */
        @Column(name="code_scholar", nullable=true, length=1000)
        private String codeScholar;

        /** null */
        @Column(name="name_pers_first", nullable=true, length=1000)
        private String namePersFirst;

        /** null */
        @Column(name="phone_home", nullable=true, length=1000)
        private String phoneHome;

        /** null */
        @Column(name="numb_stud_access_acct_id", nullable=true, length=1000)
        private String numbStudAccessAcctId;

        /** null */
        @Column(name="code_stud_stat", nullable=true, length=1000)
        private String codeStudStat;

        /** null */
        @Column(name="date_confid_end", nullable=true, length=1000)
        private String dateConfidEnd;

        /** null */
        @Column(name="date_counsel", nullable=true, length=1000)
        private String dateCounsel;

        /** null */
        @Column(name="class_load", nullable=true, length=1)
        private String classLoad;

        /** null */
        @Column(name="date_confid_beg", nullable=true, length=1000)
        private String dateConfidBeg;

        /** null */
        @Id
        @Column(name="trans_stubio_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_trans_stubio")
        @SequenceGenerator(name="seq_trans_stubio", sequenceName="seq_trans_stubio", allocationSize = 1, initialValue= 1)
        private Long transStubioKey;

        /** null */
        @Column(name="addr_locl_st1", nullable=true, length=1000)
        private String addrLoclSt1;

        /** null */
        @Column(name="code_pers_sex", nullable=true, length=1000)
        private String codePersSex;

        /** null */
        @Column(name="addr_locl_st3", nullable=true, length=1000)
        private String addrLoclSt3;

        /** null */
        @Column(name="addr_locl_st2", nullable=true, length=1000)
        private String addrLoclSt2;

        /** null */
        @Column(name="loa_return_st1", nullable=true, length=1000)
        private String loaReturnSt1;

        /**
         * Constructor
         */
        public TransStubio() {
            super();
        }

        /**
         * @return the indcStudGraduation
         */
        public String getIndcStudGraduation() {
                return indcStudGraduation;
        }

        /**
         * @param indcStudGraduation the indcStudGraduation to set.
         */
        public void setIndcStudGraduation(String indcStudGraduation) {
                this.indcStudGraduation = indcStudGraduation;
        }

        /**
         * @return the reqSem
         */
        public String getReqSem() {
                return reqSem;
        }

        /**
         * @param reqSem the reqSem to set.
         */
        public void setReqSem(String reqSem) {
                this.reqSem = reqSem;
        }

        /**
         * @return the studReg
         */
        public String getStudReg() {
                return studReg;
        }

        /**
         * @param studReg the studReg to set.
         */
        public void setStudReg(String studReg) {
                this.studReg = studReg;
        }

        /**
         * @return the codeStudAcdt2
         */
        public String getCodeStudAcdt2() {
                return codeStudAcdt2;
        }

        /**
         * @param codeStudAcdt2 the codeStudAcdt2 to set.
         */
        public void setCodeStudAcdt2(String codeStudAcdt2) {
                this.codeStudAcdt2 = codeStudAcdt2;
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
         * @return the codeStudAcdt1
         */
        public String getCodeStudAcdt1() {
                return codeStudAcdt1;
        }

        /**
         * @param codeStudAcdt1 the codeStudAcdt1 to set.
         */
        public void setCodeStudAcdt1(String codeStudAcdt1) {
                this.codeStudAcdt1 = codeStudAcdt1;
        }

        /**
         * @return the phoneLocal
         */
        public String getPhoneLocal() {
                return phoneLocal;
        }

        /**
         * @param phoneLocal the phoneLocal to set.
         */
        public void setPhoneLocal(String phoneLocal) {
                this.phoneLocal = phoneLocal;
        }

        /**
         * @return the addrLoclZip
         */
        public String getAddrLoclZip() {
                return addrLoclZip;
        }

        /**
         * @param addrLoclZip the addrLoclZip to set.
         */
        public void setAddrLoclZip(String addrLoclZip) {
                this.addrLoclZip = addrLoclZip;
        }

        /**
         * @return the nonPsuEmailAddr
         */
        public String getNonPsuEmailAddr() {
                return nonPsuEmailAddr;
        }

        /**
         * @param nonPsuEmailAddr the nonPsuEmailAddr to set.
         */
        public void setNonPsuEmailAddr(String nonPsuEmailAddr) {
                this.nonPsuEmailAddr = nonPsuEmailAddr;
        }

        /**
         * @return the codeStudAcdt4
         */
        public String getCodeStudAcdt4() {
                return codeStudAcdt4;
        }

        /**
         * @param codeStudAcdt4 the codeStudAcdt4 to set.
         */
        public void setCodeStudAcdt4(String codeStudAcdt4) {
                this.codeStudAcdt4 = codeStudAcdt4;
        }

        /**
         * @return the addrLoclCtry
         */
        public String getAddrLoclCtry() {
                return addrLoclCtry;
        }

        /**
         * @param addrLoclCtry the addrLoclCtry to set.
         */
        public void setAddrLoclCtry(String addrLoclCtry) {
                this.addrLoclCtry = addrLoclCtry;
        }

        /**
         * @return the studentAid
         */
        public String getStudentAid() {
                return studentAid;
        }

        /**
         * @param studentAid the studentAid to set.
         */
        public void setStudentAid(String studentAid) {
                this.studentAid = studentAid;
        }

        /**
         * @return the codeStudAcdt3
         */
        public String getCodeStudAcdt3() {
                return codeStudAcdt3;
        }

        /**
         * @param codeStudAcdt3 the codeStudAcdt3 to set.
         */
        public void setCodeStudAcdt3(String codeStudAcdt3) {
                this.codeStudAcdt3 = codeStudAcdt3;
        }

        /**
         * @return the codeStudColl4
         */
        public String getCodeStudColl4() {
                return codeStudColl4;
        }

        /**
         * @param codeStudColl4 the codeStudColl4 to set.
         */
        public void setCodeStudColl4(String codeStudColl4) {
                this.codeStudColl4 = codeStudColl4;
        }

        /**
         * @return the codeStudColl3
         */
        public String getCodeStudColl3() {
                return codeStudColl3;
        }

        /**
         * @param codeStudColl3 the codeStudColl3 to set.
         */
        public void setCodeStudColl3(String codeStudColl3) {
                this.codeStudColl3 = codeStudColl3;
        }

        /**
         * @return the codeStudColl2
         */
        public String getCodeStudColl2() {
                return codeStudColl2;
        }

        /**
         * @param codeStudColl2 the codeStudColl2 to set.
         */
        public void setCodeStudColl2(String codeStudColl2) {
                this.codeStudColl2 = codeStudColl2;
        }

        /**
         * @return the codeStudColl1
         */
        public String getCodeStudColl1() {
                return codeStudColl1;
        }

        /**
         * @param codeStudColl1 the codeStudColl1 to set.
         */
        public void setCodeStudColl1(String codeStudColl1) {
                this.codeStudColl1 = codeStudColl1;
        }

        /**
         * @return the addrHomeCtry
         */
        public String getAddrHomeCtry() {
                return addrHomeCtry;
        }

        /**
         * @param addrHomeCtry the addrHomeCtry to set.
         */
        public void setAddrHomeCtry(String addrHomeCtry) {
                this.addrHomeCtry = addrHomeCtry;
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
         * @return the addrHomeZip
         */
        public String getAddrHomeZip() {
                return addrHomeZip;
        }

        /**
         * @param addrHomeZip the addrHomeZip to set.
         */
        public void setAddrHomeZip(String addrHomeZip) {
                this.addrHomeZip = addrHomeZip;
        }

        /**
         * @return the cellCarrier
         */
        public String getCellCarrier() {
                return cellCarrier;
        }

        /**
         * @param cellCarrier the cellCarrier to set.
         */
        public void setCellCarrier(String cellCarrier) {
                this.cellCarrier = cellCarrier;
        }

        /**
         * @return the codeCamp
         */
        public String getCodeCamp() {
                return codeCamp;
        }

        /**
         * @param codeCamp the codeCamp to set.
         */
        public void setCodeCamp(String codeCamp) {
                this.codeCamp = codeCamp;
        }

        /**
         * @return the addrHomeSt3
         */
        public String getAddrHomeSt3() {
                return addrHomeSt3;
        }

        /**
         * @param addrHomeSt3 the addrHomeSt3 to set.
         */
        public void setAddrHomeSt3(String addrHomeSt3) {
                this.addrHomeSt3 = addrHomeSt3;
        }

        /**
         * @return the type
         */
        public String getType() {
                return type;
        }

        /**
         * @param type the type to set.
         */
        public void setType(String type) {
                this.type = type;
        }

        /**
         * @return the applStat
         */
        public String getApplStat() {
                return applStat;
        }

        /**
         * @param applStat the applStat to set.
         */
        public void setApplStat(String applStat) {
                this.applStat = applStat;
        }

        /**
         * @return the addrHomeCity
         */
        public String getAddrHomeCity() {
                return addrHomeCity;
        }

        /**
         * @param addrHomeCity the addrHomeCity to set.
         */
        public void setAddrHomeCity(String addrHomeCity) {
                this.addrHomeCity = addrHomeCity;
        }

        /**
         * @return the psuIdPrev
         */
        public String getPsuIdPrev() {
                return psuIdPrev;
        }

        /**
         * @param psuIdPrev the psuIdPrev to set.
         */
        public void setPsuIdPrev(String psuIdPrev) {
                this.psuIdPrev = psuIdPrev;
        }

        /**
         * @return the addrHomeState
         */
        public String getAddrHomeState() {
                return addrHomeState;
        }

        /**
         * @param addrHomeState the addrHomeState to set.
         */
        public void setAddrHomeState(String addrHomeState) {
                this.addrHomeState = addrHomeState;
        }

        /**
         * @return the codeStudClsfctnYrtm
         */
        public String getCodeStudClsfctnYrtm() {
                return codeStudClsfctnYrtm;
        }

        /**
         * @param codeStudClsfctnYrtm the codeStudClsfctnYrtm to set.
         */
        public void setCodeStudClsfctnYrtm(String codeStudClsfctnYrtm) {
                this.codeStudClsfctnYrtm = codeStudClsfctnYrtm;
        }

        /**
         * @return the addrHomeSt1
         */
        public String getAddrHomeSt1() {
                return addrHomeSt1;
        }

        /**
         * @param addrHomeSt1 the addrHomeSt1 to set.
         */
        public void setAddrHomeSt1(String addrHomeSt1) {
                this.addrHomeSt1 = addrHomeSt1;
        }

        /**
         * @return the loaStart
         */
        public String getLoaStart() {
                return loaStart;
        }

        /**
         * @param loaStart the loaStart to set.
         */
        public void setLoaStart(String loaStart) {
                this.loaStart = loaStart;
        }

        /**
         * @return the addrHomeSt2
         */
        public String getAddrHomeSt2() {
                return addrHomeSt2;
        }

        /**
         * @param addrHomeSt2 the addrHomeSt2 to set.
         */
        public void setAddrHomeSt2(String addrHomeSt2) {
                this.addrHomeSt2 = addrHomeSt2;
        }

        /**
         * @return the codeStudLvl
         */
        public String getCodeStudLvl() {
                return codeStudLvl;
        }

        /**
         * @param codeStudLvl the codeStudLvl to set.
         */
        public void setCodeStudLvl(String codeStudLvl) {
                this.codeStudLvl = codeStudLvl;
        }

        /**
         * @return the datePersBirth
         */
        public String getDatePersBirth() {
                return datePersBirth;
        }

        /**
         * @param datePersBirth the datePersBirth to set.
         */
        public void setDatePersBirth(String datePersBirth) {
                this.datePersBirth = datePersBirth;
        }

        /**
         * @return the cellPhone
         */
        public String getCellPhone() {
                return cellPhone;
        }

        /**
         * @param cellPhone the cellPhone to set.
         */
        public void setCellPhone(String cellPhone) {
                this.cellPhone = cellPhone;
        }

        /**
         * @return the codeStudAdmitUnivYrterm
         */
        public String getCodeStudAdmitUnivYrterm() {
                return codeStudAdmitUnivYrterm;
        }

        /**
         * @param codeStudAdmitUnivYrterm the codeStudAdmitUnivYrterm to set.
         */
        public void setCodeStudAdmitUnivYrterm(String codeStudAdmitUnivYrterm) {
                this.codeStudAdmitUnivYrterm = codeStudAdmitUnivYrterm;
        }

        /**
         * @return the sem
         */
        public String getSem() {
                return sem;
        }

        /**
         * @param sem the sem to set.
         */
        public void setSem(String sem) {
                this.sem = sem;
        }

        /**
         * @return the studHold
         */
        public String getStudHold() {
                return studHold;
        }

        /**
         * @param studHold the studHold to set.
         */
        public void setStudHold(String studHold) {
                this.studHold = studHold;
        }

        /**
         * @return the codePersCznshpSt2
         */
        public String getCodePersCznshpSt2() {
                return codePersCznshpSt2;
        }

        /**
         * @param codePersCznshpSt2 the codePersCznshpSt2 to set.
         */
        public void setCodePersCznshpSt2(String codePersCznshpSt2) {
                this.codePersCznshpSt2 = codePersCznshpSt2;
        }

        /**
         * @return the codePersResidence
         */
        public String getCodePersResidence() {
                return codePersResidence;
        }

        /**
         * @param codePersResidence the codePersResidence to set.
         */
        public void setCodePersResidence(String codePersResidence) {
                this.codePersResidence = codePersResidence;
        }

        /**
         * @return the applType
         */
        public String getApplType() {
                return applType;
        }

        /**
         * @param applType the applType to set.
         */
        public void setApplType(String applType) {
                this.applType = applType;
        }

        /**
         * @return the addrLoclCity
         */
        public String getAddrLoclCity() {
                return addrLoclCity;
        }

        /**
         * @param addrLoclCity the addrLoclCity to set.
         */
        public void setAddrLoclCity(String addrLoclCity) {
                this.addrLoclCity = addrLoclCity;
        }

        /**
         * @return the namePersLast
         */
        public String getNamePersLast() {
                return namePersLast;
        }

        /**
         * @param namePersLast the namePersLast to set.
         */
        public void setNamePersLast(String namePersLast) {
                this.namePersLast = namePersLast;
        }

        /**
         * @return the codeStudMajr4
         */
        public String getCodeStudMajr4() {
                return codeStudMajr4;
        }

        /**
         * @param codeStudMajr4 the codeStudMajr4 to set.
         */
        public void setCodeStudMajr4(String codeStudMajr4) {
                this.codeStudMajr4 = codeStudMajr4;
        }

        /**
         * @return the codeStudMajr3
         */
        public String getCodeStudMajr3() {
                return codeStudMajr3;
        }

        /**
         * @param codeStudMajr3 the codeStudMajr3 to set.
         */
        public void setCodeStudMajr3(String codeStudMajr3) {
                this.codeStudMajr3 = codeStudMajr3;
        }

        /**
         * @return the codeStudMajr2
         */
        public String getCodeStudMajr2() {
                return codeStudMajr2;
        }

        /**
         * @param codeStudMajr2 the codeStudMajr2 to set.
         */
        public void setCodeStudMajr2(String codeStudMajr2) {
                this.codeStudMajr2 = codeStudMajr2;
        }

        /**
         * @return the codeStudMajr1
         */
        public String getCodeStudMajr1() {
                return codeStudMajr1;
        }

        /**
         * @param codeStudMajr1 the codeStudMajr1 to set.
         */
        public void setCodeStudMajr1(String codeStudMajr1) {
                this.codeStudMajr1 = codeStudMajr1;
        }

        /**
         * @return the fpsId
         */
        public String getFpsId() {
                return fpsId;
        }

        /**
         * @param fpsId the fpsId to set.
         */
        public void setFpsId(String fpsId) {
                this.fpsId = fpsId;
        }

        /**
         * @return the namePersMid
         */
        public String getNamePersMid() {
                return namePersMid;
        }

        /**
         * @param namePersMid the namePersMid to set.
         */
        public void setNamePersMid(String namePersMid) {
                this.namePersMid = namePersMid;
        }

        /**
         * @return the codeHotl
         */
        public String getCodeHotl() {
                return codeHotl;
        }

        /**
         * @param codeHotl the codeHotl to set.
         */
        public void setCodeHotl(String codeHotl) {
                this.codeHotl = codeHotl;
        }

        /**
         * @return the namePersSfx
         */
        public String getNamePersSfx() {
                return namePersSfx;
        }

        /**
         * @param namePersSfx the namePersSfx to set.
         */
        public void setNamePersSfx(String namePersSfx) {
                this.namePersSfx = namePersSfx;
        }

        /**
         * @return the dateScholar
         */
        public String getDateScholar() {
                return dateScholar;
        }

        /**
         * @param dateScholar the dateScholar to set.
         */
        public void setDateScholar(String dateScholar) {
                this.dateScholar = dateScholar;
        }

        /**
         * @return the addrLoclState
         */
        public String getAddrLoclState() {
                return addrLoclState;
        }

        /**
         * @param addrLoclState the addrLoclState to set.
         */
        public void setAddrLoclState(String addrLoclState) {
                this.addrLoclState = addrLoclState;
        }

        /**
         * @return the codeScholar
         */
        public String getCodeScholar() {
                return codeScholar;
        }

        /**
         * @param codeScholar the codeScholar to set.
         */
        public void setCodeScholar(String codeScholar) {
                this.codeScholar = codeScholar;
        }

        /**
         * @return the namePersFirst
         */
        public String getNamePersFirst() {
                return namePersFirst;
        }

        /**
         * @param namePersFirst the namePersFirst to set.
         */
        public void setNamePersFirst(String namePersFirst) {
                this.namePersFirst = namePersFirst;
        }

        /**
         * @return the phoneHome
         */
        public String getPhoneHome() {
                return phoneHome;
        }

        /**
         * @param phoneHome the phoneHome to set.
         */
        public void setPhoneHome(String phoneHome) {
                this.phoneHome = phoneHome;
        }

        /**
         * @return the numbStudAccessAcctId
         */
        public String getNumbStudAccessAcctId() {
                return numbStudAccessAcctId;
        }

        /**
         * @param numbStudAccessAcctId the numbStudAccessAcctId to set.
         */
        public void setNumbStudAccessAcctId(String numbStudAccessAcctId) {
                this.numbStudAccessAcctId = numbStudAccessAcctId;
        }

        /**
         * @return the codeStudStat
         */
        public String getCodeStudStat() {
                return codeStudStat;
        }

        /**
         * @param codeStudStat the codeStudStat to set.
         */
        public void setCodeStudStat(String codeStudStat) {
                this.codeStudStat = codeStudStat;
        }

        /**
         * @return the dateConfidEnd
         */
        public String getDateConfidEnd() {
                return dateConfidEnd;
        }

        /**
         * @param dateConfidEnd the dateConfidEnd to set.
         */
        public void setDateConfidEnd(String dateConfidEnd) {
                this.dateConfidEnd = dateConfidEnd;
        }

        /**
         * @return the dateCounsel
         */
        public String getDateCounsel() {
                return dateCounsel;
        }

        /**
         * @param dateCounsel the dateCounsel to set.
         */
        public void setDateCounsel(String dateCounsel) {
                this.dateCounsel = dateCounsel;
        }

        /**
         * @return the classLoad
         */
        public String getClassLoad() {
                return classLoad;
        }

        /**
         * @param classLoad the classLoad to set.
         */
        public void setClassLoad(String classLoad) {
                this.classLoad = classLoad;
        }

        /**
         * @return the dateConfidBeg
         */
        public String getDateConfidBeg() {
                return dateConfidBeg;
        }

        /**
         * @param dateConfidBeg the dateConfidBeg to set.
         */
        public void setDateConfidBeg(String dateConfidBeg) {
                this.dateConfidBeg = dateConfidBeg;
        }

        /**
         * @return the transStubioKey
         */
        public Long getTransStubioKey() {
                return transStubioKey;
        }

        /**
         * @param transStubioKey the transStubioKey to set.
         */
        public void setTransStubioKey(Long transStubioKey) {
                this.transStubioKey = transStubioKey;
        }

        /**
         * @return the addrLoclSt1
         */
        public String getAddrLoclSt1() {
                return addrLoclSt1;
        }

        /**
         * @param addrLoclSt1 the addrLoclSt1 to set.
         */
        public void setAddrLoclSt1(String addrLoclSt1) {
                this.addrLoclSt1 = addrLoclSt1;
        }

        /**
         * @return the codePersSex
         */
        public String getCodePersSex() {
                return codePersSex;
        }

        /**
         * @param codePersSex the codePersSex to set.
         */
        public void setCodePersSex(String codePersSex) {
                this.codePersSex = codePersSex;
        }

        /**
         * @return the addrLoclSt3
         */
        public String getAddrLoclSt3() {
                return addrLoclSt3;
        }

        /**
         * @param addrLoclSt3 the addrLoclSt3 to set.
         */
        public void setAddrLoclSt3(String addrLoclSt3) {
                this.addrLoclSt3 = addrLoclSt3;
        }

        /**
         * @return the addrLoclSt2
         */
        public String getAddrLoclSt2() {
                return addrLoclSt2;
        }

        /**
         * @param addrLoclSt2 the addrLoclSt2 to set.
         */
        public void setAddrLoclSt2(String addrLoclSt2) {
                this.addrLoclSt2 = addrLoclSt2;
        }

        /**
         * @return the loaReturnSt1
         */
        public String getLoaReturnSt1() {
                return loaReturnSt1;
        }

        /**
         * @param loaReturnSt1 the loaReturnSt1 to set.
         */
        public void setLoaReturnSt1(String loaReturnSt1) {
                this.loaReturnSt1 = loaReturnSt1;
        }

}
