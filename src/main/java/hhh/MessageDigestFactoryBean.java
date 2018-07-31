package hhh;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component("messageDigest")
public class MessageDigestFactoryBean implements FactoryBean<MessageDigest>, InitializingBean {
    private MessageDigest messageDigest = null;
    private String algorithm = "SHA1";

    @Override
    public MessageDigest getObject() throws Exception {
        System.out.println("getObject");
        return messageDigest;
    }

    @Override
    public Class<?> getObjectType() {
        return MessageDigest.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        messageDigest = MessageDigest.getInstance(algorithm);
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
