package com.bsuir.document.service;

import com.bsuir.document.dto.DocumentDtoEvdokimovRD;
import com.bsuir.document.entity.DocumentEvdokimovRD;
import com.bsuir.document.factory.DocumentCommandFactoryEvdokimovRD;
import com.bsuir.document.request.DocumentRequestEvdokimovRD;
import com.bsuir.document.specification.DocumentSpecificationEvdokimovRD;
import com.bsuir.shared.search.Page;
import com.bsuir.shared.search.Paging;
import com.bsuir.shared.search.impl.PagingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceEvdokimovRD {

    @Autowired
    private DocumentCommandFactoryEvdokimovRD commandFactory;

    public DocumentDtoEvdokimovRD create(final DocumentRequestEvdokimovRD request) {
        final var command = commandFactory.createCommand();
        return command.create(request);
    }

    public DocumentDtoEvdokimovRD getById(final long id) {
        final var command = commandFactory.getByIdQuery();
        return command.getById(id);
    }

    public void delete(final long id) {
        final var command = commandFactory.deleteCommand();
        command.delete(id);
    }

    public Page<DocumentDtoEvdokimovRD> search(final Specification<DocumentEvdokimovRD> specification, Paging paging) {
        final var command = commandFactory.searchQuery();
        return command.search(specification, paging);
    }

    public DocumentDtoEvdokimovRD update(final DocumentRequestEvdokimovRD updateRequest, final long id) {
        final var command = commandFactory.updateCommand();
        return command.update(updateRequest, id);
    }

    public Page<DocumentDtoEvdokimovRD> getAll(Pageable pageable) {
        final var command = commandFactory.searchQuery();
        Page<DocumentDtoEvdokimovRD> page = command.search(DocumentSpecificationEvdokimovRD.getQueryAll(), new PagingImpl(pageable.getPageNumber(), pageable.getPageSize()));
        return page;
    }
}
