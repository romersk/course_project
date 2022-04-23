package com.bsuir.shared.crud;

import com.bsuir.shared.search.Page;
import com.bsuir.shared.search.Paging;
import org.springframework.data.jpa.domain.Specification;

public interface SearchQuery<E, D> {

    Page<D> search(Specification<E> specification, Paging paging);
}

