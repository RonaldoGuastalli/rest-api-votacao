package br.com.rjguastalli.v1.sessao;

import br.com.rjguastalli.sessao.service.SessaoService;
import br.com.rjguastalli.v1.sessao.mapper.SessaoMapper;
import br.com.rjguastalli.v1.sessao.model.request.SessaoRequest;
import br.com.rjguastalli.v1.sessao.model.response.SessaoResponse;
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

    private SessaoService sessaoService;

    @PostMapping
    @ApiOperation(value = "Criar uma nova sessão", response = SessaoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SessaoResponse.class)
    })
    public ResponseEntity<SessaoResponse> criarSessao(@ApiParam(value = "informações da sessao da pauta selecionada.",
                                                                required = true)
                                                      @Valid @RequestBody SessaoRequest sessaoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SessaoMapper
                        .mapToSessaoResponse(sessaoService
                                .criarNovaSessao(SessaoMapper.mapToSessaoModel(sessaoRequest))));

    }
}
