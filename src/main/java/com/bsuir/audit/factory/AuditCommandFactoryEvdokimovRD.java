package com.bsuir.audit.factory;

import com.bsuir.audit.dto.AuditDtoEvdokimovRD;
import com.bsuir.audit.dto.mapper.AuditDtoMapperEvdokimovRD;
import com.bsuir.audit.dto.presenter.AuditDtoPresenterEvdokimovRD;
import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.audit.repository.AuditRepositoryEvdokimovRD;
import com.bsuir.audit.request.AuditRequestEvdokimovRD;
import com.bsuir.audit.validator.AuditValidatorEvdokimovRD;
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
public class AuditCommandFactoryEvdokimovRD extends AbstractCommandFactory<AuditEvdokimovRD, Long, AuditRequestEvdokimovRD, AuditDtoEvdokimovRD> {

    @Autowired
    public AuditCommandFactoryEvdokimovRD(ApplicationContext context) {
        super(context);
    }

    @Override
    protected Class<? extends CreateDtoMapper<AuditRequestEvdokimovRD, AuditEvdokimovRD>> getCreateDtoMapperClass() {
        return AuditDtoMapperEvdokimovRD.class;
    }

    @Override
    protected Class<? extends DtoPresenter<AuditDtoEvdokimovRD, AuditEvdokimovRD>> getEntityPresenterClass() {
        return AuditDtoPresenterEvdokimovRD.class;
    }

    @Override
    protected Class<? extends UpdateDtoMapper<AuditRequestEvdokimovRD, AuditEvdokimovRD>> getUpdateDtoMapperClass() {
        return AuditDtoMapperEvdokimovRD.class;
    }

    @Override
    protected Class<? extends Validator<AuditEvdokimovRD>> getValidatorClass() {
        return AuditValidatorEvdokimovRD.class;
    }

    @Override
    protected Class<? extends EntityCrudRepository<AuditEvdokimovRD, Long>> getRepository() {
        return AuditRepositoryEvdokimovRD.class;
    }
}
