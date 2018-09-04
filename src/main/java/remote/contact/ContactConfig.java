package remote.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.ArrayList;
import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "remote.contact")
public class ContactConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.freeMarker();
        registry.order(1);
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPaths("/", "/WEB-INF");
        return freeMarkerConfigurer;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter httpMessageConverter = getMappingJackson2HttpMessageConverter();
        converters.add(marshallingHttpMessageConverter(castorMarshaller()));
        converters.add(httpMessageConverter);
    }

    private MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter httpMessageConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(new MediaType("application", "json"));
        httpMessageConverter.setSupportedMediaTypes(mediaTypes);
        return httpMessageConverter;
    }

    private MarshallingHttpMessageConverter marshallingHttpMessageConverter(CastorMarshaller castorMarshaller) {
        MarshallingHttpMessageConverter marshallingHttpMessageConverter = new MarshallingHttpMessageConverter();
        marshallingHttpMessageConverter.setMarshaller(castorMarshaller);
        marshallingHttpMessageConverter.setUnmarshaller(castorMarshaller);
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(new MediaType("application", "xml"));
        marshallingHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        return marshallingHttpMessageConverter;
    }

    private CastorMarshaller castorMarshaller() {
        CastorMarshaller castorMarshaller = new CastorMarshaller();
        ClassPathResource pathResource = new ClassPathResource("classpath:remote/oxm-mapping.xml");
        castorMarshaller.setMappingLocation(pathResource);
        return castorMarshaller;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(getMappingJackson2HttpMessageConverter());
//        converters.add(marshallingHttpMessageConverter(castorMarshaller()));
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }
}
