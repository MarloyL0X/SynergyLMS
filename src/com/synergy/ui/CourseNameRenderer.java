package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class CourseNameRenderer extends DefaultTableCellRenderer {
    
    public static final int STATUS_PASSED = 0;
    public static final int STATUS_FAILED = 1;
    public static final int STATUS_PENDING = 2;

    @Override
    public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
        super.getTableCellRendererComponent(t, v, s, f, r, c);
        
        int status;
        if (r == 0) status = STATUS_PASSED;
        else if (r == 1) status = STATUS_PASSED;
        else status = STATUS_PENDING;
        
        setIcon(new StatusDotIcon(status));
        setIconTextGap(15);
        
        setBackground(r % 2 == 0 ? ColorScheme.CARD_BACKGROUND : ColorScheme.TABLE_ROW_ALT);
        setForeground(ColorScheme.PRIMARY_TEXT);
        setFont(new Font("Arial", Font.PLAIN, 13));
        
        // FIXED: Center alignment to match header and other columns
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        setHorizontalAlignment(SwingConstants.CENTER);
        // Ensure text is centered relative to icon as well
        setHorizontalTextPosition(SwingConstants.RIGHT);
        
        return this;
    }

    private static class StatusDotIcon implements javax.swing.Icon {
        private final int status;
        private final int size = 10;

        public StatusDotIcon(int status) {
            this.status = status;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            Color color;
            switch (status) {
                case STATUS_PASSED: color = new Color(76, 175, 80); break;
                case STATUS_FAILED: color = new Color(229, 115, 115); break;
                default: color = new Color(189, 189, 189); break;
            }
            
            g2.setColor(color);
            g2.fillOval(x, y + (getIconHeight() - size) / 2, size, size);
            g2.dispose();
        }

        @Override
        public int getIconWidth() { return size; }
        @Override
        public int getIconHeight() { return 20; }
    }
}
