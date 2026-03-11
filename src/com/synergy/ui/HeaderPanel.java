package com.synergy.ui;

import com.synergy.auth.UserSession;
import com.synergy.utils.ColorScheme;
import com.synergy.utils.FontManager;
import com.synergy.utils.Localization;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HeaderPanel extends JPanel {
    private Consumer<String> navigationListener;
    private final List<MenuItem> menuItems = new ArrayList<>();
    private TabStrip tabStrip;
    
    public HeaderPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.TOP_BAR_BG);
        setPreferredSize(new Dimension(1080, 80));
        
        refresh();
    }
    
    public void refresh() {
        removeAll();
        menuItems.clear();
        
        add(createLogo(), BorderLayout.WEST);
        add(createMenu(), BorderLayout.CENTER);
        add(createActions(), BorderLayout.EAST);
        
        revalidate();
        repaint();
    }
    
    public void setNavigationListener(Consumer<String> listener) {
        this.navigationListener = listener;
    }

    private JPanel createLogo() {
        JPanel logoPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 20));
        logoPanel.setOpaque(false);
        JLabel logoLabel = new JLabel(Localization.get("app.title"));
        logoLabel.setIcon(SynergyIcons.getLogoIcon(24));
        logoLabel.setFont(FontManager.getBoldFont(16));
        logoLabel.setForeground(ColorScheme.PRIMARY_TEXT);
        logoLabel.setIconTextGap(12);
        logoPanel.add(logoLabel);
        return logoPanel;
    }

    private JPanel createMenu() {
        JPanel wrapper = new JPanel(new java.awt.GridBagLayout());
        wrapper.setOpaque(false);
        
        tabStrip = new TabStrip();
        tabStrip.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 10));
        
        addMenuItem(tabStrip, Localization.get("menu.courses"), SynergyIcons.getGridIcon(20, true), SynergyIcons.getGridIcon(20, false), true);
        addMenuItem(tabStrip, Localization.get("menu.schedule"), SynergyIcons.getCalendarIcon(20, true), SynergyIcons.getCalendarIcon(20, false), false);
        addMenuItem(tabStrip, Localization.get("menu.payments"), SynergyIcons.getBillIcon(20, true), SynergyIcons.getBillIcon(20, false), false);
        addMenuItem(tabStrip, Localization.get("menu.profile"), SynergyIcons.getDocumentIcon(20, true), SynergyIcons.getDocumentIcon(20, false), false);
        
        wrapper.add(tabStrip);
        return wrapper;
    }
    
    private void addMenuItem(JPanel parent, String text, Icon activeIcon, Icon inactiveIcon, boolean active) {
        MenuItem item = new MenuItem(text, activeIcon, inactiveIcon, active);
        menuItems.add(item);
        parent.add(item);
        
        if (active) {
            javax.swing.SwingUtilities.invokeLater(() -> {
                if (tabStrip != null) tabStrip.setActiveItem(item);
            });
        }
    }

    private JPanel createActions() {
        JPanel actionsPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 15, 10));
        actionsPanel.setOpaque(false);
        
        actionsPanel.add(new UserProfileBadge());
        
        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        spacer.setPreferredSize(new Dimension(10, 1));
        actionsPanel.add(spacer);
        
        PillButton langButton = new PillButton("Ru / En", true);
        langButton.setPreferredSize(new Dimension(100, 36));
        langButton.addActionListener(e -> Localization.toggleLanguage());
        
        actionsPanel.add(langButton);
        return actionsPanel;
    }

    private class MenuItem extends JPanel {
        private final String text;
        private final Icon iconActive;
        private final Icon iconInactive;
        private final JLabel label;
        private boolean active;

        public MenuItem(String text, Icon iconActive, Icon iconInactive, boolean active) {
            this.text = text;
            this.iconActive = iconActive;
            this.iconInactive = iconInactive;
            this.active = active;

            setLayout(new BorderLayout());
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            label = new JLabel(text, active ? iconActive : iconInactive, SwingConstants.CENTER);
            label.setVerticalTextPosition(SwingConstants.BOTTOM);
            label.setHorizontalTextPosition(SwingConstants.CENTER);
            label.setForeground(active ? ColorScheme.ICON_ACTIVE : ColorScheme.ICON_INACTIVE);
            label.setFont(FontManager.getRegularFont(11));
            
            label.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 5, 0));
            
            add(label, BorderLayout.CENTER);

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { 
                    if (!MenuItem.this.active) label.setForeground(ColorScheme.ICON_ACTIVE); 
                }
                public void mouseExited(MouseEvent e) { 
                    if (!MenuItem.this.active) label.setForeground(ColorScheme.ICON_INACTIVE); 
                }
                public void mouseClicked(MouseEvent e) {
                    handleMenuClick(MenuItem.this);
                }
            });
        }

        public void setActive(boolean isActive) {
            this.active = isActive;
            label.setIcon(isActive ? iconActive : iconInactive);
            label.setForeground(isActive ? ColorScheme.ICON_ACTIVE : ColorScheme.ICON_INACTIVE);
            repaint();
        }

        public String getText() { return text; }
    }
    
    private void handleMenuClick(MenuItem item) {
        for (MenuItem menuItem : menuItems) {
            menuItem.setActive(false);
        }
        item.setActive(true);
        
        if (tabStrip != null) {
            tabStrip.setActiveItem(item);
        }
        
        if (navigationListener != null) {
            String key = mapTextToKey(item.getText());
            navigationListener.accept(key);
        }
    }

    private String mapTextToKey(String text) {
        if (text.equals(Localization.get("menu.courses"))) return "Courses";
        if (text.equals(Localization.get("menu.schedule"))) return "Schedule";
        if (text.equals(Localization.get("menu.payments"))) return "Payments";
        if (text.equals(Localization.get("menu.profile"))) return "Profile";
        return text;
    }
}
