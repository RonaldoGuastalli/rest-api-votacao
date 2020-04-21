package br.com.rjguastalli.restclient;

import br.com.rjguastalli.model.UserInfoEnum;
import br.com.rjguastalli.model.UserInfoResponse;
import br.com.rjguastalli.properties.ApiProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserInfoRestClientTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ApiProperties apiProperties;

    @InjectMocks
    private UserInfoRestClient userInfoRestClient;

    private UserInfoResponse userInfoResponse;

    @BeforeEach
    void setUp() {
        userInfoResponse = UserInfoResponse.builder().result(UserInfoEnum.ABLE_TO_VOTE).build();
        when(apiProperties.getUserInfoUri()).thenReturn("https://user-info.herokuapp.com");
    }

    @Test
    void testDeveRetornaUsarioAbleToVote() {
        when(restTemplate.getForEntity(anyString(), any()))
                .thenReturn(ResponseEntity.of(Optional.of(userInfoResponse)));

        var response = userInfoRestClient.buscarUsuario("19839091069");
        assertEquals(UserInfoEnum.ABLE_TO_VOTE, response.getResult());
    }

    @Test
    void testDeveRetornaUsarioUnableToVote() {
        userInfoResponse.setResult(UserInfoEnum.UNABLE_TO_VOTE);
        when(restTemplate.getForEntity(anyString(), any()))
                .thenReturn(ResponseEntity.of(Optional.of(userInfoResponse)));

        var response = userInfoRestClient.buscarUsuario("12345678");
        assertEquals(UserInfoEnum.UNABLE_TO_VOTE, response.getResult());
    }

    @Test
    void testDeveRetornarUmaExceptionPoisUsuarioInvalido() {
        userInfoResponse.setResult(UserInfoEnum.SOME_EXCEPTION_OCCURRED);
        when(restTemplate.getForEntity(anyString(), any()))
                .thenReturn(ResponseEntity.of(Optional.of(userInfoResponse)));

        var response = userInfoRestClient.buscarUsuario("1");
        assertEquals(UserInfoEnum.SOME_EXCEPTION_OCCURRED, response.getResult());
    }

    @Test
    void testDeveRetornarUmErroDuranteARequisicao() {
        userInfoResponse.setResult(UserInfoEnum.SOME_EXCEPTION_OCCURRED);
        when(restTemplate.getForEntity(anyString(), any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        var response = userInfoRestClient.buscarUsuario("1");
        assertEquals(UserInfoEnum.SOME_EXCEPTION_OCCURRED, response.getResult());
    }

}