package de.gravitex.processing.testing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.gravitex.processing.core.ProcessEngine;
import de.gravitex.processing.core.ProcessItemType;
import de.gravitex.processing.core.dao.ProcessDAO;
import de.gravitex.processing.core.dao.ProcessItemEntity;
import de.gravitex.processing.core.exception.ProcessException;
import de.gravitex.processing.core.gui.ProcessTable;

public class ProcessGUI extends JFrame implements MouseListener {

	private static Logger logger = Logger.getLogger(ProcessGUI.class);
	
	private static final int CHECK_TIMER_INTERVAL = 5000;

	private static final long serialVersionUID = 1L;

	private JToolBar tbMain;

	private JButton btnStart;

	private JButton btnProceed;
	
	private JButton btnCheckTimers;

	private ProcessEngine processEngine;

	private int processId;

	private ProcessTable tbProcesses;

	protected String taskToResume;
	
	private Thread watchTimerThread = new Thread() {
		public void run() {
			while (true) {
				try {
					logger.info("thread is checking timers...");
					checkTimers();
					Thread.sleep(CHECK_TIMER_INTERVAL);
				} catch (InterruptedException e) {
					logger.error(e);
				}
			}
		}
	};

	public ProcessGUI() {
		super();
		setSize(640, 480);
		setLayout(new BorderLayout());
		// ------------------------------------------------
		tbMain = new JToolBar();
		tbMain.setFloatable(false);
		// ------------------------------------------------
		btnStart = new JButton("start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				initProcess();
				try {
					ProcessDAO.clearAll();
					processId = processEngine.startProcess();
					fillOpenTasks();
				} catch (ProcessException e) {
					logger.error(e);
				}
			}
		});
		// ------------------------------------------------
		btnProceed = new JButton("proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((taskToResume == null) || (taskToResume.length() == 0)) {
					logger.info("no task selected -- returning.");
					return;
				}
				initProcess();
				try {
					processEngine.finishTask(taskToResume, processId);
					taskToResume = null;
					fillOpenTasks();
				} catch (ProcessException pe) {
					logger.warn(pe.getMessage());
				}
			}
		});
		// ------------------------------------------------
		btnCheckTimers = new JButton("check timers");
		btnCheckTimers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				checkTimers();
			}
		});
		// ------------------------------------------------
		tbProcesses = new ProcessTable();
		add(tbProcesses, BorderLayout.CENTER);
		// ------------------------------------------------
		tbMain.add(btnStart);
		tbMain.add(btnProceed);
		tbMain.add(btnCheckTimers);
		add(tbMain, BorderLayout.NORTH);
		// ------------------------------------------------
		tbProcesses.addMouseListener(this);
		// ------------------------------------------------
		watchTimerThread.start();
		// ------------------------------------------------
		setVisible(true);
	}
	
	private void checkTimers() {
		initProcess();
		processEngine.checkTimers(processId);
		fillOpenTasks();
	}

	private void initProcess() {
		processEngine = ProcessDefinitionProvider.getApplianceProcess();
	}

	private void fillOpenTasks() {
		Connection connection = null;
		try {
			connection = ProcessDAO.getConnection();
			List<ProcessItemEntity> openTasks = ProcessDAO.loadOpenTasks(processId, ProcessItemType.TASK, connection );
			tbProcesses.setData(openTasks);
			ProcessDAO.returnConnection(connection);
		} catch (ClassNotFoundException | SQLException e) {
			logger.error(e);
		}
	}

	// ---Listener Methods

	public void mouseReleased(MouseEvent e) {
		int selectedRow = ((JTable) e.getSource()).getSelectedRow();
		taskToResume = (String) tbProcesses.getModel().getValueAt(selectedRow, 0);
		// logger.info("task to resume selected : '"+taskToResume+"'.");
	}

	public void mouseClicked(MouseEvent e) {
		// ...
	}

	public void mousePressed(MouseEvent e) {
		// ...
	}

	public void mouseEntered(MouseEvent e) {
		// ...
	}

	public void mouseExited(MouseEvent e) {
		// ...
	}

	private static void readDefXml() {
		try {
			File fXmlFile = new File("C:\\eclipseWorkspaces\\INDIGO_PROCESSING\\processing\\ProcessEngine\\src\\de\\gravitex\\processing\\testing\\charts\\testtimerdef.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			Element root = doc.getDocumentElement();
			root.normalize();
			readNodesRecursive(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void readNodesRecursive(Node root) {
		logger.info("element :" + root.getNodeName());
		NodeList children = root.getChildNodes();
		for (int childIndex = 0; childIndex < children.getLength(); childIndex++) {
			Node child = children.item(childIndex);
			readNodesRecursive((Node) child);
		}
	}

	// ---
	// ---
	// ---

	public static void main(String[] args) {

		// log4j
		PropertyConfigurator.configure("C:\\log4j_props\\processing_log4j.properties");
		//PropertyConfigurator.configure("/Users/stefan/log4j_props/log4j.properties");

		// start process gui
		new ProcessGUI();

		// readDefXml();
	}
}
