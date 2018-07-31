package hhh;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Spliterator;

@Component("address")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Address implements ApplicationContextAware, BeanClassLoaderAware, BeanNameAware {

    @Value("10")
    private int numberOfFlat;
    private String street;

    public int getNumberOfFlat() {
        return numberOfFlat;
    }

    public void setNumberOfFlat(int numberOfFlat) {
        this.numberOfFlat = numberOfFlat;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(numberOfFlat++ + " fd");
        if (applicationContext instanceof AbstractApplicationContext) {
            ((AbstractApplicationContext) applicationContext).registerShutdownHook();
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println(numberOfFlat++);
        try {
            Class<?> aClass = classLoader.loadClass("hhh.Person");
            Object o1 = aClass.newInstance();
            if (o1 instanceof Person) {
                Person o = (Person) o1;
                System.out.println(o.getName());
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setBeanName(String name) {
        System.out.println(numberOfFlat++);
        System.out.println(name);
    }
}
