package com.example.Spring_Transaction_Example.Handler;

import com.example.Spring_Transaction_Example.Entity.AuditLog;
import com.example.Spring_Transaction_Example.Entity.Order;
import com.example.Spring_Transaction_Example.Repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class AuditLogHandler {

    @Autowired
    private  AuditLogRepository auditLogRepository;

    public AuditLogHandler(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logAuditDetails(Order order, String action)
    {
        AuditLog auditLog = new AuditLog();
        auditLog.setOrgId(order.getId());
        auditLog.setAction(action);
        auditLog.setTimeStamp(LocalDateTime.now());

        auditLogRepository.save(auditLog);
    }

}
