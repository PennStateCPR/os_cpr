/* SVN FILE: $Id: LinkedProperties.java 5823 2012-12-10 09:11:14Z jal55 $ */
package edu.psu.iam.cpr.ip.util;

/**
 * LinkedProperties is a rework of the java.util.Properties class which allows properties to be 
 * be placed into a Property table in the order they are read from the InputStream class.
 * 
 * This is needed to support global properties in the properties file, since global properties
 * are listed in the file first, and then used later.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.util 
 * @author $Author: jal55 $
 * @version $Rev: 5823 $
 * @lastrevision $Date: 2012-12-10 04:11:14 -0500 (Mon, 10 Dec 2012) $
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class LinkedProperties extends Properties
{
    private static final long serialVersionUID = 1L;

    private Map<Object, Object> linkMap = new LinkedHashMap<Object,Object>(); 

    @Override
    public synchronized Object put(Object key, Object value){
        return linkMap.put(key, value);
    }

    @Override
    public synchronized boolean contains(Object value){
        return linkMap.containsValue(value);
    }

    @Override
    public boolean containsValue(Object value){
        return linkMap.containsValue(value);
    }

    @Override
    public Set<java.util.Map.Entry<Object, Object>> entrySet(){
        return linkMap.entrySet();
    }
    
    @Override
    public synchronized Object get(Object key)
    {
    	return linkMap.get(key);
    }
    
    @Override
    public synchronized void load(InputStream inStream) throws IOException
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(inStream));

    	String dataLine = "";

    	while((dataLine = br.readLine()) != null)
    	{
    		if(dataLine.startsWith("#") || dataLine.trim().length() == 0  )
    		{
    			continue;
    		}
    		
    		boolean tokensIncludeEqualSign = false;
    		
    		// Have to use a symbol (^) other than equal signs to indicate embedded equal signs present
    		StringTokenizer stk = null;
    		if(dataLine.contains("=^"))
    		{
    			tokensIncludeEqualSign = true;
    			stk = new StringTokenizer(dataLine, "^");
    		}
    		else
    		{
    			stk = new StringTokenizer(dataLine, "=");
    		}
    		
    		String key   = stk.nextToken();
    		String value = ""; 
    		if(stk.hasMoreTokens())
    		{
    			value = stk.nextToken();
    		}

    		// Remove the equal sign from the key on special patterns 
    		if(tokensIncludeEqualSign)
    		{
    			key = key.substring(0, key.length() -1 );   
    		}
    		
    		linkMap.put(key, value);
    	}
	
    }

    @Override
    public synchronized void clear(){
        linkMap.clear();
    }

    @Override
    public synchronized boolean containsKey(Object key){
        return linkMap.containsKey(key);
    }
    
    @Override
    public String toString()
    {
    	StringBuffer sb = new StringBuffer();
    	sb.append("map={");
    	
    	Set<Map.Entry<Object, Object>> entrySet = linkMap.entrySet();
    	for(Map.Entry me: entrySet)
    	{
    		sb.append(String.format("\n[%s=%s], ", me.getKey(), me.getValue()));
    	}
    	sb.append("}");
    	
    	return sb.toString();
    }
    
    public static void main(String[] args)
    {
    	
    	/** Instance of logger */                                                     
    	final Logger log = Logger.getLogger(LinkedProperties.class);
    	
    	LinkedProperties props = new LinkedProperties();
    	Properties       props2= new Properties();
    	try 
    	{
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("ui_properties.txt"));
			props2.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("ui_properties.txt"));
			
			log.info(String.format("properties-1-> %s", props.toString()));
			log.info(String.format("properties-2-> %s", props2.toString()));
			
			// Test containsValue
			String gblSiteName = "gbl.sitename";
			log.info(String.format("true  - Does '%s' exist in old props table ->> %s", gblSiteName, props .containsKey(gblSiteName))); 
			log.info(String.format("true  - Does '%s' exist in new props table ->> %s", gblSiteName, props2.containsKey(gblSiteName))); 
			log.info(String.format("false - Does '%s' exist in new props table ->> %s", gblSiteName, props2.containsKey("gbl.sitenamx"))); 
		}
    	catch (IOException ioe) 
		{
    		StackTraceElement[] ste = ioe.getStackTrace();
    		for(StackTraceElement traceElement: ste)
    		{
    			log.info(String.format("trace--> %s", traceElement.toString()));
    		}
		}
    }

}