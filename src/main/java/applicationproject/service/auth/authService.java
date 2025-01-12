package applicationproject.service.auth;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import java.util.List;
import applicationproject.reposatory.userrepo;
import lombok.RequiredArgsConstructor;
import applicationproject.dto.SignUpRequest;
import applicationproject.dto.userDto;
import applicationproject.entity.user;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class authService implements auth {
    private final userrepo userrepo;
    
    @Override
    public userDto createCustomUser(SignUpRequest signUpRequest) {
        log.info("Creating user with data: {}", signUpRequest);
        user user = new user();
        if (signUpRequest.getnom() == null || signUpRequest.getemail() == null || signUpRequest.getmotdepasse() == null) {
            log.error("Missing required fields in SignUpRequest: {}", signUpRequest);
            throw new IllegalArgumentException("All fields are required");
        }
        user.setFullName(signUpRequest.getnom());
        user.setEmail(signUpRequest.getemail());
        user.setPassword(signUpRequest.getmotdepasse());
       
        user.setRole(signUpRequest.getRole());
        log.info("User entity created: {}", user);
        user createdUser = userrepo.save(user);
        log.info("User saved to database: {}", createdUser);

        userDto userDto = new userDto();
        userDto.setId(createdUser.getId());
        userDto.setNom(createdUser.getNom());
        userDto.setEmail(createdUser.getEmail());
        userDto.setMotdepasse(user.getMotdepasse());
        userDto.setRole(createdUser.getRole());
        
        log.info("User DTO created: {}", userDto);
        return userDto;
    }

    @Override
    public boolean hascustomerEmail(String email) {
        return userrepo.findFirstByEmail(email).isPresent();
    }
    @Override
    public boolean hascustomerNomUtilisateur(String nom) {
    return userrepo.findByNom(nom).isPresent();
}
     public List<userDto> getAllUsers() {
        List<user> users = userrepo.findAll(); 
        return users.stream()
                .map(user -> {
                    userDto userDto = new userDto();
                    userDto.setId(user.getId());
                    userDto.setNom(user.getNom());
                    userDto.setEmail(user.getEmail());
                    userDto.setMotdepasse(user.getMotdepasse());
                    userDto.setRole(user.getRole());
                    return userDto;
                })
                .collect(Collectors.toList());
    }
}