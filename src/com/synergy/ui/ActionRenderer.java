package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import com.synergy.utils.Localization;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class ActionRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
    private final PillButton renderButton;
    private final PillButton editorButton;
    private final JPanel renderPanel;
    private final JPanel editorPanel;

    public ActionRenderer() {
        renderButton = createButton();
        editorButton = createButton();
        
        renderPanel = createPanel(renderButton);
        editorPanel = createPanel(editorButton);
    }

    private PillButton createButton() {
        PillButton button = new PillButton(Localization.get("table.view"), true);
        button.setPreferredSize(new Dimension(90, 40));
        return button;
    }

    private JPanel createPanel(PillButton button) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(button, gbc);
        return panel;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Update text on render
        renderButton.setText(Localization.get("table.view"));
        renderPanel.setBackground(row % 2 == 0 ? ColorScheme.CARD_BACKGROUND : ColorScheme.TABLE_ROW_ALT);
        return renderPanel;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Update text on edit
        editorButton.setText(Localization.get("table.view"));
        editorPanel.setBackground(row % 2 == 0 ? ColorScheme.CARD_BACKGROUND : ColorScheme.TABLE_ROW_ALT);
        return editorPanel;
    }

    @Override
    public Object getCellEditorValue() {
        return Localization.get("table.view");
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        if (event instanceof MouseEvent) {
            MouseEvent mouseEvent = (MouseEvent) event;
            JTable table = (JTable) mouseEvent.getSource();
            Point point = mouseEvent.getPoint();
            int row = table.rowAtPoint(point);
            int column = table.columnAtPoint(point);
            
            if (row >= 0) {
                updateEditorButtonListener(table, row);
            }
            
            return isClickInsideButton(table, row, column, point);
        }
        return false;
    }

    private void updateEditorButtonListener(JTable table, int row) {
        String name = (String) table.getValueAt(row, 0);
        String instructor = (String) table.getValueAt(row, 1);
        String status = (String) table.getValueAt(row, 4);
        
        for (ActionListener listener : editorButton.getActionListeners()) {
            editorButton.removeActionListener(listener);
        }
        editorButton.addActionListener(e -> {
            fireEditingStopped();
            openCourseDetails(table, name, instructor, status);
        });
    }

    private void openCourseDetails(JTable table, String name, String instructor, String status) {
        java.awt.Frame owner = (java.awt.Frame) SwingUtilities.getWindowAncestor(table);
        new CourseDetailsDialog(owner, name, instructor, status).setVisible(true);
    }

    private boolean isClickInsideButton(JTable table, int row, int column, Point point) {
        int cellWidth = table.getCellRect(row, column, true).width;
        int cellHeight = table.getCellRect(row, column, true).height;
        int btnWidth = 90;
        int btnHeight = 40;
        int btnX = (cellWidth - btnWidth) / 2;
        int btnY = (cellHeight - btnHeight) / 2;
        
        Point cellPoint = new Point(point);
        cellPoint.translate(-table.getCellRect(row, column, true).x, -table.getCellRect(row, column, true).y);
        
        return cellPoint.x >= btnX && cellPoint.x <= btnX + btnWidth && 
               cellPoint.y >= btnY && cellPoint.y <= btnY + btnHeight;
    }
}
