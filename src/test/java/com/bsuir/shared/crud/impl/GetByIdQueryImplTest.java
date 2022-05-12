package com.bsuir.shared.crud.impl;

import com.bsuir.shared.crud.GetByIdQuery;
import com.bsuir.shared.exception.EntityNotFoundException;
import com.bsuir.shared.mapping.DtoPresenter;
import com.bsuir.shared.persitance.EntityCrudRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class GetByIdQueryImplTest<D> {

    final long MOCK_ID = 1L;
    @Mock
    EntityCrudRepository<EntityImpl, Long> repository;
    @Mock
    DtoPresenter<D, EntityImpl> dtoPresenter;
    GetByIdQuery<EntityImpl, D, Long> getByIdQuery;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getByIdQuery = new GetByIdQueryImpl<>(repository, dtoPresenter);
    }

    @Test
    void testIfGetByIdQueryThrowsEntityNotFoundException() {
        Mockito.when(repository.getById(MOCK_ID))
                .thenThrow(new javax.persistence.EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> getByIdQuery.getById(MOCK_ID));
    }

    @Test
    void testIfGetByIdQueryGetsEntity() {
        Mockito.when(repository.getById(MOCK_ID)).thenReturn(new EntityImpl(MOCK_ID));

        getByIdQuery.getById(MOCK_ID);

        Mockito.verify(repository, Mockito.times(1)).getById(MOCK_ID);
    }

    private record EntityImpl(long id)  {
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
