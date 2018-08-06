package aop.before;

import org.springframework.lang.NonNull;

public class MessageWriter {

    public void write(@NonNull Person person) {
        System.out.println(person.getLogin() + " " + person.getPass());
    }
}
