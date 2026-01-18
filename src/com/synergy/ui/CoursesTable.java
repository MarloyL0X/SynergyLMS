package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import com.synergy.utils.Localization;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class CoursesTable extends JTable {

    public CoursesTable() {
        super(new CourseModel());
        configureTable();
        configureHeader();
        configureColumns();
    }

    private void configureTable() {
        setRowHeight(60);
        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));
        setFillsViewportHeight(true);
        setBackground(ColorScheme.CARD_BACKGROUND);
        setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    private void configureHeader() {
        JTableHeader header = getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer());
        header.setPreferredSize(new Dimension(0, 50));
        header.setBackground(ColorScheme.PRIMARY_RED);
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);
    }

    private void configureColumns() {
        TableColumnModel columnModel = getColumnModel();
        
        configureColumnWidths(columnModel);
        configureColumnRenderers(columnModel);
        configureActionColumn(columnModel);
    }
    
    private void configureColumnWidths(TableColumnModel columnModel) {
        setColumnWidth(columnModel, 5, 140);
        setColumnWidth(columnModel, 4, 120);
        setColumnWidth(columnModel, 3, 100);
        setColumnWidth(columnModel, 2, 100);
        
        columnModel.getColumn(1).setMinWidth(200);
        columnModel.getColumn(1).setPreferredWidth(250);
        
        columnModel.getColumn(0).setMinWidth(250);
        columnModel.getColumn(0).setPreferredWidth(400);
    }
    
    private void setColumnWidth(TableColumnModel model, int columnIndex, int width) {
        model.getColumn(columnIndex).setMinWidth(width);
        model.getColumn(columnIndex).setMaxWidth(width);
        model.getColumn(columnIndex).setPreferredWidth(width);
    }

    private void configureColumnRenderers(TableColumnModel columnModel) {
        columnModel.getColumn(0).setCellRenderer(new CourseNameRenderer());
        
        RowRenderer rowRenderer = new RowRenderer();
        for (int i = 1; i < 5; i++) {
            columnModel.getColumn(i).setCellRenderer(rowRenderer);
        }
    }
    
    private void configureActionColumn(TableColumnModel columnModel) {
        ActionRenderer actionRenderer = new ActionRenderer();
        columnModel.getColumn(5).setCellRenderer(actionRenderer);
        columnModel.getColumn(5).setCellEditor(actionRenderer);
    }

    private static class CourseModel extends DefaultTableModel {
        CourseModel() {
            super(getData(), getHeaders());
        }
        
        private static Object[][] getData() {
            // Data could also be localized if dynamic, but here we can just pick one or reload.
            // Since this model is static, reloading the whole table is best.
            // But we can check locale here for demo.
            if (Localization.isRussian()) {
                return new Object[][]{
                    {"Введение в информатику", "Д-р А. Смирнов", "3", "A-", "Активен", "Просмотр"},
                    {"Математический анализ I", "Проф. Б. Иванов", "4", "B+", "Активен", "Просмотр"},
                    {"Современная история", "М. К. Петрова", "3", "В процессе", "Ожидание", "Просмотр"}
                };
            } else {
                return new Object[][]{
                    {"Introduction to Computer Science", "Dr. A. Smith", "3", "A-", "Active", "View"},
                    {"Calculus I", "Prof. B. Johnson", "4", "B+", "Active", "View"},
                    {"Modern History", "Ms. C. Davis", "3", "In Progress", "Pending", "View"}
                };
            }
        }
        
        private static String[] getHeaders() {
            return new String[]{
                Localization.get("table.course"),
                Localization.get("table.instructor"),
                Localization.get("table.credits"),
                Localization.get("table.grade"),
                Localization.get("table.status"),
                Localization.get("table.action")
            };
        }

        @Override
        public boolean isCellEditable(int row, int column) { return column == 5; }
    }

    private static class HeaderRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBackground(ColorScheme.PRIMARY_RED);
            setForeground(ColorScheme.TABLE_HEADER_TEXT);
            setFont(new Font("Arial", Font.BOLD, 14));
            setBorder(BorderFactory.createEmptyBorder());
            setHorizontalAlignment(SwingConstants.CENTER);
            return this;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (!getText().equals(Localization.get("table.action"))) { 
                g.setColor(new Color(255, 255, 255, 120));
                g.drawLine(getWidth()-1, 0, getWidth()-1, getHeight());
            }
        }
    }

    private static class RowRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Object displayValue = value;
            // Simple translation for status values if they match known keys
            if (value instanceof String) {
                String s = (String) value;
                if (s.equals("Active") || s.equals("Активен")) displayValue = Localization.get("table.active");
                else if (s.equals("Pending") || s.equals("Ожидание")) displayValue = Localization.get("table.pending");
                else if (s.equals("In Progress") || s.equals("В процессе")) displayValue = Localization.get("table.in_progress");
            }
            
            super.getTableCellRendererComponent(table, displayValue, isSelected, hasFocus, row, column);
            setBackground(row % 2 == 0 ? ColorScheme.CARD_BACKGROUND : ColorScheme.TABLE_ROW_ALT);
            setForeground(ColorScheme.PRIMARY_TEXT);
            setFont(new Font("Arial", Font.PLAIN, 13));
            setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            setHorizontalAlignment(SwingConstants.CENTER);
            return this;
        }
    }
}
