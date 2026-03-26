package org.example.onlinegameapi.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.onlinegameapi.enums.Rarity;
import org.example.onlinegameapi.enums.Role;

@Converter
public class RarityConverter implements AttributeConverter<Rarity, String> {
    @Override
    public String convertToDatabaseColumn(Rarity rarity){
        if(rarity == null) return Rarity.COMMON.name();
        return rarity.name();
    }

    @Override
    public Rarity convertToEntityAttribute(String value){
        if(value == null || value.isBlank()) return Rarity.COMMON;
        return Rarity.valueOf(value);
    }
}
