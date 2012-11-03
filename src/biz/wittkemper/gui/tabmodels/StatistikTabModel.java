package biz.wittkemper.gui.tabmodels;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import biz.wittkemper.database.entity.KennungStatisik;

public class StatistikTabModel extends AbstractTableModel {

	private final int IX_KENNUNG = 0;
	private final int IX_ANZAHL = 1;
	private final int IX_LASTMESSAGE = 2;
	private String[] columnNames;

	private List<KennungStatisik> data = new ArrayList<KennungStatisik>();
	/**
	 * 
	 */
	private static final long serialVersionUID = -724713106212526322L;

	public StatistikTabModel(){
		setColumnNames();
	}
	private void setColumnNames() {
		columnNames = new String[] { "Kennung", "Anzahl", "letzte Meldung" };
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public int getColumnCount() {
		if (columnNames != null){
			return columnNames.length;
		}else{
			return 0;
		}
	}

	@Override
	public int getRowCount() {
		if (data.size() > 0) {
			return data.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		if (data.size() <= 0 ) {
			return "";
		}
		
		KennungStatisik st = data.get(row);

		switch (col) {
		case (IX_KENNUNG):
			return st.getKennung().getBezeichnung();
		case (IX_ANZAHL):
			return getHTML( st.getAnzahlMeldungen());
		case (IX_LASTMESSAGE):
			return getDateTime(st.getLetzteMeldung());
		}
		return null;
	}

	private String getDateTime(Date date){
		if (date == null){
			return "";
		}
		DateFormat formater;
		formater = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT );
		return formater.format(date);
	}
	private String getHTML(int anzahlMeldungen) {
		String lreturn ="";
		
		lreturn += "<html><center>" + anzahlMeldungen + "</center></html>" ;
		return lreturn;
	}
	public List<KennungStatisik> getData() {
		return data;
	}

	public void setData(List<KennungStatisik> data) {
		this.data = data;
	}

	@SuppressWarnings("unchecked")
	public Class getColumnClass(int c) {
		// if (c == IX_RIC) {
		// return String.class;
		// }
		return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public void refresh() {
		this.fireTableRowsUpdated(0, this.getRowCount() - 1);
	}
}
