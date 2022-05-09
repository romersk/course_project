package com.bsuir.audit.service;

import com.bsuir.audit.dto.AuditDtoEvdokimovRD;
import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.audit.factory.AuditCommandFactoryEvdokimovRD;
import com.bsuir.audit.request.AuditRequestEvdokimovRD;
import com.bsuir.audit.specification.AuditSpecificationEvdokimovRD;
import com.bsuir.shared.search.Page;
import com.bsuir.shared.search.Paging;
import com.bsuir.shared.search.impl.PagingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceEvdokimovRD {

    @Autowired
    private AuditCommandFactoryEvdokimovRD commandFactory;

    public AuditDtoEvdokimovRD create(final AuditRequestEvdokimovRD request) {
        final var command = commandFactory.createCommand();
        return command.create(request);
    }

    public AuditDtoEvdokimovRD getById(final long id) {
        final var command = commandFactory.getByIdQuery();
        return command.getById(id);
    }

    public void delete(final long id) {
        final var command = commandFactory.deleteCommand();
        command.delete(id);
    }

    public Page<AuditDtoEvdokimovRD> search(final Specification<AuditEvdokimovRD> specification, Paging paging) {
        final var command = commandFactory.searchQuery();
        return command.search(specification, paging);
    }

    public AuditDtoEvdokimovRD update(final AuditRequestEvdokimovRD updateRequest, final long id) {
        final var command = commandFactory.updateCommand();
        return command.update(updateRequest, id);
    }

    public Page<AuditDtoEvdokimovRD> getAll(Pageable pageable) {
        final var command = commandFactory.searchQuery();
        Page<AuditDtoEvdokimovRD> page = command.search(AuditSpecificationEvdokimovRD.getQueryAll(), new PagingImpl(pageable.getPageNumber(), pageable.getPageSize()));
        return page;
    }
}
