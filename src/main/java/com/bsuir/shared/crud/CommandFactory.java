package com.bsuir.shared.crud;

public interface CommandFactory {

    CreateCommand createCommand();

    DeleteCommand deleteCommand();

    GetByIdQuery getByIdQuery();

    SearchQuery searchQuery();

    UpdateCommand updateCommand();
}
