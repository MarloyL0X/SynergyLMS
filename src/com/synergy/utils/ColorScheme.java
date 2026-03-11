package com.synergy.utils;

import java.awt.Color;

public class ColorScheme {
    // New School-themed Palette
    public static final Color APP_BACKGROUND = new Color(240, 242, 245); // Light gray
    public static final Color CARD_BACKGROUND = Color.WHITE;
    public static final Color TOP_BAR_BG = Color.WHITE; // Clean white top bar

    public static final Color SIDEBAR_BG = new Color(35, 37, 41);
    public static final Color PRIMARY_BLUE = new Color(0, 122, 255); // Apple's classic blue
    public static final Color PRIMARY_TEXT = new Color(28, 28, 30); // Near-black for readability
    public static final Color SECONDARY_TEXT = new Color(142, 142, 147); // Gray for secondary info

    public static final Color PROGRESS_TRACK = new Color(229, 229, 234);
    public static final Color TABLE_HEADER_TEXT = new Color(99, 99, 102); // Darker gray for headers
    public static final Color TABLE_ROW_ALT = new Color(248, 249, 250);

    public static final Color ICON_INACTIVE = new Color(174, 174, 178);
    public static final Color ICON_ACTIVE = PRIMARY_BLUE;

    public static final Color SHADOW_COLOR = new Color(0, 0, 0, 10);
    
    // Legacy color, to be phased out
    public static final Color PRIMARY_RED = PRIMARY_BLUE;
}
