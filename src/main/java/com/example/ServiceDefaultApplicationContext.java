package com.example;

import com.example.config.AuthorizationServerConfig;
import com.example.config.ResourceServerConfig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@SpringBootConfiguration
@Import({
        AuthorizationServerConfig.class,
        ResourceServerConfig.class,
        // persistence
//        DataSourceAutoConfiguration.class
})
public class ServiceDefaultApplicationContext {

    @Bean
    @ConfigurationProperties(prefix = "oauth2-data-db")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
