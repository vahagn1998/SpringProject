package IoC.beanConfig;

import IoC.hhh.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration(value = "conf")
@ImportResource(value = "classpath:../../resources/main/spring/app-context.xml")
@PropertySource(value = "classpath:../../resources/main/spring/message.properties")
@ComponentScan(basePackages = {"com"})
@EnableTransactionManagement
//@Profile(value = "conf")
public class AppConfig {
    private final Environment environment;

    @Autowired
    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = "add")
    @Lazy
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Address person() {
        Address address = new Address();
        address.setStreet(environment.getProperty("message"));
        return address;
    }
}
