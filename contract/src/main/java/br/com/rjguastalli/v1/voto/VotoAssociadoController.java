package br.com.rjguastalli.v1.voto;

import br.com.rjguastalli.handler.response.ErrorInfo;
import br.com.rjguastalli.v1.pauta.model.response.PautaResponse;
import br.com.rjguastalli.v1.voto.mapper.VotoAssociadoMapper;
import br.com.rjguastalli.v1.voto.model.request.VotoAssociadoRequest;
import br.com.rjguastalli.voto.VotoAssociadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/")
@Api("Serviço para votação dos associados.")
public class VotoAssociadoController {

    private VotoAssociadoService votoAssociadoService;

    @PostMapping(path = "pauta/{pauta_id}/sessao/{sessao_id}/voto-sessao",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PautaResponse.class),
            @ApiResponse(code = 400, message = "Parametros inválidos", response = ErrorInfo.class),
            @ApiResponse(code = 404, message = "Erro pauta ou sessão não encontrado", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Erro interno na aplicação", response = ErrorInfo.class)
    })
    public ResponseEntity<Void> votar(@ApiParam(value = "Identificador da pauta", required = true)
                                                       @PathVariable(value = "pauta_id") Long pautaId,

                                                       @ApiParam(value = "Identificador da sessão", required = true)
                                                       @PathVariable("sessao_id") Long sessaoId,

                                                       @ApiParam(value = "Dados da sessão da pauta especificada", required = true)
                                                       @Valid @RequestBody VotoAssociadoRequest votoAssociadoRequest) {
        votoAssociadoService.computarVoto(pautaId, sessaoId, VotoAssociadoMapper.mapToVotoAssociadoModelInput(votoAssociadoRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
