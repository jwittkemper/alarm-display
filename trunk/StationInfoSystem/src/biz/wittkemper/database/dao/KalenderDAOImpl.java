package biz.wittkemper.database.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import biz.wittkemper.database.entity.Alarmierung;
import biz.wittkemper.database.entity.Kalender;

public class KalenderDAOImpl extends AbstractDAOImpl<Kalender, Integer>
		implements KalenderDAO {

	@Override
	protected Class<Kalender> getDomainClass() {
		return Kalender.class;
	}

	@Override
	public List<Kalender> getKalender() {
				
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		Date startTime = calendar.getTime();
		
		String hql =" FROM Kalender Where datum >= :startTime";
		hql += " Order by datum ";
		Session session = SessionFactotyUtil.getInstance().getCurrentSession();
		Transaction trx = session.beginTransaction();
		
		Query query = session.createQuery(hql.toString());
		query.setParameter("startTime", startTime);
		List<Kalender> list = query.list();

		trx.commit();		
		if (list.size()> 0){
			return list;
		}else{
			return new ArrayList<Kalender>();
		}
	}

	@Override
	public Kalender getnextUebung() {
		Kalender kal;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		Date startTime = calendar.getTime();
		
		String hql =" FROM Kalender Where datum >= :startTime";
		hql+= " and kalenderTyp.id = 2 ";
		hql += " Order by datum ";
		
		Session session = SessionFactotyUtil.getInstance().getCurrentSession();
		Transaction trx = session.beginTransaction();
		
		Query query = session.createQuery(hql.toString());
		query.setParameter("startTime", startTime);
		List<Kalender> list = query.list();
		trx.commit();		
		
		if (list.size()> 0){
			kal = list.get(0);
		}else{
			kal = null;
		}
		
		return kal;
	}

}
