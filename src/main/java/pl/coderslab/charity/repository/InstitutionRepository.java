package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;

import java.util.List;
import java.util.Set;

public interface InstitutionRepository extends JpaRepository<Institution,Integer> {
    Institution findFirstById(int id);


}
