package biz.wittkemper.gui.tabmodels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import biz.wittkemper.database.entity.Kennung;

public class RicTabModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int IX_RIC = 0;
	private final int IX_BEZEICHNUNG = 1;
	private final int IX_UNTERDRUECKEN = 2;
	
	private String[] columnNames;
	private List<Kennung> data = new ArrayList<Kennung>();

	public RicTabModel() {
		setColumnNames();
	}

	private void setColumnNames() {
		columnNames = new String[] { "RIC", "Bezeichung", "Meldetexte unterdrücken" };
	}

	public void setData(List<Kennung> data) {
		this.data = data;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		if (data == null)
			return 0;
		return data.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	@SuppressWarnings("unchecked")
	public Class getColumnClass(int c) {
		if (c == IX_RIC) {
			return String.class;
		}
		return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public void refresh() {
		this.fireTableRowsUpdated(0, this.getRowCount() - 1);
	}

	public Object getValueAt(int row, int col) {
		try {
			Kennung r = data.get(row);

			switch (col) {
			case IX_RIC:
				return r.getRic();

			case IX_BEZEICHNUNG:
				return r.getBezeichnung();
			
			case IX_UNTERDRUECKEN:
				return r.getAusblenden();
			}
			return null;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
