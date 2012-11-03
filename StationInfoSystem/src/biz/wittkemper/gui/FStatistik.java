package biz.wittkemper.gui;

import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import biz.wittkemper.database.dao.DAOFactory;
import biz.wittkemper.database.entity.KennungStatisik;
import biz.wittkemper.gui.tabmodels.StatistikTabModel;

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
public class FStatistik extends javax.swing.JInternalFrame implements IGUI {
	private JXTable tabMelder;
	private JScrollPane jScrollPane1;
	private JLabel jLabel1;
	StatistikTabModel tabModel;

	private static final Logger log = Logger.getLogger(FStatistik.class
			.getName());

	/**
	 * Auto-generated main method to display this JInternalFrame inside a new
	 * JFrame.
	 */

	public FStatistik() {

		super("Statistik", false, false, false);
		setName("FStatistik");
		initGUI();
	}

	private void zeigeStatistik() {
		List<KennungStatisik> auswertung = DAOFactory.getInstance()
				.getKennungDAO().getStatistikLaufendesJahr();
		tabModel.setData(auswertung);
		tabMelder.setModel(tabModel);
		tabModel.refresh();

	}

	private void initGUI() {
		try {

			this.setBounds(0, 0, 780, 527);
			GroupLayout thisLayout = new GroupLayout(getContentPane());
			getContentPane().setLayout(thisLayout);

			this.setPreferredSize(new java.awt.Dimension(780, 527));
			getContentPane().setBackground(new Color(127, 255, 0));
			this.setTitle("Statistik");
			this.setIcon(false);
			this.setMaximizable(false);
			{
				jScrollPane1 = new JScrollPane();
				jScrollPane1.setOpaque(false);
				jScrollPane1.setBackground(new Color(0, 0, 0, 0));
				jScrollPane1.getViewport().setBackground(new Color(0, 0, 0, 0));
				{

					HighlighterFactory.createAlternateStriping();
					tabMelder = new JXTable();
					tabModel = new StatistikTabModel();
					tabMelder.setModel(tabModel);
					jScrollPane1.setViewportView(tabMelder);
					tabMelder.setModel(tabModel);
					tabMelder
							.setPreferredSize(new java.awt.Dimension(737, 212));
					tabMelder.setBorder(BorderFactory
							.createEtchedBorder(BevelBorder.LOWERED));
					tabMelder.setFont(new java.awt.Font("Arial", 0, 36));
					tabMelder.setRowHeight(70);
					tabMelder.setOpaque(false);
					tabMelder.setBackground(new Color(0, 0, 0, 0));
					tabMelder.setColumnControlVisible(true);
					tabMelder.setAutoscrolls(false);
					tabMelder.setAutoStartEditOnKeyStroke(false);
					tabMelder.setSelectionBackground(new java.awt.Color(144,
							238, 144));
					tabMelder.setHighlighters(HighlighterFactory
							.createAlternateStriping());
					tabMelder.setSortsOnUpdates(false);
					tabMelder.setTerminateEditOnFocusLost(false);

				}
			}
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Meldungen im laufenden Jahr");
				jLabel1.setBackground(new java.awt.Color(255, 165, 0));
				jLabel1.setOpaque(true);
				jLabel1.setHorizontalAlignment(JLabel.CENTER);
				jLabel1.setFont(new java.awt.Font("Arial", 1, 28));
			}
			thisLayout.setVerticalGroup(thisLayout
					.createSequentialGroup()
					.addContainerGap(20, 20)
					.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE,
							GroupLayout.PREFERRED_SIZE,
							GroupLayout.PREFERRED_SIZE).addGap(17)
					.addComponent(jScrollPane1, 0, 440, Short.MAX_VALUE)
					.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout
					.createSequentialGroup()
					.addContainerGap()
					.addGroup(
							thisLayout
									.createParallelGroup()
									.addComponent(jLabel1,
											GroupLayout.Alignment.LEADING, 0,
											755, Short.MAX_VALUE)
									.addComponent(jScrollPane1,
											GroupLayout.Alignment.LEADING, 0,
											755, Short.MAX_VALUE))
					.addContainerGap());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			zeigeStatistik();
			setVisible(true);
			setMaximum(true);

		} catch (Exception ex) {
			log.error("Fehler in FStatistik", ex);
		}

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}
