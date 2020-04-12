package br.com.rjguastalli.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ApiProperties {

    @Value("${user.info.herokuapp.com}")
    private String userInfoUri;
}
