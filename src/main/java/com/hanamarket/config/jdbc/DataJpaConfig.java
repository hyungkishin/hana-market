package com.hanamarket.config.jdbc;

import com.hanamarket.HanaMarketApplication;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableTransactionManagement
@Configuration
public class DataJpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            DataSource dataSource,
            ConfigurableListableBeanFactory beanFactory
    ) {
        Map<String, String> properties = new HashMap<>();
        LocalContainerEntityManagerFactoryBean emfb = builder
                .dataSource(dataSource)
                .properties(properties)
                .packages(HanaMarketApplication.BASE_PACKAGE_MYAPP)
                .build();

        /**
         * 참고: {@link SpringBeanContainer}
         */
        emfb.getJpaPropertyMap().put(AvailableSettings.BEAN_CONTAINER, new SpringBeanContainer(beanFactory));
        return emfb;
    }

}
