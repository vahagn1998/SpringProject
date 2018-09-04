package scheduling;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext applicationContext =
                new GenericXmlApplicationContext("classpath:../resources/scheduling/task.xml");
        AsyncService asyncService = applicationContext.getBean("asyncService", AsyncService.class);
        for (int i = 0; i < 5; i++) {
            asyncService.asyncTask();
        }
        Future<String> result1 = asyncService.asyncWithReturn("Vahagn");
        Future<String> result2 = asyncService.asyncWithReturn("David");
        Future<String> result3 = asyncService.asyncWithReturn("Narek");
        try {
            Thread.sleep(6000);
            System.out.println(result1.get());
            System.out.println(result2.get());
            System.out.println(result3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
