package com.bsuir.shared.crud.impl;

import java.util.List;

import com.bsuir.shared.crud.CreateCommand;
import com.bsuir.shared.exception.ValidationException;
import com.bsuir.shared.mapping.CreateDtoMapper;
import com.bsuir.shared.mapping.DtoPresenter;
import com.bsuir.shared.validation.Error;
import com.bsuir.shared.persitance.EntityCrudRepository;
import com.bsuir.shared.validation.Validator;

public class CreateCommandImpl<E, R, D, I> implements
        CreateCommand<E, R, D> {

    private final EntityCrudRepository<E, I> repository;
    private final CreateDtoMapper<R, E> createDtoMapper;
    private final Validator<E> validator;
    private final DtoPresenter<D, E> dtoPresenter;

    public CreateCommandImpl(EntityCrudRepository<E, I> repository,
                             CreateDtoMapper<R, E> createDtoMapper, Validator<E> validator,
                             DtoPresenter<D, E> dtoPresenter) {
        this.repository = repository;
        this.createDtoMapper = createDtoMapper;
        this.validator = validator;
        this.dtoPresenter = dtoPresenter;
    }

    @Override
    public D create(R createRequest) throws ValidationException {
        E entity = createDtoMapper.mapToEntity(createRequest);
        List<Error> errorList = validator.validate(entity);
        if (!errorList.isEmpty()) {
            throw new ValidationException(errorList);
        }
        entity = repository.save(entity);
        return dtoPresenter.map(entity);
    }
}
