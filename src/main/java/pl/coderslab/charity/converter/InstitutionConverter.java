package pl.coderslab.charity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.InstitutionService;

import java.util.HashMap;
import java.util.Map;

public class InstitutionConverter implements Converter<String, Institution> {

    @Autowired
    InstitutionService institutionService;

    @Override
    public Institution convert(String id) {

        return institutionService.findById(Integer.parseInt(id));

    }
}
