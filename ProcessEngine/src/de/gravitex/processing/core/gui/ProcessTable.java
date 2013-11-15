package de.gravitex.processing.core.gui;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import de.gravitex.processing.core.dao.ProcessTask;

public class ProcessTable extends JTable {

	private static final long serialVersionUID = 1L;

	public void setData(List<ProcessTask> tasks) {
		Object[] headers = new Object[] {"name"};
		Object[][] data = new Object[tasks.size()][1];
		int rowIndex = 0;
		for (ProcessTask task : tasks) {			
			data[rowIndex][0] = task.getName();
			rowIndex++;
		}
		TableModel model = new DefaultTableModel(data, headers);
		setModel(model);
	}
}
