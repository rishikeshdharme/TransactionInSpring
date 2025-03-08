package com.example.Spring_Transaction_Example.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="Auditlog")

public class AuditLog
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private Integer orgId;
    private String action;
    private LocalDateTime timeStamp;

    public AuditLog()
    {
        this.timeStamp = LocalDateTime.now();
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
