package com.bsuir.shared.crud.impl;

import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.shared.dto.CreateRequest;
import com.bsuir.shared.exception.ValidationException;
import com.bsuir.shared.mapping.CreateDtoMapper;
import com.bsuir.shared.mapping.DtoPresenter;
import com.bsuir.shared.persitance.EntityCrudRepository;
import com.bsuir.shared.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bsuir.shared.validation.Error;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CreateCommandImplTest<D> {

    private final long MOCK_ID = 1L;
    @Mock
    private EntityCrudRepository<AuditEvdokimovRD, Long> repository;
    @Mock
    private CreateDtoMapper<CreateRequest, AuditEvdokimovRD> createDtoMapper;
    @Mock
    private Validator<AuditEvdokimovRD> validator;
    @Mock
    private DtoPresenter<D, AuditEvdokimovRD> dtoPresenter;
    @Mock
    private CreateRequest createRequest;
    private CreateCommandImpl<AuditEvdokimovRD, CreateRequest, D, Long> createCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createCommand = new CreateCommandImpl<>(repository, createDtoMapper, validator, dtoPresenter);
    }

    @Test
    void testIfCreateCommandThrowsValidationException() {
        List<Error> errorList = List.of(Error.fieldValidationError(
                "mockFieldName", "mockErrorCode"));
        Mockito.when(validator.validate(Mockito.any())).thenReturn(errorList);

        assertThrows(ValidationException.class, () -> createCommand.create(createRequest));
    }

    private record EntityImpl(long id){

        public Long getId() {
            return this.id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            EntityImpl entity = (EntityImpl) o;
            return id == entity.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
