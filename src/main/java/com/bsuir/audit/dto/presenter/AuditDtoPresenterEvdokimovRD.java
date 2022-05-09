package com.bsuir.audit.dto.presenter;

import com.bsuir.audit.dto.AuditDtoEvdokimovRD;
import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.process.dto.ProcessDtoEvdokimovRD;
import com.bsuir.process.entity.ProcessEvdokimovRD;
import com.bsuir.shared.mapping.DtoPresenter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AuditDtoPresenterEvdokimovRD implements DtoPresenter<AuditDtoEvdokimovRD, AuditEvdokimovRD> {

    private final ModelMapper mapper;

    @Autowired
    public AuditDtoPresenterEvdokimovRD(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public AuditDtoEvdokimovRD map(AuditEvdokimovRD entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, AuditDtoEvdokimovRD.class);
    }

    @Override
    public List<AuditDtoEvdokimovRD> mapList(List<AuditEvdokimovRD> entities) {
        List<AuditDtoEvdokimovRD> clinicDtoList = new ArrayList<>();
        for (AuditEvdokimovRD entity : entities) {
            clinicDtoList.add(mapper.map(entity, AuditDtoEvdokimovRD.class));
        }
        return clinicDtoList;
    }
}
