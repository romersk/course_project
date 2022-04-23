package com.bsuir.users.specification;

import com.bsuir.users.entity.UserEvdokimovRD;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class UsersSpecificationsEvdokimovRD {

    private UsersSpecificationsEvdokimovRD() {}

    public static Specification<UserEvdokimovRD> getQueryAll() {
        return new Specification<UserEvdokimovRD>() {
            public Predicate toPredicate(Root<UserEvdokimovRD> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.greaterThan(root.get("id"),0);
            }
        };
    }
}
