package com.example.Spring_Transaction_Example.Repository;

import com.example.Spring_Transaction_Example.Entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog,Integer> {

}
