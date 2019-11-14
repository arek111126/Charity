package pl.coderslab.charity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.InstitutionService;

public class InstitutionConverter implements Converter<String, Institution> {

    @Autowired
    InstitutionService institutionService;

    @Override
    public Institution convert(String id) {
        int parsedId = Integer.parseInt(id);
        return institutionService.findById(parsedId);
    }
}
