package com.hanamarket.config.jdbc.datasource;

import com.hanamarket.config.jdbc.ReplicationRoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;

@Slf4j
@Configuration
@ConditionalOnClass(DataSource.class)
@ConditionalOnProperty(prefix = "spring.datasource", name = {
        "write.driver-class-name",
        "write.jdbc-url",
        "write.username",
        "write.password",
        "read.driver-class-name",
        "read.jdbc-url",
        "read.username",
        "read.password"
})
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@AutoConfigureBefore(org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class)
@EnableConfigurationProperties({
        DataSourceReadProperties.class,
        DataSourceWriteProperties.class
})
public class DataSourceAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "datasource.write.hikari")
    public HikariDataSource writeDataSource(final DataSourceWriteProperties writeProperties) {

        log.info("### DataSourceAutoConfiguration: 'writeDataSource'");
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName(writeProperties.getDriverClassName())
                .url(writeProperties.getJdbcUrl())
                .username(writeProperties.getUsername())
                .password(writeProperties.getPassword())
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.read.hikari")
    public HikariDataSource readDataSource(final DataSourceReadProperties readProperties) {

        log.info("### DataSourceAutoConfiguration: 'readDataSource'");
        return DataSourceBuilder.create().type(HikariDataSource.class)
                .type(HikariDataSource.class)
                .driverClassName(readProperties.getDriverClassName())
                .url(readProperties.getJdbcUrl())
                .username(readProperties.getUsername())
                .password(readProperties.getPassword())
                .build();
    }

    @Bean
    public DataSource routingDataSource(final DataSourceWriteProperties writeProperties,
                                        final DataSourceReadProperties readProperties) {

        log.info("### DataSourceAutoConfiguration: 'routingDataSource'");
        return ReplicationRoutingDataSource.of(writeDataSource(writeProperties),
                readDataSource(readProperties));
    }

    @Bean
    @Primary
    public DataSource dataSource(final DataSourceWriteProperties writeProperties,
                                 final DataSourceReadProperties readProperties) {

        log.info("### DataSourceAutoConfiguration:'datasource'");
        return new LazyConnectionDataSourceProxy(routingDataSource(writeProperties, readProperties));
    }

}
