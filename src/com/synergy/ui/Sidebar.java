package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import com.synergy.utils.Localization;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Sidebar extends JPanel {
    private final List<NavItem> navItems = new ArrayList<>();
    private Consumer<String> navigationListener;
    private final JPanel navItemsPanel;

    // Inner class to hold navigation item data
    private static class NavItem {
        final String viewKey;
        final String locKey;
        final Icon icon;
        SidebarButton button;

        NavItem(String viewKey, String locKey, Icon icon) {
            this.viewKey = viewKey;
            this.locKey = locKey;
            this.icon = icon;
        }
    }

    public Sidebar() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.SIDEBAR_BG);
        setPreferredSize(new Dimension(240, 0));
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        add(createLogo(), BorderLayout.NORTH);
        
        navItemsPanel = new JPanel();
        navItemsPanel.setLayout(new BoxLayout(navItemsPanel, BoxLayout.Y_AXIS));
        navItemsPanel.setOpaque(false);
        navItemsPanel.setBorder(BorderFactory.createEmptyBorder(30, 15, 0, 15));
        
        add(navItemsPanel, BorderLayout.CENTER);
    }

    public void addNavItem(String viewKey, String locKey, Icon icon) {
        navItems.add(new NavItem(viewKey, locKey, icon));
    }

    public void buildNav() {
        navItemsPanel.removeAll();
        for (NavItem item : navItems) {
            item.button = new SidebarButton(Localization.get(item.locKey), item.icon);
            item.button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (navigationListener != null) {
                        navigationListener.accept(item.viewKey);
                    }
                }
            });
            navItemsPanel.add(item.button);
            navItemsPanel.add(Box.createVerticalStrut(10));
        }
        navItemsPanel.revalidate();
        navItemsPanel.repaint();
    }

    public void setNavigationListener(Consumer<String> listener) {
        this.navigationListener = listener;
    }

    public void refresh() {
        buildNav(); // Re-build all items with new localization
    }

    private JPanel createLogo() {
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        logoPanel.setOpaque(false);
        JLabel logoLabel = new JLabel("ПЕРСПЕКТИВА"); // Placeholder
        logoLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        logoLabel.setForeground(Color.WHITE);
        logoPanel.add(logoLabel);
        return logoPanel;
    }
}

class SidebarButton extends JPanel {
    private final JLabel label;
    private boolean isHovered = false;

    public SidebarButton(String text, Icon icon) {
        setLayout(new BorderLayout());
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        label = new JLabel(text);
        label.setIcon(icon);
        label.setIconTextGap(15);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(new Color(200, 200, 200));
        add(label, BorderLayout.CENTER);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                isHovered = true;
                label.setForeground(Color.WHITE);
                repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                isHovered = false;
                label.setForeground(new Color(200, 200, 200));
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isHovered) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(255, 255, 255, 20));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.dispose();
        }
    }
}