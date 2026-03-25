package org.example.onlinegameapi.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.onlinegameapi.enums.Role;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role){
        if(role == null) return Role.PLAYER.name();
        return role.name();
    }

    @Override
    public Role convertToEntityAttribute(String value){
        if(value == null || value.isBlank()) return Role.PLAYER;
        return Role.valueOf(value);
    }
}
