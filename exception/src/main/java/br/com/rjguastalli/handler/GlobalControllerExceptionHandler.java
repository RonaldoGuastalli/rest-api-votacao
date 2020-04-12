package br.com.rjguastalli.handler;

import br.com.rjguastalli.GenericException;
import br.com.rjguastalli.handler.response.ErrorDetail;
import br.com.rjguastalli.handler.response.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Configuration
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final String DEFAULT_LANGUAGE_TAG = "pt-BR";

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorInfo> genericException(GenericException error, HttpServletRequest httpServletRequest) {
        log.error("GlobalControllerExceptionHandler.genericException={}", error);
        var errorInfo = ErrorInfo.builder()
                .errors(List.of(ErrorDetail.builder()
                        .message(error.getMessage())
                        .type(error.getStatus().name())
                        .build())
                )
                .language(DEFAULT_LANGUAGE_TAG)
                .namespace(httpServletRequest.getServletPath())
                .build();
        return ResponseEntity.status(error.getStatus()).body(errorInfo);
    }

}
