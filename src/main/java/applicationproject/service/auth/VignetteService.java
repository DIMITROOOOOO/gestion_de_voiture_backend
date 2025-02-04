package applicationproject.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import applicationproject.entity.Vignette;
import applicationproject.reposatory.VignetteRepository;

@Service
public class VignetteService {
 @Autowired
    private VignetteRepository vignetteRepository;

    public Vignette createVignette(Vignette vignette) {
        return vignetteRepository.save(vignette);
    }
}
