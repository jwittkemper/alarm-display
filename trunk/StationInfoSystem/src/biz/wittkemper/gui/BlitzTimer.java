package biz.wittkemper.gui;

import java.awt.Color;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BlitzTimer extends TimerTask {

	JPanel master;
	JPanel links;
	JPanel rechts;
	JPanel mitte;

	JLabel text;

	Color color1;
	Color color2;
	private static boolean blitz;

	public BlitzTimer(JPanel master, JPanel links, JPanel rechts, JPanel mitte,
			JLabel text) {
		this.master = master;
		this.links = links;
		this.rechts = rechts;
		this.mitte = mitte;
		this.text = text;

		color1 = Color.RED;
		color2 = Color.YELLOW;

	}

	@Override
	public void run() {
		if (blitz) {
			master.setBackground(color1);
			links.setBackground(color2);
			rechts.setBackground(color2);
			mitte.setBackground(color2);
			text.setBackground(color1);
		} else {
			master.setBackground(color2);
			links.setBackground(color1);
			rechts.setBackground(color1);
			mitte.setBackground(color1);
			text.setBackground(color2);
		}
		master.repaint();
		links.repaint();
		rechts.repaint();
		mitte.repaint();

		blitz = !blitz;
	}

}
