/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.core.database.types;

/**
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
 * Batch Processing Sources
 * @package edu.psu.iam.cpr.core.database.types
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public enum BatchDataSource {
	ISIS,
	IBIS,
	HMC,
	OASIS,
	NAME_POSTPROCESS,
	ADDRESS_POSTPROCESS,
	UNIT_TEST, 
	CPR_PRIME,
	CIDR;
}
