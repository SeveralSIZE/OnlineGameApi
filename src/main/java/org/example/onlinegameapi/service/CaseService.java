package org.example.onlinegameapi.service;

import org.example.onlinegameapi.dto.request.CreateCaseRequest;
import org.example.onlinegameapi.dto.response.CaseResponse;
import org.example.onlinegameapi.dto.response.OpenCaseResponse;

import java.util.List;
import java.util.UUID;

public interface CaseService {
    UUID create(CreateCaseRequest request);
    CaseResponse getById(UUID id);
    List<CaseResponse> getAll();
    OpenCaseResponse openCase(UUID userId, UUID caseId);
}
