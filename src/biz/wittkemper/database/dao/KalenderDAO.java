package biz.wittkemper.database.dao;

import java.util.List;

import biz.wittkemper.database.entity.Kalender;
import biz.wittkemper.gui.tabmodels.KalenderTabModel;

public interface KalenderDAO extends AbstractDAO<Kalender, Integer> {

	List<Kalender> getKalender();

	Kalender getnextUebung();

}
