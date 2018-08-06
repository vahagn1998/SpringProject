package aop.dynamicPointcut;

public class SampleBean {
    public void foo(int x) {
        System.out.println("foo " + x);
    }

    public void bar() {
        System.out.println("bar");
    }
}
