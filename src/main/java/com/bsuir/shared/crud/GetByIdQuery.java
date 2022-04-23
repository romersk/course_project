package com.bsuir.shared.crud;

public interface GetByIdQuery<E, D, I>{

    D getById(I id);
}
