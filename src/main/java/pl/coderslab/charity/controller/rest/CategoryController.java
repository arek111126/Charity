package pl.coderslab.charity.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/app/category")
public class CategoryController {

    @Autowired
    InstitutionService institutionService;

    @GetMapping("/{categoryId}/institution")
    public ResponseEntity<List<Institution>> getIntitutionListContainsCategory(@PathVariable List<String> categoryId) {
        List<Institution> allIntitution = institutionService.findAll();
        List<Institution> findedInstitution = allIntitution.stream()
                .filter(institution -> {
                    List<Category> categoryStream = institution.getCategory().stream()
                            .filter(category -> {
                                List<String> categoryNameList = categoryId.stream()
                                        .filter(c -> category.getId() == Integer.parseInt(c))
                                        .collect(Collectors.toList());
                                boolean returnCondition = categoryNameList.size() > 0 ? true : false;
                                return returnCondition;
                            }).collect(Collectors.toList());
                    boolean returnCondition = categoryStream.size() == categoryId.size() ? true : false;
                    return returnCondition;

                }).collect(Collectors.toList());

        if (findedInstitution.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(findedInstitution);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }
}
