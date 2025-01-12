package applicationproject.controller;

import org.springframework.web.bind.annotation.RestController;

import applicationproject.dto.SignUpRequest;
import applicationproject.dto.userDto;
import applicationproject.service.auth.authService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
 class AuthController {
    private final authService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupgestionnaire(@RequestBody SignUpRequest signUpRequest) {
        if(authService.hascustomerEmail(signUpRequest.getemail()))
            return new ResponseEntity<>("email already exists", HttpStatus.NOT_ACCEPTABLE);
        if (authService.hascustomerNomUtilisateur(signUpRequest.getnom())) {
            return new ResponseEntity<>("Username already exists", HttpStatus.NOT_ACCEPTABLE);
            }
        userDto createduserdto = authService.createCustomUser(signUpRequest);
        if (createduserdto == null) {
            return new ResponseEntity<>("user not created", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createduserdto, HttpStatus.CREATED);
    }
    @GetMapping("/users")
    public ResponseEntity<List<userDto>> getAllUsers() {
        List<userDto> users = authService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}