package biz.wittkemper.gui.tabmodels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import biz.wittkemper.database.entity.Alarmierung;
import biz.wittkemper.gui.render.MultiLineCellRenderer;

public class MeldungTabModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private final int IX_MELDER = 0;
	private final int IX_DRINGLICHKEIT = 1;
	private final int IX_MELDUNG = 2;
	private final int IX_ALTER = 3;

	
	private String[] columnNames;
	private List<Alarmierung> data = new ArrayList<Alarmierung>();

	public MeldungTabModel() {
		setColumnNames();
	}

	private void setColumnNames() {
		columnNames = new String[] { "Melder", "Dringlichkeit", "Meldung",
				"aktiv" };
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
	

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public void setData(List<Alarmierung> data) {
		this.data = data;
	}

	@Override
	public int getRowCount() {
		if (data != null) {
			return data.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (data.size() <= 0 ) {
			return null;
		}
		
		Alarmierung al = data.get(rowIndex);
		switch (columnIndex) {

		case IX_MELDER:
			return getHTMLText( al.getKennung().getBezeichnung(),10);

		case IX_DRINGLICHKEIT:
			return getHTMLText(al.getStatus().getText(),10);

		case IX_MELDUNG:
			return getMeldung(al);

		case IX_ALTER:
			return getHTMLText(berechneAlter(al),25);
		default:
			return "";
		}
	}

	private String getMeldung(Alarmierung al){
		String lreturn="";
		
		if (al.getKennung().getAusblenden() == false){
			lreturn = getHTMLText(al.getText(),25);
		}else{
			lreturn ="";
		}
		return lreturn;
	}
	private String getHTMLText(String text, int anzahl) {
		String lreturn ="<HTML>";
		lreturn += getUmbruch(text, anzahl);
		lreturn += "</HTML>";
		return lreturn;
	}

	private String getUmbruch(String text, int anzahl) {
		String lreturn="";
		String lhelp ="";
		
		if (text.length() < anzahl || text.equals("")){
			return text;
		}else{
			String teile[] = text.split(" ");
			for(int i=0; i < teile.length; i++){
				if(lhelp.length() + teile[i].length() > anzahl){
					lreturn += " <br> " + teile[i];
					lhelp="";
				}else{
					lreturn += teile[i] + " ";
					lhelp += teile[i] + " ";
				}
			}
			return lreturn;
		}
		
	}

	private String berechneAlter(Alarmierung al) {
		Date date = new Date();
		long diff = date.getTime() - al.getUhrzeit().getTime();
		diff = (diff / 1000L);
		if (diff > 60){
			long min = diff / 60;
			long sek = diff % 60;
			return (min) + " min. <br>" + sek + " sek.";
		}else {
			return diff + " sek.";
		}
	}

	public void refresh() {
		this.fireTableRowsUpdated(0, this.getRowCount() - 1);
	}
}
