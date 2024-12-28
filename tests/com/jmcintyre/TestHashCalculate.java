/** This file contains code for simple checksum/hash calculation unit tests
*
* Author: Josh McIntyre
*/
import com.jmcintyre.HashCalculate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/* This class defines a class for unit testing the HashCalculate class */
public class TestHashCalculate
{

	/* Test calculating the hash from a given byte array
	* This array will normally be read from file, but hard code one here for the unit test
	*/
	@Test
	public static void testCalculateHashFromBytes()
	{
		HashCalculate hashCalculate = new HashCalculate();

		byte[] testFileContents = { 0x00 };
		
		String hash = hashCalculate.calculateHashFromBytes(testFileContents, "SHA-256");
		
		assertEquals(hash, "6e340b9cffb37a989ca544e6bb780a2c78901d3fb33738768511a30617afa01d");
	}
}