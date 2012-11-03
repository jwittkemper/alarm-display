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

public class AlarmierungDAOImpl extends AbstractDAOImpl<Alarmierung, Long> implements AlarmierungDAO{

	public List<Alarmierung> getNewMessage(){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, -20);
		Date startTime = calendar.getTime();
		
		String hql ="From Alarmierung a Where a.uhrzeit >:startTime ";
		hql += " ORDER BY a.uhrzeit DESC";
		
		Session session = SessionFactotyUtil.getInstance().getCurrentSession();
		Transaction trx = session.beginTransaction();
		
		Query query = session.createQuery(hql.toString());
		query.setParameter("startTime", startTime);
		List<Alarmierung> list = query.list();

		trx.commit();		
		if (list.size()> 0){
			return list;
		}else{
			return new ArrayList<Alarmierung>();
		}
		
	}
	
	@Override
	protected Class<Alarmierung> getDomainClass() {
		return Alarmierung.class;
	}

}
