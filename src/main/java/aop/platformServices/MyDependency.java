package aop.platformServices;


public class MyDependency {
    public void foo(int value) {
        System.out.println("foo " + value);
    }

    public void bar() {
        System.out.println("bar");
    }
}
