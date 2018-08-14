package converter;

import org.joda.time.DateTime;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext genericXmlApplicationContext =
                new GenericXmlApplicationContext("classpath:../../resources/main/converter/app-config.xml");
        Contact vahagn = genericXmlApplicationContext.getBean("vahagn", Contact.class);
        System.out.println(vahagn.getBirthDate());
        ConversionService conversionService = genericXmlApplicationContext.getBean(ConversionService.class);
        DateTime convert = conversionService.convert("1998-12-12", DateTime.class);
        System.out.println(convert);
    }
}
