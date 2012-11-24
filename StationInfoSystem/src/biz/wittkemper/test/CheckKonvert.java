package biz.wittkemper.test;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.HeadlessException;
import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import biz.wittkemper.database.dao.DAOFactory;
import biz.wittkemper.database.entity.Alarmierung;
import biz.wittkemper.utils.KonvertMessage;
import biz.wittkemper.utils.Utils;
import biz.wittkemper.utils.Utils.STATIONPROP;

public class CheckKonvert {

	@Test
	public void Propertest() {
		Utils ut = new Utils();

		Utils.storePropertie(STATIONPROP.DATABASE, "localhost");
		Utils.storePropertie(STATIONPROP.MASTER, "false");
		System.out.println(Utils.getPropertie(STATIONPROP.DATABASE));
	}

	@Ignore
	public void cheAlarm() {
		List<Alarmierung> list = DAOFactory.getInstance().getAlarmierungDAO()
				.getNewMessage();

		for (Alarmierung a : list) {
			System.out.println(a.getStatus().getText() + " "
					+ a.getKennung().getBezeichnung() + " :" + a.getText());
		}
	}

	@Ignore
	public void check() {
		KonvertMessage konvertMessage = new KonvertMessage(null, null);
		konvertMessage.run();
	}

	@Ignore
	public void cheSound() {
		File file = new File(
				"/home/joerg/Develop/StationInfoSystem/picture/pager.wav");
		try {
			AudioClip sound = new Applet().newAudioClip(file.toURL());
			sound.play();
			Thread.sleep(70000);
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
