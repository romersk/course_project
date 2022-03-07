package com.bsuir.repositories;

import com.bsuir.entities.UserEvdokimovRD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryEvdokimovRD extends JpaRepository<UserEvdokimovRD, Long> {

    UserEvdokimovRD findUserByUserName(String name);
}
