package biz.wittkemper.database.entity;

import java.util.Date;

public class KennungStatisik {
	Kennung kennung;
	int anzahlMeldungen;
	Date letzteMeldung;
	
	public Kennung getKennung() {
		return kennung;
	}
	public void setKennung(Kennung kennung) {
		this.kennung = kennung;
	}
	public int getAnzahlMeldungen() {
		return anzahlMeldungen;
	}
	public void setAnzahlMeldungen(int anzahlMeldungen) {
		this.anzahlMeldungen = anzahlMeldungen;
	}
	public Date getLetzteMeldung() {
		return letzteMeldung;
	}
	public void setLetzteMeldung(Date letzteMeldung) {
		this.letzteMeldung = letzteMeldung;
	}

}
