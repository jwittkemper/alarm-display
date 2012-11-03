package biz.wittkemper.gui;

import java.awt.Color;
import java.beans.PropertyVetoException;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.table.TableColumnExt;

import biz.wittkemper.database.dao.DAOFactory;
import biz.wittkemper.gui.tabmodels.KalenderTabModel;
import biz.wittkemper.utils.Utils;

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
public class FKalender extends javax.swing.JInternalFrame implements IGUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JXTable tabkalender;
	private JScrollPane jScrollPane1;
	private JLabel jLabel1;
	private final KalenderTabModel kaltab = new KalenderTabModel();
	private JMenuBar menuBar;
	private JMenu mnDatei;
	private JMenuItem mntmSchliessen;
	private JMenu mnBearbeiten;
	private JMenuItem mntmNeuerEintrag;
	private JMenuItem mntmEintragndern;
	private JMenuItem mntmEintragLschen;

	private static final Logger log = Logger.getLogger(FKalender.class
			.getName());

	public FKalender() {
		super();
		try {

			setName("FKalender");
			{
				menuBar = new JMenuBar();
				setJMenuBar(menuBar);
				{
					mnDatei = new JMenu("Datei");
					menuBar.add(mnDatei);
					{
						mntmSchliessen = new JMenuItem("schliessen");
						mnDatei.add(mntmSchliessen);
					}
				}
				{
					mnBearbeiten = new JMenu("bearbeiten");
					menuBar.add(mnBearbeiten);
					{
						mntmEintragndern = new JMenuItem("Eintrag ändern");
						mnBearbeiten.add(mntmEintragndern);
					}
					{
						mntmEintragLschen = new JMenuItem("Eintrag löschen");
						mnBearbeiten.add(mntmEintragLschen);
					}
					{
						mntmNeuerEintrag = new JMenuItem("neuer Eintrag");
						mnBearbeiten.add(mntmNeuerEintrag);
					}
				}
			}
			initGUI();
			if (Utils.checkMaster()) {
				menuBar.setVisible(false);
			} else {
				menuBar.setVisible(true);
			}
		} catch (Exception ex) {
			log.error("Fehler in FKalener.", ex);
		}
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(764, 520));
			this.setBounds(0, 0, 764, 520);
			GroupLayout thisLayout = new GroupLayout(getContentPane());
			getContentPane().setLayout(thisLayout);
			getContentPane().setBackground(new Color(65, 105, 225));
			{
				jScrollPane1 = new JScrollPane();
				jScrollPane1.setOpaque(true);
				{
					tabkalender = new JXTable();
					jScrollPane1.setOpaque(false);
					jScrollPane1.getViewport().setBackground(
							new Color(0, 0, 0, 0));
					jScrollPane1.setViewportView(tabkalender);
					tabkalender.setModel(kaltab);
					tabkalender.setFont(new java.awt.Font("Arial", 1, 32));
					tabkalender.setOpaque(false);
					tabkalender.setBackground(new Color(0, 0, 0, 0));
					tabkalender.setHighlighters(HighlighterFactory
							.createAlternateStriping());
					tabkalender.setRowHeight(140);
				}
			}
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Kalender");
				jLabel1.setBackground(new java.awt.Color(255, 165, 0));
				jLabel1.setOpaque(true);
				jLabel1.setFont(new java.awt.Font("Arial", 1, 28));
				jLabel1.setHorizontalAlignment(JLabel.CENTER);
			}
			thisLayout.setVerticalGroup(thisLayout
					.createSequentialGroup()
					.addContainerGap(28, 28)
					.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 29,
							GroupLayout.PREFERRED_SIZE).addGap(17)
					.addComponent(jScrollPane1, 0, 392, Short.MAX_VALUE)
					.addContainerGap(31, 31));
			thisLayout.setHorizontalGroup(thisLayout
					.createSequentialGroup()
					.addContainerGap()
					.addGroup(
							thisLayout
									.createParallelGroup()
									.addComponent(jLabel1,
											GroupLayout.Alignment.LEADING, 0,
											739, Short.MAX_VALUE)
									.addComponent(jScrollPane1,
											GroupLayout.Alignment.LEADING, 0,
											739, Short.MAX_VALUE))
					.addContainerGap());
		} catch (Exception e) {
			System.out.println("InitGUI: " + new Date());
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void run() {
		try {
			loadData();
			setVisible(true);
			setMaximum(true);
		} catch (PropertyVetoException e) {
			System.out.println("Datum: " + new Date());
			System.out.println(e.getMessage());
		}

	}

	private void loadData() {
		try {
			kaltab.setData(DAOFactory.getInstance().getKalenderDAO()
					.getKalender());
			tabkalender.setModel(kaltab);
			setColumns();
		} catch (Exception ex) {
			System.out.println("Hier aber ...: " + new Date());
			System.out.println(ex.getMessage());
		}
	}

	private void setColumns() {
		try {
			TableColumnExt tc1 = tabkalender.getColumnExt("Datum");
			tc1.setMaxWidth(200);
			tc1.setMinWidth(5);
			tc1.setWidth(200);
			tc1.setPreferredWidth(200);

			TableColumnExt tc3 = tabkalender.getColumnExt("Art");
			tc3.setMaxWidth(180);
			tc3.setMinWidth(5);
			tc3.setWidth(170);
			tc3.setPreferredWidth(170);

			// TableColumnExt tc4 = tabkalender.getColumnExt("aktiv");
			// tc4.setMaxWidth(180);
			// tc4.setMinWidth(5);
			// tc4.setWidth(170);
			// tc4.setPreferredWidth(170);

		} catch (Exception ex) {
			System.out.println("Hier ist es ....");
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}
