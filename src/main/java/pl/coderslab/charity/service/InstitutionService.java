package pl.coderslab.charity.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

@Component
public class InstitutionService {
    @Autowired
    InstitutionRepository institutionRepository;

    public Institution save(Institution institution) {
        return institutionRepository.save(institution);

    }

    public void delete(Institution institution) {
        institutionRepository.delete(institution);
    }

    public Institution findById(int id) {
        return institutionRepository.findFirstById(id);
    }
}
