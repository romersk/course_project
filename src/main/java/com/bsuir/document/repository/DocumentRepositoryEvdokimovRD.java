package com.bsuir.document.repository;

import com.bsuir.document.entity.DocumentEvdokimovRD;
import com.bsuir.shared.persitance.EntityCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepositoryEvdokimovRD extends EntityCrudRepository<DocumentEvdokimovRD, Long> {
}
