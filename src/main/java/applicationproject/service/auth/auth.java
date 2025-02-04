package applicationproject.service.auth;

import applicationproject.dto.userDto;
import applicationproject.dto.SignUpRequest;
import applicationproject.dto.signInRequest;

public interface auth {

    userDto createCustomUser(SignUpRequest signUpRequest);
    userDto signIn(signInRequest signInRequest);
    boolean hascustomerEmail(String email);
    boolean hascustomerNomUtilisateur(String nomUtilisateur);
}
