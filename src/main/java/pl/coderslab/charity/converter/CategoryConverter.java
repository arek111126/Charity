package pl.coderslab.charity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.service.CategoryService;

import javax.persistence.Convert;

import java.lang.annotation.Annotation;
import java.sql.Driver;

@Component
public class CategoryConverter implements Converter<String, Category> {

    @Autowired
    CategoryService categoryService;

    @Override
    public Category convert(String id) {
        int parseInt = Integer.parseInt(id);
        return categoryService.findById(parseInt);
    }
}
