package com.bsuir.shared.crud.impl;

import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.shared.dto.Dto;
import com.bsuir.shared.exception.EntityNotFoundException;
import com.bsuir.shared.mapping.DtoPresenter;
import com.bsuir.shared.persitance.EntityCrudRepository;
import com.bsuir.shared.search.impl.PagingImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
public class SearchQueryImplTest {

    private static final int pageNumber = 1;
    private static final int perPageCount = 1;

    @Mock
    private EntityCrudRepository<AuditEvdokimovRD, Integer> repository;
    @Mock
    private DtoPresenter<Dto, AuditEvdokimovRD> presenter;
    @Mock
    private Specification<AuditEvdokimovRD> specification;
    @Mock
    private org.springframework.data.domain.Page<AuditEvdokimovRD> emptyPage;

    private SearchQueryImpl<AuditEvdokimovRD, Dto> searchQuery;

    @BeforeEach
    public void setup() {
        searchQuery = new SearchQueryImpl<>(repository, presenter);
    }

    @Test
    public void testInvokingFindAll() {
        PagingImpl paging = new PagingImpl(pageNumber, perPageCount);
        PageRequest testPageRequest = PageRequest.of(paging.getPageNumber(),
                paging.getPerPageCount());
        Mockito.when(repository.findAll(specification, testPageRequest)).thenReturn(emptyPage);

        try {
            searchQuery.search(specification, paging);
        } catch (EntityNotFoundException ignored) {
        }

        Mockito.verify(repository, Mockito.times(1)).findAll(specification, testPageRequest);
    }

    @Test
    public void testEmptyPageAsResultShouldThrowException() {
        PagingImpl paging = new PagingImpl(pageNumber, perPageCount);
        PageRequest testPageRequest = PageRequest.of(paging.getPageNumber(),
                paging.getPerPageCount());
        Mockito.when(repository.findAll(specification, testPageRequest)).thenReturn(emptyPage);

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> searchQuery.search(specification, paging));
    }
}
