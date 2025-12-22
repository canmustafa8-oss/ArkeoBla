package com.arkeobla.model;

public enum Role {
    OGRENCI("Öğrenci"),
    AKADEMISYEN("Akademisyen"),
    ARASTIRMACI("Araştırmacı"),
    ADMIN("Admin");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
