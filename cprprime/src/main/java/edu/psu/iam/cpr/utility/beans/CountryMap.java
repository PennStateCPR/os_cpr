/* SVN FILE: $Id: CountryMap.java 5084 2012-09-13 14:49:56Z jvuccolo $ */
package edu.psu.iam.cpr.utility.beans;

import java.io.Serializable;
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
 * @version $Rev: 5084 $
 * @lastrevision $Date: 2012-09-13 10:49:56 -0400 (Thu, 13 Sep 2012) $
 */

@Entity
@Table(name="country_map")
public class CountryMap implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the countryKey. */
        @Id
        @Column(name="country_key", nullable=true)
        private Long countryKey;

        /** Contains the alpha2. */
        @Column(name="alpha2", nullable=true, length=2)
        private String alpha2;

        /** Contains the alpha3. */
        @Column(name="alpha3", nullable=true, length=3)
        private String alpha3;

        /** Contains the code2. */
        @Column(name="code2", nullable=true, length=2)
        private String code2;

        /** Contains the country. */
        @Column(name="country", nullable=true, length=50)
        private String country;

        /** Contains the isoKey. */
        @Column(name="iso_key", nullable=true)
        private Long isoKey;

        /** Contains the code3. */
        @Column(name="code3", nullable=true, length=3)
        private String code3;

        /** Contains the shortName. */
        @Column(name="short_name", nullable=true, length=50)
        private String shortName;

        /** Contains the countryMapKey. */
        @Column(name="country_map_key", nullable=true)
        private Long countryMapKey;

        /**
         * Constructor
         */
        public CountryMap() {
            super();
        }

        /**
         * @return the countryKey
         */
        public Long getCountryKey() {
                return countryKey;
        }

        /**
         * @param countryKey the countryKey to set.
         */
        public void setCountryKey(Long countryKey) {
                this.countryKey = countryKey;
        }

        /**
         * @return the alpha2
         */
        public String getAlpha2() {
                return alpha2;
        }

        /**
         * @param alpha2 the alpha2 to set.
         */
        public void setAlpha2(String alpha2) {
                this.alpha2 = alpha2;
        }

        /**
         * @return the alpha3
         */
        public String getAlpha3() {
                return alpha3;
        }

        /**
         * @param alpha3 the alpha3 to set.
         */
        public void setAlpha3(String alpha3) {
                this.alpha3 = alpha3;
        }

        /**
         * @return the code2
         */
        public String getCode2() {
                return code2;
        }

        /**
         * @param code2 the code2 to set.
         */
        public void setCode2(String code2) {
                this.code2 = code2;
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
         * @return the isoKey
         */
        public Long getIsoKey() {
                return isoKey;
        }

        /**
         * @param isoKey the isoKey to set.
         */
        public void setIsoKey(Long isoKey) {
                this.isoKey = isoKey;
        }

        /**
         * @return the code3
         */
        public String getCode3() {
                return code3;
        }

        /**
         * @param code3 the code3 to set.
         */
        public void setCode3(String code3) {
                this.code3 = code3;
        }

        /**
         * @return the shortName
         */
        public String getShortName() {
                return shortName;
        }

        /**
         * @param shortName the shortName to set.
         */
        public void setShortName(String shortName) {
                this.shortName = shortName;
        }

        /**
         * @return the countryMapKey
         */
        public Long getCountryMapKey() {
                return countryMapKey;
        }

        /**
         * @param countryMapKey the countryMapKey to set.
         */
        public void setCountryMapKey(Long countryMapKey) {
                this.countryMapKey = countryMapKey;
        }

}
