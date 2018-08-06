package aop.annotationsMatchPoincut;

public class SampleAnnotationBean {
    @AdviceRequired
    public void foo(int i){
        System.out.println("foo " + i);
    }

    public void bar() {
        System.out.println("bar");
    }
}
