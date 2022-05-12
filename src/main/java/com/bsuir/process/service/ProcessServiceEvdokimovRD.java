package com.bsuir.process.service;

import com.bsuir.process.dto.ProcessDtoEvdokimovRD;
import com.bsuir.process.entity.ProcessEvdokimovRD;
import com.bsuir.process.factory.ProcessCommandFactoryEvdokimovRD;
import com.bsuir.process.request.ProcessRequestEvdokimovRD;
import com.bsuir.process.specification.ProcessSpecificationEvdokimovRD;
import com.bsuir.shared.search.Page;
import com.bsuir.shared.search.Paging;
import com.bsuir.shared.search.impl.PagingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessServiceEvdokimovRD {

    @Autowired
    private ProcessCommandFactoryEvdokimovRD commandFactory;

    public ProcessDtoEvdokimovRD create(final ProcessRequestEvdokimovRD request) {
        final var command = commandFactory.createCommand();
        return command.create(request);
    }

    public ProcessDtoEvdokimovRD getById(final long id) {
        final var command = commandFactory.getByIdQuery();
        return command.getById(id);
    }

    public void delete(final long id) {
        final var command = commandFactory.deleteCommand();
        command.delete(id);
    }

    public Page<ProcessDtoEvdokimovRD> search(final Specification<ProcessEvdokimovRD> specification, Paging paging) {
        final var command = commandFactory.searchQuery();
        return command.search(specification, paging);
    }

    public ProcessDtoEvdokimovRD update(final ProcessRequestEvdokimovRD updateRequest, final long id) {
        final var command = commandFactory.updateCommand();
        return command.update(updateRequest, id);
    }

    public Page<ProcessDtoEvdokimovRD> getAll(Pageable pageable) {
        final var command = commandFactory.searchQuery();
        Page<ProcessDtoEvdokimovRD> page = command.search(ProcessSpecificationEvdokimovRD.getQueryAll(), new PagingImpl(pageable.getPageNumber(), pageable.getPageSize()));
        return page;
    }

    public Page<ProcessDtoEvdokimovRD> getById(Pageable pageable, Long id) {
        final var command = commandFactory.searchQuery();
        Page<ProcessDtoEvdokimovRD> page = command.search(ProcessSpecificationEvdokimovRD.getQueryById(id), new PagingImpl(pageable.getPageNumber(), pageable.getPageSize()));
        return page;
    }

    public List<ProcessDtoEvdokimovRD> getByStageOne(Pageable pageable) {
        final var command = commandFactory.searchQuery();
        Page<ProcessDtoEvdokimovRD> page = command.search(ProcessSpecificationEvdokimovRD.getQueryByStageOne(), new PagingImpl(pageable.getPageNumber(), pageable.getPageSize()));
        return page.getResult();
    }

    public List<ProcessDtoEvdokimovRD> getByStageTwo(Pageable pageable) {
        final var command = commandFactory.searchQuery();
        Page<ProcessDtoEvdokimovRD> page = command.search(ProcessSpecificationEvdokimovRD.getQueryByStageTwo(), new PagingImpl(pageable.getPageNumber(), pageable.getPageSize()));
        return page.getResult();
    }

    public List<ProcessDtoEvdokimovRD> getByStageThree(Pageable pageable) {
        final var command = commandFactory.searchQuery();
        Page<ProcessDtoEvdokimovRD> page = command.search(ProcessSpecificationEvdokimovRD.getQueryByStageThree(), new PagingImpl(pageable.getPageNumber(), pageable.getPageSize()));
        return page.getResult();
    }

    public List<ProcessDtoEvdokimovRD> getByStageFour(Pageable pageable) {
        final var command = commandFactory.searchQuery();
        Page<ProcessDtoEvdokimovRD> page = command.search(ProcessSpecificationEvdokimovRD.getQueryByStageFour(), new PagingImpl(pageable.getPageNumber(), pageable.getPageSize()));
        return page.getResult();
    }

    public Page<ProcessDtoEvdokimovRD> getByIdUser(Pageable pageable, Long id) {
        final var command = commandFactory.searchQuery();
        Page<ProcessDtoEvdokimovRD> page = command.search(ProcessSpecificationEvdokimovRD.getQueryByIdUser(id), new PagingImpl(pageable.getPageNumber(), pageable.getPageSize()));
        return page;
    }
}
