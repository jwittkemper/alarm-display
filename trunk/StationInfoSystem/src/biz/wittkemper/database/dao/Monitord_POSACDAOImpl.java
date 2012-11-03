package biz.wittkemper.database.dao;

import java.util.List;

import biz.wittkemper.database.entity.Monitord_POSAC;

public class Monitord_POSACDAOImpl extends
		AbstractDAOImpl<Monitord_POSAC, Long> implements Monitord_POSACDAO {

	@Override
	protected Class<Monitord_POSAC> getDomainClass() {
		// TODO Auto-generated method stub
		return Monitord_POSAC.class;
	}
	
	public List<Monitord_POSAC> getNewMessages(){
		String hql ="FROM Monitord_POSAC m Order by m.uhrzeit";
		return findByQueryString(hql); 
	}
}
