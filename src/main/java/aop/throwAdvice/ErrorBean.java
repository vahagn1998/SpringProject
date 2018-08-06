package aop.throwAdvice;

public class ErrorBean {
    public void exception() throws Exception {
        throw new Exception("exception");
    }

    public void runtimeException() {
        throw new RuntimeException("runtime exception");
    }
}
