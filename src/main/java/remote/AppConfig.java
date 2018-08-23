package remote;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "remote")
@PropertySource(value = "classpath:../../resources/main/jdbc/jdbc.properties")
@EnableJpaRepositories(basePackages = "remote")
@EnableTransactionManagement(proxyTargetClass = true)
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
    private final Environment environment;
    private final ContactService contactService;

    @Autowired
    public AppConfig(Environment environment, ContactService contactService) {
        this.environment = environment;
        this.contactService = contactService;
    }

    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        basicDataSource.setUsername(environment.getProperty("jdbc.username"));
        basicDataSource.setPassword(environment.getProperty("jdbc.password"));
        basicDataSource.setUrl(environment.getProperty("jdbc.url"));
        return basicDataSource;
    }

    @Bean("transactionManager")
    public JpaTransactionManager jpaTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
        return jpaTransactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setJpaProperties(properties());
        localContainerEntityManagerFactoryBean.setPackagesToScan("remote");
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return localContainerEntityManagerFactoryBean;
    }

    @Bean("contactExporter")
    public HttpInvokerServiceExporter httpInvokerServiceExporter() {
        HttpInvokerServiceExporter httpInvokerServiceExporter = new HttpInvokerServiceExporter();
        httpInvokerServiceExporter.setService(contactService);
        httpInvokerServiceExporter.setServiceInterface(ContactService.class);
        return httpInvokerServiceExporter;
    }

    @Bean("entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        return localContainerEntityManagerFactoryBean().getObject();
    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.max_fetch_depth", "3");
        properties.put("hibernate.jdbc.fetch_size", "50");
        properties.put("hibernate.jdbc.batch_size", "10");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }
}
