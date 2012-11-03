package biz.wittkemper.gui;

import javax.swing.JInternalFrame;

import org.apache.log4j.Logger;

public class FrameFactory {
	private static final Logger log = Logger.getLogger(FrameFactory.class
			.getName());
	private int currentFrame = 0;

	private static final int FRAMES = 4;

	public FrameFactory() {

		currentFrame = 0;
	}

	public JInternalFrame getNextFrame() {
		try {
			currentFrame++;
			if (currentFrame > FRAMES) {
				currentFrame = 1;
			}
			switch (currentFrame) {

			case 1:
				return new FKalender();

			case 2:
				return new FStatistik();

			case 3:
				return new FUebung();

			default:
				return null;
			}
		} catch (Exception ex) {
			log.error("Felher in Framefactory", ex);
			return null;
		}

	}
}
