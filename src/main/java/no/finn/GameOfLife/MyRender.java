package no.finn.GameOfLife;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: javalons
 * Date: 09/04/13
 * Time: 11:05
 * To change this template use File | Settings | File Templates.
 */
public class MyRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //setOpaque(true);
        if (table.getValueAt(row, column).equals(1)) {
            setBackground(Color.green);
            setForeground(Color.green);
        } else {
            setBackground(Color.white);
            setForeground(Color.white);
        }

        return this;
    }
}
