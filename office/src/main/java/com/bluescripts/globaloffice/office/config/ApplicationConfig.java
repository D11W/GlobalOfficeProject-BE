package com.bluescripts.globaloffice.office.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
public class ApplicationConfig implements Serializable {
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

}
