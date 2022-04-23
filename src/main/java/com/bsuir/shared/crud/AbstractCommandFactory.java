package com.bsuir.shared.crud;

import com.bsuir.shared.crud.impl.*;
import com.bsuir.shared.mapping.*;
import com.bsuir.shared.persitance.EntityCrudRepository;
import com.bsuir.shared.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractCommandFactory<E, I, R, D> implements CommandFactory {

    protected final ApplicationContext context;
    private CreateCommand<E, R, D> createCommand;
    private GetByIdQuery<E, D, I> getByIdQuery;
    private UpdateCommand<E, R, D, I> updateCommand;
    private SearchQuery<E, D> searchQuery;
    private DeleteCommand<E, I> deleteCommand;

    @Autowired
    public AbstractCommandFactory(ApplicationContext context) {
        this.context = context;
    }

    protected abstract Class<? extends CreateDtoMapper<R, E>> getCreateDtoMapperClass();

    protected abstract Class<? extends DtoPresenter<D, E>> getEntityPresenterClass();

    protected abstract Class<? extends UpdateDtoMapper<R, E>> getUpdateDtoMapperClass();

    protected abstract Class<? extends Validator<E>> getValidatorClass();

    protected abstract Class<? extends EntityCrudRepository<E, I>> getRepository();

    @Override
    public CreateCommand<E, R, D> createCommand() {
        if (createCommand == null) {
            final EntityCrudRepository<E, I> repository = repository();

            final CreateDtoMapper<R, E> createDtoMapper =
                    context.getBean(this.getCreateDtoMapperClass());

            final Validator<E> validator = validator();

            final DtoPresenter<D, E> dtoPresenter = dtoPresenter();

            createCommand = new CreateCommandImpl<>(repository, createDtoMapper, validator, dtoPresenter);
        }

        return createCommand;
    }

    @Override
    public GetByIdQuery<E, D, I> getByIdQuery() {
        if (getByIdQuery == null) {
            final EntityCrudRepository<E, I> repository = repository();
            final DtoPresenter<D, E> dtoPresenter = dtoPresenter();
            getByIdQuery = new GetByIdQueryImpl<>(repository, dtoPresenter);
        }
        return getByIdQuery;
    }

    @Override
    public UpdateCommand<E, R, D, I> updateCommand() {
        if (updateCommand == null) {
            final EntityCrudRepository<E, I> repository = repository();
            final UpdateDtoMapper<R, E> updateDtoMapper =
                    context.getBean(this.getUpdateDtoMapperClass());
            final Validator<E> validator = validator();
            final DtoPresenter<D, E> dtoPresenter = dtoPresenter();
            updateCommand = new UpdateCommandImpl<>(repository, updateDtoMapper, validator, dtoPresenter);
        }
        return updateCommand;
    }

    @Override
    public SearchQuery<E, D> searchQuery() {
        if (searchQuery == null) {
            final EntityCrudRepository<E, I> repository = repository();
            final DtoPresenter<D, E> dtoPresenter = dtoPresenter();
            searchQuery = new SearchQueryImpl<>(repository, dtoPresenter);
        }
        return searchQuery;
    }

    @Override
    public DeleteCommand<E, I> deleteCommand() {
        if (deleteCommand == null) {
            final EntityCrudRepository<E, I> repository = repository();
            deleteCommand = new DeleteCommandImpl<>(repository);
        }
        return deleteCommand;
    }

    private EntityCrudRepository<E, I> repository() {
        return context.getBean(this.getRepository());
    }

    private DtoPresenter<D, E> dtoPresenter() {
        return context.getBean(this.getEntityPresenterClass());
    }

    private Validator<E> validator() {
        return context.getBean(this.getValidatorClass());
    }
}
