package applicationproject.dto;
import lombok.Data;
@Data
public class signInRequest {

    private String email;
    private String motdepasse;
    
    public String getemail() {
        return email;
    }
    public String getmotdepasse() {
        return motdepasse;
    }
}

