package org.example.onlinegameapi.mapper;

import org.example.onlinegameapi.dto.request.CreateCaseRequest;
import org.example.onlinegameapi.dto.response.CaseResponse;
import org.example.onlinegameapi.entity.Case;
import org.springframework.stereotype.Component;

@Component
public class CaseMapper {
    public Case toEntity(CreateCaseRequest request){
        if (request == null) return null;

        Case entity = new Case();
        entity.setName(request.getName());
        entity.setPrice(request.getPrice());
        entity.setCommonChance(request.getCommonChance());
        entity.setUncommonChance(request.getUncommonChance());
        entity.setRareChance(request.getRareChance());
        entity.setEpicChance(request.getEpicChance());
        entity.setLegendaryChance(request.getLegendaryChance());
        entity.setEternalChance(request.getEternalChance());

        return entity;
    }

    public CaseResponse toDto(Case entity) {
        return CaseResponse.builder()
            .id(entity.getId())
            .name(entity.getName())
            .price(entity.getPrice())
            .build();
    }
}
