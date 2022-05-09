package com.bsuir.document.dto.presenter;

import com.bsuir.audit.dto.AuditDtoEvdokimovRD;
import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.document.dto.DocumentDtoEvdokimovRD;
import com.bsuir.document.entity.DocumentEvdokimovRD;
import com.bsuir.shared.mapping.DtoPresenter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class DocumentDtoPresenterEvdokimovRD implements DtoPresenter<DocumentDtoEvdokimovRD, DocumentEvdokimovRD> {
    private final ModelMapper mapper;

    @Autowired
    public DocumentDtoPresenterEvdokimovRD(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public DocumentDtoEvdokimovRD map(DocumentEvdokimovRD entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, DocumentDtoEvdokimovRD.class);
    }

    @Override
    public List<DocumentDtoEvdokimovRD> mapList(List<DocumentEvdokimovRD> entities) {
        List<DocumentDtoEvdokimovRD> clinicDtoList = new ArrayList<>();
        for (DocumentEvdokimovRD entity : entities) {
            clinicDtoList.add(mapper.map(entity, DocumentDtoEvdokimovRD.class));
        }
        return clinicDtoList;
    }
}
