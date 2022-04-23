package com.bsuir.users.repository;

import com.bsuir.shared.persitance.EntityCrudRepository;
import com.bsuir.users.entity.UserEvdokimovRD;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryEvdokimovRD extends EntityCrudRepository<UserEvdokimovRD, Long> {

    UserEvdokimovRD findUserByUserName(String name);
}
