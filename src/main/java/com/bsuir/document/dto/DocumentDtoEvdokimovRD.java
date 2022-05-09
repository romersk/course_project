package com.bsuir.document.dto;

import com.bsuir.process.entity.ProcessEvdokimovRD;
import lombok.Data;
import lombok.Setter;

@Setter
@Data
public class DocumentDtoEvdokimovRD {

    public DocumentDtoEvdokimovRD() {
    }

    private Long id;
    private String name;
    private String content;
    private ProcessEvdokimovRD process;
}
