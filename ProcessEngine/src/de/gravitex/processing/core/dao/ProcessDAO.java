package de.gravitex.processing.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.postgresql.Driver;

import de.gravitex.processing.core.ProcessState;

public class ProcessDAO {

	private static Logger logger = Logger.getLogger(ProcessDAO.class);
	
	private static final String DB_PASSWD = "pgvedder";

	private static final String DB_USER = "postgres";

	private static final String DB_PATH = "jdbc:postgresql://localhost/processing";
	
	private static final int PROCESS_COLUMN_INDEX_ID = 1;
	private static final int PROCESS_COLUMN_INDEX_NAME = 2;
	private static final int PROCESS_COLUMN_INDEX_STATE = 3;
	
	private static final int TASK_COLUMN_INDEX_ID = 1;
	private static final int TASK_COLUMN_INDEX_NAME = 2;
	private static final int TASK_COLUMN_INDEX_PROCESS_REF = 3;

	public static void writeProcessInstance(ProcessEntity process) {
		Connection cn = null;
		Statement st = null;
		try {
			cn = getConnection();
			st = cn.createStatement();
			String sql = "insert into process_instance (name, state) values ('" + process.getName() + "', '" + process.getState() + "')";
			st.executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e);
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
	
	public static void writeProcessTask(ProcessEntity processInstance, ProcessTask task) {
		Connection cn = null;
		Statement st = null;
		try {
			cn = getConnection();
			st = cn.createStatement();
			String sql = "insert into process_task (name, processId) values ('" + task.getName() + "', '" + processInstance.getId() + "')";
			st.executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	// ---

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(Driver.class.getCanonicalName());
		return DriverManager.getConnection(DB_PATH, DB_USER, DB_PASSWD);
	}
}
