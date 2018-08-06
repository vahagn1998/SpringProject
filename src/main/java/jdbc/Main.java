package jdbc;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext genericXmlApplicationContext =
                new GenericXmlApplicationContext("classpath:../../resources/main/jdbc/app-context.xml");
        JdbcDao jdbcDao = genericXmlApplicationContext.getBean("jdbcDao", JdbcDao.class);
        System.out.println(jdbcDao.findLastNameById(1L));
    }
}
