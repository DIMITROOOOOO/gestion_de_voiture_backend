package applicationproject.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import applicationproject.entity.Taxe;
import applicationproject.reposatory.TaxeRepository;

@Service
public class TaxeService {
  @Autowired
    private TaxeRepository taxeRepository;

    public Taxe createTaxe(Taxe taxe) {
        return taxeRepository.save(taxe);
    }
}
