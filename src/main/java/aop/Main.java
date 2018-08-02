package aop;

import org.springframework.aop.framework.ProxyFactory;

public class Main {
    public static void main(String[] args) {
        MessageWriter messageWriter = new MessageWriter();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new MessageDecorator());
        proxyFactory.setTarget(messageWriter);

        messageWriter.write();

        MessageWriter proxy = (MessageWriter) proxyFactory.getProxy();
        proxy.write();
    }
}
