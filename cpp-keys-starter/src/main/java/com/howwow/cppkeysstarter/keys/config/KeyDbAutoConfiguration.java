package com.howwow.cppkeysstarter.keys.config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(KeyDbProperties.class)
@EnableJpaRepositories(
        basePackages = "com.howwow.cppkeysstarter.keys",
        entityManagerFactoryRef = "keyEntityManagerFactory",
        transactionManagerRef = "keyTransactionManager"
)
@ComponentScan(basePackages = "com.howwow.cppkeysstarter.keys")
public class KeyDbAutoConfiguration {

    @Bean(name = "keyDataSource")
    @ConditionalOnMissingBean(name = "keyDataSource")
    public DataSource keyDataSource(KeyDbProperties properties) {
        return DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .build();
    }

    @Bean(name = "keyEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean keyEntityManagerFactory(
            @Qualifier("keyDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.howwow.keysstarter.keys");
        emf.setPersistenceUnitName("keydb");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.hbm2ddl.auto", "validate");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        emf.setJpaPropertyMap(jpaProperties);

        return emf;
    }

    @Bean(name = "keyTransactionManager")
    public PlatformTransactionManager keyTransactionManager(
            @Qualifier("keyEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
