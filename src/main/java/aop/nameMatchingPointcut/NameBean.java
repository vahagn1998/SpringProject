package aop.nameMatchingPointcut;

public class NameBean {
    public void foo(){
        System.out.println("foo");
    }

    public void foo(int i) {
        System.out.println("foo " + i);
    }

    public void bar(){
        System.out.println("bar");
    }

    public void yup(){
        System.out.println("yup");
    }
}
