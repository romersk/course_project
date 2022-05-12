package com.bsuir.shared.crud.impl;

import com.bsuir.shared.crud.impl.UpdateCommandImpl;
import com.bsuir.shared.validation.Error;
import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.shared.dto.UpdateRequest;
import com.bsuir.shared.exception.EntityNotFoundException;
import com.bsuir.shared.exception.ValidationException;
import com.bsuir.shared.mapping.DtoPresenter;
import com.bsuir.shared.mapping.UpdateDtoMapper;
import com.bsuir.shared.persitance.EntityCrudRepository;
import com.bsuir.shared.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UpdateCommandImplTest<D> {
    private final long DEFAULT_ID = 1;
    @Mock
    private DtoPresenter<D, AuditEvdokimovRD> presenter;
    @Mock
    private Validator<AuditEvdokimovRD> validator;
    @Mock
    private UpdateDtoMapper<UpdateRequest, AuditEvdokimovRD> updateDtoMapper;
    @Mock
    private EntityCrudRepository<AuditEvdokimovRD, Long> repository;
    @Mock
    private UpdateRequest updateRequest;
    @Mock
    private AuditEvdokimovRD entity;
    private UpdateCommandImpl<AuditEvdokimovRD, UpdateRequest, D, Long> updateCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        updateCommand = new UpdateCommandImpl<>(repository,updateDtoMapper,validator,presenter);
    }

    @Test
    public void testIfGetByIdIsInvoked() {
        Mockito.when(repository.getById(DEFAULT_ID)).thenReturn(entity);

        updateCommand.update(updateRequest, DEFAULT_ID);

        verify(repository, times(1)).getById(DEFAULT_ID);
    }

    @Test
    public void testIfSaveIsInvoked() {
        Mockito.when(repository.getById(DEFAULT_ID)).thenReturn(entity);
        Mockito.when(updateDtoMapper.mapToEntity(entity, updateRequest)).thenReturn(entity);

        updateCommand.update(updateRequest, DEFAULT_ID);

        verify(repository, times(1)).save(entity);
    }

    @Test
    public void testThrowingEntityNotFoundException() {
        Mockito.when(repository.getById(DEFAULT_ID)).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> updateCommand.update(updateRequest,DEFAULT_ID));
    }

    @Test
    public void testThrowingValidationException() {
        Mockito.when(repository.getById(DEFAULT_ID)).thenReturn(entity);
        Mockito.when(updateDtoMapper.mapToEntity(entity, updateRequest)).thenReturn(entity);
        Mockito.when(validator.validate(entity)).thenReturn(List.of(new Error()));

        assertThrows(ValidationException.class, () -> updateCommand.update(updateRequest,DEFAULT_ID));
    }
}
