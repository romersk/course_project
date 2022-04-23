package com.bsuir.shared.crud.impl;

import com.bsuir.shared.crud.DeleteCommand;
import com.bsuir.shared.exception.EntityNotFoundException;
import com.bsuir.shared.persitance.EntityCrudRepository;

public class DeleteCommandImpl<E, I> implements DeleteCommand<E, I> {

    private final EntityCrudRepository<E, I> repository;

    public DeleteCommandImpl(EntityCrudRepository<E, I> repository) {
        this.repository = repository;
    }

    @Override
    public void delete(I id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        repository.deleteById(id);
    }
}
