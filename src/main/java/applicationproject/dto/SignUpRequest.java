package applicationproject.dto;

import applicationproject.enums.roles;
import lombok.Data;

@Data
public class SignUpRequest {
    private String nom;
    private String email;
    private String motdepasse;
    private roles role; 
    public String getnom() {
        return nom;
    }
    public String getemail() {
        return email;
    }
    public String getmotdepasse() {
        return motdepasse;
    }
    public roles getRole() {
        return role;
    }
}
