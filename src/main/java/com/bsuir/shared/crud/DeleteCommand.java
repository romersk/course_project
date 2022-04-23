package com.bsuir.shared.crud;

public interface DeleteCommand<E, I> {

    void delete(I id);
}
