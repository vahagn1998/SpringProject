package aop.aspectjAnnotation;

import org.springframework.stereotype.Component;

@Component("myDependency")
public class MyDependency {
    public void foo(int value) {
        System.out.println("foo " + value);
    }

    public void bar() {
        System.out.println("bar");
    }
}
