package biz.wittkemper.gui.render;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class MyRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6271816189722681895L;
	public MyRenderer(){
		 setHorizontalAlignment(SwingConstants.LEFT);
		 setVerticalAlignment(SwingConstants.TOP);
	}
    // This method is called each time a cell in a column
    // using this renderer needs to be rendered.
	public Component getTableCellRendererComponent(JTable table, Object value,
		      boolean isSelected, boolean hasFocus, int row, int column) {
		    if (isSelected) {
		      setForeground(table.getSelectionForeground());
		      setBackground(table.getSelectionBackground());
		    } else {
		      setForeground(table.getForeground());
		      setBackground(table.getBackground());
		    }
		    setFont(table.getFont());
		    if (hasFocus) {
		      setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		      if (table.isCellEditable(row, column)) {
		        setForeground(UIManager.getColor("Table.focusCellForeground"));
		        setBackground(UIManager.getColor("Table.focusCellBackground"));
		      }
		    } else {
		      setBorder(new EmptyBorder(1, 2, 1, 2));
		    }
		    setText((value == null) ? "" : value.toString());
		    return this;
		  }

    // The following methods override the defaults for performance reasons
    public void validate() {}
    public void revalidate() {}
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {}
    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {}

}
