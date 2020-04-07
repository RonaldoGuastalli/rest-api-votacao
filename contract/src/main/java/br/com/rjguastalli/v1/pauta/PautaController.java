package br.com.rjguastalli.v1.pauta;

import br.com.rjguastalli.pauta.service.PautaService;
import br.com.rjguastalli.v1.pauta.mapper.PautaMapper;
import br.com.rjguastalli.v1.pauta.model.request.PautaRequest;
import br.com.rjguastalli.v1.pauta.model.response.PautaResponse;
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
@RequestMapping("/v1/pauta")
@Api("Serviço para operações na pauta")
@AllArgsConstructor
public class PautaController {

    private PautaService pautaService;

    @PostMapping
    @ApiOperation(value = "Criar uma nova pauta", response = PautaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PautaResponse.class)
    })
    public ResponseEntity<PautaResponse> criarPauta(@ApiParam(value = "Assunto da pauta.", required = true)
                                                    @Valid @RequestBody PautaRequest pautaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PautaMapper.mapToPautaResponse(pautaService.criarNovaPauta(PautaMapper.mapToPautaModel(pautaRequest))));
    }

}
