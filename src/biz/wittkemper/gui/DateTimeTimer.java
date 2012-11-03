package biz.wittkemper.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.TimerTask;

import javax.swing.JLabel;

public class DateTimeTimer extends TimerTask {

	private JLabel label;
	
	public DateTimeTimer(JLabel label){
		this.label = label;
	}
	@Override
	public void run() {
		Calendar cal = new GregorianCalendar( TimeZone.getTimeZone("ECT") );
	    DateFormat formater;
		formater = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM );		
	    label.setText(formater.format(cal.getTime()));

	}

}
