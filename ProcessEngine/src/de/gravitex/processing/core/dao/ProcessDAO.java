package de.gravitex.processing.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.postgresql.Driver;

import de.gravitex.processing.core.ProcessState;
import de.gravitex.processing.core.TaskState;

public class ProcessDAO {

	private static Logger logger = Logger.getLogger(ProcessDAO.class);

	private static final String DB_PASSWD = "pgvedder";

	private static final String DB_USER = "postgres";

	private static final String DB_PATH = "jdbc:postgresql://localhost/processing";

	private static final int PROCESS_COLUMN_INDEX_ID = 1;
	private static final int PROCESS_COLUMN_INDEX_NAME = 2;
	private static final int PROCESS_COLUMN_INDEX_STATE = 3;
	private static final int PROCESS_COLUMN_INDEX_CREATION_DATE = 4;

	private static final int TASK_COLUMN_INDEX_ID = 1;
	private static final int TASK_COLUMN_INDEX_NAME = 2;
	private static final int TASK_COLUMN_INDEX_PROCESS_REF = 3;
	private static final int TASK_COLUMN_INDEX_STATE = 4;
	
	public static int writeProcessInstance(String name, ProcessState processState, Date creationDate) {
		ProcessEntity process = new ProcessEntity();
		process.setName(name);
		process.setState(processState);
		process.setCreationDate(creationDate);
		return writeProcessInstance(process);
	}

	public static int writeProcessInstance(ProcessEntity process) {
		Connection cn = null;
		Statement st = null;
		int processId = -1;
		try {
			cn = getConnection();
			st = cn.createStatement();
			processId = getSequenceVal();
//			String sql = "insert into process_instance (id, name, state, creationDate) values ("+processId+", '" + process.getName() + "', '" + process.getState() + "', '" + DAOUtils.formatDateForDB(process.getCreationDate()) + "')";
			String sql = "insert into process_instance (id, name, state, creationDate) values ("+processId+", '" + process.getName() + "', '" + process.getState() + "', null)";
			st.executeUpdate(sql);
			return processId;
		} catch (Exception e) {
			logger.error(e);
			return -1;
		}
	}

	public static ProcessEntity loadProcessInstance(long processId) {
		Connection cn = null;
		Statement st = null;
		try {
			cn = getConnection();
			st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from process_instance where id = " + processId);
			rs.next();
			ProcessEntity process = new ProcessEntity();
			process.setId(processId);
			process.setName(rs.getString(PROCESS_COLUMN_INDEX_NAME));
			process.setState(ProcessState.valueOf(rs.getString(PROCESS_COLUMN_INDEX_STATE)));
			return process;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public static void writeProcessTask(int processId, ProcessTask task) {
		Connection cn = null;
		Statement st = null;
		try {
			cn = getConnection();
			st = cn.createStatement();
			String sql = "insert into process_task (name, processId, state) values ('" + task.getName() + "', '" + processId + "', '"+task.getState()+"')";
			st.executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static List<ProcessTask> loadTasksByProcess(ProcessEntity processInstance) {
		Connection cn = null;
		Statement st = null;
		try {
			cn = getConnection();
			st = cn.createStatement();
			String sql = "select * from process_task where processId = " + processInstance.getId();
			ResultSet rs = st.executeQuery(sql);
			List<ProcessTask> tasks = new ArrayList<>();
			while (rs.next()) {
				tasks.add(loadTask(rs.getLong(TASK_COLUMN_INDEX_ID)));
			}
			return tasks;
		} catch (ClassNotFoundException | SQLException e) {
			logger.error(e);
			return null;
		}
	}

	public static ProcessTask loadTask(long taskId) {
		Connection cn = null;
		Statement st = null;
		try {
			cn = getConnection();
			st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from process_task where id = " + taskId);
			rs.next();
			ProcessTask task = new ProcessTask();
			task.setId(taskId);
			task.setName(rs.getString(TASK_COLUMN_INDEX_NAME));
			task.setProcessId(rs.getInt(TASK_COLUMN_INDEX_PROCESS_REF));
			task.setState(TaskState.valueOf(rs.getString(TASK_COLUMN_INDEX_STATE)));
			return task;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public static int getSequenceVal() {
		Connection cn = null;
		Statement st = null;
		try {
			cn = getConnection();
			st = cn.createStatement();
			String sql = "SELECT nextval('processing_id_seq')";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt("nextval");
			} else {
				return -1;
			}
		} catch (Exception e) {
			logger.error(e);
			return -1;
		}
	}
	
	public static void setTaskResolved(int processId, String taskName) {
		logger.info("setting task '"+taskName+"' to resolved...");
		Connection cn = null;
		Statement st = null;
		try {
			cn = getConnection();
			st = cn.createStatement();
			String sql = "update process_task set state = '"+TaskState.FINISHED+"' where processId = "+processId+" and name = '"+taskName+"'";
			st.executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e);
		}		
	}
	
	public static void clearAll() {
		Connection cn = null;
		Statement st = null;
		try {
			cn = getConnection();
			st = cn.createStatement();
			String sqlDelTasks = "delete from process_task";
			st.executeUpdate(sqlDelTasks);
			String sqlDelProcesses = "delete from process_instance";
			st.executeUpdate(sqlDelProcesses);			
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	public static List<ProcessTask> loadOpenTasks(int processId) {
		Connection cn = null;
		Statement st = null;
		try {
			cn = getConnection();
			st = cn.createStatement();
			String sqlFetchTaskIdsByProject = "select id from process_task where state = '"+TaskState.OPEN+"' and processid = " + processId;
			ResultSet rs = st.executeQuery(sqlFetchTaskIdsByProject);
			List<ProcessTask> taskList = new ArrayList<>();
			while (rs.next()) {
				taskList.add(loadTask(rs.getInt(1)));
			}			
			return taskList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}		
	}

	// ---

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(Driver.class.getCanonicalName());
		return DriverManager.getConnection(DB_PATH, DB_USER, DB_PASSWD);
	}
}
