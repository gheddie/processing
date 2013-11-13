package de.gravitex.processing.testing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.apache.log4j.PropertyConfigurator;

import de.gravitex.processing.core.ProcessEngine;
import de.gravitex.processing.core.exception.ProcessException;
import de.gravitex.processing.testing.ProcessDefinitionProvider;

public class ProcessGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton btnProceed;
	
	private ProcessEngine processContainer;

	public ProcessGUI() {
		super();
		initProcess();
		setSize(640, 480);
		setLayout(new BorderLayout());
		btnProceed = new JButton("proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				try {
//					processContainer.startProcess();
//				} catch (ProcessException e1) {
//					e1.printStackTrace();
//				}
				//---
				try {
					processContainer.finishTask("t3", 38);
				} catch (ProcessException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(btnProceed, BorderLayout.SOUTH);
		setVisible(true);
	}

	private void initProcess() {

		// OK
		// processContainer = ProcessDefinitionProvider.getT4();

		// OK
		//processContainer = ProcessDefinitionProvider.getT5();

		// OK
		// processContainer = ProcessDefinitionProvider.getT6();
		
		// processContainer = ProcessDefinitionProvider.getT7();
		
		// processContainer = ProcessDefinitionProvider.getHaftungsSumme();
		
		// processContainer = ProcessDefinitionProvider.getBewerbung();
		
		// processContainer = ProcessDefinitionProvider.getReferenceProcess();
		
		// processContainer = ProcessDefinitionProvider.getReferenceProcessFromWork();
		
		// processContainer = ProcessDefinitionProvider.getLinearProcess();
		
		processContainer = ProcessDefinitionProvider.getLinearHomeProcess();
		//---
		/*
		processContainer.clearItemsInControl();
		try {
			processContainer.adaptItemsInControl("ac21", "ac13", "end4");
		} catch (ProcessException e) {
			e.printStackTrace();
		}
		*/
	}

	// ---
	// ---
	// ---

	public static void main(String[] args) {
		
		//log4j
		//PropertyConfigurator.configure("C:\\log4j_props\\processing_log4j.properties");
		PropertyConfigurator.configure("/Users/stefan/log4j_props/log4j.properties");
		
		//start process gui
		new ProcessGUI();
	}
}
