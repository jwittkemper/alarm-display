package biz.wittkemper.database.dao;

import biz.wittkemper.database.entity.Posac_status;

public interface Posac_statusDAO extends AbstractDAO<Posac_status, Long> {

	public Posac_status findByStatus(int status);
}
