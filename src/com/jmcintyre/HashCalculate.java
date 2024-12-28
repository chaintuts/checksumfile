/** This file contains code for a simple checksum/hash calculation
*
* Author: Josh McIntyre
*/

package com.jmcintyre;

import java.io.*; 
import java.io.IOException; 
import java.nio.file.*;
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.security.*;


/* This class defines a class for calculating a cryptographic hash */
public class HashCalculate
{
	/* Read the file to be hashed into a byte array
	* This isn't perfect as  a file could be larger than available memory
	* But it's likely a malicious executable is fairly small
	*/
	private byte[] getFileBytes(String inputFilename)
	{
		try
		{
			Path path = Paths.get(inputFilename);
			byte[] fileBytes = Files.readAllBytes(path);
			
			return fileBytes;
		}
		catch(IOException ex)
		{
			throw new RuntimeException(ex);
		}
	}
	
	/* Calculate the hash digest using the given bytes
	* Returns the hash in hexadecimal format
	*/
	public String calculateHashFromBytes(byte[] fileBytes, String algorithm)
	{

		try {
			
			// Hash the input
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(fileBytes);
			byte[] digestBytes = messageDigest.digest();
			
			// Convert the digest to a hexadecimal string
			StringBuilder digestStringBuilder = new StringBuilder();
			for (byte digestByte : digestBytes) {
				digestStringBuilder.append(String.format("%02x", digestByte));
			}
			String digest = digestStringBuilder.toString();

			return digest;
		}
		catch(NoSuchAlgorithmException ex)
		{
			throw new RuntimeException(ex);
		}
	}
	
	/* This function hashes input and returns a hexadecimal digest */
	public String calculateHash(String inputFilename, String algorithm)
	{
		byte[] fileBytes = getFileBytes(inputFilename);
		String digest = calculateHashFromBytes(fileBytes, algorithm);
		
		return digest;
	}
}