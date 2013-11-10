package de.gravitex.processing.testing.dbconn;

import org.apache.log4j.PropertyConfigurator;

import de.gravitex.processing.core.ProcessState;
import de.gravitex.processing.core.dao.ProcessDAO;
import de.gravitex.processing.core.dao.ProcessEntity;
import de.gravitex.processing.core.dao.ProcessTask;

public class TestWriteDB {

	public static void main(String[] args) {
		
		//log4j
		//PropertyConfigurator.configure("C:\\log4j_props\\processing_log4j.properties");
		PropertyConfigurator.configure("/Users/stefan/log4j_props/log4j.properties");
		
//		testWriteProcess();
//		testLoadProcess();
		testWriteTask();
	}

	private static void testWriteTask() {
		ProcessTask task = new ProcessTask();
		task.setName("test_task");
		ProcessDAO.writeProcessTask(ProcessDAO.loadProcessInstance(6l), task);
	}

	private static void testLoadProcess() {
		System.out.println(ProcessDAO.loadProcessInstance(4l));
	}

	private static void testWriteProcess() {
		ProcessEntity process = new ProcessEntity();
		process.setName("test_process");
		process.setState(ProcessState.RUNNING);
		ProcessDAO.writeProcessInstance(process);
	}
}
