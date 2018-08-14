package converter.validators;

import converter.Contact;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class MyValidationService implements ConstraintValidator<MyValidator, Contact> {

    @Override
    public void initialize(MyValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(Contact value, ConstraintValidatorContext context) {
        return value.getFirstName() != null && value.getLastName() != null;
    }
}
