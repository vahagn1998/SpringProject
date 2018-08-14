package converter.validators;

import converter.Contact;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext genericXmlApplicationContext =
                new GenericXmlApplicationContext("classpath:../../resources/main/converter/validators/app-context.xml");
        Contact contact = new Contact();
        contact.setLastName("FFF");
//        Validator validator = genericXmlApplicationContext.getBean("contactValidator", Validator.class);
//        BeanPropertyBindingResult beanPropertyBindingResult = new BeanPropertyBindingResult(contact, "Vahagn");
//        ValidationUtils.invokeValidator(validator, contact, beanPropertyBindingResult);
//        List<ObjectError> errors = beanPropertyBindingResult.getAllErrors();
//        for (ObjectError error : errors) {
//            System.out.println(error.getCode());
//        }
        contact.setLastName(null);
    }
}
