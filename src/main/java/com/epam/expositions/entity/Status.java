package com.epam.expositions.entity;

public enum Status {
    VISITED("visited"), PURCHASED("purchased"), REFUNDED("refunded"), CANCELED("canceled");
    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
