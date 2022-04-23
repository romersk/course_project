package com.bsuir.shared.mapping;

public interface CreateDtoMapper<R, E> {

    E mapToEntity(R request);
}
