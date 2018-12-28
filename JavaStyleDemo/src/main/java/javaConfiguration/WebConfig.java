package javaConfiguration;

import com.dznfit.exception.CustomExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>Title: WebConfig.java</p>
 * <p>Description: 配置类，用于定义DispatcherServlet上下文的bean</p>
 * <p>CreateDate: 2018年12月20日</p>
 *
 * @author dz
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.dznfit.controller")
@ComponentScan(basePackages = "com.dznfit.cache")
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }

    @Bean
    public CustomExceptionResolver getExceptionResolver(){
        return new CustomExceptionResolver();
    }


}
