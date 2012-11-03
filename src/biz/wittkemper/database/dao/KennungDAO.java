package biz.wittkemper.database.dao;

import java.util.List;

import biz.wittkemper.database.entity.Kennung;
import biz.wittkemper.database.entity.KennungStatisik;

public interface KennungDAO extends AbstractDAO<Kennung, Long> {

	public List<KennungStatisik> getStatistikLaufendesJahr();

}
