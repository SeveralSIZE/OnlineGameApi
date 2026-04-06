package org.example.onlinegameapi.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.onlinegameapi.enums.Rarity;
import org.example.onlinegameapi.enums.UpgradeType;

@Converter
public class UpgradeTypeConverter implements AttributeConverter<UpgradeType, String> {
    @Override
    public String convertToDatabaseColumn(UpgradeType type){
        if(type == null) return null;
        return type.name();
    }

    @Override
    public UpgradeType convertToEntityAttribute(String value){
        if(value == null || value.isBlank()) return null;
        return UpgradeType.valueOf(value);
    }
}
