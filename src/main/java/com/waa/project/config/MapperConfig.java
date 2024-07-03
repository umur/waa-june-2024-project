package com.waa.project.config;

import com.waa.project.contracts.CreateStudentRequest;
import com.waa.project.entity.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<CreateStudentRequest, Student>() {

            @Override
            protected void configure() {
                skip().setMajor(null);
                skip().setId(null);
            }
        });

        return modelMapper;
    }
}
