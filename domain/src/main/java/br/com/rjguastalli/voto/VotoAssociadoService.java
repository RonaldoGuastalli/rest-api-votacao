package br.com.rjguastalli.voto;

import br.com.rjguastalli.GenericException;
import br.com.rjguastalli.model.UserInfoEnum;
import br.com.rjguastalli.model.UserInfoResponse;
import br.com.rjguastalli.pauta.service.PautaService;
import br.com.rjguastalli.restclient.UserInfoRestClient;
import br.com.rjguastalli.sessao.repository.SessaoRepository;
import br.com.rjguastalli.sessao.repository.entity.SessaoEntity;
import br.com.rjguastalli.sessao.service.SessaoService;
import br.com.rjguastalli.voto.mapper.VotoAssociadoMapper;
import br.com.rjguastalli.voto.model.VotoAssociadoModelInput;
import br.com.rjguastalli.voto.repository.VotoAssociadoRepository;
import br.com.rjguastalli.voto.repository.entity.VotoAssociadoEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class VotoAssociadoService {

    private SessaoService sessaoService;
    private PautaService pautaService;
    private SessaoRepository sessaoRepository;
    private VotoAssociadoRepository votoAssociadoRepository;
    private UserInfoRestClient userInfoRestClient;

    public void computarVoto(Long pautaId, Long sessaoId, VotoAssociadoModelInput votoAssociadoModelInput) {

        verificarCpfValido(votoAssociadoModelInput.getCpf());
        verificaAssociadoJaVotou(sessaoId, votoAssociadoModelInput.getCpf());
        var sessao = sessaoService.buscarSessao(sessaoId);
        sessaoEstaNaPauta(sessao, pautaId);
        dataDaSessaoValidaParaVotacao(sessao);

        var sessaoVoto = VotoAssociadoMapper.mapToVotoAssociadoEntity(votoAssociadoModelInput);
        if (!Objects.isNull(sessaoVoto)) {
            sessaoVoto.setSessao(sessao);
            votoAssociadoRepository.save(sessaoVoto);
        }


    }

    private void verificarCpfValido(String cpf) {
        UserInfoResponse userInfoResponse = userInfoRestClient.buscarUsuario(cpf);
        if (UserInfoEnum.UNABLE_TO_VOTE.equals(userInfoResponse.getResult())) {
            throw new GenericException("Este CPF não está inválido ou não é apto para votar nesta sessão.",
                    HttpStatus.BAD_REQUEST);
        }

        if (UserInfoEnum.SOME_EXCEPTION_OCCURRED.equals(userInfoResponse.getResult())) {
            throw new GenericException("Impossível prosseguir a votação para este CPF.",
                    HttpStatus.BAD_REQUEST);
        }
    }

    private void sessaoEstaNaPauta(SessaoEntity sessaoEntity, Long pautaId) {
        if (pautaId != sessaoEntity.getPautaId().getId()) {
            throw new GenericException("Esta sessão não consta para esta pauta.", HttpStatus.BAD_REQUEST);
        }
    }

    private void dataDaSessaoValidaParaVotacao(SessaoEntity sessaoEntity) {
        if(LocalDateTime.now().isAfter(sessaoEntity.getDataTermino()) ||
                LocalDateTime.now().isBefore(sessaoEntity.getDataAbertura())) {
            throw new GenericException("Esta sessão esta com o perído inválido (data abertura: "
                    .concat(sessaoEntity.getDataAbertura().toString()
                    .concat(" data fechamento: ").concat(sessaoEntity.getDataTermino().toString()))
                    , HttpStatus.BAD_REQUEST);
        }
    }

    private void verificaAssociadoJaVotou(Long sessaoId, String cpfAssociado) {
        Boolean jaVotou = votoAssociadoRepository.existsBySessaoIdAndCpfAssociado(sessaoId, cpfAssociado);
        if (jaVotou) {
            throw new GenericException("Este CPF: "
                    .concat(cpfAssociado)
                    .concat(" já votou nesta sessão e seu voto já foi computado"), HttpStatus.BAD_REQUEST);
        }
    }

    private List<VotoAssociadoEntity> contabilizarVotosDaSessaoPorPauta(Long pautaId) {
        return votoAssociadoRepository.findBySessaoPautaId(pautaId);
    }
}
