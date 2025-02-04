package applicationproject.service.auth;

import applicationproject.dto.ChauffeurDTO;
import applicationproject.entity.chauffeurs;
import applicationproject.enums.statutChaffeurs;
import applicationproject.reposatory.chauffeursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import applicationproject.enums.statut;
import java.util.List;
import java.util.Optional;

@Service
public class chauffeursService {

    @Autowired
    private chauffeursRepository chauffeursRepository;

    @Autowired
    private VehiculesService vehiculesService;

    public chauffeurs addChauffeur(ChauffeurDTO chauffeurDTO) {
        chauffeurs chauffeur = new chauffeurs();
        chauffeur.setNom(chauffeurDTO.getNom());
        chauffeur.setTelephone(chauffeurDTO.getTelephone());
        chauffeur.setNumero_permis(chauffeurDTO.getNumero_permis());
        chauffeur.setDate_embauche(chauffeurDTO.getDate_embauche());
        chauffeur.setStatutChaffeurs(chauffeurDTO.getStatutChaffeurs());
        return chauffeursRepository.save(chauffeur);
    }

    public void deleteChauffeur(int id) {
        chauffeursRepository.deleteById(id);
    }

    public chauffeurs updateChauffeur(int id, ChauffeurDTO chauffeurDTO) {
        Optional<chauffeurs> optionalChauffeur = chauffeursRepository.findById(id);
        if (optionalChauffeur.isPresent()) {
            chauffeurs chauffeur = optionalChauffeur.get();
            chauffeur.setNom(chauffeurDTO.getNom());
            chauffeur.setTelephone(chauffeurDTO.getTelephone());
            chauffeur.setNumero_permis(chauffeurDTO.getNumero_permis());
            chauffeur.setDate_embauche(chauffeurDTO.getDate_embauche());
            chauffeur.setStatutChaffeurs(chauffeurDTO.getStatutChaffeurs());
            return chauffeursRepository.save(chauffeur);
        }
        return null;
    }

    public void assignChauffeurToVehicle(int chauffeurId, int vehiculeId) {
        Optional<chauffeurs> optionalChauffeur = chauffeursRepository.findById(chauffeurId);
        if (optionalChauffeur.isPresent()) {
            chauffeurs chauffeur = optionalChauffeur.get();
            if (chauffeur.getStatutChaffeurs() == statutChaffeurs.disponible) {
                chauffeur.setStatutChaffeurs(statutChaffeurs.assigné);
                chauffeursRepository.save(chauffeur);
                vehiculesService.updateVehiculeStatut(vehiculeId, statut.assigné);
            }
        }
    }

    public List<chauffeurs> getAllChauffeurs() {
        return chauffeursRepository.findAll();
    }

    public Optional<chauffeurs> getChauffeurById(int id) {
        return chauffeursRepository.findById(id);
    }
}