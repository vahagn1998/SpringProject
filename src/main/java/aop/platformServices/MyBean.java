package aop.platformServices;

public class MyBean {
    private MyDependency mydependency;

    public void execute() {
        mydependency.foo(100);
        mydependency.foo(101);
        mydependency.bar();
    }

    public void setMyDependency(MyDependency myDependency) {
        this.mydependency = myDependency;
    }
}
