package de.gravitex.processing.testing.dbconn;

import java.util.Date;

import org.apache.log4j.PropertyConfigurator;

import de.gravitex.processing.core.ProcessState;
import de.gravitex.processing.core.dao.ProcessEntity;
import de.gravitex.processing.core.dao.ProcessTask;

public class TestDBFuncs {

	public static void main(String[] args) {
		
		//log4j
		PropertyConfigurator.configure("C:\\log4j_props\\processing_log4j.properties");
		//PropertyConfigurator.configure("/Users/stefan/log4j_props/log4j.properties");
		
//		testWriteProcess();
//		testLoadProcess();
//		testWriteTask();
//		testLoadTasks();
//		testLoadTask();
		testGetNextSequenceValue();
	}

	private static void testGetNextSequenceValue() {
//		System.out.println(ProcessDAO.getSequenceVal());
	}

	private static void testLoadTask() {
//		System.out.println(ProcessDAO.loadTask(8l));
	}

	private static void testLoadTasks() {
//		System.out.println(ProcessDAO.loadTasksByProcess(ProcessDAO.loadProcessInstance(6l)).size());
	}

	private static void testWriteTask() {
		ProcessTask task = new ProcessTask();
		task.setName("test_task2");
//		ProcessDAO.writeProcessTask(6, task);
	}

	private static void testLoadProcess() {
//		System.out.println(ProcessDAO.loadProcessInstance(4l));
	}

	private static void testWriteProcess() {
		ProcessEntity process = new ProcessEntity();
		process.setName("test_process");
		process.setState(ProcessState.RUNNING);
		process.setCreationDate(new Date());
//		ProcessDAO.writeProcessInstance(process);
	}
}
