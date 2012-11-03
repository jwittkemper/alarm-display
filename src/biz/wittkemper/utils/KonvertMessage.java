package biz.wittkemper.utils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.TimerTask;

import javax.swing.JLabel;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import biz.wittkemper.database.dao.DAOFactory;
import biz.wittkemper.database.dao.SessionFactotyUtil;
import biz.wittkemper.database.entity.Alarmierung;
import biz.wittkemper.database.entity.Monitord_POSAC;
import biz.wittkemper.gui.MeldungsMelder;

public class KonvertMessage extends TimerTask {
	private static final Logger log = Logger.getLogger(KonvertMessage.class
			.getName());
	JLabel status;
	MeldungsMelder melder;
	int zaehler = 0;
	private boolean master = true;

	public KonvertMessage(JLabel status, MeldungsMelder meldungsMelder) {
		this.status = status;
		this.melder = meldungsMelder;
		this.master = Utils.checkMaster();
	}

	private void setzeText(String text) {
		if (status != null)
			status.setText(text);
	}

	private boolean meldungen() {

		boolean lreturn = false;

		setzeText("Suche neue Meldungen.....");
		List<Monitord_POSAC> meldungen = DAOFactory.getInstance()
				.getMonitordPOSACDAO().getNewMessages();
		setzeText(meldungen.size() + " Meldungen gefunden.");
		for (Monitord_POSAC mp : meldungen) {
			Alarmierung alarmierung = checkAlarmierung(mp);
			if (alarmierung != null) {
				if (alarmierung.getEot() == false) {
					if (alarmierung.getText().length() < mp.getText().length()) {
						alarmierung.setText(mp.getText());
						chekEOT(mp, alarmierung);
						DAOFactory.getInstance().getAlarmierungDAO()
								.update(alarmierung);
					}
				}
			} else {
				setzeText("Neue Meldung gefunden");
				trageAlarmEin(mp);
				lreturn = true;
			}
			DAOFactory.getInstance().getMonitordPOSACDAO().delete(mp);
		}
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("ECT"));
		DateFormat formater;
		formater = DateFormat.getDateTimeInstance(DateFormat.SHORT,
				DateFormat.MEDIUM);
		setzeText("Durchlauf beendet...." + formater.format(cal.getTime()));
		return lreturn;
	}

	public void trageAlarmEin(Monitord_POSAC mp) {
		try {
			Alarmierung alarmierung = new Alarmierung();
			alarmierung.setKennung(DAOFactory.getInstance().getKennungDAO()
					.load(Long.parseLong(mp.getKennung())));
			if (alarmierung.getKennung().getAusblenden() == true) {
				alarmierung.setText("");
			} else {
				alarmierung.setText(mp.getText());
			}
			alarmierung.setUhrzeit(mp.getUhrzeit());
			chekEOT(mp, alarmierung);
			alarmierung.setStatus(DAOFactory.getInstance().getPosacStatusDAO()
					.findByStatus(Integer.parseInt(mp.getSub())));

			DAOFactory.getInstance().getAlarmierungDAO().save(alarmierung);

		} catch (Exception ex) {
			log.error("Fehelr in KonvertMessage", ex);
		}
	}

	private void chekEOT(Monitord_POSAC mp, Alarmierung alarmierung) {
		if (mp.getText().indexOf("<EOT>") > 0) {
			alarmierung.setText(alarmierung.getText().replaceAll("<EOT>", ""));
			alarmierung.setEot(true);
		} else {
			alarmierung.setEot(false);
		}
	}

	private Alarmierung checkAlarmierung(Monitord_POSAC mp) {
		Calendar calendar = new GregorianCalendar();

		StringBuffer hql = new StringBuffer(" From Alarmierung a Where ");

		calendar.setTime(mp.getUhrzeit());
		calendar.add(Calendar.MINUTE, -2);
		Date minDate = calendar.getTime();
		calendar.add(Calendar.MINUTE, +4);
		Date maxDate = calendar.getTime();

		hql.append("a.uhrzeit >= :startDate");

		hql.append(" AND ");
		hql.append("a.uhrzeit <= :endDate");

		hql.append(" AND a.kennung.ric = " + mp.getKennung());
		hql.append(" AND a.status.posac_status = " + mp.getSub());

		Session session = SessionFactotyUtil.getInstance().getCurrentSession();
		Transaction trx = session.beginTransaction();

		Query query = session.createQuery(hql.toString());
		query.setParameter("startDate", minDate);
		query.setParameter("endDate", maxDate);

		List<Alarmierung> list = query.list();
		trx.commit();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void run() {
		if (master) {
			if (meldungen() == true) {
				melder.setAenderung("ALARM");
			} else {
				if (zaehler >= 3) {
					zaehler = 0;
					melder.setAenderung("NEXTFRAME");
				} else {
					zaehler++;
				}
			}
		} else {

		}
	}
}
