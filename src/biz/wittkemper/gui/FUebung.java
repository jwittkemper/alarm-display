package biz.wittkemper.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import biz.wittkemper.database.dao.DAOFactory;
import biz.wittkemper.database.entity.Kalender;
import biz.wittkemper.utils.FrameUtils;

public class FUebung extends JInternalFrame implements IGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final FrameUtils frameUtils = new FrameUtils();
	JTextPane textArea = new JTextPane();

	public FUebung() {
		super();
		this.setPreferredSize(new java.awt.Dimension(764, 520));
		this.setBounds(0, 0, 764, 520);
		setName("FUebung");
		getContentPane().setBackground(new Color(255, 165, 0));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 416,
								Short.MAX_VALUE).addContainerGap()));
		textArea.setEditable(false);
		textArea.setBackground(UIManager.getColor("MenuBar.highlight"));
		textArea.setAlignmentX(CENTER_ALIGNMENT);
		textArea.setAlignmentY(CENTER_ALIGNMENT);
		textArea.setFont(new Font("Arial", Font.BOLD, 32));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 244,
								Short.MAX_VALUE).addContainerGap()));
		getContentPane().setLayout(groupLayout);

	}

	@Override
	public void run() {
		setText();
		setVisible(true);
		try {
			setSize(frameUtils.getSize());
			setSelected(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setText() {
		StyleContext context = new StyleContext();
		StyledDocument document = new DefaultStyledDocument(context);
		Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);
		StyleConstants.setFontSize(style, 40);
		StringBuilder sb = new StringBuilder();
		int tage;
		String wann = "";
		Kalender kal = DAOFactory.getInstance().getKalenderDAO()
				.getnextUebung();
		sb.append("\n\nDer nächste Übungsdienst ist am: \n\n");
		if (kal != null) {
			sb.append(frameUtils.getDateFormat(kal.getDatum()) + " Uhr\n");
			tage = frameUtils.getDateDiff(new Date(), kal.getDatum());

			switch (tage) {
			case 0:
				wann = "heute";
				break;
			case 1:
				wann = "morgen";
				break;
			case 2:
				wann = "übermorgen";
				break;
			default:
				wann = "in " + tage + " Tagen";
			}
			sb.append("( " + wann + " )\n\n");
			sb.append(kal.getText());
		}
		try {
			document.insertString(document.getLength(), sb.toString(), style);
		} catch (BadLocationException badLocationException) {
			System.err.println("Oops");
		}
		textArea.setOpaque(false);
		textArea.setBackground(new Color(0, 0, 0, 0));
		textArea.setStyledDocument(document);
		textArea.repaint();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}
}
