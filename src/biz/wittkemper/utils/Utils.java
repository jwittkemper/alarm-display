package biz.wittkemper.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Utils {
	final static String filename = "properties.xml";
	final static String database = "DATABASE";
	final static String master = "MASTER";

	public enum STATIONPROP {
		DATABASE, MASTER
	};

	
	private static Map<STATIONPROP, String> list = new HashMap<STATIONPROP, String>();

	public static boolean PropertieFileExist() {

		File file = new File(filename);
		return file.exists();
	}

	public static boolean checkMaster() {
		String lmaster = getPropertie(STATIONPROP.MASTER);
		if (lmaster != null && lmaster.equalsIgnoreCase("false")) {
			return false;
		}
		return true;
	}
	
	public static String getPropertie(STATIONPROP prop) {
		String lreturn = "";

		lreturn = list.get(prop);

		if (lreturn==null || lreturn.length() == 0) {

			switch (prop) {
			case DATABASE:
				lreturn = readProp(database);
				break;
			case MASTER:
				lreturn = readProp(master);
				break;
			}
			list.put(prop, lreturn);
		}
		return lreturn;
	}

	public static void storePropertie(STATIONPROP prop, String value) {
		Properties pro = new Properties();
		try {
			pro.load(new FileInputStream(new File(filename)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		switch (prop) {
		case DATABASE:
			pro.setProperty(database, "localhost");
			break;
		case MASTER:
			pro.setProperty(master, value);
			break;

		}

		try {
			pro.store(new FileOutputStream(new File(filename)), "Daten");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String readProp(String propertie) {

		if (PropertieFileExist()) {
			Properties prop = new Properties();
			try {
				prop.load(new FileInputStream(new File(filename)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return prop.getProperty(propertie);
		}
		return "";
	}
}
