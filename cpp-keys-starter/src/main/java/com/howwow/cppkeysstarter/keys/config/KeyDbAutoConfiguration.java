package com.howwow.cppkeysstarter.keys.config;

import com.howwow.cppkeysstarter.keys.PrivateKeyService;
import com.howwow.cppkeysstarter.keys.repository.PrivateKeyRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(KeyDbProperties.class)
@EntityScan(basePackages = "com.howwow.cppkeysstarter.keys.entity")
@EnableJpaRepositories(
        basePackages = "com.howwow.cppkeysstarter.keys.repository",
        entityManagerFactoryRef = "keyEntityManagerFactory",
        transactionManagerRef = "keyTransactionManager"
)
public class KeyDbAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DataSource keyDataSource(KeyDbProperties properties) {
        return DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean keyEntityManagerFactory(DataSource keyDataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(keyDataSource);
        emf.setPackagesToScan("com.howwow.cppkeysstarter.keys.entity");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.hbm2ddl.auto", "validate");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        emf.setJpaPropertyMap(jpaProperties);

        return emf;
    }

    @Bean
    public JpaTransactionManager keyTransactionManager(EntityManagerFactory keyEntityManagerFactory) {
        return new JpaTransactionManager(keyEntityManagerFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public PrivateKeyService privateKeyService(
            PrivateKeyRepository repository,
            KeyDbProperties properties
    ) {
        return new PrivateKeyService(
                properties.getCardName(),
                properties.getJwtName(),
                repository
        );
    }
}
