package com.gwtsystem.server.dao.configuration;

import com.googlecode.flyway.core.Flyway;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * Configuration class Hibernate ORM with Spring annotations for application.
 * Created 30.07.15.
 *
 * @author Alexander Leonovich
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.gwtsystem"})
@PropertySource(value = {"classpath:application.properties"})
public class DaoConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean(initMethod = "migrate", name = "flyway")
    Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setInitOnMigrate(true);
        flyway.setValidateOnMigrate(true);
        flyway.setInitVersion("1.1");
        //flyway.setInitVersion(MigrationVersion.LATEST);
        flyway.setSchemas("isystem");
        flyway.setLocations("classpath:db.migration");
        flyway.setDataSource(dataSource());
        return flyway;
    }


    /**
     * Creating and configure DataSource from property-file.
     *
     * @return dataSource.
     */
    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));

        return dataSource;
    }

    /**
     * Creating and configure local sessionFactory.
     *
     * @return sessionFactory.
     */
    @Bean
    @DependsOn("flyway")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.gwtsystem.domain");
        return sessionFactory;
    }

    /**
     * Configuration of Transaction Manager.
     *
     * @param sessionFactory current SessionFactory.
     * @return txManager.
     */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        txManager.setDataSource(dataSource());
        return txManager;
    }

    /**
     * Configuration of Hibernate properties.
     *
     * @return properties.
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.connection.characterEncoding",
                environment.getRequiredProperty("hibernate.connection.characterEncoding"));
        properties.put("hibernate.hbm2ddl.auto",
                environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }
}
