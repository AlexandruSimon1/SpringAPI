package com.application.model.enums;

public enum Permission {
    Developers_Read("developers:read"),
    Developers_Write("developers:write");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
