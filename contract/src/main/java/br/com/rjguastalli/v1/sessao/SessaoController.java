package br.com.rjguastalli.v1.sessao;

import br.com.rjguastalli.v1.sessao.facade.SessaoContractFacade;
import br.com.rjguastalli.v1.sessao.model.request.SessaoResponse;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/sessao")
@Api("Serviço para operações na sessão")
@AllArgsConstructor
public class SessaoController {

    private SessaoContractFacade sessaoContractFacade;

    @PostMapping
    @ApiOperation(value = "Criar uma nova sessão", response = br.com.rjguastalli.v1.sessao.model.response.SessaoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = br.com.rjguastalli.v1.sessao.model.response.SessaoResponse.class)
    })
    public ResponseEntity<br.com.rjguastalli.v1.sessao.model.response.SessaoResponse> criarSessao(@ApiParam(value = "informações da sessao da pauta selecionada.",
                                                                required = true)
                                                                                                  @Valid @RequestBody SessaoResponse sessaoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sessaoContractFacade.criarNovaSessao(sessaoRequest));

    }
}
