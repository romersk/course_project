package com.bsuir.audit.dto;

import com.bsuir.shared.dto.Dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Setter
@Data
public class AuditDtoEvdokimovRD implements Dto {

    public AuditDtoEvdokimovRD() {
    }

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateEnd;
    private String plan;
}
