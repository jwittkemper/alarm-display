package biz.wittkemper.database.dao;

import biz.wittkemper.database.entity.KalenderTyp;

public class KalenderTypDAOImpl extends AbstractDAOImpl<KalenderTyp, Integer>
		implements KalenderTypDAO {

	@Override
	protected Class<KalenderTyp> getDomainClass() {
		return KalenderTyp.class;
	}

}
