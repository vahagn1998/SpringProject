package remote;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import remote.test.TestConfig;

@Configuration
@ComponentScan("remote")
@Import({AppConfig.class, TestConfig.class})
@EnableWebMvc
public class RootApplicationConfig implements WebMvcConfigurer {

}
