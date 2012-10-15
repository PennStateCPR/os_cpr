/* SVN FILE: $Id: RulesEngine.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.rules.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import edu.psu.iam.cpr.core.error.ReturnType;


/**
 * Provides the implementation of the RulesEngine class.
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
 * @package edu.psu.iam.cpr.core.rules.engine
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class RulesEngine {
	
	/** Contains the drools ruleset filename */
	private String rulesetName;
	
	/** Contains the list of known facts */
	private ArrayList<KnownFact> knownFacts = new ArrayList<KnownFact>();
	
	/** Contains the new input fact */
	private InputFact inputFact;
	
	/** Contains the drools knowledge class */
	private KnowledgeBase knowledgeBase;
	
	/** Contains the results of the rules engine */
	private ArrayList<String> ruleExecutionResults  = new ArrayList<String>();
	
	/**
	 * Constructor
	 */
	public RulesEngine() {
		super();
	}
	
	/**
	 * Constructor
	 * @param rulesetName contains the ruleset file name.
	 * @param knownFacts contains an array of strings that represent the known facts.
	 * @param inputFact contains the input fact.
	 */
	public RulesEngine(String rulesetName, String[] knownFacts, String inputFact) {
		super();
		this.rulesetName = rulesetName;
		this.inputFact = new InputFact(inputFact);
		if (knownFacts != null) {
			for (int i = 0; i < knownFacts.length; ++i) {
				this.knownFacts.add(new KnownFact(knownFacts[i]));
			}
		}
	}
	
	/**
	 * @param knowledgeBase the knowledgeBase to set
	 */
	public void setKnowledgeBase(KnowledgeBase knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}

	/**
	 * @return the knowledgeBase
	 */
	public KnowledgeBase getKnowledgeBase() {
		return knowledgeBase;
	}

	/**
	 * @return the rulesetName
	 */
	public String getRulesetName() {
		return rulesetName;
	}

	/**
	 * @param rulesetName the rulesetName to set
	 */
	public void setRulesetName(String rulesetName) {
		this.rulesetName = rulesetName;
	}

	/**
	 * @param ruleExecutionResults the ruleExecutionResults to set
	 */
	public void setRuleExecutionResults(ArrayList<String> ruleExecutionResults) {
		this.ruleExecutionResults = ruleExecutionResults;
	}

	/**
	 * @return the ruleExecutionResults
	 */
	public ArrayList<String> getRuleExecutionResults() {
		return ruleExecutionResults;
	}

	/**
	 * @return the knownFacts
	 */
	public ArrayList<KnownFact> getKnownFacts() {
		return knownFacts;
	}

	/**
	 * @param knownFacts the knownFacts to set
	 */
	public void setKnownFacts(ArrayList<KnownFact> knownFacts) {
		this.knownFacts = knownFacts;
	}

	/**
	 * @return the inputFact
	 */
	public InputFact getInputFact() {
		return inputFact;
	}

	/**
	 * @param inputFact the inputFact to set
	 */
	public void setInputFact(InputFact inputFact) {
		this.inputFact = inputFact;
	}

	/**
	 * This routine is used to load a ruleset into working memory.
	 */
	public void createKnowledgeBase() {  
		
		final KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();  
		builder.add(ResourceFactory.newClassPathResource(getRulesetName()), ResourceType.DRL);  
		if (builder.hasErrors()) {  
			throw new RuntimeException(builder.getErrors().toString());  
		}  

		final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();    
		kbase.addKnowledgePackages(builder.getKnowledgePackages());
		setKnowledgeBase(kbase);
		
    } 
	
	/**
	 * This routine is used to execute the rules engine.
	 * @return will either be SUCCESS or an EXCEPTION.
	 */
	public int executeRules() {
		
		int returnValue = ReturnType.SUCCESS.index();
		
		// Create a new session.
		final StatefulKnowledgeSession session = getKnowledgeBase().newStatefulKnowledgeSession();

		// Insert the known and new facts.
		for (final Iterator<KnownFact> it = getKnownFacts().iterator(); it.hasNext(); ) {
			KnownFact knownFact = it.next();
			session.insert(knownFact);
		}	
		
		session.insert(getInputFact());

		// Fire all of the rules.
		session.fireAllRules();

		// Get the results from working memory.
		final Collection<Object> facts = session.getObjects();
		final ArrayList<String> results = getRuleExecutionResults();
		for (final Iterator<Object> it = facts.iterator(); it.hasNext(); ) {
			Object obj = it.next();
			if (obj instanceof KnownFact) {
				KnownFact fact = (KnownFact) obj;
				if (fact.getFact().equals("ERROR")) {
					returnValue = ReturnType.GENERAL_EXCEPTION.index();
				}
				results.add(fact.getFact());
			}
			else if (obj instanceof InputFact) {
				InputFact fact = (InputFact) obj;
				if (fact.getFact().equals("ERROR")) {
					returnValue = ReturnType.GENERAL_EXCEPTION.index();
				}
				results.add(fact.getFact());
			}
		}
		
		if (facts.size() == 0) {
			returnValue = ReturnType.GENERAL_EXCEPTION.index();
		}

		// Dispose of the session.
		session.dispose();
		
		return returnValue;
	}
	
}
