package com.master.report.batch.model;

public class AuditReportItem {

    private Integer id;
    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AuditReportItem{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                '}';
    }
}