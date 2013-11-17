package de.gravitex.processing.core.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.ProcessItemType;
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
	private static final int TASK_COLUMN_INDEX_EXPIRY_DATE = 5;
	private static final int TASK_COLUMN_INDEX_ITEMTYPE = 6;
	
	public static int writeProcessInstance(String name, ProcessState processState, Date creationDate, Connection connection) {
		ProcessEntity process = new ProcessEntity();
		process.setName(name);
		process.setState(processState);
		process.setCreationDate(creationDate);
		return writeProcessInstance(process, connection);
	}

	public static int writeProcessInstance(ProcessEntity process, Connection connection) {
		Statement st = null;
		int processId = -1;
		try {
			st = connection.createStatement();
			processId = getSequenceVal(connection);
//			String sql = "insert into process_instance (id, name, state, creationDate) values ("+processId+", '" + process.getName() + "', '" + process.getState() + "', '" + DAOUtils.formatDateForDB(process.getCreationDate()) + "')";
			String sql = "insert into process_instance (id, name, state, creationDate) values ("+processId+", '" + process.getName() + "', '" + process.getState() + "', null)";
			st.executeUpdate(sql);
			return processId;
		} catch (Exception e) {
			logger.error(e);
			return -1;
		}
	}

	public static ProcessEntity loadProcessInstance(long processId, Connection connection) {
		Statement st = null;
		try {
			st = connection.createStatement();
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

	public static void writeProcessItem(int processId, ProcessItemEntity task, Connection connection) {
		Statement st = null;
		try {
			st = connection.createStatement();
			String sql = null;
			if (task.getExpiryDate() != null) {
				sql = "insert into process_item (name, processId, state, expiryDate, itemType) values ('" + task.getName() + "', '" + processId + "', '"+task.getState()+"', '"+DAOUtils.formatDateForDB(task.getExpiryDate())+"', '"+task.getItemType()+"')";	
			} else {
				sql = "insert into process_item (name, processId, state, expiryDate, itemType) values ('" + task.getName() + "', '" + processId + "', '"+task.getState()+"', null, '"+task.getItemType()+"')";
			}
			st.executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static List<ProcessItemEntity> loadTasksByProcess(ProcessEntity processInstance, Connection connection) {
		Statement st = null;
		try {
			st = connection.createStatement();
			String sql = "select * from process_item where processId = " + processInstance.getId();
			ResultSet rs = st.executeQuery(sql);
			List<ProcessItemEntity> tasks = new ArrayList<>();
			while (rs.next()) {
				tasks.add(loadTask(rs.getLong(TASK_COLUMN_INDEX_ID), connection));
			}
			return tasks;
		} catch (SQLException e) {
			logger.error(e);
			return null;
		}
	}

	public static ProcessItemEntity loadTask(long taskId, Connection connection) {
		Statement st = null;
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from process_item where id = " + taskId);
			rs.next();
			ProcessItemEntity task = new ProcessItemEntity();
			task.setId(taskId);
			task.setName(rs.getString(TASK_COLUMN_INDEX_NAME));
			task.setProcessId(rs.getInt(TASK_COLUMN_INDEX_PROCESS_REF));
			task.setState(TaskState.valueOf(rs.getString(TASK_COLUMN_INDEX_STATE)));
			task.setExpiryDate(DAOUtils.parseDBDate(rs.getString(TASK_COLUMN_INDEX_EXPIRY_DATE)));
			task.setItemType(ProcessItemType.valueOf(rs.getString(TASK_COLUMN_INDEX_ITEMTYPE)));
			return task;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public static int getSequenceVal(Connection connection) {
		Statement st = null;
		try {
			st = connection.createStatement();
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
	
	public static void setBlockingItemResolved(int processId, String taskName, Connection connection) {
//		logger.info("setting task '"+taskName+"' to resolved...");
		Statement st = null;
		try {
			st = connection.createStatement();
			String sql = "update process_item set state = '"+TaskState.FINISHED+"' where processId = "+processId+" and name = '"+taskName+"'";
			st.executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e);
		}		
	}
	
	public static void clearAll() {
		Connection connection = null;
		Statement st = null;
		try {
			connection = ProcessDAO.getConnection();
			st = connection.createStatement();
			String sqlDelTasks = "delete from process_item";
			st.executeUpdate(sqlDelTasks);
			String sqlDelProcesses = "delete from process_instance";
			st.executeUpdate(sqlDelProcesses);		
			ProcessDAO.returnConnection(connection);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	public static List<ProcessItemEntity> loadOpenTasks(int processId, ProcessItemType itemType, Connection connection) {
		Statement st = null;
		try {
			st = connection.createStatement();
			String sqlFetchTaskIdsByProject = "select id from process_item where state = '"+TaskState.OPEN+"' and itemtype = '"+itemType+"' and processid = " + processId + " order by process_item.name asc";
			ResultSet rs = st.executeQuery(sqlFetchTaskIdsByProject);
			List<ProcessItemEntity> taskList = new ArrayList<>();
			while (rs.next()) {
				taskList.add(loadTask(rs.getInt(1), connection));
			}			
			return taskList;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}		
	}

	// ---

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(Driver.class.getCanonicalName());
		return DriverManager.getConnection(DB_PATH, DB_USER, DB_PASSWD);
	}
	
	public static void returnConnection(Connection connection) throws SQLException {
		if (connection != null) {
			connection.close();	
		}		
	}
}
