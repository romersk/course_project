package com.bsuir.audit.request;

import com.bsuir.shared.dto.CreateRequest;
import com.bsuir.shared.dto.UpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuditRequestEvdokimovRD implements CreateRequest, UpdateRequest {

    private String jsonRequest;
}
