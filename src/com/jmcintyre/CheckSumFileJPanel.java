/* This file contains GUI code for the hash calculator
*
* Author: Josh McIntyre
*/
package com.jmcintyre;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/* This class defines the GUI components for a hash calculator */
public class CheckSumFileJPanel extends JPanel
{
	/* Define constants for hash calculation */
	final String DEFAULT_ALGORITHM = "SHA-256";

	/* This block defines constants for GUI generation */
	final String CHOOSE_CAPTION = "Choose file";
	final String SCAN_CAPTION = "Scan";
	final String RESULTS_CAPTION = "Scan results: ";
	final String HASH_CAPTION = "Hash: ";
	final String NO_FILE_CAPTION = "Please choose a file to scan";
	final String NO_THREAT_CAPTION = "No threat found in database";
	final String THREAT_CAPTION = "Threat found matching: ";
	
	/* This block contains variables for scanning */
	String inputFilename;

	/* This block defines GUI components */
	GridLayout gridLayout;

	JButton chooseFileButton;
	JFileChooser chooseFileFileChooser;
	
	JButton scanFileButton;

	JTextField hashResultJTextField;
	JLabel scanResultJLabel;
	JTextField scanResultJTextField;

	HashCalculate hashCalculate;
	ThreatDatabase threatDatabase;

	/* This constructor configures GUI components */
	public CheckSumFileJPanel()
	{
		initializeComponents();
		drawComponents();
	}

	/* This function initializes GUI components */
	public void initializeComponents()
	{
		gridLayout = new GridLayout(0,1);
		setLayout(gridLayout);

		chooseFileButton = new JButton(CHOOSE_CAPTION);
		chooseFileButton.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			handleChooseFileClicked();
		  } 
		});
		
		chooseFileFileChooser = new JFileChooser();
		
		scanFileButton = new JButton(SCAN_CAPTION);
		scanFileButton.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			handleScanClicked();
		  } 
		});
		
		scanResultJLabel = new JLabel(RESULTS_CAPTION);
		scanResultJTextField = new JTextField();
		scanResultJTextField.setEnabled(false);
		hashResultJTextField = new JTextField();
		hashResultJTextField.setEnabled(false);
		
		hashCalculate = new HashCalculate();
		threatDatabase = new ThreatDatabase();

	}
	
	/* This function adds components to the GUI and draws */
	public void drawComponents()
	{
		removeAll();

		add(chooseFileButton);
		add(scanFileButton);

		add(scanResultJLabel);
		add(hashResultJTextField);
		add(scanResultJTextField);

		validate();
	}

	/* This function handles the calculation of the hash based on user input */
	public void handleScanClicked()
	{
		if (inputFilename == null)
		{
			scanResultJTextField.setText(NO_FILE_CAPTION);
		}
		else
		{
			String digest = hashCalculate.calculateHash(inputFilename, DEFAULT_ALGORITHM);
			String scanResult = threatDatabase.search(digest);
			
			String hashInfo = HASH_CAPTION + digest;
			
			String threatInfo;
			if (scanResult == threatDatabase.NO_THREAT)
			{
				threatInfo = NO_THREAT_CAPTION;
			}
			else
			{
				threatInfo = THREAT_CAPTION + scanResult;
			}
			
			hashResultJTextField.setText(hashInfo);
			scanResultJTextField.setText(threatInfo);
		}
	}
	
	/* This function handles the calculation of the hash based on user input */
	public void handleChooseFileClicked()
	{

		int checkInput = chooseFileFileChooser.showOpenDialog(null);

		if (checkInput == JFileChooser.APPROVE_OPTION)
		{
			inputFilename = chooseFileFileChooser.getSelectedFile().getAbsolutePath();
		}
	}
}
