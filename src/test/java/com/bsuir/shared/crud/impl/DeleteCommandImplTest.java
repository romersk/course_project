package com.bsuir.shared.crud.impl;

import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.shared.exception.EntityNotFoundException;
import com.bsuir.shared.persitance.EntityCrudRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteCommandImplTest {
    private static final int DEFAULT_ID = 1;

    @Mock
    private EntityCrudRepository<AuditEvdokimovRD, Integer> repository;
    private DeleteCommandImpl<AuditEvdokimovRD, Integer> deleteCommand;

    @BeforeEach
    public void setup() {
        deleteCommand = new DeleteCommandImpl<AuditEvdokimovRD, Integer>(repository);
    }

    @Test
    public void testInvokingDeleteById() {
        Mockito.when(repository.existsById(DEFAULT_ID)).thenReturn(true);

        deleteCommand.delete(DEFAULT_ID);

        Mockito.verify(repository, Mockito.times(1)).deleteById(DEFAULT_ID);
    }

    @Test
    public void testDeleteWithNonExistentIdShouldThrowException() {
        Mockito.when(repository.existsById(DEFAULT_ID)).thenReturn(false);

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> deleteCommand.delete(DEFAULT_ID));
    }

}
