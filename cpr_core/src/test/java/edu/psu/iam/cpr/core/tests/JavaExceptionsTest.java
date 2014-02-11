/**
 * 
 */
package edu.psu.iam.cpr.core.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

import edu.psu.iam.cpr.core.database.beans.JavaExceptions;



/**
 * @author llg5
 *
 */
public class JavaExceptionsTest {
	JavaExceptions anExcept = new JavaExceptions();
	Date d = new Date();
	/**
	 * Test method for {@link edu.psu.iam.cpr.database.beans.JavaExceptions#JavaExceptions()}.
	 */
	@Test
	public final void testJavaExceptions() {
			JavaExceptions anException = new JavaExceptions();
			assertNotNull(anException);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.database.beans.JavaExceptions#getJavaExceptionKey()}.
	 */
	@Test
	public final void testGetJavaExceptionKey() {
		anExcept.setJavaExceptionKey(new Long(1));
		assertEquals(anExcept.getJavaExceptionKey(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.database.beans.JavaExceptions#setJavaExceptionKey(java.lang.Long)}.
	 */
	@Test
	public final void testSetJavaExceptionKey() {
		anExcept.setJavaExceptionKey(new Long(1));
		assertEquals(anExcept.getJavaExceptionKey(), new Long(1));
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.database.beans.JavaExceptions#getLastUpdateOn()}.
	 */
	@Test
	public final void testGetLastUpdateOn() {
		anExcept.setLastUpdateOn(d);
		assertEquals(anExcept.getLastUpdateOn(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.database.beans.JavaExceptions#setLastUpdateOn(java.util.Date)}.
	 */
	@Test
	public final void testSetLastUpdateOn() {
		anExcept.setLastUpdateOn(d);
		assertEquals(anExcept.getLastUpdateOn(), d);
		
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.database.beans.JavaExceptions#getCreatedOn()}.
	 */
	@Test
	public final void testGetCreatedOn() {
		anExcept.setCreatedOn(d);
		assertEquals(anExcept.getCreatedOn(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.database.beans.JavaExceptions#setCreatedOn(java.util.Date)}.
	 */
	@Test
	public final void testSetCreatedOn() {
		anExcept.setCreatedOn(d);
		assertEquals(anExcept.getCreatedOn(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.database.beans.JavaExceptions#getJavaExceptionEnum()}.
	 */
	@Test
	public final void testGetJavaExceptionEnum() {
		anExcept.setJavaExceptionEnum("ANEW_EXCEPTION");
		assertEquals(anExcept.getJavaExceptionEnum(),"ANEW_EXCEPTION");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.database.beans.JavaExceptions#setJavaExceptionEnum(java.lang.String)}.
	 */
	@Test
	public final void testSetJavaExceptionEnum() {
		anExcept.setJavaExceptionEnum("ANEW_EXCEPTION");
		assertEquals(anExcept.getJavaExceptionEnum(),"ANEW_EXCEPTION");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.database.beans.JavaExceptions#getJavaExceptionText()}.
	 */
	@Test
	public final void testGetJavaExceptionText() {
		anExcept.setJavaExceptionText("The new exception is \"%s\"");
		assertEquals(anExcept.getJavaExceptionText(),"The new exception is \"%s\"");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.database.beans.JavaExceptions#setJavaExceptionText(java.lang.String)}.
	 */
	@Test
	public final void testSetJavaExceptionText() {
		anExcept.setJavaExceptionText("The new exception is \"%s\"");
		assertEquals(anExcept.getJavaExceptionText(),"The new exception is \"%s\"");
	}

}
