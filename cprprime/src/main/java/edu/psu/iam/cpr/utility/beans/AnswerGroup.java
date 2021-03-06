/* SVN FILE: $Id: BuildBean.java 5970 2013-01-04 15:50:31Z jvuccolo $ */
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
 * @version $Rev: 5970 $
 * @lastrevision $Date: 2013-01-04 10:50:31 -0500 (Fri, 04 Jan 2013) $
 */

@Entity
@Table(name="answer_group")
public class AnswerGroup implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the answerGroupKey. */
        @Id
        @Column(name="answer_group_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_answer_group")
        @SequenceGenerator(name="seq_answer_group", sequenceName="seq_answer_group", allocationSize = 1, initialValue= 1)
        private Long answerGroupKey;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the defaultSelection. */
        @Column(name="default_selection", nullable=true, length=100)
        private String defaultSelection;

        /** Contains the answerMethod. */
        @Column(name="answer_method", nullable=false, length=30)
        private String answerMethod;

        /**
         * Constructor
         */
        public AnswerGroup() {
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
         * @return the answerGroupKey
         */
        public Long getAnswerGroupKey() {
                return answerGroupKey;
        }

        /**
         * @param answerGroupKey the answerGroupKey to set.
         */
        public void setAnswerGroupKey(Long answerGroupKey) {
                this.answerGroupKey = answerGroupKey;
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
         * @return the defaultSelection
         */
        public String getDefaultSelection() {
                return defaultSelection;
        }

        /**
         * @param defaultSelection the defaultSelection to set.
         */
        public void setDefaultSelection(String defaultSelection) {
                this.defaultSelection = defaultSelection;
        }

        /**
         * @return the answerMethod
         */
        public String getAnswerMethod() {
                return answerMethod;
        }

        /**
         * @param answerMethod the answerMethod to set.
         */
        public void setAnswerMethod(String answerMethod) {
                this.answerMethod = answerMethod;
        }

}
