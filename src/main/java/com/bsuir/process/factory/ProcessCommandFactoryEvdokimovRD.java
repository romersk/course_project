package com.bsuir.process.factory;

import com.bsuir.process.dto.ProcessDtoEvdokimovRD;
import com.bsuir.process.dto.mapper.ProcessDtoMapperEvdokimovRD;
import com.bsuir.process.dto.presenter.ProcessDtoPresenterEvdokimovRD;
import com.bsuir.process.entity.ProcessEvdokimovRD;
import com.bsuir.process.repository.ProcessRepositoryEvdokimovRD;
import com.bsuir.process.request.ProcessRequestEvdokimovRD;
import com.bsuir.process.validator.ProcessValidatorEvdokimovRD;
import com.bsuir.shared.crud.AbstractCommandFactory;
import com.bsuir.shared.mapping.CreateDtoMapper;
import com.bsuir.shared.mapping.DtoPresenter;
import com.bsuir.shared.mapping.UpdateDtoMapper;
import com.bsuir.shared.persitance.EntityCrudRepository;
import com.bsuir.shared.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ProcessCommandFactoryEvdokimovRD extends AbstractCommandFactory<ProcessEvdokimovRD, Long, ProcessRequestEvdokimovRD, ProcessDtoEvdokimovRD> {

    @Autowired
    public ProcessCommandFactoryEvdokimovRD(ApplicationContext context) {
        super(context);
    }

    @Override
    protected Class<? extends CreateDtoMapper<ProcessRequestEvdokimovRD, ProcessEvdokimovRD>> getCreateDtoMapperClass() {
        return ProcessDtoMapperEvdokimovRD.class;
    }

    @Override
    protected Class<? extends DtoPresenter<ProcessDtoEvdokimovRD, ProcessEvdokimovRD>> getEntityPresenterClass() {
        return ProcessDtoPresenterEvdokimovRD.class;
    }

    @Override
    protected Class<? extends UpdateDtoMapper<ProcessRequestEvdokimovRD, ProcessEvdokimovRD>> getUpdateDtoMapperClass() {
        return ProcessDtoMapperEvdokimovRD.class;
    }

    @Override
    protected Class<? extends Validator<ProcessEvdokimovRD>> getValidatorClass() {
        return ProcessValidatorEvdokimovRD.class;
    }

    @Override
    protected Class<? extends EntityCrudRepository<ProcessEvdokimovRD, Long>> getRepository() {
        return ProcessRepositoryEvdokimovRD.class;
    }
}
