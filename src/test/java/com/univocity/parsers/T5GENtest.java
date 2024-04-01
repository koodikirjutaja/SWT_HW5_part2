package com.univocity.parsers;

import com.univocity.parsers.conversions.RegexConversion;
import org.junit.Test;
import static org.junit.Assert.*;

public class T5GENtest {

	@Test(timeout = 4000)
	public void testRevert() throws Throwable {
	    RegexConversion regexConversion0 = new RegexConversion("%1?=PWmXs=@", "RZS9/|YWC*(3Ne>=}g");
	    String string0 = regexConversion0.revert("%1?=PWmXs=@");
	    assertEquals("%1?=PWmXs=@", string0);
	}

	@Test(timeout = 4000)
	public void testExecute() throws Throwable {
	    RegexConversion regexConversion0 = new RegexConversion(".\"&m", ".\"&m");
	    String string0 = regexConversion0.execute((String) null);
	    assertNull(string0);
	}

}