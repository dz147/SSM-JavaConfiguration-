package javaConfiguration;

import javaConfiguration.root.MybatisConfig;
import javaConfiguration.root.RedisConfig;
import javaConfiguration.root.ShiroConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * <p>Title: RootConfig.java</p>
 * <p>Description: 配置类，用于管理ContextLoadListener创建的上下文的bean</p>
 * <p>CreateDate: 2018年12月20日</p>
 *
 * @author dz
 */
@Configuration
@ComponentScan(basePackages = {"com.dznfit.service"})
@PropertySource("classpath:jdbc.properties")
@PropertySource("classpath:redis.properties")
@Import({MybatisConfig.class, ShiroConfig.class, RedisConfig.class})
public class RootConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer sourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}
