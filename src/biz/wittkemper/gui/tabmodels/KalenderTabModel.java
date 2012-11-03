package biz.wittkemper.gui.tabmodels;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import biz.wittkemper.database.entity.Kalender;

public class KalenderTabModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int IX_DATUM = 0;
	private final int IX_TYP= 1;
	private final int IX_TEXT= 2;
	
	private String[] columnNames;
	private List<Kalender> data = new ArrayList<Kalender>();

	
	public KalenderTabModel(){
		setColumnNames();
	}
	private void setColumnNames() {
		columnNames = new String[] { "Datum","Art", "Text" };
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		if (data == null)
			return 0;
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		try {
			Kalender r = data.get(row);

			switch (col) {
			case IX_DATUM:
				return  getDatum(r.getDatum());
			case IX_TYP:
				return r.getKalenderTyp().getBezeichnung();
			case IX_TEXT:
				return r.getText();
			}
			return null;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private String getDatum(Date date) {
		
		String lreturn="";
		String [] datum;
		lreturn +="<HTML>";
		DateFormat formater;
		formater = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
		datum = formater.format(date).split(" ");
		lreturn += datum[0] + "<br>" + datum[1] + " Uhr";
		lreturn += "</HTML>";
		return lreturn;
	}
	public void refresh() {
		this.fireTableRowsUpdated(0, this.getRowCount() - 1);
	}
	
	public List<Kalender> getData() {
		return data;
	}

	public void setData(List<Kalender> data) {
		this.data = data;
	}

}
