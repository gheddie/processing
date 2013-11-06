package de.gravitex.processing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import de.gravitex.processing.core.ProcessContainer;

public class ProcessGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton btnProceed;
	private ProcessContainer processContainer;

	public ProcessGUI() {
		super();
		initProcess();
		setSize(640, 480);
		setLayout(new BorderLayout());
		btnProceed = new JButton("proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processContainer.proceed();
			}
		});
		add(btnProceed, BorderLayout.SOUTH);
		setVisible(true);
	}

	private void initProcess() {

		// OK
		// processContainer = ProcessDefinitionProvider.getT4();

		// OK
		// processContainer = ProcessDefinitionProvider.getT5();

		// OK
		processContainer = ProcessDefinitionProvider.getT6();
		
		// processContainer = ProcessDefinitionProvider.getT7();
	}

	// ---

	public static void main(String[] args) {
		new ProcessGUI();
	}
}
