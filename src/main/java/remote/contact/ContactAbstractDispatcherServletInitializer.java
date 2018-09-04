package remote.contact;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;
import remote.AppConfig;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

public class ContactAbstractDispatcherServletInitializer extends AbstractDispatcherServletInitializer {
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ContactConfig.class);
        return applicationContext;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/contact/listData", "/contact/1"};
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }

    @Override
    protected String getServletName() {
        return "contact";
    }

    @Override
    protected Filter[] getServletFilters() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestContextFilter());
        registrationBean.setName("springSecurityFilterChain");
        registrationBean.addUrlPatterns("/contact/*");
        return new Filter[]{registrationBean.getFilter()};
    }
}
