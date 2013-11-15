package de.gravitex.processing.core.gui;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import de.gravitex.processing.core.dao.ProcessItemEntity;

public class ProcessTable extends JTable {

	private static final long serialVersionUID = 1L;

	public void setData(List<ProcessItemEntity> tasks) {
		Object[] headers = new Object[] {"name", "expiring"};
		Object[][] data = new Object[tasks.size()][2];
		int rowIndex = 0;
		for (ProcessItemEntity task : tasks) {			
			data[rowIndex][0] = task.getName();
			if (task.getExpiryDate() != null) {
				data[rowIndex][1] = task.getExpiryDate();	
			} else {
				data[rowIndex][1] = "NONE";
			}
			rowIndex++;
		}
		TableModel model = new DefaultTableModel(data, headers);
		setModel(model);
	}
}
