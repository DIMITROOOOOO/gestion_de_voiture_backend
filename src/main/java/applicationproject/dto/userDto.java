package applicationproject.dto;

import applicationproject.enums.roles;
import lombok.Data;

@Data
public class userDto {
    private int id;
    private String nom;
    private String email;
    private String motdepasse;
    private roles role;
}