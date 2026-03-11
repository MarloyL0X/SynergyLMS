package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import com.synergy.utils.Localization;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// TODO: Implement full functionality for the electronic journal
public class ElectronicJournalView extends JPanel {

    public ElectronicJournalView() {
        setLayout(new BorderLayout(0, 20));
        setOpaque(false);
        setBorder(new EmptyBorder(30, 40, 30, 40));
        initializeUI();
    }

    private void initializeUI() {
        add(createHeader(), BorderLayout.NORTH);
        add(createJournalTable(), BorderLayout.CENTER);
    }

    private JPanel createHeader() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel titleLabel = new JLabel("Электронный журнал"); // TODO: Localize
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(ColorScheme.PRIMARY_TEXT);

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(createFilterPanel(), BorderLayout.EAST);
        return headerPanel;
    }

    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        filterPanel.setOpaque(false);
        
        filterPanel.add(new JLabel("Класс:")); // TODO: Localize
        String[] classOptions = {"5-А", "5-Б", "6-А"}; // Mock data
        JComboBox<String> classCombo = new JComboBox<>(classOptions);
        filterPanel.add(classCombo);
        
        filterPanel.add(new JLabel("Предмет:")); // TODO: Localize
        String[] subjectOptions = {"Математика", "История", "Информатика"}; // Mock data
        JComboBox<String> subjectCombo = new JComboBox<>(subjectOptions);
        filterPanel.add(subjectCombo);

        return filterPanel;
    }

    private JScrollPane createJournalTable() {
        String[] columns = {"№", "Ученик", "01.09", "02.09", "03.09", "04.09", "05.09"}; // Mock data
        Object[][] data = {
            {"1", "Александров П.", "5", "4", "", "5", "Н"},
            {"2", "Борисова Е.", "4", "5", "5", "", "5"},
            {"3", "Васильев И.", "", "3", "4", "4", "4"},
            {"4", "Григорьева А.", "5", "5", "5", "5", "5"}
        }; // Mock data

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        
        // Basic table configuration
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setBackground(ColorScheme.TOP_BAR_BG);
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        return scrollPane;
    }
}
