package aop.methodInterceptor;

public class WorkerBean {
    public void doSomthing(int f) {
        for (int i = 0; i < f; i++) {
            work(i);
        }
    }

    private void work(int i) {
        System.out.println(i);
    }
}
