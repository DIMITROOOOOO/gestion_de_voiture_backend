package applicationproject.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import applicationproject.entity.Assurance;
import applicationproject.reposatory.AssuranceRepository;

@Service
public class AssuranceService {
@Autowired
    private AssuranceRepository assuranceRepository;

    public Assurance createAssurance(Assurance assurance) {
        return assuranceRepository.save(assurance);
    }

}
