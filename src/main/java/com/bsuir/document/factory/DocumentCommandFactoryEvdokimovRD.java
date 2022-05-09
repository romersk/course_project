package com.bsuir.document.factory;

import com.bsuir.document.dto.DocumentDtoEvdokimovRD;
import com.bsuir.document.dto.mapper.DocumentDtoMapperEvdokimovRD;
import com.bsuir.document.dto.presenter.DocumentDtoPresenterEvdokimovRD;
import com.bsuir.document.entity.DocumentEvdokimovRD;
import com.bsuir.document.repository.DocumentRepositoryEvdokimovRD;
import com.bsuir.document.request.DocumentRequestEvdokimovRD;
import com.bsuir.document.validator.DocumentValidatorEvdokimovRD;
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
public class DocumentCommandFactoryEvdokimovRD extends AbstractCommandFactory<DocumentEvdokimovRD, Long, DocumentRequestEvdokimovRD, DocumentDtoEvdokimovRD> {

    @Autowired
    public DocumentCommandFactoryEvdokimovRD(ApplicationContext context) {
        super(context);
    }

    @Override
    protected Class<? extends CreateDtoMapper<DocumentRequestEvdokimovRD, DocumentEvdokimovRD>> getCreateDtoMapperClass() {
        return DocumentDtoMapperEvdokimovRD.class;
    }

    @Override
    protected Class<? extends DtoPresenter<DocumentDtoEvdokimovRD, DocumentEvdokimovRD>> getEntityPresenterClass() {
        return DocumentDtoPresenterEvdokimovRD.class;
    }

    @Override
    protected Class<? extends UpdateDtoMapper<DocumentRequestEvdokimovRD, DocumentEvdokimovRD>> getUpdateDtoMapperClass() {
        return DocumentDtoMapperEvdokimovRD.class;
    }

    @Override
    protected Class<? extends Validator<DocumentEvdokimovRD>> getValidatorClass() {
        return DocumentValidatorEvdokimovRD.class;
    }

    @Override
    protected Class<? extends EntityCrudRepository<DocumentEvdokimovRD, Long>> getRepository() {
        return DocumentRepositoryEvdokimovRD.class;
    }
}
