package biz.wittkemper.utils;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class FrameUtils {

	public void CenterDialog(JDialog dialog) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int top = (screenSize.height - dialog.getHeight()) / 2;
		int left = (screenSize.width - dialog.getWidth()) / 2;

		dialog.setLocation(left, top);
	}

	public Image getApplicationIcon() {
		URL url = this.getClass().getResource("Feuerwehr_Symbol.jpg");
		Image image = Toolkit.getDefaultToolkit().getImage(url);
		return image;
	}

	public Icon getBlaulichtIcon() {
		URL url = this.getClass().getResource("alarm.gif");
		ImageIcon icon = new ImageIcon(url);
		return icon;
	}

	public String getDateFormat(Date date) {
		DateFormat formater;
		formater = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.SHORT);
		return formater.format(date);
	}

	public int getDateDiff(Date date1, Date date2) {
		
		if (date1 == null || date2 ==null){
			return 0;
		}
		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();
		cal1.setTime(date1); // erster Zeitpunkt
		cal2.setTime(date2); // zweiter Zeitpunkt
		
		long time = cal2.getTime().getTime() - cal1.getTime().getTime(); // Differenz
																			// in
																			// ms
		int days = (int) Math.round((double) time / (24. * 60. * 60. * 1000.)); // Differenz
																			// in
																			// Tagen
		return days;
	}
	
	public Dimension getSize(){
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
}
