package applicationproject.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import applicationproject.entity.Modele;
import applicationproject.reposatory.modeleRepository;

@Service
public class ModeleService {

    @Autowired
    private modeleRepository modeleRepository;

    public Modele createModele(Modele modele) {
        return modeleRepository.save(modele);
    }

    public Modele getModeleById(int modeleId) {
        return modeleRepository.findById(modeleId).orElse(null);
    }

    public List<Modele> getAllModeles() {
        return modeleRepository.findAll();
    }

    public List<Modele> getModelesByMarque(String marque) {
        return modeleRepository.findByMarque(marque);
    }

    public void deleteModele(int modeleId) {
        modeleRepository.deleteById(modeleId);
    }
}