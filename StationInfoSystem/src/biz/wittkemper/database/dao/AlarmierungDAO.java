package biz.wittkemper.database.dao;

import java.util.List;

import biz.wittkemper.database.entity.Alarmierung;

public interface AlarmierungDAO extends AbstractDAO<Alarmierung, Long> {
	public List<Alarmierung> getNewMessage();
}
