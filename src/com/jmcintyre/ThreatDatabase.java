/** This file contains code for a simple checksum/hash based threat database
*
* Author: Josh McIntyre
*/

package com.jmcintyre;

import java.io.*; 
import java.io.IOException;
import java.util.HashMap;


/* This class defines a class for calculating a cryptographic hash */
public class ThreatDatabase
{
	/* Define class constants for database parsing */
	final String DEFAULT_DATABASE_FILE = "threatdatabase.csv";
	final String NO_THREAT = "No threat found in database";
	
	/* Store the parsed dictionary/hash table of threats */
	HashMap<String, String> threatDatabase;

	/* Read the threat CSV and put the information in a HashMap dictionary */
	private void parseDatabase(String databaseFilename)
	{
		threatDatabase = new HashMap<String, String>();

		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(databaseFilename));
			
			String line = reader.readLine();
			String[] keyValue;
			while(line != null)
			{
				keyValue = line.split(",");
				threatDatabase.put(keyValue[0], keyValue[1]);
				
				line = reader.readLine();
			}
			reader.close();
		}
		catch(IOException ex)
		{
			throw new RuntimeException(ex);
		}
	}
	
	/* Search the database with a given threat hash */
	public String search(String threatHash)
	{
		if (threatDatabase.containsKey(threatHash))
		{
			return threatDatabase.get(threatHash);
		}
		else
		{
			return NO_THREAT;
		}
	}
	
	/* This constructor initializes the threat database */
	public ThreatDatabase()
	{
		parseDatabase(DEFAULT_DATABASE_FILE);
	}
}