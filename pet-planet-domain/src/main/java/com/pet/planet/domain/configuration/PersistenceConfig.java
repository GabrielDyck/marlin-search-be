package com.pet.planet.domain.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class PersistenceConfig {

        private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceConfig.class);

        @Value("${pet.planet.hibernate.database.url}")
        private String url;
        @Value("${pet.planet.hibernate.username}")
        private String username;
        @Value("${pet.planet.hibernate.password}")
        private String password;
        @Value("${pet.planet.hibernate.driverclassname}")
        private String driverClassName;
        @Value("${pet.planet.hibernate.dialect}")
        private String dialect;
        @Value("${pet.planet.hibernate.show_sql}")
        private String showSql;

    @Value("${pet.planet.hibernate.minPoolSize}")
    private Integer minPoolSize;
    @Value("${pet.planet.hibernate.maxPoolSize}")
    private Integer maxPoolSize;
    @Value("${pet.planet.hibernate.maxStatements}")
    private Integer maxStatements;
    @Value("${pet.planet.hibernate.idleConnectionTestPeriod}")
    private Integer idleConnectionTestPeriod;
    @Value("${pet.planet.hibernate.checkoutTimeout}")
    private Integer checkoutTimeout;


        @Bean
        public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
            return new PersistenceExceptionTranslationPostProcessor();
        }

        @Bean
        public DataSource dataSource() {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();

            try {

                dataSource.setDriverClass(this.driverClassName);
            } catch (PropertyVetoException e) {
                LOGGER.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
            dataSource.setJdbcUrl(this.url);
            dataSource.setUser(this.username);
            dataSource.setPassword(this.password);
            dataSource.setMinPoolSize(this.minPoolSize);
            dataSource.setMaxPoolSize(this.maxPoolSize);
            dataSource.setMaxStatements(this.maxStatements);
            dataSource.setIdleConnectionTestPeriod(this.idleConnectionTestPeriod);
            dataSource.setCheckoutTimeout(this.checkoutTimeout);
            return dataSource;
        }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.pet.planet.domain.dao","com.pet.planet.domain.model.dao");
        factory.getJpaPropertyMap().put("jadira.usertype.autoRegisterUserTypes", "true");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
}