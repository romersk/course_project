package com.bsuir.shared.crud;

public interface CreateCommand <E,R,D>{

    D create(R createRequest);
}
