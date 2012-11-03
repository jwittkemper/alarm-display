package biz.wittkemper.gui;

import java.awt.Color;
import java.awt.Component;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BlitzTimer extends TimerTask {

	JPanel master;
	JPanel links;
	JPanel rechts;
	Color color1;
	Color color2;
	private static boolean blitz;

	public BlitzTimer(JPanel master, JPanel links, JPanel rechts) {
		this.master = master;
		this.links = links;
		this.rechts = rechts;
		color1 = Color.RED;
		color2 = Color.YELLOW;
		
	}

	@Override
	public void run() {
		if (blitz) {
			master.setBackground(color1);
			links.setBackground(color2);
			rechts.setBackground(color2);
		} else {
			master.setBackground(color2);
			links.setBackground(color1);
			rechts.setBackground(color1);
		}
		master.repaint();
		links.repaint();
		rechts.repaint();
		blitz = !blitz;
	}

}
