package de.gravitex.processing.testing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import de.gravitex.processing.core.ProcessEngine;
import de.gravitex.processing.core.dao.ProcessDAO;
import de.gravitex.processing.core.exception.ProcessException;

public class ProcessGUI extends JFrame {
	
	private static Logger logger = Logger.getLogger(ProcessGUI.class);

	private static final long serialVersionUID = 1L;
	
	private JToolBar tbMain;
	
	private JButton btnStart;
	
	private JTextField tfResumeTask;
	
	private JButton btnProceed;
	
	private ProcessEngine processContainer;
	
	private int processId;

	public ProcessGUI() {
		super();		
		setSize(640, 480);
		setLayout(new BorderLayout());
		//------------------------------------------------
		tbMain = new JToolBar();
		tbMain.setFloatable(false);
		//------------------------------------------------
		btnStart = new JButton("start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				initProcess();
				Connection connection = null;
				try {
					connection = ProcessDAO.getConnection();
					ProcessDAO.clearAll(connection);
					processId = processContainer.startProcess();
					ProcessDAO.returnConnection(connection);
				} catch (ClassNotFoundException | SQLException | ProcessException e) {
					logger.error(e);
				}
			}
		});		
		//------------------------------------------------
		btnProceed = new JButton("proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initProcess();
				try {
					processContainer.finishTask(tfResumeTask.getText(), processId);
				} catch (ProcessException e1) {
					e1.printStackTrace();
				}
			}
		});	
		//------------------------------------------------
		tfResumeTask = new JTextField();
		//------------------------------------------------
		tbMain.add(btnStart);
		tbMain.add(tfResumeTask);
		tbMain.add(btnProceed);
		add(tbMain, BorderLayout.NORTH);
		//------------------------------------------------
		setVisible(true);
	}

	private void initProcess() {
		processContainer = ProcessDefinitionProvider.getBewerbung();
	}
	
	// ---
	// ---
	// ---

	public static void main(String[] args) {
		
		//log4j
		PropertyConfigurator.configure("C:\\log4j_props\\processing_log4j.properties");
		//PropertyConfigurator.configure("/Users/stefan/log4j_props/log4j.properties");
		
		//start process gui
		new ProcessGUI();
	}
}
