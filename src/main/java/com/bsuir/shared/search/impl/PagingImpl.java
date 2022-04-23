package com.bsuir.shared.search.impl;

import com.bsuir.shared.search.Paging;

public class PagingImpl implements Paging {

    private final int pageNumber;
    private final int perPageCount;

    public PagingImpl(int pageNumber, int perPageCount) {
        this.pageNumber = pageNumber;
        this.perPageCount = perPageCount;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPerPageCount() {
        return perPageCount;
    }
}
