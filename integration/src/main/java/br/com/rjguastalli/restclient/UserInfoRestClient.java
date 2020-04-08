package br.com.rjguastalli.restclient;

import br.com.rjguastalli.model.UserInfoEnum;
import br.com.rjguastalli.model.UserInfoResponse;
import br.com.rjguastalli.properties.ApiProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
@Slf4j
public class UserInfoRestClient {

    private ApiProperties apiProperties;

    private RestTemplate restTemplate;

    public UserInfoResponse buscarUsuario(String cpf) {
        String url = apiProperties.getUserInfoUri().concat("/users/").concat(cpf);
        try {
            return restTemplate.getForEntity(url, UserInfoResponse.class).getBody();
        } catch (HttpClientErrorException ex) {
            log.info(ex.getMessage());
            return respostaErro();
        }
    }

    private UserInfoResponse respostaErro() {
        return UserInfoResponse.builder().result(UserInfoEnum.SOME_EXCEPTION_OCCURRED).build();
    }
}
