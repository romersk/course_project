package com.bsuir.process.dto;

import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.shared.dto.Dto;
import com.bsuir.users.entity.UserEvdokimovRD;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Setter;


@Setter
@Data
public class ProcessDtoEvdokimovRD implements Dto {

    public ProcessDtoEvdokimovRD() {
    }

    private Long id;
    private UserEvdokimovRD user;
    private Long startId;
    private String name;
    private String status;
    private String recommendations;

}
