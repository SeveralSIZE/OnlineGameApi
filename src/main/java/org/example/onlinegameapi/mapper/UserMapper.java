package org.example.onlinegameapi.mapper;

import org.example.onlinegameapi.dto.request.RegisterRequest;
import org.example.onlinegameapi.dto.response.UserResponse;
import org.example.onlinegameapi.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(RegisterRequest request){
        if (request == null) return null;

        User user = new User();
        user.setUserName(request.getUserName());
        user.setRole(request.getRole());
        user.setEmail(request.getEmail());

        return user;
    }

    public UserResponse toDto(User user){
        return UserResponse.builder()
                .userName(user.getUserName())
                .role(user.getRole())
                .creationDate(user.getCreationDate())
                .build();
    }
}
