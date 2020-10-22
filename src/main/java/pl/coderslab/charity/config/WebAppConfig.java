package pl.coderslab.charity.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.coderslab.charity.utilities.CustomMapper;

@Configuration
@ComponentScan(basePackages = "pl.coderslab")
public class WebAppConfig implements WebMvcConfigurer {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
