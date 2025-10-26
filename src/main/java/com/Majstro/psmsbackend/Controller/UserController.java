package com.Majstro.psmsbackend.Controller;
import com.Majstro.psmsbackend.Models.Dtos.UserDto;
import com.Majstro.psmsbackend.Service.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {



    private  UserServices _userService;

    public UserController(UserServices userService) {
        this._userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUserInfo(@AuthenticationPrincipal Jwt accessToken) {

        var user = _userService.createUserIfNotExist(accessToken);

        System.out.println("User logged in: " + user.username + " | Email: " + user.email);

       return new ResponseEntity<>(user, HttpStatus.OK);
    }



    @PutMapping("/me")
    public ResponseEntity<Boolean> updateUser(@RequestBody UserDto updateRequest){

        var result = _userService.updateUser(updateRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable UUID id){
        var result = _userService.deleteUserLocally(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
