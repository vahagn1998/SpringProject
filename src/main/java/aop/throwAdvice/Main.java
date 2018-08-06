package aop.throwAdvice;

import org.springframework.aop.framework.ProxyFactory;

public class Main {
    public static void main(String[] args) {
        ErrorBean errorBean = new ErrorBean();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleThrowsAdvice());
        proxyFactory.setTarget(errorBean);
        ErrorBean proxy = (ErrorBean) proxyFactory.getProxy();
        try {
            throw new Error();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }

    }
}
