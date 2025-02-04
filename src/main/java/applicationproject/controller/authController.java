    package applicationproject.controller;

    import org.springframework.web.bind.annotation.RestController;

    import applicationproject.dto.SignUpRequest;
    import applicationproject.dto.signInRequest;
    import applicationproject.dto.userDto;
import applicationproject.entity.user;
import applicationproject.service.auth.authService;
    import lombok.RequiredArgsConstructor;
    import java.util.List;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
        @PostMapping("/signin")
        public ResponseEntity<?> signIn(@RequestBody signInRequest signInRequest) {
            try{
                userDto userDto = authService.signIn(signInRequest);
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            }
            catch(IllegalArgumentException e){
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
        }
        @PutMapping("/users/{id}")
        public ResponseEntity<user> updateUser(@PathVariable int id, @RequestBody user updatedUser) {
            user user = authService.updateUser(id, updatedUser);
            return ResponseEntity.ok(user);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        authService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}