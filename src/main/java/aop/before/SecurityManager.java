package aop.before;

import org.springframework.stereotype.Service;

@Service("sec")
public class SecurityManager {
    private static ThreadLocal<Person> threadLocal = new ThreadLocal<>();

    public void login(String login, String pass) {
        threadLocal.set(new Person(login, pass));
    }

    public void login(Person person) {
        threadLocal.set(person);
    }

    public void logout() {
        threadLocal.set(null);
    }

    public Person getLoggedOnPerson() {
        return threadLocal.get();
    }
}
