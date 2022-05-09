package com.bsuir.document.request;

import com.bsuir.shared.dto.CreateRequest;
import com.bsuir.shared.dto.UpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DocumentRequestEvdokimovRD implements CreateRequest, UpdateRequest {

    private String jsonRequest;
}
