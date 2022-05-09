package com.bsuir.audit.repository;

import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.shared.persitance.EntityCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepositoryEvdokimovRD extends EntityCrudRepository<AuditEvdokimovRD, Long> {
}
