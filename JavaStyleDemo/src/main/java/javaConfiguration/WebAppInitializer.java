package javaConfiguration;

import javaConfiguration.RootConfig;
import javaConfiguration.WebConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import java.util.logging.Logger;

/*
 * Spring Mvc的配置
 *createDate: 2018年12月21日
 * author: dz
 * */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private final static Logger LOG = Logger.getLogger(String.valueOf(WebAppInitializer.class));

    @Override
    protected Class<?>[] getRootConfigClasses() {
        LOG.info("root配置类初始化");
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        LOG.info("------web配置类初始化------");
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        LOG.info("------映射根路径初始化------");
        return new String[]{"/"};//请求路径映射，根路径
    }

    @Override
    protected Filter[] getServletFilters() {
        LOG.info("-----编码过滤配置-------");
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("UTF-8");
        return new Filter[]{encodingFilter};
    }
}
