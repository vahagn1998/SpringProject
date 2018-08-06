package aop.before;

public class Person {
    private String login;
    private String pass;

    public Person(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public String getLogin() {
        return login;
    }
}
