package applicationproject.reposatory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import applicationproject.entity.user;

 @Repository
 
public interface userrepo extends JpaRepository<user, Integer> {

        Optional<user>findFirstByEmail(String email);
        Optional<user> findByNom(String nom); 
    }

