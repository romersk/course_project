package com.bsuir.document.specification;

import com.bsuir.document.entity.DocumentEvdokimovRD;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DocumentSpecificationEvdokimovRD {

    public DocumentSpecificationEvdokimovRD() {
    }

    public static Specification<DocumentEvdokimovRD> getQueryAll() {
        return new Specification<DocumentEvdokimovRD>() {
            public Predicate toPredicate(Root<DocumentEvdokimovRD> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.greaterThan(root.get("id"),0);
            }
        };
    }
}
