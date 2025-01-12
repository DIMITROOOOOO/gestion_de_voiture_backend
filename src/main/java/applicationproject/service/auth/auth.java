package applicationproject.service.auth;

import applicationproject.dto.userDto;
import applicationproject.dto.SignUpRequest;

public interface auth {

    userDto createCustomUser(SignUpRequest signUpRequest);

    boolean hascustomerEmail(String email);
    boolean hascustomerNomUtilisateur(String nomUtilisateur);
}
