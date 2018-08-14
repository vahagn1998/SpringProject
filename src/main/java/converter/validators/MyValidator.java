package converter.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Documented
@Constraint(validatedBy = MyValidationService.class)
public @interface MyValidator {
    String message() default "my message";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
