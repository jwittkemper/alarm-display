package biz.wittkemper.database.dao;

import java.util.List;

import biz.wittkemper.database.entity.Posac_status;

public class Posac_statusDAOImpl extends AbstractDAOImpl<Posac_status, Long>
		implements Posac_statusDAO {

	@Override
	protected Class<Posac_status> getDomainClass() {
		return Posac_status.class;
	}

	@Override
	public Posac_status findByStatus(int status) {
		String hql = "FROM Posac_status p ";
		hql += " Where p.posac_status = " + status;
		List<Posac_status> list = findByQueryString(hql);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
}
