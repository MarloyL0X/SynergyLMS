package com.synergy.utils;

import java.awt.Font;

public class FontManager {

    public static final String PRIMARY_FONT_NAME = "SansSerif";

    public static Font getPrimaryFont(int style, float size) {
        return new Font(PRIMARY_FONT_NAME, style, (int)size);
    }

    public static Font getBoldFont(float size) {
        return getPrimaryFont(Font.BOLD, size);
    }

    public static Font getRegularFont(float size) {
        return getPrimaryFont(Font.PLAIN, size);
    }
}
