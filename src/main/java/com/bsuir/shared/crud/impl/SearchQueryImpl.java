package com.bsuir.shared.crud.impl;

import com.bsuir.shared.crud.SearchQuery;
import com.bsuir.shared.exception.EntityNotFoundException;
import com.bsuir.shared.mapping.DtoPresenter;
import com.bsuir.shared.persitance.EntityCrudRepository;
import com.bsuir.shared.search.Page;
import com.bsuir.shared.search.Paging;
import com.bsuir.shared.search.impl.PageImpl;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

public class SearchQueryImpl<E, D> implements SearchQuery<E, D> {

    private final EntityCrudRepository<E, ?> repository;
    private final DtoPresenter<D, E> presenter;

    public SearchQueryImpl(EntityCrudRepository<E, ?> repository, DtoPresenter<D, E> presenter) {
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    public Page<D> search(Specification<E> specification, Paging paging) {
        PageRequest pageRequest = PageRequest.of(paging.getPageNumber(), paging.getPerPageCount());
        org.springframework.data.domain.Page<E> pageOfEntities = repository.findAll(specification,
                pageRequest);
        List<E> listOfEntities = pageOfEntities.getContent();

        if (listOfEntities.isEmpty()) {
            throw new EntityNotFoundException("no_entities_for_page");
        }

        List<D> listOfDto = presenter.mapList(listOfEntities);
        return new PageImpl<D>(
                listOfDto,
                paging.getPageNumber(),
                (int) pageOfEntities.getTotalElements(),
                pageOfEntities.getTotalPages());
    }
}
