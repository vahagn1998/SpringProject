package aop.aspectjAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBean {
    private MyDependency mydependency;

    public void execute() {
        mydependency.foo(100);
        mydependency.foo(101);
        mydependency.bar();
    }

    @Autowired
    public void setMyDependency(MyDependency myDependency) {
        this.mydependency = myDependency;
    }
}
