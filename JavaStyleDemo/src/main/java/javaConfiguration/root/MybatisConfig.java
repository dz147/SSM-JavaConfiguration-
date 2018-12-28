package javaConfiguration.root;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

/**
 * <p>Title: DruidDataSourceConfig.java</p>
 * <p>Description: 数据源属性配置</p>
 * <p>CreateDate: 2018年12月20日</p>
 *
 * @author dz
 */
@Configuration
@MapperScan(basePackages = "com.dznfit.dao")
@EnableTransactionManagement
public class MybatisConfig {

    @Value("${driver}")
    private String driver;

    @Value("${url}")
    private String url;

    @Value("${name}")
    private String user;

    @Value("${password}")
    private String password;

    @Autowired
    private Environment environment;

    @Bean("dataSource")
    public DataSource dataSourceConfig() throws PropertyVetoException {
        // 使用c3p0
        ComboPooledDataSource source = new ComboPooledDataSource();
        source.setDriverClass(driver);
        source.setJdbcUrl(url);
        source.setUser(user);
        source.setPassword(password);
        return source;
    }

    @Bean("sqlSessionFactoryBean")
    public SqlSessionFactoryBean sqlSessionFactoryBeanConfig() throws PropertyVetoException, IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(this.dataSourceConfig());
        factoryBean.setTypeAliasesPackage("com.dznfit.entity");
        factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        factoryBean.setMapperLocations(resolver.getResources("Mapper/*.xml"));
        return factoryBean;
    }
    /* <!-- 事务管理器 对mybatis操作数据库事务控制，spring使用jdbc的事务控制类 -->*/
    @Bean("transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManagerConfig() throws PropertyVetoException {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(this.dataSourceConfig());
        return manager;
    }

    private Properties hProps() {
        Properties p = new Properties();
        p.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        p.put("hibernate.cache.use_second_level_cache", "true");
        p.put("hibernate.cache.use_query_cache", "true");
        p.put("hibernate.cache.provider_class",
                "org.hibernate.cache.EhCacheProvider");
        p.put("hibernate.cache.provider_configuration_file_resource_path",
                "ehcache.xml");
        p.put("hibernate.show_sql", "true");
        p.put("hibernate.hbm2ddl.auto", "update");
        p.put("hibernate.generate_statistics", "true");
        p.put("hibernate.cache.use_structured_entries", "true");
        return p;
    }
}
