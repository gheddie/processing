/*
 * Created by JFormDesigner on Fri Nov 08 15:34:49 CET 2013
 */

package de.gravitex.processing.testing.appliance;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import de.gravitex.processing.core.ProcessContainer;
import de.gravitex.processing.testing.ProcessDefinitionProvider;

/**
 * @author User #1
 */
public class TestApplianceGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private ProcessContainer processContainer;
	
	private ButtonGroup bgInterest;
	
	public TestApplianceGUI() {
		initComponents();
		initAdditionalComponents();
		initListeners();
		initProcess();
		setSize(800, 600);
		setVisible(true);		
	}	

	private void initProcess() {
		processContainer = ProcessDefinitionProvider.getBewerbung();
	}
	
	private void initListeners() {
		
		opInteresting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("interesting");
			}
		});
	}
	
	private void initAdditionalComponents() {
		bgInterest = new ButtonGroup();
		bgInterest.add(opInteresting);
		bgInterest.add(opPotentiallyInteresting);
		bgInterest.add(opNotInteresting);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		pnlInterest = new JPanel();
		opInteresting = new JRadioButton();
		opPotentiallyInteresting = new JRadioButton();
		opNotInteresting = new JRadioButton();
		pnlAvailability = new JPanel();
		chkAvailableForAppointment = new JCheckBox();
		pnlStoreData = new JPanel();
		chkStoreDataAllowed = new JCheckBox();
		btnStep = new JButton();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
		((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0, 0.0, 1.0E-4};

		//======== pnlInterest ========
		{
			pnlInterest.setBorder(new TitledBorder("G\u00fcte"));
			pnlInterest.setLayout(new GridBagLayout());
			((GridBagLayout)pnlInterest.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
			((GridBagLayout)pnlInterest.getLayout()).rowHeights = new int[] {0, 0};
			((GridBagLayout)pnlInterest.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0, 1.0E-4};
			((GridBagLayout)pnlInterest.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

			//---- opInteresting ----
			opInteresting.setText("interessant");
			pnlInterest.add(opInteresting, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- opPotentiallyInteresting ----
			opPotentiallyInteresting.setText("potentiell interessant");
			pnlInterest.add(opPotentiallyInteresting, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));

			//---- opNotInteresting ----
			opNotInteresting.setText("uninteressant");
			pnlInterest.add(opNotInteresting, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(pnlInterest, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 0), 0, 0));

		//======== pnlAvailability ========
		{
			pnlAvailability.setBorder(new TitledBorder("Erreichbarkeit"));
			pnlAvailability.setLayout(new GridBagLayout());
			((GridBagLayout)pnlAvailability.getLayout()).columnWidths = new int[] {0, 0};
			((GridBagLayout)pnlAvailability.getLayout()).rowHeights = new int[] {0, 0};
			((GridBagLayout)pnlAvailability.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
			((GridBagLayout)pnlAvailability.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

			//---- chkAvailableForAppointment ----
			chkAvailableForAppointment.setText("Bewerber telefonisch erreichbar");
			pnlAvailability.add(chkAvailableForAppointment, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(pnlAvailability, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 0), 0, 0));

		//======== pnlStoreData ========
		{
			pnlStoreData.setBorder(new TitledBorder("Datenspeicherung"));
			pnlStoreData.setLayout(new GridBagLayout());
			((GridBagLayout)pnlStoreData.getLayout()).columnWidths = new int[] {0, 0};
			((GridBagLayout)pnlStoreData.getLayout()).rowHeights = new int[] {0, 0};
			((GridBagLayout)pnlStoreData.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
			((GridBagLayout)pnlStoreData.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

			//---- chkStoreDataAllowed ----
			chkStoreDataAllowed.setText("Datenspeicherung im Sinne des Bewerbers");
			pnlStoreData.add(chkStoreDataAllowed, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(pnlStoreData, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 0), 0, 0));

		//---- btnStep ----
		btnStep.setText("N\u00e4chster Schritt");
		contentPane.add(btnStep, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 0, 0), 0, 0));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel pnlInterest;
	private JRadioButton opInteresting;
	private JRadioButton opPotentiallyInteresting;
	private JRadioButton opNotInteresting;
	private JPanel pnlAvailability;
	private JCheckBox chkAvailableForAppointment;
	private JPanel pnlStoreData;
	private JCheckBox chkStoreDataAllowed;
	private JButton btnStep;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
