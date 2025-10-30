package com.Majstro.psmsbackend.service;

import com.Majstro.psmsbackend.models.Dtos.UserDto;
import com.Majstro.psmsbackend.models.EntityModels.User;
import com.Majstro.psmsbackend.repo.UserRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserServices userServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userServices = new UserServices(userRepository);
    }

    @Test
    void testUpdateUser_Success() {
        UUID userId = UUID.randomUUID();
        User user = new User("sub123", "username", "email@test.com");
        user.setId(userId);

        UserDto dto = new UserDto();
        dto.id = userId;
        dto.username = "newUsername";

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        Boolean result = userServices.updateUser(dto);
        assertTrue(result);
        assertEquals("newUsername", user.getUsername());
    }

    @Test
    void testDeleteUserLocally_Success() {
        UUID userId = UUID.randomUUID();
        doNothing().when(userRepository).deleteById(userId);

        Boolean result = userServices.deleteUserLocally(userId);
        assertTrue(result);
        verify(userRepository, times(1)).deleteById(userId);
    }
}
