package com.synergy.model;

import java.util.Date;

public class Grade {
    private String id;
    private Pupil pupil;
    private Subject subject;
    private String value; // e.g., "5", "4+", "Н" (не был)
    private Date date;
}
