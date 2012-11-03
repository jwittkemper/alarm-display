package biz.wittkemper.utils;

import java.util.Date;
import java.util.TimerTask;

public class Task extends TimerTask{

	@Override
	public void run() {
		System.out.println("Läuft..." + new Date());
		
	}

}
