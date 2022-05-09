package com.bsuir.process.request;

import com.bsuir.shared.dto.CreateRequest;
import com.bsuir.shared.dto.UpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProcessRequestEvdokimovRD implements CreateRequest, UpdateRequest {

    private String jsonRequest;
}
