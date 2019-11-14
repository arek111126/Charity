package pl.coderslab.charity.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;

@RestController
@RequestMapping("/app/institution")
public class InstitutionControler {

    @Autowired
    InstitutionService institutionService;

    @GetMapping("/")
  public  List<Institution> getAllItem(){
        return institutionService.findAll();

    }

}
