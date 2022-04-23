package com.bsuir.users.service;

import com.bsuir.shared.search.Page;
import com.bsuir.shared.search.Paging;
import com.bsuir.shared.search.impl.PagingImpl;
import com.bsuir.users.dto.UsersDtoEvdokimovRD;
import com.bsuir.users.entity.UserEvdokimovRD;
import com.bsuir.users.factory.UserCommandFactoryEvdokimovRD;
import com.bsuir.users.repository.UserRepositoryEvdokimovRD;
import com.bsuir.users.request.UserRequestEvdokimovRD;
import com.bsuir.users.specification.UsersSpecificationsEvdokimovRD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceEvdokimovRD implements UserDetailsService {

    @Autowired
    private UserRepositoryEvdokimovRD repository;

    @Autowired
    private UserCommandFactoryEvdokimovRD commandFactory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEvdokimovRD user = repository.findUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден с именем + " + username);
        }
        return user;
    }

    public UsersDtoEvdokimovRD create(final UserRequestEvdokimovRD request) {
        final var command = commandFactory.createCommand();
        return command.create(request);
    }

    public UsersDtoEvdokimovRD getById(final long id) {
        final var command = commandFactory.getByIdQuery();
        return command.getById(id);
    }

    public void delete(final long id) {
        final var command = commandFactory.deleteCommand();
        command.delete(id);
    }

    public Page<UsersDtoEvdokimovRD> search(final Specification<UserEvdokimovRD> specification, Paging paging) {
        final var command = commandFactory.searchQuery();
        return command.search(specification, paging);
    }

    public UsersDtoEvdokimovRD update(final UserRequestEvdokimovRD updateRequest, final long id) {
        final var command = commandFactory.updateCommand();
        return command.update(updateRequest, id);
    }

    public Page<UsersDtoEvdokimovRD> getAll(Pageable pageable) {
        final var command = commandFactory.searchQuery();
        Page<UsersDtoEvdokimovRD> page = command.search(UsersSpecificationsEvdokimovRD.getQueryAll(), new PagingImpl(pageable.getPageNumber(), pageable.getPageSize()));
        //Collections.sort(page.getResult());
        return page;
    }
}
