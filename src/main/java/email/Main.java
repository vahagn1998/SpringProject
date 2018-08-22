package email;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(Config.class);
        EmailServiceImpl emailServiceImpl = applicationContext.getBean("emailServiceImpl", EmailServiceImpl.class);
//        emailServiceImpl.sendMessageWithAttachment("v.kostandyan@bk.ru", "Dzec", "Exav",
//                "C:/Users/Administrator/Documents/Text.txt", "Text.txt");
//        emailServiceImpl.sendTemplateMessage("v.kostandyan@bk.ru", "Dzec2", "Exav2");
        emailServiceImpl.sendMessageWithPreparator("v.kostandyan@bk.ru", "Dzec", "Exav",
                "C:/Users/Administrator/Documents/Text.txt", "Text.txt");
    }
}
