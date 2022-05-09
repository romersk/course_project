package com.bsuir.process.dto.presenter;

import com.bsuir.process.dto.ProcessDtoEvdokimovRD;
import com.bsuir.process.entity.ProcessEvdokimovRD;
import com.bsuir.shared.mapping.DtoPresenter;
import com.bsuir.users.dto.UsersDtoEvdokimovRD;
import com.bsuir.users.entity.UserEvdokimovRD;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ProcessDtoPresenterEvdokimovRD implements DtoPresenter<ProcessDtoEvdokimovRD, ProcessEvdokimovRD> {

    private final ModelMapper mapper;

    @Autowired
    public ProcessDtoPresenterEvdokimovRD(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ProcessDtoEvdokimovRD map(ProcessEvdokimovRD entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ProcessDtoEvdokimovRD.class);
    }

    @Override
    public List<ProcessDtoEvdokimovRD> mapList(List<ProcessEvdokimovRD> entities) {
        List<ProcessDtoEvdokimovRD> clinicDtoList = new ArrayList<>();
        for (ProcessEvdokimovRD entity : entities) {
            clinicDtoList.add(mapper.map(entity, ProcessDtoEvdokimovRD.class));
        }
        return clinicDtoList;
    }
}
