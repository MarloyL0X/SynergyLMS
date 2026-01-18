package com.synergy.auth;

public class UserSession {
    private static UserSession instance;
    private String fullName;
    private String group;
    private String email;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) instance = new UserSession();
        return instance;
    }

    public void login(String fullName, String group, String email) {
        this.fullName = fullName;
        this.group = group;
        this.email = email;
    }

    public String getFullName() { return fullName; }
    public String getGroup() { return group; }
    public String getEmail() { return email; }
    public String getInitials() {
        if (fullName == null || fullName.isEmpty()) return "U";
        String[] parts = fullName.split(" ");
        if (parts.length >= 2) return (parts[0].substring(0, 1) + parts[1].substring(0, 1)).toUpperCase();
        return fullName.substring(0, 1).toUpperCase();
    }
}
