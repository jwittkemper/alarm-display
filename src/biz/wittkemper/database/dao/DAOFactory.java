package biz.wittkemper.database.dao;

public class DAOFactory {
	
	private static DAOFactory daoFactory;
	
	AlarmierungDAO alarmierungDAO = new AlarmierungDAOImpl();
	KennungDAO kennungDAO = new KennungDAOImpl();
	Monitord_POSACDAO monitordPOSACDAO = new Monitord_POSACDAOImpl();
	Posac_statusDAO posacStatusDAO = new Posac_statusDAOImpl();
	KalenderDAO kalenderDAO = new KalenderDAOImpl();
	KalenderTypDAO kalenderTypDAO = new KalenderTypDAOImpl();
	
	
	private DAOFactory(){
	}
	
	static{
		daoFactory = new DAOFactory();
	}
	
	public static DAOFactory getInstance(){
		return daoFactory;
	}
	public AlarmierungDAO getAlarmierungDAO() {
		return alarmierungDAO;
	}

	public void setAlarmierungDAO(AlarmierungDAO alarmierungDAO) {
		this.alarmierungDAO = alarmierungDAO;
	}

	public KennungDAO getKennungDAO() {
		return kennungDAO;
	}

	public void setKennungDAO(KennungDAO kennungDAO) {
		this.kennungDAO = kennungDAO;
	}

	public Monitord_POSACDAO getMonitordPOSACDAO() {
		return monitordPOSACDAO;
	}

	public void setMonitordPOSACDAO(Monitord_POSACDAO monitordPOSACDAO) {
		this.monitordPOSACDAO = monitordPOSACDAO;
	}
	public Posac_statusDAO getPosacStatusDAO() {
		return posacStatusDAO;
	}
	public void setPosacStatusDAO(Posac_statusDAO posacStatusDAO) {
		this.posacStatusDAO = posacStatusDAO;
	}
	public KalenderDAO getKalenderDAO() {
		return kalenderDAO;
	}
	public void setKalenderDAO(KalenderDAO kalenderDAO) {
		this.kalenderDAO = kalenderDAO;
	}
	public KalenderTypDAO getKalenderTypDAO() {
		return kalenderTypDAO;
	}
	public void setKalenderTypDAO(KalenderTypDAO kalenderTypDAO) {
		this.kalenderTypDAO = kalenderTypDAO;
	}
}
