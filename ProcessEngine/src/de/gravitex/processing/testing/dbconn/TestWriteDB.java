package de.gravitex.processing.testing.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.postgresql.Driver;

import de.gravitex.processing.core.ProcessState;

public class TestWriteDB {

	public static void main(String[] args) {
		writeProcessInstance("test123", ProcessState.RUNNING);
	}

	static void writeProcessInstance(String processName, ProcessState processState) {
		Connection cn = null;
		Statement st = null;
		try {
			// Select fitting database driver and connect:
			Class.forName(Driver.class.getCanonicalName());
			cn = DriverManager.getConnection("jdbc:postgresql://localhost/processing", "postgres", "pgvedder");
			st = cn.createStatement();
			st.executeUpdate("insert into process_instance (name, state) values ('"+processName+"', '"+processState+"')");
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (Exception ex) {/* nothing to do */
			}
			try {
				if (cn != null)
					cn.close();
			} catch (Exception ex) {/* nothing to do */
			}
		}
	}
}
