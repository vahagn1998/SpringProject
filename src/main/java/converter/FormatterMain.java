package converter;

import org.joda.time.DateTime;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;

public class FormatterMain {
    public static void main(String[] args) {
        GenericXmlApplicationContext genericXmlApplicationContext =
                new GenericXmlApplicationContext("classpath:../../resources/main/converter/formatter-config.xml");
        Contact vahagn = genericXmlApplicationContext.getBean("vahagn", Contact.class);
        ConversionService conversionService = genericXmlApplicationContext.getBean("conversionService", ConversionService.class);
        System.out.println(conversionService.convert(vahagn.getBirthDate(), String.class));
        System.out.println(conversionService.convert("1996-06-03", DateTime.class));
    }
}
