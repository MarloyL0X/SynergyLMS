package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import com.synergy.utils.FontManager;
import com.synergy.utils.Localization;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class PaymentsView extends JPanel {

    public PaymentsView() {
        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(new EmptyBorder(30, 40, 30, 40));

        add(createTopSection(), BorderLayout.NORTH);
        add(createContentSection(), BorderLayout.CENTER);
    }

    private JPanel createTopSection() {
        JPanel panel = new JPanel(new BorderLayout(0, 20));
        panel.setOpaque(false);

        JLabel title = new JLabel(Localization.get("payments.title"));
        title.setFont(FontManager.getBoldFont(24));
        title.setForeground(ColorScheme.PRIMARY_TEXT);
        panel.add(title, BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(1, 3, 25, 0));
        cards.setOpaque(false);
        cards.setPreferredSize(new Dimension(0, 120));

        cards.add(createSummaryCard(Localization.get("payments.paid"), "$12,500", SynergyIcons.getBillIcon(24, true)));
        cards.add(createSummaryCard(Localization.get("payments.due"), "$2,500", SynergyIcons.getCalendarIcon(24, false)));
        cards.add(createSummaryCard(Localization.get("payments.outstanding"), "$2,500", SynergyIcons.getAlertIcon(24)));

        panel.add(cards, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createSummaryCard(String title, String value, Icon icon) {
        RoundedPanel card = new RoundedPanel(16, true);
        card.setBackground(Color.WHITE);
        card.setLayout(new GridBagLayout());
        card.setBorder(new EmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();

        // Icon
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 0, 15);
        JLabel iconLabel = new JLabel(icon);
        card.add(iconLabel, gbc);

        // Title
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(0, 0, 0, 0);
        JLabel titleLbl = new JLabel(title);
        titleLbl.setFont(FontManager.getBoldFont(14));
        titleLbl.setForeground(ColorScheme.SECONDARY_TEXT);
        card.add(titleLbl, gbc);

        // Value
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        JLabel valueLbl = new JLabel(value);
        valueLbl.setFont(FontManager.getBoldFont(28));
        valueLbl.setForeground(ColorScheme.PRIMARY_TEXT);
        card.add(valueLbl, gbc);

        return card;
    }

    private JPanel createContentSection() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(30, 0, 0, 0));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel listTitle = new JLabel(Localization.get("payments.history"));
        listTitle.setFont(FontManager.getBoldFont(18));
        listTitle.setForeground(ColorScheme.PRIMARY_TEXT);
        
        PillButton payButton = new PillButton(Localization.get("payments.make_payment"), true);
        payButton.setPreferredSize(new Dimension(140, 40));
        payButton.addActionListener(e -> showPaymentDialog());

        header.add(listTitle, BorderLayout.WEST);
        header.add(payButton, BorderLayout.EAST);
        panel.add(header, BorderLayout.NORTH);

        JPanel listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        listContainer.setOpaque(false);

        listContainer.add(createPaymentRow(Localization.get("pay.spring2026"), Localization.get("pay.due_feb20"), "$2,500.00", Localization.get("payments.pending"), new Color(241, 196, 15)));
        listContainer.add(Box.createVerticalStrut(10));
        listContainer.add(createPaymentRow(Localization.get("pay.fall2025"), Localization.get("pay.paid_sep10"), "$5,000.00", Localization.get("payments.status_paid"), new Color(46, 204, 113)));
        listContainer.add(Box.createVerticalStrut(10));
        listContainer.add(createPaymentRow(Localization.get("pay.books2025"), Localization.get("pay.paid_sep05"), "$450.00", Localization.get("payments.status_paid"), new Color(46, 204, 113)));
        listContainer.add(Box.createVerticalStrut(10));
        listContainer.add(createPaymentRow(Localization.get("pay.spring2025"), Localization.get("pay.paid_feb15"), "$5,000.00", Localization.get("payments.status_paid"), new Color(46, 204, 113)));
        listContainer.add(Box.createVerticalStrut(10));
        listContainer.add(createPaymentRow(Localization.get("pay.winter2024"), Localization.get("pay.overdue_jan"), "$150.00", Localization.get("payments.overdue"), ColorScheme.PRIMARY_RED));

        JScrollPane scroll = new JScrollPane(listContainer);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPaymentRow(String title, String date, String amount, String status, Color statusColor) {
        RoundedPanel row = new RoundedPanel(12, true);
        row.setBackground(Color.WHITE);
        row.setLayout(new GridBagLayout());
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        row.setPreferredSize(new Dimension(0, 80));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;

        // Status Circle
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.insets = new Insets(0, 0, 0, 15);
        JPanel statusCircle = new JPanel();
        statusCircle.setBackground(statusColor);
        statusCircle.setPreferredSize(new Dimension(10, 10));
        row.add(statusCircle, gbc);

        // Info Panel
        JPanel info = new JPanel(new GridLayout(2, 1, 0, 2));
        info.setOpaque(false);
        JLabel t = new JLabel(title);
        t.setFont(FontManager.getBoldFont(15));
        t.setForeground(ColorScheme.PRIMARY_TEXT);
        JLabel d = new JLabel(date);
        d.setFont(FontManager.getRegularFont(12));
        d.setForeground(ColorScheme.SECONDARY_TEXT);
        info.add(t);
        info.add(d);
        
        gbc.gridx = 1;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 0, 0);
        row.add(info, gbc);

        // Amount
        JLabel amt = new JLabel(amount);
        amt.setFont(FontManager.getBoldFont(16));
        amt.setForeground(ColorScheme.PRIMARY_TEXT);
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 20, 0, 20);
        row.add(amt, gbc);

        // Status Text
        JLabel statusLabel = new JLabel(status);
        statusLabel.setFont(FontManager.getBoldFont(12));
        statusLabel.setForeground(statusColor);
        gbc.gridx = 3;
        row.add(statusLabel, gbc);

        return row;
    }
    
    private void showPaymentDialog() {
        JOptionPane.showMessageDialog(this, "Payment Gateway Integration Pending.\n(Mock: Payment Processed Successfully!)", "Make Payment", JOptionPane.INFORMATION_MESSAGE);
    }
}
