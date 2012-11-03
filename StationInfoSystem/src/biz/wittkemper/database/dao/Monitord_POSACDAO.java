package biz.wittkemper.database.dao;

import java.util.List;

import biz.wittkemper.database.entity.Monitord_POSAC;

public interface Monitord_POSACDAO extends AbstractDAO<Monitord_POSAC, Long> {

	public List<Monitord_POSAC> getNewMessages();
}
