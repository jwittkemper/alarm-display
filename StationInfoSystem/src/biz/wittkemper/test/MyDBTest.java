package biz.wittkemper.test;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Ignore;
import org.junit.Test;

import biz.wittkemper.database.dao.DAOFactory;
import biz.wittkemper.database.dao.SessionFactotyUtil;
import biz.wittkemper.database.entity.Alarmierung;
import biz.wittkemper.database.entity.Kalender;
import biz.wittkemper.database.entity.KalenderTyp;
import biz.wittkemper.database.entity.Kennung;
import biz.wittkemper.database.entity.KennungStatisik;
import biz.wittkemper.database.entity.Monitord_POSAC;
import biz.wittkemper.database.entity.Posac_status;
import biz.wittkemper.gui.FKalender;
import biz.wittkemper.utils.FrameUtils;

public class MyDBTest {


	@Test
	public void checkKalender(){
		FrameUtils frameUtils = new FrameUtils();
		Kalender kal = DAOFactory.getInstance().getKalenderDAO().getnextUebung();
		
		if (kal != null){
			System.out.println( frameUtils.getDateDiff(new Date(), kal.getDatum()));
		}
	}
	@Ignore
	public void checkStatistik(){
		
		
		List<KennungStatisik> stat = DAOFactory.getInstance().getKennungDAO().getStatistikLaufendesJahr();
		
		for(KennungStatisik k: stat){
			System.out.println(k.getKennung().getBezeichnung() + ": " + k.getAnzahlMeldungen());
			System.out.println("letzte Meldung: " + k.getLetzteMeldung());
		}
		
		
		
		
	}
	@Ignore
	public void checkDAO(){
			List<Kennung>list = DAOFactory.getInstance().getKennungDAO().findByQueryString("FROm Kennung k");
			
			for(Kennung k: list){
				System.out.println(k.getBezeichnung() + " " + k.getAlarmierungen().size());
			}
			
			List<Posac_status> st = DAOFactory.getInstance().getPosacStatusDAO().findByQueryString("FROM Posac_status P");
			
			for(Posac_status p: st){
				System.out.println(p.getText() + " : " + p.getAlarmierungen().size());

			}
	}
	@Ignore
	public void checkDB(){
		Transaction trx;
		Session session = SessionFactotyUtil.getInstance().getCurrentSession();
		
		trx = session.beginTransaction();
		List<Kennung> list = session.createQuery("Select h From Kennung as h").list();
		
		for(Kennung kn: list){
			System.out.println(kn.getBezeichnung());
		}
		trx.commit();
		
		session = SessionFactotyUtil.getInstance().getCurrentSession();
		trx = session.beginTransaction();
		List<Posac_status> list2 = session.createQuery("From Posac_status").list();
		
		for(Posac_status ps : list2){
			System.out.println(ps.getText());
		}
		trx.commit();
		
		session = SessionFactotyUtil.getInstance().getCurrentSession();
		trx = session.beginTransaction();
		
		List<Monitord_POSAC> mp = session.createQuery("From Monitord_POSAC ").list();
		
		for(Monitord_POSAC mps: mp){
			if (mps.getKennung() != null)
				System.out.println(mps.getText() + "   :  " + mps.getKennung());
		}
		trx.commit();
		
		session = SessionFactotyUtil.getInstance().getCurrentSession();
		trx = session.beginTransaction();
		Alarmierung alarmierung = new Alarmierung();
		
		alarmierung.setEot(true);
		alarmierung.setKennung(list.get(0));
		alarmierung.setStatus(list2.get(0));
		alarmierung.setText("Hallo Jörg...");
		alarmierung.setUhrzeit(new Date());
		
		session.save(alarmierung);
		trx.commit();
	}
}
