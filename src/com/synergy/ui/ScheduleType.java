package com.synergy.ui;

import com.synergy.utils.Localization;
import java.awt.Color;

public enum ScheduleType {
    LECTURE("schedule.type.lecture", new Color(33, 150, 243)), // Blue
    PRACTICE("schedule.type.practice", new Color(76, 175, 80)), // Green
    SEMINAR("schedule.type.seminar", new Color(156, 39, 176)), // Purple
    EXAM("schedule.type.exam", new Color(244, 67, 54)); // Red

    private final String key;
    private final Color color;

    ScheduleType(String key, Color color) {
        this.key = key;
        this.color = color;
    }

    public String getDisplayName() {
        return Localization.get(key);
    }

    public Color getColor() {
        return color;
    }
}
