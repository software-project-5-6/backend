package com.Majstro.psmsbackend.service;

import com.Majstro.psmsbackend.exception.NullException;
import com.Majstro.psmsbackend.exception.UserNotFoundException;
import com.Majstro.psmsbackend.exception.UserServiceException;
import com.Majstro.psmsbackend.mapper.UserMapper;
import com.Majstro.psmsbackend.models.Dtos.UserDto;
import com.Majstro.psmsbackend.models.EntityModels.GlobalRole;
import com.Majstro.psmsbackend.models.EntityModels.User;
import com.Majstro.psmsbackend.repo.UserRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.UUID;

@Service
public class UserServices {

    UserRepository _userRepository;


    private final String issuerUri = "https://cognito-idp.eu-north-1.amazonaws.com/eu-north-1_fEghCDpYb";
    private final JwtDecoder jwtDecoder = JwtDecoders.fromIssuerLocation(issuerUri);


    public UserServices(UserRepository userRepo){
        _userRepository=userRepo;

    }


    public UserDto createUserIfNotExist(Jwt accessToken){


            List<String> groups = accessToken.getClaimAsStringList("cognito:groups");

            GlobalRole role = "ADMIN".equalsIgnoreCase(groups.get(0)) ? GlobalRole.ADMIN : GlobalRole.USER;

            String sub = accessToken.getClaim("sub");
            String email = accessToken.getClaim("email");
            String username = email;

            // Lazy create user if not found
        try {
            User user = _userRepository.findByCognitoSub(sub)
                    .orElseGet(() -> {
                        User newUser = new User(sub, username, email);
                        newUser.setGlobalRole(role);
                        return _userRepository.save(newUser);
                    });

            UserDto newuserdto = UserMapper.UserToUserDto(user);
            return newuserdto;
        }catch (Exception e){
            throw new UserServiceException("Error in creating or retrieving user",e);
        }

    }


    public List<UserDto> getAllUsers(){

        var userList = _userRepository.findAll();

          List<UserDto> listToReturn = userList.stream()
                  .map(x->UserMapper.UserToUserDto(x))
                  .toList();

            return listToReturn;

    }


    public Boolean updateUser(UserDto request){


        User updatingUser = _userRepository.findById(request.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + request.getId()));

        updatingUser.setUsername(request.getUsername());
        updatingUser.setEmail(request.getEmail());

        _userRepository.save(updatingUser);
        return true;
    }


    public Boolean deleteUserLocally(UUID userId){

        if(userId == null){
            throw new NullException("User id cannot be null");
        }
            _userRepository.deleteById(userId);
            return true;

    }
}
