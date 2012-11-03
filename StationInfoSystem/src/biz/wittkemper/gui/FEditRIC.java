package biz.wittkemper.gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.calendar.DateSelectionModel.SelectionMode;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import biz.wittkemper.database.dao.DAOFactory;
import biz.wittkemper.database.entity.Kennung;
import biz.wittkemper.gui.tabmodels.RicTabModel;
import biz.wittkemper.utils.FrameUtils;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class FEditRIC extends javax.swing.JDialog implements ActionListener {
	private JMenuBar MMain;
	private JMenu jMenu1;
	private JMenuItem mExit;
	private JScrollPane jScrollPane1;
	private JLabel titel;
	private JButton bSave;
	private JButton bCancel;
	private JXTable tabRIC = new JXTable();
	private FrameUtils frameUtils = new FrameUtils();
	/**
	* Auto-generated main method to display this JDialog
	*/
	
	public FEditRIC(JFrame frame) {
		super(frame);
		this.setModal(true);
		initGUI();
		frameUtils.CenterDialog(this);
		
		GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
		getContentPane().setLayout(thisLayout);
		thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
			.addContainerGap()
			.addComponent(getJTextField1(), GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
			.addGap(22)
			.addComponent(getJScrollPane1(), GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(bSave, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(bCancel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addContainerGap(16, 16));
		thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
			.addContainerGap()
			.addGroup(thisLayout.createParallelGroup()
			    .addGroup(thisLayout.createSequentialGroup()
			        .addComponent(getJScrollPane1(), GroupLayout.PREFERRED_SIZE, 559, GroupLayout.PREFERRED_SIZE)
			        .addGap(0, 0, Short.MAX_VALUE))
			    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
			        .addComponent(getJTextField1(), GroupLayout.PREFERRED_SIZE, 553, GroupLayout.PREFERRED_SIZE)
			        .addGap(0, 6, Short.MAX_VALUE))
			    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
			        .addGap(278)
			        .addComponent(bSave, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
			        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			        .addComponent(bCancel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
			        .addGap(0, 6, Short.MAX_VALUE)))
			.addContainerGap());
		this.setTitle("RIC Editor");
		this.setResizable(false);
		tabRIC.setHighlighters(HighlighterFactory.createSimpleStriping());
		tabRIC.setModel(getTabModel());
		tabRIC.setSurrendersFocusOnKeystroke(true);
		tabRIC.setColumnSelectionAllowed(false);
		tabRIC.setSelectionMode(0);
		
	}
	
	private TableModel getTabModel() {
		List<Kennung> data =  DAOFactory.getInstance().getKennungDAO().findByQueryString("FROM Kennung a");
		RicTabModel tabModel = new RicTabModel();
		tabModel.setData(data);
		return tabModel;
	}

	private void initGUI() {
		try {
			{
				bCancel = new JButton();
				bCancel.setText("abbrechen");
				bCancel.setToolTipText("Eingabe abbrechen");
			}
			{
				bSave = new JButton();
				bSave.setText("speichern");
				bSave.setSize(135, 22);
				bSave.setToolTipText("Änderungen speichern");
			}
			{
				MMain = new JMenuBar();
				setJMenuBar(MMain);
				{
					jMenu1 = new JMenu();
					MMain.add(jMenu1);
					jMenu1.setText("Datei");
					{
						mExit = new JMenuItem();
						jMenu1.add(mExit);
						mExit.setText("schliessen");
						mExit.setActionCommand("close");
						mExit.addActionListener(this);
					}
				}
			}
			this.setSize(593, 403);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("close")){
			this.dispose();
		}
	}
	
	private JScrollPane getJScrollPane1() {
		if(jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(tabRIC);
			tabRIC.setPreferredSize(new java.awt.Dimension(556, 269));
		}
		return jScrollPane1;
	}
	
	private JLabel getJTextField1() {
		if(titel == null) {
			titel = new JLabel();
			titel.setText("Überwachte RIC's");
			titel.setFont(new java.awt.Font("AlMohanad",1,18));
			titel.setBackground(new java.awt.Color(144,238,144));
			titel.setHorizontalAlignment(JTextField.CENTER);
			titel.setOpaque(true);

		}
		return titel;
	}

}
