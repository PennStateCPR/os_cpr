/* SVN FILE: $Id: RulesEngineHelper.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.rules.engine;

import java.util.ArrayList;

import edu.psu.iam.cpr.core.error.ReturnType;

/**
 * This class implements a wrapper around the rules engine.
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
 * 
 * @package edu.psu.iam.cpr.core.rules.engine
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class RulesEngineHelper {
	
	/**
	 * Constructor
	 */
	public RulesEngineHelper() {
		super();
	}

	/**
	 * This routine is used to process rules and return the results of processing.
	 * @param rulesetName contains the ruleset filename.
	 * @param knownFacts contains the array of known facts.
	 * @param newFact contains the new fact to be asserted.
	 * @return RulesReturn will return a RuleReturn class upon successful processing of the ruleset.
	 */
	public RulesReturn processRules(String rulesetName, String[] knownFacts, String newFact) {

		RulesReturn rulesReturn = new RulesReturn();

		try {

			// Create the knowledge base for the service.
			final RulesEngine rulesEngine = new RulesEngine(rulesetName, knownFacts, newFact);

			rulesEngine.createKnowledgeBase();

			final int returnValue = rulesEngine.executeRules();
			
			rulesReturn.setStatusCode(returnValue);
			if (returnValue == ReturnType.SUCCESS.index()) {
				rulesReturn.setStatusMessage("Success!");
			}
			else {
				rulesReturn.setStatusMessage("A rule processing error was encountered!");
			}
			
			ArrayList<String> results = rulesEngine.getRuleExecutionResults();
			rulesReturn.setNumberOfFacts(results.size());
			rulesReturn.setFacts(results.toArray(new String[results.size()]));
		}

		catch (Exception e) {
			e.printStackTrace();
			return new RulesReturn(ReturnType.GENERAL_EXCEPTION.index(), e.getMessage());
		} 
		return rulesReturn;
	}
}
