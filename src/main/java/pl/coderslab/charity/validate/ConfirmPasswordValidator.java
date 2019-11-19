package pl.coderslab.charity.validate;

import pl.coderslab.charity.entity.authentication.User;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {
    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        User user = (User) o;

       return user.getPassword().equals(user.getRetypePassword());


    }
}
