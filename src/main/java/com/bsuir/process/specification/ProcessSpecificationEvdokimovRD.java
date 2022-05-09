package com.bsuir.process.specification;

import com.bsuir.process.entity.ProcessEvdokimovRD;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProcessSpecificationEvdokimovRD {

    public ProcessSpecificationEvdokimovRD() {
    }

    public static Specification<ProcessEvdokimovRD> getQueryAll() {
        return new Specification<ProcessEvdokimovRD>() {
            public Predicate toPredicate(Root<ProcessEvdokimovRD> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.greaterThan(root.get("id"),0);
            }
        };
    }

    public static Specification<ProcessEvdokimovRD> getQueryById(Long id) {
        return new Specification<ProcessEvdokimovRD>() {
            public Predicate toPredicate(Root<ProcessEvdokimovRD> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.equal(root.get("startId"), id);
            }
        };
    }
}
