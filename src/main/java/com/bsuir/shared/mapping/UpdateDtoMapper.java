package com.bsuir.shared.mapping;

public interface UpdateDtoMapper<R, E> {

    E mapToEntity(E entity, R request);
}
