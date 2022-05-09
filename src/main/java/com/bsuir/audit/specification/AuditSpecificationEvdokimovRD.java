package com.bsuir.audit.specification;

import com.bsuir.audit.entity.AuditEvdokimovRD;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AuditSpecificationEvdokimovRD {

    public AuditSpecificationEvdokimovRD() {
    }

    public static Specification<AuditEvdokimovRD> getQueryAll() {
        return new Specification<AuditEvdokimovRD>() {
            public Predicate toPredicate(Root<AuditEvdokimovRD> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.greaterThan(root.get("id"),0);
            }
        };
    }
}
