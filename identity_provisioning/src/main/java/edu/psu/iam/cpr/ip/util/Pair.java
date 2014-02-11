/* SVN FILE: $Id: Pair.java 5705 2012-11-27 14:13:31Z jal55 $ */
package edu.psu.iam.cpr.ip.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Pair represents a key-value based object. 
 * methods for accessing server resources.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 5705 $
 * @lastrevision $Date: 2012-11-27 09:13:31 -0500 (Tue, 27 Nov 2012) $
 */
public class Pair<K, V> implements Serializable{
	
	private K key;
	private V value;
	
	public Pair() {
		
	}

	/**
	* @param key key to be set
	* @param value value to be set
	*/
	public Pair(final K key, final V value) {

		this.key = key;
		this.value = value;
	}

	/**
	* @param key key to be set
	* @param value value to be set
	* @return this
	*/
	public Pair<K, V> setPair(final K key, final V value) {
		this.key = key;
		this.value = value;
		return this;
	}

	/**
	* @return key
	*/
	public K getKey() {
		return key;
	}

	/**
	* @param key key to be set
	* @return this
	*/
	public Pair<K, V> setKey(final K key) {
		this.key = key;
		return this;
	}

	/**
	* @return value
	*/
	public V getValue() {
		return value;
	}

	/**
	* @param value value to be set
	* @return this
	*/
	public Pair<K, V> setValue(final V value) {
		this.value = value;
		return this;
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException 
	{
		key   = (K)in.readObject();
		value = (V)in.readObject();
	}
	private void writeObject(ObjectOutputStream out) throws IOException 
	{
		out.writeObject(key);
		out.writeObject(value);
		
	}
	
}
