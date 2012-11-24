package biz.wittkemper.gui;

import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.TableColumnExt;

import biz.wittkemper.database.dao.DAOFactory;
import biz.wittkemper.gui.render.MultiLineCellRenderer;
import biz.wittkemper.gui.tabmodels.MeldungTabModel;
import biz.wittkemper.utils.FrameUtils;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class FMeldungsDisplay extends javax.swing.JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel PMainBlitz;
	private JPanel BlitzLinks;
	private JScrollPane MainTab;
	private JXTable tabMeldungen;
	private JPanel BlitzRechts;
	private JPanel BlitzMitte;
	private JFrame master;
	private final Timer blitzTimer = new Timer();
	Timer t = new Timer("Timer-Thread", true);
	private JLabel lbTitel;
	private final MeldungsMelder melder;
	private final FrameUtils frameUtils = new FrameUtils();
	MultiLineCellRenderer multiLineRenderer = new MultiLineCellRenderer(
			SwingConstants.LEFT, SwingConstants.TOP);

	public FMeldungsDisplay(JFrame master, MeldungsMelder melder) {
		super("Meldungsanzeige", false, false, false);
		initGUI();
		try {
			setMaximum(true);
		} catch (Exception ex) {

		}
		repaint();
		this.melder = melder;

		if (master != null) {
			this.master = master;
		}
		addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentResized(ComponentEvent e) {
				berechneBlitze();

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				setPosition();

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}
		});
		berechneBlitze();
		startBlitz(true);
		runMeldungen();

	}

	private void startBlitz(boolean run) {

		class UpdateJob implements Runnable {
			@Override
			public void run() {
				blitzTimer.schedule(new BlitzTimer(PMainBlitz, BlitzLinks,
						BlitzRechts, BlitzMitte, lbTitel), 250, 280);
			}
		}

		if (run) {
			UpdateJob job = new UpdateJob();
			SwingUtilities.invokeLater(job);
		} else {
			blitzTimer.cancel();
		}

	}

	protected void setPosition() {
		this.setLocation(master.getContentPane().getLocation());

	}

	private void berechneBlitze() {
		int with = master.getContentPane().getWidth() / 5;
		int links = master.getContentPane().getX();
		int mitte = (master.getContentPane().getWidth() / 2) - (with / 2);
		int rechts = links + master.getContentPane().getWidth();

		BlitzLinks.setSize(with, BlitzLinks.getHeight());
		BlitzMitte.setSize(with, BlitzLinks.getHeight());
		BlitzRechts.setSize(with + 500, BlitzRechts.getHeight());

		BlitzLinks.setLocation(links, BlitzRechts.getLocation().y);
		BlitzMitte.setLocation(mitte, BlitzRechts.getLocation().y);
		BlitzRechts.setLocation(rechts - with, BlitzRechts.getLocation().y);

	}

	private void initGUI() {
		try {
			this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			this.setBounds(0, 0, 738, 579);
			this.setFrameIcon(frameUtils.getBlaulichtIcon());
			GroupLayout thisLayout = new GroupLayout(getContentPane());
			getContentPane().setLayout(thisLayout);
			setVisible(true);
			{
				PMainBlitz = new JPanel();
				PMainBlitz.setBorder(new LineBorder(
						new java.awt.Color(0, 0, 0), 1, false));
				PMainBlitz.setLayout(null);
				{
					BlitzLinks = new JPanel();
					PMainBlitz.add(BlitzLinks);
					BlitzLinks.setBorder(new LineBorder(new java.awt.Color(0,
							0, 0), 1, false));
					BlitzLinks.setBounds(0, 0, 372, 166);
				}
				{
					BlitzRechts = new JPanel();
					PMainBlitz.add(BlitzRechts, "ANCHOR_RIGHT");
					BlitzRechts.setBorder(new LineBorder(new java.awt.Color(0,
							0, 0), 1, false));
					BlitzRechts.setBounds(407, 0, 329, 166);
				}
				{
					BlitzMitte = new JPanel();
					PMainBlitz.add(BlitzMitte, "ANCHOR_ENTER");
					BlitzMitte.setBorder(new LineBorder(new java.awt.Color(0,
							0, 0), 1, false));
					BlitzMitte.setBounds(407, 0, 329, 166);
				}
			}
			{
				MainTab = new JScrollPane();
				{

					tabMeldungen = new JXTable();
					MainTab.setViewportView(tabMeldungen);
					tabMeldungen.setModel(getTabModel());
					tabMeldungen.setSelectionMode(0);
					tabMeldungen.setFont(new java.awt.Font("Arial", 1, 32));
					tabMeldungen.setPreferredSize(new java.awt.Dimension(709,
							284));

					tabMeldungen.setDefaultRenderer(Object.class,
							new MultiLineCellRenderer(SwingConstants.LEFT,
									SwingConstants.TOP));

					tabMeldungen.setRowHeight(140);

					tabMeldungen
							.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					tabMeldungen
							.setPreferredScrollableViewportSize(tabMeldungen
									.getPreferredSize());

				}
			}
			{
				lbTitel = new JLabel();
				lbTitel.setText("anstehende Meldungen");
				lbTitel.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.LOWERED));
				lbTitel.setBackground(new java.awt.Color(255, 0, 0));
				lbTitel.setOpaque(true);
				lbTitel.setFont(new java.awt.Font("AlArabiya", 2, 22));
				lbTitel.setAlignmentY(5.0f);
				lbTitel.setHorizontalAlignment(JLabel.CENTER);
			}
			thisLayout.setVerticalGroup(thisLayout
					.createSequentialGroup()
					.addComponent(PMainBlitz, GroupLayout.PREFERRED_SIZE, 166,
							GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(lbTitel, GroupLayout.PREFERRED_SIZE, 32,
							GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(MainTab, 0, 306, Short.MAX_VALUE)
					.addContainerGap());
			thisLayout
					.setHorizontalGroup(thisLayout
							.createParallelGroup()
							.addComponent(PMainBlitz,
									GroupLayout.Alignment.LEADING, 0, 736,
									Short.MAX_VALUE)
							.addGroup(
									thisLayout
											.createSequentialGroup()
											.addPreferredGap(
													PMainBlitz,
													lbTitel,
													LayoutStyle.ComponentPlacement.INDENT)
											.addGroup(
													thisLayout
															.createParallelGroup()
															.addComponent(
																	lbTitel,
																	GroupLayout.Alignment.LEADING,
																	0,
																	712,
																	Short.MAX_VALUE)
															.addComponent(
																	MainTab,
																	GroupLayout.Alignment.LEADING,
																	0,
																	712,
																	Short.MAX_VALUE))
											.addContainerGap()));
		} catch (Exception e) {
			System.out.println("InitGUI....");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private void runMeldungen() {
		startTimer();
	}

	public void startTimer() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				TimerTask task = new TimerTask() {
					@Override
					public void run() {
						RefreshMeldung();
					}
				};
				t.schedule(task, 0, 4000);
			}
		});

	}

	private void RefreshMeldung() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MeldungTabModel model = (MeldungTabModel) getTabModel();
				tabMeldungen.setModel(model);

				if (model != null && model.getRowCount() > 0) {
					setColumns();
				}

				if (model.getRowCount() <= 0) {
					t.cancel();
					blitzTimer.cancel();
					setVisible(false);
					melder.setAenderung("ALARMENDE");
					dispose();
				}

			}
		});

	}

	private void setColumns() {
		try {
			TableColumnExt tc1 = tabMeldungen.getColumnExt("Melder");
			tc1.setMaxWidth(200);
			tc1.setMinWidth(5);
			tc1.setWidth(200);
			tc1.setPreferredWidth(200);
			TableColumnExt tc2 = tabMeldungen.getColumnExt("Dringlichkeit");
			tc2.setMaxWidth(350);
			tc2.setMinWidth(5);
			tc2.setWidth(250);
			tc2.setPreferredWidth(250);

			TableColumnExt tc3 = tabMeldungen.getColumnExt("aktiv");
			tc3.setMaxWidth(180);
			tc3.setMinWidth(5);
			tc3.setWidth(170);
			tc3.setPreferredWidth(170);

		} catch (Exception ex) {
			System.out.println("Hier ist es ....");
		}
	}

	private TableModel getTabModel() {
		try {
			MeldungTabModel tabModel = new MeldungTabModel();
			tabModel.setData(DAOFactory.getInstance().getAlarmierungDAO()
					.getNewMessage());
			return tabModel;
		} catch (Exception ex) {

			return new MeldungTabModel();
		}
	}

}
