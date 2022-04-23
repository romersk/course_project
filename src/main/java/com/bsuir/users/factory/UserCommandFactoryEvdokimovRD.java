package com.bsuir.users.factory;

import com.bsuir.shared.crud.AbstractCommandFactory;
import com.bsuir.shared.mapping.CreateDtoMapper;
import com.bsuir.shared.mapping.DtoPresenter;
import com.bsuir.shared.mapping.UpdateDtoMapper;
import com.bsuir.shared.persitance.EntityCrudRepository;
import com.bsuir.shared.validation.Validator;
import com.bsuir.users.dto.UsersDtoEvdokimovRD;
import com.bsuir.users.dto.mapper.UsersDtoMapperEvdokimovRD;
import com.bsuir.users.dto.presenter.UsersDtoPresenterEvdokimovRD;
import com.bsuir.users.entity.UserEvdokimovRD;
import com.bsuir.users.repository.UserRepositoryEvdokimovRD;
import com.bsuir.users.request.UserRequestEvdokimovRD;
import com.bsuir.users.validator.UsersValidatorEvdokimovRD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UserCommandFactoryEvdokimovRD extends AbstractCommandFactory<UserEvdokimovRD, Long, UserRequestEvdokimovRD, UsersDtoEvdokimovRD> {

    @Autowired
    public UserCommandFactoryEvdokimovRD(ApplicationContext context) {
        super(context);
    }

    @Override
    protected Class<? extends CreateDtoMapper<UserRequestEvdokimovRD, UserEvdokimovRD>> getCreateDtoMapperClass() {
        return UsersDtoMapperEvdokimovRD.class;
    }

    @Override
    protected Class<? extends DtoPresenter<UsersDtoEvdokimovRD, UserEvdokimovRD>> getEntityPresenterClass() {
        return UsersDtoPresenterEvdokimovRD.class;
    }

    @Override
    protected Class<? extends UpdateDtoMapper<UserRequestEvdokimovRD, UserEvdokimovRD>> getUpdateDtoMapperClass() {
        return UsersDtoMapperEvdokimovRD.class;
    }

    @Override
    protected Class<? extends Validator<UserEvdokimovRD>> getValidatorClass() {
        return UsersValidatorEvdokimovRD.class;
    }

    @Override
    protected Class<? extends EntityCrudRepository<UserEvdokimovRD, Long>> getRepository() {
        return UserRepositoryEvdokimovRD.class;
    }
}
