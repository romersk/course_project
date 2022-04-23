package com.bsuir.shared.search.impl;

import com.bsuir.shared.search.Page;
import java.util.List;

public class PageImpl<D> implements Page<D> {

    private final List<D> result;
    private final int pageNumber;
    private final int totalElements;
    private final int totalPages;

    public PageImpl(List<D> result, int pageNumber, int totalElements, int totalPages) {
        this.result = result;
        this.pageNumber = pageNumber;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    @Override
    public List<D> getResult() {
        return result;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getTotalElements() {
        return totalElements;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }
}
