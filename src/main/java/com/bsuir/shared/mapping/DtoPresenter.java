package com.bsuir.shared.mapping;

import java.util.List;

public interface DtoPresenter<D, E> {

    D map(E entity);

    List<D> mapList(List<E> entities);
}
