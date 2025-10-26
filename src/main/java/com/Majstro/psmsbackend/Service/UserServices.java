package com.Majstro.psmsbackend.Service;

import com.Majstro.psmsbackend.Mapper.UserMapper;
import com.Majstro.psmsbackend.Models.Dtos.UserDto;
import com.Majstro.psmsbackend.Models.EntityModels.GlobalRole;
import com.Majstro.psmsbackend.Models.EntityModels.User;
import com.Majstro.psmsbackend.Repo.UserRepository;
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


    public UserDto createUserIfNotExist(Jwt accessToken,String idTokenString){

        // Decode ID token
        Jwt idToken = jwtDecoder.decode(idTokenString);

        List<String> groups = accessToken.getClaimAsStringList("cognito:groups");

        GlobalRole role = "ADMIN".equalsIgnoreCase(groups.get(0))?GlobalRole.ADMIN:GlobalRole.USER;

        String sub = accessToken.getClaim("sub");
        String email = idToken.getClaim("email");  // from ID token
        String username = email;

        // Lazy create user if not found
        User user = _userRepository.findByCognitoSub(sub)
                .orElseGet(() -> {
                    User newUser = new User(sub, username, email);
                    newUser.setGlobalRole(role);
                    return _userRepository.save(newUser);
                });

        UserDto newuserdto= UserMapper.UserToUserDto(user);
        return newuserdto;
    }


    public Boolean updateUser(UserDto Request){

     var result = _userRepository.findById(Request.id);

     if(result.isPresent()){
         var updatingUser  =result.get();
         updatingUser.setUsername(Request.username);
         _userRepository.save(updatingUser);
         return true;
     }
       return false;
    }

    public Boolean deleteUserLocally(UUID userId){
        try{
            _userRepository.deleteById(userId);
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }


    }
}
