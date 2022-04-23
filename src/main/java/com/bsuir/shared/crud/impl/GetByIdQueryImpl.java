package com.bsuir.shared.crud.impl;

import com.bsuir.shared.crud.GetByIdQuery;
import com.bsuir.shared.exception.EntityNotFoundException;
import com.bsuir.shared.mapping.DtoPresenter;
import com.bsuir.shared.persitance.EntityCrudRepository;

public class GetByIdQueryImpl<E, D, I> implements GetByIdQuery<E, D, I> {

    private final EntityCrudRepository<E, I> repository;
    private final DtoPresenter<D, E> dtoPresenter;

    public GetByIdQueryImpl(EntityCrudRepository<E, I> repository,
                            DtoPresenter<D, E> dtoPresenter) {
        this.repository = repository;
        this.dtoPresenter = dtoPresenter;
    }

    @Override
    public D getById(I id) throws EntityNotFoundException {
        try {
            E entity = repository.getById(id);
            return dtoPresenter.map(entity);
        } catch (javax.persistence.EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }
}
